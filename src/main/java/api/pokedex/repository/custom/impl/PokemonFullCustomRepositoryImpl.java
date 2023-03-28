package api.pokedex.repository.custom.impl;

import api.pokedex.model.ability.Ability;
import api.pokedex.model.moves.Moves;
import api.pokedex.model.pokemon.Pokemon;
import api.pokedex.model.type.Type;
import api.pokedex.repository.custom.PokemonFullCustomRepository;
import api.pokedex.response.pokemon.PokemonResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokemonFullCustomRepositoryImpl implements PokemonFullCustomRepository {

    private final MongoTemplate mongoTemplate;
    private final Logger logger = LoggerFactory.getLogger(PokemonFullCustomRepositoryImpl.class);

    @Autowired
    public PokemonFullCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public PokemonResponseDTO getPokemonByID(Integer id) {
        Query query = new Query(Criteria.where("_id").is(id));
        logger.info("Query : " + query);
        Pokemon pokemon = mongoTemplate.find(query, Pokemon.class).get(0);
        List<Type> types = getTypeByName(pokemon.getType());
        List<Ability> abilities = getAbilityDetailsByName(pokemon.getAbilities());
        List<Moves> moves = getMovesByName(pokemon.getMoves());
        return new PokemonResponseDTO(
                pokemon.getId(), pokemon.getName(), pokemon.getBaseExperience(), pokemon.getHeight(), pokemon.getWeight(),
                pokemon.getImage(), pokemon.getStats(), types, abilities, moves
        );
    }

    public List<Type> getTypeByName(List<String> names) {
        Query query = new Query(Criteria.where("name").in(names));
        logger.info("Query : " + query);
        return mongoTemplate.find(query, Type.class);
    }

    public List<Ability> getAbilityDetailsByName(List<String> abilities) {
        Query query = new Query(Criteria.where("name").in(abilities));
        logger.info("Query : " + query);
        return mongoTemplate.find(query, Ability.class);
    }

    public List<Moves> getMovesByName(List<String> moves) {
        Query query = new Query(Criteria.where("name").in(moves));
        logger.info("Query : " + query);
        return mongoTemplate.find(query, Moves.class);
    }
}

package api.pokedex.repository.custom.impl;

import api.pokedex.model.pokemon.Pokemon;
import api.pokedex.repository.custom.EvolutionChainRepository;
import api.pokedex.response.EvolutionChainResponse;
import api.pokedex.response.evolution.EvolutionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EvolutionChainRepositoryImpl implements EvolutionChainRepository {

    private final MongoTemplate mongoTemplate;
    private final Logger logger = LoggerFactory.getLogger(PokemonFullCustomRepositoryImpl.class);

    @Autowired
    public EvolutionChainRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public EvolutionChainResponse getEvolutionChain(Integer id) {
        Query query = new Query(Criteria.where("_id").is(id));
        logger.info("Query : " + query);
        Pokemon pokemon = mongoTemplate.find(query, Pokemon.class).get(0);
        if (pokemon.getEvolvesFrom() == null && pokemon.getEvolvesTo().isEmpty()) {
            return new EvolutionChainResponse(pokemon.getId(), pokemon.getName(), null);
        }

        if (pokemon.getEvolvesFrom() == null && !pokemon.getEvolvesTo().isEmpty()) {
            List<EvolutionDTO> evolutionDTOS = new ArrayList<>();
            for (String name : pokemon.getEvolvesTo()) {
                evolutionDTOS.add(getNextEvolutions(name));
            }
            return new EvolutionChainResponse(id, pokemon.getName(), new EvolutionDTO(pokemon.getName(), pokemon.getImage(), evolutionDTOS));
        }

        return null
    }

    private EvolutionDTO getNextEvolutions(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        logger.info("Query : " + query);
        Pokemon pokemon = mongoTemplate.find(query, Pokemon.class).get(0);
        List<EvolutionDTO> evolutionDTOS = new ArrayList<>();
        for (String pokemonName : pokemon.getEvolvesTo()) {
            Query nextQuery = new Query(Criteria.where("name").is(pokemonName));
            logger.info("Query : " + query);
            Pokemon nextPokemon = mongoTemplate.find(nextQuery, Pokemon.class).get(0);
            evolutionDTOS.add(new EvolutionDTO(nextPokemon.getName(), nextPokemon.getImage(), new ArrayList<>()));
        }
        return new EvolutionDTO(name, pokemon.getImage(), evolutionDTOS);
    }
}

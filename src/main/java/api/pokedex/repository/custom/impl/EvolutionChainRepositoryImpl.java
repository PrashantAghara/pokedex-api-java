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

        Pokemon currPokemon = pokemon;

        while (currPokemon.getEvolvesFrom() != null) {
            Query prev = new Query(Criteria.where("name").is(currPokemon.getEvolvesFrom()));
            logger.info("Query : " + prev);
            currPokemon = mongoTemplate.find(prev, Pokemon.class).get(0);
        }
        EvolutionChainResponse evolutionChainResponse = new EvolutionChainResponse();
        evolutionChainResponse.setId(pokemon.getId());
        evolutionChainResponse.setName(pokemon.getName());
        evolutionChainResponse.setEvolutions(new EvolutionDTO(currPokemon.getName(), currPokemon.getImage(), getAllEvolutions(currPokemon)));
        return evolutionChainResponse;
    }

    private List<EvolutionDTO> getAllEvolutions(Pokemon currPokemon) {
        List<String> nextPokemons = currPokemon.getEvolvesTo();
        List<EvolutionDTO> firstEvolutionDTOs = new ArrayList<>();

        for (String pokemonName : nextPokemons) {
            Query getNextEvolution = new Query(Criteria.where("name").is(pokemonName));
            logger.info("Query : " + getNextEvolution);
            Pokemon pokemon = mongoTemplate.find(getNextEvolution, Pokemon.class).get(0);
            List<EvolutionDTO> secondEvolutionDTOs = new ArrayList<>();
            if (!pokemon.getEvolvesTo().isEmpty()) {
                List<String> finalPokemons = pokemon.getEvolvesTo();
                for (String finalName : finalPokemons) {
                    Query getFinalEvolution = new Query(Criteria.where("name").is(finalName));
                    logger.info("Query : " + getFinalEvolution);
                    Pokemon finalPokemon = mongoTemplate.find(getFinalEvolution, Pokemon.class).get(0);
                    EvolutionDTO evolutionDTO = new EvolutionDTO(finalPokemon.getName(), finalPokemon.getImage(), new ArrayList<>());
                    secondEvolutionDTOs.add(evolutionDTO);
                }
            }
            EvolutionDTO secondForm = new EvolutionDTO();
            secondForm.setName(pokemon.getName());
            secondForm.setImage(pokemon.getImage());
            secondForm.setNextEvolution(secondEvolutionDTOs);
            firstEvolutionDTOs.add(secondForm);
        }

        return firstEvolutionDTOs;
    }
}

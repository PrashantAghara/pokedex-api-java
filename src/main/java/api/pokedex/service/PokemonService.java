package api.pokedex.service;

import api.pokedex.model.pokemon.Pokemon;
import api.pokedex.repository.custom.PokemonCustomRepository;
import api.pokedex.repository.custom.PokemonFullCustomRepository;
import api.pokedex.request.PokemonRequest;
import api.pokedex.response.PokemonResponse;
import api.pokedex.response.pokemon.PokemonResponseCompact;
import api.pokedex.response.pokemon.PokemonResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {
    private final PokemonCustomRepository pokemonCustomRepository;
    private final PokemonFullCustomRepository pokemonFullCustomRepository;
    private final Logger logger = LoggerFactory.getLogger(PokemonService.class);

    @Autowired
    public PokemonService(PokemonCustomRepository pokemonCustomRepository, PokemonFullCustomRepository pokemonFullCustomRepository) {
        this.pokemonCustomRepository = pokemonCustomRepository;
        this.pokemonFullCustomRepository = pokemonFullCustomRepository;
    }


    @Cacheable(value = "pokemons")
    public PokemonResponse getPokemonBasedOnTypeAndName(String name, String type, Integer limit, Long offset, String generation, String sort) {
        logger.info("Fetching Pokemon Details by below Filters & Pagination");
        logger.info("Name : " + name + " | Type :" + type);
        logger.info("Limit : " + limit + " | Skip : " + offset);
        List<Pokemon> pokemons = pokemonCustomRepository.getPokemonByNameAndType(name, type, limit, offset, generation, sort);
        List<PokemonResponseCompact> pokemonResponseCompacts = new ArrayList<>();
        for (Pokemon pokemon : pokemons) {
            pokemonResponseCompacts.add(new PokemonResponseCompact(pokemon.getId(), pokemon.getName(), pokemon.getImage(), pokemon.getType(), pokemon.getGeneration()));
        }
        PokemonResponse pokemonResponse = new PokemonResponse(pokemonResponseCompacts.size(), offset, pokemonResponseCompacts);
        logger.info("SUCCESS RESPONSE");
        return pokemonResponse;
    }

    @Cacheable("pokemons")
    public PokemonResponse getPokemonByID(Integer id) {
        System.out.println("Not Cache");
        logger.info("Fetching Pokemon with ID : " + id);
        PokemonResponseDTO pokemonResponseDTO = pokemonFullCustomRepository.getPokemonByID(id);
        return new PokemonResponse(pokemonResponseDTO);
    }

    @Cacheable(value = "pokemons", key = "#pokemonRequest.id")
    public PokemonResponse getPokemonById(PokemonRequest pokemonRequest) {
        return this.getPokemonByID(pokemonRequest.getId());
    }
}

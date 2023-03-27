package api.pokedex.service;

import api.pokedex.model.pokemon.Pokemon;
import api.pokedex.repository.custom.PokemonCustomRepository;
import api.pokedex.response.PokemonResponse;
import api.pokedex.response.pokemon.PokemonResponseCompact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {

    private final PokemonCustomRepository pokemonCustomRepository;
    private final Logger logger = LoggerFactory.getLogger(PokemonService.class);

    @Autowired
    public PokemonService(PokemonCustomRepository pokemonCustomRepository) {
        this.pokemonCustomRepository = pokemonCustomRepository;
    }


    public PokemonResponse getPokemonBasedOnTypeAndName(String name, String type, Integer limit, Long offset) {
        logger.info("Fetching Pokemon Details by below Filters & Paginations");
        if (limit == null) {
            limit = 20;
        }
        if (offset == null) {
            offset = 0L;
        }
        logger.info("Name : " + name + " | Type :" + type);
        logger.info("Limit : " + limit + " | Skip : " + offset);
        List<Pokemon> pokemons = pokemonCustomRepository.getPokemonByNameAndType(name, type, limit, offset);
        List<PokemonResponseCompact> pokemonResponseCompacts = new ArrayList<>();
        for (Pokemon pokemon : pokemons) {
            pokemonResponseCompacts.add(new PokemonResponseCompact(pokemon.getId(), pokemon.getName(), pokemon.getImage(), pokemon.getType(), pokemon.getStats()));
        }
        PokemonResponse pokemonResponse = new PokemonResponse(pokemonResponseCompacts.size(), offset, pokemonResponseCompacts);
        logger.info("SUCCESS RESPONSE");
        return pokemonResponse;
    }
}

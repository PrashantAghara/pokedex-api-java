package api.pokedex.service;

import api.pokedex.model.pokemon.Pokemon;
import api.pokedex.repository.PokemonRepository;
import api.pokedex.response.PokemonResponseCompact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }


    public List<PokemonResponseCompact> getPokemonBasedOnTypeAndName(String name, String type, Long limit, Long offset) {
        List<Pokemon> pokemons = null;
        if (name == null && type == null) {
            pokemons = pokemonRepository.getAllPokemon(limit, offset);
        } else if (type == null) {
            pokemons = pokemonRepository.getPokemonByName("^" + name, limit, offset);
        } else if (name == null) {
            List<String> types = Arrays.asList(type.split(","));
            pokemons = pokemonRepository.getPokemonByType(types, limit, offset);
        } else {
            List<String> types = Arrays.asList(type.split(","));
            pokemons = pokemonRepository.getPokemonByNameAndType("^" + name, types, limit, offset);
        }
        List<PokemonResponseCompact> pokemonResponseCompacts = new ArrayList<>();
        if (pokemons == null || pokemons.isEmpty()) {
            return pokemonResponseCompacts;
        }
        for (Pokemon pokemon : pokemons) {
            pokemonResponseCompacts.add(new PokemonResponseCompact(pokemon.getId(), pokemon.getName(), pokemon.getImage(), pokemon.getType(), pokemon.getStats()));
        }
        return pokemonResponseCompacts;
    }
}

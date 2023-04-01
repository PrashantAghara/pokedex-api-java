package api.pokedex.repository.custom;

import api.pokedex.model.pokemon.Pokemon;

import java.util.List;

public interface PokemonCustomRepository {
    List<Pokemon> getPokemonByNameAndType(String name, String types, int limit, Long skip, String attack, String defense, String speed, String sort);
}

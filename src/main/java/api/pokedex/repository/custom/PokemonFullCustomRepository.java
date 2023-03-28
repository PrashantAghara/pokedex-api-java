package api.pokedex.repository.custom;

import api.pokedex.response.pokemon.PokemonResponseDTO;

public interface PokemonFullCustomRepository {
    PokemonResponseDTO getPokemonByID(Integer id);
}

package api.pokedex.repository.custom;

import api.pokedex.model.moves.Moves;
import api.pokedex.response.pokemon.PokemonResponseDTO;

import java.util.List;

public interface PokemonFullCustomRepository {
    PokemonResponseDTO getPokemonByID(Integer id);
    List<Moves> getMovesByName(Integer id);
}

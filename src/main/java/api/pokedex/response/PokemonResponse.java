package api.pokedex.response;

import api.pokedex.response.pokemon.PokemonResponseCompact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonResponse {
    private int size;
    private Long offset;
    private List<PokemonResponseCompact> pokemons;
}

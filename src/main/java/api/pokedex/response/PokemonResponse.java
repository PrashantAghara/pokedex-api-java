package api.pokedex.response;

import api.pokedex.response.pokemon.PokemonResponseCompact;
import api.pokedex.response.pokemon.PokemonResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PokemonResponse {
    private Integer size;
    private Long offset;
    private List<PokemonResponseCompact> pokemons;
    private PokemonResponseDTO pokemon;
}

package api.pokedex.response;

import api.pokedex.model.pokemon.Stats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonResponseCompact {
    private Integer id;
    private String name;
    private String image;
    private List<String> type;
    private Stats stats;
}

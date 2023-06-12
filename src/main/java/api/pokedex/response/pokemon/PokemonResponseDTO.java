package api.pokedex.response.pokemon;

import api.pokedex.model.ability.Ability;
import api.pokedex.model.moves.Moves;
import api.pokedex.model.pokemon.Stats;
import api.pokedex.model.type.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PokemonResponseDTO {
    private Integer id;
    private String name;
    private Integer baseExperience;
    private Integer height;
    private Integer weight;
    private String image;
    private Stats stats;
    private List<Type> type;
    private List<Ability> abilities;
    private String generation;
}

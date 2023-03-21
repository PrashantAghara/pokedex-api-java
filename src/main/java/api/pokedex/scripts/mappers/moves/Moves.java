package api.pokedex.scripts.mappers.moves;

import api.pokedex.scripts.mappers.ability.Effect;
import api.pokedex.scripts.mappers.pokemon.ability.AbilityDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Moves {
    private Integer id;
    private String name;
    private Integer pp;
    private Integer power;
    private Integer accuracy;
    private AbilityDetail damage_class;
    private List<Effect> effect_entries;
    private AbilityDetail type;
}

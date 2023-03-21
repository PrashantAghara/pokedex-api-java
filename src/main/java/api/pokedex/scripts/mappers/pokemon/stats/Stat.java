package api.pokedex.scripts.mappers.pokemon.stats;

import api.pokedex.scripts.mappers.pokemon.ability.AbilityDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stat {
    private Integer base_stat;
    private AbilityDetail stat;
}

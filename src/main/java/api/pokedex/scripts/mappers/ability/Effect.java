package api.pokedex.scripts.mappers.ability;

import api.pokedex.scripts.mappers.pokemon.ability.AbilityDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Effect {
    private String effect;
    private AbilityDetail language;
    private String short_effect;
}

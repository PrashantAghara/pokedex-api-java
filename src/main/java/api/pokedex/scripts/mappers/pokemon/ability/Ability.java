package api.pokedex.scripts.mappers.pokemon.ability;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ability {
    private AbilityDetail ability;
    private Boolean is_hidden;
}

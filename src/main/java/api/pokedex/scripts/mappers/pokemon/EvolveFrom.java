package api.pokedex.scripts.mappers.pokemon;

import api.pokedex.scripts.mappers.pokemon.ability.AbilityDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvolveFrom {
    private AbilityDetail evolves_from_species;
    private AbilityDetail generation;
}

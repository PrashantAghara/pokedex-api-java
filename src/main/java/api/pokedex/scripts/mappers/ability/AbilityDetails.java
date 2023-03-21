package api.pokedex.scripts.mappers.ability;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AbilityDetails {
    private String name;
    private Integer id;
    private List<Effect> effect_entries;
}

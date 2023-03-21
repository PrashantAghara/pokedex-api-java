package api.pokedex.scripts.mappers.types;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Types {
    private Integer id;
    private String name;
    private DamageRelation damage_relations;
}

package api.pokedex.scripts.mappers.types;

import api.pokedex.scripts.mappers.pokemon.ability.AbilityDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DamageRelation {
    List<AbilityDetail> double_damage_from;
    List<AbilityDetail> double_damage_to;
    List<AbilityDetail> half_damage_from;
    List<AbilityDetail> half_damage_to;
    List<AbilityDetail> no_damage_from;
    List<AbilityDetail> no_damage_to;
}

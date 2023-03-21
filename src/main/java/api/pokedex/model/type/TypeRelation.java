package api.pokedex.model.type;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TypeRelation {
    private List<String> doubleDamageFrom;
    private List<String> doubleDamageTo;
    private List<String> halfDamageTo;
    private List<String> halfDamageFrom;
    private List<String> noDamageFrom;
    private List<String> noDamageTo;
}

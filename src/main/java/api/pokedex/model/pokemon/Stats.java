package api.pokedex.model.pokemon;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stats {
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int speed;
    private int specialDefense;
}

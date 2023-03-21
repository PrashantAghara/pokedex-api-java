package api.pokedex.scripts.mappers.pokemon;

import api.pokedex.scripts.mappers.pokemon.ability.Ability;
import api.pokedex.scripts.mappers.pokemon.images.Spirites;
import api.pokedex.scripts.mappers.pokemon.moves.Move;
import api.pokedex.scripts.mappers.pokemon.stats.Stat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Pokemon {
    private List<Ability> abilities;
    private Integer base_experience;
    private Integer height;
    private Integer id;
    private String name;
    private Integer weight;
    private List<Type> types;
    private List<Stat> stats;
    private List<Move> moves;
    private Spirites sprites;
}

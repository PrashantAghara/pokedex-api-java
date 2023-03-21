package api.pokedex.scripts.mappers;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CountMapper {
    private int count;
    private List<Results> results;
}

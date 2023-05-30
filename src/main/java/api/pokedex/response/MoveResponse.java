package api.pokedex.response;

import api.pokedex.model.moves.Moves;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoveResponse {
    private int size;
    private List<Moves> moves;
}

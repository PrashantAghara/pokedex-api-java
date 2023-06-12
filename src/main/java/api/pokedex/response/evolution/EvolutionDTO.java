package api.pokedex.response.evolution;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EvolutionDTO {
    private String name;
    private String image;
    private List<EvolutionDTO> nextEvolution;
}

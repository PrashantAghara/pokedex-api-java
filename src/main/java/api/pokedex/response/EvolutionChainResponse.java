package api.pokedex.response;

import api.pokedex.response.evolution.EvolutionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvolutionChainResponse {
    private Integer id;
    private String name;
    private EvolutionDTO evolutions;
}

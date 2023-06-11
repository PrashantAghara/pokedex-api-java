package api.pokedex.controller;

import api.pokedex.response.EvolutionChainResponse;
import api.pokedex.service.EvolutionChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokedex/")
@CrossOrigin(origins = "*")
public class EvolutionChainController {
    private final EvolutionChainService evolutionChainService;

    @Autowired
    public EvolutionChainController(EvolutionChainService evolutionChainService) {
        this.evolutionChainService = evolutionChainService;
    }

    @GetMapping("/evolution-chain/{id}")
    public ResponseEntity<EvolutionChainResponse> getPokemonByID(@PathVariable(name = "id") Integer id) {
        return evolutionChainService.getEvolutionChainById(id);
    }
}

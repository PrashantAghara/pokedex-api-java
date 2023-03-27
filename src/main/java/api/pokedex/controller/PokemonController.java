package api.pokedex.controller;

import api.pokedex.response.PokemonResponse;
import api.pokedex.response.pokemon.PokemonResponseCompact;
import api.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/compact")
    public ResponseEntity<PokemonResponse> getPokemon(@RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "offset", required = false) Long offset, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "name", required = false) String name) {
        PokemonResponse pokemonResponse = pokemonService.getPokemonBasedOnTypeAndName(name, type, size, offset);
        return new ResponseEntity<>(pokemonResponse, HttpStatus.OK);
    }
}

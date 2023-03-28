package api.pokedex.controller;

import api.pokedex.response.PokemonResponse;
import api.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokedex/")
public class PokemonController {
    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemons")
    public ResponseEntity<PokemonResponse> getPokemon(@RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "offset", required = false) Long offset, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "name", required = false) String name) {
        PokemonResponse pokemonResponse = pokemonService.getPokemonBasedOnTypeAndName(name, type, size, offset);
        return new ResponseEntity<>(pokemonResponse, HttpStatus.OK);
    }

    @GetMapping("/pokemon/{id}")
    public ResponseEntity<PokemonResponse> getPokemonByID(@PathVariable(name = "id") Integer id) {
        PokemonResponse pokemonResponse = pokemonService.getPokemonByID(id);
        return new ResponseEntity<>(pokemonResponse, HttpStatus.OK);
    }
}

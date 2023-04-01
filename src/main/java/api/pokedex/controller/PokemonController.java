package api.pokedex.controller;

import api.pokedex.response.PokemonResponse;
import api.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokedex/")
@CrossOrigin(origins = "*")
public class PokemonController {
    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemons")
    public ResponseEntity<PokemonResponse> getPokemon(@RequestParam(value = "size", required = false, defaultValue = "20") Integer size, @RequestParam(value = "offset", required = false, defaultValue = "0") Long offset, @RequestParam(value = "type", required = false) String type,
                                                      @RequestParam(value = "name", required = false) String name, @RequestParam(value = "attack", required = false) String attack, @RequestParam(value = "defense", required = false) String defense,
                                                      @RequestParam(value = "speed", required = false) String speed, @RequestParam(required = false, name = "sort", defaultValue = "ASC") String sort
    ) {
        PokemonResponse pokemonResponse = pokemonService.getPokemonBasedOnTypeAndName(name, type, size, offset, attack, speed, defense, sort);
        return new ResponseEntity<>(pokemonResponse, HttpStatus.OK);
    }

    @GetMapping("/pokemon/{id}")
    public ResponseEntity<PokemonResponse> getPokemonByID(@PathVariable(name = "id") Integer id) {
        PokemonResponse pokemonResponse = pokemonService.getPokemonByID(id);
        return new ResponseEntity<>(pokemonResponse, HttpStatus.OK);
    }
}

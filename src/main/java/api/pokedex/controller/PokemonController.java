package api.pokedex.controller;

import api.pokedex.response.PokemonResponseCompact;
import api.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/pokemon")
public class PokemonController {
    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemon/compact")
    public List<PokemonResponseCompact> getPokemon(@RequestParam("size") Long size, @RequestParam("offset") Long offset, @RequestParam("type") String type, @RequestParam("name") String name) {
        return pokemonService.getPokemonBasedOnTypeAndName(name, type, size, offset);
    }
}

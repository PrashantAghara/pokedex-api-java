package api.pokedex.controller;

import api.pokedex.response.MoveResponse;
import api.pokedex.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokedex/")
@CrossOrigin(origins = "*")
public class MovesController {
    private final MoveService moveService;

    @Autowired
    public MovesController(MoveService moveService) {
        this.moveService = moveService;
    }

    @GetMapping("/moves/{id}")
    public ResponseEntity<MoveResponse> getMovesByPokemonId(@PathVariable(name = "id") Integer id) {
        MoveResponse moveResponse = moveService.getPokemonMovesByID(id);
        return new ResponseEntity<>(moveResponse, HttpStatus.OK);
    }
}

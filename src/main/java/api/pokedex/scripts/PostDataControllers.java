package api.pokedex.scripts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostDataControllers {
    private final AddPokemons saveData;

    @Autowired
    public PostDataControllers(AddPokemons addPokemons) {
        this.saveData = addPokemons;
    }

    @GetMapping(value = "/pokemon")
    public int savePokemons() {
        return saveData.addAllPokemons();
    }

    @GetMapping(value = "/move")
    public int saveMoves() {
        return saveData.addAllMoves();
    }

    @GetMapping(value = "/type")
    public int saveTypes() {
        return saveData.addAllTypes();
    }

    @GetMapping(value = "/ability")
    public int saveAbility() {
        return saveData.addAllAbility();
    }
}

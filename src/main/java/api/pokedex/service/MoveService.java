package api.pokedex.service;

import api.pokedex.model.moves.Moves;
import api.pokedex.repository.custom.PokemonFullCustomRepository;
import api.pokedex.response.MoveResponse;
import api.pokedex.response.PokemonResponse;
import api.pokedex.response.pokemon.PokemonResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoveService {
    private final Logger logger = LoggerFactory.getLogger(MoveService.class);
    private final PokemonFullCustomRepository pokemonFullCustomRepository;

    @Autowired
    public MoveService(PokemonFullCustomRepository pokemonFullCustomRepository) {
        this.pokemonFullCustomRepository = pokemonFullCustomRepository;
    }

    public MoveResponse getPokemonMovesByID(Integer id) {
        logger.info("Fetching Pokemon with ID : " + id);
        List<Moves> moves = pokemonFullCustomRepository.getMovesByName(id);
        return new MoveResponse(moves.size(), moves);
    }
}

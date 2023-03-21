package api.pokedex.repository;

import api.pokedex.model.moves.Moves;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends MongoRepository<Moves, Integer> {
}

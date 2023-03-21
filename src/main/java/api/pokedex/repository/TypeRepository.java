package api.pokedex.repository;

import api.pokedex.model.type.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends MongoRepository<Type, Integer> {
}

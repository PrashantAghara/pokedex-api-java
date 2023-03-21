package api.pokedex.repository;

import api.pokedex.model.ability.Ability;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityRepository extends MongoRepository<Ability, Integer> {
}

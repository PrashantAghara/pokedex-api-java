package api.pokedex.repository;

import api.pokedex.model.pokemon.Pokemon;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends MongoRepository<Pokemon, Integer> {

    @Aggregation("{'limit': ?1, 'skip': ?2}")
    List<Pokemon> getAllPokemon(Long limit, Long skip);

    @Aggregation("{'type' : ?0 },{'limit': ?1, 'skip': ?2}")
    List<Pokemon> getPokemonByType(List<String> type, Long limit, Long skip);

    @Aggregation("{'name' : {$regex : ?0, $options: 'i'}}, {'limit': ?1, 'skip': ?2}")
    List<Pokemon> getPokemonByName(String name, Long limit, Long skip);

    @Aggregation("$and : [{'name' : {$regex : ?0, $options: 'i'}}, {'type' : ?1 }], {'limit': ?2, 'skip': ?3}")
    List<Pokemon> getPokemonByNameAndType(String name, List<String> type, Long limit, Long skip);
}

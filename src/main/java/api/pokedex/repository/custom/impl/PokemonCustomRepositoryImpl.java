package api.pokedex.repository.custom.impl;

import api.pokedex.model.pokemon.Pokemon;
import api.pokedex.repository.custom.PokemonCustomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class PokemonCustomRepositoryImpl implements PokemonCustomRepository {

    private final MongoTemplate mongoTemplate;
    private final Logger logger = LoggerFactory.getLogger(PokemonCustomRepositoryImpl.class);

    @Autowired
    public PokemonCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Pokemon> getPokemonByNameAndType(String name, String types, int limit, Long skip, String generation, String sort) {
        List<Criteria> criteriaList = new ArrayList<>();
        if (name != null) {
            Criteria criteria = Criteria.where("name").regex(Pattern.compile("^" + name, Pattern.CASE_INSENSITIVE));
            criteriaList.add(criteria);
        }
        if (types != null) {
            List<String> type = Arrays.asList(types.split(","));
            Criteria criteria = Criteria.where("type").all(type);
            criteriaList.add(criteria);
        }
        if (generation != null) {
            Criteria criteria = Criteria.where("generation").is(generation);
            criteriaList.add(criteria);
        }
        Criteria queryCriteria;
        if (!criteriaList.isEmpty()) {
            queryCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
        } else {
            queryCriteria = new Criteria();
        }
        Query query = new Query();
        query.addCriteria(queryCriteria);
        query.limit(limit).skip(skip);
        if (sort.equals("DESC")) {
            query.with(Sort.by("_id").descending());
        } else {
            query.with(Sort.by("_id").ascending());
        }
        logger.info("QUERY : " + query);
        return mongoTemplate.find(query, Pokemon.class);
    }
}

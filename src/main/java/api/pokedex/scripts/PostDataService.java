package api.pokedex.scripts;

import api.pokedex.model.pokemon.Stats;
import api.pokedex.model.type.TypeRelation;
import api.pokedex.repository.AbilityRepository;
import api.pokedex.repository.MoveRepository;
import api.pokedex.repository.PokemonRepository;
import api.pokedex.repository.TypeRepository;
import api.pokedex.scripts.mappers.ability.AbilityDetails;
import api.pokedex.scripts.mappers.moves.Moves;
import api.pokedex.scripts.mappers.pokemon.Pokemon;
import api.pokedex.scripts.mappers.pokemon.Type;
import api.pokedex.scripts.mappers.pokemon.ability.Ability;
import api.pokedex.scripts.mappers.pokemon.ability.AbilityDetail;
import api.pokedex.scripts.mappers.pokemon.moves.Move;
import api.pokedex.scripts.mappers.pokemon.stats.Stat;
import api.pokedex.scripts.mappers.types.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostDataService {
    private final PokemonRepository pokemonRepository;
    private final AbilityRepository abilityRepository;
    private final TypeRepository typeRepository;
    private final MoveRepository moveRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public PostDataService(PokemonRepository pokemonRepository, AbilityRepository abilityRepository, TypeRepository typeRepository, MoveRepository moveRepository, MongoTemplate mongoTemplate) {
        this.abilityRepository = abilityRepository;
        this.moveRepository = moveRepository;
        this.pokemonRepository = pokemonRepository;
        this.typeRepository = typeRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void savePokemon(Pokemon pokemon) {
        api.pokedex.model.pokemon.Pokemon savePokemon = new api.pokedex.model.pokemon.Pokemon();
        savePokemon.setId(pokemon.getId());
        savePokemon.setName(pokemon.getName());
        savePokemon.setHeight(pokemon.getHeight());
        savePokemon.setWeight(pokemon.getWeight());
        savePokemon.setBaseExperience(pokemon.getBase_experience());
        savePokemon.setGeneration(pokemon.getGeneration());
        Stats stats = new Stats();
        for (Stat stat : pokemon.getStats()) {
            if (stat.getStat().getName().equals("attack")) {
                stats.setAttack(stat.getBase_stat());
            }
            if (stat.getStat().getName().equals("defense")) {
                stats.setDefense(stat.getBase_stat());
            }
            if (stat.getStat().getName().equals("speed")) {
                stats.setSpeed(stat.getBase_stat());
            }
            if (stat.getStat().getName().equals("hp")) {
                stats.setHp(stat.getBase_stat());
            }
            if (stat.getStat().getName().equals("special-attack")) {
                stats.setSpecialAttack(stat.getBase_stat());
            }
            if (stat.getStat().getName().equals("special-defense")) {
                stats.setSpecialDefense(stat.getBase_stat());
            }
        }
        List<String> types = new ArrayList<>();
        List<String> abilities = new ArrayList<>();
        List<String> moves = new ArrayList<>();
        for (Type type : pokemon.getTypes()) {
            types.add(type.getType().getName());
        }
        for (Ability ability : pokemon.getAbilities()) {
            abilities.add(ability.getAbility().getName());
        }
        for (Move move : pokemon.getMoves()) {
            moves.add(move.getMove().getName());
        }
        savePokemon.setAbilities(abilities);
        savePokemon.setType(types);
        savePokemon.setStats(stats);
        savePokemon.setMoves(moves);
        if (pokemon.getSprites().getOther().getDream_world().getFront_default() != null) {
            savePokemon.setImage(pokemon.getSprites().getOther().getDream_world().getFront_default());
        } else if (pokemon.getSprites().getOther().getHome().getFront_default() != null) {
            savePokemon.setImage(pokemon.getSprites().getOther().getHome().getFront_default());
        } else if (pokemon.getSprites().getOther().getOfficialArtwork().getFront_default() != null) {
            savePokemon.setImage(pokemon.getSprites().getOther().getOfficialArtwork().getFront_default());
        } else {
            return;
        }
        savePokemon.setEvolvesFrom(pokemon.getEvolveFrom());
        if (pokemon.getEvolveFrom() != null) {
            getNextPokemons(pokemon.getEvolveFrom(), pokemon.getName());
        }
        savePokemon.setEvolvesTo(getNextPokemons(pokemon.getName()));
        pokemonRepository.save(savePokemon);
    }

    private void getNextPokemons(String name, String pokemonName) {
        Query query = new Query(Criteria.where("name").is(name));
        List<api.pokedex.model.pokemon.Pokemon> pokemon = mongoTemplate.find(query, api.pokedex.model.pokemon.Pokemon.class);
        if (pokemon.isEmpty()) {
            return;
        }
        List<String> pokemonTo = pokemon.get(0).getEvolvesTo();
        pokemonTo.add(pokemonName);
        pokemon.get(0).setEvolvesTo(pokemonTo);
        pokemonRepository.save(pokemon.get(0));
    }

    private List<String> getNextPokemons(String name) {
        Query query = new Query(Criteria.where("evolvesFrom").is(name));
        List<api.pokedex.model.pokemon.Pokemon> pokemons = mongoTemplate.find(query, api.pokedex.model.pokemon.Pokemon.class);
        if (pokemons.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> response = new ArrayList<>();
        for (api.pokedex.model.pokemon.Pokemon pokemon : pokemons) {
            response.add(pokemon.getName());
        }
        return response;
    }

    public void saveAbility(AbilityDetails ability) {
        api.pokedex.model.ability.Ability saveAbility = new api.pokedex.model.ability.Ability();
        saveAbility.setId(ability.getId());
        saveAbility.setName(ability.getName());
        if (ability.getEffect_entries().stream().anyMatch(effect -> effect.getLanguage().getName().equals("en"))) {
            saveAbility.setDescription(ability.getEffect_entries().stream().filter(effect -> effect.getLanguage().getName().equals("en")).findAny().get().getEffect());
        }
        abilityRepository.save(saveAbility);
    }

    public void saveMove(Moves move) {
        api.pokedex.model.moves.Moves moves = new api.pokedex.model.moves.Moves();
        moves.setId(move.getId());
        moves.setName(move.getName());
        moves.setPp(move.getPp());
        moves.setAccuracy(move.getAccuracy());
        moves.setPower(move.getPower());
        moves.setType(move.getType().getName());
        moves.setMoveType(move.getDamage_class().getName());
        if (move.getEffect_entries().stream().anyMatch(effect -> effect.getLanguage().getName().equals("en"))) {
            moves.setDescription(move.getEffect_entries().stream().filter(effect -> effect.getLanguage().getName().equals("en")).findAny().get().getEffect());
        }
        moveRepository.save(moves);
    }

    public void saveType(Types type) {
        api.pokedex.model.type.Type saveType = new api.pokedex.model.type.Type();
        saveType.setId(type.getId());
        saveType.setName(type.getName());
        TypeRelation typeRelation = new TypeRelation();
        if (!type.getDamage_relations().getDouble_damage_from().isEmpty()) {
            typeRelation.setDoubleDamageFrom(type.getDamage_relations().getDouble_damage_from().stream().map(AbilityDetail::getName).collect(Collectors.toList()));
        }
        if (!type.getDamage_relations().getNo_damage_to().isEmpty()) {
            typeRelation.setNoDamageTo(type.getDamage_relations().getNo_damage_to().stream().map(AbilityDetail::getName).collect(Collectors.toList()));
        }
        if (!type.getDamage_relations().getDouble_damage_to().isEmpty()) {
            typeRelation.setDoubleDamageTo(type.getDamage_relations().getDouble_damage_to().stream().map(AbilityDetail::getName).collect(Collectors.toList()));
        }
        if (!type.getDamage_relations().getHalf_damage_to().isEmpty()) {
            typeRelation.setHalfDamageTo(type.getDamage_relations().getHalf_damage_to().stream().map(AbilityDetail::getName).collect(Collectors.toList()));
        }
        if (!type.getDamage_relations().getHalf_damage_from().isEmpty()) {
            typeRelation.setHalfDamageFrom(type.getDamage_relations().getHalf_damage_from().stream().map(AbilityDetail::getName).collect(Collectors.toList()));
        }
        if (!type.getDamage_relations().getNo_damage_from().isEmpty()) {
            typeRelation.setNoDamageFrom(type.getDamage_relations().getNo_damage_from().stream().map(AbilityDetail::getName).collect(Collectors.toList()));
        }
        saveType.setRelation(typeRelation);
        typeRepository.save(saveType);
    }
}

package api.pokedex.scripts;

import api.pokedex.scripts.mappers.CountMapper;
import api.pokedex.scripts.mappers.Results;
import api.pokedex.scripts.mappers.ability.AbilityDetails;
import api.pokedex.scripts.mappers.moves.Moves;
import api.pokedex.scripts.mappers.pokemon.EvolveFrom;
import api.pokedex.scripts.mappers.pokemon.Pokemon;
import api.pokedex.scripts.mappers.pokemon.ability.Ability;
import api.pokedex.scripts.mappers.pokemon.ability.AbilityDetail;
import api.pokedex.scripts.mappers.types.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Objects;

@Service
public class AddPokemons {
    private final PostDataService postDataService;

    @Autowired
    public AddPokemons(PostDataService postDataService) {
        this.postDataService = postDataService;
    }

    public int addAllPokemons() {
        HttpClient httpClient = HttpClient.create()
                .resolver(spec -> spec.queryTimeout(Duration.ofSeconds(100000)));
        WebClient getPokemon = WebClient.builder().codecs(codecs -> codecs
                        .defaultCodecs()
                        .maxInMemorySize(30000 * 1024))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        CountMapper countMapper = Objects.requireNonNull(getPokemon.get().uri("https://pokeapi.co/api/v2/pokemon?limit=5000").retrieve().bodyToMono(CountMapper.class).block());
        for (Results result : countMapper.getResults()) {
            Pokemon pokemon = getPokemon.get().uri(result.getUrl()).retrieve().bodyToMono(Pokemon.class).block();

            assert pokemon != null;
            EvolveFrom evolveFrom = getPokemon.get().uri(pokemon.getSpecies().getUrl()).retrieve().bodyToMono(EvolveFrom.class).block();
            assert evolveFrom != null;
            if (evolveFrom.getEvolves_from_species() == null) {
                pokemon.setEvolveFrom(null);
            } else {
                pokemon.setEvolveFrom(evolveFrom.getEvolves_from_species().getName());
            }

            if (evolveFrom.getGeneration() == null) {
                pokemon.setGeneration(null);
            } else {
                pokemon.setGeneration(evolveFrom.getGeneration().getName());
            }

            //DB
            System.out.println("DB Saved : " + pokemon.getId());
            postDataService.savePokemon(pokemon);
        }
        return countMapper.getCount();
    }

    public int addAllMoves() {
        HttpClient httpClient = HttpClient.create()
                .resolver(spec -> spec.queryTimeout(Duration.ofSeconds(100000)));
        WebClient getMoves = WebClient.builder().codecs(codecs -> codecs
                        .defaultCodecs()
                        .maxInMemorySize(30000 * 1024))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        CountMapper countMapper = Objects.requireNonNull(getMoves.get().uri("https://pokeapi.co/api/v2/move?limit=5000").retrieve().bodyToMono(CountMapper.class).block());
        int count = 1;
        for (Results result : countMapper.getResults()) {
            Moves moves = getMoves.get().uri(result.getUrl()).retrieve().bodyToMono(Moves.class).block();
            //DB TODO
            System.out.println("DB Saved : " + count++);
            assert moves != null;
            postDataService.saveMove(moves);
        }
        return countMapper.getCount();
    }

    public int addAllAbility() {
        HttpClient httpClient = HttpClient.create()
                .resolver(spec -> spec.queryTimeout(Duration.ofSeconds(100000)));
        WebClient getMoves = WebClient.builder().codecs(codecs -> codecs
                        .defaultCodecs()
                        .maxInMemorySize(30000 * 1024))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        CountMapper countMapper = Objects.requireNonNull(getMoves.get().uri("https://pokeapi.co/api/v2/ability?limit=5000").retrieve().bodyToMono(CountMapper.class).block());
        int count = 1;
        for (Results result : countMapper.getResults()) {
            AbilityDetails ability = getMoves.get().uri(result.getUrl()).retrieve().bodyToMono(AbilityDetails.class).block();
            //DB TODO
            System.out.println("DB Saved : " + count++);
            postDataService.saveAbility(ability);
        }
        return countMapper.getCount();
    }

    public int addAllTypes() {
        HttpClient httpClient = HttpClient.create()
                .resolver(spec -> spec.queryTimeout(Duration.ofSeconds(100000)));
        WebClient getMoves = WebClient.builder().codecs(codecs -> codecs
                        .defaultCodecs()
                        .maxInMemorySize(30000 * 1024))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        CountMapper countMapper = Objects.requireNonNull(getMoves.get().uri("https://pokeapi.co/api/v2/type?limit=30").retrieve().bodyToMono(CountMapper.class).block());
        int count = 1;
        for (Results result : countMapper.getResults()) {
            Types types = getMoves.get().uri(result.getUrl()).retrieve().bodyToMono(Types.class).block();
            //DB TODO
            System.out.println("DB Saved : " + count++);
            assert types != null;
            postDataService.saveType(types);
        }
        return countMapper.getCount();
    }
}

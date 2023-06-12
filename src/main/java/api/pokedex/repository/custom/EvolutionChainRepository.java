package api.pokedex.repository.custom;

import api.pokedex.response.EvolutionChainResponse;

public interface EvolutionChainRepository {
    EvolutionChainResponse getEvolutionChain(Integer id);
}

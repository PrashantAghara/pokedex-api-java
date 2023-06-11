package api.pokedex.service;

import api.pokedex.repository.custom.EvolutionChainRepository;
import api.pokedex.response.EvolutionChainResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EvolutionChainService {
    private final EvolutionChainRepository evolutionChainRepository;
    private final Logger logger = LoggerFactory.getLogger(EvolutionChainService.class);

    @Autowired
    public EvolutionChainService(EvolutionChainRepository evolutionChainRepository) {
        this.evolutionChainRepository = evolutionChainRepository;
    }

    public ResponseEntity<EvolutionChainResponse> getEvolutionChainById(Integer id) {
        logger.info("Fetching Evolution info for Pokemon with ID : " + id);
        EvolutionChainResponse evolutionChainResponse = evolutionChainRepository.getEvolutionChain(id);
        return new ResponseEntity<>(evolutionChainResponse, HttpStatus.OK);
    }
}

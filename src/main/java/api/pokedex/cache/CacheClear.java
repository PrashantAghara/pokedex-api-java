package api.pokedex.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CacheClear {
    @CacheEvict(value = "pokemons", allEntries = true)
    @Scheduled(fixedRate = 10L, timeUnit = TimeUnit.MINUTES)
    public void clearCache() {
        log.info("Cleared Cache");
    }
}

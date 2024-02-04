package api.pokedex.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CacheController {
    @Autowired
    CacheManager cacheManager;

    @GetMapping("/cache/{name}")
    public Cache getCacheByName(@PathVariable("name") String name) {
        return cacheManager.getCache(name);
    }
}

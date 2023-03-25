package api.pokedex.scripts.mappers.pokemon.images;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Other {
    private DreamWorld dream_world;
    private DreamWorld home;
    @JsonProperty("official-artwork")
    private DreamWorld officialArtwork;
}

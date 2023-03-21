package api.pokedex.model.pokemon;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collation = "pokemon")
public class Pokemon {
    @Id
    private Integer id;
    private String name;
    private Integer baseExperience;
    private Integer height;
    private Integer weight;
    private List<String> type;
    private List<String> abilities;
    private List<String> moves;
    private Stats stats;
    private String image;
}

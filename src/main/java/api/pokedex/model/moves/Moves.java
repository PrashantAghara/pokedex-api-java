package api.pokedex.model.moves;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "moves")
public class Moves {
    @Id
    private Integer id;
    private String name;
    private Integer pp;
    private Integer power;
    private Integer accuracy;
    private String moveType;
    private String type;
    private String description;
}

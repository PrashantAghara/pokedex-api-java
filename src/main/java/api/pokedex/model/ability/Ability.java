package api.pokedex.model.ability;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "ability")
public class Ability {
    @Id
    private Integer id;
    private String name;
    private String description;
}

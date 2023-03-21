package api.pokedex.model.type;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "type")
public class Type {
    @Id
    private Integer id;
    private String name;
    private TypeRelation relation;
}

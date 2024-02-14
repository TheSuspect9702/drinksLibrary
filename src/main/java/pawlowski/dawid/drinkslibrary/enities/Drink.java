package pawlowski.dawid.drinkslibrary.enities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import pawlowski.dawid.drinkslibrary.model.DrinkPower;

import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drink {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar", updatable = false,nullable = false)
    private UUID id;
    private String name;
    private Double rating;
    private DrinkPower power;
    private String description;
    //add ingredients as another table connected by Id of Drink
}

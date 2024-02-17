package pawlowski.dawid.drinkslibrary.enities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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

    @NotNull
    private String name;

    @NotNull
    @PositiveOrZero
    private Double rating;

    @NotNull
    private DrinkPower power;

    @NotNull
    @Size(max = 10000)
    @Column(length = 10000)
    private String description;
    //add ingredients as another table connected by Id of Drink
}

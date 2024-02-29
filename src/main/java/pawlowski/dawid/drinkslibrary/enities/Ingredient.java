package pawlowski.dawid.drinkslibrary.enities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import pawlowski.dawid.drinkslibrary.model.IngredientType;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    private IngredientType alcoholType;

    @NotNull
    @Positive
    private double quantity;

    @ManyToOne
    @JoinColumn(name="drink_id")
    private Drink drink;
}

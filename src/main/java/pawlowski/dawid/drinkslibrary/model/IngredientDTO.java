package pawlowski.dawid.drinkslibrary.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pawlowski.dawid.drinkslibrary.enities.Drink;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {
    private UUID id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String alcoholType;

    @NotNull
    @Positive
    private Double quantity;

    private Drink drink;
}

package pawlowski.dawid.drinkslibrary.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pawlowski.dawid.drinkslibrary.enities.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrinkDTO {
    private UUID id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private Double rating;

    @NotNull
    private DrinkPower power;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max = 10000)
    private String description;

    private List<Ingredient> ingredients;
}

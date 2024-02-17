package pawlowski.dawid.drinkslibrary.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class DrinkDTO {
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
    private String description;
}

package pawlowski.dawid.drinkslibrary.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class DrinkDTO {
    private UUID id;
    private String name;
    private Double rating;
    private DrinkPower power;
    private String description;
}

package pawlowski.dawid.drinkslibrary.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class Drink {
    private UUID id;
    private String name;
    private Double rating;
    private Map<String,Integer> ingredients;
    private DrinkPower power;
    private String description;
}

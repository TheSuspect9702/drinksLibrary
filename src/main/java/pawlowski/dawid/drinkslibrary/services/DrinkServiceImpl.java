package pawlowski.dawid.drinkslibrary.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pawlowski.dawid.drinkslibrary.model.DrinkDTO;
import pawlowski.dawid.drinkslibrary.model.DrinkPower;

import java.util.*;

@Slf4j
@Service
public class DrinkServiceImpl implements DrinkService {
    private Map<UUID, DrinkDTO> drinkMap;
    public DrinkServiceImpl(){
        this.drinkMap = new HashMap<>();

        DrinkDTO drink0 = DrinkDTO.builder()
                .id(UUID.randomUUID())
                .name("Sex on the Beach")
                .power(DrinkPower.MEDIUM)
                .ingredients(new HashMap<>() {{put("Vodka", 45); put("Peach Schnapps", 15); put("Orange Juice", 60); put("Cranberry Juice", 60);}})
                .description("Fill a glass with ice and add vodka, peach schnapps, and orange juice. Pour cranberry juice over. Garnish with an orange wedge.")
                .rating(8.4)
                .build();

        DrinkDTO drink1 = DrinkDTO.builder()
                .id(UUID.randomUUID())
                .name("Piña Colada")
                .power(DrinkPower.STRONG)
                .ingredients(new HashMap<>() {{put("Rum", 75); put("Pineapple Juice", 90); put("Coconut Cream", 30);}})
                .description("Combine rum with unsweetened pineapple juice (you can sub in 3 ounces crushed or whole pineapple) and coconut cream in a blender." +
                        " Blend on high with a cup or so of crushed ice, or 5 to 6 ice cubes. Pour into a tall glass. Garnish with a pineapple and cherry.")
                .rating(6.2)
                .build();

        DrinkDTO drink2 = DrinkDTO.builder()
                .id(UUID.randomUUID())
                .name("Martini")
                .power(DrinkPower.EXTRA_STRONG)
                .ingredients(new HashMap<>() {{put("Gin or Vodka", 90); put("dry Vermouth", 15); put("Lemon peel or Olive", 1);}})
                .description("Stir ingredients in a mixing glass with ice. Strain into chilled martini glass. Squeeze oil from lemon peel into the glass or garnish with olive.")
                .rating(7.8)
                .build();

        DrinkDTO drink3 = DrinkDTO.builder()
                .id(UUID.randomUUID())
                .name("Mojito")
                .power(DrinkPower.SOFT)
                .ingredients(new HashMap<>(){{put("White Rum", 60); put("Lime Juice", 20); put("Simple Syrup", 2); put("Mint Leaves", 3);}})
                .description("Muddle mint into a shaker tin, then add ice and rest of ingredients. Shake to chill and strain into a highball glass with ice. " +
                        "Top with club soda, if desired, and garnish with mint.")
                .rating(9.5)
                .build();

        drinkMap.put(drink0.getId(), drink0);
        drinkMap.put(drink1.getId(), drink1);
        drinkMap.put(drink2.getId(), drink2);
        drinkMap.put(drink3.getId(), drink3);


    }
    @Override
    public List<DrinkDTO> listDrinks() {
        return new ArrayList<>(drinkMap.values());
    }

    @Override
    public DrinkDTO getDrinkById(UUID id) {
        log.debug("Get Drink by Id - in service. Id: " + id.toString());
        return drinkMap.get(id);
    }

    @Override
    public DrinkDTO saveNewDrink(DrinkDTO drink) {
        DrinkDTO newDrink = DrinkDTO.builder()
                .id(UUID.randomUUID())
                .name(drink.getName())
                .power(drink.getPower())
                .ingredients(drink.getIngredients())
                .description(drink.getDescription())
                .rating(drink.getRating())
                .build();
        drinkMap.put(newDrink.getId(),newDrink);
        return newDrink;
    }

    @Override
    public void updateDrinkById(UUID drinkId, DrinkDTO drink) {
        DrinkDTO drinkToUpdate = drinkMap.get(drinkId);
        drinkToUpdate.setName(drink.getName());
        drinkToUpdate.setIngredients(drink.getIngredients());
        drinkToUpdate.setDescription(drink.getDescription());
        drinkToUpdate.setPower(drink.getPower());
        drinkToUpdate.setRating(drink.getRating());

        drinkMap.put(drinkId, drinkToUpdate);
    }

    @Override
    public void deleteDrinkById(UUID drinkId) {
        drinkMap.remove(drinkId);
    }

    @Override
    public void patchDrinkId(UUID drinkId, DrinkDTO drink) {
        DrinkDTO drinkToPatch = drinkMap.get(drinkId);

        if(StringUtils.hasText(drink.getName()))
            drinkToPatch.setName(drink.getName());
        if(drink.getRating() != null)
            drinkToPatch.setRating(drink.getRating());
        if(drink.getIngredients() != null)
            drinkToPatch.setIngredients(drink.getIngredients());
        if(drink.getPower() != null)
            drinkToPatch.setPower(drink.getPower());
        if(StringUtils.hasText(drink.getDescription()))
            drinkToPatch.setDescription(drink.getDescription());


    }
}

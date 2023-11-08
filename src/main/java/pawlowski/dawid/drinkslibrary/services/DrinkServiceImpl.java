package pawlowski.dawid.drinkslibrary.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pawlowski.dawid.drinkslibrary.model.Drink;
import pawlowski.dawid.drinkslibrary.model.DrinkPower;

import java.util.*;

@Slf4j
@Service
public class DrinkServiceImpl implements DrinkService {
    private Map<UUID,Drink> drinkMap;

    public DrinkServiceImpl(){
        this.drinkMap = new HashMap<>();

        Drink drink0 = Drink.builder()
                .id(UUID.randomUUID())
                .name("Sex on the Beach")
                .power(DrinkPower.MEDIUM)
                .ingredients(new HashMap<>() {{put("Vodka", 45); put("Peach Schnapps", 15); put("Orange Juice", 60); put("Cranberry Juice", 60);}})
                .description("Fill a glass with ice and add vodka, peach schnapps, and orange juice. Pour cranberry juice over. Garnish with an orange wedge.")
                .rating(8.4)
                .build();

        Drink drink1 = Drink.builder()
                .id(UUID.randomUUID())
                .name("Piña Colada")
                .power(DrinkPower.STRONG)
                .ingredients(new HashMap<>() {{put("Rum", 75); put("Pineapple Juice", 90); put("Coconut Cream", 30);}})
                .description("Combine rum with unsweetened pineapple juice (you can sub in 3 ounces crushed or whole pineapple) and coconut cream in a blender." +
                        " Blend on high with a cup or so of crushed ice, or 5 to 6 ice cubes. Pour into a tall glass. Garnish with a pineapple and cherry.")
                .rating(6.2)
                .build();

        Drink drink2 = Drink.builder()
                .id(UUID.randomUUID())
                .name("Martini")
                .power(DrinkPower.EXTRA_STRONG)
                .ingredients(new HashMap<>() {{put("Gin or Vodka", 90); put("dry Vermouth", 15); put("Lemon peel or Olive", 1);}})
                .description("Stir ingredients in a mixing glass with ice. Strain into chilled martini glass. Squeeze oil from lemon peel into the glass or garnish with olive.")
                .rating(7.8)
                .build();

        drinkMap.put(drink0.getId(), drink0);
        drinkMap.put(drink1.getId(), drink1);
        drinkMap.put(drink2.getId(), drink2);
    }
    @Override
    public List<Drink> listDrinks() {
        return new ArrayList<>(drinkMap.values());
    }

    @Override
    public Drink getDrinkById(UUID id) {
        log.debug("Get Drink by Id - in service. Id: " + id.toString());
        return drinkMap.get(id);
    }

    @Override
    public Drink saveNewDrink(Drink drink) {
        Drink newDrink = Drink.builder()
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
}

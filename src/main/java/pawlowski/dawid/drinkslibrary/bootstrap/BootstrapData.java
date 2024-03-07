package pawlowski.dawid.drinkslibrary.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pawlowski.dawid.drinkslibrary.enities.Drink;
import pawlowski.dawid.drinkslibrary.enities.Ingredient;
import pawlowski.dawid.drinkslibrary.model.DrinkPower;
import pawlowski.dawid.drinkslibrary.model.IngredientDTO;
import pawlowski.dawid.drinkslibrary.repositories.DrinkRepository;
import pawlowski.dawid.drinkslibrary.repositories.IngredientRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final DrinkRepository drinkRepository;
    private final IngredientRepository ingredientRepository;
    @Override
    public void run(String... args) throws Exception {
        if(drinkRepository.count() == 0) // later change it to something more equivalent
            loadData();
    }

    private void loadData() {
        Drink drink0 = Drink.builder()
                .name("Sex on the Beach")
                .power(DrinkPower.MEDIUM)
                .description("Fill a glass with ice and add vodka, peach schnapps, and orange juice. Pour cranberry juice over. Garnish with an orange wedge.")
                .rating(8.4)
                .build();

        Drink drink1 = Drink.builder()
                .name("Piña Colada")
                .power(DrinkPower.STRONG)
                .description("Combine rum with unsweetened pineapple juice (you can sub in 3 ounces crushed or whole pineapple) and coconut cream in a blender." +
                        " Blend on high with a cup or so of crushed ice, or 5 to 6 ice cubes. Pour into a tall glass. Garnish with a pineapple and cherry.")
                .rating(6.2)
                .build();

        Drink drink2 = Drink.builder()
                .name("Martini")
                .power(DrinkPower.EXTRA_STRONG)
                .description("Stir ingredients in a mixing glass with ice. Strain into chilled martini glass. Squeeze oil from lemon peel into the glass or garnish with olive.")
                .rating(7.8)
                .build();

        Drink drink3 = Drink.builder()
                .name("Mojito")
                .power(DrinkPower.SOFT)
                .description("Muddle mint into a shaker tin, then add ice and rest of ingredients. Shake to chill and strain into a highball glass with ice. " +
                        "Top with club soda, if desired, and garnish with mint.")
                .rating(9.5)
                .build();
        drinkRepository.saveAll(Arrays.asList(drink0,drink1,drink2,drink3));

        Ingredient ingredient0 = Ingredient.builder()
                .alcoholType("Vodka [ml]")
                .quantity(50.0)
                .drink(drink0)
                .build();
        Ingredient ingredient1 = Ingredient.builder()
                .alcoholType("Peach Schnapps [ml]")
                .quantity(25.0)
                .drink(drink0)
                .build();
        Ingredient ingredient2 = Ingredient.builder()
                .alcoholType("Orange Slices")
                .quantity(2.0)
                .drink(drink0)
                .build();
        Ingredient ingredient3 = Ingredient.builder()
                .alcoholType("Cranberry Juice [ml]")
                .quantity(50.0)
                .drink(drink0)
                .build();
        Ingredient ingredient4 = Ingredient.builder()
                .alcoholType("Pineapple Juice [ml]")
                .quantity(120.0)
                .drink(drink1)
                .build();
        Ingredient ingredient5 = Ingredient.builder()
                .alcoholType("White Rum [ml]")
                .quantity(60.0)
                .drink(drink1)
                .build();
        Ingredient ingredient6 = Ingredient.builder()
                .alcoholType("Coconut Cream [ml]")
                .quantity(60.0)
                .drink(drink1)
                .build();
        Ingredient ingredient7 = Ingredient.builder()
                .alcoholType("Vodka [ml]")
                .quantity(60.0)
                .drink(drink2)
                .build();
        Ingredient ingredient8 = Ingredient.builder()
                .alcoholType("Dry Vermouth table spoon")
                .quantity(1.0)
                .drink(drink2)
                .build();
        Ingredient ingredient9 = Ingredient.builder()
                .alcoholType("Olive Peal")
                .quantity(1.0)
                .drink(drink2)
                .build();
        Ingredient ingredient10 = Ingredient.builder()
                .alcoholType("Lime Juice [ml]")
                .quantity(30.0)
                .drink(drink3)
                .build();
        Ingredient ingredient11 = Ingredient.builder()
                .alcoholType("Sugar table spoon")
                .quantity(1.0)
                .drink(drink3)
                .build();
        Ingredient ingredient12 = Ingredient.builder()
                .alcoholType("Mint Leaves")
                .quantity(5.0)
                .drink(drink3)
                .build();
        Ingredient ingredient13 = Ingredient.builder()
                .alcoholType("Soda Water [ml]")
                .quantity(30.0)
                .drink(drink3)
                .build();
        Ingredient ingredient14 = Ingredient.builder()
                .alcoholType("White Rum [ml]")
                .quantity(60.0)
                .drink(drink3)
                .build();

        List<Ingredient> ingredients = Arrays.asList(ingredient0, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7,
                ingredient8, ingredient9, ingredient10, ingredient11, ingredient12, ingredient13, ingredient14);
        ingredientRepository.saveAll(ingredients);

    }
}

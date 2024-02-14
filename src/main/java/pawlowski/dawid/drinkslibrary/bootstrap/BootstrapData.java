package pawlowski.dawid.drinkslibrary.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pawlowski.dawid.drinkslibrary.enities.Drink;
import pawlowski.dawid.drinkslibrary.model.DrinkPower;
import pawlowski.dawid.drinkslibrary.repositories.DrinkRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final DrinkRepository drinkRepository;
    @Override
    public void run(String... args) throws Exception {
        loadDrinkData();
    }

    private void loadDrinkData() {
        Drink drink0 = Drink.builder()
                .name("Sex on the Beach")
                .power(DrinkPower.MEDIUM)
                .description("Fill a glass with ice and add vodka, peach schnapps, and orange juice. Pour cranberry juice over. Garnish with an orange wedge.")
                .rating(8.4)
                .build();

        Drink drink1 = Drink.builder()
                .name("Piña Colada")
                .power(DrinkPower.STRONG)
                .description("Combine rum with unsweetened pineapple juice (you can sub in 3 ounces crushed or whole pineapple) and coconut cream in a blender.")
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
                .description("Muddle mint into a shaker tin, then add ice and rest of ingredients. Shake to chill and strain into a highball glass with ice. ")
                .rating(9.5)
                .build();
        drinkRepository.saveAll(Arrays.asList(drink0,drink1,drink2,drink3));
    }
}

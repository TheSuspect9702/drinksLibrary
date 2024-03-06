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
                .description("Fill a glass with ice and add vodka, peach schnapps, and orange juice. Pour cranberry juice over. Garnish with an orange wedge.")
                .rating(8.4)
                .build();

        DrinkDTO drink1 = DrinkDTO.builder()
                .id(UUID.randomUUID())
                .name("Piña Colada")
                .power(DrinkPower.STRONG)
                .description("Combine rum with unsweetened pineapple juice (you can sub in 3 ounces crushed or whole pineapple) and coconut cream in a blender." +
                        " Blend on high with a cup or so of crushed ice, or 5 to 6 ice cubes. Pour into a tall glass. Garnish with a pineapple and cherry.")
                .rating(6.2)
                .build();

        DrinkDTO drink2 = DrinkDTO.builder()
                .id(UUID.randomUUID())
                .name("Martini")
                .power(DrinkPower.EXTRA_STRONG)
                .description("Stir ingredients in a mixing glass with ice. Strain into chilled martini glass. Squeeze oil from lemon peel into the glass or garnish with olive.")
                .rating(7.8)
                .build();

        DrinkDTO drink3 = DrinkDTO.builder()
                .id(UUID.randomUUID())
                .name("Mojito")
                .power(DrinkPower.SOFT)
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
    public Optional<DrinkDTO> getDrinkById(UUID id) {
        log.debug("Get Drink by Id - in service. Id: " + id.toString());
        return Optional.ofNullable(drinkMap.get(id));
    }

    @Override
    public DrinkDTO saveNewDrink(DrinkDTO drink) {
        DrinkDTO newDrink = DrinkDTO.builder()
                .id(UUID.randomUUID())
                .name(drink.getName())
                .power(drink.getPower())
                .description(drink.getDescription())
                .rating(drink.getRating())
                .build();
        drinkMap.put(newDrink.getId(),newDrink);
        return newDrink;
    }

    @Override
    public Optional<DrinkDTO> updateDrinkById(UUID drinkId, DrinkDTO drink) {
        DrinkDTO drinkToUpdate = drinkMap.get(drinkId);
        drinkToUpdate.setName(drink.getName());
        drinkToUpdate.setDescription(drink.getDescription());
        drinkToUpdate.setPower(drink.getPower());
        drinkToUpdate.setRating(drink.getRating());

        drinkMap.put(drinkId, drinkToUpdate);
        return Optional.of(drinkToUpdate);
    }

    @Override
    public Boolean deleteDrinkById(UUID drinkId) {
        drinkMap.remove(drinkId);

        return true;
    }

    @Override
    public Optional<DrinkDTO> patchDrinkId(UUID drinkId, DrinkDTO drink) {
        DrinkDTO drinkToPatch = drinkMap.get(drinkId);

        if(drink.getName() != null)
            drinkToPatch.setName(drink.getName());
        if(drink.getRating() != null)
            drinkToPatch.setRating(drink.getRating());
        if(drink.getPower() != null)
            drinkToPatch.setPower(drink.getPower());
        if(drink.getDescription() != null)
            drinkToPatch.setDescription(drink.getDescription());

        return Optional.of(drinkToPatch);
    }

    @Override
    public Boolean deleteDrinks() {
        drinkMap.clear();

        return true;
    }
}

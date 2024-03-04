package pawlowski.dawid.drinkslibrary.services;

import pawlowski.dawid.drinkslibrary.model.DrinkDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DrinkService {

    List<DrinkDTO> listDrinks();
    Optional<DrinkDTO> getDrinkById(UUID id);
    DrinkDTO saveNewDrink(DrinkDTO drink);

    Optional<DrinkDTO> updateDrinkById(UUID drinkId, DrinkDTO drink);

    Boolean deleteDrinkById(UUID drinkId);

    Optional<DrinkDTO> patchDrinkId(UUID drinkId, DrinkDTO drink);

    Boolean deleteDrinks();
}

package pawlowski.dawid.drinkslibrary.services;

import pawlowski.dawid.drinkslibrary.model.DrinkDTO;

import java.util.List;
import java.util.UUID;

public interface DrinkService {

    List<DrinkDTO> listDrinks();
    DrinkDTO getDrinkById(UUID id);
    DrinkDTO saveNewDrink(DrinkDTO drink);

    void updateDrinkById(UUID drinkId, DrinkDTO drink);

    void deleteDrinkById(UUID drinkId);

    void patchDrinkId(UUID drinkId, DrinkDTO drink);
}

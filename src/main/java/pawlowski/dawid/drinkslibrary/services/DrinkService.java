package pawlowski.dawid.drinkslibrary.services;

import pawlowski.dawid.drinkslibrary.model.Drink;

import java.util.List;
import java.util.UUID;

public interface DrinkService {

    List<Drink> listDrinks();
    Drink getDrinkById(UUID id);
    Drink saveNewDrink(Drink drink);

    void updateDrinkById(UUID drinkId, Drink drink);

    void deleteDrinkById(UUID drinkId);

    void patchDrinkId(UUID drinkId, Drink drink);
}

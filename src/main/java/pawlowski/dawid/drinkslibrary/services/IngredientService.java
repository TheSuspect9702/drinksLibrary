package pawlowski.dawid.drinkslibrary.services;

import pawlowski.dawid.drinkslibrary.enities.Drink;
import pawlowski.dawid.drinkslibrary.model.IngredientDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientService {

    List <IngredientDTO> listIngredients();

    Optional<IngredientDTO> getIngredientById(UUID id);

    //List<Optional<IngredientDTO>> getIngredientsById(UUID id);

    IngredientDTO saveNewIngredient(IngredientDTO ingredientDTO, Drink drink);

    //List<IngredientDTO> saveNewIngredients(List<IngredientDTO> ingredientDTOS);

    Optional<IngredientDTO> updateIngredientById(UUID id, IngredientDTO ingredientDTO);

    Optional<IngredientDTO> patchIngredientById(UUID id, IngredientDTO ingredientDTO);

    Boolean deleteIngredientById(UUID id);

    Boolean deleteIngredients();
}

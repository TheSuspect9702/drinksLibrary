package pawlowski.dawid.drinkslibrary.mappers;

import pawlowski.dawid.drinkslibrary.enities.Ingredient;
import pawlowski.dawid.drinkslibrary.model.IngredientDTO;

public interface IngredientMapper {

    IngredientDTO ingredientToIngredientDto(Ingredient ingredient);

    Ingredient ingredientDtoToIngredient(IngredientDTO ingredientDTO);
}

package pawlowski.dawid.drinkslibrary.mappers;

import org.mapstruct.Mapper;
import pawlowski.dawid.drinkslibrary.enities.Ingredient;
import pawlowski.dawid.drinkslibrary.model.IngredientDTO;

@Mapper
public interface IngredientMapper {

    IngredientDTO ingredientToIngredientDto(Ingredient ingredient);

    Ingredient ingredientDtoToIngredient(IngredientDTO ingredientDTO);
}

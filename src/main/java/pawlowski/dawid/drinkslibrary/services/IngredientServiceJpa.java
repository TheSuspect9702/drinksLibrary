package pawlowski.dawid.drinkslibrary.services;

import pawlowski.dawid.drinkslibrary.model.IngredientDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class IngredientServiceJpa implements IngredientService {
    @Override
    public List<IngredientDTO> listIngredients() {
        return null;
    }

    @Override
    public Optional<IngredientDTO> getIngredientById(UUID id) {
        return Optional.empty();
    }

    @Override
    public IngredientDTO saveNewIngredient(IngredientDTO ingredientDTO) {
        return null;
    }

    @Override
    public List<IngredientDTO> saveNewIngredients(List<IngredientDTO> ingredientDTOS) {
        return null;
    }

    @Override
    public Optional<IngredientDTO> updateIngredientById(UUID id, IngredientDTO ingredientDTO) {
        return Optional.empty();
    }

    @Override
    public Boolean deleteIngredientById(UUID id) {
        return null;
    }
}

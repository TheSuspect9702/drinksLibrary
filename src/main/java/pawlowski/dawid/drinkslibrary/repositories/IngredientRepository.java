package pawlowski.dawid.drinkslibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import pawlowski.dawid.drinkslibrary.enities.Ingredient;

import java.util.UUID;

public interface IngredientRepository extends CrudRepository<Ingredient, UUID> {
}

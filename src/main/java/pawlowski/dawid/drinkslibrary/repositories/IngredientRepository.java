package pawlowski.dawid.drinkslibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pawlowski.dawid.drinkslibrary.enities.Ingredient;

import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
}

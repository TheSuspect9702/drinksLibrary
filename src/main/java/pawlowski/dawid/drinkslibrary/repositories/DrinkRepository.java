package pawlowski.dawid.drinkslibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pawlowski.dawid.drinkslibrary.enities.Drink;

import java.util.UUID;

public interface DrinkRepository extends JpaRepository<Drink, UUID> {
}

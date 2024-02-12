package pawlowski.dawid.drinkslibrary.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pawlowski.dawid.drinkslibrary.enities.Drink;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DrinkRepositoryTest {
    DrinkRepository drinkRepository;

    @Test
    void testSaveDrink(){
        Drink savedDrink = drinkRepository.save(Drink.builder()
                .name("My Drink")
                .build());
        assertThat(savedDrink).isNotNull();
        assertThat(savedDrink.getId()).isNotNull();
    }
}
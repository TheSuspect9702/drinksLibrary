package pawlowski.dawid.drinkslibrary.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pawlowski.dawid.drinkslibrary.enities.Drink;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DrinkRepositoryTest {

    //automaticly add drinkReposetory to the test
    @Autowired
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
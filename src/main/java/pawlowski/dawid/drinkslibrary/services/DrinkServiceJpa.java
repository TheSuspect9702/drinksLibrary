package pawlowski.dawid.drinkslibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pawlowski.dawid.drinkslibrary.mappers.DrinkMapper;
import pawlowski.dawid.drinkslibrary.model.DrinkDTO;
import pawlowski.dawid.drinkslibrary.repositories.DrinkRepository;

import java.util.List;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public class DrinkServiceJpa implements DrinkService{
    private final DrinkRepository drinkRepository;
    private final DrinkMapper drinkMapper;

    @Override
    public List<DrinkDTO> listDrinks() {
        return null;
    }

    @Override
    public DrinkDTO getDrinkById(UUID id) {
        return null;
    }

    @Override
    public DrinkDTO saveNewDrink(DrinkDTO drink) {
        return null;
    }

    @Override
    public void updateDrinkById(UUID drinkId, DrinkDTO drink) {

    }

    @Override
    public void deleteDrinkById(UUID drinkId) {

    }

    @Override
    public void patchDrinkId(UUID drinkId, DrinkDTO drink) {

    }
}

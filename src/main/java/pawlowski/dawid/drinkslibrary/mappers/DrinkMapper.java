package pawlowski.dawid.drinkslibrary.mappers;

import org.mapstruct.Mapper;
import pawlowski.dawid.drinkslibrary.enities.Drink;
import pawlowski.dawid.drinkslibrary.model.DrinkDTO;

@Mapper
public interface DrinkMapper {

    Drink drinkDtoToDrink(DrinkDTO dto);

    DrinkDTO drinkToDrinkDto(Drink drink);
}

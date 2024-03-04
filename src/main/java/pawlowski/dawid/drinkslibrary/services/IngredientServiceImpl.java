package pawlowski.dawid.drinkslibrary.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawlowski.dawid.drinkslibrary.enities.Drink;
import pawlowski.dawid.drinkslibrary.mappers.DrinkMapper;
import pawlowski.dawid.drinkslibrary.model.DrinkDTO;
import pawlowski.dawid.drinkslibrary.model.IngredientDTO;
import pawlowski.dawid.drinkslibrary.repositories.DrinkRepository;

import java.util.*;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private Map<UUID, IngredientDTO> ingredientMap;

    public IngredientServiceImpl(){
        this.ingredientMap = new HashMap<>();

        IngredientDTO ingredient0 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("Vodka [ml]")
                .quantity(50.0)
                .build();
        IngredientDTO ingredient1 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("Peach Schnapps [ml]")
                .quantity(25)
                .build();
        IngredientDTO ingredient2 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("Orange Slices")
                .quantity(2)
                .build();
        IngredientDTO ingredient3 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("Cranberry Juice xDDDD [ml]")
                .quantity(50)
                .build();
        IngredientDTO ingredient4 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("Pineapple Juice [ml]")
                .quantity(120)
                .build();
        IngredientDTO ingredient5 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("White Rum [ml]")
                .quantity(60)
                .build();
        IngredientDTO ingredient6 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("Coconut Cream [ml]")
                .quantity(60)
                .build();
        IngredientDTO ingredient7 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("Vodka [ml]")
                .quantity(60)
                .build();
        IngredientDTO ingredient8 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("Dry Vermouth table spoon")
                .quantity(1)
                .build();
        IngredientDTO ingredient9 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("Olive Peal")
                .quantity(1)
                .build();
        IngredientDTO ingredient10 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("Lime Juice [ml]")
                .quantity(30)
                .build();
        IngredientDTO ingredient11 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("Sugar table spoon")
                .quantity(1)
                .build();
        IngredientDTO ingredient12 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("Mint Leaves")
                .quantity(5)
                .build();
        IngredientDTO ingredient13 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("Soda Water [ml]")
                .quantity(30)
                .build();
        IngredientDTO ingredient14 = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType("White Rum [ml]")
                .quantity(60)
                .build();

        List<IngredientDTO> ingredients = List.of(ingredient0, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7,
                ingredient8, ingredient9, ingredient10, ingredient11, ingredient12, ingredient13, ingredient14);
        for(IngredientDTO ingredient : ingredients)
            ingredientMap.put(ingredient.getId(), ingredient);
    }

    @Override
    public List<IngredientDTO> listIngredients() {
        return new ArrayList<>(ingredientMap.values());
    }

    @Override
    public Optional<IngredientDTO> getIngredientById(UUID id) {
        return Optional.ofNullable(ingredientMap.get(id));
    }

    @Override
    public IngredientDTO saveNewIngredient(IngredientDTO ingredientDTO, Drink drink) {
        IngredientDTO newIngredient = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType(ingredientDTO.getAlcoholType())
                .quantity(ingredientDTO.getQuantity())
                .drink(drink)
                .build();
        ingredientMap.put(newIngredient.getId(),newIngredient);
        return newIngredient;
    }

   /* @Override
    public List<IngredientDTO> saveNewIngredients(List<IngredientDTO> ingredientDTOS) {
        List<IngredientDTO> ingredients = new ArrayList<>();
        for(IngredientDTO nextIngredient : ingredientDTOS){
            IngredientDTO newIngredient = IngredientDTO.builder()
                    .id(UUID.randomUUID())
                    .alcoholType(nextIngredient.getAlcoholType())
                    .quantity(nextIngredient.getQuantity())
                    .drink(nextIngredient.getDrink())
                    .build();
            ingredients.add(newIngredient);
            ingredientMap.put(newIngredient.getId(),newIngredient);
        }
        return ingredients;
    }*/

    @Override
    public Optional<IngredientDTO> updateIngredientById(UUID id, IngredientDTO ingredientDTO) {
        IngredientDTO updatedIngredient = IngredientDTO.builder()
                .id(UUID.randomUUID())
                .alcoholType(ingredientDTO.getAlcoholType())
                .quantity(ingredientDTO.getQuantity())
                .drink(ingredientDTO.getDrink())
                .build();
        ingredientMap.put(updatedIngredient.getId(), updatedIngredient);
        return Optional.of(updatedIngredient);
    }

    @Override
    public Boolean deleteIngredientById(UUID id) {

        ingredientMap.remove(id);

        return true;
    }

    @Override
    public Boolean deleteIngredients() {
        ingredientMap.clear();
        return true;
    }

}

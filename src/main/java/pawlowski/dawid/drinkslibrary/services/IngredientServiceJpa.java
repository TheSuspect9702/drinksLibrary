package pawlowski.dawid.drinkslibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pawlowski.dawid.drinkslibrary.enities.Drink;
import pawlowski.dawid.drinkslibrary.mappers.IngredientMapper;
import pawlowski.dawid.drinkslibrary.model.IngredientDTO;
import pawlowski.dawid.drinkslibrary.repositories.IngredientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class IngredientServiceJpa implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;
    @Override
    public List<IngredientDTO> listIngredients() {
        return ingredientRepository.findAll()
                .stream()
                .map(ingredientMapper::ingredientToIngredientDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<IngredientDTO> getIngredientById(UUID id) {
        return Optional.ofNullable(ingredientMapper
                .ingredientToIngredientDto(ingredientRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public IngredientDTO saveNewIngredient(IngredientDTO ingredientDTO) {
        return ingredientMapper.ingredientToIngredientDto(ingredientRepository.save(ingredientMapper.ingredientDtoToIngredient(ingredientDTO)));
    }

    @Override
    public List<IngredientDTO> saveNewIngredients(List<IngredientDTO> ingredientDTOS) {
        List<IngredientDTO> ingredients = new ArrayList<>();
        for(IngredientDTO nextIngredient : ingredientDTOS)
            ingredients.add(ingredientMapper.ingredientToIngredientDto(ingredientRepository
                    .save(ingredientMapper.ingredientDtoToIngredient(nextIngredient))));
        return ingredients;
    }

    @Override
    public Optional<IngredientDTO> updateIngredientById(UUID id, IngredientDTO ingredientDTO) {
        AtomicReference<Optional<IngredientDTO>> atomicReference = new AtomicReference<>();
        ingredientRepository.findById(id).ifPresentOrElse(foundIngredient -> {
            foundIngredient.setAlcoholType(ingredientDTO.getAlcoholType());
            foundIngredient.setQuantity(ingredientDTO.getQuantity());
            foundIngredient.setDrink(ingredientDTO.getDrink());
            atomicReference.set(Optional.of(ingredientMapper.ingredientToIngredientDto(ingredientRepository.save(foundIngredient))));
        }, () -> {
                atomicReference.set(Optional.empty());
    });
        return atomicReference.get();
    }

    @Override
    public Optional<IngredientDTO> patchIngredientById(UUID id, IngredientDTO ingredientDTO) {
        AtomicReference<Optional<IngredientDTO>> atomicReference = new AtomicReference<>();
        ingredientRepository.findById(id).ifPresentOrElse(ingredientToPatch -> {
            if(ingredientDTO.getAlcoholType() != null)
                ingredientToPatch.setAlcoholType(ingredientDTO.getAlcoholType());
            if(ingredientDTO.getQuantity() != null)
                ingredientToPatch.setQuantity(ingredientDTO.getQuantity());
            if(ingredientDTO.getDrink() != null)
                ingredientToPatch.setDrink(ingredientDTO.getDrink());
            atomicReference.set(Optional.of(ingredientMapper.ingredientToIngredientDto(ingredientRepository.save(ingredientToPatch))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });
        return atomicReference.get();
    }

    @Override
    public Boolean deleteIngredientById(UUID id) {
        if(ingredientRepository.existsById(id)){
            ingredientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteIngredients() {
        ingredientRepository.deleteAll();
        return true;
    }
}

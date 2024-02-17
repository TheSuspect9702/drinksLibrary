package pawlowski.dawid.drinkslibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pawlowski.dawid.drinkslibrary.mappers.DrinkMapper;
import pawlowski.dawid.drinkslibrary.model.DrinkDTO;
import pawlowski.dawid.drinkslibrary.repositories.DrinkRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class DrinkServiceJpa implements DrinkService{
    private final DrinkRepository drinkRepository;
    private final DrinkMapper drinkMapper;

    @Override
    public List<DrinkDTO> listDrinks() {
        return drinkRepository.findAll()
                .stream()
                .map(drinkMapper::drinkToDrinkDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DrinkDTO> getDrinkById(UUID id) {
        return Optional.ofNullable(drinkMapper.drinkToDrinkDto(drinkRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public DrinkDTO saveNewDrink(DrinkDTO drink) {
        return drinkMapper.drinkToDrinkDto(drinkRepository.save(drinkMapper.drinkDtoToDrink(drink)));
    }

    @Override
    public Optional<DrinkDTO> updateDrinkById(UUID drinkId, DrinkDTO drink) {
        AtomicReference<Optional<DrinkDTO>> atomicReference = new AtomicReference<>();

        drinkRepository.findById(drinkId).ifPresentOrElse(foundDrink ->{
            foundDrink.setName(drink.getName());
            foundDrink.setRating(drink.getRating());
            foundDrink.setPower(drink.getPower());
            atomicReference.set(Optional.of(drinkMapper.drinkToDrinkDto(drinkRepository.save(foundDrink))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });
        return atomicReference.get();
    }

    @Override
    public Boolean deleteDrinkById(UUID drinkId) {
        if(drinkRepository.existsById(drinkId)){
            drinkRepository.deleteById(drinkId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<DrinkDTO> patchDrinkId(UUID drinkId, DrinkDTO drink) {
        AtomicReference<Optional<DrinkDTO>> atomicReference = new AtomicReference<>();
        drinkRepository.findById(drinkId).ifPresentOrElse( drinkToPatch -> {
            if(StringUtils.hasText(drink.getName()))
                drinkToPatch.setName(drink.getName());
            if(drink.getRating() != null)
                drinkToPatch.setRating(drink.getRating());
            if(drink.getPower() != null)
                drinkToPatch.setPower(drink.getPower());
            if(StringUtils.hasText(drink.getDescription()))
                drinkToPatch.setDescription(drink.getDescription());
            atomicReference.set(Optional.of(drinkMapper.drinkToDrinkDto(drinkRepository.save(drinkToPatch))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });
        return atomicReference.get();
    }
}

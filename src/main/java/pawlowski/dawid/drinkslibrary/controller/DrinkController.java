package pawlowski.dawid.drinkslibrary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pawlowski.dawid.drinkslibrary.model.DrinkDTO;
import pawlowski.dawid.drinkslibrary.services.DrinkService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DrinkController {
public static final String DRINK_PATH = "/api/v1/drink";
public static final String DRINK_PATH_ID = DRINK_PATH + "/{drinkId}";



    private final DrinkService drinkService;

    @PatchMapping(DRINK_PATH_ID)
    public ResponseEntity updateDrinkPatchById(@PathVariable("drinkId") UUID drinkId, @RequestBody DrinkDTO drink) {

        drinkService.patchDrinkId(drinkId, drink);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(DRINK_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("drinkId") UUID drinkId){

        if( !drinkService.deleteDrinkById(drinkId))
            throw new NotFoundException();

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(DRINK_PATH_ID)
    public ResponseEntity handlePut(@PathVariable("drinkId") UUID drinkId,@Validated @RequestBody DrinkDTO drink) {

        if(drinkService.updateDrinkById(drinkId, drink).isEmpty())
            throw new NotFoundException();

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
    @PostMapping(DRINK_PATH)
    public ResponseEntity handlePost(@Validated @RequestBody DrinkDTO drink) {
        DrinkDTO newDrink = drinkService.saveNewDrink(drink);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", DRINK_PATH + "/" + newDrink.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping (DRINK_PATH)
    public List<DrinkDTO> listDrinks() {return drinkService.listDrinks();}

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(){
        return ResponseEntity.notFound().build();
    }
    @GetMapping(value = DRINK_PATH_ID)
    public DrinkDTO getDrinkById(@PathVariable("drinkId") UUID drinkId){
        return drinkService.getDrinkById(drinkId).orElseThrow(NotFoundException::new);
    }
}

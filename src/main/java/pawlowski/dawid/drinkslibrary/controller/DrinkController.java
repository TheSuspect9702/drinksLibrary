package pawlowski.dawid.drinkslibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pawlowski.dawid.drinkslibrary.model.Drink;
import pawlowski.dawid.drinkslibrary.services.DrinkService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DrinkController {
public static final String DRINK_PATH = "/api/v1/drink";
public static final String DRINK_PATH_ID = DRINK_PATH + "/{drinkId}";



    private final DrinkService drinkService;

    @PatchMapping(DRINK_PATH_ID)
    public ResponseEntity updateDrinkPatchById(@PathVariable("drinkId") UUID drinkId, @RequestBody Drink drink) {

        drinkService.patchDrinkId(drinkId, drink);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(DRINK_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("drinkId") UUID drinkId){

        drinkService.deleteDrinkById(drinkId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(DRINK_PATH_ID)
    public ResponseEntity handlePut(@PathVariable("drinkId") UUID drinkId, @RequestBody Drink drink) {

        drinkService.updateDrinkById(drinkId, drink);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
    @PostMapping(DRINK_PATH)
    public ResponseEntity handlePost(@RequestBody Drink drink) {
        Drink newDrink = drinkService.saveNewDrink(drink);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", DRINK_PATH + "/" + newDrink.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping (DRINK_PATH)
    public List<Drink> listDrinks() {return drinkService.listDrinks();}
    @GetMapping(value = DRINK_PATH_ID)
    public Drink getDrinkById(@PathVariable("drinkId") UUID drinkId) {
        log.debug("Get Drink by Id - in controller");

        return drinkService.getDrinkById(drinkId);
    }
}

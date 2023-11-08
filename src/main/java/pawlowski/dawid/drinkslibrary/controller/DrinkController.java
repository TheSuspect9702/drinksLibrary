package pawlowski.dawid.drinkslibrary.controller;

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
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/drink")
public class DrinkController {

    private final DrinkService drinkService;

    @PostMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity handlePost(@RequestBody Drink drink) {
        Drink newDrink = drinkService.saveNewDrink(drink);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/drink/" + newDrink.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Drink> listDrinks() {return drinkService.listDrinks();}
    @RequestMapping(value = "{drinkId}", method = RequestMethod.GET)
    public Drink getDrinkById(@PathVariable("drinkId") UUID drinkId) {
        log.debug("Get Drink by Id - in controller");

        return drinkService.getDrinkById(drinkId);
    }
}

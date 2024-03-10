package pawlowski.dawid.drinkslibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pawlowski.dawid.drinkslibrary.model.DrinkDTO;
import pawlowski.dawid.drinkslibrary.model.IngredientDTO;
import pawlowski.dawid.drinkslibrary.services.DrinkService;
import pawlowski.dawid.drinkslibrary.services.IngredientService;

import java.util.List;

@Controller
@RequestMapping("/drinks")
public class WebPageDrinkController {
    private final DrinkService drinkService;
    public WebPageDrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public String listDrinks(Model model) {
        List<DrinkDTO> drinks = drinkService.listDrinks();
        model.addAttribute("drinks", drinks);
        return "drinks";
    }
}


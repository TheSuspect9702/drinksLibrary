package pawlowski.dawid.drinkslibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pawlowski.dawid.drinkslibrary.model.Drink;
import pawlowski.dawid.drinkslibrary.services.DrinkService;

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
        List<Drink> drinks = drinkService.listDrinks();
        model.addAttribute("drinks", drinks);
        return "drinks";
    }
}


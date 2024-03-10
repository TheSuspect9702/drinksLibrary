package pawlowski.dawid.drinkslibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pawlowski.dawid.drinkslibrary.model.DrinkDTO;
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
        List<DrinkDTO> drinks = drinkService.listDrinks();
        model.addAttribute("drinks", drinks);
        return "drinks";
    }
    @GetMapping("/add")
    public String addDrinkForm(Model model) {
        model.addAttribute("drinkDTO", new DrinkDTO());
        return "addDrink";
    }
    @PostMapping
    public String saveDrink(DrinkDTO drinkDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addDrink";
        }
        drinkService.saveNewDrink(drinkDTO); // Assuming you have a method in your service to save the drink
        return "redirect:/drinks"; // Redirect back to the drinks list
    }
}


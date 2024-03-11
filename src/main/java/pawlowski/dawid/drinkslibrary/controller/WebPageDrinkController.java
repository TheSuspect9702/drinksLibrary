package pawlowski.dawid.drinkslibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pawlowski.dawid.drinkslibrary.mappers.DrinkMapper;
import pawlowski.dawid.drinkslibrary.model.DrinkDTO;
import pawlowski.dawid.drinkslibrary.model.IngredientDTO;
import pawlowski.dawid.drinkslibrary.services.DrinkService;
import pawlowski.dawid.drinkslibrary.services.IngredientService;


import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/drinks")
public class WebPageDrinkController {
    private final DrinkService drinkService;
    private final IngredientService ingredientService;
    private final DrinkMapper drinkMapper;
    public WebPageDrinkController(DrinkMapper drinkMapper, DrinkService drinkService, IngredientService ingredientService) {
        this.ingredientService = ingredientService;
        this.drinkService = drinkService;
        this.drinkMapper = drinkMapper;
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
    @PostMapping("/add")
    public String saveDrink(DrinkDTO drinkDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addDrink";
        }
        drinkService.saveNewDrink(drinkDTO);
        return "redirect:/drinks"; // Redirect back to the drinks list
    }
    @GetMapping("/addIngredient/{drinkId}")
    public String addIngredientForm(@PathVariable UUID drinkId, Model model) {
        DrinkDTO drink = drinkService.getDrinkById(drinkId).get();
        model.addAttribute("ingredientDTO", new IngredientDTO());
        model.addAttribute("drinkDTO", drink);
        return "addIngredient";
    }

    @PostMapping("/addIngredient/{drinkId}")
    public String saveIngredient(@PathVariable UUID drinkId, IngredientDTO ingredientDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("drinkDTO", drinkService.getDrinkById(drinkId));
            model.addAttribute("org.springframework.validation.BindingResult.ingredientDTO", result);
            return "addIngredient";
        }
        ingredientDTO.setDrink(drinkMapper.drinkDtoToDrink(drinkService.getDrinkById(drinkId).get()));
        ingredientService.saveNewIngredient(ingredientDTO);
        return "redirect:/drinks/addIngredient/{drinkId}";
    }
}


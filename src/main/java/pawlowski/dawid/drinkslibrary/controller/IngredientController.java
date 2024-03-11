package pawlowski.dawid.drinkslibrary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pawlowski.dawid.drinkslibrary.model.IngredientDTO;
import pawlowski.dawid.drinkslibrary.services.IngredientService;

import java.util.List;
import java.util.UUID;

/**
 * TODO modify html files, that after passing wrong parameters there should be communicate not error
 *
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class IngredientController {
    public static final String INGREDIENT_PATH = "/api/v1/ingredient";
    public static final String INGREDIENT_PATH_ID = INGREDIENT_PATH + "/{ingredientId}";

    private final IngredientService ingredientService;

    @GetMapping(INGREDIENT_PATH)
    public List<IngredientDTO> listIngredients(){
        return ingredientService.listIngredients();
    }

    @GetMapping(value = INGREDIENT_PATH_ID)
    public IngredientDTO getDrinkById(@PathVariable("ingredientId") UUID ingredientId){
        return ingredientService.getIngredientById(ingredientId).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/api/v1/multipleIngredients")
    public ResponseEntity handleMultiplePost(@Validated @RequestBody List<IngredientDTO> ingredientDTOS){
        List<IngredientDTO> newIngredients = ingredientService.saveNewIngredients(ingredientDTOS);

        HttpHeaders headers = new HttpHeaders();
        for(IngredientDTO newIngredient : newIngredients)
            headers.add("Location","/api/v1/multipleIngredients" + "/" + newIngredient.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PostMapping(INGREDIENT_PATH)
    public ResponseEntity handlePost(@Validated @RequestBody IngredientDTO ingredientDTO) {
        IngredientDTO newIngredient = ingredientService.saveNewIngredient(ingredientDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", INGREDIENT_PATH + "/" + newIngredient.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(INGREDIENT_PATH_ID)
    public ResponseEntity handlePut(@PathVariable("ingredientId") UUID ingredientId, @Validated @RequestBody IngredientDTO ingredientDTO){
        if(ingredientService.updateIngredientById(ingredientId,ingredientDTO).isEmpty())
            throw new NotFoundException();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(INGREDIENT_PATH_ID)
    public ResponseEntity handlePatch(@PathVariable("ingredientId") UUID ingredientId, @RequestBody IngredientDTO ingredientDTO){
        ingredientService.patchIngredientById(ingredientId, ingredientDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(INGREDIENT_PATH_ID)
    public ResponseEntity handleDeleteById(@PathVariable("ingredientId") UUID ingredientId){
        if( !ingredientService.deleteIngredientById(ingredientId))
            throw new NotFoundException();

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(INGREDIENT_PATH)
    public ResponseEntity deleteIngredients(){
        if( !ingredientService.deleteIngredients())
            throw new NotFoundException();

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

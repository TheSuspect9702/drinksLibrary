package pawlowski.dawid.drinkslibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import pawlowski.dawid.drinkslibrary.enities.Ingredient;
import pawlowski.dawid.drinkslibrary.mappers.IngredientMapper;
import pawlowski.dawid.drinkslibrary.model.IngredientDTO;
import pawlowski.dawid.drinkslibrary.repositories.DrinkRepository;
import pawlowski.dawid.drinkslibrary.repositories.IngredientRepository;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class IngredientControllerIntegrationTest {

    @Autowired
    IngredientController ingredientController;
    
    @Autowired
    IngredientMapper ingredientMapper;
    
    @Autowired
    IngredientRepository ingredientRepository;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    DrinkRepository drinkRepository;

    MockMvc mockMvc;
    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testDeleteNotFound() {
        assertThrows(NotFoundException.class, () ->{
            ingredientController.handleDeleteById(UUID.randomUUID());
        });
    }
    
    @Rollback
    @Transactional
    @Test
    void testDeleteById(){
        Ingredient ingredient = ingredientRepository.findAll().get(0);

        ResponseEntity responseEntity = ingredientController.handleDeleteById(ingredient.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        
        assertThat(ingredientRepository.findById(ingredient.getId()).isEmpty());
    }

    @Rollback
    @Transactional
    @Test
    void testSaveNewIngredient(){
        IngredientDTO ingredientDTO = IngredientDTO.builder()
                .alcoholType("jakis alkohol")
                .quantity(10.0)
                .drink(drinkRepository.findAll().get(0))
                .build();
        ResponseEntity responseEntity = ingredientController.handlePost(ingredientDTO);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();
        
        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedId = UUID.fromString(locationUUID[4]);
        
        Ingredient ingredient = ingredientRepository.getReferenceById(savedId);
        assertThat(ingredient).isNotNull();
    }

    @Test
    void testGetById(){
        Ingredient ingredient = ingredientRepository.findAll().get(0);

        IngredientDTO ingredientDTO = ingredientController.getDrinkById(ingredient.getId());

        assertThat(ingredientDTO).isNotNull();
    }

}

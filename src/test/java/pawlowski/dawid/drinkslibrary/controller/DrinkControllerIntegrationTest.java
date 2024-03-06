package pawlowski.dawid.drinkslibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import pawlowski.dawid.drinkslibrary.enities.Drink;
import pawlowski.dawid.drinkslibrary.mappers.DrinkMapper;
import pawlowski.dawid.drinkslibrary.model.DrinkDTO;
import pawlowski.dawid.drinkslibrary.repositories.DrinkRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pawlowski.dawid.drinkslibrary.controller.DrinkController.DRINK_PATH_ID;

@SpringBootTest
class DrinkControllerIntegrationTest {
    @Autowired
    DrinkController drinkController;

    @Autowired
    DrinkRepository drinkRepository;
    
    @Autowired
    DrinkMapper drinkMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void testPatchBadRatingDrink() throws Exception {
        Drink drink = drinkRepository.findAll().get(0);

        Map<String,Object> drinkMap = new HashMap<>();
        drinkMap.put("rating", -9.99);
        MvcResult response = mockMvc.perform(patch(DRINK_PATH_ID, drink.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(drinkMap)))
                        .andExpect(status().isBadRequest())
                        .andReturn();

        System.out.println(response.getResponse().getContentAsString());
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> {
            drinkController.deleteById(UUID.randomUUID());
        });
    }

    @Rollback
    @Transactional
    @Test
    void deleteById() {
        Drink drink = drinkRepository.findAll().get(0);

        ResponseEntity responseEntity = drinkController.deleteById(drink.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        assertThat(drinkRepository.findById(drink.getId()).isEmpty());
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class, () -> {
            drinkController.handlePut(UUID.randomUUID(), DrinkDTO.builder().build());
        });
    }

    @Rollback
    @Transactional
    @Test
    void updateDrink() {
        Drink drink = drinkRepository.findAll().get(0);
        DrinkDTO drinkDTO = drinkMapper.drinkToDrinkDto(drink);
        drinkDTO.setId(null);
        final String drinkName = "Updated Drink";
        drinkDTO.setName(drinkName);

        ResponseEntity responseEntity = drinkController.handlePut(drink.getId(), drinkDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Drink updatedDrink = drinkRepository.findById(drink.getId()).get();
        assertThat(updatedDrink.getName()).isEqualTo(drinkName);
    }

    @Rollback
    @Transactional
    @Test
    void saveNewDrinkTest() {
        DrinkDTO drinkDTO = DrinkDTO.builder()
                .name("nowy drink")
                .description("przykladowy opis")
                .build();
        ResponseEntity responseEntity = drinkController.handlePost(drinkDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Drink drink = drinkRepository.findById(savedUUID).get();
        assertThat(drink).isNotNull();
    }

    @Test
    void testDrinkIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            drinkController.getDrinkById(UUID.randomUUID());
        });
    }

    @Test
    void testGetDrinkById() {
        Drink drink = drinkRepository.findAll().get(0);

        DrinkDTO drinkDTO = drinkController.getDrinkById(drink.getId());

        assertThat(drinkDTO).isNotNull();
    }

    @Test
    void testDrinkList(){
        List<DrinkDTO> listOfDrinks = drinkController.listDrinks();

        assertThat(listOfDrinks.size()).isEqualTo(4);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyDrinkList(){
        drinkRepository.deleteAll();
        List<DrinkDTO> listOfDrinks = drinkController.listDrinks();

        assertThat(listOfDrinks.size()).isEqualTo(0);
    }
}
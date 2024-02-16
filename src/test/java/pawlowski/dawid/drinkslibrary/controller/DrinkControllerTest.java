package pawlowski.dawid.drinkslibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pawlowski.dawid.drinkslibrary.model.DrinkDTO;
import pawlowski.dawid.drinkslibrary.services.DrinkService;
import pawlowski.dawid.drinkslibrary.services.DrinkServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import static org.hamcrest.core.Is.is;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pawlowski.dawid.drinkslibrary.controller.DrinkController.DRINK_PATH;
import static pawlowski.dawid.drinkslibrary.controller.DrinkController.DRINK_PATH_ID;

@WebMvcTest(DrinkController.class)
public class DrinkControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    DrinkService drinkService;

    DrinkServiceImpl drinkServiceImpl = new DrinkServiceImpl();

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<DrinkDTO> drinkArgumentCaptor;

    @BeforeEach
    void setUp(){
        drinkServiceImpl = new DrinkServiceImpl();
    }

    @Test
    void testPatchDrink() throws Exception {
        DrinkDTO drink = drinkServiceImpl.listDrinks().get(0);

        Map<String,Object> drinkMap = new HashMap<>();
        drinkMap.put("name", "New name");

        mockMvc.perform(patch(DRINK_PATH_ID, drink.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(drinkMap)))
                .andExpect(status().isNoContent());

        verify(drinkService).patchDrinkId(uuidArgumentCaptor.capture(), drinkArgumentCaptor.capture());

        assertThat(drink.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(drinkMap.get("name")).isEqualTo(drinkArgumentCaptor.getValue().getName());

    }

    @Test
    void testDeleteDrink() throws Exception {
        DrinkDTO drink = drinkServiceImpl.listDrinks().get(0);

        given(drinkService.deleteDrinkById(any())).willReturn(true);

        mockMvc.perform(delete(DRINK_PATH_ID, drink.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(drinkService).deleteDrinkById(uuidArgumentCaptor.capture());

        assertThat(drink.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testUpdateDrink() throws Exception {
        DrinkDTO drink = drinkServiceImpl.listDrinks().get(0);

        given(drinkService.updateDrinkById(any(),any())).willReturn(Optional.of(drink));

        mockMvc.perform(put(DRINK_PATH_ID,drink.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(drink)))
                .andExpect(status().isNoContent());

        verify(drinkService).updateDrinkById(any(UUID.class), any(DrinkDTO.class));

    }

    @Test
    void testCreateNewDrink() throws Exception {
        DrinkDTO drink = drinkServiceImpl.listDrinks().get(0);
        drink.setId(null);

        given(drinkService.saveNewDrink(any(DrinkDTO.class))).willReturn(drinkServiceImpl.listDrinks().get(1));

        mockMvc.perform(post(DRINK_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(drink)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
        
    }

    @Test
    void testListDrinks() throws Exception {
        given(drinkService.listDrinks()).willReturn(drinkServiceImpl.listDrinks());

        mockMvc.perform(get(DRINK_PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()",is(4)));
    }

    @Test
    void getDrinkByIdNotFound() throws Exception {
        given(drinkService.getDrinkById(any(UUID.class))).willThrow(NotFoundException.class);

        mockMvc.perform(get(DRINK_PATH_ID, UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }
    @Test
    void getDrinkById() throws Exception {
        DrinkDTO drink = drinkServiceImpl.listDrinks().get(0);

        given(drinkService.getDrinkById(drink.getId())).willReturn(Optional.of(drink));

        mockMvc.perform(get(DRINK_PATH_ID, drink.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",is(drink.getId().toString())))
                .andExpect(jsonPath("$.name", is(drink.getName())));
    }
}

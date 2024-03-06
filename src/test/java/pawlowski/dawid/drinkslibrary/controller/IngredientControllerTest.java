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
import pawlowski.dawid.drinkslibrary.model.IngredientDTO;
import pawlowski.dawid.drinkslibrary.services.IngredientService;
import pawlowski.dawid.drinkslibrary.services.IngredientServiceImpl;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pawlowski.dawid.drinkslibrary.controller.IngredientController.INGREDIENT_PATH_ID;

@WebMvcTest(IngredientController.class)
public class IngredientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    IngredientService ingredientService;

    IngredientServiceImpl ingredientServiceImpl = new IngredientServiceImpl();

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<IngredientDTO> ingredientArgumentCaptor;

    @BeforeEach
    void setUp(){
        ingredientServiceImpl = new IngredientServiceImpl();
    }

    @Test
    void testDeleteIngredient() throws Exception {
        IngredientDTO ingredientDTO = ingredientServiceImpl.listIngredients().get(0);

        given(ingredientService.deleteIngredientById(any())).willReturn(true);

        mockMvc.perform(delete(INGREDIENT_PATH_ID, ingredientDTO.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        verify(ingredientService).deleteIngredientById(uuidArgumentCaptor.capture());

        assertThat(ingredientDTO.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }
}
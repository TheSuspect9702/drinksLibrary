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
import org.springframework.test.web.servlet.MvcResult;
import pawlowski.dawid.drinkslibrary.model.IngredientDTO;
import pawlowski.dawid.drinkslibrary.services.IngredientService;
import pawlowski.dawid.drinkslibrary.services.IngredientServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pawlowski.dawid.drinkslibrary.controller.IngredientController.INGREDIENT_PATH;
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

    @Test
    void testUpdateIngredient() throws Exception {
        IngredientDTO ingredientDTO = ingredientServiceImpl.listIngredients().get(0);

        given(ingredientService.updateIngredientById(any(),any())).willReturn(Optional.of(ingredientDTO));

        mockMvc.perform(put(INGREDIENT_PATH_ID, ingredientDTO.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ingredientDTO)))
                .andExpect(status().isNoContent());

        verify(ingredientService).updateIngredientById(any(UUID.class), any(IngredientDTO.class));
    }

    @Test
    void testCreateNewIngredient() throws Exception {
        IngredientDTO ingredientDTO = ingredientServiceImpl.listIngredients().get(0);
        ingredientDTO.setId(null);

        given(ingredientService.saveNewIngredient(any(IngredientDTO.class))).willReturn(ingredientServiceImpl.listIngredients().get(1));

        mockMvc.perform(post(INGREDIENT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ingredientDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void testPatchIngredient() throws Exception {
        IngredientDTO ingredientDTO = ingredientServiceImpl.listIngredients().get(0);

        Map<String, Object> ingredientMap = new HashMap<>();
        ingredientMap.put("alcoholType", "Nowy alkohol");

        mockMvc.perform(patch(INGREDIENT_PATH_ID, ingredientDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ingredientMap)))
                .andExpect(status().isNoContent());

        verify(ingredientService).patchIngredientById(uuidArgumentCaptor.capture(), ingredientArgumentCaptor.capture());

        assertThat(ingredientDTO.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(ingredientMap.get("alcoholType")).isEqualTo(ingredientArgumentCaptor.getValue().getAlcoholType());
    }

    @Test
    void testGetIngredientById() throws Exception {
        IngredientDTO ingredientDTO = ingredientServiceImpl.listIngredients().get(0);

        given(ingredientService.getIngredientById(any())).willReturn(Optional.of(ingredientDTO));

        mockMvc.perform(get(INGREDIENT_PATH_ID, ingredientDTO.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",is(ingredientDTO.getId().toString())))
                .andExpect(jsonPath("$.alcoholType",is(ingredientDTO.getAlcoholType())));
    }

    @Test
    void testBadIngredientCreate() throws Exception {
        IngredientDTO ingredientDTO = IngredientDTO.builder().build();
        given(ingredientService.saveNewIngredient(any(IngredientDTO.class))).willReturn(ingredientServiceImpl.listIngredients().get(1));

        MvcResult mvcResult = mockMvc.perform(post(INGREDIENT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ingredientDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()", is(4)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testBadIngredientPut() throws Exception {
        IngredientDTO ingredientDTO = ingredientServiceImpl.listIngredients().get(0);
        ingredientDTO.setQuantity(-1.0);
        ingredientDTO.setAlcoholType("   ");

        given(ingredientService.updateIngredientById(any(),any())).willReturn(Optional.of(ingredientDTO));

        MvcResult mvcResult = mockMvc.perform(put(INGREDIENT_PATH_ID, ingredientDTO.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ingredientDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()", is(2)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //findout why this test case doesnt work properly
    /*@Test
    void testBadIngredientPatch() throws Exception {
        IngredientDTO ingredientDTO = ingredientServiceImpl.listIngredients().get(0);
        Map<String, Object> ingredientMap = new HashMap<>();
        ingredientMap.put("alcoholType", "  ");
        //given(ingredientService.patchIngredientById(any(),any())).willReturn(Optional.of(ingredientDTO));

        MvcResult mvcResult = mockMvc.perform(patch(INGREDIENT_PATH_ID, ingredientDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ingredientMap)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()", is(1)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }*/
    @Test
    void testListIngredients() throws Exception {
        given(ingredientService.listIngredients()).willReturn(ingredientServiceImpl.listIngredients());

        mockMvc.perform(get(INGREDIENT_PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()",is(15)));
    }
}
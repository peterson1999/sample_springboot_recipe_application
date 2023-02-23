package com.recipe.recipe_project.controller;

import com.recipe.recipe_project.command.RecipeCommand;
import com.recipe.recipe_project.domain.Recipe;
import com.recipe.recipe_project.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class RecipeControllerTest {

    RecipeController rc;
    MockMvc mockMvc;
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.rc = new RecipeController(recipeService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(rc).build();
    }

    @Test
    void testMockMVC()throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/show"));
    }
    @Test
    void getSaveRecipe()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/recipeform"))
                .andExpect((MockMvcResultMatchers.model().attributeExists("recipe")));
    }

    @Test
    void getUpdateRecipe() throws Exception{
        RecipeCommand recipeCommand=new RecipeCommand();
        recipeCommand.setId(1l);

        when(recipeService.getRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/recipeform"))
                .andExpect((MockMvcResultMatchers.model().attributeExists("recipe")));
    }
    @Test
    void postSaveRecipe()throws Exception{
        RecipeCommand recipeCommand=new RecipeCommand();
        recipeCommand.setId(1l);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id","")
                        .param("description","some string"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/show/1"));
    }

    @Test
    void deleteRecipe()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/1/delete")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id",""))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));
    }
//    @Test
//    void getRecipePage() {
//        assertEquals("recipe/show",this.rc.getRecipePage("1",model));
//        verify(recipeService,times(1)).findById(anyLong());
//        verify(model,times(1)).addAttribute(eq("recipe"),any(Recipe));
//    }
}
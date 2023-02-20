package com.recipe.recipe_project.controller;

import com.recipe.recipe_project.domain.Recipe;
import com.recipe.recipe_project.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RecipeControllerTest {

    RecipeController rc;
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.rc = new RecipeController(recipeService);
    }

    @Test
    void testMockMVC()throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(rc).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/show"));
    }
//    @Test
//    void getRecipePage() {
//        assertEquals("recipe/show",this.rc.getRecipePage("1",model));
//        verify(recipeService,times(1)).findById(anyLong());
//        verify(model,times(1)).addAttribute(eq("recipe"),any(Recipe));
//    }
}
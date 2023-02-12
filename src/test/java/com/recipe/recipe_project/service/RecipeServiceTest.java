package com.recipe.recipe_project.service;

import com.recipe.recipe_project.domain.Recipe;
import com.recipe.recipe_project.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceTest {
    RecipeService recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService=new RecipeService(recipeRepository);
    }

    @Test
    void getRecipeList() {
        Recipe recipe=new Recipe();
        HashSet<Recipe> recipeHashSet = new HashSet<>();
        recipeHashSet.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeHashSet);

        Set<Recipe> recipeSet= (Set<Recipe>) recipeRepository.findAll();
        assertEquals(1,recipeSet.size());
        verify(recipeRepository,times(1)).findAll();
    }
}
package com.recipe.recipe_project.service;

import com.recipe.recipe_project.domain.Recipe;
import com.recipe.recipe_project.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Iterable<Recipe> getRecipeList(){
        return recipeRepository.findAll();
    }

    public Recipe findById(Long id){
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(!recipe.isPresent()){
            throw new RuntimeException("Null recipe");
        }
        return recipe.get();
    }
}

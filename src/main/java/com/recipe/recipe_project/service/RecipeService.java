package com.recipe.recipe_project.service;

import com.recipe.recipe_project.command.RecipeCommand;
import com.recipe.recipe_project.converter.RecipeCommandToRecipe;
import com.recipe.recipe_project.converter.RecipeToRecipeCommand;
import com.recipe.recipe_project.domain.Recipe;
import com.recipe.recipe_project.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeService(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
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

    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand){
        Recipe recipeFromCommand = recipeCommandToRecipe.convert(recipeCommand);
        Recipe recipe = recipeRepository.save(recipeFromCommand);
        log.debug("Saved new Recipe: "+ recipe.getId());

        return recipeToRecipeCommand.convert(recipe);
    }

    public RecipeCommand getRecipeCommandById(Long id){
        return recipeToRecipeCommand.convert(findById(id));
    }

    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}

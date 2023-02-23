package com.recipe.recipe_project.converter;

import com.recipe.recipe_project.command.RecipeCommand;
import com.recipe.recipe_project.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand,Recipe> {

    CategoryCommandToCategory categoryCommandConverter;
    IngredientCommandToIngredient ingredientCommandConverter;
    NotesCommandToNotes notesCommandConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryCommandConverter, IngredientCommandToIngredient ingredientCommandConverter, NotesCommandToNotes notesCommandConverter) {
        this.categoryCommandConverter = categoryCommandConverter;
        this.ingredientCommandConverter = ingredientCommandConverter;
        this.notesCommandConverter = notesCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand==null)
            return null;

        final Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setServings(recipeCommand.getServings());
        recipe.setNotes(notesCommandConverter.convert(recipeCommand.getNotes()));

        if(recipeCommand.getIngredients()!=null && recipeCommand.getIngredients().size()>0){
            recipeCommand.getIngredients()
                    .forEach(ingredientCommand -> recipe.getIngredients().add(ingredientCommandConverter.convert(ingredientCommand)));
        }

        if(recipeCommand.getCategories()!=null && recipeCommand.getCategories().size()>0){
            recipeCommand.getCategories()
                    .forEach(categoryCommand ->  recipe.getCategories().add(categoryCommandConverter.convert(categoryCommand)));
        }
        return recipe;
    }
}

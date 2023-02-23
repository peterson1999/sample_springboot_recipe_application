package com.recipe.recipe_project.converter;

import com.recipe.recipe_project.command.CategoryCommand;
import com.recipe.recipe_project.command.IngredientCommand;
import com.recipe.recipe_project.command.NotesCommand;
import com.recipe.recipe_project.command.RecipeCommand;
import com.recipe.recipe_project.domain.Category;
import com.recipe.recipe_project.domain.Ingredient;
import com.recipe.recipe_project.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    CategoryToCategoryCommand categoryCommandConverter;
    IngredientToIngredientCommand ingredientCommandConverter;
    NotesToNotesCommand notesCommandConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryCommandConverter, IngredientToIngredientCommand ingredientCommandConverter, NotesToNotesCommand notesCommandConverter) {
        this.categoryCommandConverter = categoryCommandConverter;
        this.ingredientCommandConverter = ingredientCommandConverter;
        this.notesCommandConverter = notesCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe==null)
            return null;

        final RecipeCommand recipeCommand= new RecipeCommand();
        recipeCommand.setId(recipe.getId());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setNotes(notesCommandConverter.convert(recipe.getNotes()));

        if(recipe.getIngredients()!=null && recipe.getIngredients().size()>0){
            recipe.getIngredients()
                    .forEach(
                            ingredient -> recipeCommand.getIngredients().add(
                                    ingredientCommandConverter.convert(ingredient)));
        }

        if(recipe.getCategories()!=null && recipe.getCategories().size()>0){
            recipe.getCategories()
                    .forEach(
                            category -> recipeCommand.getCategories().add(
                                    categoryCommandConverter.convert(category)));
        }
        return recipeCommand;
    }
}

package com.recipe.recipe_project.repository;

import com.recipe.recipe_project.domain.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient,Long> {
    //Optional<Ingredient> findByIdAndRecipeId(Long ingredientId, Long recipeId);
    List<Ingredient> findAllByRecipeId(Long recipeId);
}

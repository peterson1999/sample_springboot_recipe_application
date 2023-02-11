package com.recipe.recipe_project.repository;

import com.recipe.recipe_project.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}

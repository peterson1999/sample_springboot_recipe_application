package com.recipe.recipe_project.repository;

import com.recipe.recipe_project.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}

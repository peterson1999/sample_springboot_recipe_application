package com.recipe.recipe_project.repository;

import com.recipe.recipe_project.domain.Category;
import com.recipe.recipe_project.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository <UnitOfMeasure, Long> {
    Optional<UnitOfMeasure> findByDescription(String uom);
}

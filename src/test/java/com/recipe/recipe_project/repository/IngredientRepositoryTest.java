package com.recipe.recipe_project.repository;

import com.recipe.recipe_project.domain.Ingredient;
import com.recipe.recipe_project.domain.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;
//    @Test
//    void findByIdAndRecipe_Id() {
//        Optional<Ingredient> ingredient = ingredientRepository.findByIdAndRecipeId(1l,1l);
//        assertEquals("fresh lime juice or lemon juice",ingredient.get().getDescription());
//    }

    @Test
    void findAllByRecipeId() {
        List<Ingredient> ingredient = ingredientRepository.findAllByRecipeId(1l);
        assertNotNull(ingredient);
    }
}
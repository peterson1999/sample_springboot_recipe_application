package com.recipe.recipe_project.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    Category category;
    @BeforeEach
    public void setUp(){
        category = new Category();
    }
    @Test
    void getId() {
        Long valueId = 4L;
        category.setId(valueId);
        assertEquals(4L,category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}
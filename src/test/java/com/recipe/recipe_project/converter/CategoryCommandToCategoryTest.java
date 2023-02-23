package com.recipe.recipe_project.converter;

import com.recipe.recipe_project.command.CategoryCommand;
import com.recipe.recipe_project.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {
    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    CategoryCommandToCategory categoryCommandToCategory;

    @BeforeEach
    public void setUp() throws Exception {
        categoryCommandToCategory= new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(categoryCommandToCategory.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(categoryCommandToCategory.convert(new CategoryCommand()));
    }
    @Test
    void convert() throws Exception{
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        Category category = categoryCommandToCategory.convert(categoryCommand);

        assertEquals(ID_VALUE,category.getId());
        assertEquals(DESCRIPTION,category.getDescription());
    }
}
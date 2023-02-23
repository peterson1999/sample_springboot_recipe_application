package com.recipe.recipe_project.controller;

import com.recipe.recipe_project.command.RecipeCommand;
import com.recipe.recipe_project.domain.Recipe;
import com.recipe.recipe_project.service.RecipeService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {
    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String getRecipePage(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new Recipe());

        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.getRecipeCommandById(Long.parseLong(id)));
        return "recipe/recipeform";
    }
    @PostMapping("/recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedRecipeCommand= recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/"+savedRecipeCommand.getId()+"/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id, Model model){
        recipeService.deleteById(Long.parseLong(id));

        return "redirect:/";
    }
}

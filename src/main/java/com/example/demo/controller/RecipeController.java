package com.example.demo.controller;

import com.example.demo.model.Recipe;
import com.example.demo.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Operation(summary = "Create a new recipe")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created recipe"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping

    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }

    @Operation(summary = "Get all recipes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved recipes"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping

    public Page<Recipe> getAllRecipes(Pageable pageable) {
        return recipeService.getAllRecipes(pageable);
                                                              //http://localhost:8080/recipes?page=0&size=5&sort=name,asc
    }

    // Find by ID
    @Operation(summary = "Get a recipe by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved recipe"),
        @ApiResponse(responseCode = "404", description = "Recipe not found")
    })
    @GetMapping("/{id}")

    public Recipe getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    // Find by Name
    @Operation(summary = "Find recipes by name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved recipes"),
        @ApiResponse(responseCode = "404", description = "No recipes found with the given name")
    })
    @GetMapping("/search")

    public List<Recipe> getRecipeByName(@RequestParam String name) {
        return recipeService.findRecipeByName(name); 
        //http://localhost:8080/recipes/search?name=Pasta
    }

    @Operation(summary = "Update an existing recipe")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated recipe"),
        @ApiResponse(responseCode = "404", description = "Recipe not found")
    })
    @PutMapping("/{id}")

    public Recipe updateRecipe(@PathVariable Long id, @RequestBody Recipe updatedRecipe) {
        return recipeService.updateRecipe(id, updatedRecipe);
    }

    @Operation(summary = "Delete a recipe")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted recipe"),
        @ApiResponse(responseCode = "404", description = "Recipe not found")
    })
    @DeleteMapping("/{id}")

    public String deleteRecipe(@PathVariable Long id) {
        return recipeService.deleteRecipe(id);
    }
}

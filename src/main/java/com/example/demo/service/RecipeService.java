package com.example.demo.service;

import com.example.demo.model.Recipe;
import com.example.demo.repository.RecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Page<Recipe> getAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id " + id));
    }

    public List<Recipe> findRecipeByName(String name) {
        return recipeRepository.findByName(name);
    }

    public Recipe updateRecipe(Long id, Recipe updatedRecipe) {
        Optional<Recipe> existingRecipe = recipeRepository.findById(id);
        if (existingRecipe.isPresent()) {
            Recipe recipe = existingRecipe.get();
            recipe.setName(updatedRecipe.getName());
            recipe.setIngredients(updatedRecipe.getIngredients());
            recipe.setInstructions(updatedRecipe.getInstructions());
            return recipeRepository.save(recipe);
        }
        throw new RuntimeException("Recipe not found with id " + id);
    }

    public String deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
        return "Recipe successfully deleted!";
    }
}

package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String ingredients;
    private String instructions;

    @OneToMany(mappedBy = "recipe")
    private List<Rating> ratings;

    public Recipe() {}

    public Recipe(String name, String ingredients, String instructions, List<Rating> ratings) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.ratings = ratings;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }
    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
    public List<Rating> getRatings() { return ratings; }
    public void setRatings(List<Rating> ratings) { this.ratings = ratings; }
}

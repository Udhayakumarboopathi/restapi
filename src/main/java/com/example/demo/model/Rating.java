package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Rating() {}

    public Rating(Long id, String user, int rating, Recipe recipe) {
        this.id = id;
        this.user = user;
        this.rating = rating;
        this.recipe = recipe;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public Recipe getRecipe() { return recipe; }
    public void setRecipe(Recipe recipe) { this.recipe = recipe; }
}

package com.example.demo.controller;

import com.example.demo.model.Rating;
import com.example.demo.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @Operation(summary = "Add a new rating")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully added rating"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping

    public Rating addRating(@RequestBody Rating rating) {
        return ratingService.addRating(rating);   //http://localhost:8080/ratings
    }

    @Operation(summary = "Update an existing rating")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated rating"),
        @ApiResponse(responseCode = "404", description = "Rating not found")
    })
    @PutMapping("/{id}")

    public Rating updateRating(@PathVariable Long id, @RequestBody Rating rating) {
        return ratingService.updateRating(id, rating);
    }

    @Operation(summary = "Delete a rating")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted rating"),
        @ApiResponse(responseCode = "404", description = "Rating not found")
    })
    @DeleteMapping("/{id}")

    public String deleteRating(@PathVariable Long id) {
        return ratingService.deleteRating(id);               
    }

    @Operation(summary = "Get all ratings")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved ratings"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping

    public List<Rating> getAllRatings(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy,
                                      @RequestParam(defaultValue = "asc") String direction) {
        return ratingService.getAllRatings(page, size, sortBy, direction);
                                                                                             //http://localhost:8080/ratings?page=0&size=5&sortBy=rating&direction=asc
    }

    @Operation(summary = "Get a rating by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved rating"),
        @ApiResponse(responseCode = "404", description = "Rating not found")
    })
    @GetMapping("/{id}")

    public Rating getRatingById(@PathVariable Long id) {
        return ratingService.getRatingById(id);
    }

    @Operation(summary = "Get ratings by user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved ratings"),
        @ApiResponse(responseCode = "404", description = "No ratings found for user")
    })
    @GetMapping("/search")

    public List<Rating> getRatingByUser(@RequestParam String user) {
        return ratingService.getRatingByUser(user); // JPQL http://localhost:8080/ratings/search?user=udhay
    }
}

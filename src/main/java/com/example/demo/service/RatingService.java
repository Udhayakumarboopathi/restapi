package com.example.demo.service;

import com.example.demo.model.Rating;
import com.example.demo.repository.RatingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating updateRating(Long id, Rating updatedRating) {
        Rating existingRating = ratingRepository.findById(id).orElseThrow();
        existingRating.setUser(updatedRating.getUser());
        existingRating.setRating(updatedRating.getRating());
        return ratingRepository.save(existingRating);
    }

    public String deleteRating(Long id) {
        ratingRepository.deleteById(id);
        return "Rating successfully deleted";
    }

    public List<Rating> getAllRatings(int page, int size, String sortBy, String direction) {
        Pageable pageable = PageRequest.of(page, size, org.springframework.data.domain.Sort.Direction.fromString(direction), sortBy);
        Page<Rating> ratingsPage = ratingRepository.findAll(pageable);
        return ratingsPage.getContent();
    }

    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    public List<Rating> getRatingByUser(String user) {
        return ratingRepository.findByUser(user);
    }
}

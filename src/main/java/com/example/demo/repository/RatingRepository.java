package com.example.demo.repository;

import com.example.demo.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByUser(String user);

    @Query("SELECT r FROM Rating r WHERE r.user = ?1")
    List<Rating> searchByUser(String user);
}

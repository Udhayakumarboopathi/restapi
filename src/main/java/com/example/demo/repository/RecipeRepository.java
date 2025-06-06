package com.example.demo.repository;

import com.example.demo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByName(String name);
    @SuppressWarnings("null")
    Page<Recipe> findAll(Pageable pageable);
}

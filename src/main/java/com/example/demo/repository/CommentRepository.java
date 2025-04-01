package com.example.demo.repository;

import com.example.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Custom JPA Methods (Spring Data JPA automatically generates queries)
    @SuppressWarnings("null")
    Optional<Comment> findById(Long id);
    List<Comment> findByUser(String user); // "findByName" in your case is "findByUser"

    // JPQL Query (Manually defined query)
    @Query("SELECT c FROM Comment c WHERE c.content LIKE %?1%")
    List<Comment> searchByContent(String content);
    
}

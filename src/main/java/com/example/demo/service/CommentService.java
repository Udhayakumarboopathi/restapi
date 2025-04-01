package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Add a comment
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // Update a comment
    public Comment updateComment(Long id, Comment updatedComment) {
        Optional<Comment> existingComment = commentRepository.findById(id);
        if (existingComment.isPresent()) {
            Comment comment = existingComment.get();
            comment.setUser(updatedComment.getUser());
            comment.setContent(updatedComment.getContent());
            return commentRepository.save(comment);
        }
        return null;
    }

    // Delete a comment
    public String deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return "Comment successfully deleted";
        }
        return "Comment not found";
    }

    // Get all comments with pagination & sorting
    public Page<Comment> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable); 
    }

    // Find by ID (Custom JPA)
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    // Find by user (Custom JPA)
    public List<Comment> findByUser(String user) {
        return commentRepository.findByUser(user);
    }

    // Find by content (JPQL)
    public List<Comment> searchByContent(String content) {
        return commentRepository.searchByContent(content);
    }
}

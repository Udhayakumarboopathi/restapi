package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Add a comment
    @Operation(summary = "Add a new comment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully added comment"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping

    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    // Update a comment
    @Operation(summary = "Update an existing comment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated comment"),
        @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @PutMapping("/{id}")

    public Comment updateComment(@PathVariable Long id, @RequestBody Comment updatedComment) {
        return commentService.updateComment(id, updatedComment);
    }

    // Delete a comment
    @Operation(summary = "Delete a comment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted comment"),
        @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @DeleteMapping("/{id}")

    public String deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

    // Get all comments with pagination & sorting
    @Operation(summary = "Get all comments")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved comments"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping

    public Page<Comment> getAllComments(Pageable pageable) {
        return commentService.getAllComments(pageable);  //GET http://localhost:8080/comments?page=0&size=5&sort=user,asc

    }

    // Find by ID (Custom JPA)
    
    @Operation(summary = "Get a comment by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved comment"),
        @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @GetMapping("/id/{id}")

    public Comment getCommentById(@PathVariable Long id) {
    return commentService.findById(id)
    .orElseThrow(() -> new RuntimeException("Comment not found with ID: " + id));
    }


    // Find by user (Custom JPA)
    @Operation(summary = "Find comments by user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved comments"),
        @ApiResponse(responseCode = "404", description = "No comments found for user")
    })
    @GetMapping("/user/{user}")

    public List<Comment> findByUser(@PathVariable String user) {  //http://localhost:8080/comments/user/raja
        return commentService.findByUser(user);
    }

    // Find by content (JPQL)
    @Operation(summary = "Search comments by content")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved comments"),
        @ApiResponse(responseCode = "404", description = "No comments found with the given content")
    })
    @GetMapping("/content/{content}")

    public List<Comment> searchByContent(@PathVariable String content) {
        return commentService.searchByContent(content);
    }
}

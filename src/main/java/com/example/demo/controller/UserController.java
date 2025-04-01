package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }                                                        //http://localhost:8080/User

    // Create a new user
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created user"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping

    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // Get all users
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved users"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user by ID
    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")

    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Update user
    @Operation(summary = "Update an existing user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated user"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")

    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete user
    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted user"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")

public String deleteUser(@PathVariable Long id) {
    boolean isDeleted = userService.deleteUser(id);
    if (isDeleted) {
        return "User deleted successfully";
    } else {
        return "User not found";
    }
}

}

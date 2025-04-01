package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user;
    private String content;

    @ManyToOne
    @JoinColumn(name = "rating_id")
    private Rating rating;

    public Comment() {}

    public Comment(String user, String content, Rating rating) {
        this.user = user;
        this.content = content;
        this.rating = rating;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Rating getRating() { return rating; }
    public void setRating(Rating rating) { this.rating = rating; }
}

package com.teamtreehouse.blog.model;

import java.time.LocalDateTime;

public class Comment {
    private String author;
    private String content;
    private LocalDateTime date;

    // Constructor
    public Comment(String author, String content, LocalDateTime date) {
        this.author = author;
        this.content = content;
        this.date = date;
    }

    // Getters and Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

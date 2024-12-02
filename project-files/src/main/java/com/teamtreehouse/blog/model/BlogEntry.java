package com.teamtreehouse.blog.model;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

public class BlogEntry {

    private String title;
    private String content;
    private LocalDateTime date;
    List<Comment> comments;

    // Constructor
    public BlogEntry(String title, String content, LocalDateTime date) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.comments = new ArrayList<>();
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


    // Methods to manage comments
    public void addComment(Comment comment) {
       comments.add(comment);
    }

    public List<Comment> getComments(){
        return comments;
    }
}

package com.teamtreehouse.blog.model;

import com.github.slugify.Slugify;

import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

public class BlogEntry {
    private String title;
    private String slug;
    private String content;
    private LocalDateTime date;
    List<Comment> comments;

    // Constructor
    public BlogEntry(String title, String content, LocalDateTime date) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.comments = new ArrayList<>();
        try {
            Slugify slugify = new Slugify();
            slug = slugify.slugify(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getSlug(){
        return slug;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
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

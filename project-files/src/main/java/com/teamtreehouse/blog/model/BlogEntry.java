package com.teamtreehouse.blog.model;

import com.github.slugify.Slugify;

import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlogEntry {
    private String title;
    private String slug;
    private String content;
    private LocalDateTime date;
    private Set<String> tags;
    List<Comment> comments;


    // Constructor
    public BlogEntry(String title, String content, LocalDateTime date) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.tags = new HashSet<>();
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

    public List<Comment> getComments(){
        return comments;
    }

    public Set<String> getTags(){
        return tags;
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

    // Method to manage comments
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    // Methods for managing tags
    public void addTag(String tag) {
        tags.add(tag.toLowerCase());
    }

    public void removeTag(String tag){
        tags.remove(tag.toLowerCase());
    }

}

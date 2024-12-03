package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.model.BlogEntry;

import java.util.ArrayList;
import java.util.List;

public class SimpleBlogEntryDAO implements BlogDao {

    private final List<BlogEntry> blogEntries;

    public SimpleBlogEntryDAO() {
        blogEntries = new ArrayList<>();
    }

    // Add new entry to the blog entry list
    @Override
    public boolean addEntry(BlogEntry blogEntry) {
        return blogEntries.add(blogEntry);
    }

    // Retrieves all blog posts
    @Override
    public List<BlogEntry> findAllEntries() {
        return new ArrayList<>(blogEntries); // Return a copy to prevent modification
    }

    // Fetchs a specific blog entry
    @Override
    public BlogEntry findEntryBySlug(String slug) {
        return blogEntries.stream()
                .filter(blogEntry -> blogEntry.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}

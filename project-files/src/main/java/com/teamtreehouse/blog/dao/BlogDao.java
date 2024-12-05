package com.teamtreehouse.blog.dao;

import com.github.slugify.Slugify;
import com.teamtreehouse.blog.model.BlogEntry;

import java.io.IOException;
import java.util.List;

public interface BlogDao {
    boolean addEntry(BlogEntry blogEntry);

    List<BlogEntry> findAllEntries();

    BlogEntry findEntryBySlug(String slug);

    void deleteEntryBySlug(String slug);

}

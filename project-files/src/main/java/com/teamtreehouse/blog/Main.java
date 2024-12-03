package com.teamtreehouse.blog;

import com.teamtreehouse.blog.dao.BlogDao;
import com.teamtreehouse.blog.dao.SimpleBlogEntryDAO;
import com.teamtreehouse.blog.model.BlogEntry;

import java.time.LocalDateTime;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        BlogDao dao = new SimpleBlogEntryDAO();

        // Entries
        BlogEntry entry1 = new BlogEntry("The best day I’ve ever had", "Content", BlogEntry.getDate());
        BlogEntry entry2 = new BlogEntry("The absolute worst day I’ve ever had", "Content", BlogEntry.getDate());
        BlogEntry entry3 = new BlogEntry("That time at the mall", "Content", BlogEntry.getDate());
        BlogEntry entry4 = new BlogEntry("Dude, where’s my car?", "Content", BlogEntry.getDate());

        // Add entries
        dao.addEntry(entry1);
        dao.addEntry(entry2);
        dao.addEntry(entry3);
        dao.addEntry(entry4);

    }
}

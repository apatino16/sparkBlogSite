package com.teamtreehouse.blog;

import com.teamtreehouse.blog.dao.BlogDao;
import com.teamtreehouse.blog.dao.SimpleBlogEntryDAO;
import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {
    public static void main(String[] args) {
        BlogDao dao = new SimpleBlogEntryDAO();

        // Entries
        BlogEntry entry1 = new BlogEntry("The best day I’ve ever had", "Content", LocalDateTime.of(2024, 10, 1, 12, 0));
        BlogEntry entry2 = new BlogEntry("The absolute worst day I’ve ever had", "Content", LocalDateTime.of(2024, 10, 1, 12, 0));
        BlogEntry entry3 = new BlogEntry("That time at the mall", "Content", LocalDateTime.of(2024, 10, 1, 12, 0));
        BlogEntry entry4 = new BlogEntry("Dude, where’s my car?", "Content", LocalDateTime.of(2024, 10, 1, 12, 0));

        // Add entries
        dao.addEntry(entry1);
        dao.addEntry(entry2);
        dao.addEntry(entry3);
        dao.addEntry(entry4);

        // Index Page Route: display all blog entries
        get("/", (req, res) -> {
            // Retrieve all blog entries from the DAO
            List<BlogEntry> entries = dao.findAllEntries();

            // Create model to pass to the template
            Map<String, Object> model = new HashMap<>();
            model.put("entries", entries);

            // Render the template with the model
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // Detail Page Route: display a specific blog entry
        get("/entries/:slug", (req, res) -> {
            // Extract slug from URL parameters
            String slug = req.params(":slug");
            // Find the blog entry with the matching slug
            BlogEntry entry = dao.findEntryBySlug(slug);

            // Model
            Map<String, Object> model = new HashMap<>();
            model.put("entry", entry);
            model.put("comment", entry.getComments());

            // Render the template
            return new ModelAndView(model, "detail.hbs");
        }, new HandlebarsTemplateEngine());

        // Route for add/ edit pages:
        // Shows the add new entry form
        get("/entries/new", (req, res) -> {
            return new ModelAndView(null, "new.hbs");
        }, new HandlebarsTemplateEngine());

        // Handles form submission for a new blog entry
        post("/entries", (req, res) -> {
            String title = req.queryParams("title");
            String content = req.queryParams("content");
            LocalDateTime date;

            BlogEntry newEntry = new BlogEntry(title, content, date = LocalDateTime.now());
            dao.addEntry(newEntry);

            res.redirect("/"); // Redirect to the index page
            return null;
        });

        // Route for comment posting
        post("/entries/:slug/comments", ((req, res) -> {
            String slug = req.params(":slug");
            String author = req.queryParams("author");
            String content = req.queryParams("content");

            BlogEntry entry = dao.findEntryBySlug(slug);

            // Add comment to the blog entry
            Comment comment = new Comment(author, content, LocalDateTime.now());
            entry.addComment(comment);

            res.redirect("/entries/" + slug); // Redirect to the detail page
            return null;
        }));
    }
}

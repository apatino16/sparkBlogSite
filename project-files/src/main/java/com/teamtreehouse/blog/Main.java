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

import static spark.Spark.*;

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

    // Before
        // Middleware to enforce authentication: before accessing the add or edit page, check if the user cookie is present and valid
        before("/entries/*", (req, res) -> {
            String user = req.cookie("user");
            if(!"admin".equals(user)){
                res.redirect("/password"); // Redirect to login page if unauthenticated
                halt();
            }
        });

    // Get
        // Password Page Route: this route renders the password page
        get("/password", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "password.hbs");
        }, new HandlebarsTemplateEngine());

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
            model.put("comments", entry.getComments());

            // Render the template
            return new ModelAndView(model, "detail.hbs");
        }, new HandlebarsTemplateEngine());

        // Route for new entry page
        get("/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "new.hbs");
        }, new HandlebarsTemplateEngine());

    // POST Request
        // Route to handle Login Submission
        // Password validation: Submitting the correct password (admin) sets a cookie (user=admin) and redirects to /entries/new
        post("/password", (req, res) -> {
            String password = req.queryParams("password");

            if ("admin".equals(password)){
                // set cookie
                res.cookie("user", "admin");
                res.redirect("/new"); // Redirect to the add entry page
                return null;
            } else {
                //Redirect back to login page
                res.redirect("/password");
            }
            return null;
        });

        // Handles form submission for a new blog entry
        post("/new", (req, res) -> {
            String title = req.queryParams("title");
            String content = req.queryParams("content");
            String tagsInput = req.queryParams("tags");

            // Creates a new BlogEntry
            BlogEntry newEntry = new BlogEntry(title, content, LocalDateTime.now());

            // Add tags if provided
            if (tagsInput != null && !tagsInput.isEmpty()){
                String[] tags = tagsInput.split(",");
                for (String tag : tags){
                    newEntry.addTag(tag.trim());
                }
            }

            dao.addEntry(newEntry);

            // Redirect to the homepage after adding the entry
            res.redirect("/");
            return null;
        });

        // Handles Edit Submissions: process and updates the corresponding blog entry
        post("/entries/:slug/edit", (req, res) -> {
           String slug = req.params(":slug");
           String newTitle = req.queryParams("title");
           String newContent = req.queryParams("content");
            String tagsInput = req.queryParams("tags");

           // Find the existing blog entry by slug
            BlogEntry entry = dao.findEntryBySlug(slug);

            // Update the entry with the new title and content
            entry.setTitle(newTitle);
            entry.setContent(newContent);

            // Update tags
            entry.getTags().clear(); // Clear existing tags

            // Add tags if provided
            if (tagsInput != null && !tagsInput.isEmpty()){
                String[] tags = tagsInput.split(",");
                for (String tag : tags){
                    entry.addTag(tag.trim());
                }
            }

            // Redirect to the detail page of the edited entry
            res.redirect("/entries/" + slug);

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

# Spark Blog Site
---
## Project Overview
---
A minimalistic blog platform built using Java, Spark Java, and Handlebars for dynamic templating. This project allows users to read blog posts, add comments, and categorize posts with tags. Admin users can add, edit, or delete blog entries through a secure password-protected interface.

This project was created as part of Unit 4 of my Java Web Development Techdegree at Treehouse.
---

## **Features**

### **Public User**
- View a list of blog posts on the homepage.
- Read detailed blog entries with their comments.
- Post anonymous comments on blog entries.

### **Admin User**
- Add new blog entries.
- Edit existing blog entries.
- Delete blog entries.
- Categorize posts using tags.

### **General Features**
- Password-protected admin interface.
- Tag-based categorization and filtering.

---

## **Technologies Used**

- **Java**: Core programming language for the application.
- **Spark Java**: Web framework for routing and server-side logic.
- **Handlebars**: Template engine for dynamic HTML rendering.
- **CSS**: Styling and responsive design.
- **Gradle**: Build automation tool.
- **LocalDateTime**: Java API for handling date and time.

---

## **Setup Instructions**

### **Prerequisites**
- Java 11 or higher installed.
- Gradle installed
- An IDE like IntelliJ IDEA, Eclipse, or VS Code.

### **Clone the Repository**
```bash
git clone https://github.com/apatino16/sparkBlogSite.git
```

### **Run the Application**
1. Open the project in your IDE.
2. Build and run the project using Gradle:
   ```bash
   gradle run
   ```
3. Access the application in your browser at `http://localhost:4567`

---

## **File Structure**

```
src/
  main/
    java/
      dao/                # Data Access Objects
      model/              # Models (e.g., BlogEntry, Comment)
      Main.java           # Entry point of the application
    resources/
      css/                # Stylesheets
        site.css
        normalize.css
      templates/          # Handlebars templates
        base.hbs
        index.hbs
        detail.hbs
        new.hbs
        edit.hbs
        password.hbs
```

---

## **How to Use**

### **Public Users**
1. Open the homepage (`/`) to see a list of blog entries.
2. Click on a blog title to view its details, including comments.
3. Post a comment on any blog entry using the form provided.

### **Admin Users**
1. Visit `/login` and enter the password (`admin`) to gain admin access.
2. Add new entries via `/entries/new`.
3. Edit or delete entries via `/entries/:slug/edit`.

---

## **Key Routes**

| Route                   | Method | Description                                    |
|-------------------------|--------|------------------------------------------------|
| `/`                     | GET    | Displays the homepage with all blog entries.  |
| `/entries/:slug`        | GET    | Displays details of a specific entry.         |
| `/entries/new`          | GET    | Displays the form to add a new entry.         |
| `/entries/:slug/edit`   | GET    | Displays the form to edit an existing entry.  |
| `/entries/:slug/edit`   | POST   | Updates the specified blog entry.             |
| `/entries/:slug/delete` | POST   | Deletes the specified blog entry.             |
| `/password`             | GET    | Displays the password page for admin login.   |

---

## **Planned Features**
- Tag filtering on the homepage.

---

## **Sources**
[Spark Framework Documentation](https://web.archive.org/web/20240205075950/http://sparkjava.com/documentation#getting-started)

---

 

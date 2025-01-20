package com.service2.EditCourse;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.service2.EditCourse.CourseService;
import com.service2.EditCourse.Models.Course;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final MongoCollection<Document> courseCollection;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;

        // Connect to MongoDB Atlas
        String connectionString = "mongodb+srv://amrmoh:DWr4h8ESLdc7IAVP@cluster0.dgipkmw.mongodb.net/CreateCourse?retryWrites=true&w=majority&appName=cluster2";
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        // Get the database
        MongoDatabase database = mongoClient.getDatabase("CreateCourse");

        // Get the collection
        this.courseCollection = database.getCollection("cluster2");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        try {
            courseService.editCourse(id, updatedCourse);
            return ResponseEntity.ok("Course edited successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCourse(@PathVariable Long id) {
        try {
            courseService.removeCourse(id);
            return ResponseEntity.ok("Course removed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        String errorMessage = "{\"error\": \"" + ex.getMessage() + "\"}";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);    }
}



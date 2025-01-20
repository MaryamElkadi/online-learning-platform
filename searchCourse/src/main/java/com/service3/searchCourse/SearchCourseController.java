package com.service3.searchCourse;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.service3.searchCourse.Models.Course;
import com.service3.searchCourse.repo.CourseRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/search-courses")
public class SearchCourseController {
    private final MongoCollection<Document> courseCollection;

    @Autowired
    private CourseRepository courseRepository;

    public SearchCourseController(CourseRepository courseRepository) {
        this.courseRepository=courseRepository;
        String connectionString = "mongodb+srv://amrmoh:DWr4h8ESLdc7IAVP@cluster0.dgipkmw.mongodb.net/SearchCourse?retryWrites=true&w=majority&appName=cluster3";
        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(new ConnectionString(connectionString)).build();

        // Connect to MongoDB Atlas
        MongoClient mongoClient = MongoClients.create(settings);

        // Get the database
        MongoDatabase database = mongoClient.getDatabase("SearchCourse");

        // Get the collection
        courseCollection = database.getCollection("cluster3");
    }


    @Autowired
    private CourseService courseService;

    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam String query, @RequestParam(required = false) String sortBy) {
        List<Course> courses = courseService.searchCourses(query, sortBy);
        if (courses.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(courses);
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        try {
            List<Course> courses = courseService.getAllCourses();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

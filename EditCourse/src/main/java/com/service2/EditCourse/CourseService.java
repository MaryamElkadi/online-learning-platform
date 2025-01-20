package com.service2.EditCourse;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.service2.EditCourse.Models.Course;
import com.service2.EditCourse.repo.CourseRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final MongoCollection<Document> courseCollection;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
        // Connect to MongoDB Atlas
        String connectionString = "mongodb+srv://amrmoh:DWr4h8ESLdc7IAVP@cluster0.dgipkmw.mongodb.net/CreateCourse?retryWrites=true&w=majority&appName=cluster2";
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        // Get the database
        MongoDatabase database = mongoClient.getDatabase("CreateCourse");

        //this.courseCollection = courseCollection;
        this.courseCollection = database.getCollection("cluster2");
    }

    public void editCourse(Long id, Course updatedCourse) {
        Document query = new Document("_id", id);
        Document existingCourse = courseCollection.find(query).first();
        if (existingCourse == null) {
            throw new IllegalArgumentException("Course not found");
        }

        Document updatedDocument = new Document()
                .append("name", updatedCourse.getName())
                .append("description", updatedCourse.getDescription());

        courseCollection.updateOne(Filters.eq("_id", id), Updates.combine(
                Updates.set("name", updatedCourse.getName()),
                Updates.set("description", updatedCourse.getDescription())
        ));
    }

    public void removeCourse(Long id) {
        Document query = new Document("_id", id);
        Document existingCourse = courseCollection.find(query).first();
        if (existingCourse == null) {
            throw new IllegalArgumentException("Course not found");
        }

        courseCollection.deleteOne(query);
    }
}

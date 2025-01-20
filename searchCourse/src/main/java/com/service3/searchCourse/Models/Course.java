package com.service3.searchCourse.Models;


import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "cluster3")
public class Course {
    @Id
    private String id;

    @Field
    private String name;

    @Field
    private int duration;

    @Field
    private String category;

    @Field
    private double rating;

    @Field
    private int capacity;

    @Field
    private int enrolledStudents;

    @Field
    private double reviews;
    public Course(String id, String name, int duration, String category, double rating, int capacity, int enrolledStudents, double reviews) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.category = category;
        this.rating = rating;
        this.capacity = capacity;
        this.enrolledStudents = enrolledStudents;
        this.reviews = reviews;
    }


    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(int enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public double getReviews() {
        return reviews;
    }

    public void setReviews(double reviews) {
        this.reviews = reviews;
    }
}

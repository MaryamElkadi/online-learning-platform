package com.service4.review;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.service4.review.Model.Review;
import com.service4.review.repo.ReviewRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewService = (ReviewService) reviewRepository;
        final MongoCollection<Document> reviewCollection;

        // Configure MongoDB Atlas connection settings
        String connectionString = "mongodb+srv://amrmoh:DWr4h8ESLdc7IAVP@cluster0.dgipkmw.mongodb.net/review?retryWrites=true&w=majority&appName=cluster4";
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();

        // Connect to MongoDB Atlas
        MongoClient mongoClient = MongoClients.create(settings);

        // Get the database
        MongoDatabase database = mongoClient.getDatabase("review");

        // Get the collection
        reviewCollection = database.getCollection("cluster4");
    }
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody Review review) {
        // Validate the review content and rating before saving
        if (review.getContent() == null || review.getContent().isEmpty()) {
            return ResponseEntity.badRequest().body("Review content cannot be empty.");
        }

        if (review.getRating() < 1 || review.getRating() > 5) {
            return ResponseEntity.badRequest().body("Rating must be between 1 and 5.");
        }

        // Save the review if it passes all checks
        Review savedReview = reviewService.saveReview(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

}
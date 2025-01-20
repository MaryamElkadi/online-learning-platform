package com.service3.searchCourse.repo;


import com.service3.searchCourse.Models.Course;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CourseRepository extends MongoRepository<Course, Long> {
    List<Course> findByCategoryContainingIgnoreCaseOrderByRatingDesc(String query);

    List<Course> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String query, String query1);
    // You can add custom query methods here if needed
}

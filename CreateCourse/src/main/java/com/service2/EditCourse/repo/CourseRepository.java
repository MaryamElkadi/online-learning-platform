package com.service2.EditCourse.repo;

import com.service2.EditCourse.CourseService;
import com.service2.EditCourse.Models.Course;
import com.service2.EditCourse.CourseService;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CourseRepository extends MongoRepository<Course, Long> {
    // You can add custom query methods here if needed
}

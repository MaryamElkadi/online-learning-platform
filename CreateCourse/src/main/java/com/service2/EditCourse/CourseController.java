package com.service2.EditCourse;

import com.service2.EditCourse.Models.Course;
import com.service2.EditCourse.repo.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Endpoint to create a new course
    @PostMapping("/api/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            Course savedCourse = courseRepository.save(course);
            return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get all courses
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        try {
            // Retrieve all courses from MongoDB Atlas
            List<Course> courses = courseRepository.findAll();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity <String> editCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        try {
            Course existingCourse = courseRepository.findById(id).orElseThrow();
            existingCourse.setName(updatedCourse.getName());
            existingCourse.setDuration(updatedCourse.getDuration());
            existingCourse.setCategory(updatedCourse.getCategory());
            existingCourse.setRating(updatedCourse.getRating());
            existingCourse.setCapacity(updatedCourse.getCapacity());
            existingCourse.setEnrolledStudents(updatedCourse.getEnrolledStudents());
            existingCourse.setReviews(updatedCourse.getReviews());
            courseRepository.save(existingCourse);
            return ResponseEntity.ok("Course edited successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCourse(@PathVariable Long id) {
        try {
            courseRepository.deleteById(id);
            return ResponseEntity.ok("Course removed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        String errorMessage = "{\"error\": \"" + ex.getMessage() + "\"}";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
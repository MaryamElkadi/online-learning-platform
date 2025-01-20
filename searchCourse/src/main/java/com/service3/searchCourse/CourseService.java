package com.service3.searchCourse;

import com.service3.searchCourse.Models.Course;
import com.service3.searchCourse.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class CourseService {

        @Autowired
        private CourseRepository courseRepository;

        public List<Course> searchCourses(String query, String sortBy) {
            if (sortBy != null && sortBy.equalsIgnoreCase("rating")) {
                return courseRepository.findByCategoryContainingIgnoreCaseOrderByRatingDesc(query);
            } else {
                return courseRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(query, query);
            }
        }

        public List<Course> getAllCourses() {
            return courseRepository.findAll();
        }
    }


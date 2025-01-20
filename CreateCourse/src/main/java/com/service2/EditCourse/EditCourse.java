package com.service2.EditCourse;

import com.service2.EditCourse.Models.Course;
import com.service2.EditCourse.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class EditCourse implements CommandLineRunner {
    private final CourseRepository courseRepository;

    @Autowired
    public EditCourse(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EditCourse.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createNewCourse(2, "Data Structures", 3, "Computer Science", 4, 3, 7, 4);
        createNewCourse(5, "Machine learning", 3, "AI", 3, 32, 11, 4);
        getAllCourses();
    }

    private void createNewCourse(long id, String name, int duration, String category, double rating, int capacity, int enrolledStudents, double reviews) {
        Course newCourse = new Course(id, name, duration, category, rating, capacity, enrolledStudents, reviews);
        Course savedCourse = courseRepository.save(newCourse);
        System.out.println("Saved course: " + savedCourse);
    }

    private void getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println("All courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }
}

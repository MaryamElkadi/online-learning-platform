package com.service2.EditCourse;

import com.service2.EditCourse.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	}
}

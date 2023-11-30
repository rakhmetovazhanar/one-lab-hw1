package com.example.onelab1.service;

import com.example.onelab1.db.CourseDb;
import com.example.onelab1.entity.Course;
import com.example.onelab1.exception.CourseNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    public void addCourse(Course course) {
        CourseDb.courses.add(course);
    }

    public Course getCourseByName(String name) {
        return CourseDb.courses.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new CourseNotFoundException("Course: %s not found".formatted(name)));
    }

    public void deleteCourseByName(String name) {
        Course courseByName = this.getCourseByName(name);
        CourseDb.courses.remove(courseByName);
    }

    public Course updateCourseByName(String name, String newName) {
        Course updateByCourseName = CourseDb.courses.stream()
                .filter(course -> course.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new CourseNotFoundException("Course: %s not found".formatted(name)));

        updateByCourseName.setName(newName);
        return updateByCourseName;
    }
}

package com.example.onelab1.controlelr;

import com.example.onelab1.dto.CourseSaveRequestDto;
import com.example.onelab1.entity.Course;
import com.example.onelab1.exception.CourseNotFoundException;
import com.example.onelab1.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Course> saveCourseByName(@RequestBody CourseSaveRequestDto requestDto) {
        String name = requestDto.getName();
        if (name == null || name.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Course course = new Course();
        course.setName(name);
        courseService.addCourse(course);
        return ResponseEntity.ok(course);
    }

    @GetMapping(value = "/byName")
    public ResponseEntity<Course> findCourseByName(@RequestParam(name = "name") String name) {
        Course courseByName = courseService.getCourseByName(name);
        return ResponseEntity.ok(courseByName);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> updateCourseByName(@RequestBody CourseSaveRequestDto requestDto) {
        String newName = requestDto.getNewName();

        if (newName == null || newName.isBlank()) {
            return ResponseEntity.badRequest().body("New name cannot be null or blank");
        }

        String oldName = requestDto.getName();
        if (oldName == null || oldName.isBlank()) {
            return ResponseEntity.badRequest().body("Old name cannot be null or blank");
        }

        try {
            Course updated = courseService.updateCourseByName(oldName, newName);
            return ResponseEntity.ok("Course updated successfully! New name: " + newName);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping(value = "/deleteByName")
    public ResponseEntity<Void> deleteCourse(@RequestParam(name = "name") String name) {
        courseService.deleteCourseByName(name);
        return ResponseEntity.ok().build();
    }

}

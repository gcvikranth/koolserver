package com.kool.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3050", "http://localhost:4200" })
@RestController
public class MyServerResource {

	@Autowired
	private HardcodedService hardCodedManagementService;

	@GetMapping("/instructors/{username}/courses")
	public List<Course> getAllCourses(@PathVariable String username) {
		return hardCodedManagementService.findAllCourses();
	}

	@GetMapping("/instructors/{username}/students")
	public List<Student> getAllStudents(@PathVariable String username) {
		return hardCodedManagementService.findAllStudents();
	}
	@GetMapping("/instructors/{username}/courses/{id}")
	public Course getCourse(@PathVariable String username, @PathVariable long id) {
		return hardCodedManagementService.findCourseById(id);
	}
	@GetMapping("/instructors/{username}/students/{id}")
	public Student getStudent(@PathVariable String username, @PathVariable long id) {
		return hardCodedManagementService.findStudentById(id);
	}
	@DeleteMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable String username, @PathVariable long id) {

		Course course = hardCodedManagementService.deleteCourseById(id);

		if (course != null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable String username, @PathVariable long id,
			@RequestBody Course course) {

		Course courseUpdated = hardCodedManagementService.save(course);

		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@PostMapping("/instructors/{username}/courses")
	public ResponseEntity<Void> createCourse(@PathVariable String username, @RequestBody Course course) {

		Course createdCourse = hardCodedManagementService.save(course);

		// Location
		// Get current resource url
		/// {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCourse.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}
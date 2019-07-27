package com.kool.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;
//@CrossOrigin(origins = "*,http://localhost:3000", allowedHeaders = "*")
//@CrossOrigin(origins = { "http://192.168.56.1:3000","http://localhost:3000","http://10.160.0.9","http://10.160.0.9:80", "http://34.93.62.135","http://34.93.62.135:80" })
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
public class MyServerResource {
	private Logger log(){
		return  Logger.getLogger("MyServerResource class");

	}

	@Autowired
	private HardcodedService hardCodedManagementService;

	@GetMapping("/instructors/{username}/courses")
	public List<Course> getAllCourses(@PathVariable String username) {
		return hardCodedManagementService.findAllCourses();
	}

	@GetMapping("/instructors/{username}/students")
	public List<Student> getAllStudents(@PathVariable String username) {
		log().info(" ## #Get all students");
		System.out.println(" **** ## #get all student ##### ***8");

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

	@DeleteMapping("/instructors/{username}/students/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable String username, @PathVariable long id) {

		Student student = hardCodedManagementService.deleteStudentById(id);

		if (student != null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable String username, @PathVariable long id,
			@RequestBody Course course) {

		log().info(" ## #Update course");

		Course courseUpdated = hardCodedManagementService.save(course);

		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@PutMapping("/instructors/{username}/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable String username, @PathVariable long id,
											   @RequestBody Student student) {
		log().info(" ## #Updastudent");

		Student studentUpdated = hardCodedManagementService.saveStudent(student);

		return new ResponseEntity<Student>(student, HttpStatus.OK);
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
	@PostMapping("/instructors/{username}/students")
	public ResponseEntity<Void> createStudent(@PathVariable String username, @RequestBody Student student) {
		log().info("Adding new student"+student.getStudentName());

		Student createdStudent = hardCodedManagementService.saveStudent(student);

		// Location
		// Get current resource url
		/// {id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdStudent.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}


}
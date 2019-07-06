package com.kool.server;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HardcodedService {

	private static List<Course> courses = new ArrayList<>();
	private static List<Student> students = new ArrayList<>();

	private static long courseIdCounter = 0;
	private static long studentIdCounter = 0;

	static {
		courses.add(new Course(++courseIdCounter, "kooladmin", "Learn Full stack with Spring Boot and Angular"));
		courses.add(new Course(++courseIdCounter, "kooladmin", "Learn Full stack with Spring Boot and React"));
		courses.add(new Course(++courseIdCounter, "kooladmin", "Master Microservices with Spring Boot and Spring Cloud"));
		courses.add(new Course(++courseIdCounter, "kooladmin",
				"Deploy Spring Boot Microservices to Cloud with Docker and Kubernetes"));
		students.add(new Student(++studentIdCounter, "Amitabh Bachpan","The Universal school" ,"IX C"));
		students.add(new Student(++studentIdCounter, "Sharad","The Universal school" ,"IV D"));
		students.add(new Student(++studentIdCounter, "Kotler ","The Timbuktu school" ,"IX C"));

	}

	public List<Course> findAllCourses() {
		return courses;
	}
	public List<Student> findAllStudents() {
		return students;
	}

	public Course save(Course course) {
		if (course.getId() == -1 || course.getId() == 0) {
			course.setId(++courseIdCounter);
			courses.add(course);
		} else {
			deleteCourseById(course.getId());
			courses.add(course);
		}
		return course;
	}
	public Student saveStudent(Student student) {
		if (student.getId() == -1 || student.getId() == 0) {
			student.setId(++studentIdCounter);
			students.add(student);
		} else {
			deleteStudentById(student.getId());
			students.add(student);
		}
		return student;
	}

	public Course deleteCourseById(long id) {
		Course course = findCourseById(id);


		if (course == null)
			return null;

		if (courses.remove(course)) {
			return course;
		}

		return null;
	}
	public Student deleteStudentById(long id) {
		Student student = findStudentById(id);


		if (student == null)
			return null;

		if (students.remove(student)) {
			return student;
		}

		return null;
	}

	public Course findCourseById(long id) {
		for (Course course : courses) {
			if (course.getId() == id) {
				return course;
			}
		}

		return null;
	}

	public Student findStudentById(long id) {
		for (Student student : students) {
			if (student.getId() == id) {
				return student;
			}
		}

		return null;
	}
}
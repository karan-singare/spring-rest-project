package com.karansingare.springrest.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karansingare.springrest.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	private List<Student> theStudents;
	 
	// define @PostConstruct to load the student data .. only once
	// this method will be called after constructor call
	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Poornima", "Patel"));
		theStudents.add(new Student("Mario","Rossie"));
		theStudents.add(new Student("Mary","Smith"));
		theStudents.add(new Student("Poornima", "Patel"));
		
	}
	
	// define endpoint for "/students" return list of students
	@GetMapping("/students")
	public List<Student> getStudents() {
		return theStudents;
	}
	
	// define endpoint for "/student/{studentId}" -  return student at index
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		//just index into to the list
		
		// check the StudentId against the list size
		
		if (studentId >= theStudents.size() || studentId < 0) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}
		
		return theStudents.get(studentId);
	}
	
	// Add an exception handler using @ExceptionHandler
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
		// create a StudentErrorREsponse 
		StudentErrorResponse error = new StudentErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		// return ResponseEntity
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	
}

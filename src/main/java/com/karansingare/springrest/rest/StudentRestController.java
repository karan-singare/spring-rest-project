package com.karansingare.springrest.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
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
	
	// define ednpoint for "/students" return list of students
	@GetMapping("/students")
	public List<Student> getStudents() {
		return theStudents;
	}
	
	// 
	
}

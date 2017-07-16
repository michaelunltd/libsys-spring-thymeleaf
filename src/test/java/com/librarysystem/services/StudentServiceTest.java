package com.librarysystem.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.librarysystem.dto.Student;

public class StudentServiceTest {

	@Autowired
	private StudentService studentService;
	
	@Test
	public void testFindOne() {
	}
	
	@Test
	public void testSave() {
		Student student = new Student("Mikko", "Tan", "201706270001");
		studentService.save(student);
	}
	
	@Test
	public void testFindAll() {
		
	}
	
	@Test
	public void testDelete() {
		
	}
	
	@Test
	public void testUpdate() {
		
	}
}

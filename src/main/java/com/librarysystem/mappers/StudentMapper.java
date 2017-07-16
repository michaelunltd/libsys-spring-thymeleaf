package com.librarysystem.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.librarysystem.dto.Borrow;
import com.librarysystem.dto.Student;
import com.librarysystem.entities.BorrowEntity;
import com.librarysystem.entities.StudentEntity;

public class StudentMapper {

	public static Student toDTO(StudentEntity studentEntity) {
		if (studentEntity == null) {
			return null;
		}
		Student student = new Student();
		student.setId(studentEntity.getId());
		student.setFirstName(studentEntity.getFirstName());
		student.setLastName(studentEntity.getLastName());
		student.setStudentId(studentEntity.getStudentId());
		
		return student;
	}
	
	public static StudentEntity toEntity(Student student) {
		if (student == null) {
			return null;
		}
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setId(student.getId());
		studentEntity.setFirstName(student.getFirstName());
		studentEntity.setLastName(student.getLastName());
		studentEntity.setStudentId(student.getStudentId());
		return studentEntity;
	}
	
	public static Student toDTOWithList(StudentEntity studentEntity) {
		Student student = toDTO(studentEntity);
		List<Borrow> borrows = studentEntity.getBorrows().stream().map(borrowEntity -> {
			return BorrowMapper.toDTOWithList(borrowEntity);
		}).collect(Collectors.toList());
		student.setBorrows(borrows);
		
		return student;
	}
	
	public static StudentEntity toEntityWithList(Student student) {
		StudentEntity studentEntity = toEntity(student);
		List<BorrowEntity> borrowsEntity = student.getBorrows().stream().map(borrow -> {
			return BorrowMapper.toEntityWithList(borrow);
		}).collect(Collectors.toList());
		studentEntity.setBorrows(borrowsEntity);
		return studentEntity;
	}
}

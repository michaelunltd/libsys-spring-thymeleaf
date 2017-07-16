package com.librarysystem.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarysystem.dto.Student;
import com.librarysystem.entities.StudentEntity;
import com.librarysystem.mappers.StudentMapper;
import com.librarysystem.repositories.StudentRepository;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public Student save(Student student) {
		if (student == null) {
			return null;
		}
		StudentEntity savedStudent = studentRepository.save(StudentMapper.toEntity(student));
		return StudentMapper.toDTO(savedStudent);
	}
	
	public Student saveWithList(Student student) {
		if (student == null) {
			return null;
		}
		StudentEntity savedStudent = studentRepository.save(StudentMapper.toEntityWithList(student));
		return StudentMapper.toDTOWithList(savedStudent);
	}
	
	public Student update(Long id, Student student) {
		if (student == null) {
			return null;
		}
		StudentEntity savedStudentEntity = studentRepository.save(StudentMapper.toEntity(student));
		return StudentMapper.toDTO(savedStudentEntity);
	}
	
	public Student findOne(Long id) {
		StudentEntity findStudent = studentRepository.findOne(id);
		return StudentMapper.toDTO(findStudent);
	}

	public Student findOneWithList(Long id) {
		StudentEntity findStudent = studentRepository.findOne(id);
		return StudentMapper.toDTOWithList(findStudent);
	}
	
	public List<Student> findAll() {
		List<Student> students = studentRepository.findAll().stream().map(studentEntity -> {
			Student student = StudentMapper.toDTO(studentEntity);
			return student;
		}).collect(Collectors.toList());
		
		return students;
	}
	
	public void delete(Long id) {
		StudentEntity studentEntity = studentRepository.findOne(id);
		if (studentEntity != null) {
			studentRepository.delete(id);
		}
	}
}

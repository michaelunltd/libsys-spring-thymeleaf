package com.librarysystem.dto;

import java.util.ArrayList;
import java.util.List;

public class Student {

	private Long id;
	private String firstName;
	private String lastName;
	private String studentId;
	private List<Borrow> borrows;
	
	public Student() {
		this.borrows = new ArrayList<>();
	}
	
	public Student(String firstName, String lastName, String studentId) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentId = studentId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public List<Borrow> getBorrows() {
		return borrows;
	}
	public void setBorrows(List<Borrow> borrows) {
		this.borrows = borrows;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("Student: [ firstName: " + this.firstName + ", lastName: " + this.lastName + ". studentId: " + this.studentId + "]\n");
		
		for (Borrow b : borrows) {
			sb.append(b.toString());
		}
		return sb.toString();
	}
}

package com.librarysystem.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "student_id", unique=true)
	private String studentId;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<BorrowEntity> borrows;
	
	public StudentEntity() {};
	
	public StudentEntity(String firstName, String lastName, String studentId) {
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

	public List<BorrowEntity> getBorrows() {
		return borrows;
	}

	public void setBorrows(List<BorrowEntity> borrows) {
		this.borrows = borrows;
	}
	
}

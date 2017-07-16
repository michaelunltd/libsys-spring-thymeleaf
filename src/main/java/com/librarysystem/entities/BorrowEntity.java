package com.librarysystem.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "borrow")
public class BorrowEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "transaction_id", unique=true)
	private String transactionId;
	
	@Column(name = "date_borrowed")
	private Date dateBorrowed;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "borrow", fetch=FetchType.LAZY)
	private List<BorrowedBookEntity> borrowedBooks;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private StudentEntity student;

	public BorrowEntity() {};
	
	public BorrowEntity(String transactionId, Date dateBorrowed, StudentEntity student) {
		this.transactionId = transactionId;
		this.dateBorrowed = dateBorrowed;
		this.student = student;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getDateBorrowed() {
		return dateBorrowed;
	}

	public void setDateBorrowed(Date dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	public List<BorrowedBookEntity> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(List<BorrowedBookEntity> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
		for (BorrowedBookEntity b : this.borrowedBooks) {
			b.setBorrow(this);
		}
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}
}

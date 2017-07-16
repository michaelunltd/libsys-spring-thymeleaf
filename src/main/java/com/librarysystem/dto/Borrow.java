package com.librarysystem.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Borrow {

	private Long id;
	private String transactionId;
	private Date dateBorrowed;
	private Student student;
	private List<BorrowedBook> borrowedBooks;
	
	public Borrow() {
		borrowedBooks = new ArrayList<>();
	}
	
	public Borrow(String transactionId, Date dateBorowed, Student student) {
		this();
		this.setStudent(student);
		this.transactionId = transactionId;
		this.dateBorrowed = dateBorowed;
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
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<BorrowedBook> getBorrowedBooks() {
		return borrowedBooks;
	}
	public void setBorrowedBooks(List<BorrowedBook> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
//		for(BorrowedBook b : this.borrowedBooks) {
//			b.setBorrow(this);
//		}
	}
	public void addBorrowedBook(BorrowedBook borrowedBook) {
		Book book = borrowedBook.getBook();
		if (book != null) {
			int quantity = borrowedBook.getQuantity();
			int remaining = book.getRemainingCopies();
			int diff = remaining - quantity;
			if (diff > 0) {
				// Positive number, allow borrow, update new number of copies, add borrowedBook to list.
				System.out.println(borrowedBook);
				borrowedBooks.add(borrowedBook);
			}
			else {
				borrowedBook.setBook(null);
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		// TODO Auto-generated method stub
		if (this != null) {
			sb.append("Borrow: [transactionId: " + this.transactionId + ", dateBorrowed: " + this.dateBorrowed + ", student: " + this.student.getId());
			
			for(BorrowedBook bb : borrowedBooks) {
				sb.append(bb.toString());
			}
		}

		return sb.toString();
	}
}

package com.librarysystem.dto;

import java.util.ArrayList;
import java.util.List;

public class Book {

	private Long id;
	private String title;
	private String author;
	private String bookCode;
	private int numberOfCopies;
	private List<BorrowedBook> borrowedBooks;
	
	public Book() {
		this.borrowedBooks = new ArrayList<>();
	}
	
	public Book(String bookCode, String title, String author, int numberOfCopies) {
		this();
		this.title = title;
		this.bookCode = bookCode;
		this.author = author;
		this.numberOfCopies = numberOfCopies;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getNumberOfCopies() {
		return numberOfCopies;
	}
	public void setNumberOfCopies(int numberOfCopies) {
		if (numberOfCopies >= getTotalNumberOfCopiesBorrowed()) {
			this.numberOfCopies = numberOfCopies;
		}
	}
//	public void setNumberOfCopies(int numberOfCopies) {
//		this.numberOfCopies = numberOfCopies;
//	}
	public List<BorrowedBook> getBorrowedBooks() {
		return borrowedBooks;
	}
	public void setBorrowedBooks(List<BorrowedBook> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}
	public int getTotalNumberOfCopiesBorrowed() {
		int total = 0;
		for (BorrowedBook b : borrowedBooks) {
			total += b.getQuantity();
		}
		return total;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public int getRemainingCopies() {
		return this.numberOfCopies - getTotalNumberOfCopiesBorrowed();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("Book: [ title: " + this.title + ", bookCode: " + this.bookCode + ", author: " + this.author + ", numberOfCopies: " + this.numberOfCopies + "]");
		for(BorrowedBook bb : borrowedBooks) {
			sb.append(bb.toString());
		}
		return sb.toString();
	}
}

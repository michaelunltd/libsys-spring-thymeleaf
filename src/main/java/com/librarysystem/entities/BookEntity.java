package com.librarysystem.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class BookEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "book_code", unique=true)
	private String bookCode;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "number_of_copies")
	private int numberOfCopies;
	
	@OneToMany(mappedBy = "book", fetch=FetchType.LAZY)
	private List<BorrowedBookEntity> borrowedBooks;
	
	public BookEntity() {};
	
	public BookEntity(String bookCode, String title, String author, int numberOfCopies) {
		this.bookCode = bookCode;
		this.title = title;
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
		this.numberOfCopies = numberOfCopies;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public List<BorrowedBookEntity> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(List<BorrowedBookEntity> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}
	
	
}

package com.librarysystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "borrowed_book")
public class BorrowedBookEntity {

	public BorrowedBookEntity() {}
	
	public BorrowedBookEntity(BorrowEntity borrow, BookEntity book, int quantity) {
		setBorrow(borrow);
		setBook(book);
		this.quantity = quantity;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "book_id")
	private BookEntity book;
	
	@Column(name = "quantity")
	private int quantity;
	
	@ManyToOne()
	@JoinColumn(name = "borrow_id")
	private BorrowEntity borrow;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BookEntity getBook() {
		return book;
	}

	public void setBook(BookEntity book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BorrowEntity getBorrow() {
		return borrow;
	}

	public void setBorrow(BorrowEntity borrow) {
		this.borrow = borrow;
	}
	
}

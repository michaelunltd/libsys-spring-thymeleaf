package com.librarysystem.dto;

public class BorrowedBook {

	private Long id;
	private Book book;
	private int quantity;
	private Borrow borrow;
	
	public BorrowedBook() {}
	
	public BorrowedBook(Borrow borrow, Book book, int quantity) {
		this.quantity = quantity;
		setBorrow(borrow);
		setBook(book);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		if (book.getRemainingCopies() >= this.quantity) {
			this.book = book;
		}
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Borrow getBorrow() {
		return borrow;
	}
	public void setBorrow(Borrow borrow) {
		this.borrow = borrow;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("BorrowedBook: [book: " + this.book.getId() + ", borrow: " + this.borrow.getId() + ", qty: " + this.quantity + "]");
		// TODO Auto-generated method stub
		return sb.toString();
	}
}

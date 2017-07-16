package com.librarysystem.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.librarysystem.dto.Book;
import com.librarysystem.entities.BookEntity;
import com.librarysystem.dto.BorrowedBook;

public class BookMapper {

	public static Book toDTO(BookEntity bookEntity) {
		if (bookEntity == null) {
			return null;
		}
		Book book = new Book();
		book.setId(bookEntity.getId());
		book.setTitle(bookEntity.getTitle());
		book.setAuthor(bookEntity.getAuthor());
		book.setBookCode(bookEntity.getBookCode());
		book.setNumberOfCopies(bookEntity.getNumberOfCopies());
		
		return book;
	}
	
	public static BookEntity toEntity(Book book) {
		if (book == null) {
			return null;
		}
		BookEntity bookEntity = new BookEntity();
		bookEntity.setId(book.getId());
		bookEntity.setAuthor(book.getAuthor());
		bookEntity.setTitle(book.getTitle());
		bookEntity.setBookCode(book.getBookCode());
		bookEntity.setNumberOfCopies(book.getNumberOfCopies());
		
		return bookEntity;
	}
	
	public static Book toDTOWithList(BookEntity bookEntity) {
		if (bookEntity == null) {
			return null;
		}
		Book book = toDTO(bookEntity);
		List<BorrowedBook> borrowedBooks = bookEntity.getBorrowedBooks().stream().map(borrowedBookEntity -> {
			return BorrowedBookMapper.toDTO(borrowedBookEntity);
		}).collect(Collectors.toList());
		book.setBorrowedBooks(borrowedBooks);
		
		return book;
	}
}

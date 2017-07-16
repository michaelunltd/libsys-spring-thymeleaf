package com.librarysystem.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarysystem.repositories.BookRepository;
import com.librarysystem.dto.Book;
import com.librarysystem.entities.BookEntity;
import com.librarysystem.mappers.BookMapper;

@Service
@Transactional
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAll() {
		List<Book> books = bookRepository.findAll().stream().map(bookEntity -> {
			return BookMapper.toDTO(bookEntity);
		}).collect(Collectors.toList());
		
		return books;
	}
	
	public List<Book> findAllWithBorrowedBookList() {
		List<Book> books = bookRepository.findAll().stream().map(bookEntity -> {
			return BookMapper.toDTOWithList(bookEntity);
		}).collect(Collectors.toList());
		
		return books;
	}
	
	public Book findOne(Long id) {
		BookEntity bookEntity = bookRepository.findOne(id);
		return BookMapper.toDTO(bookEntity);
	}
	
	public Book findOneWithList(Long id) {
		BookEntity bookEntity = bookRepository.findOne(id);
		return BookMapper.toDTOWithList(bookEntity);
	}
	
	public Book save(Book book) {
		if (book == null) {
			return null;
		}
		BookEntity savedBookEntity = bookRepository.save(BookMapper.toEntity(book));
		return BookMapper.toDTO(savedBookEntity);
	}
	
	public Book update(Long id, Book book) {
		BookEntity bookEntity = bookRepository.findOne(id);
		bookEntity.setAuthor(book.getAuthor());
		bookEntity.setNumberOfCopies(book.getNumberOfCopies());
		bookEntity.setTitle(book.getTitle());
		bookEntity = bookRepository.save(bookEntity);
		
		return BookMapper.toDTO(bookEntity);
	}
	
	public void delete(Long id) {
		BookEntity bookEntity = bookRepository.findOne(id);
		if (bookEntity != null) {
			bookRepository.delete(bookEntity);
		}
	}
}

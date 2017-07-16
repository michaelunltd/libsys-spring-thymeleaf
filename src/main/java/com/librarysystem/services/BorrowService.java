package com.librarysystem.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarysystem.dto.Book;
import com.librarysystem.dto.Borrow;
import com.librarysystem.dto.BorrowedBook;
import com.librarysystem.entities.BorrowEntity;
import com.librarysystem.mappers.BorrowMapper;
import com.librarysystem.repositories.BorrowRepository;

@Service
@Transactional
public class BorrowService {
	
	@Autowired
	private BorrowRepository borrowRepository;
	
	@Autowired
	private BookService bookService;
	
	public List<Borrow> findAll() {
		List<Borrow> borrows = borrowRepository.findAll().stream().map(borrowEntity -> {
			return BorrowMapper.toDTO(borrowEntity);
		}).collect(Collectors.toList());
		
		return borrows;
	}
	
	public Borrow save(Borrow borrow) {
//		if (borrow.getBorrowedBooks().size() == 0) {
//			return null;
//		}
		borrow.getBorrowedBooks().stream().map(borrowedBook -> {
			Book b1 = borrowedBook.getBook();
			Book b = bookService.findOne(borrowedBook.getBook().getId());
			borrowedBook.setBook(b);
			return borrowedBook;
		}).collect(Collectors.toList());
		
		BorrowEntity savedBorrow = borrowRepository.save(BorrowMapper.toEntityWithList(borrow));
		Borrow b = BorrowMapper.toDTOWithList(savedBorrow);
		return b;
	}
	
	public Borrow findOne(Long id) {
		BorrowEntity borrowEntity = borrowRepository.findOne(id);
		return BorrowMapper.toDTO(borrowEntity);
	}
	
	public Borrow findOneWithList(Long id) {
		BorrowEntity borrowEntity = borrowRepository.findOne(id);
		return BorrowMapper.toDTOWithList(borrowEntity);
	}
	
	public void delete(Long id) {
		borrowRepository.delete(id);
	}
	
	public boolean addBorrowedBook(Borrow borrow, BorrowedBook borrowedBook) {
		Book book = borrowedBook.getBook();
		if (book.getNumberOfCopies() > borrowedBook.getQuantity()) {
			book.setNumberOfCopies(book.getNumberOfCopies() - borrowedBook.getQuantity());
			
			boolean res = borrow.getBorrowedBooks().add(borrowedBook);
			borrowRepository.save(BorrowMapper.toEntity(borrow));
			
			return res; 
		}
		else {
			return false;
		}
	}
}

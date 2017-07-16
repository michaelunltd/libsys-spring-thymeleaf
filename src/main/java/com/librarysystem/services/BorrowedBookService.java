package com.librarysystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarysystem.dto.BorrowedBook;
import com.librarysystem.entities.BorrowedBookEntity;
import com.librarysystem.mappers.BorrowedBookMapper;
import com.librarysystem.repositories.BorrowedBookRepository;

@Service
public class BorrowedBookService {

	@Autowired
	private BorrowedBookRepository borrowedBookRepository;
	
	public BorrowedBook save(BorrowedBook borrowedBook) {
		BorrowedBookEntity savedBorrowedBook = borrowedBookRepository.save(BorrowedBookMapper.toEntity(borrowedBook));
		
		return BorrowedBookMapper.toDTO(savedBorrowedBook);
	}
}

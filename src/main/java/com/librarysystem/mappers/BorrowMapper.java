package com.librarysystem.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.librarysystem.dto.Borrow;
import com.librarysystem.dto.BorrowedBook;
import com.librarysystem.entities.BorrowEntity;
import com.librarysystem.entities.BorrowedBookEntity;

public class BorrowMapper {

	public static Borrow toDTO(BorrowEntity borrowEntity) {
		Borrow borrow = new Borrow();
		borrow.setId(borrowEntity.getId());
		borrow.setDateBorrowed(borrowEntity.getDateBorrowed());
		borrow.setTransactionId(borrowEntity.getTransactionId());
		borrow.setStudent(StudentMapper.toDTO(borrowEntity.getStudent()));
		
		return borrow;
	}
	
	public static BorrowEntity toEntity(Borrow borrow) {
		BorrowEntity borrowEntity = new BorrowEntity();
		borrowEntity.setId(borrow.getId());
		borrowEntity.setDateBorrowed(borrow.getDateBorrowed());
		borrowEntity.setTransactionId(borrow.getTransactionId());
		borrowEntity.setStudent(StudentMapper.toEntity(borrow.getStudent()));
		
		return borrowEntity;
	}
	
	public static Borrow toDTOWithList(BorrowEntity borrowEntity) {
		Borrow borrow = toDTO(borrowEntity);
		List<BorrowedBook> borrowedBooks = borrowEntity.getBorrowedBooks().stream().map(borrowedBookEntity -> {
			return BorrowedBookMapper.toDTO(borrowedBookEntity);
		}).collect(Collectors.toList());
		borrow.setBorrowedBooks(borrowedBooks);
		return borrow;
	}
	
	public static BorrowEntity toEntityWithList(Borrow borrow) {
		BorrowEntity borrowEntity = toEntity(borrow);
		List<BorrowedBookEntity> borrowedBooksEntity = borrow.getBorrowedBooks().stream().map(borrowedBook -> {
			return BorrowedBookMapper.toEntity(borrowedBook);
		}).collect(Collectors.toList());
		borrowEntity.setBorrowedBooks(borrowedBooksEntity);
		
		return borrowEntity;
	}
}

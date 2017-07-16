package com.librarysystem.mappers;

import com.librarysystem.dto.BorrowedBook;
import com.librarysystem.entities.BookEntity;
import com.librarysystem.entities.BorrowedBookEntity;

public class BorrowedBookMapper {

	public static BorrowedBook toDTO(BorrowedBookEntity borrowedBookEntity) {
		BorrowedBook bb = new BorrowedBook();
		BookEntity be = borrowedBookEntity.getBook();
		bb.setBook(BookMapper.toDTO(borrowedBookEntity.getBook()));
		bb.setBorrow(BorrowMapper.toDTO(borrowedBookEntity.getBorrow()));
		bb.setId(borrowedBookEntity.getId());
		bb.setQuantity(borrowedBookEntity.getQuantity());
		
		return bb;
	}
	
	public static BorrowedBookEntity toEntity(BorrowedBook borrowedBook) {
		BorrowedBookEntity bbe = new BorrowedBookEntity();
		bbe.setBook(BookMapper.toEntity(borrowedBook.getBook()));
//		bbe.setBorrow(BorrowMapper.toEntity(borrowedBook.getBorrow()));
		bbe.setId(borrowedBook.getId());
		bbe.setQuantity(borrowedBook.getQuantity());
		
		return bbe;
	}
}

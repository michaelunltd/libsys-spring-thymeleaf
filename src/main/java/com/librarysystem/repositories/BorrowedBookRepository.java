package com.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarysystem.entities.BorrowedBookEntity;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBookEntity, Long>{

}

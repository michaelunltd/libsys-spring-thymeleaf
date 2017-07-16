package com.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarysystem.entities.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long>{

}

package com.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarysystem.entities.BorrowEntity;

public interface BorrowRepository extends JpaRepository<BorrowEntity, Long>{

}

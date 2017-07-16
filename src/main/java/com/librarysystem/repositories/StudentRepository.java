package com.librarysystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarysystem.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{

}

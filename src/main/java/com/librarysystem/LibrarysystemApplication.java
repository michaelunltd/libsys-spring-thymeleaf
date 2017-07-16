package com.librarysystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.hadoop.mapred.gethistory_jsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.librarysystem.dto.Book;
import com.librarysystem.dto.Borrow;
import com.librarysystem.dto.BorrowedBook;
import com.librarysystem.dto.Student;
import com.librarysystem.entities.BookEntity;
import com.librarysystem.entities.BorrowEntity;
import com.librarysystem.entities.BorrowedBookEntity;
import com.librarysystem.entities.StudentEntity;
import com.librarysystem.repositories.BookRepository;
import com.librarysystem.repositories.BorrowRepository;
import com.librarysystem.repositories.StudentRepository;
import com.librarysystem.services.BookService;
import com.librarysystem.services.BorrowService;
import com.librarysystem.services.StudentService;


@SpringBootApplication
public class LibrarysystemApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(LibrarysystemApplication.class);
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BorrowRepository borrowRepository;
	
	@Autowired
	private BorrowService borrowService;
	
	public static void main(String[] args) {
		SpringApplication.run(LibrarysystemApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		// Entity Test
//		StudentEntity studentEntity1 = new StudentEntity("StudentFirst1", "StudentLast1", "201706230001");
//		StudentEntity studentEntity2 = new StudentEntity("StudentFirst2", "StudentLast2", "201706230002");
//		studentRepository.save(Arrays.asList(studentEntity1, studentEntity2));
//		
//		System.out.println(studentEntity1);
//		System.out.println(studentEntity2);
//		
//		BookEntity book1 = new BookEntity("BKC001", "Title1", "Author1", 10);
//		BookEntity book2 = new BookEntity("BKC002", "Title2", "Author2", 12);
//		bookRepository.save(Arrays.asList(book1, book2));
//
//		System.out.println(book1);
//		System.out.println(book2);
//		
//		BorrowEntity borrow1 = new BorrowEntity("TR0001", new Date(), studentEntity1);
//		BorrowedBookEntity bb1 = new BorrowedBookEntity(borrow1, book1, 2);
//		BorrowedBookEntity bb2 = new BorrowedBookEntity(borrow1, book2, 3);
//		borrowRepository.save(borrow1);
//		System.out.println(borrow1);
//		System.out.println(studentEntity1.getBorrows().size());
//		System.out.println(borrow1.getBorrowedBooks().size());
//		System.out.println(book1.getBorrowedBooks().size());
		
		
		//DTO Test and Services
//		Student student1 = new Student("StudentFirst1", "StudentLast1", "201706230001");
//		Student student2 = new Student("StudentFirst2", "StudentLast2", "201706230002");
//		
//		student1 = studentService.save(student1);
//		student2 = studentService.save(student2);
//		
//		System.out.println(student1);
//		System.out.println(student2);
//		
//		Book book1 = new Book("BKC001", "Title1", "Author1", 10);
//		Book book2 = new Book("BKC002", "Title2", "Author2", 12);
//		
//		book1 = bookService.save(book1);
//		book2 = bookService.save(book2);
//		
//		System.out.println(book1);
//		System.out.println(book2);
//		
//		student1 = studentService.findOneWithList(student1.getId());
//		Borrow borrow1 = new Borrow("TR0001", new Date(), student1);
//		BorrowedBook bb1 = new BorrowedBook(borrow1, book1, 2);
//		BorrowedBook bb2 = new BorrowedBook(borrow1, book2, 3);
//		borrow1 = borrowService.save(borrow1);
//		
//		Student studentWithList = studentService.findOneWithList(student1.getId());
//		Book bookWithList = bookService.findOneWithList(book1.getId());
//		System.out.println(borrow1);
//		System.out.println(borrow1.getBorrowedBooks().size());
//		System.out.println(student1.getBorrows().size());
//		System.out.println(book1.getBorrowedBooks().size());
//		System.out.println();
	}
}

package com.librarysystem.test;

import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTest {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BorrowRepository borrowRepository;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BorrowService borrowService;
	
	@Ignore
	@Test
	public void entitiesTest() {	
		StudentEntity studentEntity1 = new StudentEntity("StudentFirst1", "StudentLast1", "201706230001");
		StudentEntity studentEntity2 = new StudentEntity("StudentFirst2", "StudentLast2", "201706230002");
		studentRepository.save(Arrays.asList(studentEntity1, studentEntity2));
		
		System.out.println(studentEntity1);
		System.out.println(studentEntity2);
		
		BookEntity book1 = new BookEntity("BKC001", "Title1", "Author1", 10);
		BookEntity book2 = new BookEntity("BKC002", "Title2", "Author2", 12);
		bookRepository.save(Arrays.asList(book1, book2));

		System.out.println(book1);
		System.out.println(book2);
		
		BorrowEntity borrow1 = new BorrowEntity("TR0001", new Date(), studentEntity1);
		BorrowedBookEntity bb1 = new BorrowedBookEntity(borrow1, book1, 2);
		BorrowedBookEntity bb2 = new BorrowedBookEntity(borrow1, book2, 3);
		borrowRepository.save(borrow1);
		
		System.out.println(borrow1);
		System.out.println(studentEntity1.getBorrows().size());
		System.out.println(borrow1.getBorrowedBooks().size());
		System.out.println(book1.getBorrowedBooks().size());
	}
	
	@Ignore
	@Test
	public void testDto() {
		Student student1 = new Student("StudentFirst1", "StudentLast1", "201706230001");
		Student student2 = new Student("StudentFirst2", "StudentLast2", "201706230002");
		
		student1 = studentService.save(student1);
		student2 = studentService.save(student2);
		
		System.out.println(student1);
		System.out.println(student2);
		
		Book book1 = new Book("BKC001", "Title1", "Author1", 10);
		Book book2 = new Book("BKC002", "Title2", "Author2", 12);
		
		book1 = bookService.save(book1);
		book2 = bookService.save(book2);
		
		System.out.println(book1);
		System.out.println(book2);
		
		student1 = studentService.findOneWithList(student1.getId());
		Borrow borrow1 = new Borrow("TR0001", new Date(), student1);
		BorrowedBook bb1 = new BorrowedBook(borrow1, book1, 2);
		BorrowedBook bb2 = new BorrowedBook(borrow1, book2, 3);
		borrow1 = borrowService.save(borrow1);
		
		Student studentWithList = studentService.findOneWithList(student1.getId());
		Book bookWithList = bookService.findOneWithList(book1.getId());
		System.out.println(borrow1);
		System.out.println(borrow1.getBorrowedBooks().size());
		System.out.println(studentWithList.getBorrows().size());
		System.out.println(student1.getBorrows().size());
		System.out.println(bookWithList.getBorrowedBooks().size());
		System.out.println();
	}
	
	@Ignore
	@Test
	public void testCurrentData() {
		Student student = studentService.findOneWithList(1L);
		Book book = bookService.findOneWithList(1L);
		Borrow borrow = borrowService.findOneWithList(1L);
		Assert.assertEquals(1, student.getBorrows().size());
		Assert.assertEquals(2, borrow.getBorrowedBooks().size());
		Assert.assertEquals(1, book.getBorrowedBooks().size());
	}
	
	@Ignore
	@Test
	public void testNumberOfCopiesSetter() {
		Borrow borrow1 = new Borrow();
		Book book = new Book("BKC1001", "TITLE", "AUTHOR", 10);
		BorrowedBook bb = new BorrowedBook(borrow1, book, 8);
		borrow1.addBorrowedBook(bb);
		book.setNumberOfCopies(7);
		Assert.assertEquals(10, book.getNumberOfCopies());
		Assert.assertEquals(2, book.getRemainingCopies());
	}
	
	@Ignore
	@Test
	public void testNumberOfCopiesSetterAndRemainingCopies() {
		Borrow borrow = new Borrow();
		Book book = new Book("BKC1001", "TITLE", "AUTHOR", 10);
		BorrowedBook bb = new BorrowedBook(borrow, book, 8);
		borrow.addBorrowedBook(bb);
		book.setNumberOfCopies(15);
		Assert.assertEquals(15, book.getNumberOfCopies());
		Assert.assertEquals(7, book.getRemainingCopies());
	}
	
	@Ignore
	@Test
	public void testRemainingCopies() {
		Borrow borrow = new Borrow();
		Book book = new Book("BKC1001", "TITLE", "AUTHOR", 10);
		BorrowedBook bb = new BorrowedBook(borrow, book, 8);
		borrow.addBorrowedBook(bb);
		Assert.assertEquals(2, book.getRemainingCopies());
	}
	
}

package com.librarysystem.test;


import java.util.Date;
import java.util.List;

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
import com.librarysystem.services.BookService;
import com.librarysystem.services.BorrowService;
import com.librarysystem.services.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FunctionalTest {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BorrowService borrowService;
	
	
	
	@Test
	public void createAndUpateStudent() {
		System.out.println("-------------CreateAndUpdateStudent-------------");
		Student student = new Student("FirstName", "LastName", "201706270001");
		student = studentService.save(student);
		
		System.out.println(student.toString());
		Assert.assertNotNull(student.getId());
		
		student = studentService.findOne(student.getId());
		student.setFirstName("Edited");
		student = studentService.save(student);
		
		System.out.println(student.toString());
		Assert.assertEquals("Edited", student.getFirstName());
		System.out.println("--------------EndCreateAndUpdateStudent-----------");
	}
	
	
	@Test
	public void createAndUpdateBook() {
		System.out.println("----------------CreateAndUpdateBook-----------------");
		Book book = new Book("BKC1001", "TITLE", "AUTHOR", 10);
		book = bookService.save(book);
		
		System.out.println(book.toString());
		Assert.assertNotNull(book.getId());
		
		book = bookService.findOne(book.getId());
		book.setAuthor("Edited");
		book = bookService.save(book);
		
		System.out.println(book.toString());
		Assert.assertEquals("Edited", book.getAuthor());
		System.out.println("----------------EndCreateAndUpdateBook---------------");
	}
	
	
	@Test
	public void changeBookNumberOfCopies() {
		System.out.println("---------------ChangeBookNumberOfCopies--------------");
		Student student = studentService.findOne(1L);
		Book book = bookService.findOne(1L);		

		Borrow borrow = new Borrow("TRA011211", new Date(), student);
		BorrowedBook bb = new BorrowedBook(borrow, book, 2);
		borrow.addBorrowedBook(bb);
		borrow = borrowService.save(borrow);
		
		book = bookService.findOneWithList(book.getId());
		
		System.out.println(book.toString());
		Assert.assertEquals(9, book.getTotalNumberOfCopiesBorrowed());
		
		book.setNumberOfCopies(2);
		book = bookService.save(book);
		
		book = bookService.findOneWithList(book.getId());
		
		System.out.println(book.toString());
		Assert.assertEquals(6, book.getRemainingCopies());
		
		book.setNumberOfCopies(20);
		book = bookService.save(book);
		
		book = bookService.findOneWithList(book.getId());
		System.out.println(book.toString());
		Assert.assertEquals(20, book.getNumberOfCopies());
		Assert.assertEquals(9, book.getTotalNumberOfCopiesBorrowed());
		Assert.assertEquals(11, book.getRemainingCopies());
		System.out.println("----------------EndChangeBookNumberOfCopies--------------");
	}
	
	@Test
	public void studentCanBorrowIfBooksLeftIsSufficient() {
		System.out.println("------------------EndStudentCanBorrowIfBooksLeftIsSufficient------------");
		Student student = studentService.findOne(1L);
		Book book1 = bookService.findOne(1L);
		
		book1 = bookService.findOneWithList(book1.getId());
		Borrow borrow1 = new Borrow("TRB001", new Date(), student);
		borrow1.addBorrowedBook(new BorrowedBook(borrow1, book1, 1));
		borrow1 = borrowService.save(borrow1);
		
		System.out.println(book1.toString());
		Assert.assertEquals(1, borrow1.getBorrowedBooks().size());
		System.out.println("------------------EndStudentCanBorrowIfBooksLeftIsSufficient------------");
	}
	
	
	@Test
	public void studentCannotBorrowIfBooksLeftNotSufficient() {
		System.out.println("------------------StudentCannotBorrowIfBooksLeftNotSufficient---------------");
		Student student = studentService.findOne(1L);
		Book book1 = bookService.findOneWithList(1L);
		
		Borrow borrow1 = new Borrow("TRC001", new Date(), student);
		BorrowedBook bb= new BorrowedBook(borrow1, book1, 17);
		borrow1.addBorrowedBook(bb);
		borrow1 = borrowService.save(borrow1);
		
		
		Assert.assertNull(borrow1);
		System.out.println(student);
		Assert.assertEquals(0, student.getBorrows().size());
		System.out.println("------------------EndStudentCannotBorrowIfBooksLeftNotSufficient---------------");
	}
	
	
	@Test
	public void canViewRemainingNumberOfBooks() {
		System.out.println("------------------------CanViewRemainingNumberOfBooks-----------------");
		
		Book book1 = bookService.findOneWithList(1L);
		System.out.println(book1);
		Assert.assertEquals(10, book1.getRemainingCopies());
		System.out.println("------------------------EndCanViewRemainingNumberOfBooks-----------------");
	}
	
	
	@Test
	public void canGetBorrowListOfStudent() {
		Student student = studentService.findOneWithList(1L);
		System.out.println(student.toString());
		Assert.assertEquals(6, student.getBorrows().size());
	}
	
	@Test
	public void canGetBorrowTransactions() {
		List<Borrow> borrows = borrowService.findAll();
		for (Borrow b : borrows) {
			System.out.println(b.toString());
		}
		Assert.assertEquals(6, borrows.size());
	}
}

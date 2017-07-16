package com.librarysystem.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.librarysystem.dto.Book;
import com.librarysystem.dto.Borrow;
import com.librarysystem.dto.BorrowedBook;
import com.librarysystem.dto.Student;
import com.librarysystem.services.BookService;
import com.librarysystem.services.BorrowService;
import com.librarysystem.services.BorrowedBookService;
import com.librarysystem.services.StudentService;

@Controller
public class BorrowController {

	@Autowired
	private BorrowService borrowService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BorrowedBookService borrowedBookService;
	
	@RequestMapping(value="/students/{studentId}/borrows/new")
	public String newBorrow(@PathVariable Long studentId) {
		Student student = studentService.findOne(studentId);
		Borrow borrow = new Borrow("BT" + new Date().getTime(), new Date(), student);
		borrow = borrowService.save(borrow);
		return "redirect:/borrows/" + borrow.getId();
	}
	
	@RequestMapping(value="/borrows/{id}")
	public String showBorrowDetails(@PathVariable Long id, Model model) {
		Borrow borrow = borrowService.findOneWithList(id);
		System.out.println(borrow);
		model.addAttribute("borrow", borrow);
		return "borrows/show";
	}
	
	@RequestMapping(value="/students/{studentId}/borrows/{id}/delete")
	public String deleteBorrow(@PathVariable Long id, @PathVariable Long studentId) {
		borrowService.delete(id);
		return "redirect:/students/" + studentId;
	}
	
	@RequestMapping(value="/borrows/{id}/borrowedBooks/add")
	public String addBorrowedBook(@PathVariable Long id, Model model) {
		Borrow borrow = borrowService.findOne(id);
		BorrowedBook bb = new BorrowedBook();
		bb.setBorrow(borrow);
		model.addAttribute("borrowedBook", bb);
		model.addAttribute("books", bookService.findAll());
		return "borrows/addBorrowedBook";
	}
	
	@RequestMapping(value="/borrowedBooks/save", method=RequestMethod.POST)
	public String savedBorrowedBook(@ModelAttribute("borrowedBook") @Valid BorrowedBook borrowedBook, BindingResult bindingResult) {
		Book book = bookService.findOne(borrowedBook.getBook().getId());
		Borrow borrow = borrowService.findOneWithList(borrowedBook.getBorrow().getId());
		borrowedBook.setBook(book);
		borrowedBook.setBorrow(borrow);
		borrowedBookService.save(borrowedBook);
		return "redirect:/borrows/" + borrowedBook.getBorrow().getId();
	}
	
	@RequestMapping(value="/borrows", method=RequestMethod.GET)
	public String showAll(Model model) {
		model.addAttribute("borrows", borrowService.findAll());
		return "borrows/list";
	}
}

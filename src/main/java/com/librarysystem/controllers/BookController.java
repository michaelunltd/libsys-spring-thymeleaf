package com.librarysystem.controllers;

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
import com.librarysystem.services.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/books")
	public String findAll(Model model) {
		model.addAttribute("books", bookService.findAllWithBorrowedBookList());
		return "books/list";
	}
	
	@RequestMapping(value="/books/new", method=RequestMethod.GET)
	public String create(Book book) {
		return "books/new";
	}
	
	@RequestMapping(value="/books/save", method=RequestMethod.POST)
	public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("book", book);
			return "books/new";
		}
		bookService.save(book);
		return "redirect:/books";
	}
	
	@RequestMapping(value="/books/{id}/edit", method=RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		return "books/edit";
	}
	
	@RequestMapping(value="/books/{id}/update", method=RequestMethod.POST)
	public String update(@PathVariable Long id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("book", book);
			return "books/edit";
		}
		bookService.update(id, book);
		return "redirect:/books/" + book.getId();
	}
	
	@RequestMapping(value="/books/{id}", method=RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		Book book = bookService.findOneWithList(id);
		model.addAttribute("book", book);
		return "books/show";
	}
	
	@RequestMapping("/books/{id}/delete")
	public String delete(@PathVariable Long id) {
		bookService.delete(id);
		return "redirect:/books";
	}
}

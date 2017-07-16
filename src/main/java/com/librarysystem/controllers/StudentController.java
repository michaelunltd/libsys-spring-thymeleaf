package com.librarysystem.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.librarysystem.dto.Borrow;
import com.librarysystem.dto.BorrowedBook;
import com.librarysystem.dto.Student;
import com.librarysystem.services.BookService;
import com.librarysystem.services.BorrowService;
import com.librarysystem.services.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BorrowService borrowService;
		
	@RequestMapping(value="/students", method=RequestMethod.GET)
	public String getAll(Model model) {
		model.addAttribute("students", studentService.findAll());
		return "students/list";
	}
	
	@RequestMapping(value="/students/new", method=RequestMethod.GET)
	public String create(Student student) {
		return "students/new";
	}
	
	@RequestMapping(value="/students/save", method=RequestMethod.POST)
	public String save(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "students/new";
		}
		studentService.save(student);
		return "redirect:/students";
	}
	
	@RequestMapping(value="/students/{id}", method=RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.findOneWithList(id));
		return "students/show";
	}
	
	@RequestMapping("/students/{id}/delete")
	public String delete(@PathVariable Long id) {
		studentService.delete(id);
		return "redirect:/students";
	}
	
	@RequestMapping(value="/students/{id}/edit", method=RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		Student student = studentService.findOne(id);
		model.addAttribute("student", student);
		return "students/edit";
	}
	
	@RequestMapping(value="/students/{id}/update", method=RequestMethod.POST)
	public String update(@PathVariable Long id, @ModelAttribute("student") @Valid Student student, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("student", student);
			return "student/edit";
		}
		studentService.update(id, student);
		return "redirect:/students/" + student.getId();
	}
	
	@RequestMapping(value="/students/{id}/studentBorrows/add")
	public String addBorrowForStudent(@PathVariable Long id, Model model) {
		Student student = studentService.findOne(id);
		Borrow borrow = new Borrow();
		borrow.setStudent(student);
		borrow.setTransactionId("BT" + new Date().getTime());
		model.addAttribute("borrow", borrow);
		model.addAttribute("books", bookService.findAll());
		return "students/addBorrow";
	}
	
	@RequestMapping(value="/students/{studentId}/studentBorrows/save", method=RequestMethod.POST)
	public String saveBorrowForStudent(@PathVariable Long studentId, @ModelAttribute("borrow") Borrow borrow, BindingResult bindingResult, Model model) {
		borrow.setDateBorrowed(new Date());
		borrowService.save(borrow);
		return "redirect:/students/" + studentId;
	}
	
	@RequestMapping(value="/students/{studentId}/studentBorrows/save", params={"addBook"})
	public String addBookForBorrow(@PathVariable Long studentId, @ModelAttribute("borrow") Borrow borrow, BindingResult bindingResult, Model model) {
		Student student = studentService.findOne(borrow.getStudent().getId());
		borrow.setStudent(student);
		borrow.getBorrowedBooks().add(new BorrowedBook());
		model.addAttribute("books", bookService.findAll());
		model.addAttribute("borrow", borrow);
		return "students/addBorrow";
	}
	
	@RequestMapping(value="/students/{studentId}/studentBorrows/save", params={"removeBook"})
	public String removeBookForBorrow(@PathVariable Long studentId, @ModelAttribute("borrow") Borrow borrow, BindingResult bindingResult, Model model, HttpServletRequest req) {
		int rowId = Integer.valueOf(req.getParameter("removeBook"));
		Student student = studentService.findOne(borrow.getStudent().getId());
		borrow.setStudent(student);
		borrow.getBorrowedBooks().remove(rowId);
		int size = borrow.getBorrowedBooks().size();
		List<BorrowedBook> bb = borrow.getBorrowedBooks();
		model.addAttribute("borrow", borrow);
		model.addAttribute("books", bookService.findAll());
		return "students/addBorrow";
	}
}

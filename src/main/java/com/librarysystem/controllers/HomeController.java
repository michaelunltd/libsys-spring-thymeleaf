package com.librarysystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public String home() {
		return "home/index";
	}
	
	@RequestMapping(value="/studs")
	public String students() {
		return "students/list";
	}
}

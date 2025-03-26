package com.vicky.learn.springsecsection1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

	@GetMapping("/errormessage")
	public String errorMessage() {
		return "Error Message";
	}

}

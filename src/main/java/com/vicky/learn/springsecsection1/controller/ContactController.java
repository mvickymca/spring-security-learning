package com.vicky.learn.springsecsection1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

	@GetMapping("/contact")
	public String welcomeMessage() {
		return "This message is from Contact Controller";

	}

}

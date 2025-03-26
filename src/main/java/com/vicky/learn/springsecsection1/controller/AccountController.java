package com.vicky.learn.springsecsection1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
	@GetMapping("/account")
	public String account() {
		return "This message is from Account Controller";

	}
}

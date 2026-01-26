package com.bolsaideas.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "forward:/app/index"; // you can use redirect. But they have many differences, u can redirect to external pages but no with forward. But with forward u can keep the request parameters
	}
}
 
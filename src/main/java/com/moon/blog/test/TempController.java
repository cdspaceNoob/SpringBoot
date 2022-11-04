package com.moon.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller	// file을 return.
public class TempController {

	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("welcome to tempHome");
		// Controller에 의한 file이 리턴될 때 기본 경로: src/main/resources/static
		return "home.html";
	}//
	
	@GetMapping("/temp/img")
	public String tempImage() {
		return "/springBoot.png";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		return "/temp";
	}
	
}

package com.moon.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController	// com.moon.blog 패키지 이하를 스캔, 특정 어노테이션이 선언된 클래스를 new 생성자로 스프링 컨테이너에 넣어서 관리해준다.(IoC)
public class TestController {

	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello Spring Boot</h1>";
	}
	
}

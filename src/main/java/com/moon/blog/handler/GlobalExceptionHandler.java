package com.moon.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice	// 어디에서든 예외가 발생했을 때 여기로 들어오게 하려면 넣어줘야 한다. 
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=IllegalArgumentException.class)	// value에는 어떤 예외를 받을 것인지 명시한다.
	public String HandleArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";		// getMessage()는 Illegal...Exception 객체를 리턴할 때 넣는 메세지를 가져온다. 
	}
	
}

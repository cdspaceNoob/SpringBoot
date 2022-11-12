package com.moon.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moon.blog.dto.ResponseDto;
import com.moon.blog.model.User;

@RestController
public class UserApiController {

	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user)  {
		System.out.println("UserApiController: save() 메서드 호출.");
		// 실제로 DB에 insert하고 return하는 코드 작성.
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
}

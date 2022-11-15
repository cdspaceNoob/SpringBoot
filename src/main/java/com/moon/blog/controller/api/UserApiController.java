package com.moon.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moon.blog.dto.ResponseDto;
import com.moon.blog.model.RoleType;
import com.moon.blog.model.User;
import com.moon.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired	// UserService는 @Service 어노테이션을 달고 있으므로.
	private UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user)  {
		System.out.println("UserApiController: save() 메서드 호출.");
		// 실제로 DB에 insert하고 return하는 코드 작성.
		user.setRole(RoleType.USER);
//		int result = userService.회원가입(user);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);		// JavaOBJ를 JSON으로 변환하여 return.(MessageConverter - Jackson)
	}
	
	
}

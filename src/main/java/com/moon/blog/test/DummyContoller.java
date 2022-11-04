package com.moon.blog.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moon.blog.model.User;

@RestController
public class DummyContoller {

	@PostMapping("/dummy/join")
//	public String join(String username, String password, String email) {	
	public String join(User user) {		// @ModelAttribute? 객체도 자동으로 받아준다. @Bean으로 등록이 안 됐는데 어떻게? 
			System.out.println("id: " + user.getId());
			System.out.println("username: " + user.getUsername());
			System.out.println("password: " + user.getPassword());
			System.out.println("email: " + user.getEmail());
			System.out.println("role: " + user.getRole());
			System.out.println("createDate: " + user.getCreateDate());
		return "회원가입이 완료되었습니다.";
	}
	
}

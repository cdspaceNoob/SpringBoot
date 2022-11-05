package com.moon.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moon.blog.model.RoleType;
import com.moon.blog.model.User;
import com.moon.blog.repository.UserRepository;

@RestController
public class DummyContoller {
	
	@Autowired	// DummyController가 사용될 때, 아래의 객체도 함께 생성되어 사용된다. (의존성 주입)
	private UserRepository userRepository;

	@PostMapping("/dummy/join")
//	public String join(String username, String password, String email) {	
	public String join(User user) {		// @ModelAttribute? 객체도 자동으로 받아준다. @Bean으로 등록이 안 됐는데 어떻게? 
			System.out.println("id: " + user.getId());
			System.out.println("username: " + user.getUsername());
			System.out.println("password: " + user.getPassword());
			System.out.println("email: " + user.getEmail());
			System.out.println("role: " + user.getRole());
			System.out.println("createDate: " + user.getCreateDate());
			
			user.setRole(RoleType.USER);
			userRepository.save(user);		// 이렇게만 해주면 user 객체를 user 테이블에 알아서 매핑하고 저장해준다.(insert)
		return "회원가입이 완료되었습니다.";
	}
	
}

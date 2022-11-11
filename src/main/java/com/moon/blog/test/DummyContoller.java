package com.moon.blog.test;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moon.blog.model.RoleType;
import com.moon.blog.model.User;
import com.moon.blog.repository.UserRepository;

@RestController
public class DummyContoller {
	
	@Autowired	// DummyController가 사용될 때, 아래의 객체도 함께 생성되어 사용된다. (의존성 주입)
	private UserRepository userRepository;
	
	// detail 함수와 url은 동일하지만 요청 방식이 다르므로 알아서 구별한다.(REST)
	// email, password를 수정할 수 있도록 구현.
	
	@Transactional
	@PutMapping("/dummy/user/{id}")
//	public User updateUser(@PathVariable int id, User requestUser) {	// 이렇게 받으면 form 태그의 summit으로 들어오는 데이터만 받을 수 있다.
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {	// JSON 데이터를 받으려면 @RequestBody를 써줘야 한다.	
		// JSON 데이터로 요청이 들어오면 MessageConverter의 Jackson lib이 사용되어 Java Obj로 변환되어 들어온다. 
		System.out.println("id: " + id);
		System.out.println("password: " + requestUser.getPassword());
		System.out.println("email: " + requestUser.getEmail());
		
//		requestUser.setId(id);
//		requestUser.setUsername("edited");
//		userRepository.save(requestUser);
		
		// save()로 update할 때는 다음과 같이 코드를 짠다.
		// 일단 findById()로 찾아라 그런데 없으면 orElseThrow() 실행해라.
		// 람다 사용한다.
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했습니다.");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
//		userRepository.save(user);
		return null;
	}
	
	// http://localhost:8000/blog/dummy/user/
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한 페이지에 2개의 데이터 받아보기.
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
//		Page<User> users = userRepository.findAll(pageable);
//		List<User> users = userRepository.findAll(pageable).getContent();
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		if(pagingUser.isFirst()) {
			// 이런 부가적인 분기 처리를 위해서 Page 객체로 받고, 원하는 내용 처리를 실행한 후 return 해주면 짜임이 좋다.
		}
		List<User> users = pagingUser.getContent();
		return users;
	}

	// {id} 주소로 파라미터를 전달받을 수 있다.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// 만약 user/4 를 요청했는데 데이터베이스에서 못 찾아오게 되면 user가 null이 된다.
		// 그러면 return 할 때 null이 return 되는데?
		// 그러면 프로그램에 문제가 생긴다!
		// 그러므로, Optional로 이 User 객체를 감싸서 가져다 줄 테니 null 여부를 판단하여 return하라.
		// .get()은 null이든 뭐든 일단 무조건 뽑아달라는 것이다.
//		User user = userRepository.findById(id).get();
		// .orElseGet()은 객체 하나 만들어서 user에 넣어주란 것이다. 들어갈 수 있는 파라미터는 Supplier 타입이다. 제너릭은 ? 
		// 정상적으로 파라미터가 들어오면 정상적으로 생성되고, 이상한 파라미터가 들어오면 객체를 생성하여 넣어준다.
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//			@Override
//			public User get() {
//				return new User();	// 빈 객체를 하나 return해준다. 
//			}
//		});
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {	// Illegal은 말 그대로 잘못된 파라미터가 들어왔을 때를 의미한다.
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저 "+id+"는 존재하지 않습니다.");		// mesage를 담을 수 있다.
			}
		});
		
		// 람다로 표현할 수도 있다.
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 유저 "+id+"는 존재하지 않습니다.");		
//		}); 
		
		// 요청은 웹 브라우저가 했는데 객체를 던져주면 이상해진다.
		// 따라서 이 객체를 웹 브라우저가 이해할 수 있는 데이터로 변환하는데 이 형식이 Json임.
		// SpringBoot에는 MessageConverter라는 애가 응답 시에 자동으로 작동한다.
		// 만약에 Java Obj를 return하면 MessageConverter가 Jackson을 호출하여 자동으로 변환하여 던져준다. 
		return user;
	}
	
	@PostMapping("/dummy/join")
//	public String join(String username, String password, String email) {	
	public String join(User user) {		// @ModelAttribute? 객체도 자동으로 받아준다. @Bean으로 등록이 안 됐는데 어떻게? 
			System.out.println("id: " + user.getId());
			System.out.println("username: " + user.getUsername());
			System.out.println("password: " + user.getPassword());
			System.out.println("email: " + user.getEmail());
			System.out.println("role: " + user.getRole());
			System.out.println("createDate: " + user.getCreateDate());
			
			user.setRole(RoleType.USER);		// 오타 내면 안 되니까 정해진 값을 쓰도록 한다. 
			userRepository.save(user);			// 이렇게만 해주면 user 객체를 user 테이블에 알아서 매핑하고 저장해준다.(insert)
		return "회원가입이 완료되었습니다.";
	}
	
}

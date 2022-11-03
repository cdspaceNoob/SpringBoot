package com.moon.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController	// com.moon.blog 패키지 이하를 스캔, 특정 어노테이션이 선언된 클래스를 new 생성자로 스프링 컨테이너에 넣어서 관리해준다.(IoC)
public class TestController {

	
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello Spring Boot</h1>";
	}//
	
//	http:localhost:8080/http/get
	@GetMapping("/http/get")
//	public String getTest(@RequestParam int id, @RequestParam String username) {	// 쿼리스트링을 통해 날아오는 정보를 받기 위해서 쓴다. id=? 이걸 받을 것이다. 
	public String getTest(Member member)  {	// 이걸 한꺼번에 받으려면? @ModelAttribute로 받는다. 이 어노테이션은 생략 가능하다. 없는 건 null로 처리된다. 
		return "get 요청: " + member.getId() + ", " + member.getUsername() + ", " + member.getEmail();
	}//
	
	
	@PostMapping("/http/post")
//	public String postTest(Member member) {
//	public String postTest(@RequestBody String text) {				// raw로 POST 요청이 들어온다면 @RequestBody를 적어준다. 
	public String PostTest(@RequestBody Member member) { 	// JSON 객체의 Key와 Value를 파싱하여 자동으로 VO 필드에 넣어준다. 
		return "post 요청: " + member.getId() + ", " + member.getUsername() + ", " + member.getEmail();
//		return "post 요청: " + text;
	}//
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member member) {
		return "put 요청" + member.getId() + ", " + member.getUsername() + ", " + member.getPassword()+ ", " + member.getEmail();
	}//
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}//
}

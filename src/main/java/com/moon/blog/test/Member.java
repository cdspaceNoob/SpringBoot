package com.moon.blog.test;

//import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;

//@Getter										// getter 구성.
//@Setter										// setter 구성.
//@RequiredArgsConstructor			// final이 붙은 필드에 대해서 생성자 구성.
//@AllArgsConstructor					//	모든 필드에 대해서 생성자 구성.

@Data										// getter, setter 구성.
@NoArgsConstructor					// 기본 생성자 구성. 이 때 final이 붙어서는 안 된다.
public class Member {

	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
	
	// 요즘은 이렇게한다. 데이터의 불변성을 유지하기 위해서.
//	private final int id;
//	private final String username;
//	private final String password;
//	private final String email;
	
}

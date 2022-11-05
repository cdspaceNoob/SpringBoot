package com.moon.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data							// getter setter
@NoArgsConstructor		// 빈 생성자.
@AllArgsConstructor		// 풀 생성자. 
@Builder						// 선택적 생성자.
//@DynamicInsert				// null으로 잡히는 칼럼은 insert 쿼리에서 제외. Default값 자동 입력을 위해서 선언.
@Entity
public class User {
	
	@Id																					// PK로 설정.
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 프로젝트에 연결된 DB 넘버링 전략을 따라간다.
	private int id;													// Orace: sequence로 처리, MySQL: auto_increment로 처리.
	
	@Column(nullable = false, length = 30)			// null이 될 수 없음을 의미, 최대 30글자를 넘을 수 없다. 
	private String username;	
	
	@Column(nullable = false, length = 100)			// 왜 100이나 주는가? 이후에 Hash로 변환하여 암호화하기 위해서다. 따라서 넉넉하게 100 준다. 
	private String password;
	
	@Column(nullable = false, length = 50)			
	private String email;
	
//	@ColumnDefault(" 'user' ")								// ColumnDefault를 사용할 때는 따옴표 사용에 주의한다. default value를 설정한다.  
	@Enumerated(EnumType.STRING)					// DB에는 RoleType이라는 이름의 ENUM이 설정될 수 없다. 따라서 얘가 ENUM이라는 것을 알려줘야 하고, 어떤 자료 타입인지도 명시해준다.
	private RoleType role;										// 정확하게 하자면 Enum을 써야 한다. 데이터의 도메인을 만들어 줄 수 있다. admin, user, manager 등...
																		// String을 쓰면 오타가 날 수 있기 때문에, 가능하다면 Enum을 사용해준다. 
	
	@CreationTimestamp										// 이 값들이 insert 되는 시점의 시간이 자동으로 입력된다. (insert문에서 비워두면 된다)
	private Timestamp createDate;
}

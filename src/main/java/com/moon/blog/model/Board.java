package com.moon.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data							// getter setter
@NoArgsConstructor		// 빈 생성자.
@AllArgsConstructor		// 풀 생성자. 
@Builder						// 선택적 생성자.
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob								// 매우 큰 대용량 데이터로 잡아줄 때 선언하는 어노테이션.
	private String content;	// 라이브러리를 사용하면 예를 들어 에디터, <html> 태그가 섞이기 때문에 매우매우 길어질 수 있다.
	
	@ColumnDefault("0")		// int이기 때문에 "" 따옴표만 써준다. 
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER)		// board를 select 했을 때, 얘는 일단 무조건 함께 가져온다.(EAGER)
	@JoinColumn(name = "userId")
	private User user;			// Database에서는 OBJ를 저장할 수 없으므로 FK를 지정하여 잇는다. 하지만 Java는 OBJ를 저장할 수 있다.
										// 따라서 OBJ에 대해 충돌이 일어날 수 있는데(Java는 OBJ를 던지고 DB는 OBJ를 안 받으려하고), ORM을 사용하여 오브젝트를 저장한다.
	
	// mappedBy를 가지고 있으면 연관관계의 주인이 아니라는 뜻이다. 이 필드는 칼럼으로 만들지 않겠다는 선언이다. 
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)	 
	private List<Reply> reply;	// 하나의 게시글에 여러 개의 답글이 있으므로 리스트로 받아야 한다.
											// 가져와야 할 것이 매우 많을 수 있으므로, 필요하면 가져오고 그렇지 않으면 가져오지 않겠다.(LAZY)
											// JoinColumn(name = "칼럼 이름")이 필요하지 않다. 
											// 이게 칼럼으로 들어가면 안 된다. 하나의 칼럼은 하나의 값만 가져야 하기 때문이다. 
											// 단지 JPA가 자동으로 JOIN 쿼리를 실행하기 위한 하나의 단서로서 존재하는 필드다. 
	
	@CreationTimestamp
	private Timestamp createDate;
}

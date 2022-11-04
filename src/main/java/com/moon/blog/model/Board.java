package com.moon.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

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
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;			// Database에서는 OBJ를 저장할 수 없으므로 FK를 지정하여 잇는다. 하지만 Java는 OBJ를 저장할 수 있다.
										// 따라서 OBJ에 대해 충돌이 일어날 수 있는데(Java는 OBJ를 던지고 DB는 OBJ를 안 받으려하고), ORM을 사용하여 오브젝트를 저장한다.
	
	@CreationTimestamp
	private Timestamp createDate;
}

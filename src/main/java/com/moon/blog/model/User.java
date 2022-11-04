package com.moon.blog.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentRendererTokenTypes;
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 프로젝트에 연결된 DB 넘버링 전략을 따라간다.
	private int id;						// Orace: sequence로 처리, MySQL: auto_increment로 처리.
	
	private String username;	// id.
	
	private String password;
	
	private String email;
	
	private Timestamp createDate;
}

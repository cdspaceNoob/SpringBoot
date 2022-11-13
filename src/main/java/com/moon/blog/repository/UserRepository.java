package com.moon.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moon.blog.model.User;

// DAO와 동일한 역할을 한다. 
// 자동으로 Bean으로 등록된다.
// @Repository 어노테이션을 생략할 수 있다. 
public interface UserRepository extends JpaRepository<User, Integer>{	// 모델 이름(테이블 이름이 된다), PK의 타입을 넣어준다. 
		// JpaRepository를 상속하여 이미 만들어져 있는 기본적인 CRUD 작업을 할 수 있게 된다.  
}

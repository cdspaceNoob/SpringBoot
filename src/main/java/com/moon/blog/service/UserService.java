package com.moon.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moon.blog.model.User;
import com.moon.blog.repository.UserRepository;

@Service		// component scan을 통해 bean에 등록. IoC 수행.(개발자 대신 메모리에 올려준다)
public class UserService {

		@Autowired
		private UserRepository userRepository;
		
		@Transactional	// 아래의 로직들을 하나의 트랜잭션으로 묶어준다. 
		public Integer 회원가입(User user) {
			try {
				userRepository.save(user);
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("UserService: 회원가입(): " + e.getMessage());
			}
			return -1;
		}//
		
}

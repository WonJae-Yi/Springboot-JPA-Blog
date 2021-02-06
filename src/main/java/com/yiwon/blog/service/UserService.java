package com.yiwon.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yiwon.blog.model.Users;
import com.yiwon.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. ioc를 해준다.
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void joinMember(Users user) {
		userRepository.save(user);
		
	}

	@Transactional(readOnly = true)  //Select시 트랜잭션시작, 서비스 종료시에 트랜잭션 종료(정합성)
	public Users login(Users user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
	}

}

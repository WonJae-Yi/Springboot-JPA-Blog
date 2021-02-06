package com.yiwon.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yiwon.blog.model.Users;

//DAO
//자동으로 bean등록이 된다.
//@Repository  //생략가능
public interface UserRepository extends JpaRepository<Users, Integer>{
	
	//JPA Naming 쿼리 전략
	//select * from user where username = ?1 and password = ?2 이런 쿼리가 동작한다.
	Users findByUsernameAndPassword(String username, String password);
	
//  다른 또하나의 방법	
//	@Query(value="select * from user where username = ?1 and password = ?2", nativeQuery = true)
//	Users login(String username, String password);
	

}

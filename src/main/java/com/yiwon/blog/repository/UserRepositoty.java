package com.yiwon.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiwon.blog.model.Users;

//DAO
//자동으로 bean등록이 된다.
//@Repository  //생략가능
public interface UserRepositoty extends JpaRepository<Users, Integer>{

}

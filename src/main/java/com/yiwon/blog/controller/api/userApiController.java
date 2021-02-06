package com.yiwon.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yiwon.blog.dto.ResponseDto;
import com.yiwon.blog.model.RoleType;
import com.yiwon.blog.model.Users;
import com.yiwon.blog.service.UserService;

@RestController
public class userApiController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody Users user) {
		System.out.println("UserApiContrioller:save 호출됨");
		user.setRole(RoleType.USER);
		userService.joinMember(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody Users user, HttpSession session) {
//		System.out.println("UserApiContrioller:login 호출됨");		
//		Users principal = userService.login(user); // (접근주체)
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
	
	
}

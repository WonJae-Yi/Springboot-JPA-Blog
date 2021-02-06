package com.yiwon.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yiwon.blog.model.RoleType;
import com.yiwon.blog.model.Users;
import com.yiwon.blog.repository.UserRepository;

@Transactional
@RestController
public class DynamicController {
	
	@Autowired  //의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);			
		} catch(EmptyResultDataAccessException e) {
			return "존재하지 않는 id입니다";
		}
		
		return "삭제 되었습니다";
	}
	
	//update
	@Transactional  //메소드 종료시 commit;
	@PutMapping("/dummy/update/{id}")
	public Users updateUser(@PathVariable int id, @RequestBody Users requestUser) {
		
		Users user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//dirty checking
		return user;
	}
	
	//한페이지당 2건의 데이타를 받아봄
	@GetMapping("/dummy/user2")
	public List<Users>pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC)Pageable pageable ){
		Page<Users>pagingUser = userRepository.findAll(pageable);
		
		List<Users>users = pagingUser.getContent();
		return users;
	}
	
	@GetMapping("/dummy/user")
	public List<Users> list(){
		
		return userRepository.findAll();  //모든 데이타 다 가져옴
	}
	
	//{id}주소로 파라메타를 전달받을 수 있음.
	//http://localhost:8000/blog/dummy/user/5
	@GetMapping("/dummy/user/{id}")
	public Users detail(@PathVariable int id) {
		
		Users user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다.");
			}
		});
		return user;
	};
	
	//localhost:8000/blog/dummy/join 요청
	//http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	//public String join(String username, String password, String email) { // key=value (약속된 규칙)
	public String join(Users user) { // object로도 받아진다.
		System.out.println("id:" + user.getId());
		System.out.println("username:" + user.getUsername());
		System.out.println("password:" + user.getPassword());
		System.out.println("email:" + user.getEmail());
		System.out.println("role:" + user.getRole());
		
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}

}

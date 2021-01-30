package com.yiwon.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//ORM : JAVA 에서 OBJECT -> TABLE 만들어주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity  //User 클래스가 ORACLE에 테이블에 생성된다.
//@DynamicInsert  //insert시 null값 컬럼은 제외한다.
public class Users {
	
	@Id  //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)  //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;
	
	@Column(nullable = false, length = 30)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
		
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)  //DB에는 Enum type가 없어서 String이라는 것을 알려준다.
	private RoleType role;

	@CreationTimestamp  //시간이 자동으로 입력
	private Timestamp createDate;

}

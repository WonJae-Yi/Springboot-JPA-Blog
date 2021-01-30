package com.yiwon.blog.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class Reply {
	
	@Id  //Primary key
	private int id;
		
	private String content;
	
	@ManyToOne	
	@JoinColumn(name="boardid")
	private Board board;
	
	@ManyToOne	
	@JoinColumn(name="userid")
	private Users users;
	
	@CreationTimestamp  //시간이 자동으로 입력
	private Timestamp createDate;

}

package com.yiwon.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
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
public class Board {

	@Id  //Primary key
	private int id;
	
	private String title;
	
	@Lob
	private String content;
	
	@ColumnDefault("0")
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER)	//조인해서 가져온다  LAZY : 가져오지 않는다
	@JoinColumn(name="userid")
	private Users users;
	
	@OneToMany(mappedBy = "board",fetch = FetchType.LAZY) //mappedBy 연관관계의 주인이 아니다.(난 FK가 아니예요) DB에 컬럼을 만들지 마세요
	private List<Reply> reply;
	
	@CreationTimestamp  //시간이 자동으로 입력
	private Timestamp createDate;
	
	
}

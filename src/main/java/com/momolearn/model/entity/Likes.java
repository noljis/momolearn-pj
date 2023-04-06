package com.momolearn.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ApiModel(value="좋아요 정보", description = "게시글에 할당된 좋아요 번호, 게시글, 회원 정보")
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer likeNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "com_no")
	private Board board;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mem_id")
	private Members members;
	
	public Likes(Board board, Members members) {
		
		this.board = board;
		this.members = members;
		
	}
}

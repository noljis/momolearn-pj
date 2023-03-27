package com.momolearn.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer likeNo; //좋아요 번호
	
	@ManyToOne
	@JoinColumn(name = "com_no")
	private Board board; //게시글 번호
	
	@ManyToOne
	@JoinColumn(name = "mem_id")
	private Members members; //회원id
	
	public Likes(Board board, Members members) {
		this.board = board;
		this.members = members;
	}
}

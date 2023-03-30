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
	private Integer likeNo;
	
	@ManyToOne
	@JoinColumn(name = "com_no")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name = "mem_id")
	private Members members;
	
	public Likes(Board board, Members members) {
		this.board = board;
		this.members = members;
	}
}

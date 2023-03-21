package com.momolearn.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MembersDTO {
	private String memId; //회원id
	private String pw; //비밀번호
	private String name; //회원이름
	private String email; //이메일
	private String profile; //프로필이미지
	private String grade; //등급
	private LocalDateTime regdate; //가입일자

}
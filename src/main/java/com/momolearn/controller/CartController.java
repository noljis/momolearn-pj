package com.momolearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.momolearn.model.service.CartService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	//페이지 이동
	@PostMapping("/payment")
	public String handlePayment(@RequestParam String pay, @RequestParam String cart) {
	    if (pay != null) {
	        // 결제하기 버튼 클릭 시 실행할 로직
	    }
	    if (cart != null) {
	        // 장바구니 버튼 클릭 시 실행할 로직
	    }
	    return "";
	}
	
	//1. 장바구니
	/* 1. 필요한 속성
	 * 2. 동일 과목을 담으면 이미 담겨있다는 알람창
	 * */
	
	//2. 결제
	
	//3. 결제 후 MyLectures에 데이터 추가

}

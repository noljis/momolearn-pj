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

}

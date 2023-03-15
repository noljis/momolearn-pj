package com.momolearn.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momolearn.model.CartDAO;

@Service
public class CartService {
	
	@Autowired
	private CartDAO cartDAO;
	

}

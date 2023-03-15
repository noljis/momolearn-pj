package com.momolearn.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Cart;

@Repository
public interface CartDAO extends JpaRepository<Cart, String>{

}

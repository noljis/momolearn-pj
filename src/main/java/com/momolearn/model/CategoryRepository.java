package com.momolearn.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	//카테고리명으로 조회
	Optional<Category> findByCateName(String cate);
}

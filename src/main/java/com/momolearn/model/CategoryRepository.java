package com.momolearn.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
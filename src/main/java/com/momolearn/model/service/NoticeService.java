package com.momolearn.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momolearn.model.NoticeDAO;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeDAO noticeDAO;

}

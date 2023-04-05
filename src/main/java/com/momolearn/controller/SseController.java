package com.momolearn.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.momolearn.model.dto.MembersDTO;

import lombok.RequiredArgsConstructor;

@SessionAttributes({"members"})
@RestController
@RequiredArgsConstructor
public class SseController {
	
	public static Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();
	
	@CrossOrigin
	@GetMapping(value = "/sub", consumes = MediaType.ALL_VALUE)
	public SseEmitter subscribe(@ModelAttribute MembersDTO members, @RequestParam String userId) {
		
		SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
		try {
			sseEmitter.send(SseEmitter.event().name("connect"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		sseEmitters.put(userId, sseEmitter);
		sseEmitter.onCompletion(()->sseEmitters.remove(userId));
		sseEmitter.onTimeout(()->sseEmitters.remove(userId));
		sseEmitter.onError((e)->sseEmitters.remove(userId));
		
		return sseEmitter;
	}
	
	
	
	@ExceptionHandler
	public String exHandler(IOException e, Model model) {
		e.printStackTrace();
		model.addAttribute("errorMsg", e.getMessage());
		return "error";
	}
}

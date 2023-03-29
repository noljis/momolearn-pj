package com.momolearn.model.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.CartRepository;
import com.momolearn.model.LecturesRepository;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.dto.CartDTO;
import com.momolearn.model.entity.Cart;
import com.momolearn.model.entity.Lectures;
import com.momolearn.model.entity.Members;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
	
	private final CartRepository cartRepository;

	private final LecturesRepository lecturesRepository;
	
	private final MembersRepository membersRepository;
	
	private ModelMapper mapper = new ModelMapper();

	//수강바구니 검증 메소드
	public boolean checkCart(int lecId, String memId) {
		
		List<Cart> cart = cartRepository.findByLectureId(lecId);
		
		for(int i = 0; i < cart.size(); i++) {
			//List에 세션id 값이 존재하면 true 반환
			if(cart.get(i).getMember().getMemId().equals(memId)) {
				
				return true;
			}
			
		}
		return false;
	}
	
	//수강바구니에 담기
	public CartDTO addCart(int lecId, String memId) throws NotExistException {
		
		Members member = membersRepository.findById(memId).orElseThrow(() -> new NotExistException("존재하는 회원이 없습니다."));
		
		Lectures lecture = lecturesRepository.findById(lecId);

		Cart cart = cartRepository.save(new Cart(member, lecture));
		
		return mapper.map(cart, CartDTO.class);
	}

	//수강바구니 조회
	public List<CartDTO> getCart(String memId) {
		
		List<Cart> cart = cartRepository.findByMemberMemId(memId);
		
		return Arrays.asList(mapper.map(cart, CartDTO[].class));
	}
	

}

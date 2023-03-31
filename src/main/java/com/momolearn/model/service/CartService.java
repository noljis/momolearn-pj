package com.momolearn.model.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momolearn.exception.NotExistException;
import com.momolearn.model.CartRepository;
import com.momolearn.model.LecturesRepository;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.MyLecturesRepository;
import com.momolearn.model.dto.CartDTO;
import com.momolearn.model.dto.PaymentRequestDTO;
import com.momolearn.model.entity.Cart;
import com.momolearn.model.entity.Lectures;
import com.momolearn.model.entity.Members;
import com.momolearn.model.entity.MyLectures;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
	
	private final CartRepository cartRepository;

	private final LecturesRepository lecturesRepository;
	
	private final MembersRepository membersRepository;
	
	private final MyLecturesRepository myLecturesRepository;
	
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
	@Transactional
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

	//강의 저장 + 수강바구니 삭제 + 해당 강의 update
	@Transactional
	public void getMyLectures(PaymentRequestDTO request) throws NotExistException {
		
		for(int i = 0; i < request.getCheckedTitles().size(); i++) {
			
			Lectures lecture = lecturesRepository.findByTitleContaining(request.getCheckedTitles().get(i)).get(0);
			
			Members member = membersRepository.findById(request.getMemId()).orElseThrow(() -> new NotExistException("존재하는 회원이 없습니다."));
			
			//수강생 수 +1
			lecture.setApplyCnt(lecture.getApplyCnt() + 1);
			lecturesRepository.save(lecture);
			
			myLecturesRepository.save(new MyLectures(member, lecture));
			int result = cartRepository.deleteByMemberAndLecture(member, lecture);
			
			System.out.println(result);
			if(result == 0) {
				
				throw new NotExistException("수강신청에 실패했습니다. 관리자에게 문의하십시오.");
				
			}
			
		}
		
	}

}

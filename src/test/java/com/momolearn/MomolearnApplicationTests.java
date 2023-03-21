package com.momolearn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.momolearn.model.MembersRepository;
import com.momolearn.model.entity.Members;
import com.momolearn.model.service.MembersService;

@SpringBootTest
class MomolearnApplicationTests {
	@Autowired MembersRepository membersRepository;
	@Autowired MembersService membersService;

//	@Test
//	void joinTest() {
//		Members members = new Members();
////		members.setmemId("test1");
//		
//		String id = MembersService.join(members);
//		
//		Members findMem = membersRepository.findById(id).get();
//		assertThat(findMem.getMemId()).isEqualTo(members.getMemId());
//		
//	}
//	@Test
//	void TestException() {
//		Members members = new Members();
////		members.setmemId("test1");
//		
//		Members members1 = new Members();
////		members.setmemId("test1");
//		
//		MembersService.join(members);
//		IllegalStateException e = assertThrows(IllegalStateException.class, 
//				()->MembersService.join(members1));
//		
//		assertThat(e.getMessage()).isEqualTo("이미존재하는 회원입니다.");
//		
//	}

}

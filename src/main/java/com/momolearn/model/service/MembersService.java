package com.momolearn.model.service;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momolearn.exception.MessageException;
import com.momolearn.exception.NotExistException;
import com.momolearn.model.MembersRepository;
import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.entity.Members;

@Service
public class MembersService {
	
	@Autowired 
	private MembersRepository membersRepository;

	private ModelMapper mapper = new ModelMapper();

    @Transactional
    public MembersDTO memJoin(MembersDTO members) throws SQLException, MessageException {
    	
		try {
			
			Members mem = mapper.map(members, Members.class);
			Optional<Members> memId = membersRepository.findById(mem.getMemId());
			
			if(memId.isPresent()) {
				
				throw new MessageException("이미 존재하는 아이디입니다.");
				
			}else {
				
				membersRepository.save(mem);
				
				return mapper.map(mem, MembersDTO.class);
			}
	        
		 } catch (Exception e) {
			 
			e.printStackTrace();
			throw new SQLException("Failed to join member.");
		}
		
    }
    
    public boolean checkId(String memId) throws Exception {

		List<Members> members  = membersRepository.findAll();
		
		for(Members member : members) {
			
			if(memId.equals(member.getMemId())) {
				
				return false; 
			}
		}
		
		return true; 
    }
    
    @Transactional
    public MembersDTO loginMember(String memId, String password) throws SQLException {
        
    	try {
    		
        	boolean data = validateUser(memId,password);
        	
        	if(data == true) {
        		
        		Members loginData = membersRepository.findByMemIdAndPw(memId, password);
        		
        		return mapper.map(loginData, MembersDTO.class);
        	}
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
            
            throw new SQLException("로그인 실패: 아이디/비밀번호를 재확인하세요.");
        }
    	
		return null;
    }
	 
	public boolean validateUser(String memId, String password) throws Exception {
	
		Members member = membersRepository.findByMemId(memId);
		
        if (member != null && member.getPw().equals(password)) {
        	
            return true;
        }
        
        return false;
	}
	
	public MembersDTO findId(String email) throws SQLException{
		
		Members member = membersRepository.findByEmail(email);
		
        if (member == null) {
        	
            return null;
        }
        
        return mapper.map(member, MembersDTO.class);
	}
	
	public MembersDTO findPw(String memId, String email) throws SQLException{
		
		Members member = membersRepository.findByMemIdAndEmail(memId, email);
		
        if (member == null) {
        	
        	return null;
        }
        
        return mapper.map(member, MembersDTO.class);
	}
    
    public MembersDTO getMember (String memId) {
    	
    	Members member = membersRepository.findByMemId(memId);
    	
        if (member == null) {
        	
            throw new RuntimeException("해당 회원을 찾을 수 없습니다.");
        }
        
        return mapper.map(member, MembersDTO.class);
	}
    
	public MembersDTO getOneMember(String id) throws NotExistException {

		Members member = membersRepository.findById(id).orElseThrow(() -> new NotExistException("해당 회원을 찾을 수 없습니다."));

		return mapper.map(member, MembersDTO.class);
	}
    
    @Transactional
    public void updateMember (MembersDTO members) throws SQLException {
  	
    try {
    	
		Members mem = mapper.map(members, Members.class); 
		membersRepository.save(mem);
		
    } catch (Exception e) {
  	
	    e.printStackTrace();
	    throw new SQLException("Failed to update member.");
	    }
    }
    
    @Transactional
    public void deleteMember(String memId) throws SQLException {
    	
        try {
        	
            Optional<Members> member = membersRepository.findById(memId);
            
            if (member.isPresent()) { 
            	
            	Members members = member.get();
                membersRepository.delete(members);
                
            } else {
            	
                throw new SQLException("이미 탈퇴처리가 완료된 회원입니다.");
                
            }
            
        } catch (Exception e) {
        	
            e.printStackTrace();
            
            throw new SQLException("Failed to delete member.");
        }
    }
	
	public List<Members> getAllMembers() {
		
		return membersRepository.findAll();
	}

	@Transactional
	public MembersDTO updateGrade(String membersMemId) throws NotExistException{
		
		Members member = membersRepository.findById(membersMemId).orElseThrow(()->new NotExistException("회원정보가 존재하지 않습니다."));
		
		member.setGrade("teacher");
		
		membersRepository.save(member);
		
		return mapper.map(member, MembersDTO.class);
	}
	
}
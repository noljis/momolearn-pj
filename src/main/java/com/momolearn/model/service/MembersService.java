package com.momolearn.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momolearn.model.MembersRepository;

import com.momolearn.model.dto.MembersDTO;
import com.momolearn.model.entity.Members;
import com.momolearn.util.DBUtil;
import com.momolearn.util.DBUtil2;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor //생성자 자동 생성
public class MembersService {
	

	@Autowired //의존관계를 자동으로 설정해 준다. (DAO 객체 주입)
	private MembersRepository membersRepository;


	//회원가입
    public static Members memJoin(Members members) throws SQLException {
    	EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(members);
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			
		} finally {
			em.close();
		}
		
        return members;
    }
    
    //아이디 중복 확인
    public static boolean duplecateID(String id) throws Exception{
		boolean result = true ;
		
	    try{
	    	Connection con = DBUtil2.getConnection();
	        String sql= "select id from members where id =?";
	        
	        
	        PreparedStatement pstmt=con.prepareStatement(sql);
	        pstmt.setString(1, id);
	        
	        ResultSet rs=pstmt.executeQuery();
	        
	        //존재하는 id라면 false로 바꾸기
	        if(rs.next()){
	        	result= false;
	        }
	        
	    }catch(Exception e){
	     	System.out.println("아이디 중복 확인 실패 : " + e);
	     	e.printStackTrace();
	     	throw e;
	    }
		return result;
	}
    
    public boolean checkId(String memId) throws Exception {
		EntityManager em = DBUtil.getEntityManager();
		
		Long count = null;
		try {
			count = em.createQuery("select count(m) from Members m where m.mem_Id = :mem_Id", Long.class)
					.setParameter("memId", memId)
					.getSingleResult();
		} finally {
			em.close();
		}
		
		return count == 0;
	}

	public boolean validateUser(String memId, String memPassword) throws Exception{
		EntityManager em = DBUtil.getEntityManager();
		
		Long count = null;
		try {
			count = em.createQuery("select count(m) from User m where m_id = :m_id and m_password = :m_password", Long.class)
					.setParameter("m_id", memId)
					.setParameter("m_password", memPassword)
					.getSingleResult();
			
		} finally {
			em.close();			
		}
		
		return count == 1;
	}
    
	//본인 조회 - jpa
    public static Members getMember (String id) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Members member = null;
		
		try {
			tx.begin();
			member = em.find(Members.class, id);
			
			System.out.println(member);
			
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			em.close();
		}
		
		return member;
	}
    
    //본인 프로필 수정
    public static boolean memUpdate(String id, String pw, String name, String email, String profile, String grade) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Members mem = null;
		
		try {
			tx.begin();
			mem = em.find(Members.class, id);
			if (mem != null) {
				// before update
				System.out.println("update 전 : " + mem);
//				mem.setMemId(id);
//				mem.setPw(pw);
//				mem.setName();
//				mem.setEmail();
//				mem.setProfile();
//				mem.setGrade();
				
				
			} else {
				System.out.println("업데이트 하려는 사람의 정보를 찾지 못하였습니다");
			}
			em.persist(mem); //persist -> update
			tx.commit(); 
			// after update
			System.out.println("update 후 : " + mem);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

		return false;
		
	}
    
    //회원 한명 삭제
	public void deleteMember(String id) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			//find로 찾기 
			Members members = em.find(Members.class, id);
			if (members != null) {
				em.remove(members);
			}else {
				System.out.println("이미 탈퇴처리가 완료된 회원입니다.");
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	
	//로그인
	public MembersDTO loginMember(String memId, String password) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();

			MembersDTO loginData = (MembersDTO) em.createNamedQuery("Members.findByLoginInfo").setParameter("memId", memId).setParameter("password", password).getSingleResult();
			
			em.persist(loginData);
			
			System.out.println(loginData);

			tx.commit();
			
			return loginData;
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return null;
	}
	
	//id찾기 (email로 찾기)
	public Members findId(String email) throws SQLException{
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Members members = null;
		try {
			
			tx.begin();
			
			members = (Members) em.createNamedQuery("Members.findByEmail").setParameter("email", email).getSingleResult();
			
			System.out.println(members); //테스트

			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return members;
	}
	//pw찾기 (id,email로 찾기)
	public Members findPwd(String id, String email) throws SQLException{
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Members members = null;
		try {
			
			tx.begin();
			
			members = (Members) em.createNamedQuery("Members.findPassword").setParameter("id", id).setParameter("email", email).getSingleResult();
			
			System.out.println(members); //테스트

			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return members;
	}
	
	//관리자 - 모든 회원 검색
	public List<Members> getAllMembers() {
		EntityManager em = DBUtil.getEntityManager();
		
		//jpql
		String sql = "select m from Members m";
		//createQuery : 쿼리 데이터 조회
		//getResultList : 조회된 데이터 추출
		List<Members> all = em.createQuery(sql).getResultList();

		em.close();
		
		return all;
	}
	
	//관리자 프로필 수정
    public static boolean adminUpdate(String id, String pw, String name, String email, String profile, String grade) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Members mem = null;
		
		try {
			tx.begin();
			mem = em.find(Members.class, id);
			if (mem != null) {
				// before update
				System.out.println("update 전 : " + mem);
//				mem.setMemId(id);
//				mem.setPw(pw);
//				mem.setName();
//				mem.setEmail();
//				mem.setProfile();
//				mem.setGrade();
				
				
			} else {
				System.out.println("업데이트 하려는 사람의 정보를 찾지 못하였습니다");
			}
			em.persist(mem); //persist -> update
			tx.commit(); 
			// after update
			System.out.println("update 후 : " + mem);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

		return false;
		
	}

}

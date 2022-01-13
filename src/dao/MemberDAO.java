package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbmanager.DBmanager;
import dto.MemberDTO;

public class MemberDAO {
	DBmanager dbm = DBmanager.getInstance();
	
	public int getSelectIdCheck(String id) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select id from member where id = ?";
		//정렬 하고싶으면 조회순, 번호순 쿼리 만들어놓고 매개변수 받아서 if문으로 돌리면 됌
		
		int result=0;
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result=1;
			}else {
				result=-1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return result;
	}
	
	public int getSelectNickCheck(String nickname) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select nickname from member where nickname = ?";
		//정렬 하고싶으면 조회순, 번호순 쿼리 만들어놓고 매개변수 받아서 if문으로 돌리면 됌
		
		int result=0;
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			
			if(rs.next()==true) {//중복됐을 경우 1 중복아닐 때 -1
				result=1;
			}else {
				result=-1;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return result;
	}
	
	
	public void memberJoin(MemberDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		String query = "insert into member (bno, name, id, pw, phone, email, profileimg, nickname) "
				+ "values (member_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPw());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getProfileimg());
			pstmt.setString(7, dto.getNickname());
			
			pstmt.executeUpdate();
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public int memberIdPwCheck(String id, String pw) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select id, pw from member where id = ?";
		//정렬 하고싶으면 조회순, 번호순 쿼리 만들어놓고 매개변수 받아서 if문으로 돌리면 됌
		
		int result=0;
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//true: id 있음
				if(rs.getString("pw") != null && rs.getString("pw").equals(pw)) {
					result=1;//id와 pw가 같을 때
				}else {
					result=0;//pw가 같지 않을 때
				}
			}else {
				result=-1;//id가 없을 때(회원가입한 사람(아이디)이 없을 때
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return result;
	}
	
	//업데이트 하기위한 레코드 검색
	public MemberDTO memberOneSelect(String id) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from member where id = '"+id+"'";
		
		MemberDTO dto = null;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
	
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setName(rs.getString("name"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setProfileimg(rs.getString("profileimg"));
				dto.setNickname(rs.getString("nickname"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return dto;
	}
	
	public void setMemberUpdate(MemberDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		String query = null;
		
		try {
			conn = dbm.GetConnection();
			
			if(dto.getPw() != null && dto.getProfileimg() != null) {//비번 프사 둘 다 변경
				query = "update member set name=?, pw=?, phone=?, email=?, profileimg=?, nickname=? where id = '"+dto.getId()+"'";
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, dto.getName());
				pstmt.setString(2, dto.getPw());
				pstmt.setString(3, dto.getPhone());
				pstmt.setString(4, dto.getEmail());
				pstmt.setString(5, dto.getProfileimg());
				pstmt.setString(6, dto.getNickname());
				
			}else if(dto.getPw() != null && dto.getProfileimg() == null){//비번만 변경
				query = "update member set name=?, pw=?, phone=?, email=?, nickname=? where id = '"+dto.getId()+"'";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, dto.getName());
				pstmt.setString(2, dto.getPw());
				pstmt.setString(3, dto.getPhone());
				pstmt.setString(4, dto.getEmail());
				pstmt.setString(5, dto.getNickname());
				
			}else if(dto.getPw() == null && dto.getProfileimg() != null){//프사만 변경
				query = "update member set name=?, phone=?, email=?, profileimg=?, nickname=? where id = '"+dto.getId()+"'";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, dto.getName());
				pstmt.setString(2, dto.getPhone());
				pstmt.setString(3, dto.getEmail());
				pstmt.setString(4, dto.getProfileimg());
				pstmt.setString(5, dto.getNickname());
			}else {//둘 다 안바꾸는 경우
				query = "update member set name=?, phone=?, email=?, nickname=? where id = '"+dto.getId()+"'";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, dto.getName());
				pstmt.setString(2, dto.getPhone());
				pstmt.setString(3, dto.getEmail());
				pstmt.setString(4, dto.getNickname());
			}
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	//탈퇴 관련 메서드(회원정보, 게시글, 댓글, 질문까지 삭제)
	public void memberWithdraw(String id) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		myblogCmtDeleteWithdraw(id);
		myblogDeleteWithdraw(id);
		answerDeleteWithdraw(id);
		qaDeleteWithdraw(id);
		
		String query = "delete from member where id = ?";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
		
	}
	
	public void myblogDeleteWithdraw(String id) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		String query = "delete from myblog where writer = ?";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
		
		
	}
	
	public void myblogCmtDeleteWithdraw(String id) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		String query = "delete from myblogcmt where id = ?";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public void qaDeleteWithdraw(String id) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		String query = "delete from qa where writer = ?";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public void answerDeleteWithdraw(String id) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		String query = "delete from myanswer where id = ?";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	//탈퇴 관련 메서드 끝
}

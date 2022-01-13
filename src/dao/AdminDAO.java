package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbmanager.DBmanager;
import dto.GuestbookDTO;
import dto.MemberDTO;
import utility.Criteria;

public class AdminDAO {
	DBmanager dbm = DBmanager.getInstance();
	
	private static AdminDAO instance = new AdminDAO();
	private AdminDAO() {}
	public static AdminDAO getInstance() {
		return instance;
	}
	
	public List<MemberDTO> getListwithPaging(Criteria cri, String query ){
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = null;
		//정렬 하고싶으면 조회순, 번호순 쿼리 만들어놓고 매개변수 받아서 if문으로 돌리면 됌
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		
		 if(query != "") {//검색 키워드가 있을 때
	            sql = "select * from (select /*+ index_desc(member mbno_pk) */  "
	                  + "rownum rn, bno, id, name, phone, email, nickname "
	                  + "from member where (" + query + ") and rownum <= ? * ? )"
	                  + " where rn > (?-1) * ?";
	            
	         }else {//검색 안할 때
	            sql = "select * from (select /*+ index_desc(member mbno_pk) */  "
	                  + "rownum rn, bno, id, name, phone, email, nickname from member where rownum  <= ? * ?) "
	                  + " where rn > (?-1) * ?";
	         }
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, cri.getPageNum());
			pstmt.setInt(2, cri.getAmount());
			pstmt.setInt(3, cri.getPageNum());
			pstmt.setInt(4, cri.getAmount());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setNickname(rs.getString("nickname"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
	
	public int AdminCount(String query){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		if(query != "") {
			sql = "select count(*) as cnt from member where "+query;
		}else {
			sql = "select count(*) as cnt from member";
		}
		
		int count=0;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				count=rs.getInt("cnt");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return count;
	}
}

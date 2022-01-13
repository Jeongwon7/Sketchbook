package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbmanager.DBmanager;
import dto.GuestbookDTO;
import utility.Criteria;

public class GuestbookDAO {
	DBmanager dbm = DBmanager.getInstance();
	
	public void GuestbookInsert(GuestbookDTO gdto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query="insert into guestbook (bno, title, content, writer,imgurl) "
				+ " values (guestbook_seq.nextval,?,?,?,?)";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gdto.getTitle());
			pstmt.setString(2, gdto.getContent());
			pstmt.setString(3, gdto.getWriter());
			pstmt.setString(4, gdto.getImgurl());
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public List<GuestbookDTO> GuestbookSelect() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from guestbook order by bno desc";
		
		List<GuestbookDTO> list = new ArrayList<GuestbookDTO>();
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				GuestbookDTO dto = new GuestbookDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setViewcount(rs.getInt("viewcount"));
				
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public int guestbookCount(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select count(*) as cnt from guestbook";
		
		int count=0;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
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
	
	public GuestbookDTO GuestbookView(int bno) {
		guestbookViewCount(bno);
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from guestbook where bno=? ";
		
		GuestbookDTO gdto = null;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,bno);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				gdto = new GuestbookDTO();
				
				gdto.setBno(rs.getInt("bno"));
				gdto.setTitle(rs.getString("title"));
				gdto.setContent(rs.getString("content"));
				gdto.setWriter(rs.getString("writer"));
				gdto.setRegdate(rs.getString("regdate"));
				gdto.setViewcount(rs.getInt("viewcount"));
				gdto.setImgurl(rs.getString("imgurl"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return gdto;
	}
	
	public void guestbookViewCount(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query="update guestbook set viewcount = viewcount + 1 where bno = ?";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public GuestbookDTO prevByBno(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select bno,title from guestbook where bno=(select max(bno) from guestbook where bno < ?)";
		
		GuestbookDTO gdto = null;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,bno);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				gdto = new GuestbookDTO();
				gdto.setBno(rs.getInt("bno"));
				gdto.setTitle(rs.getString("title"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return gdto;
	}
	
	public GuestbookDTO nextByBno(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select bno,title from guestbook where bno=(select min(bno) from guestbook where bno > ?)";
		
		GuestbookDTO gdto = null;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,bno);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				gdto = new GuestbookDTO();
				gdto.setBno(rs.getInt("bno"));
				gdto.setTitle(rs.getString("title"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return gdto;
	}
	
	public void guestbookDelete(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "delete from guestbook where bno=?";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public GuestbookDTO guestbookModify(int bno) {
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from guestbook where bno=? ";
		
		GuestbookDTO gdto = null;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,bno);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				gdto = new GuestbookDTO();
				
				gdto.setBno(rs.getInt("bno"));
				gdto.setTitle(rs.getString("title"));
				gdto.setContent(rs.getString("content"));
				gdto.setWriter(rs.getString("writer"));
				gdto.setRegdate(rs.getString("regdate"));
				gdto.setViewcount(rs.getInt("viewcount"));
				gdto.setImgurl(rs.getString("imgurl"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return gdto;
	}
	
	public void guestbookModify(GuestbookDTO dto) {
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "update guestbook set title=?, content=?, imgurl=? where bno=? ";

		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getImgurl());
			pstmt.setInt(4, dto.getBno());
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	
	}
	
	public List<GuestbookDTO> GuestbookSelect(String query) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql="";
		
		if(query != "") {
			sql = "select * from guestbook where "+query+" order by bno desc";
		}else {
			sql = "select * from guestbook order by bno desc";
		}
		
		List<GuestbookDTO> list = new ArrayList<GuestbookDTO>();
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				GuestbookDTO dto = new GuestbookDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setViewcount(rs.getInt("viewcount"));
				
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public int guestbookCount(String query){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		if(query != "") {
			sql = "select count(*) as cnt from guestbook where "+query;
		}else {
			sql = "select count(*) as cnt from guestbook";
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
	
	public List<GuestbookDTO> getListwithPaging(Criteria cri, String query ){
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = null;
		//정렬 하고싶으면 조회순, 번호순 쿼리 만들어놓고 매개변수 받아서 if문으로 돌리면 됌
		
		List<GuestbookDTO> list = new ArrayList<GuestbookDTO>();
		
		
		 if(query != "") {//검색 키워드가 있을 때
	            sql = "select * from (select /*+ index_desc(guestbook guestbook_pk) */ "
	                  + "rownum rn, bno, title, content, writer, regdate, viewcount "
	                  + "from guestbook where (" + query + ") and rownum <= ? * ? )"
	                  + " where rn > (?-1) * ?";
	            
	         }else {//검색 안할 때
	            sql = "select * from (select /*+ index_desc(guestbook guestbook_pk) */ "
	                  + "rownum rn, bno, title, content, writer, regdate, viewcount from guestbook where rownum  <= ? * ?) "
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
				GuestbookDTO dto = new GuestbookDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegdate(rs.getString("regdate"));
				//dto.setRegdate(rs.getString("regdate").substring(0, 10));
				dto.setViewcount(rs.getInt("viewcount"));
				
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
}//class

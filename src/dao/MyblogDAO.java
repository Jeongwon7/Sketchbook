package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbmanager.DBmanager;
import dto.MyblogDTO;
import utility.Criteria;

public class MyblogDAO {
	DBmanager dbm = DBmanager.getInstance();
	
	public void MyblogInsert(MyblogDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query="insert into myblog (bno, title, content, imgurl, writer) "
				+ " values (myblog_seq.nextval,?,?,?,?)";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getImgurl());
			pstmt.setString(4, dto.getWriter());
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public List<MyblogDTO> MyblogSelect() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from myblog order by bno desc";
		
		List<MyblogDTO> list = new ArrayList<MyblogDTO>();
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				MyblogDTO dto = new MyblogDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setViewcount(rs.getInt("viewcount"));
				dto.setImgurl(rs.getString("imgurl"));
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public MyblogDTO myblogView(int bno) {
		myblogViewCount(bno);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select b.bno, b.title, b.content, b.regdate, b.viewcount, b.imgurl, b.writer, m.nickname "
				+ "from myblog b, member m where b.bno=?  and b.writer=m.id";
		
		MyblogDTO  gdto = null;
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,bno );
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				gdto = new MyblogDTO();
				
				gdto.setBno(rs.getInt("bno"));
				gdto.setTitle(rs.getString("title"));
				gdto.setContent(rs.getString("content"));
				gdto.setRegdate(rs.getString("regdate"));
				gdto.setViewcount(rs.getInt("viewcount"));
				gdto.setImgurl(rs.getString("imgurl"));
				gdto.setWriter(rs.getString("writer"));
				gdto.setNickname(rs.getString("nickname"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return gdto;
	}
	
	public void myblogViewCount(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query="update myblog set viewcount = viewcount + 1 where bno = ?";
		
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
	
	public MyblogDTO prevByBno(int bno, String writer) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select bno, title, writer from myblog "
				+ "where bno=(select max(bno) from myblog where bno < ?) and writer='"+writer+"'";
		
		MyblogDTO gdto = null;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,bno);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				gdto = new MyblogDTO();
				gdto.setBno(rs.getInt("bno"));
				gdto.setTitle(rs.getString("title"));
				gdto.setWriter(rs.getString("writer"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return gdto;
		
	}
	
	public MyblogDTO nextByBno(int bno, String writer) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select bno, title, writer from myblog "
				+ "where bno=(select min(bno) from myblog where bno > ?) and writer='"+writer+"'";
		
		MyblogDTO gdto = null;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,bno);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				gdto = new MyblogDTO();
				gdto.setBno(rs.getInt("bno"));
				gdto.setTitle(rs.getString("title"));
				gdto.setWriter(rs.getString("writer"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return gdto;
	}
	
	public void MyblogDelete(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "delete from myblog where bno=?";
		
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
	
	public void MyblogCmtAllDelete(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "delete from myblogcmt where bno=?";
		
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
	
	public MyblogDTO myblogModify(int bno) {
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from myblog where bno=? ";
		
		MyblogDTO gdto = null;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,bno);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				gdto = new MyblogDTO();
				
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
	
	public void myblogModify(MyblogDTO dto) {
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = null;
		
		if(dto.getImgurl() != null) {//첨부파일도 수정할 때
			query = "update myblog set title=?, content=?, imgurl='"+dto.getImgurl()+"' where bno=? ";
		}else {
			query = "update myblog set title=?, content=? where bno=? ";
		}
		
		

		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getBno());
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	
	}
	
	//서치+전체 셀렉트
	public List<MyblogDTO> myblogSelect(String query) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql="";
		
		if(query != "") {
			sql = "select * from myblog where "+query+" order by bno desc";
		}else {
			sql = "select * from myblog order by bno desc";
		}
		
		List<MyblogDTO> list = new ArrayList<MyblogDTO>();
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				MyblogDTO dto = new MyblogDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setViewcount(rs.getInt("viewcount"));
				dto.setImgurl(rs.getString("imgurl"));
				
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public List<MyblogDTO> getPopulerSelect(String writer) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from (select * from myblog order by viewcount desc) where rownum < 4 and writer = '"+writer+"'";
		
		List<MyblogDTO> list = new ArrayList<MyblogDTO>();
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				MyblogDTO dto = new MyblogDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setViewcount(rs.getInt("viewcount"));
				dto.setImgurl(rs.getString("imgurl"));
				dto.setWriter(rs.getString("writer"));
				
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public int myblogCount(String query, String writer){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		if(query != "") {
			sql = "select count(*) as cnt from myblog where "+query+" and writer = '"+writer+"'";
		}else {
			sql = "select count(*) as cnt from myblog where writer = '"+writer+"'";
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
	
	//셀렉트 검색 페이징
	public List<MyblogDTO> getListwithPaging(Criteria cri, String query, String writer){
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = null;
		//정렬 하고싶으면 조회순, 번호순 쿼리 만들어놓고 매개변수 받아서 if문으로 돌리면 됌
		
		List<MyblogDTO> list = new ArrayList<MyblogDTO>();
		
		
		 if(query != "") {//검색 키워드가 있을 때
	            sql = "select * from (select /*+ index_desc(myblog myblog_pk) */ "
	                  + "rownum rn, b.bno, b.title, b.content, b.regdate, b.viewcount, b.imgurl, b.writer, m.nickname "
	                  + "from myblog b, member m where (" + query + ") and rownum <= ? * ? and b.writer = '"+writer+"' and b.writer=m.id order by b.bno desc)"
	                  + " where rn > (?-1) * ?";
	            
	         }else {//검색 안할 때
	            sql = "select * from (select /*+ index_desc(myblog myblog_pk) */ "
	                  + "rownum rn, b.bno, b.title, b.content, b.regdate, b.viewcount, b.imgurl, b.writer, m.nickname"
	                  + " from myblog b, member m where rownum  <= ? * ? and b.writer = '"+writer+"' and b.writer=m.id order by b.bno desc) "
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
				MyblogDTO dto = new MyblogDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setViewcount(rs.getInt("viewcount"));
				dto.setImgurl(rs.getString("imgurl"));
				dto.setWriter(rs.getString("writer"));
				dto.setNickname(rs.getString("nickname"));
				
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
}

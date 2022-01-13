package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbmanager.DBmanager;
import dto.MyblogDTO;

public class MainDAO {
	
	DBmanager dbm = DBmanager.getInstance();
	
	public List<MyblogDTO> getPopulerSelect() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from (select b.bno, b.title, b.content, b.regdate, b.viewcount, b.imgurl, b.writer, m.nickname "
				+ "from myblog b, member m where b.writer=m.id order by b.viewcount desc) where rownum < 7 ";
		
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
				dto.setNickname(rs.getString("nickname"));
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public List<MyblogDTO> getLatestSelect() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from (select b.bno, b.title, b.content, b.regdate, b.viewcount, b.imgurl, b.writer, m.nickname "
				+ "from myblog b, member m where b.writer=m.id order by b.bno desc) where rownum < 6 ";
		
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
				dto.setNickname(rs.getString("nickname"));
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
}

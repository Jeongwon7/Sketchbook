package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbmanager.DBmanager;
import dto.CmtDTO;
import dto.MyblogDTO;
import utility.Criteria;

public class MycmtDAO {
	
	private static MycmtDAO instance = new MycmtDAO();
	private MycmtDAO() {}
	public static MycmtDAO getInstance() {
		return instance;
	}
	
	DBmanager dbm = DBmanager.getInstance();
	
	public int cmtInsert(CmtDTO cvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int rst = 0;
		
		String query="insert into myblogcmt (num, bno, id, replytext) values(mycmt_seq.nextval, ?, ?, ?)";
		
		try {
			conn = dbm.GetConnection();
			
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, cvo.getBno());
			pstmt.setString(2, cvo.getId());
			pstmt.setString(3, cvo.getReplytext());
			
			rst = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
		
		return rst;
	}
	
	public List<CmtDTO> getCmtList(int bno){//bno는 글번호
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CmtDTO> clist = new ArrayList<CmtDTO>();
		
		String  query = "select c.num, c.bno, c.id, c.replytext, c.wdate, m.profileimg, m.nickname from myblogcmt c, member m "
				+ "where c.bno = ? and c.id = m.id order by c.num desc";
        		
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, bno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CmtDTO dto = new CmtDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setBno(rs.getInt("bno"));
				dto.setId(rs.getString("id"));
				dto.setReplytext(rs.getString("replytext"));
				dto.setWdate(rs.getString("wdate").substring(0,10));
				dto.setProfileimg(rs.getString("profileimg"));
				dto.setNickname(rs.getString("nickname"));
				clist.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return clist;
	}
	
	
	public List<CmtDTO> getCmtListWithPaging(Criteria cri, int bno){//bno는 글번호
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CmtDTO> clist = new ArrayList<CmtDTO>();
		
		String  query = "select * from (select /*+ index_desc(myblogcmt cmtnum_pk) */ "
        		+"rownum rn, c.num, c.bno, c.id, c.replytext, c.wdate, m.profileimg from myblogcmt c, member m where c.bno = ? and c.id = m.id  rownum <= ? *?)"
        		+ " where rn > (?-1) * ?";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, bno);
			pstmt.setInt(2, cri.getPageNum());
			pstmt.setInt(3, cri.getAmount());
			pstmt.setInt(4, cri.getPageNum());
			pstmt.setInt(5, cri.getAmount());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CmtDTO dto = new CmtDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setBno(rs.getInt("bno"));
				dto.setId(rs.getString("id"));
				dto.setReplytext(rs.getString("replytext"));
				dto.setWdate(rs.getString("wdate").substring(0,10));
				dto.setProfileimg(rs.getString("profileimg"));
				clist.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return clist;
	}
	
	
	public int myblogCmtCount(int bno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) as cnt from myblogcmt where bno = ?";
				
		int count=0;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
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
	
	public void cmtDelete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		String query="delete from myblogcmt where num = ?";
		
		try {
			conn = dbm.GetConnection();
			pstmt= conn.prepareStatement(query);
			
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
		
	}

	
	public int myblogCmtUpdate(CmtDTO dto) {
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "update myblogcmt set replytext=? where num=? ";
		
		int result = 0;
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, dto.getReplytext());
			pstmt.setInt(2, dto.getNum());
			result = pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
		return result;
	}
	
}

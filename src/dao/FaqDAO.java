package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbmanager.DBmanager;
import dto.FaqDTO;
import dto.QnaDTO;
import utility.Criteria;

public class FaqDAO {
	DBmanager dbm = DBmanager.getInstance();
	
	private static FaqDAO instance = new FaqDAO();
	private FaqDAO() {}
	public static FaqDAO getInstance() {
		return instance;
	}
	
	
	public void FaqInsert(FaqDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "insert into myfaq (bno, fq, fa) values (faq_seq.nextval, ?, ?)";
	
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getFq());
			pstmt.setString(2, dto.getFa());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public List<FaqDTO> FaqSelectwithPaging(Criteria cri, String query){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<FaqDTO> list = new ArrayList<FaqDTO>();
		
		if(query != "") {//검색 키워드가 있을 때
            sql = "select * from (select /*+ index_desc(myfaq faqbno_pk) */ "
                  + "rownum rn, bno, fq, fa "
                  + "from myfaq where (" + query + ") and rownum <= ? * ? )"
                  + " where rn > (?-1) * ?";
            
         }else {//검색 안할 때
            sql = "select * from (select /*+ index_desc(myfaq faqbno_pk) */ "
                  + "rownum rn, bno, fq, fa from myfaq where rownum  <= ? * ?) "
                  + " where rn > (?-1) * ?";
         }
		
		
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cri.getPageNum());
			pstmt.setInt(2, cri.getAmount());
			pstmt.setInt(3, cri.getPageNum());
			pstmt.setInt(4, cri.getAmount());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FaqDTO dto = new FaqDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setFq(rs.getString("fq"));
				dto.setFa(rs.getString("fa"));
				
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public int FaqCount(String query){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		if(query != "") {
			sql = "select count(*) as cnt from myfaq where "+query;
		}else {
			sql = "select count(*) as cnt from myfaq";
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
	
	public void faqDelete(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "delete from myfaq where bno=?";
		
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
	
	public List<FaqDTO> FaqSelect(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<FaqDTO> list = new ArrayList<FaqDTO>();
		
		String sql = "select * from myfaq order by bno desc";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FaqDTO dto = new FaqDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setFq(rs.getString("fq"));
				dto.setFa(rs.getString("fa"));
				
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public FaqDTO faqModify(int bno) {
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from myfaq where bno=? ";
		
		FaqDTO dto = null;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,bno);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto = new FaqDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setFq(rs.getString("fq"));
				dto.setFa(rs.getString("fa"));
			
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return dto;
	}

	public void FaqModify(FaqDTO dto) {
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "update myfaq set fq=?, fa=? where bno=? ";

		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, dto.getFq());
			pstmt.setString(2, dto.getFa());
			pstmt.setInt(3, dto.getBno());
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbmanager.DBmanager;
import dto.AnswerDTO;
import dto.CmtDTO;
import dto.GuestbookDTO;
import dto.QnaDTO;
import utility.Criteria;

public class QnaDAO {
	DBmanager dbm =  DBmanager.getInstance();
	
	private static QnaDAO qdao = new QnaDAO();
	private QnaDAO() {}
	public static QnaDAO getInstance() {
		return qdao;
	}
	
	public int qnaInsert(QnaDTO cvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int rst = 0;
		
		String query="insert into qa (qbno, title, qcontent, writer, imgurl) values(qa_seq.nextval, ?, ?, ?, ?)";
		
		try {
			conn = dbm.GetConnection();
			
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, cvo.getTitle());
			pstmt.setString(2, cvo.getQcontent());
			pstmt.setString(3, cvo.getWriter());
			pstmt.setString(4, cvo.getImgurl());
			rst = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
		
		return rst;
	}
	
	public List<QnaDTO> qnaSelectwithPaging(Criteria cri, String query){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<QnaDTO> list = new ArrayList<QnaDTO>();
		
		if(query != "") {//검색 키워드가 있을 때
            sql = "select * from (select /*+ index_desc (q qa_pk) */ "
                  + "rownum rn, q.qbno, q.title, q.qcontent, q.writer, q.regdate, q.viewcount, q.status, q.imgurl, m.nickname "
                  + "from qa q, member m where q.writer = m.id and (" + query + ") and rownum <= ? * ? order by q.qbno desc )"
                  + " where rn > (?-1) * ?";
            
         }else {//검색 안할 때
            sql = "select * from (select /*+ index_desc (q qa_pk) */ "
                  + "rownum rn, q.qbno, q.title, q.qcontent, q.writer, q.regdate, q.viewcount, q.status, q.imgurl, m.nickname "
                  + "from qa q, member m where q.writer = m.id and rownum  <= ? * ? order by q.qbno desc ) "
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
				QnaDTO dto = new QnaDTO();
				dto.setQbno(rs.getInt("qbno"));
				dto.setTitle(rs.getString("title"));
				dto.setQcontent(rs.getString("qcontent"));
				dto.setStatus(rs.getInt("status"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setViewcount(rs.getInt("viewcount"));
				dto.setImgurl(rs.getString("imgurl"));
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
	
	public QnaDTO qnaselect(int qbno){
		QnaViewCount(qbno);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select q.qbno, q.title, q.qcontent, q.writer, q.regdate, q.viewcount, q.status, q.imgurl, m.nickname "
				+ " from qa q, member m where qbno = ? and q.writer=m.id";
		
		QnaDTO dto = new QnaDTO();
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qbno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto.setQbno(rs.getInt("qbno"));
				dto.setTitle(rs.getString("title"));
				dto.setQcontent(rs.getString("qcontent"));
				dto.setStatus(rs.getInt("status"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setViewcount(rs.getInt("viewcount"));
				dto.setImgurl(rs.getString("imgurl"));
				dto.setNickname(rs.getString("nickname"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return dto;
	}
	
	public void QnaViewCount(int qbno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query="update qa set viewcount = viewcount + 1 where qbno = ?";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qbno);
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public int QnaCount(String query){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
				
		if(query != "") {
			sql = "select count(*) as cnt from qa where "+query;
		}else {
			sql = "select count(*) as cnt from qa";
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
	
	//이전글
	public QnaDTO prevByBno(int qbno) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query="select qbno, title from qa where qbno=(select max(qbno) from qa where qbno < ?)";
		
		QnaDTO dto = null;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, qbno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new QnaDTO();
				dto.setQbno(rs.getInt("qbno"));
				dto.setTitle(rs.getString("title"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return dto;
	}
	
	//다음글
	public QnaDTO nextByBno(int qbno) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query="select qbno, title from qa where qbno=(select min(qbno) from qa where qbno > ?)";
		
		QnaDTO dto = null;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, qbno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new QnaDTO();
				dto.setQbno(rs.getInt("qbno"));
				dto.setTitle(rs.getString("title"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return dto;
	}
	public int AnswerInsert(AnswerDTO avo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int rst = 0;
		
		String query="insert into myanswer (num, qbno, id, answertext) values(myanswer_seq.nextval, ?, ?, ?)";
		
		try {
			conn = dbm.GetConnection();
			
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, avo.getQbno());
			pstmt.setString(2, avo.getId());
			pstmt.setString(3, avo.getAnswertext());
			
			rst = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
		
		return rst;
	}
	
	public List<AnswerDTO> selectAnswer(int qbno){//qbno는 글번호
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<AnswerDTO> clist = new ArrayList<AnswerDTO>();
		
		String query = "select c.num, c.qbno, c.id, c.answertext, c.wdate, m.nickname, m.profileimg "
				+ "from myanswer c, member m where c.qbno = ? and c.id = m.id order by c.num desc";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, qbno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AnswerDTO dto = new AnswerDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setQbno(rs.getInt("qbno"));
				dto.setId(rs.getString("id"));
				dto.setAnswertext(rs.getString("answertext"));
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
	
	public int AnswerCount(int qbno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) as cnt from myanswer where qbno = ?";
				
		int count=0;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, qbno);
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
	
	public void AnswerDelete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		String query="delete from myanswer where num = ?";
		
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
	
	public void status(int qbno) {//답변댓글이 달렸을 때 실행
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "update qa set status = 1 where qbno = ?";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qbno);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public void statusminus(int qbno) {//답변댓글이 달렸을 때 실행
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "update qa set status = 0 where qbno = ?";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qbno);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public QnaDTO qnaModify(int qbno) {
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from qa where qbno=? ";
		
		QnaDTO dto = null;
		
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,qbno);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				dto = new QnaDTO();
				
				dto.setQbno(rs.getInt("qbno"));
				dto.setTitle(rs.getString("title"));
				dto.setQcontent(rs.getString("qcontent"));
				dto.setStatus(rs.getInt("status"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setImgurl(rs.getString("imgurl"));
			
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt, rs);
		}
		return dto;
	}

	public void qnaModify(QnaDTO dto) {
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "update qa set title=?, qcontent=?, imgurl=? where qbno=? ";

		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getQcontent());
			pstmt.setString(3, dto.getImgurl());
			pstmt.setInt(4, dto.getQbno());
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	
	}
	
	public void QnaDelete(int qbno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "delete from qa where qbno=?";
		QnaAmswerDelete(qbno);
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qbno);
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public void QnaAmswerDelete(int qbno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "delete from myanswer where qbno=?";
		
		try {
			conn = dbm.GetConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qbno);
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbm.close(conn, pstmt);
		}
	}
	
	public int QnaAnswerUpdate(AnswerDTO dto) {
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "update myanswer set answertext=? where num=? ";
		
		int result = 0;
		try {
			conn=dbm.GetConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, dto.getAnswertext());
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

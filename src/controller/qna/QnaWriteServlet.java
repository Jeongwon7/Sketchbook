package controller.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.MyblogDAO;
import dao.QnaDAO;
import dto.QnaDTO;

@WebServlet("/qawrite.do")
public class QnaWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QnaWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("qna/qnawrite.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		 //첨부파일 처리
	    String savepath = "upload"; //업로드 폴더
	    int maxPostSize = 20 * 1024 * 1024; // 최대 업로드 용량
	    String enctype = "utf-8"; //인코딩 문자
	      
	    ServletContext context = getServletContext();
	    String path = context.getRealPath(savepath); //서버상의 업로드 폴더 실제 경로
	    System.out.println("서버상의 업로드 실제 디렉토리: "+path);
	      
	    MultipartRequest multi = new MultipartRequest(//MultipartRequest객체가 생성됨과 동시에 업로드는 실행된다.
	          request, //request객체
	          path, //서버상의 실제 디렉토리
	          maxPostSize, //최대 업로드 파일 크기
	          enctype,  //인코딩
	          new DefaultFileRenamePolicy() //동일한 파일이 존재하면 새로운 이름을 부여하는 객체
	       ); //업로드 이미 완료됨
	    
	    String imgurl = multi.getFilesystemName("imgurl"); //실제 서버에 업로드된 파일명을 구함
	    String title = multi.getParameter("title");
	    String qcontent = multi.getParameter("qcontent");
	    String writer = (String) request.getSession().getAttribute("userid");
	    
	    QnaDTO dto = new QnaDTO();
	    dto.setTitle(title);
	    dto.setQcontent(qcontent);
	    dto.setImgurl(imgurl);
	    dto.setWriter(writer);
	      
	    QnaDAO dao = QnaDAO.getInstance();
	    dao.qnaInsert(dto);
		
		response.sendRedirect("qna.do");//처리 다하고 리스트 페이지로~~
	
	}

}

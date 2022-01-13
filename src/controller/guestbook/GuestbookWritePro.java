package controller.guestbook;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.GuestbookDAO;
import dto.GuestbookDTO;

@WebServlet("/guestbookwritepro.do")
public class GuestbookWritePro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuestbookWritePro() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
	    String content = multi.getParameter("content");
	    String writer = multi.getParameter("writer");
	      
	    GuestbookDTO dto = new GuestbookDTO();
	    dto.setTitle(title);
	    dto.setContent(content);
	    dto.setWriter(writer); //임의로 저장(글쓰기 화면엔 작성자(writer)가 없는데 테이블에 작성자가 있기때문에 null값이 넘어가면 안되니까)
	    dto.setImgurl(imgurl);
	      
	    GuestbookDAO dao = new GuestbookDAO();
	    dao.GuestbookInsert(dto);
		
		response.sendRedirect("guestbook.do");//처리 다하고 리스트 페이지로~~
	}

}

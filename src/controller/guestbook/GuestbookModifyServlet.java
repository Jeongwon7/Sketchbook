package controller.guestbook;

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

import dao.GuestbookDAO;
import dto.GuestbookDTO;

@WebServlet("/guestbookmodify.do")
public class GuestbookModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuestbookModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		GuestbookDAO dao = new GuestbookDAO();
		GuestbookDTO dto = dao.guestbookModify(bno);
		
		request.setAttribute("dto", dto);
		RequestDispatcher rd = request.getRequestDispatcher("guestbook/guestbookmodify.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
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
	         ); //업로드 이미 완료됨(path에)
	      
	      String imgurl = multi.getFilesystemName("imgurl"); //실제 서버에 업로드된 파일명을 구함
	      String title = multi.getParameter("title");
	      String content = multi.getParameter("content");
	      int bno = Integer.parseInt(multi.getParameter("bno"));
	      
	      GuestbookDTO dto = new GuestbookDTO();
	      dto.setTitle(title);
	      dto.setContent(content);
	      dto.setBno(bno); //임의로 저장(글쓰기 화면엔 작성자(writer)가 없는데 테이블에 작성자가 있기때문에 null값이 넘어가면 안되니까)
	      dto.setImgurl(imgurl);
	      
	      GuestbookDAO dao = new GuestbookDAO();
	      dao.guestbookModify(dto);
	      
	      response.sendRedirect("guestbook.do");
		
	}

}

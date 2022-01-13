package controller.member;

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

import dao.MemberDAO;
import dto.MemberDTO;
import utility.SecurityPassword;

@WebServlet("/member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("member/member.jsp");
		rd.forward(request, response);	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String savepath = "upload"; 
	    int maxPostSize = 20 * 1024 * 1024; 
	    String enctype = "utf-8"; 
	    
	    ServletContext context = getServletContext();
	    String path = context.getRealPath(savepath);
	    System.out.println("서버상의 업로드 실제 디렉토리: "+path);
	    
	    MultipartRequest multi = new MultipartRequest(
		          request, 
		          path, 
		          maxPostSize, 
		          enctype, 
		          new DefaultFileRenamePolicy() 
		       );
	    
	    String name = multi.getParameter("name");
	    String nickname = multi.getParameter("nickname");
	    String id = multi.getParameter("id");
	    String pw = SecurityPassword.encoding(multi.getParameter("pw1"));
	    String phone = multi.getParameter("phone");
	    String email = multi.getParameter("email");
	    String profileimg = multi.getFilesystemName("profileimg");
		
		MemberDTO dto = new MemberDTO();
		
		dto.setName(name);
		dto.setNickname(nickname);
		dto.setId(id);
		dto.setPw(pw);
		dto.setPhone(phone);
		dto.setEmail(email);
		dto.setProfileimg(profileimg);
		
		MemberDAO dao = new MemberDAO();
		
		dao.memberJoin(dto);
		
		response.sendRedirect("main.do");
	}

}

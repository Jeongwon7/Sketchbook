package controller.admin;

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

@WebServlet("/adminusermodify.do")
public class AdminUserModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminUserModify() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		
		MemberDAO dao = new MemberDAO(); 
		
		MemberDTO dto =  dao.memberOneSelect(id);
		
		request.setAttribute("upmember", dto);
		
		RequestDispatcher rd = request.getRequestDispatcher("admin/adminusermodify.jsp");
		rd.forward(request, response);
	}

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
		
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		
		String name = multi.getParameter("name");
		String id = multi.getParameter("id");
		String pw = SecurityPassword.encoding(multi.getParameter("pw1"));
		String phone = multi.getParameter("phone");
		String email = multi.getParameter("email");
		String profileimg = multi.getFilesystemName("profileimg");
		String nickname = multi.getParameter("nickname");
		
		dto.setName(name);
		dto.setId(id);
		if(multi.getParameter("pw1") != "") {
			dto.setPw(pw);
		}
		dto.setPhone(phone);
		dto.setEmail(email);
		if(multi.getFilesystemName("profileimg") != "") {
			dto.setProfileimg(profileimg);
		}
		dto.setNickname(nickname);
		
		dao.setMemberUpdate(dto);
		
		response.sendRedirect("adminusermodify.do?id="+id);
	}

}

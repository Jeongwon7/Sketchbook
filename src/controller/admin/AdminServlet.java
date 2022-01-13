package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import utility.SecurityPassword;

@WebServlet("/admin.do")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("admin/AdminLogin.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pw = SecurityPassword.encoding(request.getParameter("pw"));
		
		if((id.equals("admin")) && (pw.equals(SecurityPassword.encoding("1234")))){
			MemberDAO dao = new MemberDAO();
			
			int result = dao.memberIdPwCheck(id, pw);
			
			HttpSession session = request.getSession();//세션 객체 생성
			
			if(result == 1) {
				session.setAttribute("adminId", id);//세션 속성에 저장(페이지를 벗어나도 로그인 유지)
				response.sendRedirect("adminlist.do");
			}else if (result == 0) {
				request.setAttribute("msg", "ID、PASSWORDを確認してください");//리퀘스트 속성에 저장 login/login.jsp 에서만 유지된다
				RequestDispatcher rd = request.getRequestDispatcher("admin/AdminLogin.jsp");
				rd.forward(request, response);
			}else if(result == -1) {
				request.setAttribute("msg", "管理者アカウントが存在しません");
				RequestDispatcher rd = request.getRequestDispatcher("admin/AdminLogin.jsp");
				rd.forward(request, response);
			}
			
		}else {
			request.setAttribute("msg", "ID、PASSWORDを確認してください");
			RequestDispatcher rd = request.getRequestDispatcher("admin/AdminLogin.jsp");
			rd.forward(request, response);
		}
		
	}

}

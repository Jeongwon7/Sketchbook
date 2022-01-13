package controller.login;

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

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pw = SecurityPassword.encoding(request.getParameter("pw"));
		
		MemberDAO dao = new MemberDAO(); 
		int result = dao.memberIdPwCheck(id, pw);
		
		HttpSession session = request.getSession();
		
		if(result == 1) {//id, pw 일치
			session.setAttribute("userid", id);
			response.sendRedirect("main.do");
		}else if(result == 0) {//id는 검색 되지만 pw가 일치 x
			request.setAttribute("msg", "ID及びPASSWORDを確認してください");
			RequestDispatcher rd = request.getRequestDispatcher("login/login.jsp");
			rd.forward(request, response);
		}else if(result == -1) {//id가 없어서 검색이 안되는 경우
			request.setAttribute("msg", "会員登録してください");
			RequestDispatcher rd = request.getRequestDispatcher("login/login.jsp");
			rd.forward(request, response);
		}
		
	}

}

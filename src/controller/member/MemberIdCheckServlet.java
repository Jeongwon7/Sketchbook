package controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;

@WebServlet("/memberIdCheck.do")
public class MemberIdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberIdCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//요청
		String id = request.getParameter("id");
		
		//메서드 호출 - id 존재 여부 확인 메서드
		
		int result = new MemberDAO().getSelectIdCheck(id);
		
		//리턴 값 받기
		
		PrintWriter out = response.getWriter();
		out.print(result);
		
		//jsp로 보내기(비동기식이니까 포워딩 x)
	}

}

package controller.myblog;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MycmtDAO;

@WebServlet("/myblogCmtdelete.do")
public class myblogCmtdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public myblogCmtdelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		MycmtDAO cdao = MycmtDAO.getInstance();
		
		cdao.cmtDelete(num);
		int count = cdao.myblogCmtCount(bno);
		PrintWriter out = response.getWriter();
		out.print(count);
		
	}

}

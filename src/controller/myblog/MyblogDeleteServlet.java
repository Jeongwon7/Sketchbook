package controller.myblog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyblogDAO;

@WebServlet("/myblogdelete.do")
public class MyblogDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyblogDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String writer = request.getParameter("writer");
		
		MyblogDAO dao = new MyblogDAO();
		
		dao.MyblogCmtAllDelete(bno);
		dao.MyblogDelete(bno);
		
		response.sendRedirect("myblog.do?writer="+writer);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

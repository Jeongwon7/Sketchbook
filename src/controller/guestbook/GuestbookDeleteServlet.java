package controller.guestbook;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GuestbookDAO;


@WebServlet("/guestbookdelete.do")
public class GuestbookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GuestbookDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		GuestbookDAO dao = new GuestbookDAO();
		
		dao.guestbookDelete(bno);
		
		response.sendRedirect("guestbook.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package controller.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GuestbookDAO;
import dto.GuestbookDTO;

@WebServlet("/guestbookview.do")
public class GuestbookViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuestbookViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		GuestbookDAO dao = new GuestbookDAO();
		
		GuestbookDTO dto = dao.GuestbookView(bno);
		GuestbookDTO prev = dao.prevByBno(bno);
		GuestbookDTO next = dao.nextByBno(bno);
		
		request.setAttribute("view", dto);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		
		RequestDispatcher rd = request.getRequestDispatcher("guestbook/guestbookview.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

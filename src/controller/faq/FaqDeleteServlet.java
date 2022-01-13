package controller.faq;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FaqDAO;

@WebServlet("/faqdelete.do")
public class FaqDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FaqDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		System.out.println("bno: "+bno);
		String query = null;
		
		FaqDAO dao = FaqDAO.getInstance();
		dao.faqDelete(bno);
		int count = dao.FaqCount(query);
		PrintWriter out = response.getWriter();
		out.print(count);
	}

}

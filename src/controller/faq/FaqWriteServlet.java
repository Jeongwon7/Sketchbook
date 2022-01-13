package controller.faq;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FaqDAO;
import dto.FaqDTO;

@WebServlet("/faqwrite.do")
public class FaqWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FaqWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("faq/faq_write.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		FaqDTO dto = new FaqDTO();
		
		dto.setFa(request.getParameter("fa"));
		dto.setFq(request.getParameter("fq"));
		
		FaqDAO dao = FaqDAO.getInstance();
		dao.FaqInsert(dto);
		
		response.sendRedirect("faq.do");
	}

}

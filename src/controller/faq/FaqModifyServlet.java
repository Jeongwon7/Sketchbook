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

@WebServlet("/faqmodify.do")
public class FaqModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FaqModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		FaqDAO dao = FaqDAO.getInstance();
		
		FaqDTO fdto = dao.faqModify(bno);
		
		request.setAttribute("faqmodify", fdto);
		RequestDispatcher rd = request.getRequestDispatcher("faq/faqmodify.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String fq = request.getParameter("fq");
		String fa = request.getParameter("fa");
		
		FaqDAO dao = FaqDAO.getInstance();
		FaqDTO dto = new FaqDTO();
		dto.setBno(bno);
		dto.setFq(fq);
		dto.setFa(fa);
		
		dao.FaqModify(dto);
		
		response.sendRedirect("faq.do");
	}

}

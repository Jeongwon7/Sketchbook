package controller.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QnaDAO;

@WebServlet("/qnadelete.do")
public class QnaDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QnaDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qbno = Integer.parseInt(request.getParameter("qbno"));
		QnaDAO dao = QnaDAO.getInstance();
		dao.QnaDelete(qbno);
		response.sendRedirect("qna.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

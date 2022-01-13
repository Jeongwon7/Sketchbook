package controller.qna;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QnaDAO;
import dto.AnswerDTO;
import dto.QnaDTO;

@WebServlet("/qnaview.do")
public class QnaViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public QnaViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qbno = Integer.parseInt(request.getParameter("qbno"));
		
		QnaDAO dao = QnaDAO.getInstance();
		
		QnaDTO dto = dao.qnaselect(qbno);
		QnaDTO prev = dao.prevByBno(qbno);
		QnaDTO next = dao.nextByBno(qbno);
		int count = dao.AnswerCount(qbno);
		List<AnswerDTO> alist = dao.selectAnswer(qbno);
		
		request.setAttribute("view", dto);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		request.setAttribute("count", count);
		request.setAttribute("cmtList", alist);
		RequestDispatcher rd = request.getRequestDispatcher("qna/qnaview.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

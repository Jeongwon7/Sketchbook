package controller.qna;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MycmtDAO;
import dao.QnaDAO;
import dto.AnswerDTO;

@WebServlet("/answermodify.do")
public class AnswerModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnswerModifyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json; charset=utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String answertext = request.getParameter("answertext");
		
		AnswerDTO cvo = new AnswerDTO();
		
		cvo.setNum(num);
		cvo.setAnswertext(answertext);
		
		QnaDAO cdao = QnaDAO.getInstance();
		
		int result = cdao.QnaAnswerUpdate(cvo);
		
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}

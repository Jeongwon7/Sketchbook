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


@WebServlet("/answerdelete.do")
public class AnswerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AnswerDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		int qbno = Integer.parseInt(request.getParameter("qbno"));
		
		QnaDAO cdao = QnaDAO.getInstance();
		
		cdao.AnswerDelete(num);
		
		int count = cdao.AnswerCount(qbno);
		
		if(count == 0) {
			cdao.statusminus(qbno);
		}
		PrintWriter out = response.getWriter();
		out.print(count);//댓글수를 석세스로
	}
}

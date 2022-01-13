package controller.qna;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.QnaDAO;
import dto.AnswerDTO;
import dto.QnaDTO;


@WebServlet("/answer.do")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnswerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json; charset=utf-8");
		
		int qbno = Integer.parseInt(request.getParameter("qbno"));
		
		QnaDAO cdao = QnaDAO.getInstance();
		
		List<AnswerDTO> clist = cdao.selectAnswer(qbno);
		
		//gson 데이터 형식으로 데이터 리턴하는 방법
		
		Gson gson = new Gson();
		String cmtList = gson.toJson(clist);
		//System.out.println("cmtList: "+cmtList);
		PrintWriter out = response.getWriter();
		out.print(cmtList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json; charset=utf-8");
		
		int qbno = Integer.parseInt(request.getParameter("qbno"));
		String id = request.getParameter("writer");
		String answertext = request.getParameter("content");
		
		AnswerDTO avo = new AnswerDTO();
		
		avo.setQbno(qbno);
		avo.setId(id);
		avo.setAnswertext(answertext);
		
		QnaDAO cdao = QnaDAO.getInstance();
		
		cdao.AnswerInsert(avo);
		
		int count = cdao.AnswerCount(qbno);
		
		if(count != 0) {
			cdao.status(qbno);
		}
		
		PrintWriter out = response.getWriter();
		out.print(count);//댓글수를 석세스로
	}

}

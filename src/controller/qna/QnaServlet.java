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
import dto.QnaDTO;
import utility.Criteria;
import utility.PageVO;

@WebServlet("/qna.do")
public class QnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QnaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//검색
		String sel="";
		String word="";
		String query="";
		
		int pageNum = 1;
		int amount = 10;
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}
		
		if(request.getParameter("sel") != null && !request.getParameter("word").equals("")) {
			sel = request.getParameter("sel");//title 이나 content가 들어간다
			word = request.getParameter("word");
			query = sel + " like '%" + word + "%'";// title like '%코딩%'
		}
		
		Criteria cri = new Criteria();
		
		cri.setPageNum(pageNum);
		cri.setAmount(amount);
		cri.setType(sel);
		cri.setKeyword(word);
		
		QnaDAO qdao = QnaDAO.getInstance();
		
		List<QnaDTO> list = qdao.qnaSelectwithPaging(cri, query);
		int count = qdao.QnaCount(query);
		
		PageVO pvo = new PageVO(cri, count);
		
		request.setAttribute("pageMaker", pvo);
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		RequestDispatcher rd = request.getRequestDispatcher("qna/qna.jsp");
		rd.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

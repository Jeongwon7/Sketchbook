package controller.faq;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FaqDAO;
import dto.FaqDTO;
import utility.Criteria;
import utility.PageVO;

@WebServlet("/faq.do")
public class FaqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FaqServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sel="";
		String word="";
		String query="";
		
		int pageNum = 1;
		int amount = 5;
		
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
		
		FaqDAO dao = FaqDAO.getInstance();
		
		List<FaqDTO> list = dao.FaqSelectwithPaging(cri, query);
		
		int count = dao.FaqCount(query);
		
		PageVO pvo = new PageVO(cri, count); 
		
		request.setAttribute("pageMaker", pvo);
		request.setAttribute("faqlist", list);
		request.setAttribute("count", count);
		
		RequestDispatcher rd = request.getRequestDispatcher("faq/faq.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package controller.myblog;

import java.io.IOException;
import java.util.List;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyblogDAO;
import dto.MyblogDTO;
import utility.Criteria;
import utility.PageVO;

@WebServlet("/myblog.do")
public class MyblogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyblogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//writer 받기
		String writer = request.getParameter("writer");
		
		String sel = "";
		String word = "";
		String query= "";
		
		int pageNum=1;
		int amount=3;
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}
		
		if(request.getParameter("sel") != null && !request.getParameter("word").equals("")) {
			sel=request.getParameter("sel");
			word=request.getParameter("word");
			query= "lower("+sel+") like lower('%"+word+"%')";
		}
		
		Criteria cri = new Criteria();
		
		cri.setPageNum(pageNum);
		cri.setAmount(amount);
		cri.setType(sel);
		cri.setKeyword(word);
		
		MyblogDAO dao = new MyblogDAO();
		
		List<MyblogDTO> list = dao.getListwithPaging(cri, query, writer);
		List<MyblogDTO> plist = dao.getPopulerSelect(writer);
		
		int count=dao.myblogCount(query, writer);
		
		PageVO pvo = new PageVO(cri, count);
		
		request.setAttribute("pageMaker", pvo);
		request.setAttribute("myblogList", list);
		request.setAttribute("plist", plist);
		request.setAttribute("count", count);
		
		RequestDispatcher rd = request.getRequestDispatcher("myblog/myblog.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDAO;
import dto.MemberDTO;
import utility.Criteria;
import utility.PageVO;

@WebServlet("/adminlist.do")
public class AdminListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
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
		
		AdminDAO dao = AdminDAO.getInstance();
		
		List<MemberDTO> list = dao.getListwithPaging(cri, query);
		
		int count = dao.AdminCount(query);
		
		PageVO pvo = new PageVO(cri, count); 
		
		request.setAttribute("pageMaker", pvo);
		request.setAttribute("UserList", list);
		request.setAttribute("count", count);
		
		RequestDispatcher rd = request.getRequestDispatcher("admin/admin.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

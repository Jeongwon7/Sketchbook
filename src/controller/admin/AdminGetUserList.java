package controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.AdminDAO;
import dto.MemberDTO;
import utility.Criteria;

@WebServlet("/adminuserlist.do")
public class AdminGetUserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminGetUserList() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json; charset=utf-8");
		
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
		
		
		AdminDAO cdao = AdminDAO.getInstance();
		
		List<MemberDTO> ulist = cdao.getListwithPaging(cri, query);
		
		//gson 데이터 형식으로 데이터 리턴하는 방법
		
		Gson gson = new Gson();
		String userList = gson.toJson(ulist);
		//System.out.println("cmtList: "+cmtList);
		PrintWriter out = response.getWriter();
		out.print(userList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

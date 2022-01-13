package controller.myblog;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.MycmtDAO;
import dto.CmtDTO;
import utility.Criteria;
import utility.PageVO;

@WebServlet("/myblogCmt.do")
public class myblogCmt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public myblogCmt() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json; charset=utf-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		MycmtDAO cdao = MycmtDAO.getInstance();
		
		List<CmtDTO> clist = cdao.getCmtList(bno);
		
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
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String id = request.getParameter("writer");
		String replytext = request.getParameter("content");
		
		CmtDTO cvo = new CmtDTO();
		cvo.setBno(bno);
		cvo.setId(id);
		cvo.setReplytext(replytext);
		
		MycmtDAO cdao = MycmtDAO.getInstance();
		
		cdao.cmtInsert(cvo);
		int count = cdao.myblogCmtCount(bno);
		PrintWriter out = response.getWriter();
		out.print(count);//댓글수를 석세스로
	}

}

package controller.myblog;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MycmtDAO;
import dto.CmtDTO;

@WebServlet("/myblogcmtmodify.do")
public class myblogCmtmodify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public myblogCmtmodify() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json; charset=utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String replytext = request.getParameter("replytext");
		
		CmtDTO cvo = new CmtDTO();
		
		cvo.setNum(num);
		cvo.setReplytext(replytext);
		
		MycmtDAO cdao = MycmtDAO.getInstance();
		
		int result = cdao.myblogCmtUpdate(cvo);
		
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}

package controller.myblog;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyblogDAO;
import dao.MycmtDAO;
import dto.CmtDTO;
import dto.MyblogDTO;
import utility.Criteria;
import utility.PageVO;

@WebServlet("/myblogview.do")
public class MyblogViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyblogViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		String writer = request.getParameter("writer");
		
		MyblogDAO dao = new MyblogDAO();
		
		MyblogDTO dto =  dao.myblogView(bno);
		MyblogDTO prev =  dao.prevByBno(bno, writer);
		MyblogDTO next =  dao.nextByBno(bno, writer);
		
		MycmtDAO cdao = MycmtDAO.getInstance();
		
		List<CmtDTO> clist = cdao.getCmtList(bno);
		int count = cdao.myblogCmtCount(bno);
		
		
		request.setAttribute("view", dto);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		request.setAttribute("cmtList", clist);
		request.setAttribute("count", count);
		
		RequestDispatcher rd = request.getRequestDispatcher("myblog/myblogview.jsp");
		rd.forward(request, response);	
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

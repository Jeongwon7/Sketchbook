package controller.faq;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.FaqDAO;
import dto.FaqDTO;

@WebServlet("/getfaqList.do")
public class FaqGetListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FaqGetListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json; charset=utf-8");
		
		FaqDAO cdao = FaqDAO.getInstance();
		
		List<FaqDTO> faqlist = cdao.FaqSelect();
		
		//gson 데이터 형식으로 데이터 리턴하는 방법
		
		Gson gson = new Gson();
		String  faqList= gson.toJson(faqlist);
		//System.out.println("cmtList: "+cmtList);
		PrintWriter out = response.getWriter();
		out.print(faqList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

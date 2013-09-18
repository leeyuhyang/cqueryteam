package boardtest.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardtest.dao.userDao;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String act = request.getParameter("act");
		userDao dao = new userDao();
		String result = "";
		
		if("view".equals(act)){
			String tpage = request.getParameter("page");
			int page = Integer.parseInt(tpage);
			
			if(page < 1)
				page = 1;
				
			result = dao.view(page);
			
		} else if("detail".equals(act)){
			String tnum = request.getParameter("dnum");
			int num = Integer.parseInt(tnum);
			
			result = dao.detail(num);
			
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		
	}

}

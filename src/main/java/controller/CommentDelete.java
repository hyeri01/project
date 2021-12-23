package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommentDAO;

@WebServlet("/CommentDelete")
public class CommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CommentDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String testsession = (String)session.getAttribute("mem_id");
		
		
		String b_no = request.getParameter("b_no");
		String com_no = request.getParameter("com_no");
		String com_writer = request.getParameter("com_writer");
		
		CommentDAO dao = new CommentDAO();
		dao.commentDelete(com_no);
		
		response.sendRedirect("/project/ContentSelect?bod_no=" + b_no);
	
	
	
	
	}

}

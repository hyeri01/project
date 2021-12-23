package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommentDAO;

@WebServlet("/CommentInsert")
public class CommentInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentInsert() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String b_no = request.getParameter("b_no");
		String com_writer = request.getParameter("com_writer");
		System.out.println(com_writer);
		String com_content = request.getParameter("com_content");

		CommentDAO dao = new CommentDAO();
		dao.commentInsert(b_no, com_writer, com_content);
		
		response.sendRedirect("/project/ContentSelect?bod_no=" + b_no);
	
	
	
	}

}

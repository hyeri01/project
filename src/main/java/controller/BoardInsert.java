package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardDAO;

@WebServlet("/boardInsert")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* String line = System.getProperty("line.separator"); */

		
		String bod_title = request.getParameter("bod_title");
		String bod_writer = request.getParameter("bod_writer");
		String bod_content = request.getParameter("bod_content");
		String bod_password = request.getParameter("bod_password");
		/*
		 * bod_content = bod_content.replaceAll("\n", line);
		 */
		
		bod_title = XssReplace(bod_title);
		bod_content = XssReplace(bod_content);
		
		BoardDAO dao = new BoardDAO();
		dao.boardInsert(bod_title, bod_writer, bod_content, bod_password);
		
		response.sendRedirect("/project/BoardSelect");
		
		
	}
	
	public static String XssReplace(String str) {
        
	      str = str.replaceAll("&", "&amp;");
	      str = str.replaceAll("\"", "&quot;");
	      str = str.replaceAll("'", "&apos;");
	      str = str.replaceAll("<", "&lt;");
	      str = str.replaceAll(">", "&gt;");
	      str = str.replaceAll("\r", "<br>");
	      str = str.replaceAll("\n", "<p>");

	      return str;
	   }

}

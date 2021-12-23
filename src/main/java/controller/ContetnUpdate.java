package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardDAO;

@WebServlet("/ContetnUpdate")
public class ContetnUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContetnUpdate() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bod_no = request.getParameter("bod_no");
		String bod_title = request.getParameter("bod_title");
		String bod_content = request.getParameter("bod_content");
		String bod_password = request.getParameter("bod_password");
		bod_content = bod_content.replace("\r\n", "");
		
		BoardDAO dao = new BoardDAO();
		dao.boardUpdate(bod_no, bod_title, bod_content, bod_password);
		
		String page = "ContentSelect?bod_no=" + bod_no;
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}

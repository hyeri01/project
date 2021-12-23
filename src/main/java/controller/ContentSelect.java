package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDTO;
import service.BoardDAO;

@WebServlet("/ContentSelect")
public class ContentSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bod_no = request.getParameter("bod_no");
		BoardDAO dao = new BoardDAO();
		
		dao.updateViewHits(bod_no);
		
		List<BoardDTO> list = dao.contentSelect(bod_no);
		
		request.setAttribute("list", list);
		
		if(request.getParameter("modify") != null) {
			String page = "/update/contentUpdateForm.jsp?bod_no=" + bod_no;
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} else {
			String page = "/viewList/viewContentBoard.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
		
		
	
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bod_no = request.getParameter("bod_no");
		BoardDAO dao = new BoardDAO();
		
		List<BoardDTO> list = dao.contentSelect(bod_no);
		
		request.setAttribute("list", list);
		
		if(request.getParameter("modify") != null) {
			String page = "/project/update/contentUpdateForm.jsp?bod_no=" + bod_no;
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} else {
			String page = "/viewList/viewContentBoard.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}
	
	
	

}

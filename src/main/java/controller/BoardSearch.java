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

@WebServlet("/BoardSearch")
public class BoardSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardSearch() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String select = request.getParameter("select");
		String search = request.getParameter("search");
		
		BoardDAO dao = new BoardDAO();
		
		List<BoardDTO> list = dao.boardSearch(select, search);
		
		request.setAttribute("list", list);

		String page = "/viewList/viewBoardList.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	
	
	}

}

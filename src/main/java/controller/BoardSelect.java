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

@WebServlet("/BoardSelect")
public class BoardSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		
		List<BoardDTO> list = dao.boardSelect();
		request.setAttribute("list", list);

		String page = "/viewList/viewBoardList.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
		
		 
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

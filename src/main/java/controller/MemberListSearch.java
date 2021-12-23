package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberListDTO;
import service.MemberListDAO;

@WebServlet("/MemberListSearch")
public class MemberListSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberListSearch() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String select = request.getParameter("select");
		String search = request.getParameter("search");
		
		MemberListDAO dao = new MemberListDAO();
		
		List<MemberListDTO> list = dao.memberListSearch(select, search);
		
		request.setAttribute("list", list);

		String page = "/join/memberList.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}

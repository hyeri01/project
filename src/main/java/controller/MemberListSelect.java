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

/**
 * Servlet implementation class MemberListSelect
 */
@WebServlet("/MemberListSelect")
public class MemberListSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberListSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = request.getParameter("mem_id");
		String mem_auth = request.getParameter("auth");
		
		MemberListDAO dao = new MemberListDAO();
		
		List<MemberListDTO> m_list = dao.memberSelect(mem_id);

		request.setAttribute("m_list", m_list);
		
		if(mem_auth != "") {
			List<MemberListDTO> list = dao.memberListSelect();
			request.setAttribute("list", list);
			String page = "/join/memberList.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}else {
			if(request.getParameter("modify") != null) {
				String page = "/account/editAccount.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
			} else {
				String page = "/account/account.jsp?mem_id=" + mem_id;
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
				
			}
		}
		
		
		
		
		
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberListDTO;
import service.MemberListDAO;

@WebServlet("/loginChecker")
public class LoginChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = request.getParameter("mem_id");
		String mem_password = request.getParameter("mem_password");
		
		HttpSession session = request.getSession();
		
		MemberListDAO dao = new MemberListDAO();
		
		String res = dao.loginCheck(mem_id, mem_password);
		List<MemberListDTO> m_list = dao.memberSelect(mem_id);
		int mem_auth = m_list.get(0).getMem_auth();
		
		
		if(res.equals("success")) {
			if(mem_auth == 0) {
				session.setAttribute("mem_auth", mem_auth);
				session.setAttribute("mem_id", mem_id);
				response.getWriter().print(res);
			}else {
				session.setAttribute("mem_id", mem_id);
				response.getWriter().print(res);
			}
		}else {
			response.getWriter().print(res);
		}
		

	
	}

}

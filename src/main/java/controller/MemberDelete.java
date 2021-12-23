package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberListDAO;

@WebServlet("/MemberDelete")
public class MemberDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberDelete() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = request.getParameter("mem_id");
		String[] chkArr = request.getParameterValues("chkArr");
		
		
		if(chkArr != null) {
			MemberListDAO dao = new MemberListDAO();
			dao.memberListDelete(chkArr);
		}else {
			MemberListDAO dao = new MemberListDAO(); dao.memberDelete(mem_id);
			 HttpSession session = request.getSession(); session.invalidate();
			 response.sendRedirect("/project/home.jsp");
		}
		
	}

}

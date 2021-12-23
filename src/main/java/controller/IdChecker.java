package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberListDAO;

@WebServlet("/idCheck")
public class IdChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IdChecker() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_id = request.getParameter("mem_id");
		
		MemberListDAO dao = new MemberListDAO();
		int res = dao.idCheck(mem_id);
		response.getWriter().println(res);
	
	}

}

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberListDAO;

@WebServlet("/MemberUpdate")
public class MemberUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mem_password = request.getParameter("mem_password");
		String mem_name = request.getParameter("mem_name");
		String mem_tel = request.getParameter("mem_tel");
		String mem_email_id = request.getParameter("mem_email_id");
		String mem_email_email = request.getParameter("mem_email_email");
		String mem_email = mem_email_id + "@" + mem_email_email;
		String mem_bday = request.getParameter("mem_bday");
		String mem_id = request.getParameter("mem_id");
		
		MemberListDAO dao = new MemberListDAO();
		dao.memberUpdate(mem_id, mem_password, mem_name, mem_tel, mem_email, mem_bday);
		
		String page = "MemberListSelect?mem_id=" + mem_id + "&auth=";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
		
	}

}

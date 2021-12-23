package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardDAO;

@WebServlet("/ContentPwdCheck")
public class ContentPwdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContentPwdCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String bod_password = request.getParameter("bod_password");
    	
    	
    	BoardDAO dao = new BoardDAO();
    	int res = dao.pwdCheck(bod_password);
    	
    	if(res == 1) {
    		response.getWriter().println(res);
    	}else {
    		response.getWriter().println(res);
    	}
    	
    	
    
    
    }

}

package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.CommentDTO;
import service.CommentDAO;

@WebServlet("/CommentSelect")
public class CommentSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentSelect() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String b_no = request.getParameter("b_no");
		CommentDAO dao = new CommentDAO();
		List<CommentDTO> list = dao.commentSelect(b_no);
		
		String cm_list = new Gson().toJson(list);
		
		response.getWriter().println(cm_list);
	}

}

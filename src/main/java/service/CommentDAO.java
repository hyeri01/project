package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.CommentDTO;

public class CommentDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	/* Insert */
	public void commentInsert(String b_no, String com_writer, String com_content) {
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");
			
			conn = ds.getConnection();
			
			String sql = "insert into comment(b_no, com_writer, com_content) values(?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_no);
			pstmt.setString(2, com_writer);
			pstmt.setString(3, com_content);
			
			
			pstmt.executeUpdate();
			
			
		}catch (SQLException | NamingException e) {
			System.out.println(e.getMessage());
			System.err.println("sql문 오류");
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	/* Select */
	public List<CommentDTO> commentSelect(String b_no){
		
		conn = null;
		pstmt = null;
		rs = null;
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

			conn = ds.getConnection();

			String sql = "select * from comment where b_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_no);
			
			rs = pstmt.executeQuery();
			
			List<CommentDTO> list = new ArrayList<CommentDTO>();
			
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setCom_no(rs.getString("com_no"));
				dto.setCom_writer(rs.getString("com_writer"));
				dto.setCom_reg_date(rs.getString("com_reg_date"));
				dto.setCom_content(rs.getString("com_content"));
				dto.setB_no(rs.getInt("b_no"));
				list.add(dto);
							
			}
			
			return list;
			
		} catch (SQLException | NamingException e) {
			System.out.println(e.getMessage());
			System.out.println("sql문 오류");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;

	}
	
	public void commentDelete(String com_no) {
		
		conn = null;
		pstmt = null;
		
		
		try{
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");
			
			conn = ds.getConnection();
			
			String sql = "delete from comment where com_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, com_no);
			
			pstmt.executeUpdate();
			
			
			
			
		} catch (SQLException | NamingException e) {
			System.out.println(e.getMessage());
			System.out.println("sql문 오류");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

}

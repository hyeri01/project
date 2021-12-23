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

import model.BoardDTO;

public class BoardDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public void init(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {

	      int count = 0;
	      String sql = "select count(*) as 'count' from board";

	      try {
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();

	         if (rs.next()) {
	            count = rs.getInt("count");
	         }

	         String sqlList[] = { "SET @CNT = 0",
	               "UPDATE board SET board.bod_no = @CNT:=@CNT+1", 
	               "ALTER TABLE board AUTO_INCREMENT=" + (count + 1) };

	         for (int i = 0; i < 3; i++) {
	            pstmt = conn.prepareStatement(sqlList[i]);
	            pstmt.executeUpdate();
	         }

	      } finally {
	         if (rs != null) {
	            rs.close();
	         }
	      }
	   }
	
	
	
	/* Insert */
	public void boardInsert(String bod_title, String bod_writer, String bod_content, String bod_password) {
		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

			conn = ds.getConnection();

			String sql = "insert into board(bod_title, bod_writer, bod_content, bod_password) values(?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bod_title);
			pstmt.setString(2, bod_writer);
			pstmt.setString(3, bod_content);
			pstmt.setString(4, bod_password);

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
	
	
	
	/* Delete */
	public void boardDelete(String bod_no) {
		
		conn = null;
		pstmt = null;
		
		
		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

			conn = ds.getConnection();

			String sql = "delete from board where bod_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bod_no);

			pstmt.executeUpdate();
			
			init(conn, pstmt, rs);
			
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
	
	
	public void boardSelectDelete(String[] chkArr) {
		
		conn = null;
		pstmt = null;
		
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");
			
			conn = ds.getConnection();
			
			String sql = "delete from board where bod_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			for(int i = 0; i<chkArr.length; i++) {
				
				pstmt.setString(1, chkArr[i]);
				pstmt.addBatch();
				pstmt.executeUpdate();
				
			}
			
			
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
	
	
	/* Update */
	public void boardUpdate(String bod_no, String bod_title, String bod_content, String bod_password) {
		
		conn = null;
		pstmt = null;
		rs = null;
		
		try{
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");
			
			conn = ds.getConnection();
			
			String sql = "update board set bod_title = ?, bod_content = ?, bod_password = ? where bod_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bod_title);
			pstmt.setString(2, bod_content);
			pstmt.setString(3, bod_password);
			pstmt.setString(4, bod_no);
			
			pstmt.executeUpdate();
			
		}catch (SQLException | NamingException e) {
			System.out.println(e.getMessage());
			System.out.println("update 실패");
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
	
	   public void updateViewHits(String bod_no){
		      
		      conn=null;
		      pstmt=null;

		      try{
		         Context init = new InitialContext();
		         DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");         
		         conn = ds.getConnection();
		                           
		         String sql = "update board set bod_hits = bod_hits+1 where bod_no =?";
		         
		         pstmt = conn.prepareStatement(sql);
		         
		         pstmt.setString(1, bod_no);
		         
		               
		         pstmt.executeUpdate();   
		                  
		         
		      }catch(Exception ex){
		         System.out.println("updateViews 실패");
		         ex.printStackTrace();
		      }finally {
		         try {
		            pstmt.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		         try {
		            conn.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		      }
		      

		      
		   }
	
	
	
	
	/* 게시판 목록 */
	public List<BoardDTO> boardSelect(){

		conn = null;
		pstmt = null;
		rs = null;
		
		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

			conn = ds.getConnection();

			String sql = "select *, count(com_no) from board left join comment on bod_no = b_no group by bod_no order by bod_no desc";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			
			List<BoardDTO> list = new ArrayList<BoardDTO>();
			
			while(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				dto.setBod_no(rs.getInt("bod_no"));
				dto.setBod_title(rs.getString("bod_title"));
				dto.setBod_writer(rs.getString("bod_writer"));
				dto.setBod_password(rs.getString("bod_password"));
				dto.setBod_reg_date(rs.getString("bod_reg_date"));
				dto.setBod_hits(rs.getInt("bod_hits"));
				dto.setCom_count(rs.getString("count(com_no)"));
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
	
	/* 게시판 search */
	public List<BoardDTO> boardSearch(String select, String search) {
		conn = null;
		pstmt = null;
		rs = null;
		
		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

			conn = ds.getConnection();

			String sql = "select *, count(com_no) from board left join comment on bod_no = b_no where " 
						+ select + " like '%" + search + "%' group by bod_no order by bod_no desc";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			
			List<BoardDTO> list = new ArrayList<BoardDTO>();
			
			while(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				dto.setBod_no(rs.getInt("bod_no"));
				dto.setBod_title(rs.getString("bod_title"));
				dto.setBod_writer(rs.getString("bod_writer"));
				dto.setBod_password(rs.getString("bod_password"));
				dto.setBod_reg_date(rs.getString("bod_reg_date"));
				dto.setBod_hits(rs.getInt("bod_hits"));
				dto.setCom_count(rs.getString("count(com_no)"));
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
	
	
	/* 게시판 비밀번호 체크 */
	public int pwdCheck(String bod_password) {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");
			
			conn = ds.getConnection();
			
			String sql = "select * from board where bod_password = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bod_password);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return 1;
			}else if (bod_password.equals("")) {
				return 2;
			}else {
				return 3;
			}
		} catch(Exception e) {
			System.out.println("pwd 비교 실패");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return -1;
	}
	
	/* 게시판 본문 내용 */
	public List<BoardDTO> contentSelect(String bod_no){
		
		conn = null;
		pstmt = null;
		rs = null;
		
		
		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

			conn = ds.getConnection();

			String sql = "select * from board where bod_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bod_no);
			rs = pstmt.executeQuery();
			
			List<BoardDTO> list = new ArrayList<BoardDTO>();
			
			if(rs.next()) {	
				BoardDTO dto = new BoardDTO();
				dto.setBod_no(rs.getInt("bod_no"));
				dto.setBod_title(rs.getString("bod_title"));
				dto.setBod_writer(rs.getString("bod_writer"));
				dto.setBod_password(rs.getString("bod_password"));
				dto.setBod_content(rs.getString("bod_content"));
				dto.setBod_reg_date(rs.getString("bod_reg_date"));
				dto.setBod_hits(rs.getInt("bod_hits"));
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
	
	
	
	
	
	

}

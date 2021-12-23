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

import model.MemberListDTO;


public class MemberListDAO {

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
	                  "UPDATE board SET bod_no = @CNT:=@CNT+1", 
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
	   
	   public void init2(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {

	         int count = 0;
	         String sql = "select count(*) as 'count' from comment";

	         try {
	            pstmt = conn.prepareStatement(sql);
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	               count = rs.getInt("count");
	            }

	            String sqlList[] = { "SET @CNT = 0",
	                  "UPDATE comment SET com_no = @CNT:=@CNT+1", 
	                  "ALTER TABLE comment AUTO_INCREMENT=" + (count + 1) };

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
	
	
	public void joinInsert(String mem_id, String mem_password, String mem_name, String mem_tel, String mem_email, String mem_bday){
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");
			
			conn = ds.getConnection();
			
			String sql = "insert into member_list(mem_id, mem_password, mem_name, mem_tel, mem_email, mem_bday) values(?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem_id);
			pstmt.setString(2, mem_password);
			pstmt.setString(3, mem_name);
			pstmt.setString(4, mem_tel);
			pstmt.setString(5, mem_email);
			pstmt.setString(6, mem_bday);
			
			pstmt.executeUpdate();
		} catch(SQLException | NamingException e) {
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
	
	
	public List<MemberListDTO> memberListSelect(){
		
		conn = null;
		pstmt = null;
		rs = null;
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");
			
			conn = ds.getConnection();
			
			String sql = "select * from member_list";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			List<MemberListDTO> list = new ArrayList<MemberListDTO>();
			
			while(rs.next()) {
				MemberListDTO dto = new MemberListDTO();
				dto.setMem_no(rs.getInt("mem_no"));
				dto.setMem_id(rs.getString("mem_id"));
				dto.setMem_password(rs.getString("mem_password"));
				dto.setMem_name(rs.getString("mem_name"));
				dto.setMem_tel(rs.getString("mem_tel"));
				dto.setMem_email(rs.getString("mem_email"));
				dto.setMem_bday(rs.getString("mem_bday"));
				dto.setMem_reg_date(rs.getString("mem_reg_date"));
				dto.setMem_auth(rs.getInt("mem_auth"));
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
	
	
	public List<MemberListDTO> memberSelect(String mem_id){
		
		conn = null;
		pstmt = null;
		rs = null;
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");
			
			conn = ds.getConnection();
			
			String sql = "select * from member_list where mem_id = ?";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			
			List<MemberListDTO> list = new ArrayList<MemberListDTO>();
			
			while(rs.next()) {
				MemberListDTO dto = new MemberListDTO();
				dto.setMem_no(rs.getInt("mem_no"));
				dto.setMem_id(rs.getString("mem_id"));
				dto.setMem_password(rs.getString("mem_password"));
				dto.setMem_name(rs.getString("mem_name"));
				dto.setMem_tel(rs.getString("mem_tel"));
				dto.setMem_email(rs.getString("mem_email"));
				dto.setMem_bday(rs.getString("mem_bday"));
				dto.setMem_reg_date(rs.getString("mem_reg_date"));
				dto.setMem_auth(rs.getInt("mem_auth"));
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
	
	
	public List<MemberListDTO> memberListSearch(String select, String search) {
		conn = null;
		pstmt = null;
		rs = null;
		
		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

			conn = ds.getConnection();

			String sql = "select * from member_list where " + select + " like '%" + search + "%'";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			List<MemberListDTO> list = new ArrayList<MemberListDTO>();
			
			while(rs.next()) {
				MemberListDTO dto = new MemberListDTO();
				dto.setMem_no(rs.getInt("mem_no"));
				dto.setMem_id(rs.getString("mem_id"));
				dto.setMem_password(rs.getString("mem_password"));
				dto.setMem_name(rs.getString("mem_name"));
				dto.setMem_tel(rs.getString("mem_tel"));
				dto.setMem_email(rs.getString("mem_email"));
				dto.setMem_bday(rs.getString("mem_bday"));
				dto.setMem_reg_date(rs.getString("mem_reg_date"));
				dto.setMem_auth(rs.getInt("mem_auth"));
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
	
	
		
	public void memberUpdate(String mem_id, String mem_password, String mem_name, String mem_tel, String mem_email, String mem_bday) {
		
		conn = null;
		pstmt = null;
		
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");
			
			conn = ds.getConnection();
			
			String sql = "update member_list set mem_password = ?, mem_name = ?, mem_tel = ?, mem_email = ?, mem_bday = ? where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem_password);
			pstmt.setString(2, mem_name);
			pstmt.setString(3, mem_tel);
			pstmt.setString(4, mem_email);
			pstmt.setString(5, mem_bday);
			pstmt.setString(6, mem_id);
			
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("update 실패");
			e.printStackTrace();
		} finally {
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
	
	
	public void memberListDelete(String[] chkArr) {
		
		conn = null;
		pstmt = null;
		
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");
			
			conn = ds.getConnection();
			
			String sql = "delete from member_list where mem_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			
			for(int i = 0; i<chkArr.length; i++) {
			
				pstmt.setString(1, chkArr[i]);
				pstmt.addBatch();
				pstmt.executeUpdate(); 
				
			}
			
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("delete 실패");
			e.printStackTrace();
		} finally {
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
	
	
	public void memberDelete(String mem_id) {
		
		conn = null;
		pstmt = null;
		
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");
			
			conn = ds.getConnection();
			
			String sql = "delete from member_list where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem_id);
			
			pstmt.executeUpdate();
			
			init(conn, pstmt, rs);
			init2(conn, pstmt, rs);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("delete 실패");
			e.printStackTrace();
		} finally {
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

	
	public String loginCheck(String mem_id, String mem_password) {
		
		String res = "";
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");
			
			conn = ds.getConnection();
			
			String sql = "select * from member_list where mem_id = ? and mem_password = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, mem_password);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {

				if(mem_id.equals(rs.getString("mem_id"))) {
					if(mem_password.equals(rs.getString("mem_password"))) {
						res = "success";
						return res;
					}else {
						res = "Check pwd";
						return res;
					}
				}
					
			} else {
				res = "Check Id";
				return res;
			}
			
			
			
			
			
		} catch(Exception e) {
			System.out.println("id/pw 비교 실패");
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
		return res;
	}
		
	
	
	
	
	public int idCheck(String mem_id) {
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");
			
			conn = ds.getConnection();
			
			String sql = "select * from member_list where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return 1;
			}else if(mem_id.equals("")) {
				return 2;
			}else {
				return 3;
			}
			
			
		} catch(Exception e) {
			System.out.println("id 비교 실패");
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
	
}
	
	

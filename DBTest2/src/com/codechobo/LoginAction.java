package com.codechobo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}

	boolean loginCheck(String id,String pw) {
		
		String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";

		String DB_USER = "student"; // DB의 userid와 pwd를 알맞게 변경

		String DB_PASSWORD = "1234";

		Connection conn = null;

		Statement stmt = null;

		ResultSet rs = null;

		String query = "select * from user_info where USER_ID = ?"; // 시스템의 날짜를 출력한다.

		try {

			// 드라이버를 로딩한다.

			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // 데이터베이스의 연결을 설정한다.
			rs = stmt.executeQuery(query);

			while (rs.next()) {

				String DB_id = rs.getString("USER_ID");
				String DB_pw = rs.getString("PASSWORD");
				
				if(DB_id.equals(id)&&DB_pw.equals(pw)) 
					return true;
			}
			
		} catch ( Exception e ) {
			
			System.out.println("불러오는데실패");
			e.printStackTrace();

		} finally {

			if (rs != null) { try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } } 
			if (stmt != null) { try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); } } 
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } } 

		}
		return false;

	} // main()의 끝
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String id = request.getParameter("id");
		String pw = request.getParameter("pwd");

//		HttpSession session = request.getSession();
		
		if(loginCheck(id, pw)) {
//			session.setAttribute("id", id);
			response.sendRedirect("/");
		}
		else {
			response.sendRedirect("/loginForm.jsp");
		}

	}
}




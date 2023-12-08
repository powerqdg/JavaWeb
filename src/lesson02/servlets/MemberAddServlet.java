package lesson02.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>회원추가</title></head>");
		out.println("<body><h1>회원추가</h1>");
		out.println("<form action='add' method='post'>");
		out.println("<label for='mname'>이름: </label><input type='text' id='mname' name='mname'><br>");
		out.println("<label for='email'>이메일: </label><input type='text' id='email' name='email'><br>");
		out.println("<label for='password'>비밀번호: </label><input type='password' id='password' name='password'><br>");
		out.println("<input type='submit' value='추가'><input type='reset' value='취소'>");
		out.println("</form>");
		out.println("</body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			stmt = conn.prepareStatement("INSERT INTO MEMBERS VALUES(MNO_SEQ.nextVal, ?, ?, ?, SYSDATE, SYSDATE)");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("password"));
			stmt.setString(3, request.getParameter("mname"));
			int cnt = stmt.executeUpdate();
			
			if (cnt > 0) {
				response.sendRedirect("list");
			} else {
				throw new Error("등록하는데 문제가 발생했습니다.");
			}
			/*
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원 등록 결과</title></head>");
			if (cnt > 0) {
				out.println("<body><h1>등록에 성공했습니다.</h1>");
			} else {
				out.println("<body><h1>등록에 실패했습니다.</h1>");
			}
			out.println("</body></html>");
			
			response.setHeader("Refresh", "5;url=list");
			*/
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (conn != null) conn.close();} catch (Exception e) {}
		}
		
	}
}

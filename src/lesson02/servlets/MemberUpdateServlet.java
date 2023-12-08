package lesson02.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			stmt = conn.prepareStatement("SELECT MNO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE FROM MEMBERS WHERE MNO = ?");
			stmt.setInt(1, Integer.parseInt(request.getParameter("mno")));
			rs = stmt.executeQuery();
			rs.next();
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원수정</title></head>");
			out.println("<body><h1>회원수정</h1>");
			out.println("<form action='update' method='post'>");
			out.println("<label for='mno'>번호: </label><input type='text' id='mno' name='mno' value='" + rs.getInt("MNO") + "' readonly><br>");
			out.println("<label for='mname'>이름: </label><input type='text' id='mname' name='mname' value='" + rs.getString("MNAME") + "'><br>");
			out.println("<label for='email'>이메일: </label><input type='text' id='email' name='email' value='" + rs.getString("EMAIL") + "'><br>");
			out.println("<span>가입일: " + rs.getDate("CRE_DATE") + "</span><br>");
			out.println("<span>수정일: " + rs.getDate("MOD_DATE") + "</span><br>");
			out.println("<input type='submit' value='수정'><input type='reset' value='취소'>");
			out.println("</form>");
			out.println("</body></html>");
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (conn != null) conn.close();} catch (Exception e) {}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			stmt = conn.prepareStatement("UPDATE MEMBERS SET MNAME = ?, EMAIL = ?, MOD_DATE = SYSDATE WHERE MNO = ?");
			stmt.setString(1, request.getParameter("mname"));
			stmt.setString(2, request.getParameter("email"));
			stmt.setInt(3, Integer.parseInt(request.getParameter("mno")));
			int cnt = stmt.executeUpdate();
			
			if (cnt > 0) {
				response.sendRedirect("list");
			} else {
				throw new Error("수정하는데 문제가 발생했습니다.");
			}
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (conn != null) conn.close();} catch (Exception e) {}
		}
	}
}

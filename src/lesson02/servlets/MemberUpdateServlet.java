package lesson02.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson02.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			stmt = conn.prepareStatement("SELECT MNO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE FROM MEMBERS WHERE MNO = ?");
			stmt.setInt(1, Integer.parseInt(request.getParameter("mno")));
			rs = stmt.executeQuery();
			rs.next();
			
			response.setContentType("text/html;charset=UTF-8");
			request.setAttribute("member", new Member()
					.setMno(rs.getInt("MNO"))
					.setMname(rs.getString("MNAME"))
					.setEmail(rs.getString("EMAIL"))
					.setCreDate(rs.getDate(("CRE_DATE")))
					.setModDate(rs.getDate("MOD_DATE")));
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberUpdate.jsp");
			rd.include(request, response);
		} catch (Exception e) {
			// throw new ServletException(e);
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8");
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			stmt = conn.prepareStatement("UPDATE MEMBERS SET MNAME = ?, EMAIL = ?, MOD_DATE = SYSDATE WHERE MNO = ?");
			stmt.setString(1, request.getParameter("mname"));
			stmt.setString(2, request.getParameter("email"));
			stmt.setInt(3, Integer.parseInt(request.getParameter("mno")));
			stmt.executeUpdate();
			
			response.sendRedirect("list");
		} catch (Exception e) {
			// throw new ServletException(e);
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
		}
	}
}

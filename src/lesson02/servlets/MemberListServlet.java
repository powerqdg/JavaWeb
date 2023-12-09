package lesson02.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson02.vo.Member;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT MNO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE FROM MEMBERS ORDER BY MNO");
			response.setContentType("text/html;charset=UTF-8");
			ArrayList<Member> members = new ArrayList<Member>();
			
			while (rs.next()) {
				members.add(new Member()
					.setMno(rs.getInt("MNO"))
					.setEmail(rs.getString("EMAIL"))
					.setMname(rs.getString("MNAME"))
					.setCreDate(rs.getDate("CRE_DATE"))
					.setModDate(rs.getDate("MOD_DATE")));
			}
			
			request.setAttribute("members", members);
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
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
}

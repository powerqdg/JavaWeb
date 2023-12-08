package lesson02.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
		out.println("<form action='add'>");
		out.println("<label for='mname'>이름: </label><input type='text' id='mname' name='mname'><br>");
		out.println("<label for='email'>이메일: </label><input type='text' id='email' name='email'><br>");
		out.println("<label for='password'>비밀번호: </label><input type='password' id='password' name='password'><br>");
		out.println("<input type='submit' value='추가'><input type='reset' value='취소'>");
		out.println("</form>");
		out.println("</body></html>");
	}
}

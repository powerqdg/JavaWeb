package lesson02.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson02.vo.Member;


@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String servletPath = request.getServletPath();
		
		try {
			String pageControllPath = null;
			
			if ("/member/list.do".equals(servletPath)) {
				pageControllPath = "/member/list";
			} else if ("/member/add.do".equals(servletPath)) {
				pageControllPath = "/member/add";
				if (request.getParameter("email") != null) {
					request.setAttribute("member", new Member()
							.setEmail(request.getParameter("email"))
							.setMname(request.getParameter("mname"))
							.setPassword(request.getParameter("password")));
				}
			} else if ("/member/update.do".equals(servletPath)) {
				pageControllPath = "/member/update";
				if (request.getParameter("email") == null) {
					request.setAttribute("member", new Member()
							.setMno(Integer.parseInt(request.getParameter("mno"))));
				} else {
					request.setAttribute("member", new Member()
							.setMno(Integer.parseInt(request.getParameter("mno")))
							.setEmail(request.getParameter("email"))
							.setMname(request.getParameter("mname")));
				}
			} else if ("/member/delete.do".equals(servletPath)) {
				pageControllPath = "/member/delete";
			} else if ("/auth/login.do".equals(servletPath)) {
				pageControllPath = "/auth/login";
			} else if ("/auth/logout.do".equals(servletPath)) {
				pageControllPath = "/auth/logout";
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(pageControllPath);
			rd.include(request, response);
			
			String viewUrl = (String)request.getAttribute("viewUrl");
			if (viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}

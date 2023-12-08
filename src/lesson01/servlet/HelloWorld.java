package lesson01.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWorld implements Servlet {
	ServletConfig config;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
		this.config = config;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("service");
	}

	@Override
	public void destroy() {
		System.out.println("destroy");
	}
	
	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo");
		return "";
	}
	
	@Override
	public ServletConfig getServletConfig() {
		System.out.println("ServletConfig");
		return this.config;
	}

}

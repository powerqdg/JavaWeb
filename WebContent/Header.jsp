<%@ page import="lesson02.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="member" scope="session" class="lesson02.vo.Member"/>
<div style="background-color:#00008b;color:#ffffff;height:20px;padding:5px;">
SPMS(Simple Project Management System)
<% if (member.getMname() != null) { %>
<span style="float: right;">
<%=member.getMname() %>
<a href="../auth/logout" style="color:#ffffff">로그아웃</a>
</span>
<% } else { %>
<span style="float: right;">
<a href="../auth/login" style="color:#ffffff">로그인</a>
</span>
<% } %>
</div>
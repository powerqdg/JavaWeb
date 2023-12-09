<%@ page import="lesson02.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원목록</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h1>회원목록</h1>
<p><a href="add">신규회원</a></p>
<jsp:useBean id="members" scope="request" class="java.util.ArrayList" type="java.util.ArrayList<lesson02.vo.Member>"/>
<% for (Member member : members) { %>
<%=member.getMno() %>,
<a href="update?mno=<%=member.getMno() %>"><%=member.getMname() %></a>, 
<%=member.getEmail() %>,
<%=member.getCreDate() %>,
<%=member.getModDate() %>
<a href="delete?mno=<%=member.getMno() %>"> [삭제]</a><br>  
<% } %>
<jsp:include page="/Tail.jsp"/>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<c:forEach var="member" items="${requestScope.members}">
${member.mno}, 
<a href="update?mno=${member.mno}">${member.mname}</a>,
${member.email}, 
${member.creDate}, 
${member.modDate}, 
<a href="delete?mno=${member.mno}">[삭제]</a><br>  
</c:forEach>
<jsp:include page="/Tail.jsp"/>
</body>
</html>
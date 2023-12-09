<%@ page import="lesson02.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원수정</title>
</head>
<body>
<h1>회원수정</h1>
<% Member member = (Member)request.getAttribute("member"); %>
<form action="update" method="post">
<label for="mno">번호: <%=member.getMno() %></label><br>
<label for="mname">이름: </label>
<input id="mname" type="text" name="mname" value=<%=member.getMname() %>><br>
<label for="email">이메일: </label>
<input id="email" type="text" name="email" value=<%=member.getEmail() %>><br>
<label for="email">가입일: <%=member.getCreDate() %></label><br>
<label for="email">수정일: <%=member.getModDate() %></label><br>
<input type="submit" value="수정">
<input type="reset" value="취소">
</form>
</body>
</html>
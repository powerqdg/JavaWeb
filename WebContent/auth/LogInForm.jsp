<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h1>로그인</h1>
<form action="login.do" method="post">
<label for="email">이메일: </label>
<input id="email" type="text" name="email"><br>
<label for="password">비밀번호: </label>
<input id="password" type="password" name="password"><br>
<input type="submit" value="로그인">
<input type="reset" value="취소">
</form>
<jsp:include page="/Tail.jsp"/>
</body>
</html>
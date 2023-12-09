<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원등록</title>
</head>
<body>
<h1>회원등록</h1>
<form action="add" method="post">
<label for="mname">이름: </label>
<input id="mname" type="text" name="mname"><br>
<label for="email">이메일: </label>
<input id="email" type="text" name="email"><br>
<label for="password">비밀번호: </label>
<input id="password" type="password" name="password"><br>
<input type="submit" value="등록">
<input type="reset" value="취소">
</form>
</body>
</html>
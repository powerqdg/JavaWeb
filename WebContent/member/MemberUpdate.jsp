<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원수정</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h1>회원수정</h1>
<form action="update" method="post">
<label for="mno">번호: </label>
<input id="mno" type="text" name="mno" value='${requestScope.member.mno}' readonly><br>
<label for="mname">이름: </label>
<input id="mname" type="text" name="mname" value='${requestScope.member.mname}'><br>
<label for="email">이메일: </label>
<input id="email" type="text" name="email" value='${requestScope.member.email}'><br>
<label>가입일: ${requestScope.member.creDate}</label><br>
<label>수정일: ${requestScope.member.modDate}</label><br>
<input type="submit" value="수정">
<input type="reset" value="취소">
</form>
<jsp:include page="/Tail.jsp"/>
</body>
</html>
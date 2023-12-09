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
<jsp:useBean id="member" scope="request" class="lesson02.vo.Member"/>
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
<jsp:include page="/Tail.jsp"/>
</body>
</html>
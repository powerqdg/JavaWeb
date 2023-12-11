<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="background-color:#00008b;color:#ffffff;height:20px;padding:5px;">
SPMS(Simple Project Management System)
<span style="float: right;">
<c:choose>
  <c:when test="${empty sessionScope.member.email}">
    <a href="../auth/login.do" style="color:#ffffff">로그인</a>
  </c:when>
  <c:otherwise>
    ${sessionScope.member.mname}
    <a href="../auth/logout.do" style="color:#ffffff">로그아웃</a>
  </c:otherwise>
</c:choose>
</span>
</div>
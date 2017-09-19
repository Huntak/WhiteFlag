<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%
	String errorMessage = (String)request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error.jsp</title>
</head>
<body>
	<h1>에러 발생 : <%= /* request.getAttribute("message") */ errorMessage %></h1>
	<a href="#" onclick="javascript:history.go(-1)">이전 페이지로</a>
</body>
</html>
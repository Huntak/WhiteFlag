<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <% String message = (String) request.getAttribute("message"); %>
  
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>공지Error</title>
</head>
<body>
<h1>공지 서비스 에러 발생</h1>
<h2><%= message  %></h2>
<h2><a href="/semip/index.jsp">시작페이지로 이동</a></h2>
</body>
</html>
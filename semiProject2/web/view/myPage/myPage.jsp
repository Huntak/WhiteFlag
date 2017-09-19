<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import = "member.model.vo.Member" %>
<%
  	Member loginUser = (Member)session.getAttribute("loginUser");
	Member member = (Member)request.getAttribute("member");
%>
<!DOCTYPE html >
<html>
<title>Insert title here</title>
<head>
<meta charset=UTF-8">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js"></script>
<link rel="stylesheet" type = "text/css" href = "/semi/view/myPage/css/mypage2.css?ver=4">
<script type="text/javascript"  >
document.oncontextmenu = function(e){
    if(e){
     e.preventDefault();
    }
    else{
     event.keyCode = 0;
     event.returnValue = false;
    }
}
</script>
 </head>
 
 <body>
<header></header>

<div id="tag">ㅣMY SHOOPING</div>
<%if(loginUser != null){%>
 <div id="div_welcome" align="center">
저희 쇼핑몰을 이용해주셔서 감사합니다 <font color="gray">
<%= loginUser.getMNAME() %></font> 님은, <font color="gray">[<%= loginUser.getGRADE() %>]</font> 회원이십니다.
</div>
<%}else{%>
 <div id="div_welcome" align="center">
저희 쇼핑몰을 이용해주셔서 감사합니다 . <a href="/semi/view/member/loginForm.jsp"><font color="red">로그인</font></a> 후 이용해 주시기 바랍니다.
</div>
<%}%>

 
 	<div id="div_link" >
 	<ul>
 		<%if(loginUser != null){%>
 		<a href="/semi/olist?mid=<%= loginUser.getMID() %>"><li class="li_link" align="center"><b>ORDERLIST</b></li></a>
 		<a href="/semi/view/member/editMemberInfo.jsp"><li class="li_link" align="center"><b>MYINFO</b></li></a>
 		<a href="/semi/myList?userId=<%=loginUser.getMID() %>"><li class="li_link" align="center"><b>BORDER</b></li></a>
 		<a href="/semi/view/member/deleteMember.jsp"><li class="li_link" align="center" ><b>MEMBER<br>CANCELLATION</b></li></a>
 		<a href="#"><li class="li_link" align="center"><b>Mileage<br><%= member.getMTOTALMILEAGE() %> 점</b></li></b></a>
 		<%}else{%>
 		<a href="#"><li class="li_link" align="center"><b>ORDERLIST</b></li></a>
 		<a href="#"><li class="li_link" align="center"><b>MYINFO</b></li></a>
 		<a href="#"><li class="li_link" align="center"><b>BORDER</b></li></a>
 		<a href="#"><li class="li_link" align="center" ><b>MEMBER CANCELLATION</b></li></a>
 		<a href="#"><li class="li_link" align="center"><b>Mileage</b></li></a>
 		<%}%>
 	</ul>
 	</div>
 

<footer></footer>
 </body>
</html>

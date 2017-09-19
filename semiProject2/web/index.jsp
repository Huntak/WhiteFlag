<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member" %>
<%
	// session.invalidate();
	Member loginUser = null;
	if (session.getAttribute("loginUser") != null) {
		loginUser = (Member) session.getAttribute("loginUser");
	}
	
	response.setHeader("cache-control","no-cache");
    response.setHeader("expires","0");
    response.setHeader("pragma","no-cache");
    response.setHeader("cache-control","no-store");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Cache-Control" content="No-Cache"> 
<meta http-equiv="pragma" content="No-cache"> 
<meta http-equiv="expires" content="0"> 
<meta charset="UTF-8">
<title>WhiteFlag</title>

<link rel="stylesheet" type="text/css" href="/semi/common/css/mainCenter.css?ver=3">

<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=3"></script>
<script type="text/javascript" src ="/semi/common/js/mainbys.js?ver=3" ></script>
<script type="text/javascript">
history.pushState(null, null, location.href); 

window.onpopstate = function(event) { 
	history.go(1); 
}

document.oncontextmenu = function(e) {
	if (e) {
		e.preventDefault();
	} else {
		event.keyCode = 0;
		event.returnValue = false;
	}
}
</script>
</head>
<body >
	<header>
	</header>
	<div id="center">
		<ul>
	 	<li><img src="common/images/01.jpg" alt="" class="mainpicture" href="#"></li>	
	 	<li><img src="common/images/02.jpg" alt="" class="mainpicture" href="#"></li>	 
	 	<li><img src="common/images/03.jpg" alt="" class="mainpicture" href="#"></li>	 
	 	<li><img src="common/images/04.jpg" alt="" class="mainpicture" href="#"></li>	 	
	 	</ul>  
 	<img src="common/images/left1.png" alt="" id="forChangeLeft" onclick="beforeImg();" style="cursor : pointer;">  
	<img src="common/images/right1.png" alt="" id="forChangeRight" onclick="afterImg();" style="cursor : pointer;"> 
	</div>
    <footer>
	</footer>
		</body>
</html>
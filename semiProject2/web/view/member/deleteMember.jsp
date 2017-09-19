<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "member.model.vo.Member" %>
<%
  Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>회원 삭제</title>
<link rel="stylesheet" type="text/css" href="/semi/view/member/css/deleteMember.css?ver=1">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript"  src="/semi/view/member/js/checkIdPwd.js?ver=1"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=1"></script>
<script type="text/javascript">
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
<body >
<header>
</header>
	<div id="center"  > 
		<form action="/semi/dropOutMember" method="post" onsubmit="return infoConfirmRepeat();"  >
					
					<br><br><br><br><br><br><br><br>
					<h2 align="center"> 이용해주셔서 감사 드립니다.</h2> <br/> 
					<h2 align="center"> 아이디와 패스워드 재입력 후 탈퇴하기 버튼을 누르시면 탈퇴처리 됩니다.</h2> <br/> 
					<label for="userId" id="loginLabel">I D</label> &nbsp; &nbsp; <br/>
					 
					<input type="text" name="userId" id="userId" class="loginTextbox" placeholder=""  size="50"  required>
					<br/><br/>
					 <label for="userPwd" id="loginLabel2">PASSWORD</label>&nbsp; &nbsp;<br/>
					 
					<input type="password" name="userPwd" id="userPwd" class="loginTextbox" placeholder=""  size="50" required>
					
					<br/><br/>							 
					<br/> <br/> <br/>
					<span>   
					<input class="submitButton" type="submit" value="탈퇴하기" id="dropOutButton" >  &nbsp; &nbsp;
					<a href="#"  ><input type="button" value="탈퇴취소" class="submitButton" id="cancleOutButton" onclick="history.back();"></a> 
					</span>
			
		</form>
	</div>
    
<footer>
</footer>
		</body>
</html>
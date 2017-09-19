<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "member.model.vo.Member" %>
<%
  Member loginUser = (Member)session.getAttribute("loginUser");
  String errorYn =  "";
  if((String)request.getAttribute("message")!=null){
     errorYn = (String)request.getAttribute("message");}
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" type="text/css" href="/semi/view/member/css/memberLogin.css?ver=2">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript"  src="/semi/view/member/js/memberLogin.js?ver=3"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=1"></script>
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
<body >
<header>
</header>
   <div id="center"> 
      <input type="hidden" id="hiddenErrormessage" value="<%=errorYn %>">
      <form action="/semi/login" method="post" onsubmit="return validate();"  >      
               <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
               <h3 align="center"> 안녕하세요 WhiteFlag 입니다.</h3> <br/> 
               <label for="userId" id="loginLabel">I D</label><br/>
               
               <input type="text" name="userId" id="userId" class="loginTextbox" placeholder=""  size="50"  required>
               <br/><br/>
               
                <label for="userPwd" id="loginLabel2">PASSWORD</label><br/>
                
               <input type="password" name="userPwd" id="userPwd" class="loginTextbox" placeholder=""  size="50" required>
               <br/><br/> &nbsp;  &nbsp;  
               <a href="/semi/view/member/findingId.jsp">아이디 찾기</a>  &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp;
               <a href="/semi/view/member/findingPwd.jsp">비밀번호 찾기</a>           
               <br/> <br/> <br/>
               <span><input class="submitButton" type="submit" value="로그인" id="loginButton" >  &nbsp; &nbsp;
               <a href="/semi/view/member/enrollForm.jsp"  ><input type="button" value="회원가입" class="submitButton" id="goEnrollButton"  ></a> 
               </span>
               <br><br>
               <label id="loginFailLabel"></label>
      </form>
   </div>
    
<footer>
</footer>
</body>
</html>
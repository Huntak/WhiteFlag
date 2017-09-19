<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "member.model.vo.Member" %>
<%
  Member loginUser = (Member)session.getAttribute("loginUser");		 
%>
<!DOCTYPE html >
<html>
<head>
<meta  charset="UTF-8">
<title>아이디찾기</title>
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=7"></script>
<script type="text/javascript"  src="/semi/view/member/js/findingId.js?ver=7"></script>
<link rel="stylesheet" type="text/css" href="/semi/view/member/css/findingId.css?ver=15">
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
<div id="center"  >		
	<div id="beforConfirm"  style="height:402px;">
		<table >
		<tr>
		<td colspan="2"><h2 > 회원가입시 등록하신 이메일과 전화번호를<br> 통해 ID를 찾으실 수 있습니다.</h2> </td>
		</tr>
		<tr>
		<td colspan="2" class="choiceRadio">	
		<label class="labels">선 택 &nbsp;  &nbsp;&nbsp; &nbsp;&nbsp; 
		<input type="radio" name="choiceWay" id="choiceWay1" value="email" checked >이메일 &nbsp; &nbsp;
		<input type="radio" name="choiceWay" id="choiceWay2" value="phone">전화번호 </label>
		</td>
		</tr>
		<tr>
		<td class="td1"><label class="labels">이&nbsp; &nbsp;    름 : </label> </td>      
		<td><input type="text" name="userName" id="userName" class="findingUserInfo" placeholder=""  size="30"  required></td>
		</tr>
	    <tr id="trEmail">
	    <td class="td1"><label class="labels">이 메 일 : </label></td>
	    <td><input type="text" name="userEmail" id="userEmail" class="findingUserInfo" placeholder=""  size="30" required></td>
	    </tr>	   
	    <tr id="trPhone">
	    <td class="td1"><label class="labels">전화번호 : </label></td>
	    <td><input type="text" name="userPhone1" id="userPhone1" class="findingUserInfo" placeholder=""  size="4" required>&nbsp;
		-&nbsp;<input type="text" name="userPhone2" id="userPhone2" class="findingUserInfo" placeholder=""  size="4" required>&nbsp;
		-&nbsp;<input type="text" name="userPhone3" id="userPhone3" class="findingUserInfo" placeholder=""  size="4" required></td>
	    </tr>	    
		<tr>
		<td colspan="2" class="labelButtonTd1"><label id="foundId" ></label></td>
		</tr>
		<tr>
		<td colspan="2" class="labelButtonTd2"><input class="submitarea" type="button" value="찾기" id="ConfirmButton" onclick="bringId();" ></td>
		</tr>
	    </table>
	</div>
	
	<div  id="afterConfirm" style="height:302px;" > <br/><br/>
	<h2 align="center"> 회원님의 아이디 검색 결과 입니다.</h2>  <br/><br/><br/>					     
	<label id="foundId2" ></label><br/><br/>
	<label id="foundId3" ></label>
	<br/><br/><br/><br/><br/>
	<a class="submitarea"  id="transferToPwd" href="/semi/view/member/findingPwd.jsp" >패스워드 찾기</a>  &nbsp; &nbsp;	 
	<a class="submitarea"  id="transferToMain" href="/semi/view/member/loginForm.jsp">로그인화면 이동</a> 
	<br/>  <br/>   
	</div>
</div>
   <footer>
   </footer>
		</body>
</html>
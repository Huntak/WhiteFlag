<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "member.model.vo.Member" %>
<%
  Member loginUser = (Member)session.getAttribute("loginUser");
  String id = "";
  if(request.getAttribute("id")!=null){
   id = (String)request.getAttribute("id");
  }
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>패스워드 찾기</title>
<link rel="stylesheet" type="text/css" href="/semi/view/member/css/findingPwd.css?ver=4">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=4"></script>
<script type="text/javascript"  src="/semi/view/member/js/findingPwd.js?ver=4"></script>
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
<div id="center" >	
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
	<td class="td1"><label class="labels">아이디 : </label> </td>      
	<td>
	<% if(id !=null){ %>
	<input type="text" name="userId" id="userId" class="findingUserInfo" placeholder=""  size="30" value="<%=id %>" required>
	<% } else { %>
	<input type="text" name="userId" id="userId" class="findingUserInfo" placeholder=""  size="30" required>
	<% } %>  				
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

	<div  id="afterConfirm"  style="height:402px;">
	<form action="/semi/changePw" method="post" onsubmit="return validate();"  > <br/><br/><br/><br/><br/>
	<h2 align="center"> 비밀번호를 초기화 합니다. <br/>변경할 비밀번호를 입력하여 주세요.</h2>  <br/><br/><br/>				
     &nbsp; &nbsp; &nbsp; <label  class="afterConfirmLabel" for="userId">아이디 : </label> &nbsp; &nbsp; &nbsp; &nbsp;
	<input type="text" name="afteruserId" id="afteruserId"  size="20" class="findingUserInfo"   readonly  ><br><br>
	 <label class="afterConfirmLabel" for="userId">변경 비밀번호 :</label> &nbsp; &nbsp;
	<input type="password" name="updatePwd" id="updatePwd" class="findingUserInfo"    size="20"   required><br><br>
	<label class="afterConfirmLabel" for="userId">비밀번호 확인 :</label> &nbsp;&nbsp; &nbsp;
	<input type="password" name="updatePwdRepeat" id="updatePwdRepeat" class="findingUserInfo"   size="20" required>
	<br/><br/><br/><br/><br/>
	<input class="forChangePwd"  id="transferToPwd" type="submit" value="변경하기" id="enrollButton" >  &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
	<label  >
	<a href="/semi/index.jsp" class="forChangePwd" id="transferToMain" >메인으로 이동</a></label> <br/>
	<br/>  <br/>   <label id="warningPwd"></label> <br/>
	</form>
	</div>
</div>
	
<footer> 
</footer>
</body>
</html>
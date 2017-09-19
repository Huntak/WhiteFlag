<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member" %>   
<%
	Member loginUser = (Member)session.getAttribute("loginUser");

%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<link rel="stylesheet" type="text/css" href="/semi/view/member/css/editMemberInfo.css?ver=3">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript"  src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=2"></script>
<script type="text/javascript"  src="/semi/view/member/js/editMemberInfo.js?ver=2"></script>
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
 	<form action="/semi/editMemberInfo" method="post" onsubmit="return validate();"  >
 		<h4>&nbsp;&nbsp;&nbsp;&nbsp;회원정보수정</h4> <br/> 
		<table>
		<tr><td class="item" width="100px;"><label for="userName">이름</label></td>
		<td><input type="text" name="userName" id="userName" size="24"  value="<%= loginUser.getMNAME() %>"   readonly>
		<label class="impossibleEdit">수정불가</label>			
		</td></tr>
		<tr><td class="item"><label for="userId">아이디</label></td>
		<td><input type="text" name="userId" id="userId"   size="24" value="<%= loginUser.getMID() %>"  readonly  >
		<label class="impossibleEdit">수정불가</label></td></tr>
		<tr><td class="item"><label for="userPwd">비밀번호</label></td>
		<td><input type="password" name="userPwd" id="userPwd" placeholder="영문/숫자 조합 8글자 이상"  size="24" value="<%= loginUser.getMPWD() %>" required>
		<label for="userPwd" id="pwd"></label>
		</td></tr>
		<tr><td class="item"><label for="userPwdCheck">비밀번호확인</label></td>
		<td><input type="password" name="userPwdCheck" id="userPwdCheck" placeholder="암호다시입력"  size="24" value="<%= loginUser.getMPWD() %>"  required>
		<label for="userPwdCheck" id="checkPwdResult"></label></td></tr>
		<tr><td class="item"><label for="email">이메일</label></td>			
		<td> <input type="text" name="userEmail1" size="14" id="userEmail1" value="<%= (loginUser.getMEMAIL().split("@"))[0] %>"  required>@
		<input type="text" name="userEmail2" size="10"  id="userEmail2" value="<%= (loginUser.getMEMAIL().split("@"))[1] %>" >   
    				  <select name="selectEmail" id="selectEmail" class="selectField" required>
                                 <option value="direct" id="direct" selected="selected">직접입력</option>
                                 <option value="naver.com" >네이버 메일</option>
                                 <option value="hanmail.net" >한 메일</option>
                                 <option value="hotmail.com" >핫 메일</option>
                                 <option value="gmail.com" >지 메일</option></select>
                    <label for="email" id="email1"></label>             
		</td></tr>
		<tr><td class="item"><label for="gender">성별</label></td>
		<%if(loginUser.getMGENDER() == 'M'){ %>
		<td><input type="radio" name="gender" id="gender1" value="M" checked disabled>남 &nbsp; &nbsp;
		<input type="radio" name="gender" id="gender2" value="F" disabled>여    &nbsp; &nbsp;
		<%} else{ %>
		<td><input type="radio" name="gender" id="gender1" value="M"  disabled>남 &nbsp; &nbsp;
		<input type="radio" name="gender" id="gender2" value="F" checked disabled>여    &nbsp; &nbsp;
			<%}%>			
		<label class="impossibleEdit">수정불가</label>
		</td> </tr>
		<tr><td class="item"><label for="birth">주민번호6자리</label></td>
		<td><input type="text" name="birth" id="birth" size="10" value="<%= loginUser.getMBIRTH() %>" readonly>
		<label class="impossibleEdit">수정불가 </label>
		</td></tr>
		<tr><td class="item"><label for="phone">전화번호</label></td> 
		<td><select id="hp1" name="hp1" >
		   <option value="02"> 02 </option><option value="031"> 031 </option><option value="032"> 032 </option>
		   <option value="033"> 033 </option><option value="041"> 041 </option><option value="042"> 042 </option>
		   <option value="043"> 043 </option><option value="044"> 044 </option><option value="051"> 051 </option>
		   <option value="052"> 052 </option><option value="053"> 053 </option><option value="054"> 054 </option>
		    <option value="055"> 055 </option><option value="061"> 061 </option><option value="062"> 062 </option>
		   <option value="063"> 063 </option><option value="064"> 064 </option><option value="0502"> 0502 </option>
		   <option value="0503"> 0503 </option><option value="0504"> 0504 </option><option value="0505"> 0505 </option>
		   <option value="0506"> 0506 </option><option value="0507"> 0507 </option> 
		   <option value="010"  selected> 010 </option><option value="011"> 011 </option><option value="016"> 016 </option>
		   <option value="017"> 017 </option><option value="018"> 018 </option><option value="019"> 019 </option>
		</select> - <input type="number" name="hp2" id="hp2" size="6"   maxlength="4" value="<%= loginUser.getMPHONE2() %>" >
		- <input type="number" name="hp3" id="hp3" size="6"  maxlength="4" value="<%= loginUser.getMPHONE3() %>"> 
		<label for="hp2" id ="hiddenhp1" ><%= loginUser.getMPHONE1() %></label>
		</td></tr>
		<tr><td class="item"><label for="zipcode">우편번호</label></td>
        <td><input type="text" name="post1" id="post1" size="10" maxlength="10" value="<%= loginUser.getMADDRESS1() %>"  readonly required >-<input type="text" name="post2" id="post2"
          size="10" maxlength="10"value="<%= loginUser.getMADDRESS2() %>" readonly required >
        <input type="button" value="우편번호 검색" onclick="searchZipcode();" class="button" id="postB">
        <label id ="postError"></label>
        </td></tr>
        <tr><td class="item"><label for="address">주소</label></td><td >  
        <input type="text" name="address" id="address"  size="50" value="<%= loginUser.getMADDRESS3() %>" readonly required/><br><br>
        <input type="text" name="address2" id="address2"  size="50" value="<%= loginUser.getMADDRESS4() %>" required/>
        <label id ="addressError"></label>
        </td></tr>
		</table><br/><br/>
	 	<div  class="surfaceEnrollbutton" align="center"> <br/><br/>
		<input class="forEnroll" type="submit" value="정보수정" id="enrollButton" >  &nbsp; &nbsp;
		<a href="#"><input class="forEnroll" type="button" value="수정취소" id="cancelButton" onclick="history.back();"></a> <br/>
		 </div> 
	</form>
</div>
<footer>
</footer> 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "member.model.vo.Member"%>

 <%
 	Member m = (Member) request.getAttribute("m");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<link rel="stylesheet" type="text/css" href="/semi/view/manager/css/insertRowProduct.css?ver=3">

<script type="text/javascript" src="/semi/common/js/jquery-3.1.1.min.js?ver=1"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js"></script>
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
function close2(){
	self.close();
}
</script>
<title>회원 보기</title>
</head>
<body>
<div id="title">
	| Member DETAIL VIEW
</div>

<table id="contents" cellspacing="0">
	<tr><td class="menu"><label>아이디</label></td><td class="item2"><label><%=m.getMID() %></label></td></tr>
	<tr><td class="menu"><label>이름</label></td><td class="item2"><label><%=m.getMNAME() %></label></td></tr>
	<tr><td class="menu"><label>생년월일</label></td><td class="item2"><label><%= m.getMBIRTH() %></label></td></tr>
	<tr><td class="menu"><label>이메일</label></td><td class="item2"><label><%= m.getMEMAIL() %></label></td></tr>
	<tr><td class="menu"><label>전화번호</label></td><td class="item2"><label><%= m.getMPHONE1() + "-" + m.getMPHONE2() + "-" +m.getMPHONE3() %></label></td></tr>
	<tr><td class="menu" rowspan="2"><label>주소</label></td>
	<td class="item2"><label><%= m.getMADDRESS1()+"-"+m.getMADDRESS2() %></label></td>
	
	</tr>
	<tr>
	<td class="item2"><label><%= m.getMADDRESS3() +"  " + m.getMADDRESS4() %></label></td>
	</tr>
	<tr><td class="menu"><label>회원등급</label></td><td class="item2">	
	<label><%=m.getGRADE()%></label>	</td></tr>
	<tr><td class="menu"><label>마일리지</label></td><td class="item2"><label><%= m.getMTOTALMILEAGE() %></label></td></tr>
	<tr><td class="menu"><label>성별</label></td><td class="item2"><label><%=m.getMGENDER() %></label></td></tr>
	<tr><td class="menu"><label>가입날짜</label></td><td class="item2"><label><%=m.getMENROLLDATE() %></label></td></tr>
	<tr><td class="menu"><label>구매누적금액</label></td><td class="item2"><label><%=m.getMTOTALPURCHASE() %></label></td></tr>

</table>
<input id="submit" type="button" value="닫기" onclick="close2();">


</body>
</html>
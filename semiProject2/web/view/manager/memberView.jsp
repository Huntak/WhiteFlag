<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "member.model.vo.Member"%>

 <%
 	Member m = (Member)request.getAttribute("m");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<link rel="stylesheet" type="text/css" href="/semi/view/manager/css/memberView.css?ver=5">

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
$(function(){  
	opener.location.reload();
	
});

function deleteId(){
		var mid = $('input[name=mid]').val();
	$.ajax({
			url : '/semi/deleteMInManager',
			type : 'get',
			data : {
				memberId : mid
			},
			dataType : "json",
			success : function(data) {
				if (Number(data.result) > 0) {  self.close(); opener.location.reload();
				} 
				else {alert("어라");}}
		}); 	
}

</script>
<title>회원정보 보기 및 수정</title>
</head>
<body>
<div id="title">
	| member view
</div>
<form action="/semi/mmupdate" method="post">
<table id="contents" cellspacing="0">
	<input type="hidden" name="mid" value="<%=m.getMID() %>">
	<input type="hidden" name="mpwd" value="<%=m.getMPWD() %>">
	<input type="hidden" name="mname" value="<%=m.getMNAME() %>">
	<input type="hidden" name="mname" value="<%=m.getMNAME() %>">
	<input type="hidden" name="mbirth" value="<%=m.getMBIRTH() %>">
	<input type="hidden" name="memail" value="<%=m.getMEMAIL() %>">
	<input type="hidden" name="mphone1" value="<%=m.getMPHONE1() %>">
	<input type="hidden" name="mphone2" value="<%=m.getMPHONE2() %>">
	<input type="hidden" name="mphone3" value="<%=m.getMPHONE3() %>">
	<input type="hidden" name="maddress1" value="<%=m.getMADDRESS1() %>">
	<input type="hidden" name="maddress2" value="<%=m.getMADDRESS2() %>">
	<input type="hidden" name="maddress3" value="<%=m.getMADDRESS3() %>">
	<input type="hidden" name="maddress4" value="<%=m.getMADDRESS4() %>">
	<input type="hidden" name="mtotalmileage" value="<%=m.getMTOTALMILEAGE() %>">
	<input type="hidden" name="mtotalpurchase" value="<%=m.getMTOTALPURCHASE() %>">
	<input type="hidden" name="mgender" value="<%=m.getMGENDER() %>">
	<input type="hidden" name="menrolldate" value="<%=m.getMENROLLDATE() %>">
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
	<select name="grade" >
	<%if(m.getGRADE().equals("NORMAL")) {%>
	<option value="NORMAL" selected>NORMAL</option>
	<%}else{%>
	<option value="NORMAL">NORMAL</option>
	<%} %>
	<%if(m.getGRADE().equals("SILVER")) {%>
	<option value="SILVER" selected>SILVER</option>
	<%}else{%>
	<option value="SILVER">SILVER</option>
	<%} %>
	<%if(m.getGRADE().equals("GOLD")) {%>
	<option value="GOLD" selected>GOLD</option>
	<%}else{%>
	<option value="GOLD">GOLD</option>
	<%} %>
	<%if(m.getGRADE().equals("VIP")) {%>
	<option value="VIP" selected>VIP</option>
	<%}else{%>
	<option value="VIP">VIP</option>
	<%} %>
	</select>
	
	</td></tr>
	<tr><td class="menu"><label>마일리지</label></td><td class="item2"><label><%= m.getMTOTALMILEAGE() %></label></td></tr>
	<tr><td class="menu"><label>성별</label></td><td class="item2"><label><%=m.getMGENDER() %></label></td></tr>
	<tr><td class="menu"><label>가입날짜</label></td><td class="item2"><label><%=m.getMENROLLDATE() %></label></td></tr>
	<tr><td class="menu"><label>구매누적금액</label></td><td class="item2"><label><%=m.getMTOTALPURCHASE() %></label></td></tr>

</table>
<div id="buttonSurface">
<input id="submit" type="submit" value="변경">
<input id="outmember" type="button" value="탈퇴"  id="cancleOutButton" onclick="deleteId();">
</div>
</form>

</body>
</html>
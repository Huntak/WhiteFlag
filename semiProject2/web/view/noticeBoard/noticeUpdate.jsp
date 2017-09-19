<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "notice.model.vo.Notice, member.model.vo.Member" %>
<% 
	Notice notice = (Notice)request.getAttribute("notice"); 
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js"></script>
<link rel = "stylesheet" type = "text/css" href = "/semi/view/noticeBoard/css/noticedetialview.css?ver=6">
<style>
input:focus, textarea:focus{
    outline: none;
}
td{
	border-top: 1px solid lightgray;
	border-bottom: 1px solid lightgray;
	font-size: 9pt;
}
table{
	border-collapse : collapse;
	width:1200px;
	top:100px;
}
.td1{
	height : 30px;
	width :10px;
	text-align : center;
	background : #f6f6f6;
	font-size : 9pt;
	align : left;
}
.admin{
	height : 50px;
	width :150px;
	text-align : center;
	background : #f6f6f6;
	font-size : 9pt;
	align : left;
}
div#title{
	margin-left:100px;
	color: black;
	font-size:10pt;
	margin-bottom:10px;
}

</style>

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
function deleteConfirm(){
	if(confirm("정말로 삭제하시겠습니까?")){
		location.href="/semi/ndel?nnumber=<%=notice.getnNumber()%>";
	}
}
function modifyConfirm(){
	if(confirm("정말 수정하시겠습니까?")){
		document.getElementById('frm').submit();
	}
}
$(function(){
	var ntitle="<%=notice.getnTitle() %>";
	$("select[name=ntitle]").val(ntitle);
});
</script>
<title>noticeUp</title>
</head>
<body>
<header></header>
<div id="title">|  NOTICE  <td>: news & event</td></div>
	
<div id="content">
	<form onsubmit="return ok();" action="/semi/nupd" id="frm" method="post" enctype="multipart/form-data" >
	<input type="hidden" value="<%=notice.getnNumber()%>" name="nnumber">
		<table class="cla1">
			<tr>
				<td  class="td1" >subject</td>
				<td><input type="text" name="ntitle" value="<%= notice.getnTitle()%>"></td>
			</tr>			

			<tr><td class="td1">name</td><td><input type="text" value="admin" readOnly style="border:none;"></td></tr>
			<tr><td colspan="2" id="tdcontent" style="text-align:left">
			<div style="margin-bottom:20px;"><textarea name="ncontent" style="resize:none; width:1400px; height:300px;"><%= notice.getnContent() %></textarea></div></td></tr>
			<tr>
				<td class="td1">file</td>
				<td>
					<input type="file" name="nimage" value = "<%= notice.getnImage() %>"><%= notice.getnImage() %>
				</td>
			</tr>
			<tr><td class="td1">개인정보 수집및 이용 동의</td>
		<td height="130px;" align="left">
		<textarea id="foragree" style="resize:none; height : 150px; width : 550px;" readonly >		
■ 개인정보의 수집·이용 목적
서비스 제공 및 계약의 이행, 구매 및 대금결제, 물품배송 또는 청구지 발송, 회원관리 등을 위한 목적
■ 수집하려는 개인정보의 항목
이름, 주소, 연락처, 이메일 등     
회사는 개인정보 수집 및 이용목적이 달성된 후에는 예외없이 해당정보를 파기합니다.
		</textarea>
				<br>
					<font size="2pt;">개인정보 수집 및  이용에 동의하십니까?</font> 
					<input type="radio" name="agree1" id="okk">동의함 <input type="radio" name="agree1" id="not" checked>동의안함
				</td>
			</tr>
			<tr><td><div style="margin-bottom:40px; id:qlist"><a href="/semi/qlist" id="qlist" >list</a></div></td>
				<td align="right"><a href="#" onclick="javascript:modifyConfirm();" id="modify" class="qdb">modify</a>
				<a href="/semi/nlist" onclick="javascript:cancelConfirm();" id="delete" class="qdb">cancel</a>
				</td>
			</tr>
		</table>
	</form>
	<br>
		
		<br>
			<br>
				<br>
					<br>
</div>
</body>
</html>
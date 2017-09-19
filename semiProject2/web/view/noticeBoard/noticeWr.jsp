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
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=4"></script>
<style>
input:focus, textarea:focus{
    outline: none;
}
#noticewr{
	width : 900px;
	margin-right : auto;
	margin-left : auto;
}
#noticewr td{
	border : solid 1px lightgray;
}
#noticewr table{
	border-collapse : collapse;
}
#noticewr .td1{
	height : 40px;
	width : 80px;
	text-align : center;
	background : #f6f6f6;
	font-size : 8pt;
	align : left;
}
#noticewr select{
	margin-left : 10px;
	width : 350px;
}
#noticewr input{
	margin-left : 10px;
}
#agree{
	border : 1px solid #f6f6f6;
	width : 500px;	
	text-align : left;
	margin-left : 10px;
	font-size : 10pt;
}
.button{
	margin-top : 20px;
	left : 900px;
	border : 1px solid black;
	width : 60px;
	height : 30px;
	background : white;
}
#list_button{
	border : 1px solid black;
	width : 60px;
	height : 30px;
	background : white;
}
.btnArea {
    overflow: hidden;
    margin: 20px 0 50px;
    text-align: right;
    font-size: 12px;
    line-height: 12px;
}
 .btnArea span.right a:first-child {
    padding: 5px 20px;
    margin-right: 5px;
    font-size: 12px;
    background: #333;
    color: #fff;
    line-height: 30px;
    border: 1px solid #333;
}

 .btnArea span.left a, .btnArea span a {
    padding: 5px 10px;
    margin-right: 5px;
    font-size: 12px;
    color: #333;
    line-height: 30px;
    border: 1px solid #ccc;
}

.btnArea span.left a, btnArea span a {
    padding: 5px 10px;
    margin-right: 5px;
    font-size: 12px;
    color: #333;
    line-height: 30px;
    border: 1px solid
}
#nwlist{
    width: 7%;
    height: 27px;
    border: 1px solid black;
    background: white;
    font-style: italic;
    font-weight: bold;
    cursor: pointer;
    margin-right:70%;
    text-decoration:none;
}
#nwmainQ{
	margin-left: 300px;
    margin-top: 60px;
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
	$(function(){					//문서가 시작하면 자동으로 실행되는 부분
		$('input:radio[name="agree1"]').change(function(){// 라디오버튼 부분이 바뀔때 실행
			if($("#okk").is(":checked")){
				$('#bok').attr('disabled',false);
			}
		});
	});
	
	
	function ok(){
		if($('textarea[name=ncontent]').val()=="") {
			alert("내용을 입력하십시오");
			return false;  //error 페이지로 넘어가는것을 막음
		}else if($('#okk').is(":checked") == false){
			alert("개인정보 수집 및 이용에 동의해 주시기 바랍니다..");
			return false;
		}else{
			return true;
		}
		
	} 
</script>
<title>noticeWr</title>
</head>
<body>
 <header></header>
<div id="nwmainQ">| NOTICE<td> : news & event</td></div>
<form action="/semi/nwrite" method="post" enctype="multipart/form-data" onsubmit="return ok();" style="margin-top:70px;">
<div id="noticewr">
<table class="cla1">
<tr>
	<td  class="td1">subject</td>
	<td>
		<input type="text" name="ntitle" size = "80">
	</td>
</tr>
	<tr><td class="td1">name</td><td>&nbsp;<label>WhiteFlag[관리자]</label></td></tr>
	<tr><td colspan="2"><textarea style="resize:none; width:1000px; height:300px;" name="ncontent"></textarea></td></tr>
	<tr><td class="td1">file</td><td><input type="file" name="nimage"></td></tr>
	<tr><td class="td1">개인정보 수집및 이용 동의</td>
		<td height="130px;" align="left">
		<textarea id="agree" style="resize:none; height : 150px; width : 500px;" readonly align="left">		
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
		</table>
		<div class="btnArea">
		<span><a href="/semi/nlist" id="nwlist">list</a></span>
		<input type="submit" value="ok" style="width:7%; height:27px; border:none;background:black;color:white;font-style:italic;font-weight:bold; cursor : pointer;">
        <a href="/semi/nlist">
        <input type="button" value="cancel" style="width:7%; height:27px; border:1px solid black;background:white;font-style:italic;font-weight:bold; cursor : pointer;"></a>
		</div>
	</div>
</form>
</body>
</html>
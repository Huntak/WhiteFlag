<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "qanda.model.vo.Qna, member.model.vo.Member" %>
<% 
	Qna qna = (Qna)request.getAttribute("qna"); 
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
	border-collapse: collapse;
    width: 1100px;
    top: 100px;
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
	margin-left:165px;
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
		location.href="/semi/qdel?qnumber=<%=qna.getqNumber()%>";
	}
}
function modifyConfirm(){
	if(confirm("정말 수정하시겠습니까?")){
		document.getElementById('frm').submit();
	}
}
$(function(){
	var qtitle="<%=qna.getqTitle() %>";
	$("select[name=qtitle]").val(qtitle);
});
$(function(){					//문서가 시작하면 자동으로 실행되는 부분
	/* $('#bok').attr('disabled',true); */
	
	$('input:radio[name="agree1"]').change(function(){// 라디오버튼 부분이 바뀔때 실행
		if($("#okk").is(":checked")){
			$('#bok').attr('disabled',false);
		}
	});
});


function ok(){
	if($('textarea[name=qcontent]').val()=="") {
		alert("내용을 입력하십시오");
		return false;  //error 페이지로 넘어가는것을 막음
	}else if($('#okk').is(":checked") == false){
		alert("개인정보 수집 및 이용에 동의해 주시기 바랍니다.");
		return false;
	}else{
		return true;
	}
	
} 
</script>
<title>qnaUp</title>
</head>
<body>
<header></header>
<div id="title">|  ITEM Q&A <td>: news & event</td></div>
	
<div id="qucontent">
	<form onsubmit="return ok();" action="/semi/qupd" id="frm" method="post" enctype="multipart/form-data" >
	<input type="hidden" value="<%=qna.getqNumber()%>" name="qnumber">
		<table class="cla1">
			<tr>
				<td  class="td1">subject</td>
				<td>
					<select name="qtitle" style="width:16%; height:80%">
						<option value="상품문의">상품문의</option>
						<option  value="배송문의">배송문의</option>
						<option  value="교환&반품문의">교환&반품문의</option>
						<option  value="기타문의">기타문의</option>
						<option  value="배송전 취소&변경문의">배송전 취소&변경문의</option>
						<option  value="재입고문의">재입고문의</option>
						<option  value="입금&미입금문의">입금&미입금문의</option>
					</select>
				</td>
			</tr>			

			<tr><td class="td1">name</td><td><input type="text" value="<%=qna.getmId() %>" readOnly style="border:none;"></td></tr>
			<tr><td colspan="2" id="tdcontent" style="text-align:left">
			<%-- <%if(qna.getqImage()==null){ %>
		
			<%}else{ %>
			<div style="margin-top:40px; margin-bottom:10px;"><img width="500" src ="/semi/qnaUploadFiles/<%= qna.getqImage() %>"></div>
			<%} %> --%>
			<div style="margin-bottom:20px;"><textarea name="qcontent" style="resize:none; width:1100px; height:300px;"><%= qna.getqContent() %></textarea></div></td></tr>
			<tr>
				<td class="td1">file</td>
				<td>
					<input type="file" name="qimage" value = "<%= qna.getqImage() %>"><%= qna.getqImage() %>
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
				<a href="/semi/qlist" onclick="javascript:cancelConfirm();" id="delete" class="qdb">cancel</a>
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
<footer></footer>
</body>
</html>
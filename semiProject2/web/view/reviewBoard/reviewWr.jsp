<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member, java.util.*, product.model.vo.*" %>
<% 
	Member loginUser = (Member)session.getAttribute("loginUser");
	ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("productList");
	
	Product[] p = null;
	if(productList.size() > 0){
		p = new Product[productList.size()];
		int i = 0;
		p[0] = productList.get(0);
		for(int k = 1; k < productList.size(); k++){
			if(!(p[i].getpName().equals(productList.get(k).getpName()))){
				i++;
				p[i] = productList.get(k);
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=3"></script>
<link rel = "stylesheet" type = "text/css" href = "/semi/view/noticeBoard/css/noticedetialview.css?ver=5">
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
			if($("#not").is(":checked")){
				$('#rok').attr('disabled',false);
			}
		});
	});
	function ok(){
		if($('input[name=rtitle]').val()=="") {
			alert("제목을 입력하십시오");
			return false;  //error 페이지로 넘어가는것을 막음
		}else if($('#okk').is(":checked") == false){
			alert("개인정보 수집 및 이용에 동의해 주시기 바랍니다.");
			return false;
		}else{
			return true;
		}
	} 
</script>
<style>
input:focus, textarea:focus{
    outline: none;
}
#reviewwr{
	width: 1210px;
    margin-left: 150px
}
#reviewwr td{
	border : solid 1px lightgray;
}
#reviewwr table{
	border-collapse : collapse;
}
#reviewwr .td1{
	height : 40px;
	width : 150px;
	text-align : center;
	background : #f6f6f6;
	font-size : 8pt;
	align : left;
}
#reviewwr select{
	margin-left : 10px;
	width : 350px;
	height: 62%;
}
#reviewwr input{
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
	position : relative;
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
</style>
<title>reviewWr</title>
</head>
<body>
<header></header>
 <div id="rwtitle">| REVIEW   <td>:: After Review</td></div>
<form action="/semi/rwrite" method="post" enctype="multipart/form-data" onsubmit="return ok();"style="margin-top:50px;">
<input type="hidden" name="mid" value="<%= loginUser.getMID() %>">
<div id="reviewwr" >
<table class="cla1">
<tr><td  class="td1">subject</td><td>&nbsp;<label><input type="text" name="rtitle" size ="45"></label></td></tr>
	<tr><td class="td1">name</td><td>&nbsp;<label><input type="text" name="" size ="20" value="<%= loginUser.getMID() %>"></label></td></tr>
	<tr><td colspan="2"><textarea style="resize:none; width:1200px; height:300px;" name="rcontent">
키:
몸무게:
구매한 사이즈:
구매한 색상:
상품후기:
		</textarea>
	</td>
</tr>
	<% if(productList.size() > 0){ %>
	<tr><td class="td1">구매했던 상품</td>
		<td><select name="pid" required>
			<option value="" selected>상품선택
			<option value="">-------------------
			<% for(int i = 0; i < p.length; i++){
				if(p[i] != null){ %>
			<option value="<%= p[i].getpId() %>"><%= p[i].getpName() %>
			<% }} %>
			</select>
		</td>
	</tr>
	<% } %>
	<tr><td class="td1">file</td><td><input type="file" name="rimage"></td></tr>
	<tr><td class="td1">개인정보 수집및 이용 동의</td>
		<td height="130px;" align="left">
		<textarea id="agree" style="resize:none; height : 150px; width : 550px;" readonly align="left">		
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
			<span><a href="/semi/rlist" id="rlist">list</a></span>
			<input type="submit" value="ok" id="rok">
       		 <a href="/semi/rlist"><input type="button" value="cancel" id="rcancel"></a>
		</div>
	</div>
</form>
<footer></footer>
</body>
</html>

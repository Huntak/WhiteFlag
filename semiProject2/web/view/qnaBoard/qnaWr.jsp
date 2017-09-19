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
<link rel = "stylesheet" type = "text/css" href = "/semi/view/noticeBoard/css/noticedetialview.css?ver=3">
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
<style>
input:focus, textarea:focus{
    outline: none;
}
#QandAwr div{
	width : 1200px;
	margin-right : auto;
	margin-left : auto;
}
#QandAwr td{
	border : solid 1px lightgray;
}
#QandAwr table{
	border-collapse : collapse;
}
#QandAwr .td1{
	height : 40px;
	width : 100px;
	text-align : center;
	background : #f6f6f6;
	font-size : 8pt;
	align : left;
}
#QandAwr select{
	margin-left : 10px;
	width : 350px;
	height: 62%;
}
#QandAwr input{
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
    margin-right:150px;
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
</style>
<title>qnaWr</title>
</head>
<body>
 <header></header>
 <div id="qwtitle">| ITEM  Q&A  <td>: ask anything</td></div>
<form action="/semi/qwrite" onsubmit="return ok();"method="post" enctype="multipart/form-data" style="margin-top: 50px; margin-left: 150px;">
<input type="hidden" name="mid" value="<%= loginUser.getMID() %>">
<div id="QandAwr">
<table class="cla1">
<tr>
	<td  class="td1">subject</td>
	<td>
		<select name="qtitle">
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
	<input type="hidden" name="mid"value="<%=loginUser.getMID() %>">
	<tr><td class="td1">name</td><td>&nbsp;<label><%=loginUser.getMID() %></label></td></tr>
	<tr><td></td><td>
  	<tr><td colspan="2">
 	<textarea  id="word" style="resize:none; width:1200px; height:300px;" name="qcontent" ></textarea>
	    
	
		</td>
	</tr>
	<% if(productList.size() > 0){ %>
	<tr><td class="td1">구매했던 상품</td>
		<td>
			<select name="pid" required>
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
	<tr><td class="td1">file</td><td><input type="file" name="qimage"></td></tr>
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
		</table>
		<div class="btnArea">
			<span><a href="/semi/qlist" id="qqlist">list</a></span>
			<input type="submit" value="ok" id="bok">
       		<a href="/semi/qlist"><input type="button" value="cancel" id="bcancel"></a>
		</div>
	</div>
</form>
<footer></footer>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "member.model.vo.Member" %>
<%
  Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>새 거래처 등록</title>
<script type="text/javascript" src="/semi/common/js/jquery-3.1.1.min.js?ver=1"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=2"></script>
<link rel="stylesheet" type="text/css" href="/semi/view/manager/css/newProvider.css?ver=2">
<script type="text/javascript"  src="http://dmaps.daum.net/map_js_init/postcode.v2.js?ver=1"></script>
<style type="text/css">
    h1 { font-size: 34px; line-height: 1.2; margin: 0.3em 0 10px; }
    #scrolltotop{position:fixed;bottom:0px;right:0px}#scrolltotop
    span{width:48px;height:48px;display:block;background:url("/test/1183/top.png") top no-repeat;margin:0px
    15px 10px 0;border-radius:3px;-webkit-transition:all 0.2s ease-out;-moz-transition:all 0.2s ease-out;-o-transition:all 0.2s ease-out;-ms-transition:all 0.2s ease-out;transition:all 0.2s ease-out}#scrolltotop a:hover
    span{background:url("/test/1183/top.png") bottom no-repeat}
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
function searchZipcode() {
	new daum.Postcode({
		oncomplete : function(data) {
			document.getElementById("post1").value = data.postcode1;
			document.getElementById("post2").value = data.postcode2;
			document.getElementById("address").value = data.address;
			document.getElementById("address2").focus();
		}
	}).open();
}


</script>
</head>
<body>
<header></header>
<div id="title">
   | NEW PROVIDER
</div>

<form action="/semi/newprovider" method="post" >
<table id="contents" cellspacing="0">
	<thead>
   <tr><td class="menu"><label>거래처이름</label></td><td class="item2"><input type="text"class="item" name="providercompany" required></td></tr>
   <tr><td class="menu"><label>담당자이름</label></td><td class="item2"><input type="text"class="item" name="providername" required></td></tr>
   </thead>
  
   <tbody>
   <tr><td class="menu"><label>거래처번호</label></td><td class="item2"><input type="text"class="item" name="providertell" required></td></tr>
   <tr><td class="menu"><label>담당자번호</label></td><td class="item2"><input type="text"class="item" name="providerphone" required>
    </td></tr>
   <tr><td class="menu"><label for="zipcode">우편번호</label></td>
	<td class="item2"><input type="text" name="post1" id="post1" size="10" maxlength="10"  readonly required >-<input type="text" name="post2" id="post2"
			          size="10" maxlength="10" readonly required >
			        <input type="button" value="우편번호 검색" onclick="searchZipcode();" class="button" id="postB">
			        <label id ="postError"></label>
			        </td></tr>
			        <tr><td class="menu"><label for="address">주소</label></td><td class="item2">  
			        <input type="text" name="address" id="address" placeholder="기본주소" size="50" readonly required/><br><br>
			        <input type="text" name="address2" id="address2" placeholder="상세주소입력" size="50" required/>
			        <label id ="addressError"></label>
			        </td></tr>
	<tr><td class="menu"><label>기타사항</label></td><td class="item2"><textarea name="provideretc" style="resize:none;width:500px;height:200px;"></textarea>
</table>
<input id="submit" type="submit" value="등록">
</form>
<div id="scrolltotop" style="display: block;"><a href="#top"><span></span></a></div>
<!-- <footer></footer> -->
</body>
</html>
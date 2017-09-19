<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "member.model.vo.Member, provider.model.vo.Provider, java.util.*" %>
<% Member loginUser = (Member)session.getAttribute("loginUser"); 
	Provider p = (Provider)request.getAttribute("p");
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/semi/common/js/jquery-3.1.1.min.js?ver=1"></script>
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
function deletepv(){
	
	$.ajax({
		url : "/semi/dprovider",
		data : {providercode: $('input[name=providercode]').val()},
		type : "get",
		dataType : "json",
		success : function(data){
			
			if(data.result > 0){				
				opener.location.reload();
				self.close();
			}else{
				alert("이 거래처로 등록된 상품을 먼저 삭제하셔야 합니다.")
			} 
			
			
		}
	});
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/semi/common/js/jquery-3.1.1.min.js?ver=1"></script>
<link rel="stylesheet" type="text/css" href="/semi/view/manager/css/providerView.css?ver=2">
<script type="text/javascript"  src="http://dmaps.daum.net/map_js_init/postcode.v2.js?ver=1"></script>
<script type="text/javascript">
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

<div id="title">
   | Provider View
</div>

<form action="/semi/updateprovider" method="post">
<input type="hidden"class="item" name="providercode" value="<%= p.getProviderCode()%>">
<table id="contents" cellspacing="0">
	<thead>
	<tr><td class="menu"><label>거래처코드</label></td><td class="item2"><label><%= p.getProviderCode()%></label></td></tr>
   <tr><td class="menu"><label>거래처이름</label></td><td class="item2"><input type="text"class="item" name="providercompany" value="<%= p.getProviderCompany()%>"></td></tr>
   <tr><td class="menu"><label>담당자이름</label></td><td class="item2"><input type="text"class="item" name="providername" value="<%= p.getProviderName()%>"></td></tr>
   </thead>
   
   <tbody>
   <tr><td class="menu"><label>거래처번호</label></td><td class="item2"><input type="text"class="item" name="providertell" value="<%= p.getProviderTell()%>"></td></tr>
   <tr><td class="menu"><label>담당자번호</label></td><td class="item2"><input type="text"class="item" name="providerphone" value="<%= p.getProviderPhone()%>">
    </td></tr>
   <tr><td class="menu"><label for="zipcode">우편번호</label></td>
	<td class="item2"><input type="text" name="post1" id="post1" size="10" maxlength="10"  readonly required value="<%= p.getProviderAddress1()%>">-<input type="text" name="post2" id="post2"
			          size="10" maxlength="10" readonly required value="<%= p.getProviderAddress2()%>">
			        <input type="button" value="우편번호 검색" onclick="searchZipcode();" class="button" id="postB">
			        <label id ="postError"></label>
			        </td></tr>
			        <tr><td class="menu"><label for="address">주소</label></td><td class="item2">  
			        <input type="text" name="address" id="address" placeholder="기본주소" size="50" value="<%= p.getProviderAddress3()%>" readonly required/><br><br>
			        <input type="text" name="address2" id="address2" placeholder="상세주소입력" value="<%= p.getProviderAddress4()%>" size="50" required/>
			        <label id ="addressError"></label>
			        </td></tr>
	<tr><td class="menu"><label>기타사항</label></td><td class="item2"><textarea name="provideretc" style="resize:none;width:500px;height:200px;"><%= p.getProviderETC()%></textarea>
</table>
<input id="submit" type="submit" value="변경">
<input type="button" onclick="deletepv();" value = "삭제">
</form>
</body>
</html>
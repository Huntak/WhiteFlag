<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "orderAndPay.model.vo.OrderList"%>

 <%
 	OrderList o = (OrderList) request.getAttribute("o");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<link rel="stylesheet" type="text/css" href="/semi/view/manager/css/orderListUpdateView.css?ver=2">

<script type="text/javascript" src="/semi/common/js/jquery-3.1.1.min.js?ver=1"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js"></script>
<script type="text/javascript"  src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
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
			document.getElementById("address1").value = data.address;
			document.getElementById("address2").focus();
		}
	}).open();
}
function insertpd(){
	var oid = $('input[name=oid]').val();
	var name = $('input[name=name]').val();
	var phone1 = $('input[name=phone1]').val();
	var phone2 = $('input[name=phone2]').val();
	var phone3 = $('input[name=phone3]').val();
	var post1 = $('input[name=post1]').val();
	var post2 = $('input[name=post2]').val();
	var address1 = $('input[name=address1]').val();
	var address2 = $('input[name=address2]').val();
	var email = $('input[name=email]').val();
	var osnumber = $('select[name=osnumber]').select().val();
	$.ajax({
		url : "/semi/updateol",
		data : { oid: oid,name: name, phone1 : phone1, phone2: phone2, phone3: phone3, post1: post1, post2: post2,
			address1: address1, address2: address2, email: email, osnumber: osnumber
			},
		
		type : "get",
		dataType : "json",
		success : function(data){
			
			if(data.result > 0){
				opener.location.reload();
				self.close();
			}else{
				alert("수정실패!")
			} 
			
			
		}
	});
}
function close2(){
	self.close();
}

function delorderlist(){
	var oid = $('input[name=oid]').val();
	$.ajax({
		url : "/semi/deleteorder",
		data : {oid:oid},
		
		type : "get",
		dataType : "json",
		success : function(data){
			
			if(data.result > 0){
				opener.location.reload();
				self.close();
			}else{
				alert("삭제실패!")
			} 
			
			
		}
	});
}
</script>
<title>상품 추가</title>

</head>
<body>
<div id="title">
	| ORDER 
</div>


<table id="contents" cellspacing="0">
	<input type="hidden" name="oid" value="<%= o.getOID() %>">
	<tr><td class="menu"><label>주문자이름</label></td><td class="item2"><label><input type="text" name="name" size="15" value="<%= o.getNAME()%>"></label></td></tr>
	<tr><td class="menu"><label>전화번호</label></td><td class="item2"><input type="text" name="phone1" size="5" value="<%= o.getPHONE1()%>">-<input type="text" name="phone2" size="5" value="<%= o.getPHONE2()%>">-<input type="text" name="phone3" size="5" value="<%= o.getPHONE3()%>"></td></tr>
	<tr><td class="menu"><label>이메일</label></td><td class="item2"><label><input type="text" name="email" size="30" value="<%= o.getEMAIL()%>"></label></td></tr>
	
	<tr><td class="menu"><label for="zipcode">우편번호</label></td>
	<td><input type="text" name="post1" id="post1" size="10" maxlength="10" value="<%=o.getPOST1() %>"  readonly required >-<input type="text" name="post2" id="post2" size="10" maxlength="10" value="<%=o.getPOST2() %>" readonly required >
			<input type="button" value="우편번호 검색" onclick="searchZipcode();" class="button" id="postB"> </td></tr>
	<tr>
	<td class="menu"><label for="address">주소</label></td>
	<td ><input type="text" name="address1" id="address1" value="<%=o.getADDRESS1() %>" placeholder="기본주소" size="50" readonly required/><br><br>
	<input type="text" name="address2" id="address2" value="<%=o.getADDRESS2() %>" placeholder="상세주소입력" size="50" required/>
			        
	
	<tr><td class="menu"><label>주문상태</label></td><td class="item2">
	
	<select name="osnumber">
		<option value="1">결제진행중</option>
	    <option value="2">결제완료</option>
	    <option value="3">배송준비중</option>
	    <option value="4">배송진행중</option>
	    <option value="5">배송완료</option>
	    <option value="6">구매확정</option>
	    <option value="7">구매완료</option>
	    <option value="8">취소처리중</option>
	    <option value="9">취소완료</option>
	    <option value="10">반품처리중</option>
	    <option value="11">반품취소</option>
	    <option value="12">반품완료</option>
	</select>
	<script type="text/javascript">
	$(function(){
		var os = <%= o.getOSNUMBER() %>		
		
			$("select[name=osnumber]").val(os).selected();
		
	});
	</script>
	
	</td></tr>
	
</table>
<input id="submit" type="button" value="변경" onclick="insertpd();">
<input type="button" value="삭제" onclick="delorderlist()">
<input type="button" value="닫기" onclick="close2();">



</body>
</html>
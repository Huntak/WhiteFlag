<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "product.model.vo.Product"%>

 <%
 	Product p = (Product)request.getAttribute("p");
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
<title>상품 보기</title>
</head>
<body>
<div id="title">
	| PRODUCT DETAIL VIEW
</div>

<table id="contents" cellspacing="0">
	<tr><td class="menu"><label>상품명</label></td><td class="item2"><label><%=p.getpName() %></label></td></tr>
	<tr><td class="menu"><label>컬러</label></td><td class="item2"><label><%= p.getpColor() %></label></td></tr>
	<tr><td class="menu"><label>사이즈</label></td><td class="item2"><label><%= p.getpSize() %></label></td></tr>
	<tr><td class="menu"><label>재질</label></td><td class="item2"><label><%=p.getpMaterial()%></label></td></tr>
	<tr><td class="menu"><label>가격</label></td><td class="item2"><label><%=p.getpPrice() %></label></td></tr>
	<tr><td class="menu"><label>시즌</label></td><td class="item2"><label><%=p.getpSeason() %></label></td></tr>
	<tr><td class="menu"><label>거래처코드</label></td><td class="item2"><label><%=p.getProviderCode() %></label></td></tr>
	<tr><td class="menu"><label>카테고리</label></td><td class="item2"><label><%=p.getcId() %></label></td></tr>
	<tr><td class="menu"><label>이미지id</label></td><td class="item2"><label><%=p.getImageId() %></label></td></tr>

</table>
<input id="submit" type="button" value="닫기" onclick="close2();">


</body>
</html>
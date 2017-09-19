<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="orderAndPay.model.vo.OrderList, product.model.vo.ProductImage"%>
<%
String mainimage = (String)request.getAttribute("mainimage");
String pname = (String)request.getAttribute("pname");
String pcolor = (String)request.getAttribute("pcolor");
String psize = (String)request.getAttribute("psize");
String quantity = (String)request.getAttribute("quantity");
OrderList orderlist = (OrderList)request.getAttribute("orderlist");
//String odate = (String)request.getAttribute("odate");
int imageid = (int)request.getAttribute("imageid");
%>    
<!DOCTYPE html >
<html>
<head>
<meta charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/semi/view/orderAndPay/css/SuccessOrder.css?ver=3">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
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
</script>
<title>주문완료</title>
</head>
<body>
<header></header>
<div id="info_div">
<font size="3"><font color="#4C4C4C">감사합니다.</font>주문과 결제가 정상적으로 완료되었습니다.<br>
<font size="2" color="gray">주문 내역 확인은 마이페이지에서 확인 가능합니다.</font><br><br></font>

<font size="3" >회원님의 주문번호는 : <%= orderlist.getOID() %> 입니다.</font><br>
<font size="3">주문일시 : <%= orderlist.getSODATE() %></font>
</div>

<div id="product_div">
<table id="product_table">
<tr><th width="20%;">이미지</th><th width="40%;">상품이름</th><th>색상</th><th>사이즈</th><th>수량</th></tr>
<tr>
<td><a href="/semi/detail?imageId=<%= imageid%>"><image src="/semi/image/<%= mainimage%>" width="70" height="80"></a></td>
<td><a href="/semi/detail?imageId=<%= imageid%>"><%= pname %></a></td>
<td><%= pcolor %></td>
<td><%= psize %></td>
<td><%= quantity %>개</td></tr>
</table>
</div>
<div id="tomain_div">
<a href="/semi/index.jsp" id="tomain_a">메인으로가기</a>
</div>
<footer></footer>
</body>
</html>
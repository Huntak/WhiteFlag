<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 <%@page import="orderAndPay.model.vo.OrderList, product.model.vo.ProductImage, java.util.*, cart.model.vo.Cart
 , product.model.vo.Product" %>  
 <% 
 	ArrayList<Product> plist = (ArrayList<Product>)request.getAttribute("plist");
 	ArrayList<ProductImage> pimage = (ArrayList<ProductImage>)request.getAttribute("pimage");
 	ArrayList<OrderList> orderlist = (ArrayList<OrderList>)request.getAttribute("orderlist");
	//String odate = (String)request.getAttribute("odate");
 
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
<title>전체주문완료</title>
</head>
<body>
<header></header>
<div id="info_div">
<font size="3"><font color="#4C4C4C">감사합니다.</font>주문과 결제가 정상적으로 완료되었습니다.<br>
<font size="2" color="gray">주문 내역 확인은 마이페이지에서 확인 가능합니다.</font><br><br></font>

<font size="3" >회원님의 주문번호는 :  <%= orderlist.get(0).getTOTALID()  %>입니다.</font><br>
<font size="3">주문일시 : <%= orderlist.get(0).getSODATE() %></font>
</div>

<div id="product_div">
<table id="product_table">
<tr><th width="20%;">이미지</th><th width="40%;">상품이름</th><th>색상</th><th>사이즈</th><th>수량</th></tr>
<%for(int i = 0; i<plist.size(); i++){%>
<tr>
<td><a href="/semi/detail?imageId=<%= pimage.get(i).getImageId()%>"><image src="/semi/image/<%= pimage.get(i).getMainImage()%>" width="70" height="80"></a></td>
<td><a href="/semi/detail?imageId=<%= pimage.get(i).getImageId()%>"><%= plist.get(i).getpName() %></a></td>
<td><%= plist.get(i).getpColor() %></td>
<td><%= plist.get(i).getpSize() %></td>
<td><%= orderlist.get(i).getOQUANTITY() %>개</td>
</tr>
<%}%>
</table>
</div>
<div id="tomain_div">
<a href="/semi/index.jsp" id="tomain_a">메인으로가기</a>
</div>
<footer></footer>
</body>
</html>
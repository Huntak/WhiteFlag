<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 <%@page import="java.util.*, orderAndPay.model.vo.OrderList, product.model.vo.Product, orderstatus.model.vo.OrderStatus, member.model.vo.Member,
 product.model.vo.ProductImage"%>
 <%
  ArrayList<OrderList> olist = (ArrayList<OrderList>)request.getAttribute("olist");
  ArrayList<Product> plist = (ArrayList<Product>)request.getAttribute("plist");
  ArrayList<ProductImage> pimage = (ArrayList<ProductImage>)request.getAttribute("pimage");
  ArrayList<OrderStatus> ostatus = (ArrayList<OrderStatus>)request.getAttribute("ostatus");
  Member loginUser = (Member)session.getAttribute("loginUser");
 %>   
<!DOCTYPE html >
<html>
<head>
<meta charset=UTF-8">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=2"></script>
<link rel="stylesheet" type = "text/css" href = "/semi/view/myPage/css/orderlist.css?ver=5">
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
	//주문취소
	$('.cancleorder').click(function(event){
		var oid = $(this).parent().parent().children().eq(1).html();
		var mid = '<%=loginUser.getMID()%>';
		
		if(confirm('주문을 취소하시겠습니까?'))
			location.href="/semi/cancleorder?oid="+oid+"&mid="+mid+"&check=cancle";
	});
	//구매확정
	$('.Completion').click(function(event){
		var oid = $(this).parent().parent().children().eq(1).html();
		var mid = '<%=loginUser.getMID()%>';
		
		alert('구매가 확정되었습니다.');
		location.href="/semi/cancleorder?oid="+oid+"&mid="+mid+"&check=Completion";
	});
	//반품신청
	$('.back').click(function(event){
		var oid = $(this).parent().parent().children().eq(1).html();
		var mid = '<%=loginUser.getMID()%>';
		
		alert('반품신청을 하시겠습니까?');
		location.href="/semi/cancleorder?oid="+oid+"&mid="+mid+"&check=back";
	});
	//반품취소
	$('.cback').click(function(event){
		var oid = $(this).parent().parent().children().eq(1).html();
		var mid = '<%=loginUser.getMID()%>';
		
		alert('반품신청을 하시겠습니까?');
		location.href="/semi/cancleorder?oid="+oid+"&mid="+mid+"&check=cback";

});
});


</script>
<title>주문 내역</title>
</head>
<body>
<header></header>

<label id="title">| OrderList</label>

<div id="title_div"><font color="gray"><%=loginUser.getMNAME() %></font> 님의 주문내역입니다.</div>
<%if(olist.size() > 0){%>

<div id="orderlist_div">
<table id="orderlist_table">
<tr><th>이미지</th><th>주문번호</th><th>상품이름</th><th>상품가격</th><th>수량</th><th>주문일자</th><th>처리상태</th>
<th>선택</th></tr>
<%for(int i = 0; i < olist.size(); i++) {%>
<tr>
<td><a href="/semi/detail?imageId=<%=pimage.get(i).getImageId()%>">
<image src="/semi/image/<%= pimage.get(i).getMainImage()%>" width="60" height="70">
</a></td>
<td><%= olist.get(i).getOID() %></td>
<td><a href="/semi/detail?imageId=<%=pimage.get(i).getImageId()%>">
<%= plist.get(i).getpName() %><br>
<font color="gray" size="2">ㄴ>옵션  사이즈 : <%=plist.get(i).getpSize() %> 색상 : <%=plist.get(i).getpColor() %></font>
</a></td>
<td><%= plist.get(i).getpPrice() %> 원</td>
<td><%= olist.get(i).getOQUANTITY() %> 개</td>
<td><%= olist.get(i).getODATE() %></td>
<td><font color="blue"><%= ostatus.get(i).getOsstatus() %></font></td>


<td>
<% if(olist.get(i).getOSNUMBER().equals("2")){%>
<input type="button" value="주문취소" class="cancleorder"> &nbsp;
<%}else if(olist.get(i).getOSNUMBER().equals("5")){%>
<input type="button" value="반품" class="back">
<%}else if(olist.get(i).getOSNUMBER().equals("10")){%>
<input type="button" value="반품취소" class="cback">
<%}else{%>
비고
<%}%>
<%if(olist.get(i).getOSNUMBER().equals("2")){%>
<input type="button" value="구매확정"  class="Completion"> &nbsp;
<%}%>
</td>
<tr>
<%}%>
</table>
</div>
<%}else{%>
<table id="orderlist_table">
<tr><th>이미지</th><th>주문번호</th><th>상품이름</th><th>상품가격</th><th>수량</th><th>주문일자</th><th>처리상태</th><th>선택</th></tr>
<tr><td colspan="10">주문내역이 없습니다.</td></tr>
</table>
<%}%>

<footer></footer>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "member.model.vo.Member, product.model.vo.Product, orderAndPay.model.vo.OrderList, java.util.*" %>
<%
  Member loginUser = (Member)session.getAttribute("loginUser");
  ArrayList<OrderList> list = (ArrayList<OrderList>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리</title>
<script type="text/javascript" src="/semi/common/js/jquery-3.1.1.min.js?ver=1"></script>
<link rel="stylesheet" type="text/css" href="/semi/view/manager/css/orderManager.css?ver=2">
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=2"></script>
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
	$('span#date').hide();
	$('select[name=orderSearch]').change(function(){
		if($(this).val()=='orderdate'){
			$('span#keyword').hide();
			$('span#date').show();
		}else{
			$('span#date').hide();
			$('span#keyword').show();
		}
	})
});
</script>
</head>
<body>
<title>주문 관리</title>
<header></header>
<div id="title">
	| ORDER
</div>
<section>	
<fieldset>
<legend>&nbsp;SEARCH&nbsp;</legend>
<form action="/semi/omsearch" method = "post">
	<div id="search">
	<select name="orderSearch">
		<option value="ordernumber">주문번호</option>
		<option value="pname">상품명</option>
		<option value="mid">주문자</option>
		<option value="orderdate">주문날짜</option>
		<option value="orderstatus">주문상태</option>
	</select>
	<span id="date">
	<input type="date" name="beforeDate"> - <input type="date" name="afterDate">
	</span>
	<span id="keyword">
	<input type="text" name="keyword">
	</span>
	<input type="submit" value="검색">
	</div>
	</form>
	</fieldset>
	<br><br>
	<div id="contents">
		<table cellspacing="0" cellpadding="0">
			<tr class="tr1" align="center" >
				<th  width="100px">주문번호</th>
				<th  width="100px">주문날짜</th>
				<th width="100px">회원ID</th>
				<th width="100px">주문자</th>
				<th width="150px">상품ID</th>
				<th width="100px">수량</th>
				<th width="100px">총 가격</th>
				<th width="200px">주문처리상태</th>
			</tr>
			<%if(list !=null){ %>
			<%for(OrderList o : list) {%>
			<tr align="center">
			<td><%=o.getOID() %></td>
			<td><%=o.getODATE() %></td>
			<td><a href="javascript:void(0);"onclick="window.open('/semi/mdetailview?mid=<%= o.getMID()%>','productView','scrollbars=yes,toolbar=no,resizable=no,width=500,height=650,left=0,top=0,margin=0');"><%=o.getMID() %></a></td>
			<td><a href="javascript:void(0);"onclick="window.open('/semi/odetailview?oid=<%= o.getOID()%>','productView','scrollbars=yes,toolbar=no,resizable=no,width=600,height=800,left=0,top=0,margin=0');"><%=o.getNAME() %></a></td>
			<td><a href="javascript:void(0);"onclick="window.open('/semi/pddetailview?pid=<%= o.getPID()%>','productView','scrollbars=yes,toolbar=no,resizable=no,width=500,height=550,left=0,top=0,margin=0');"><%=o.getPID() %></a></td>
			<td><%=o.getOQUANTITY() %></td>
			<td><%=o.getTOTALPRICE() %></td>
 <% if(o.getOSNUMBER().equals("1")){ %>
         <td>결제진행중</td>
<%}else if (o.getOSNUMBER().equals("2")){%>
<td>결제완료</td>
<%}else if (o.getOSNUMBER().equals("3")){%>
<td>배송준비중</td>
<%}else if (o.getOSNUMBER().equals("4")){%>
<td>배송진행중</td>
<%}else if (o.getOSNUMBER().equals("5")){%>
<td>배송완료</td>
<%}else if (o.getOSNUMBER().equals("6")){%>
<td>구매확정</td>
<%}else if (o.getOSNUMBER().equals("7")){%>
<td>구매완료</td>
<%}else if (o.getOSNUMBER().equals("8")){%>
<td>취소처리중</td>
<%}else if (o.getOSNUMBER().equals("9")){%>
<td>취소완료</td>
<%}else if (o.getOSNUMBER().equals("10")){%>
<td>반품처리중</td>
<%}else if (o.getOSNUMBER().equals("11")){%>
<td>반품취소</td>
<%}else if (o.getOSNUMBER().equals("12")){%>
<td>반품완료</td>
<%} %> 
			</tr>
			<%} %>
			<%} %>
		</table>
		
	</div>
	
	
</section>
<footer></footer>
</body>
</html>
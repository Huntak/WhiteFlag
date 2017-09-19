<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "member.model.vo.Member,java.util.*,plus.model.vo.Plus,stock.model.vo.Stock" %>
<%
  Member loginUser = (Member)session.getAttribute("loginUser");
  ArrayList<Plus> plusList = (ArrayList<Plus>)request.getAttribute("plusList");
  ArrayList<Stock> stockList = (ArrayList<Stock>)request.getAttribute("stockList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고 재고</title>
<script type="text/javascript" src="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=3"></script>
<link rel="stylesheet" type="text/css" href="/semi/view/manager/css/plusAndStock.css?ver=6">
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
function search(){
$.ajax({
	url : "/semi/stocksearch",
	data : {stocksearchType: $('#stockSearch option:selected').val(),stocksearchvalue: $('#stockvalue').val()},
	type : "get",
	dataType : "json",
	success : function(data){
		var values = '<tr class="tr1" align="center" id="stockMenu" >'+
			'<th class="id" width="70px">상품ID</th>'+
			'<th class="name" width="200px">상품명</th>'+
			'<th class="size" width="50px">사이즈</th>'+
		'<th class="" width="100px">색상</th>'+
			'<th class="" width="150px">재질</th>'+
			'<th class="" width="100px">가격</th>'+
			'<th class="" width="100px">시즌</th>'+
			'<th class="" width="100px">거래처코드</th>'+
			'<th width="70px">재고량</th>'+
		'</tr>';
		
		for(var i in data.list){
			values += '<tr align="center" style="border-top-bottom:1px solid lightgray">'+'<td>'+data.list[i].pid+'</td>'+'<td>'+data.list[i].pname+'</td>'+'<td>'+data.list[i].psize+'</td>'+'<td>'+data.list[i].pcolor+'</td>'+
			'<td>'+data.list[i].pmaterial+'</td>'+'<td>'+data.list[i].pprice+'</td>'+'<td>'+data.list[i].pseason+'</td>'+'<td>'+data.list[i].providercode+'</td>'+'<td>'+data.list[i].squantity+'</td>'+
						'</tr>';
		}
		
		$('#stockTable').html(values);
	}
		
	
})}

function search2(){

	$.ajax({
		url : "/semi/plusSearch",
		data : {plusSearchType: $('#plusSearch option:selected').val(),plusSearchvalue: $('#plusvalue').val(), 
			  plusSearchBeforeDate :   $('#beforeDate').val(),   plusSearchAfterDate :   $('#afterDate').val()  },
		type : "get",
		dataType : "json",
		success : function(data){
			var values = '<tr class="tr1" align="center" id="stockMenu" >'+		
			'<th class="id" width="70px">입고번호</th>'+
			'<th class="id" width="70px">상품ID</th>'+
			'<th class="name" width="200px">상품명</th>'+
			'<th class="size" width="50px">사이즈</th>'+
			'<th class="" width="100px">색상</th>'+
			'<th class="jazil" width="150px">재질</th>'+
			'<th class="" width="100px">가격</th>'+
			'<th class="" width="100px">시즌</th>'+
			'<th class="" width="100px">거래처코드</th>'+
			'<th class="" width="100px">입고가격</th>'+
			'<th class="" width="70px">입고갯수</th>'+
			'<th class="" width="100px">총합가격</th>'+
			'<th class="" width="100px">입고날짜</th>'+
			'</tr>';

			for(var i in data.list){
				////  for문 내역 수정하자.
				values += '<tr align="center" style="border-top-bottom:1px solid lightgray">'+'<td>'+data.list[i].pNum+'</td>'+'<td>'+data.list[i].pId+'</td>'+'<td>'+data.list[i].pName +'</td>'+'<td>'+data.list[i].pSize +'</td>'+
				'<td>'+data.list[i].pColor +'</td>'+'<td>'+data.list[i].pMaterial+'</td>'+'<td>'+data.list[i].pPrice+'</td>'+'<td>'+data.list[i].pSeason+'</td>'+
				'<td>'+data.list[i].pProvider+'</td>'+'<td>'+data.list[i].pPlusPrice+'</td>'+'<td>'+data.list[i].pPlusQuantity+'</td>'+'<td>'+data.list[i].pPlusTotal+'</td>'+
				'<td>'+data.list[i].pPlusDate+'</td>'+'</tr>';
			}
			
			$('#plusTable').html(values);
		}
	})}
	
	
$(function(){
	$('span#keyword').hide();
	$('select[name=memberSearch]').change(function(){
		if($(this).val()=='plusDate'){
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
</head>
<body>
<header></header>

	<div id="title">
	| PLUS & STOCK
</div>

<section>
<div id="subtitlePlus" class = "subtitle">
	| PLUS
</div>

<fieldset>
<legend>&nbsp;SEARCH&nbsp;</legend>
	<div id="search">
	<select>
		<option>회원ID</option>
		<option>주문날짜</option>
		<option>상품명</option>
	</select>
	<input type="text" ><input type="button" id="btnSea" value="검색">
	</div>
	</fieldset>

	<div id="contents">
		<table cellspacing="0" cellpadding="0" id="plusTable">
			<tr class="tr1" align="center"  >
				<th class="id" width="70px">입고번호</th>
				<th class="id" width="70px">상품ID</th>
				<th class="name" width="200px">상품명</th>
				<th class="size" width="50px">사이즈</th>
				<th class="" width="100px">색상</th>
				<th class="jazil" width="150px">재질</th>
				<th class="" width="100px">가격</th>
				<th class="" width="100px">시즌</th>
				<th class="" width="100px">거래처코드</th>
				<th class="" width="100px">입고가격</th>
				<th class="" width="70px">입고갯수</th>
				<th class="" width="100px">총합가격</th>
				<th class="" width="100px">입고날짜</th>	
			</tr>
			<%if(plusList != null){ %>
			<%for(Plus p : plusList){ %>
			<tr align="center" style="border-top-bottom:1px solid lightgray">
			<td><%= p.getPlusNumber() %></td>
			<td><%= p.getpId() %></td>
			<td><%= p.getpName() %></td>
			<td><%= p.getpSize() %></td>
			<td><%= p.getpColor() %></td>
			<td><%= p.getpMaterial() %></td>
			<td><%= p.getpPrice() %></td>
			<td><%= p.getpSeason() %></td>
			<td><%= p.getProvidercode() %></td>
			<td><%= p.getPlusPrice() %></td>
			<td><%= p.getPlusQuantity() %></td>
			<td><%= p.getPlusTotal() %></td>
			<td><%= p.getPlusDate()%></td>
			</tr>
			<%}}%>
		</table>
	
	</div>
	<div id="btnchange">
		<a href="/semi/newplusselect"><button id="change">입고</button></a>
		<a href="/semi/deleteplusselect"><button id="deletePlus">수정  및 삭제</button></a>  <!-- 수정 추가 -->
	</div>
	<div id="subtitleStock" class = "subtitle">
	| STOCK
</div>
<fieldset>
<legend>&nbsp;SEARCH&nbsp;</legend>

	<div id="search">
	<select id="stockSearch">
		<option value="pid">상품ID</option>
		<option value="pname">상품명</option>
		<option value="pmaterial">재질</option>
		<option value="pseason">시즌</option>
		<option value="providercode">거래처코드</option>
	</select>
	<input type="text" id="stockvalue" ><input type="button" id="btnSea" value="검색" onclick="search();">
	</div>
</fieldset>
	<div id="contents">
		<table cellspacing="0" cellpadding="0" id="stockTable">
			<tr class="tr1" align="center" id="stockMenu" >
				<th class="id" width="70px">상품ID</th>
				<th class="name" width="200px">상품명</th>
				<th class="size" width="50px">사이즈</th>
				<th class="" width="100px">색상</th>
				<th class="" width="150px">재질</th>
				<th class="" width="100px">가격</th>
				<th class="" width="100px">시즌</th>
				<th class="" width="100px">거래처코드</th>
				<th width="70px">재고량</th>
			</tr>
			<%if(stockList != null){ %>
			<%for(Stock s : stockList){ %>
			<tr align="center">
			
			<td><%= s.getpId() %></td>
			<td><%= s.getpName() %></td>
			<td><%= s.getpSize() %></td>
			<td><%= s.getpColor() %></td>
			<td><%= s.getpMaterial() %></td>
			<td><%= s.getpPrice() %></td>
			<td><%= s.getpSeason() %></td>
			<td><%= s.getProvidercode() %></td>
			<td><%= s.getsQuantity() %></td>
			
			</tr>
			<%}}%>
		</table>
	</div>
	

	
	</section>
<div>
<br>
<br><br><br>
</div>

	<footer></footer>
</body>
</html>
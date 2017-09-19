<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "member.model.vo.Member, product.model.vo.Product, java.util.*" %>
<%
  Member loginUser = (Member)session.getAttribute("loginUser");
  ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리</title>
<script type="text/javascript" src="/semi/common/js/jquery-3.1.1.min.js?ver=1"></script>
<link rel="stylesheet" type="text/css" href="/semi/view/manager/css/productManager.css?ver=4">
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=4"></script>
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
</head>
<body>
<title>상품 관리</title>
<header></header>
<div id="title">
	| PRODUCT MANAGER
</div>
<section>	
<fieldset>
<legend>&nbsp;SEARCH&nbsp;</legend>
<form action="/semi/searchpd" method="post">
	<div id="search">
	<select name="searchtype">
		<option value="pname">상품명</option>
		<option value="pseason">시즌</option>
		<option value="pmaterial">재질</option>
		<option value="category">카테고리</option>
		<option value="providercode">거래처코드</option>
	</select>
	<input type="text" name="keyword" ><button id="btnSea">검색</button>
	</div>
	</form>
	</fieldset>
	<br><br>
	<div id="contents">
		<table cellspacing="0" cellpadding="0">
			<tr class="tr1" align="center" >
				
				<th  width="300px">상품명</th>
				<th width="300px">재질</th>
				<th width="100px">가격</th>
				<th width="100px">시즌</th>
				<th width="200px">거래처코드</th>
				<th width="100px">카테고리</th>
			</tr>
			<%if (list.size()>0){ %>
			<%int j = 0;  %>
					 	<tr>
				<td><a  href="/semi/productselectone?productname=<%= list.get(j).getpName() %>"><%= list.get(j).getpName() %></a></td>
				<td><%= list.get(j).getpMaterial() %></td>
				<td><%= list.get(j).getpPrice() %></td>
				<td><%= list.get(j).getpSeason() %></td>
				<td><%= list.get(j).getProviderCode()%></td>
				<td><%= list.get(j).getcId()%></td>
				
				</tr> 
			<%
				for(int i = 1; i<list.size();i++){System.out.println(list.get(i));
					%>
					<%
					
					if(!(list.get(j).getpName().equals(list.get(i).getpName()))){
						
						%>
					 	<tr>
				<td><a  href="/semi/productselectone?productname=<%= list.get(i).getpName() %>"><%= list.get(i).getpName() %></a></td>
				<td><%= list.get(i).getpMaterial() %></td>
				<td><%= list.get(i).getpPrice() %></td>
				<td><%= list.get(i).getpSeason() %></td>
				<td><%= list.get(i).getProviderCode()%></td>
				<td><%= list.get(i).getcId()%></td>
				
				</tr> 
						
					<%j=i; }  %>
				<%}}%>
			
			
			
			
			<%-- <tr>
				<td><%= list.get(i).getpName() %></td>
				<td><%= list.get(i).getpMaterial() %></td>
				<td><%= list.get(i).getpPrice() %></td>
				<td><%= list.get(i).getpSeason() %></td>
				<td><%= list.get(i).getProviderCode()%></td>
				<td><%= list.get(i).getcId()%></td>
				</tr> --%>
		</table>
		
	</div>
	
	
	
</section>

	

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "member.model.vo.Member, provider.model.vo.Provider, java.util.*" %>
<% Member loginUser = (Member)session.getAttribute("loginUser"); 
	ArrayList<Provider> list = (ArrayList<Provider>)request.getAttribute("list");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/semi/common/js/jquery-3.1.1.min.js?ver=1"></script>
<link rel="stylesheet" type="text/css" href="/semi/view/manager/css/providerManager.css?ver=4">
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
</script>
</head>
<body>
<header></header>
	<div id="title">
	| PROVIDER MANAGER
	</div>
<section>	
<fieldset>
<legend>&nbsp;SEARCH&nbsp;</legend>
<form action="/semi/searchpv" method="post">
	<div id="search">
	<select name="searchtype">
		<option value="code">거래처코드</option>
		<option value="company">거래처명</option>
		<option value="tell">거래처번호</option>
		<option value="name">담당자명</option>
		<option value="phone">담당자번호</option>
		<option value="address">거래처주소</option>
	</select>
	<input type="text" name="keyword" ><input type="submit" value="검색" >
	
	</div>
	</form>
	</fieldset>
	<br><br>
	<div id="contents">
		<table cellspacing="0" cellpadding="0">
			<tr class="tr1" align="center" >
				<th  width="100px">코드</th>
				<th  width="100px">회사명</th>
				<th width="100px">담당자</th>
				<th width="150px">회사 번호</th>
				<th width="150px">담당자 전화번호</th>
				<th width="400px">회사 주소</th>
				<th width="400px">기타사항</th>	
			</tr>
			<%if(list != null){ %>
			<%for(Provider p : list){ %>
			<tr>
			<td><a href="javascript:void(0);" onclick="window.open('/semi/providerview?providercode=<%= p.getProviderCode() %>','memberView','scrollbars=yes,toolbar=no,resizable=no,width=650,height=910,left=0,top=0,margin=0');"><%= p.getProviderCode() %></a></td>
			<td><%= p.getProviderCompany() %></td>
			<td><%= p.getProviderName() %></td>
			<td><%= p.getProviderTell() %></td>
			<td><%= p.getProviderPhone() %></td>
			<td><%= p.getProviderAddress1() %>-<%= p.getProviderAddress2() %>&nbsp;<%= p.getProviderAddress3() %>&nbsp;<%= p.getProviderAddress4() %></td>
			<td><%= p.getProviderETC() %></td>
			</tr>
			<%}}%>
		</table>
		
	</div>
	<div id="btn">
	<a href ="/semi/view/manager/newProvider.jsp"><button>새 거래처 등록</button></a>
	</div>
	
</section>
<!-- <footer></footer> -->
</body>
</html>
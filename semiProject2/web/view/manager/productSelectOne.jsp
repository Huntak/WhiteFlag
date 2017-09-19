<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member, java.util.*"%>
<%@ page import = "member.model.vo.Member,java.util.*,product.model.vo.Product,provider.model.vo.Provider,category.model.vo.Category" %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
	ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("list");
	ArrayList<Provider> providercodelist = (ArrayList<Provider>)request.getAttribute("providercodelist");
	ArrayList<Category> categoryidlist = (ArrayList<Category> ) request.getAttribute("categoryidlist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>회원관리</title>
<link rel="stylesheet" type="text/css" href="/semi/view/manager/css/productSeletOne.css?ver=1">
<script type="text/javascript" src="/semi/common/js/jquery-3.1.1.min.js?ver=2"></script>
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
	$('.pname').keyup(function(){		
		var value = $(this).val();		
		$('.pname').val(value);				
	});
	$('.pmaterial').keyup(function(){		
		var value = $(this).val();		
		$('.pmaterial').val(value);				
	});
	$('.pseason').keyup(function(){		
		var value = $(this).val();		
		$('.pseason').val(value);				
	});
	$('.pprice').keyup(function(){		
		var value = $(this).val();		
		$('.pprice').val(value);				
	});
	$('.cid').change(function(){
		var value= $(this).val();
		$('.cid').val(value);
	});
	$('.providercode').change(function(){
		var value= $(this).val();
		$('.providercode').val(value);
	});
});
providercode

</script>

</head>
<body>
<header></header>
<div id="title">
	| PRODUCT
</div>
<section>	

	<br><br>
	<form action="/semi/updatepd" method="post">
	<div id="contents">
	
		<table cellspacing="0" cellpadding="0">
			<tr class="tr1" align="center" >
				<th width="80">상품ID</th>
				<th  width="200px">상품명</th>
				<th  width="100px">컬러</th>
				<th width="70px">사이즈</th>
				<th width="100px">재질</th>
				<th width="80px">가격</th>
				<th width="70px">시즌</th>
				<th width="70px">거래처코드</th>
				<th width="70px">카테고리</th>
				<th width="70px">이미지id</th>
				<th width="50px"></th>
			</tr>
			
			<%for(Product p : list){ %>
			<tr align="center" >
				<input type="hidden" name="pid" value="<%= p.getpId() %>">
				<input type="hidden" name="pcolor" value="<%= p.getpColor() %>">
				<input type="hidden" name="psize" value="<%= p.getpSize() %>">
				<input type="hidden" name="imageid" value="<%= p.getImageId() %>">
				<td><%= p.getpId() %></td>
				<td><input type="text" name="pname" value="<%=p.getpName() %>"class="pname"></td>
				<td><%=p.getpColor() %></td>
				<td><%=p.getpSize() %></td>
				<td><input type="text" name="pmaterial" value="<%=p.getpMaterial() %>" class="pmaterial"></td>
				<td><input type="text" name="pprice" value="<%=p.getpPrice() %>"class="pprice"></td>
				<td><input type="text" name="pseason" value="<%=p.getpSeason() %>"class="pseason"></td>
				<td>
				<select id="provider" name="providercode" class="providercode">
				<%for(Provider pv : providercodelist){ %>
				<option vlaue="<%=pv.getProviderCode() %>"id="<%=pv.getProviderCode() %>"><%=pv.getProviderCode() %></option>
				<script>
			$(function(){
				var pprovider = "<%= p.getProviderCode()%>";
				var provider = "<%=pv.getProviderCode() %>";
				if(pprovider == provider){
					$("select#provider").val(provider).selected();
				}
			});
			</script>
				<%} %>
				</select>
				</td>
				<td>
				<select id="cid" name="cid" class="cid">
				<%for(Category c : categoryidlist){ %>
				
					<option value="<%=c.getcId() %>"><%=c.getcId() %></option>
				<script>
			$(function(){
				var pcid = "<%= p.getcId()%>";
				var cid = "<%=c.getcId() %>";
				if(pcid == cid){
					$("select#cid").val(cid).selected();
				}
			});
			</script>
				<%} %>
				</select>
				</td>
				<td><label id="imageid"><%=p.getImageId() %></label></td>
				<td><a href="/semi/drowproduct?pid=<%= p.getpId()%>&pname=<%=p.getpName()%>"><input type="button" value="삭제"></a></td>
			</tr>
			<%} %>
			
		</table>
		
	</div>
	
	<div id="btnchange">
		<input type="submit" value="변경">
	</div>
	
	</form>
	
	<a href="javascript:void(0);"onclick="window.open('/semi/isovpro?pname=<%= list.get(0).getpName()%>','memberView','scrollbars=yes,toolbar=no,resizable=no,width=500,height=550,left=0,top=0,margin=0');"><input type="button"  value="추가"></a>
	<a href="/semi/pselectall"><input type="button" value="목록으로"></a>
	
	
</section>
<!-- <footer></footer> -->
</body>
</html>
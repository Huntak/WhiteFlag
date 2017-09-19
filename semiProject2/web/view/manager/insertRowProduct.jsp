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
$(function(){  
	opener.location.reload();
});
function insertpd(){
	
	$.ajax({
		url : "/semi/insertrowone",
		data : {providercode: $('input[name=providercode]').val(), pname: $('input[name=pname]').val(),
			pmaterial: $('input[name=pmaterial]').val(), pprice: $('input[name=pprice]').val(), pseason: $('input[name=pseason]').val(), 
			cid: $('input[name=cid]').val(), imageid: $('input[name=imageid]').val(), pcolor: $('input[name=pcolor]').val(), 
			psize: $('input[name=psize]').val(),pname: $('input[name=pname]').val()
			},
		
		type : "get",
		dataType : "json",
		success : function(data){
			
			if(data.result > 0){				
				opener.location.reload();
				self.close();
			}else{
				alert("추가실패!")
			} 
			
			
		}
	});
}
</script>
<title>상품 추가</title>
</head>
<body>
<div id="title">
	| INSERT PRODUCT
</div>

<input type="hidden" name="pname" value="<%=p.getpName() %>">
<input type="hidden" name="pmaterial" value="<%=p.getpMaterial() %>">
<input type="hidden" name="pprice" value="<%=p.getpPrice() %>">
<input type="hidden" name="pseason" value="<%=p.getpSeason() %>">
<input type="hidden" name="providercode" value="<%=p.getProviderCode() %>">
<input type="hidden" name="cid" value="<%=p.getcId() %>">
<input type="hidden" name="imageid" value="<%=p.getImageId() %>">
<table id="contents" cellspacing="0">
	<tr><td class="menu"><label>상품명</label></td><td class="item2"><label><%=p.getpName() %></label></td></tr>
	<tr><td class="menu"><label>컬러</label></td><td class="item2"><input type="text" name="pcolor" size="15"></td></tr>
	<tr><td class="menu"><label>사이즈</label></td><td class="item2"><input type="text" name="psize" size="5"></td></tr>
	<tr><td class="menu"><label>재질</label></td><td class="item2"><label><%=p.getpMaterial()%></label></td></tr>
	<tr><td class="menu"><label>가격</label></td><td class="item2"><label><%=p.getpPrice() %></label></td></tr>
	<tr><td class="menu"><label>시즌</label></td><td class="item2"><label><%=p.getpSeason() %></label></td></tr>
	<tr><td class="menu"><label>거래처코드</label></td><td class="item2"><label><%=p.getProviderCode() %></label></td></tr>
	<tr><td class="menu"><label>카테고리</label></td><td class="item2"><label><%=p.getcId() %></label></td></tr>
	<tr><td class="menu"><label>이미지id</label></td><td class="item2"><label><%=p.getImageId() %></label></td></tr>

</table>
<input id="submit" type="button" value="추가" onclick="insertpd();">


</body>
</html>
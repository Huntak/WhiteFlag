<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, member.model.vo.Member, cart.model.vo.Cart, product.model.vo.Product, product.model.vo.ProductImage" %>   
<%
	ArrayList<ProductImage> ProductImage = (ArrayList<ProductImage>)request.getAttribute("ProductImage");
	ArrayList<Product> pl = (ArrayList<Product>)request.getAttribute("ProdutList");
	ArrayList<Cart> cl = (ArrayList<Cart>)request.getAttribute("CartList");
	Member loginUser = (Member)session.getAttribute("loginUser");
	
%> 
<!DOCTYPE html">
<html>
<title>장바구니</title>
<head>
<meta charset=UTF-8">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js"></script>
<link rel="stylesheet" type = "text/css"  href ="/semi/view/cart/css/cart3.css?ver=9">
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
  		$('#allcheckbox').click(function(event){
  			if($("input:checkbox[id='checkox']").is(":checked") == false){
  	  		$('input:checkbox').attr('checked', 'true');
  			}else{
  				$('input:checkbox').attr('checked', false);
  			}
  		});
  		
  		 $('#topmenu').css('width', screen.width);
  		var a = 2500;
  		var b = Number($('#total1').html());
  		var c = '원';
  		$('#total2').html(a+b+c);
  		$('#total1').html($('#total1').html()+c);
  		
  	$('#selectOrder').click(function(event){
  		var s = $("input[name='checkbox']:checked").serialize();
  		if(s.length != 0){
  		var mid = $('#mid').val();
  		location.href="/semi/selectorder?"+ s+"&mid="+mid;
  	}else{
  		alert('주문할 상품을 선택해주세요');
  	}
  	
  	});
  	$('#deleteAll').click(function(event){
  		var s = $("input[name='checkbox']:checked").serialize();
  		if(s.length != 0){
  		var mid = $('#mid').val();
  		location.href="/semi/deleteAllcart?"+ s+"&userid="+mid;
  		}else{
  			alert('삭제할 상품을 선택해주세요');
  		}
  	});
 
  	});
  </script>
  </head>
  <body>
 <header></header>

 
<label id="cart_label">| CART</label>
<%if(loginUser != null){%>
 <div id="div_welcome" align="center">
저희 쇼핑몰을 이용해주셔서 감사합니다 
<label><font color="gray"><%= loginUser.getMNAME() %></font></label>님은, 
<label><font color="gray">[<%= loginUser.getGRADE() %>]</font></label> 회원이십니다.
</div>
<%}else{%>
 <div id="div_welcome2" align="center">
저희 쇼핑몰을 이용해주셔서 감사합니다 
<label><a href="/semi/view/member/loginForm.jsp"><font color="red">로그인</font></a> 후 이용해 주세요.</label>
</div>
<%}%>
<%if(loginUser != null){%>
 <input type="hidden" value="<%= loginUser.getMID()%>" id="mid">
<section id="cartinfo_section">
 <table id="cartinfo_table" >
  		<tr style="font-size:10pt;">
  		<th><input type="checkbox" id="allcheckbox"></th>
  		<th id="th_image_14">사진</th>
  		<th id="th_15">제품 상세</th>
  		<th>가격</th>
  		<th>색상</th>
  		<th>사이즈</th>
  		<th id="th_17">수량</th>
  		<th>적립금</th>
  		<th>배송비</th>
  		<th>총금액</th>
  		<th>선택</th>
  		</tr>
  	
  		
  		<tr>
  		<%if(pl != null && cl != null){
  			for(int i =0;  i < cl.size(); i++){
  		int a = 0; a+= pl.get(i).getpPrice();%>
  		<td width="2%;"><input type="checkbox" value="<%= cl.get(i).getCartId()%>" name="checkbox" id="checkox"></td>
  		<td width="12%;"><div id="image"><a href="/semi/detail?imageId=<%= ProductImage.get(i).getImageId()%>"><image src="/semi/image/<%= ProductImage.get(i).getMainImage() %>" width="90" height="100" ></a></div></td>
  		<td width="40%;"><a href="/semi/detail?imageId=<%= ProductImage.get(i).getImageId()%>"><%= pl.get(i).getpName() %></a></td>
  		<td width="7%;"  id="price"><%= pl.get(i).getpPrice()%>원</td>
  		<td><%= pl.get(i).getpColor() %></td>
  		<td><%= pl.get(i).getpSize() %></td>
  		
  		<form action="quantity" method="post">
  		<td width="2%;"><input type="number" min="0" max="10" value="<%= cl.get(i).getQuantity()%>" name="quantity">
  		<input type ="hidden" value="<%= loginUser.getMID()%>" name="mid" id="mid">
  		<input type ="hidden" value="<%= cl.get(i).getCartId()%>" name="cartid">
  		<input type ="hidden" value="<%= pl.get(i).getpId() %>" name="pid">
  		<input type="submit" value="변경" id="change_quantity_button">
  		</td>
  		</form>
  		
  		
  		<td width="4%;">
  		<% if(loginUser != null){double mileageRate = (double)request.getAttribute("mileageRate");  %>
  		<%= Math.round((float)mileageRate * pl.get(i).getpPrice()) %>
  		<%}%>
  		</td>
  		<td width="4%;">조건</td>
  		<td width="7%;"><%= pl.get(i).getpPrice() * cl.get(i).getQuantity()%>원</td>
  		<td width="7%;"><a href="/semi/ordero?cartid=<%= cl.get(i).getCartId() %>&userid=<%= loginUser.getMID()%>&mainimage=<%= ProductImage.get(i).getMainImage()%>&imageid=<%=ProductImage.get(i).getImageId() %>">
  		<button id="order_button" onclick="">주문</button></a><p><p>
  		<a href="/semi/delete?cartid=<%= cl.get(i).getCartId() %>&userid=<%= loginUser.getMID()%>">
  		<button id="delete_button" >삭제</button></a></td></tr>
  		<%}%>
  		</table>
 </section>
 
  <%if( cl.size() != 0){ %>
  <section id="pay_section">
   <table id="pay_table">
   	<tr id="pay_tr">
   	<th width="20%;" >총 상품금액</th>
   	<th width="20%;" >총 배송비</th>
   	<th width="60%;">결제예정금액</th></tr>
   	<tr align="center" style="font-size:14pt;">
   	<td id="total1"><% int a = 0; int b = 0; int c = 0; for(int i =0;  i < pl.size(); i++){
  		 a= pl.get(i).getpPrice();
  		 b = cl.get(i).getQuantity();
  			c += a*b;  }   out.print(c);%></td>	
   	<td id="delivery">2500원</td>
   	<td id="total2"></td></tr>
   </table>
   </section>
   <%}}%>
   
  <section id="order_buttons_section">
  <table id="order_buttons_table">
  <tr align="center">
	<td>
   <%if(cl.size() != 0){ %>
	<a href="/semi/orderall?mid=<%= loginUser.getMID()%>"><button  class="order_all_button">전체상품주문</button></a>
  	<a href="#"  ><button  class="order_all_button" id="selectOrder">선택상품주문</button></a>
  	<a href="#"  ><button  class="order_all_button" id="deleteAll">선택상품삭제</button></a>
  <%}else{%>
	<a href="#"><button  class="order_all_button">전체상품주문</button></a>
  	<a href="#"  ><button  class="order_all_button" >선택상품주문</button></a>
  	<a href="#"  ><button  class="order_all_button" >선택상품삭제</button></a>
  	<a href="/semi/index.jsp"  ><button  class="order_all_button">쇼핑계속하기</button></a></td>
  <%}}%>
  	
  </tr>
  </table>
  </section>


  <footer></footer>
  </body>
 </html>
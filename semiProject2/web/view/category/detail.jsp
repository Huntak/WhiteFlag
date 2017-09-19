<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member, product.model.vo.*, java.util.*, qanda.model.vo.*, review.model.vo.*, stock.model.vo.*"%>
<% 
	ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("productList");
	ArrayList<Stock> stockList = (ArrayList<Stock>)request.getAttribute("stockList");;
	ProductImage productImage  = (ProductImage)request.getAttribute("productImage");
	
	Product[] p = new Product[productList.size()];
	
	for(int i = 0; i < productList.size(); i++){ 
		p[i] = productList.get(i);
	}
	
	Member loginUser = (Member)session.getAttribute("loginUser");
	
	// session에서 멤버받아서 적립금 계산하기
	double mileageRate = (Double)request.getAttribute("mileageRate");
	int mileage = 0;
	
	if(mileageRate != 0){
		float mileagef = (p[0].getpPrice()) * (float)mileageRate;
		mileage = Math.round(mileagef);
	}
	
	/* System.out.println(request.getAttribute("listCountR")); */
	int listCountR = (Integer)request.getAttribute("listCountR");
	int listCountQ = (Integer)request.getAttribute("listCountQ");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail</title>
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=2"></script>
<link rel="stylesheet" type="text/css" href="/semi/view/category/css/detail.css?ver=27">
<script type="text/javascript" src ="/semi/view/category/js/detail.js?ver=27"></script>
<script type="text/javascript">
function listR(pageR){
	var query = "imageId=" + <%= productImage.getImageId() %> + "&pageR=" + pageR;
	var url="/semi/dReviewList";
	
	$.ajax({
		type:"get",
		url:url,
		data:query,
		dataType:"json",
		success:function(data){
			<% if(loginUser != null){ %>
			printListR(data, "<%=loginUser.getMID()%>");
			<% }else{ %>
			printListR(data, null);
			<% } %>
		},
		error:function(e){
			console.log(e.responseText);
		}
	});
}

function listQ(pageQ){
	var query = "imageId=" + <%= productImage.getImageId() %> + "&pageQ=" + pageQ;
	var url="/semi/dQnaList";
	
	$.ajax({
		type:"get",
		url:url,
		data:query,
		dataType:"json",
		success:function(data){
			<% if(loginUser != null){ %>
			printListQ(data, "<%=loginUser.getMID()%>");
			<% }else{ %>
			printListQ(data, null);
			<% } %>
		},
		error:function(e){
			console.log(e.responseText);
		}
	});
}

jQuery(function($){
    $('#scrolltotop').hide();
    
    $(function () {
        $(window).scroll(function () {
            if ($(this).scrollTop() > 100) {
                $('#scrolltotop').fadeIn();
            } else {
                $('#scrolltotop').fadeOut();
            }
        });
        
        $('#scrolltotop a').click(function () {
            $('body,html').animate({
                scrollTop: 0
            }, 500);
            return false;
        });
    });
});

$(function(){
	$("select").change(function(){
		if($(this).attr("id") == "selectSize"){
			if($("#selectColor").val() == ""){
				alert("색상을 먼저 선택하세요");	
				$("#selectColor").focus();
				$("#selectSize").val("");
			}
		}else{
			$("#selectSize").val("");
			var selectedColor = $("#selectColor").val();
			$("#selectSize").find('option:gt(1)').each(function(){
				$(this).text($(this).val());
				
				var selectedSize = $(this).val();
					
				<% for(int i = 0; i < stockList.size(); i++){ %>
				var stockColor = "<%= stockList.get(i).getpColor() %>";
				var stockSize = "<%= stockList.get(i).getpSize() %>";
				var stockQuantity = "<%= stockList.get(i).getsQuantity() %>";
				
				if(selectedColor == stockColor){
					if(selectedSize == stockSize){
						$(this).text($(this).text() + " (재고수량 : <%= stockList.get(i).getsQuantity() %>개)");
					}
				}
				<% } %>
			});
		}
	});
});
</script>
</head>
<body>
<header></header>
	<section>
	<form name="mysubmit" method="post">
	<div id="purchaseDiv">
	<table id="purchaseTable">
			<tr>
			<% if(loginUser != null){ %>
			<input type="hidden" name="userid" value="<%= loginUser.getMID() %>">
			<input type="hidden" name="pname" value="<%= p[0].getpName() %>">
			<input type="hidden" name="mileage" value="<%= mileage %>">
			<input type="hidden" name="imageid" value="<%= productImage.getImageId() %>">
			<% } %>
				<td class="tdOuter"><a href="#"><img src="/semi/image/<%= productImage.getMainImage() %>" width="auto" height="auto" border="0" alt=""></a></td>
				<td class="tdOuter" valign=top>
					<table id="purchaseTableOption">
						<tr>
							<td COLSPAN=2><img src="/semi/view/category/images/new.gif" width="48" height="15" border="0" alt=""></td>
						</tr>
						<tr>
							<td COLSPAN=2 id="td1"><%= p[0].getpName() %></td>
						</tr>
						<tr>
							<td id="td2">상품명</td>
							<td id="td3" class="form"><%= p[0].getpName() %></td>
						</tr>
						<tr>
							<td>PRICE</td>
							<td><%= p[0].getpPrice() %>won</td>
						</tr>
						<tr>
							<td>SAVE POINT</td>
							<% 
								if(mileageRate != 0){
							%>
							<td><%= mileage %>won(<%= mileageRate * 100 %>%)</td>
							<% 	}else{	%>
							<td>로그인을 해주세요</td>	
							<%	} %>
						</tr>
						<tr>
							<td>QUANTITY</td>
							<td>
								<input type="number" id="selectQuantity" min="1" value="1" name="quantity">
							</td>
						</tr>
						<tr>
							<td>COLOR</td>
							<td>
								<select id="selectColor" name="color" required>
									<option value="" selected>옵션선택
									<option value="">-------------------
									<option value="<%= p[0].getpColor() %>"><%= p[0].getpColor() %>
									<% 
										for(int i = 0; i < productList.size(); i++){ 
											for(int j = 0; j < i; j++){
												if(p[j].getpColor().equals(p[i].getpColor())){
													break;
												}
												else if(j == (i - 1)){
									%>
									<option value="<%= p[i].getpColor() %>"><%= p[i].getpColor() %>
									<% }}} %>
								</select>
							</td>
						</tr>
						<tr>
							<td>SIZE</td>
							<td>
								<select id="selectSize" name="size" required>
									<option value="" selected>옵션선택
									<option value="">-------------------
									<option value="<%= p[0].getpSize() %>"><%= p[0].getpSize() %>
									<% 
										for(int i = 0; i < productList.size(); i++){ 
											for(int j = 0; j < i; j++){
												if(p[j].getpSize().equals(p[i].getpSize())){
													break;
												}
												else if(j == (i - 1)){
									%>
									<option value="<%= p[i].getpSize() %>"><%= p[i].getpSize() %>
									<% }}} %>
								</select>
							</td>
						</tr>
						<tr>
							<td COLSPAN=2>&nbsp;</td>
						</tr>
						<%	if(loginUser != null){%>
						<tr>
							<td COLSPAN=2 class="buttonTd"><a href="#"><input type="button" id="buyNowButton" value="BUY NOW" onclick="a(1)"></a></td>
						</tr>
						<tr>
							<td COLSPAN=2 class="buttonTd"><a href="#"><input type="button" id="addToCartButton" value="ADD TO CART" onclick="a(2)"></a></td>
						</tr>
						<% 	}else{ %>
						<tr>
							<td COLSPAN=2 class="buttonTd"><a href="/semi/view/member/loginForm.jsp"><input type="button" id="buyNowButton" value="BUY NOW"></a></td>
						</tr>
						<tr>
							<td COLSPAN=2 class="buttonTd"><a href="/semi/view/member/loginForm.jsp"><input type="button" id="addToCartButton" value="ADD TO CART"></a></td>
						</tr>
						<% 	} %>
					</table>
					
					<br>
					<table id="purchaseTableEtc">
						<tr>
							<td>
								<a href="/semi/rlist">REVIEW ( <%= listCountR %> ) </a>
							</td>
							<td id="space"></td>
							<td>
								<a href="/semi/qlist">Q & A ( <%= listCountQ %> ) </a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	</form>
		<br> <br>
		<div id="productDetailDiv">
			<% if(productImage.getImage1() != null){ %>
			<img src="/semi/image/<%= productImage.getImage1() %>" width="auto" height="auto" border="0" alt="">
			<% } if(productImage.getImage2() != null){ %>
			<img src="/semi/image/<%= productImage.getImage2() %>" width="auto" height="auto" border="0" alt="">
			<% } if(productImage.getImage3() != null){ %>
			<img src="/semi/image/<%= productImage.getImage3() %>" width="auto" height="auto" border="0" alt="">
			<% } if(productImage.getImage4() != null){ %>
			<img src="/semi/image/<%= productImage.getImage4() %>" width="auto" height="auto" border="0" alt="">
			<% } if(productImage.getImage5() != null){ %>
			<img src="/semi/image/<%= productImage.getImage5() %>" width="auto" height="auto" border="0" alt="">
			<% } if(productImage.getImage6() != null){ %>
			<img src="/semi/image/<%= productImage.getImage6() %>" width="auto" height="auto" border="0" alt="">
			<% } if(productImage.getImage7() != null){ %>
			<img src="/semi/image/<%= productImage.getImage7() %>" width="auto" height="auto" border="0" alt="">
			<% } if(productImage.getImage8() != null){ %>
			<img src="/semi/image/<%= productImage.getImage8() %>" width="auto" height="auto" border="0" alt="">
			<% } if(productImage.getImage9() != null){ %>
			<img src="/semi/image/<%= productImage.getImage9() %>" width="auto" height="auto" border="0" alt="">
			<% } if(productImage.getImage10() != null){ %>
			<img src="/semi/image/<%= productImage.getImage10() %>" width="auto" height="auto" border="0" alt="">
			<% } %>
			<div id="review">
				<label class="label1">REVIEW</label><label class="label2"> : WRITE YOUR REVIEWS~!</label>
				<br><br>
				<table id="reviewTable"></table>
			</div>
			<br> <br><br> <br>
			<div id="qna">
				<label class="label1">Q & A</label><label class="label2"> : HAVE A QUESTION?</label>
				<br><br>
				<table id="qnaTable"></table>
			</div>
			<div id="scrolltotop"><a href="#top"><span><img src="/semi/common/images/gototop_over.png"></span></a></div>
			<br><br><br><br><br><br><br><br>
		</div>
	</section>
	<footer></footer>
</body>
</html>
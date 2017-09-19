<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member, product.model.vo.*, java.util.*"%>
<% 
	ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("productList");
	ArrayList<ProductImage> productImageList = (ArrayList<ProductImage>)request.getAttribute("productImageList");
	
	Member loginUser = (Member)session.getAttribute("loginUser");
	
	// 중복 제거 하여 Product, ProductImage 객체배열에 담기
	Product[] p = new Product[productImageList.size()];
	ProductImage[] pi = new ProductImage[productImageList.size()];
	
	int indexP = 0;
	if(productList.size() > 0 && productImageList.size() > 0){
		p[0] = productList.get(0);
		for(int k = 1; k < productList.size(); k++){
			if(!(p[indexP].getpName().equals(productList.get(k).getpName()))){
				indexP++;
				p[indexP] = productList.get(k);
				//System.out.println(p[indexPi]);
			}
		}
		indexP++;
		
		int indexPi = 0;
		pi[0] = productImageList.get(0);
		for(int k = 1; k < productImageList.size(); k++){
			if(pi[indexPi].getImageId() != productImageList.get(k).getImageId()){
				indexPi++;
				pi[indexPi] = productImageList.get(k);
				//System.out.println(pi[indexPi]);
			}
		}
		indexPi++;
	}

	
	// 출력 확인용
	/* for(int i = 0; i < p.length; i++){
		System.out.println(p[i]);
	}
	
	for(int i = 0; i < pi.length; i++){
		System.out.println(pi[i]);
	} */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search</title>
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=2"></script>
<link rel="stylesheet" type="text/css" href="/semi/view/category/css/search.css?ver=6">
<script type="text/javascript" src ="/semi/view/category/js/search.js?ver=6"></script>
</head>
<body>
<header></header>
	<section>
		<div id="searchDiv">
			<label id="label1">| Search</label> <br>
			<section>
				<form action="/semi/dsearch" method="post">
				<table>
					<tr>
						<td id="td1">상품분류</td>
						<td id="td2"><select id="selectProduct" name="cId">
							<option value="" selected>상품분류 선택
							<option value="t">TOP
							<option value="tm">TOP > MTM
							<option value="tk">TOP > KNIT
							<option value="tt">TOP > T-SHIRTS
							<option value="ts">TOP > SHIRTS
							<option value="p">PANTS
							<option value="ps">PANTS > SLACKS
							<option value="pje">PANTS > JEANS
							<option value="pc">PANTS > COTTON
							<option value="pjg">PANTS > JOGGER
							<option value="o">OUTER
							<option value="ocoa">OUTER > COAT
							<option value="ojum">OUTER > JUMPER
							<option value="ojac">OUTER > JACKET
							<option value="os">OUTER > SUIT
							<option value="ocar">OUTER > CARDIGAN
							<option value="ov">OUTER > VEST
							<option value="ol">OUTER > LEATHER
							<option value="ob">OUTER > BLOUSON
						</select></td>
					</tr>
					<tr>
						<td>검색조건</td>
						<td><input type="text" id="searchCondition" name="searchCondition"></td>
					</tr>
					<tr>
						<td></td>
						<td><label id="hotSearch">인기검색어</label></td>
					</tr>
					<tr>
						<td>판매가격대</td>
						<td><input type="number" id="searchPrice1" name="searchPrice1">~<input
							type="number" id="searchPrice2" name="searchPrice2"></td>
					</tr>
					<tr>
						<td>검색정렬기준</td>
						<td><select id="searchOrder" name="searchOrder">
								<option value="" selected>::: 기준선택 :::
								<option value="plusdate desc">신상품 순
								<option value="pname asc">상품명(↑) 순
								<option value="pname desc">상품명(↓) 순
								<option value="pprice asc">낮은가격 순
								<option value="pprice desc">높은가격 순
								<option value="providercompany asc">제조사 순
						</select></td>
					</tr>
				</table>
				<br> <button>SEARCH >></button>
				</form>
			</section>
			<br> <br> <br> <br> <br> <br> <br> <br> <br>
			<% if(productImageList.size() > 0){ %>
			<div id="searchResult">showing all <%= indexP %> items
				<form action="/semi/sorder" method="post" style="display: inline">
					<input type="hidden" name="searchOrder" value="plusdate desc">
					<% for(int k = 0; k < indexP; k++){ %>
					<input type="hidden" name="pNameList" value="<%= p[k].getpName() %>">
					<% } %>
					<a><input type="submit" value="신상품 |" style="border:none; background:none;"></a>
				</form>
				<form action="/semi/sorder" method="post" style="display: inline">
					<input type="hidden" name="searchOrder" value="pname asc">
					<% for(int k = 0; k < indexP; k++){ %>
					<input type="hidden" name="pNameList" value="<%= p[k].getpName() %>">
					<% } %>
					<a><input type="submit" value="상품명(↑) |" style="border:none; background:none;"></a>
				</form>
				<form action="/semi/sorder" method="post" style="display: inline">
					<input type="hidden" name="searchOrder" value="pname desc">
					<% for(int k = 0; k < indexP; k++){ %>
					<input type="hidden" name="pNameList" value="<%= p[k].getpName() %>">
					<% } %>
					<a><input type="submit" value="상품명(↓) |" style="border:none; background:none;"></a>
				</form>
				<form action="/semi/sorder" method="post" style="display: inline">
					<input type="hidden" name="searchOrder" value="pprice asc">
					<% for(int k = 0; k < indexP; k++){ %>
					<input type="hidden" name="pNameList" value="<%= p[k].getpName() %>">
					<% } %>
					<a><input type="submit" value="낮은가격 |" style="border:none; background:none;"></a>
				</form>
				<form action="/semi/sorder" method="post" style="display: inline">
					<input type="hidden" name="searchOrder" value="pprice desc">
					<% for(int k = 0; k < indexP; k++){ %>
					<input type="hidden" name="pNameList" value="<%= p[k].getpName() %>">
					<% } %>
					<a><input type="submit" value="높은가격 |" style="border:none; background:none;"></a>
				</form>
				<form action="/semi/sorder" method="post" style="display: inline">
					<input type="hidden" name="searchOrder" value="providercompany asc">
					<% for(int k = 0; k < indexP; k++){ %>
					<input type="hidden" name="pNameList" value="<%= p[k].getpName() %>">
					<% } %>
					<a><input type="submit" value="제조사 |" style="border:none; background:none;"></a>
				</form>
			</div>
			<% }else{ %>
			<div id="searchResult">showing all <%= indexP %> items
			<% } %>
		</div>
		<br> <br>
		<% if(productImageList.size() > 0){ %>
		<div id="productImgDiv">
			<table>
			<% 
				for(int k = 0; k < indexP; k++){
				if(p[k] != null && pi[k] != null){
				if(k == 0 || k % 4 == 0){
			%>
			<tr>
				<td>
					<div class="image">
						<a href="/semi/detail?imageId=<%= pi[k].getImageId() %>">
						<img src="/semi/image/<%= pi[k].getMainImage() %>" width="300" height="460" border="0" alt=""></a>
						<div class="text">
							<%= p[k].getpName() %><br>
							PRICE <%= p[k].getpPrice() %>won
						</div>
					</div>
				</td>
			<% 
				} else if(k % 3 == 0){
			%>
				<td>
					<div class="image">
						<a href="/semi/detail?imageId=<%= pi[k].getImageId() %>">
						<img src="/semi/image/<%= pi[k].getMainImage() %>" width="300" height="460" border="0" alt=""></a>
						<div class="text">
							<%= p[k].getpName() %><br>
							PRICE <%= p[k].getpPrice() %>won
						</div>
					</div>
				</td>
			</tr>
			<% 
				}else{
			%>
				<td>
					<div class="image">
						<a href="/semi/detail?imageId=<%= pi[k].getImageId() %>">
						<img src="/semi/image/<%= pi[k].getMainImage() %>" width="300" height="460" border="0" alt=""></a>
						<div class="text">
							<%= p[k].getpName() %><br>
							PRICE <%= p[k].getpPrice() %>won
						</div>
					</div>
				</td>
			<% }}} %>
			</table>
			</div>
			<% }else { %>
			<div id="productImgDiv">
			<table>
				<tr>
					<td>
						<div class="image">
							<a href="#">
							<img src="/semi/view/category/images/more-coming-soon.png" width="700" height="336" border="0" alt=""></a>
							<div class="textNo">
								상품이 없습니다.
							</div>
						</div>
					</td>
				</tr>
			</table>
			</div>
			<% } %>
	<footer></footer>
	</section>
</body>
</html>
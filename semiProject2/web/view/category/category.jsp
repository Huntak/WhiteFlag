<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member, product.model.vo.*, java.util.*"%>
<% 
	ArrayList<ProductImage> productImageList = (ArrayList<ProductImage>)request.getAttribute("productImageList");
	ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("productList");
	String category = (String)request.getAttribute("category");
	
	ProductImage[] pi = new ProductImage[productImageList.size()];
	Product[] p = new Product[productImageList.size()];
	
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Top</title>
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=1"></script>
<link rel="stylesheet" type="text/css" href="/semi/view/category/css/category.css?ver=8">
<script type="text/javascript" src ="/semi/view/category/js/category.js?ver=8"></script>
</head>
<body>
	<header></header>
	<section>
		<div id="categoryDiv">
		<% if(category.equals("t")){ %>
			<label id="label1">| TOP</label> <br> <br> <br>
			<table>
					<tr>
						<td><a href="/semi/category?cId=tm">MTM</a></td>
						<td><a href="/semi/category?cId=tk">KNIT</a></td>
						<td><a href="/semi/category?cId=tt">T-SHIRTS</a></td>
						<td><a href="/semi/category?cId=ts">SHIRTS</a></td>
					</tr>
			</table>
		<% }else if(category.equals("p")){ %>
			<label id="label1">| PANTS</label> <br> <br> <br>
			<table>
					<tr>
						<td><a href="/semi/category?cId=ps">SLACKS</a></td>
						<td><a href="/semi/category?cId=pje">JEANS</a></td>
						<td><a href="/semi/category?cId=pc">COTTON</a></td>
						<td><a href="/semi/category?cId=pjg">JOGGER </a></td>
					</tr>
			</table>
		<% }else if(category.equals("o")){ %>
			<label id="label1">| OUTER</label> <br> <br> <br>
			<table>
					<tr>
						<td><a href="/semi/category?cId=ocoa">COAT</a></td> 
						<td><a href="/semi/category?cId=ojum">JUMPER</a></td>  
						<td><a href="/semi/category?cId=ojac">JACKET</a></td> 
						<td><a href="/semi/category?cId=os">SUIT</a></td> 
						<td><a href="/semi/category?cId=ocar">CARDIGAN</a></td>  
						<td><a href="/semi/category?cId=ov">VEST</a></td> 
						<td><a href="/semi/category?cId=ol">LEATHER</a></td> 
						<td><a href="/semi/category?cId=ob">BLOUSON</a></td> 	
					</tr>
			</table>
		<% } %>
			<br> <br> <br> <br> <br> <br> <br>
			<label id="label2">BEST SELLERS<br> <br></label>
		</div>
		<% if(productImageList.size() > 0){ %>
		<div id="productImgDiv">
			<table>
			<% 
				for(int k = 0; k < productImageList.size(); k++){
					pi[k] = productImageList.get(k);
				}
			
				int i = 0;
				p[0] = productList.get(0);
				for(int k = 1; k < productList.size(); k++){
					if(!(p[i].getpName().equals(productList.get(k).getpName()))){
						i++;
						p[i] = productList.get(k);
					}
				}
				
				for(int k = 0; k < p.length; k++){
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
			<% }} %>
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
	</section>
	<footer></footer>
</body>
</html>
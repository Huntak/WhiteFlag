<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, review.model.vo.Review, member.model.vo.Member"%>
<%@ page import="member.model.vo.Member, java.util.*, review.model.vo.Review, product.model.vo.*" %>
<% 
	Member loginUser = (Member)session.getAttribute("loginUser");
	ArrayList<Review> list = (ArrayList<Review>)request.getAttribute("list");
	ArrayList<Product> listP = (ArrayList<Product>)request.getAttribute("listP");
	ArrayList<ProductImage> listPi = (ArrayList<ProductImage>)request.getAttribute("listPi");
	
	// 총 목록수
		int listCount = ((Integer)request.getAttribute("listCount")).intValue();
		System.out.print(listCount);
		// 현재 보여질 목록 페이지
		int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
		System.out.print(currentPage);
		// 현재 페이지(19)의 시작 페이지(11)
		int startPage = ((Integer)request.getAttribute("startPage")).intValue();
		System.out.print(startPage);
		// 현재 페이지(19)의 마지막 페이지(20)
		int endPage = ((Integer)request.getAttribute("endPage")).intValue();
		System.out.print(endPage);
		
		// 맨 마지막 페이지
		int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
		System.out.print(maxPage);
		String keyword = (String)request.getAttribute("keyword");
	 	String selectedItem = (String)request.getAttribute("selectedItem");
	 
		/* 
			총 목록수가 1372개 일때, 한 페이지에 목록을 10개씩 보이게 한다면 138페이지가 됨
			[맨처음][이전]11 12 13 .... 20[다음][맨마지막]
		*/
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review</title>
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js"></script>
<link rel = "stylesheet" type = "text/css" href = "/semi/view/noticeBoard/css/notice.css?ver=15">
<script type="text/javascript"  >
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
<style type="text/css">
</style>

</head>
<body>
<header></header>
<div id="rcenter" >	
<div id="rmainQ">| REVIEW <td> :: After Review</td></div>
<div id="rtd">
	<table border="0" align="center" cellspacing=0 id="rtable">
		<tr style="" id="tr22">
	 		<th id="no" class="no">no</th>
	 		<th id="item" class="item" >item</th>
	 		<th id="subject" class="subject" scope="col">subject</th>
	 		<th id="name" class="name" scope="col">name</th>
	 		<th id="hit">hit</th>
	 	</tr>
	<% 
 	 if(list != null){
 	 for(int i = 0; i < list.size(); i++){ %>  
 	 	<tr>
 	 		<td id="rtdn"><%= list.get(i).getrNumber()%></td>
 	 		<td>
	 	 		<%if(list.get(i).getpId() != null){ %> <!-- 그림을 넣지않았을경우 -->
	 	 		<a  href="/semi/detail?imageId=<%= listP.get(i).getImageId() %>">
	 	 		<img src="/semi/image/<%=listPi.get(i).getMainImage() %>"  width="50" height="70">
	 	 		</a>
	 	 		<%} %>
 	 		</td>
 	 		<%if(loginUser != null){ %>
 	 		<td id="rsub">
 	 		<%if(listP.get(i).getpName() != null){ %>
 	 			<a href="/semi/rdetail?rnumber=<%= list.get(i).getrNumber()%>" id="rsub1"><%= listP.get(i).getpName() %></a><br>
 	 			<%} %>
 	 			<a href="/semi/rdetail?rnumber=<%= list.get(i).getrNumber()%>" id="rsub2"><%= list.get(i).getrTitle() %></a>
 	 			<% if(list.get(i).getrReadcount() >= 100){ %>
				&nbsp;<img src="/semi/view/category/images/icon_hit.gif" width="18" height="9" border="0">
				<% } %>
 	 		</td>
 	 			<%}else{ %>
 	 		<td  id="rsub">
 	 		<%if(listP.get(i).getpName() != null){ %>
 	 			<a href="/semi/view/member/loginForm.jsp" id="rsub1"><%= listP.get(i).getpName() %></a><br>
 	 			<%} %>
 	 			<a href="/semi/view/member/loginForm.jsp" id="rsub2"><%= list.get(i).getrTitle() %></a>
 	 			<% if(list.get(i).getrReadcount() >= 100){ %>
				&nbsp;<img src="/semi/view/category/images/icon_hit.gif" width="18" height="9" border="0">
				<% } %>
 	 		</td>
 	 		<%} %>
			<td id="rid"><%=list.get(i).getmId() %></td>
			<td id="rhit"><%= list.get(i).getrReadcount() %></td>
 	 	</tr>
	 	 <%} }else{%>
	 		 <tr><tr>
	 	 <%} %>
	</table>
	<form action = "/semi/rsearch" >
		<table>
			<tr>
				<td align="left">
					<select name="selectedItem" id="search">
						<option value="title"> 제목</option>
						<option value="content">내용</option>
						<option value="mid">아이디</option>
						<option value="pid">상품코드</option>
						<option value="number">글번호</option>
					</select>
						<input type = "search" name = "keyword" id="search">
						<input type = "submit" value = "검색">
					</td>
					<td align="right">
				<%if(loginUser != null){ %><!-- 로그인하면 write버튼 보이게 처리 -->
					<a href="/semi/reviewToWrite" id="rright1">
						<input type="button" value="write" style="width:28%; height:28px; border:1px solid black;background:white;font-style:italic;font-weight:bold; cursor : pointer;">
					</a>
				<%} %>
				</td>
			</tr>
		</table>
	</form>	
			<div id="paging_div">
				<table id="paging_table">
					<tr id="aaa">
						<td>
						<%if(keyword==null){ %>
							<% if(currentPage <= 1){ %>
							<img src="/semi/view/category/images/DoubleChevronLeft.png" width="16" height="16" border="0" alt="">
							<% }else{ %>
							<a href="/semi/rlist?page=<%= currentPage - 1 %>">
							<img src="/semi/view/category/images/DoubleChevronLeft.png" width="16" height="16" border="0" alt=""></a>
							<% } %>
						</td>
						<%	if(endPage == 0){ %>
						<td>
							<b>1</b>
						</td>
						<%	}else{ %>
						<% 
							for(int u = startPage; u <= endPage; u++){ 
							if(u==currentPage){
						%>
						<td>
							<b><%= u %></b>
						</td>
						<% 	}else{ %>
						<td>
							<a href="/semi/rlist?page=<%= u %>"><%= u %></a>
						</td>
						<% 	}}} %>
						<% 	if(currentPage >= maxPage){ %>
						<td>
							<img src="/semi/view/category/images/DoubleChevronRight.png" width="16" height="16" border="0" alt="">
						</td>
						<% 	}else{ %>
						<td>
							<a href="/semi/rlist?page=<%= currentPage + 1 %>">
							<img src="/semi/view/category/images/DoubleChevronRight.png" width="16" height="16" border="0" alt=""></a>
						</td>
						<% 	}}else{ %>
						<% if(currentPage <= 1){ %>
							<img src="/semi/view/category/images/DoubleChevronLeft.png" width="16" height="16" border="0" alt="">
							<% }else{ %>
							<a href="/semi/rsearch?page=<%= currentPage - 1 %>&selectedItem=<%= selectedItem %>&keyword=<%=keyword %>">
							<img src="/semi/view/category/images/DoubleChevronLeft.png" width="16" height="16" border="0" alt=""></a>
							<% } %>
						</td>
						<%	if(endPage == 0){ %>
						<td>
							<b>1</b>
						</td>
						<%	}else{ %>
						<% 
							for(int u = startPage; u <= endPage; u++){ 
							if(u==currentPage){
						%>
						<td>
							<b><%= u %></b>
						</td>
						<% 	}else{ %>
						<td>
							<a href="/semi/rsearch?page=<%= u %>&selectedItem=<%= selectedItem %>&keyword=<%=keyword %>"><%= u %></a>
						</td>
						<% 	}}} %>
						<% 	if(currentPage >= maxPage){ %>
						<td>
							<img src="/semi/view/category/images/DoubleChevronRight.png" width="16" height="16" border="0" alt="">
						</td>
						<% 	}else{ %>
						<td>
							<a href="/semi/rsearch?page=<%= currentPage + 1 %>&selectedItem=<%= selectedItem %>&keyword=<%=keyword %>">
							<img src="/semi/view/category/images/DoubleChevronRight.png" width="16" height="16" border="0" alt=""></a>
						</td>
						<%}} %>
					</tr>
				</table>
			</div>
		</div>
	</div>
<footer></footer>
</body>
</html>
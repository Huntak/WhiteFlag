<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, notice.model.vo.Notice, member.model.vo.Member"%>
<%@ page import = "notice.model.vo.Notice , java.util.*, member.model.vo.Member" %>
<%
   Member loginUser = (Member)session.getAttribute("loginUser");
   ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
   ArrayList<Notice> list2 = (ArrayList<Notice>)request.getAttribute("list2");
	// 총 목록수
	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	// 현재 보여질 목록 페이지
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	// 현재 페이지(19)의 시작 페이지(11)
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	// 현재 페이지(19)의 마지막 페이지(20)
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	// 맨 마지막 페이지
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
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
<title>Notice</title>
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
</head>
<body>
<header></header>
<div id="nmainQ">| NOTICE<td> : news & event</td></div>
<div id="ntd">
	<table id="nntable" border="0" cellspacing=0 style="border-color:#F6F6F6;">
	<tr style="" class="tr22">
 		<th id="no" class="no" width="100px">no</th>
 		<th id="subject" class="subject" scope="col">subject</th>
 		<th id="name" class="name" scope="col">name</th>
 		<th id="date" class="date">date</th>
 		<th id="hit">hit</th>
 	</tr>
 	 <% 
 	 if(list != null){%>
 	<% for(Notice n : list){ %>  
 	 	<tr>
 	 		<td id="nnum">공지</td>
 	 		<td id="nsub"><a href="/semi/ndetail?nnumber=<%= n.getnNumber()%>"" id="nsub1"><%= n.getnTitle() %></a>
 	 		<% if(n.getnReadcount() >= 100){ %>
				&nbsp;<img src="/semi/view/category/images/icon_hit.gif" width="18" height="9" border="0">
			<% } %>
			</td>
	      	<td id="nnum">WhiteFlag[관리자]</td>
	 	 	<td id="nnum1"><%= n.getnDate() %></td>
	 	 	<td id="nhit"><%=n.getnReadcount() %></td>
 	 	</tr>
 	 <%} }%>
 	 
 	 <%if(list2 !=null){ %>
 	 <% for(Notice n : list2){ %>  
 	 	<tr>
 	 		<td id="nnum">공지</td>
 	 		<td id="nsub"><a href="/semi/ndetail?nnumber=<%= n.getnNumber()%>"" id="nsub1"><%= n.getnTitle() %></a>
 	 		<% if(n.getnReadcount() >= 100){ %>
				&nbsp;<img src="/semi/view/category/images/icon_hit.gif" width="18" height="9" border="0">
			<% } %>
			</td>
	      	<td id="nnum">WhiteFlag[관리자]</td>
	 	 	<td id="nnum1"><%= n.getnDate() %></td>
	 	 	<td id="nhit"><%=n.getnReadcount() %></td>
 	 	</tr>
 	 <%} }%>
 	 
 	 <%if(list2 !=null && list2.size() <=0){ %>
 	 <tr></tr>
 	 <%} %>
	</table>
	<form action = "/semi/nsearch" >
		<table>
			<tr>
				<td align="left">
					<select name="selectedItem" id="search">
						<option value="title"> 제목</option>
						<option value="content">내용</option>
					</select>
						<input type = "search" name = "keyword" id="search">
						<input type = "submit" value = "검색">
					</td>
					<%if(loginUser != null){ %><!-- 로그인하거나 안해도 notice 글볼수있음 -->
					<td align="right">
					<%if(loginUser.getMID().equals("admin")){ %><!-- 로그인하면 write버튼 보이게 처리 -->
						<a href="/semi/view/noticeBoard/noticeWr.jsp" id="rright1">
							<input type="button" value="write" style="width:28%; height:28px; border:1px solid black;background:white;font-style:italic;font-weight:bold; cursor : pointer;">
						</a>
					<%}}%>
				</td>
			</tr>
		</table>
	</form>	
	<div id="paging_div">
				<table id="paging_table">
					<tr id="aaa">
						<td>
						<%if(list2 ==null){ %>
							<% if(currentPage <= 1){ %>
							<img src="/semi/view/category/images/DoubleChevronLeft.png" width="16" height="16" border="0" alt="">
							<% }else{ %>
							<a href="/semi/nlist?page=<%= currentPage - 1 %>">
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
							<a href="/semi/nlist?page=<%= u %>"><%= u %></a>
						</td>
						<% 	}}} %>
						<% 	if(currentPage >= maxPage){ %>
						<td>
							<img src="/semi/view/category/images/DoubleChevronRight.png" width="16" height="16" border="0" alt="">
						</td>
						<% 	}else{ %>
						<td>
							<a href="/semi/nlist?page=<%= currentPage + 1 %>">
							<img src="/semi/view/category/images/DoubleChevronRight.png" width="16" height="16" border="0" alt=""></a>
						</td>
						<% 	}}else{ %>
						<% if(currentPage <= 1){ %>
							<img src="/semi/view/category/images/DoubleChevronLeft.png" width="16" height="16" border="0" alt="">
							<% }else{ %>
							<a href="/semi/nsearch?page=<%= currentPage - 1 %>&selectedItem=<%= selectedItem %>&keyword = <%=keyword %>">
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
						<%System.out.println("페이지" + keyword + selectedItem); %>
							<a href="/semi/nsearch?page=<%= u %>&selectedItem=<%= selectedItem %>&keyword=<%=keyword %>"><%= u %></a>
						</td>
						<% 	}}} %>
						<% 	if(currentPage >= maxPage){ %>
						<td>
							<img src="/semi/view/category/images/DoubleChevronRight.png" width="16" height="16" border="0" alt="">
						</td>
						<% 	}else{ %>
						<td>
							<a href="/semi/nsearch?page=<%= currentPage + 1 %>&selectedItem=<%= selectedItem %>&keyword = <%=keyword %>">
							<img src="/semi/view/category/images/DoubleChevronRight.png" width="16" height="16" border="0" alt=""></a>
						</td>
						
						<%}} %>
					</tr>
				</table>
			</div>
		</div>
	</div>
<footer style="overflow:auto;
	width: 1537px;
    height: auto;
    margin-bottom: 100px;"></footer>
</body>
</html>
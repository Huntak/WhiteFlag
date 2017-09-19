<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member" %>
<%
	// session.invalidate();
	Member loginUser = null;
	if (session.getAttribute("loginUser") != null) {
		loginUser = (Member) session.getAttribute("loginUser");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WhiteFlag</title>
<link rel="stylesheet" type="text/css" href="/semi/common/css/mainTopAgain.css?ver=9">

<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/mainTop.js?ver=9"></script>
<script type="text/javascript">
	function search(){
		var searchCondition = document.getElementById("searchText").value;
		document.location.href = "/semi/search?searchCondition=" + searchCondition;
	}
	
	$("#searchText").keypress(function(e) { 
		if(e.keyCode == 13){
			search();
	    }
	});
</script>
</head>
<body>
<div id="topmenu" >
	<div id="div1"> <a href="/semi/index.jsp" id ="logoimgGap"><img src="/semi/common/images/ourlogo.jpg" id="logoimg" alt=""></a>   </div>
	<div id="div2" class="group2"> 
		<ul><li><a href="/semi/category?cId=t">TOP</a>
				<ul class="submenuUl">
				<li><a  href="/semi/category?cId=tm">▶ MTM&nbsp;&nbsp;</a></li> 
				<li><a  href="/semi/category?cId=tk">▶ KNIT</a></li>  
				<li><a  href="/semi/category?cId=tt">▶ T-SHIRTS</a></li> 
				<li><a  href="/semi/category?cId=ts">▶ SHIRTS </a></li> 
				</ul>	
			</li>
		</ul>
	 </div>	
	 <div id="div3" class="group2">				
		<ul><li ><a href="/semi/category?cId=p" >PANTS</a>
				<ul class="submenuUl">		
				<li ><a href="/semi/category?cId=ps">▶ SLACKS </a></li> 
				<li ><a href="/semi/category?cId=pje">▶ JEANS</a></li>  
				<li ><a href="/semi/category?cId=pc">▶ COTTON</a></li> 
				<li ><a href="/semi/category?cId=pjg">▶ JOGGER</a></li> 
				</ul>	
			</li>
		</ul>
	</div>
	<div id="div4" class="group2">			
		<ul><li><a href="/semi/category?cId=o" >OUTER</a>
				<ul class="submenuUl">		
				<li ><a href="/semi/category?cId=ocoa">▶ COAT </a></li> 
				<li ><a href="/semi/category?cId=ojum">▶ JUMPER</a></li>  
				<li ><a href="/semi/category?cId=ojac">▶ JACKET</a></li> 
				<li ><a href="/semi/category?cId=os">▶ SUIT </a></li> 
				<li ><a href="/semi/category?cId=ocar">▶ CARDIGAN</a></li>  
				<li ><a href="/semi/category?cId=ov">▶ VEST</a></li> 
				<li ><a href="/semi/category?cId=ol">▶ LEATHER</a></li> 
				<li ><a href="/semi/category?cId=ob">▶ BLOUSON</a></li> 	
				</ul>					
			</li>
		</ul>
	</div>
	<div id="div5" class="group2">
		<input id="searchText" type="text" placeholder="" onclick="this.value='';">
		<span onClick="search();"><img src="/semi/common/images/find.png" id="btnSearch"></span>
	</div>
	<div id="div6" class="group2"><br>
		<span>
			<% if(loginUser !=null){ %>
			<a href="/semi/logout" id="logoutA" style="color:red; font-size: 13px; " >logout</a>
			<% } else { %>
			<a href="/semi/view/member/loginForm.jsp" >LOGIN</a>
			<% } %>  
		</span>	 
	</div>
	<div id="div7" class="group2"><br>
		<span><a href="/semi/view/member/enrollForm.jsp" >JOIN</a></span>
	</div>
	<%if(loginUser != null){ %>
	<div id="div8" class="group2"><br>
		<span><a href="/semi/mypage?mid=<%=loginUser.getMID()%>">MYPAGE</a></span>
	</div>
	<div id="div9" class="group2"><br>
	<span>	
		<a href="/semi/mycart?userid=<%= loginUser.getMID() %>">CART</a></span></div>
		<%} else{ %>
		<div id="div8" class="group2"><br>
		<span><a href="/semi/view/myPage/myPage.jsp">MYPAGE</a></span>
	</div>
	<div id="div9" class="group2"><br>
	<span>	
		<a href="/semi/view/cart/cart.jsp">CART</a></span></div>
		<%}%>
	
	<div id="div10" class="group2"><br>
		<span><a href="/semi/nlist" >NOTICE</a></span>
	</div>
	<div id="div11" class="group2"><br>
		<span><a href="/semi/qlist" >Q&A</a></span>
	</div>
	<div id="div12" class="group2" ><br>
		<span ><a href="/semi/rlist" >REVIEW</a></span>
	</div>
	<%if(loginUser != null && loginUser.getMID().equals("admin")){ %>
	
	<div id="div13" class="group2">	
		<ul><li><a href="#" >MANAGER</a>
				<ul id="managerUl">		
					<li ><a href="/semi/newproductview">▶ NEW PRODUCT </a></li> 
					<li ><a href="/semi/pselectall">▶ PRODUCT MANAGER</a></li> 
					<li ><a href="/semi/pandslist">▶ PLUS & STOCK</a></li> 
					<li ><a href="/semi/ordermanagerlistview">▶ ORDER</a></li>
					<li ><a href="/semi/plistview">▶ PROVIDER</a></li>
					<li ><a href="/semi/mmview">▶ MEMBER</a></li>
				</ul>
			</li>	
		</ul>
	</div> 
	<%} %> 
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member, java.util.*"%>
<%@ page import = "member.model.vo.Member" %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");
	String mid ="";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>회원관리</title>
<link rel="stylesheet" type="text/css" href="/semi/view/manager/css/memberManager.css?ver=3">
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
	$('span#date').hide();
	$('select[name=memberSearch]').change(function(){
		if($(this).val()=='menrolldate'){
			$('span#keyword').hide();
			$('span#date').show();
		}else{
			$('span#date').hide();
			$('span#keyword').show();
		}
	})
});

</script>
</head>
<body>
<header></header>
<div id="title">
	| MEMBER MANAGER
</div>
<section>	
<form action="/semi/mmsearch" method="post">
<fieldset>
<legend>&nbsp;SEARCH&nbsp;</legend>
	<div id="search">
	<select name="memberSearch">
		<option value="mid">회원ID</option>
		<option value="mname">이름</option>
		<option value="mphone">전화번호</option>
		<option value="maddress">주소</option>
		<option value="menrolldate">가입날짜</option>
	</select>
	<span id = "date">
	<input type="date" name="beforeDate"> - <input type="date" name="afterDate">
	</span>
	<span id="keyword">
	<input type="text" name="keyword">
	</span>
	<input type="submit" value="검색">
	</div>
	</fieldset>
	</form>
	<br><br>
	<div id="contents">
		<table cellspacing="0" cellpadding="0">
			<tr class="tr1" align="center" >
				<th  width="100px">회원ID</th>
				<th  width="100px">이름</th>
				<th width="70px">성별</th>
				<th width="100px">회원등급</th>
				<th width="100px">누적금액</th>
			</tr>
			<%for(Member m : list){ %>
			<tr align="center" height="50px">
			<% mid =m.getMID(); %>
				<td class="member_value"><a href="javascript:void(0);" onclick="window.open('/semi/mselectone?mid=<%= m.getMID() %>','memberView','scrollbars=yes,toolbar=no,resizable=no,width=450,height=650,left=0,top=0,margin=0');"><%= m.getMID() %></a></td>
				<td class="member_value"><%= m.getMNAME() %></td>
				<td class="member_value"><%=m.getMGENDER() %></td>
				<td class="member_value"><%=m.getGRADE() %></td>
				<td class="member_value"><%=m.getMTOTALPURCHASE() %></td>
			</tr>
			<%} %>
		</table>
		
	</div>
	<div id="btnchange">
		<button id="change">변경</button>
	</div>
	
	
</section>
<!-- <footer></footer> -->
</body>
</html>
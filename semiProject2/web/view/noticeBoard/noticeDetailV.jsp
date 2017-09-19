<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "notice.model.vo.Notice, member.model.vo.Member" %>
<% 
	Notice notice = (Notice)request.getAttribute("notice"); 
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js"></script>
<link rel = "stylesheet" type = "text/css" href = "/semi/view/noticeBoard/css/noticedetialview.css?ver=11">
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
function deleteConfirm(){
	if(confirm("정말로 삭제하시겠습니까?")){
		location.href="/semi/ndel?nnumber=<%=notice.getnNumber()%>";
	}
}
function modifyConfirm(){
	if(confirm("수정하시겠습니까?")){
		location.href="/semi/nupform?nnumber=<%=notice.getnNumber()%>";
	}
}
</script>
<title>noticeWr</title>
</head>
<body>
<header></header>
<div id="ntitle">| NOTICE  <td>: news & event</td></div>
	
<div id="content">
<table class="cla1">
		<tr><td class="ntd1">subject</td><td class="td2"><%= notice.getnTitle() %></td></tr>
		<tr><td class="ntd1">name</td><td>&nbsp;<label>WhiteFlag[관리자]</label></td></tr>
		<tr><td class="ntd1">date</td><td class="td2"><%= notice.getnDate() %></td></tr>
		</table>
		<table>
		<%if(notice.getnImage()==null){ %>
	
		<%}else{ %>
		<tr><td colspan="2" id="tdcontent">
		<img width="400"src ="/semi/noticeUploadFiles/<%= notice.getnImage() %>" id="nimage"></tr>
		<%} %>
		<tr ><td colspan="2" id="tdcontent" style="text-align:center;"><%= notice.getnContent() %></tr>
		
		<tr>
			<td>
				<div><a href="/semi/nlist" id="nlist" >list</a></div>
			</td>
			
		<%if(loginUser != null){ %><!-- 로그인하거나 안해도 notice 글볼수있음 -->
		<%if(loginUser.getMID().equals("admin")){ %>
			<td align="right">
				<a href="#" onclick="javascript:deleteConfirm();" id="ndelete" class="ndb">delete</a>
				<a href="#" onclick="javascript:modifyConfirm();" id="nmodify" class="ndb">modify</a>
			</td>
		</tr>
		<%}}%>
		</table>
</div>
 <footer></footer>
</body>
</html>
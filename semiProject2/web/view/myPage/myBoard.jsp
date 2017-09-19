<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member, java.util.*, review.model.vo.Review, qanda.model.vo.Qna" %>
<% 
   Member loginUser = (Member)session.getAttribute("loginUser");
   ArrayList<Qna> qlist = (ArrayList<Qna>)request.getAttribute("qlist");
   ArrayList<Review> rlist = (ArrayList<Review>)request.getAttribute("rlist");
   
   
%>
    
<!DOCTYPE html >
<html>
<head>
<meta charset=UTF-8">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js"></script>
<script type="text/javascript" src ="/semi/view/myPage/js/myBoard.js"></script>
<link rel="stylesheet" type = "text/css" href = "/semi/view/myPage/css/myBoard.css?ver=17">
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
<title>MyBoard</title>
</head>
<body>
<header></header>

<div id="ccenter" >
<%if(loginUser != null){%>
 <div id="div_welcome" align="center">
<font color="gray"><%= loginUser.getMNAME() %></font> 님의, 게시글 작성 내역입니다.
</div>
<%}else{%>
 <div id="div_welcome" align="center">
저희 쇼핑몰을 이용해주셔서 감사합니다 . <a href="/semi/view/member/loginForm.jsp"><font color="red">로그인</font></a> 후 이용해 주시기 바랍니다.
</div>
<%}%>
<div> <a href="/semi/qlist" >Q&A</a> </div> 
<div id="divQandA"  >
<table id="myQandATable" >
      <tr  ><th class="dateText">등록일자</th><th>글번호</th><th>제품번호</th><th class="titleText">글제목</th><th class="tdcontent">글내용</th>
      <th>이미지</th><th>조회수</th><th class="tdYn">댓글여부</th>
      </tr>
      <% 
     if(qlist != null){
     for(Qna q : qlist){ %>  
        <tr style="height:9px;"  >
           <td align="center" class="dateText"><%= q.getqDate()%></td>
           <td align="center"><%= q.getqNumber()%></td>
           <td align="center"><%= q.getPid()%></td>
           <td align="center" class="titleText"><%= q.getqTitle()%></td>
           <td align="center" class="tdcontent"><a href="/semi/qdetail?qnumber=<%= q.getqNumber()%>"><%= q.getqContent()%></a></td>
           <td>
           <%if(q.getqImage() != null){ %> <!-- 그림을 넣지않았을경우 -->
           <img src="/semi/reviewUploadFiles/<%=q.getqImage() %>"  width="8" height="8">
           <%} %>
           </td>
           <td align="center"><%= q.getqReadcount()%></td>
           <td align="center" class="tdYn"><%= q.getqAnswerYN()%></td>    
              </tr>
     <%} }else{%>
     <tr><tr>
     <%} %>
      
      
      </table>  
</div>

<div>   <a href="/semi/rlist" >Review</a> </div> 
<div id="divReview" >
 
    <table id="myReviewTable"  >
      <tr  ><th class="dateText">등록일자</th><th>글번호</th><th>제품번호</th><th class="titleText">글제목</th><th class="tdcontent">글내용</th><th>이미지</th><th>조회수</th>
      <th class="tdYn"  >댓글여부</th>
      </tr>
      <% 
     if(rlist != null){
     for(Review r : rlist){ %>  
        <tr style="height:9px;">
           <td align="center" class="dateText" ><%= r.getrDate()%></td>
           <td align="center"><%= r.getrNumber()%></td>
           <td align="center"><%= r.getpId()%></td>
           <td align="center" class="titleText"><%= r.getrTitle()%></td>
           <td align="center" class="tdcontent"><a href="/semi/rdetail?rnumber=<%= r.getrNumber()%>"><%= r.getrContent()%></a></td>
           <td>
           <%if(r.getrImage() != null){ %> <!-- 그림을 넣지않았을경우 -->
           <img src="/semi/reviewUploadFiles/<%=r.getrImage() %>"  width="8" height="8">
           <%} %>
           </td>
           <td align="center"><%= r.getrReadcount()%></td>
           <td align="center"class="tdYn"><%= r.getrAnswer_YN()%></td> 
        </tr>
     <%} }else{%>
     <tr><tr>
     <%} %>
      
      
      </table>
</div>   
</div>
<footer></footer>   
</body>
</html>
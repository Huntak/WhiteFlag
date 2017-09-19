<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "qanda.model.vo.Qna, member.model.vo.Member, java.util.*, product.model.vo.*,qandareply.model.vo.QandAReply" %>
<% 
	Qna qna = (Qna)request.getAttribute("qna"); 
	Product product = (Product)request.getAttribute("product");
	ProductImage productimage = (ProductImage)request.getAttribute("productimage");
	ArrayList<QandAReply> qlist = (ArrayList<QandAReply>) request.getAttribute("qrlist"); 
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js"></script>
<link rel = "stylesheet" type = "text/css" href = "/semi/view/noticeBoard/css/noticedetialview.css?ver=22">
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
	$('.modifytr').hide();
   
    $(".modifybutton").click(function(){
    	$(this).hide();
    	$(this).prev().hide();
        $(this).parent().parent().next().show()
    });
    
	$(".cancelbutton").click(function(){
		$(this).parent().parent().prev().find("td:eq(1)").find("a").show();
		$(this).parent().parent().prev().find("td:eq(1)").find(".modifybutton").show();
		$(this).parent().parent().hide();
	});
});

function deleteConfirm(){
	if(confirm("정말로 삭제하시겠습니까?")){
		location.href="/semi/qdel?qnumber=<%=qna.getqNumber()%>";
	}
}
function modifyConfirm(){
	if(confirm("수정하시겠습니까?")){
		location.href="/semi/qupform?qnumber=<%=qna.getqNumber()%>";
	}
}

function ok(){
	   
	   if($('#qrcontent').val()==""){
	      alert("내용을 입력하세요");
	   }
	   
	   
	   if($('#qrcontent').val()!=""){
	      $.ajax({
	         url : "/semi/qreply",
	         data : {qnumber: $('input[name=qnumber]').val(), mid: $('input[name=mid]').val(),qrcontent: $('#qrcontent').val() },
	         type : "get",
	         dataType : "json",
	         success : function(data){
	            self.location.reload();
	         }
	      })
	   }
	}
$(function(){  
	   $('.modifytr').hide();
	});
</script>
<title>qnaDV</title>
</head>
<body>
<header></header>
<div id="title">|  ITEM Q&A : news & event</div>
<div id="qqcontent">
	<table class="cla1">
	<%if(productimage.getMainImage()!=null){ %>
		<tr>
			<td><a  href="/semi/detail?imageId=<%= product.getImageId()%>"><img style="margin-bottom:20px;" width="100" src ="/semi/image/<%= productimage.getMainImage()%>"></a></td>
				<td align="left" style="padding-left:10px;">	
				<a  id="piname" href="/semi/detail?imageId=<%= product.getImageId()%>"><%= product.getpName() %></a><br>
				<%= product.getpPrice() %> won <br><br><br>
				<a  href="/semi/detail?imageId=<%= product.getImageId()%>">
       	  			 <input type="button" value="ITEM DETAIL" id="qitem">
       	  			 </a>
            	</td>
		</tr>
		<%} %>
		<tr><td class="qtd1" ><label>subject</label></td><td ><%= qna.getqTitle() %></td></tr>
		<tr><td class="qtd1">name</td><td>&nbsp;<label><%=qna.getmId() %></label></td></tr>
		<tr><td class="qtd1" >date</td><td><%= qna.getqDate() %><br></td></tr>
		<tr><td colspan="2" id="tdcontent" style="text-align:left">
			<%if(qna.getqImage()==null){ %>
	
			<%}else{ %>
				<div style="margin-top:40px; margin-bottom:10px;"><img width="450" src ="/semi/qnaUploadFiles/<%= qna.getqImage() %>"></div>
			<%}%>
				<%= qna.getqContent() %>
				</td>
			</tr>
				
		<%if(qna.getqImage() !=null){ %>
			<tr><td class="td1">file</td><td><%= qna.getqImage() %></td></tr>
		<%}else{ %>
	  		<tr><td></td></tr>
		<%} %>
	</table>
		<table>
		<tr>
			<td>
				<a href="/semi/qlist" id="qlist" >list</a>
			</td>
		<%if(loginUser.getMID().equals(qna.getmId()) || (loginUser.getMID().equals("admin"))){ %>
			<td align="right">
				<a href="#" onclick="javascript:deleteConfirm();" id="delete" class="qdb">delete</a>
				<a href="#" onclick="javascript:modifyConfirm();" id="modify" class="qdb">modify</a>
			</td>		
		<%}else{%>
		<td></td>
		<%} %>
		</tr>
	</table>
	
 
	<table id="replytb">
		<%if(qlist.size() >0){%>
     	 <%for(QandAReply q : qlist){ %>         
         <tr>   
            <td id="reid"><%=q.getMID() %></td><td id="redate"><%= q.getQRDATE() %></td>
         </tr>
         <tr>
            <td id="qecontent"><%=q.getQRCONTENT() %></td>
            
           	  	 <%if(loginUser.getMID().equals(q.getMID()) || (loginUser.getMID().equals("admin"))){ %>
         	 <td>
	               <a href="/semi/delqnare?qrnumber=<%=q.getQRNUMBER()%>&qnumber=<%=qna.getqNumber()%>" id="delete" class="qdb" > 
	               DELETE</a>
             	  <a class="modifybutton" id="modify">modify</a>
             	    </td> 
             	  <%} %>
		</tr>
          
         <tr class="modifytr">
         <form action="/semi/updateqre" method="post">
         <input type="hidden" name="qrnumber" value="<%= q.getQRNUMBER() %>">
             <input type="hidden" name="qnumber" value="<%= q.getQNUMBER() %>">
         	   <td>
         		   <textarea name="qrcontent2" style="resize:none; width:700px; height:40px;"><%=q.getQRCONTENT() %></textarea>
         	   </td>
            	<td>
       	  			<input type="submit" value="ok" class= "okbutton"> 
       	  			<input type="button" value="cancel" class = "cancelbutton">
            	</td>
            	
            	</form>
         </tr>
         
      <%}} %>
	</table>
 
	
	<%if(!(loginUser.getMID().equals(qna.getmId())) && !(loginUser.getMID().equals("admin"))){ %>
		<table class="cla1">
			<tr><td class="admin"><div style="margin-right:800px;">관리자에게만 댓글 작성 권한이 있습니다.</div></td></tr>
		</table>
		<%}else{ %>
		<input type="hidden" name="qnumber" value="<%= qna.getqNumber() %>">
		 <div style="margin-bottom: 110px;">
         <table  style="height : 130px; background:#f6f6f6;" >
	         <tr><td class="com" colspan="4">comments</td></tr>
	         <tr><td class="com" >name: <input type="text" size="15" name="mid" value="<%=loginUser.getMID() %>"></td></tr>
	         <tr>
	            <td class="com" colspan="4" >
	                  <textarea id="qrcontent" name="qrcontent" style="resize:none; width:700px; height:40px;"></textarea>
	            </td>
	               <td class="com" style="width: 475px;">
	                  <a onclick="ok();"><input type="button"  value="OK"   id="rrok"  style="width:45px;"></a>
	               </td>
	         </tr>
      </table>
   </div>
   <%} %>
</div>
<footer></footer>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "review.model.vo.Review, member.model.vo.Member, reviewreply.model.vo.ReviewReply, java.util.*, product.model.vo.*" %>
<% 
   Review review = (Review)request.getAttribute("review");
   Product product = (Product)request.getAttribute("product");
   ProductImage productimage = (ProductImage)request.getAttribute("productimage");
   ArrayList<ReviewReply> rlist = (ArrayList<ReviewReply>) request.getAttribute("rrlist");

   Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js"></script>
<link rel = "stylesheet" type = "text/css" href = "/semi/view/noticeBoard/css/noticedetialview.css?ver=17">
<style>
input:focus, textarea:focus{
    outline: none;
}               
#rdelete{
    padding: 5px 10px;
    font-size: 12px;
    font-family: georgiaz,NanumBarunGothic;
    color: #333;
    line-height: 30px;
    border: 1px solid #ccc;
    width: 35px
}
      
td{
   border-top: 1px solid lightgray;
   border-bottom: 1px solid lightgray;
   font-size: 9pt;
}

.admin{
   height : 50px;
   width :150px;
   text-align : center;
   background : #f6f6f6;
   font-size : 10pt;
}
.com{
    border: none;
    backgroud: #f6f6f6;
    font-size: 10pt;
    width: 370px;
    height: 40px;
}
#ok{
    margin-right: 35px;
    padding: 5px 10px;
    font-size: 12px;
    font-family: georgiaz,NanumBarunGothic;
    color: #333;
    line-height: 30px;
    border: 1px solid #ccc;
    text-decoration: none;
    margin-right: 300px;
}
</style>
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
         //$('.modifytr').show();
    });
    
	$(".cancelbutton").click(function(){
		$(this).parent().parent().prev().find("td:eq(1)").find("a").show();
		$(this).parent().parent().prev().find("td:eq(1)").find(".modifybutton").show();
		
		//var rrcontent2 = $(".recontent").text().trim();
		/* var rrcontent2 = $(this).parent().parent().prev().find("td:eq(0)").text().trim();
		$(this).parent().find("textarea[name=rrcontent2]").val(rrcontent2); */
		$(this).parent().parent().hide();
	});
});


function ok(){
   
   if($('#rrcontent').val()==""){
      alert("내용을 입력하세요");
   }
   
   
   if($('#rrcontent').val()!=""&&$('input[name=rrpwd]').val()!=""){
      $.ajax({
         url : "/semi/rreply",
         data : {rnumber: $('input[name=rnumber]').val(), mid: $('input[name=mid]').val(),rrcontent: $('#rrcontent').val() },
         type : "get",
         dataType : "json",
         success : function(data){
            <%--  var values = $('#replytb').html();
         
            
               values +=  '<tr>'+
                                    '<td id="reid" width="100px">'+data.mid+'</td>'+'<td id="redate"width="700px">'+data.rrdate+'</td>'+'</tr><tr>'+'<td id="recontent"width="150px">'+data.rrcontent+' '+'<a href="/semi/redel?rrnumber=<%=r.getRRNUMBER()%>&rnumber=<%=review.getrNumber()%>" id="redelete" class="redb" ><input type="button" value="DELETE"></a>'+' '+'<a href="#" onclick="remodifyConfirm();" id="remodify" class="redb">modify</a>'+'</td>'
                           +'</tr>'
                           
            
            $('#replytb').html(values);  --%>
            self.location.reload();
            
            
         }
            
         
      })
      
   }
}
function deleteConfirm(){
   
   if(confirm("정말로 삭제하시겠습니까?")){
      
      location.href="/semi/rdel?rnumber=<%=review.getrNumber()%>";
   }
}
function modifyConfirm(){
   if(confirm("수정하시겠습니까?")){
      location.href="/semi/rupform?rnumber=<%=review.getrNumber()%>";
   }
   

}
</script>
<title>reviewDV</title>
</head>
<body>
<header></header>
<div id="rtitle">| Review  <td>:: After Review</td></div>
   
<div id="content">
   <table class="cla1">
   <%if(productimage != null){ %>
      <%if(productimage.getMainImage()!=null){ %>
      <tr>
            <td><a  href="/semi/detail?imageId=<%= product.getImageId()%>" ><img style="margin-bottom:20px;" width="100" src ="/semi/image/<%= productimage.getMainImage()%>"></a></td>
				<td align="left">
				<a  id="piname" href="/semi/detail?imageId=<%= product.getImageId()%>"><%= product.getpName() %></a><br>
				<%= product.getpPrice() %> won <br><br><br>
				<a  href="/semi/detail?imageId=<%= product.getImageId()%>">
       	  			 <input type="button" value="ITEM DETAIL" id="item">
       	  			 </a>
           	</td>
      </tr>
      <%}} %>
      <tr><td class="td11">subject</td><td class="td2"><%= review.getrTitle() %></td></tr>
      <tr><td class="td11">name</td><td>&nbsp;<label><%=review.getmId() %></label></td></tr>
    
		      <%if(review.getrImage() !=null){ %>
		        <tr>
	      	<td> </td>
	      	<td>
		           <div style="margin-top:40px; margin-bottom:10px;"> <img width="500" src ="/semi/reviewUploadFiles/<%= review.getrImage() %>"></div>
		           <%System.out.println(review.getrImage()); %>
		   </td>
      </tr> 
		  <%}else{ %>
		  <tr><td></td></tr>
		  <%} %>

      <tr>
         	<td colspan="2" id="tdcontent">
   	 <div style="text-align:left;">
               <textarea style="resize:none; width:1100px; height:300px; border:0;" name="rcontent"   readonly><%= review.getrContent() %>   </textarea>
     </div>
         </td>
      </tr>
   </table>
   
   <table>
      <tr>
         <td>
            <div style="margin-bottom:40px; id:rlist"><a href="/semi/rlist" id="list" >list</a></div>
         </td>
      <%if(loginUser.getMID().equals(review.getmId()) || (loginUser.getMID().equals("admin"))){ %>
         <td align="right">
            <a href="#" onclick="javascript:deleteConfirm();"  id="delete" class="qdb">delete</a>
            <a href="#" onclick="javascript:modifyConfirm();" id="modify" class="qdb">modify</a>
         </td>
         <%}%>
      </tr>
   </table>
      
      
	<div>
	<table id="replytb">
		<%if(rlist.size() >0){%>
		<%for(ReviewReply r : rlist){ %>   
		<tr>
			<td id="reid"><%=r.getMID() %></td><td id="redate"><%= r.getRRDATE() %></td>            
		</tr>
		
		<tr>
			<td class="recontent"><%=r.getRRCONTENT() %></td>
         	 
         	 <%if(loginUser.getMID().equals(r.getMID()) || (loginUser.getMID().equals("admin"))){ %>
         	 <td>
	               <a href="/semi/redel?rrnumber=<%=r.getRRNUMBER()%>&rnumber=<%=review.getrNumber()%>" id="delete" class="qdb" > 
	               DELETE</a>
             	  <a class="modifybutton" id="modify">modify</a>
             	    </td> 
             	  <%} %>
		</tr>
		
		<tr class="modifytr">
			<form action="/semi/updatere" method="post">
			 <input type="hidden" name="rrnumber" value="<%= r.getRRNUMBER() %>">
		            <input type="hidden" name="rnumber" value="<%= r.getRNUMBER() %>">
	            <td>
	           		<textarea name="rrcontent" style="resize:none; width:700px; height:40px;"><%=r.getRRCONTENT() %></textarea>
	            </td>
	            <td>
	            	<input type="submit" value="ok" class= "okbutton"> 
	            	<input type="button" value="cancel" class = "cancelbutton">
	            </td>
            </form>
		</tr>            
		<%}} %>
	</table>
   </div>
      <input type="hidden" name="rnumber" value="<%= review.getrNumber() %>">
      <div style="margin-bottom: 110px;">
         <table  style="height : 130px; background:#f6f6f6;" >
         <tr><td class="com" colspan="4">comments</td></tr>
         <tr><td class="com" >name: <input type="text" size="15" name="mid" value="<%=loginUser.getMID() %>"></td></tr>
         <tr>
            <td class="com" colspan="4">
                  <textarea id="rrcontent" name="rrcontent" style="resize:none; width:700px; height:40px;"></textarea>
            </td>
               <td class="com" >
                  <a onclick="ok();"><input type="button"  value="OK"   id="rrok" ></a>
               </td>
         </tr>
      </table>
   </div>
</div>
<footer></footer>
</body>
</html>
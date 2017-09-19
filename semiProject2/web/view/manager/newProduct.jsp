<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "member.model.vo.Member, java.util.*,provider.model.vo.Provider" %>
<%
  Member loginUser = (Member)session.getAttribute("loginUser");
  ArrayList<Provider> list = (ArrayList<Provider>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>새 상품 등록</title>
<link rel="stylesheet" type="text/css" href="/semi/view/manager/css/newProduct.css?ver=4">
<style type="text/css">
    h1 { font-size: 34px; line-height: 1.2; margin: 0.3em 0 10px; }
    #scrolltotop{position:fixed;bottom:0px;right:0px}#scrolltotop
    span{width:48px;height:48px;display:block;background:url("/test/1183/top.png") top no-repeat;margin:0px
    15px 10px 0;border-radius:3px;-webkit-transition:all 0.2s ease-out;-moz-transition:all 0.2s ease-out;-o-transition:all 0.2s ease-out;-ms-transition:all 0.2s ease-out;transition:all 0.2s ease-out}#scrolltotop a:hover
    span{background:url("/test/1183/top.png") bottom no-repeat}
   
</style>
<script type="text/javascript" src="/semi/common/js/jquery-3.1.1.min.js"></script>
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
jQuery(function($){
   $('#provider').hide();
   
   $('#bottom').css('height', screen.height);
    
   var index = Number(1);
   $("form input[name=image1]").change(function(){
      if(! $(this).val()){
         return;
      }
      
      index++;
      var s = $("#contents tr:last").clone(true);
      s.find("input").attr("name", "image" + index);
      $("#contents tfoot").append(s);
   });
   
   $("input[name=pColorBtn]").click(function(){
      var a = $("#color tr:last").clone(true);
      a.find("input[name=pColor]").val("");
      $("#color").append(a);
   });
});

jQuery(function($){
    $('#scrolltotop').hide();
    $(function () {
        $(window).scroll(function () {
            if ($(this).scrollTop() > 100) {
                $('#scrolltotop').fadeIn();
            } else {
                $('#scrolltotop').fadeOut();
            }
        });
        $('#scrolltotop a').click(function () {
            $('body,html').animate({
                scrollTop: 0
            }, 500);
            return false;
        });
    });
});
function searchProvider(){
	$('div#provider').show();
	$.ajax({
		url : "/semi/vprovider",
		data : {providerCode : $('#providerCode').val()},
		type : "get",
		dataType : "json",
		success : function(data){
			if(data!=null){
				$("label#code").text(data.code); 
				 $("label#company").text(data.company);
				$("label#name").text(data.name);
				$("label#tell").text(data.tell);
				$("label#phone").text(data.phone);
				$("label#address").text(data.address1+' '+data.address2+' '+data.address3+' '+data.address4);			
				$("label#etc").text(data.etc);
			}else{
				alert("검색결과없음");
			}
		}
		
	});
	
}

function vale(){	
	if(!($('input[name=pSize]').is(':checked'))){
		alert("사이즈를 선택해주세요")
		return false;
		} 
}
</script>
</head>
<body>
<header></header>
<div id="title">
   | New Product
</div>

<form action="/semi/pnew" method="post" enctype="multipart/form-data" onsubmit="return vale();">
<table id="contents" cellspacing="0">
	<thead>
   <tr><td class="menu"><label>상품이름</label></td><td class="item2"><input type="text"class="item" name="pName" id="pname" required></td></tr>
   <tr><td class="menu"><label>가격</label></td><td class="item2"><input type="text"class="item" name="pPrice" required></td></tr>
   <tr><td class="menu"><label>사이즈</label></td><td id="size">
   <input type="checkbox" name="pSize" value="s">S
   <input type="checkbox" name="pSize"  value="m">M
   <input type="checkbox" name="pSize"  value="l">L
   <input type="checkbox" name="pSize"  value="xl">XL
   <input type="checkbox" name="pSize"  value="xxl">XXL<BR>
   <input type="checkbox" name="pSize"  value="90">90
   <input type="checkbox" name="pSize"  value="95">95
   <input type="checkbox" name="pSize"  value="100">100
   <input type="checkbox" name="pSize"  value="105">105
   <input type="checkbox" name="pSize"  value="110">110<BR>
   <input type="checkbox" name="pSize"  value="44">44
   <input type="checkbox" name="pSize"  value="55">55
   <input type="checkbox" name="pSize"  value="66">66
   <input type="checkbox" name="pSize"  value="77">77
   </td></tr>
   </thead>
   <tbody id="color">
   <tr><td class="menu"><label>색상</label></td><td class="item2"><input type="text" class="item" name="pColor" required>&nbsp;&nbsp;<input type="button" name="pColorBtn" value="추가"></td></tr>
   </tbody>
   <tbody>
   <tr><td class="menu"><label>재질</label></td><td class="item2"><input type="text"class="item" name="pMaterial" required></td></tr>
   <tr><td class="menu"><label>카테고리(대)</label></td><td class="item2">
      <select name="category">
         <option value="tm">TOP:MTM</option>
         <option value="tt">TOP:T-SHIRTS</option>
         <option value="tk">TOP:KNIT</option>
         <option value="ts">TOP:SHIRTS</option>
         <option value="ps">PANTS:SLACKS</option>
         <option value="pje">PANTS:JEANS</option>
         <option value="pc">PANTS:COTTON</option>
         <option value="pjg">PANTS:JOGGER</option>
         
         <option value="ocoa">OUTER:COAT</option>
         <option value="ojum">OUTER:JUMPER</option>
         <option value="ojac">OUTER:JACKET</option>
         <option value="os">OUTER:SUIT</option>
         <option value="ocar">OUTER:CARDIGAN</option>
         <option value="ov">OUTER:VEST</option>
         <option value="ol">OUTER:LEATHER</option>
         <option value="ob">OUTER:BLOUSON</option>
      </select>
   </td></tr>
   <tr><td class="menu"><label>시즌</label></td><td class="item2">
   <select name="season">
      <option value="봄">봄</option>
      <option value="여름">여름</option>
      <option value="가을">가을</option>
      <option value="겨울">겨울</option>
      <option value="춘추">춘추</option>
      <option value="사계절">사계절</option>
   </select></td></tr>
   <tr><td class="menu"><label>거래처코드</label></td><td class="item2">
   				<select id="providerCode" name="providerCode">
   				<%for(Provider p : list){ %>
   					<option value="<%= p.getProviderCode()%>"><%= p.getProviderCode()%></option>
   				<%} %>
   				</select>
               <input type="button" value="확인" onclick="searchProvider();">
               
               <div id="provider" >
               
               <table style="width:500px;    	height:200px;    	background: #e1e2e3; margin-top:10px;">               
               	<tr align="center"><td><label>거래처 코드</label></td><td><label id="code" ></label></td></tr>
             	<tr align="center"><td><label>회사명</label></td><td><label id="company"></label></td></tr>
             	<tr align="center"><td><label>담당자명</label></td><td><label id="name"></label></td></tr>
             	<tr align="center"><td><label>TELL</label></td><td><label id="tell"></label></td></tr>
             	<tr align="center"><td><label>PHONE</label></td><td><label id="phone"></label></td></tr>
             	<tr align="center"><td><label>주소</label></td><td><label id="address"></label></td></tr>
             	<tr align="center"><td><label>ETC</label></td><td><label id="etc"></label></td></tr>
               </table>
               </div>
               </td></tr>
               
   <tr><td class="menu"><label>메인 이미지</label></td><td class="item2"><input type="file" name="mainimage"></td>
   </tbody>
   <tfoot>
   <tr><td class="menu"><label>서브 이미지</label></td><td class="item2"><input type="file" name="image1"  ></td>   
   </tfoot>
</table>
<input id="submit" type="submit"  value="등록">
</form>
<!-- <footer></footer> -->
<div id="aaa"><br></div>

</body>
</html>
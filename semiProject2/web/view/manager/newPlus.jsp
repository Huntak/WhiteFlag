<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "member.model.vo.Member,java.util.*,stock.model.vo.Stock" %>
<%
  Member loginUser = (Member)session.getAttribute("loginUser");
  ArrayList<Stock> stockList = (ArrayList<Stock>)request.getAttribute("stockList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/semi/view/manager/css/newPlus.css?ver=4">
<script type="text/javascript" src="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js?ver=3"></script>
<title>Insert title here</title>
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
   $(".plus").keyup(function (evt) {
          
        var index = $(".plus").index(this);            
       var price;
       var quantity;
       var totalRowIndex;
       var convertPrice;
       var convertQuantity;    
       if( index%2==0) {   
           price =$(".plus").eq(index).val();
           quantity  =$(".plus").eq(index+1).val();
           totalRowIndex = index/2;                    
       }
       else { 
           price =$(".plus").eq(index-1).val();
           quantity  =$(".plus").eq(index).val();                  
           totalRowIndex = index/2-0.5;             
       }    
       convertPrice = !price || isNaN(price) ? 0:parseInt(price);
       convertQuantity = !quantity || isNaN(quantity) ? 0:parseInt(quantity);
        $('input[name=plustotal]').eq(totalRowIndex).val(convertPrice*convertQuantity); 
     });
});

function search2(){
   $.ajax({
      url : "/semi/stocksearch",
      data : {stocksearchType: $('#stockSearch option:selected').val(),stocksearchvalue: $('#stockvalue').val()},
      type : "get",
      dataType : "json",
      async: false,
      success : function(data){
         var values = '<tr class="tr1" align="center" id="stockMenu" style="height: 40px;" >'+
            '<th class="id" width="70px">상품ID</th>'+
            '<th class="name" width="200px">상품명</th>'+
            '<th class="size" width="50px">사이즈</th>'+
         '<th class="" width="100px">색상</th>'+
            '<th class="" width="150px">재질</th>'+
            '<th class="" width="100px">가격</th>'+
            '<th class="" width="100px">시즌</th>'+
            '<th class="" width="100px">거래처코드</th>'+
            '<th width="70px">재고량</th>'+
            '<th class="" width="175px">입고가격</th>'+
            '<th class="" width="175px">입고갯수</th>'+
            '<th class="" width="180px">총합가격</th>'+
         '</tr>';
         console.log(data.list[0]);
         for(var i in data.list){
            
            
            
            
            values += '<tr align="center" style="border-top-bottom:1px solid lightgray; height: 40px;">'+         
            '<input type="hidden" name="pid" value="'+data.list[i].pid+'" readonly>'+
            '<input type="hidden" name="pname" value="'+data.list[i].pname+'" readonly>'+
            '<input type="hidden" name="psize" value="'+data.list[i].psize+'" readonly>'+
            '<input type="hidden" name="pcolor" value="'+data.list[i].pcolor+'" readonly>'+
            '<input type="hidden" name="pmaterial"value="'+data.list[i].pmaterial+'" readonly>'+
            '<input type="hidden" name="pprice" value="'+data.list[i].pprice+ '" readonly>'+
            '<input type="hidden" name="pseason" value="'+data.list[i].pseason+'" readonly>'+
            '<input type="hidden" name="providercode" value="'+data.list[i].providercode+'" readonly>'+
            '<input type="hidden" name="squantity" value="'+data.list[i].squantity+ '" readonly>'+
            '<td>'+data.list[i].pid+'</td>'+'<td>'+data.list[i].pname+'</td>'+'<td>'+data.list[i].psize+'</td>'+'<td>'+data.list[i].pcolor+'</td>'+
            '<td>'+data.list[i].pmaterial+'</td>'+'<td>'+data.list[i].pprice+'</td>'+'<td>'+data.list[i].pseason+'</td>'+'<td>'+data.list[i].providercode+'</td>'+'<td>'+data.list[i].squantity+'</td>'+
            '<td width="100px"><input type="text" name="plusprice" class="plus"></td>'+
            '<td width="100px"><input type="text" name="plusquantity" class="plus"></td>'+
            '<td width="100px"><input type="text" name="plustotal"></td>   '+'</tr>';
         }
         
         $('#stockTable').html(values);
      }   
   })
   
   
   
   $(".plus").keyup(function (evt) {
       
        var index = $(".plus").index(this);            
       var price;
       var quantity;
       var totalRowIndex;
       var convertPrice;
       var convertQuantity;    
       if( index%2==0) {   
           price =$(".plus").eq(index).val();
           quantity  =$(".plus").eq(index+1).val();
           totalRowIndex = index/2;                    
       }
       else { 
           price =$(".plus").eq(index-1).val();
           quantity  =$(".plus").eq(index).val();                  
           totalRowIndex = index/2-0.5;             
       }    
       convertPrice = !price || isNaN(price) ? 0:parseInt(price);
       convertQuantity = !quantity || isNaN(quantity) ? 0:parseInt(quantity);
        $('input[name=plustotal]').eq(totalRowIndex).val(convertPrice*convertQuantity); 
    });
   
   }
   
function store(){
   var length = $('input[name=plustotal]').length;
   var plusArray = new Array(length);
       
    for(var i=0; i<length; i++){      
      var  pidNo = Number($('input[name=pid]').eq(i).val());
      var  plusPrice = Number($('input[name=plusprice]').eq(i).val());
      var  plusquantity =  Number($('input[name=plusquantity]').eq(i).val());
      var  plustotal =  Number($('input[name=plustotal]').eq(i).val());     
        plusArray[i] = [pidNo,plusPrice,plusquantity,plustotal];                    
   }    
    jQuery.ajaxSettings.traditional = true;
    $.ajax({
         url : '/semi/plusstock',
         type : 'POST',
         data : {plusArray : JSON.stringify(plusArray )},
         dataType : "json",
         success : function(data) {
            if (Number(data.result) != 0) {
               alert('입고처리 되었습니다.'); location.reload();       
            } else {
                alert('입고처리에 실패하였습니다.');
            }
         }
      });
 
}   
</script>

</head>
<body>
<header></header>
 
<div id="title">
   | NEW PLUS
</div>
<fieldset>
<legend>&nbsp;SEARCH&nbsp;</legend>

   <div id="search">
   <select id="stockSearch">
      <option value="pid">상품ID</option>
      <option value="pname">상품명</option>
      <option value="pmaterial">재질</option>
      <option value="pseason">시즌</option>
      <option value="providercode">거래처코드</option>
   </select>
   <input type="text" id="stockvalue" ><input type="button" value="검색" onclick="search2();">
   </div>

</fieldset>
   <div id="contents" style="height:280px;">
      <table cellspacing="0" cellpadding="0" id="stockTable">
         <tr class="tr1" align="center" id="stockMenu">
            <th class="id" width="70px">상품ID</th>
            <th class="name" width="200px">상품명</th>
            <th class="size" width="50px">사이즈</th>
            <th class="" width="100px">색상</th>
            <th class="" width="150px">재질</th>
            <th class="" width="100px">가격</th>
            <th class="" width="100px">시즌</th>
            <th class="" width="100px">거래처코드</th>
            <th width="70px">재고량</th>
            <th class="" width="175px">입고가격</th>
            <th class="" width="175px">입고갯수</th>
            <th class="" width="180px">총합가격</th>            
         </tr>
         
         <%if(stockList != null){ %>
         <%for(Stock s : stockList){ %>
         <tr align="center">
         <input type="hidden" name="pid" value="<%= s.getpId() %>" readonly>
         <input type="hidden" name="pname" value="<%= s.getpName() %>" readonly>
         <input type="hidden" name="psize" value="<%= s.getpSize() %>" readonly>
         <input type="hidden" name="pcolor" value="<%= s.getpColor() %>"readonly>
         <input type="hidden" name="pmaterial"value="<%= s.getpMaterial() %>"readonly>
         <input type="hidden" name="pprice" value="<%= s.getpPrice() %>"readonly>
         <input type="hidden" name="pseason" value="<%= s.getpSeason() %>"readonly>
         <input type="hidden" name="providercode" value="<%= s.getProvidercode() %>"readonly>
         <input type="hidden" name="squantity" value="<%= s.getsQuantity() %>"readonly>
         <td width="70px"><%= s.getpId() %></td>
         <td width="200px"><%= s.getpName() %></td>
         <td width="50px"><%= s.getpSize() %></td>
         <td width="100px"><%= s.getpColor() %></td>
         <td width="150px"><%= s.getpMaterial() %></td>
         <td width="100px"><%= s.getpPrice() %></td>
         <td width="100px"><%= s.getpSeason() %></td>
         <td width="100px"><%= s.getProvidercode() %></td>
         <td width="70px"><%= s.getsQuantity() %></td>
         <td width="100px"><input type="text" name="plusprice" class="plus"></td>
         <td width="100px"><input type="text" name="plusquantity" class="plus"></td>
         <td width="100px"><input type="text" name="plustotal"></td>
         </tr>
         <%}}%>              
      </table>
      
      
   </div>
   <div id="storebtnDiv"  >
       <input type="button" value="저장" id="storebtn" style="width :140px; height:40px; " onclick="store();"> 
       </div>
 
<footer></footer>
</body>
</html>
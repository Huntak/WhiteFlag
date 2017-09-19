
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.*, member.model.vo.Member, cart.model.vo.Cart, product.model.vo.Product, product.model.vo.ProductImage" %>   
 <%
 	ArrayList<Product> pl = (ArrayList<Product>)request.getAttribute("pl");
 	ArrayList<Cart> cl = (ArrayList<Cart>)request.getAttribute("cl");
 	ArrayList<ProductImage> pimage = (ArrayList<ProductImage>)request.getAttribute("pimage");
	Member loginUser = (Member)session.getAttribute("loginUser");
	Member member = (Member)request.getAttribute("member");
	%> 
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src ="/semi/common/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
<script type="text/javascript"  src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src ="/semi/common/js/commonSize.js"></script>
<link rel = "stylesheet" type = "text/css" href = "/semi/view/orderAndPay/css/orderAndPay2.css?ver=4">

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
  		// 주문자정보&배송지정보 라디오 박스
  		$('#allcheckbox').click(function(event){
  	  		$('input:checkbox').attr('checked', 'true');
  	  	});
  		
  		$('#topmenu').css('width', screen.width);
  		var a = 2500;
  		var b = Number($('#total1').html());
  		var c = '원';
  		
  		$('#total2').html(a+b+c);
  		$('#total1').html($('#total1').html()+c);
  		
  		//주문자 정보와 동일 체크
  	   $(".zzz").change(function(){
  		if( $("input:radio[id='a']").is(":checked")){
			$('#receviername').val($("#buyername").val());
			$('#addr1').val($('#buyeraddr1').val());
			$('#addr2').val($('#buyeraddr2').val());
			$('#addr3').val($('#buyeraddr3').val());
			$('#addr4').val($('#buyeraddr4').val());
			$('#tel1').val($('#buyertel1').val());
			$('#tel2').val($('#buyertel2').val());
			$('#tel3').val($('#buyertel3').val());
			$('#recevieremail').val($('#buyeremail').val());
			
  		}else if($("input:radio[id='b']").is(":checked")){
  			$('#table2 input:eq(0), #table2 input:eq(1), #table2 input:eq(2), #table2 input:eq(4), #table2 input:eq(5),#table2 input:eq(6) ,#table2 input:eq(7) ,#table2 input:eq(8), #table2 input:eq(9)').val(null);
  		};
  	  });//주문자 정보와 동일 체크 
  	  
//마일리지 적용 버튼
$("#insert").click(function(){
		
		var miliage = <%= member.getMTOTALMILEAGE()%>
		var minus = $('#point').val();
		
		if(minus <= miliage){
  		$('#afterm').val(miliage - minus);
  		$('#dc_td').val(minus + '점');
}else{
	alert('마일지가 부족합니다');
}
  	 <% int stotal = 0; for(int i = 0; i < cl.size(); i++){ stotal += (pl.get(i).getpPrice() * cl.get(i).getQuantity());};%>
  		var total = <%= stotal + 2500%>
  		
  		$('#totalpay').val(total-minus+'원');
  		
  		$('#final_pay_input').val(total-minus+'원');

}); 	//마일리지 적용 버튼 닫기 괄호  	
 
 
 //주문 버튼 클릭시 	  
$('#final_pay_button').click(function(){
	
if($("input:radio[id='pay_card']").is(":checked") == true){
	
	var total = <%= stotal + 2500%>
	var minus = $('#point').val();
	var finalPay = total - minus;
 
	//결제 부분	
		IMP.init('imp26929010');
		IMP.request_pay({
		    pg : 'inicis', // version 1.1.0부터 지원.
		    pay_method : 'card',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : '<%= pl.get(0).getpName() + " 외" + cl.size() + " 개"%>',
		    amount : finalPay,
		    buyer_email : $('#recevieremail').val(),
		    buyer_name : $('#receviername').val(),
		    buyer_tel :  $('#tel1').val() + $('#tel2').val() + $('#tel3').val() ,
		    buyer_addr : $('#addr3').val() + $('#addr4').val() ,
		    buyer_postcode :  $('#addr1').val() +"-"+$('#addr2').val() ,
		    m_redirect_url : 'https://www.yourdomain.com/payments/complete'
		}, function(rsp) {
		    if ( rsp.success ) {
		        var msg = '결제가 완료되었습니다.';
		      
		        
		        var minus = $('#point').val();
		        var mid = $('#loginuser').val();
			    var osnumber = '2';
			    var mmileage = $('#point').val();
			    
			    var omileage = [];
			    $("input[name='mileage']").each(function(i) {
			    	omileage.push($(this).val());
		        });
		    	
		        var allpid = [];
		        $("input[name='allpid']").each(function(i) {
		        	allpid.push($(this).val());
		        });
		         var allcartid = [];
		        $("input[name='allcartid']").each(function(i) {
		        	allcartid.push($(this).val());
		        });
		        var allquantity = [];
		        $("input[name='allquantity']").each(function(i) {
		        	allquantity.push($(this).val());
		        });
		        var allprice = [];
		        $("input[name='allprice']").each(function(i) {
		        	allprice.push($(this).val());
		        });
		       var varray = [];
		        $("input[name='recevier']").each(function(i) {
		        	varray.push($(this).val());
		          });

				 //ajax 로 orderlist 값 보내기
		        $.ajaxSettings.traditional = true;
		        $.ajax({
		        	url : "/semi/payall",
		        	data : {varray : varray, mid : mid, osnumber : osnumber, omileage : omileage, allprice : allprice, allpid : allpid, allcartid : allcartid, allquantity : allquantity, minus : minus },
		        	type : "post"	
		        });	//ajax   
		        
		      //마일리지 적용 ajax
				$.ajaxSettings.traditional = true;
				$.ajax({
				url : "/semi/applyAll",
				data : {mid :mid, allprice : allprice, allquantity : allquantity, mmileage : mmileage},
				type : "post"	
				});	//ajax  
		      
		 		location.href="/semi/successAll?mid="+mid;
				
		    } else {
		        var msg = '결제에 실패하였습니다.';
		        msg += '에러내용 : ' + rsp.error_msg;
		    }
		    alert(msg);
		});
	}else{
		alert('결제방식을 선택해 주세요');
	};
				
	});	//click 	  
});//ready

//우편 번호
function searchZipcode() {
	new daum.Postcode({
		oncomplete : function(data) {
			document.getElementById("addr1").value = data.postcode1;
			document.getElementById("addr2").value = data.postcode2;
			document.getElementById("addr3").value = data.address;
			document.getElementById("addr4").focus();
			}
		}).open();
	};  	
</script>
<title>결제페이지</title>
</head>
<body>
 <header></header>

<!-- ----------------------------------------------------------------------------------------------------------------------------------------------- -->
<label id="main_label">l 결제</label>
<input type="hidden" value="<%= loginUser.getMID() %>" id="loginuser">
<div id="div_welcome" align="center">
저희 쇼핑몰을 이용해주셔서 감사합니다 <%= loginUser.getMNAME() %>님은, [<%= loginUser.getGRADE() %>]회원이십니다.
</div>


<section id ="product_section">
<table id="product_table">
  		<tr><td colspan="8" align="left" style="font-size:8pt;">! 수량변경은 장바구니 또는 상품상세보기 페이지에서만 변경할 수 있습니다.</td></tr>
  		<tr id="product_tr1">
  		<th><input type="checkbox" id="allcheckbox"></th>
  		<th id="th_image_14">사진</th>
  		<th id="th_15">제품 상세</th>
  		<th>가격</th>
  		<th id="th_17">수량</th>
  		<th>적립금</th>
  		<th>배송비</th>
  		<th>총금액</th>
  		</tr>
  		
  		<%if(pl != null && cl != null){
  			for(int i =0;  i < cl.size(); i++){%>
  		<input type="hidden" name="allpid" value="<%= pl.get(i).getpId() %>">
  		<input type="hidden" name="allcartid" value="<%= cl.get(i).getCartId() %>">	
  		<input type="hidden" name="allquantity" value="<%= cl.get(i).getQuantity() %>">
  		<input type="hidden" name="allprice" value="<%= pl.get(i).getpPrice()%>">		
  		<tr id="product_tr2">
  		<td width="5%;"><input type="checkbox" ></td>
  		<td width="12%;"><a href="/semi/detail?imageId=<%= pimage.get(i).getImageId() %>" ><image src="/semi/image/<%= pimage.get(i).getMainImage() %>"  width="90" height="100"></a></td>
  		<td width="40%;"><a href="/semi/detail?imageId=<%= pimage.get(i).getImageId() %>  %>" ><%= pl.get(i).getpName() %></a></td>
  		<td width="7%;"><%= pl.get(i).getpPrice() %></td>
  		<td width="2%;"><%= cl.get(i).getQuantity() %></td>
  		<td width="4%;">
  		<input type="text" value="<% if(loginUser != null){double mileageRate = (double)request.getAttribute("mileageRate");  %>
  		<%= Math.round((float)mileageRate * (pl.get(i).getpPrice() * cl.get(i).getQuantity())) %>
  		<%}%>" name="mileage" id="mileage">
  		</td>
  		<td width="4%;">조건</td>
  		<td width="7%;"><%= pl.get(i).getpPrice() * cl.get(i).getQuantity() %></td>
  		</tr>
  		<%}}%>
  	
  		<tr id="product_tr3"><td colspan="8" id="td_38" align="right">
  		상품구매금액 :<label><% int sum = 0; for(int i = 0;  i< cl.size(); i++){
  		sum += pl.get(i).getpPrice();} out.print(sum);%></label> &nbsp;
  		배송비 :<label>2,500</label>  &nbsp;
  		합계 :<label><% int sum2 = 2500; for(int i = 0;  i< cl.size(); i++){
  		sum2 += pl.get(i).getpPrice();} out.print(sum2);%>원</label>
  		</td>
  		</tr>
  		
  	  
  	</table>
  	</section>

<section id="info_section">
<div id="delivery_label">주문자 정보</div><p>
<table class="delivery_table" id="table1">
<tr><td class="delivery_td">&nbsp;주문하시는분</td><td>&nbsp;<input type="text" value="<%= loginUser.getMNAME() %>" id="buyername"></td></tr>
<tr><td class="delivery_td">&nbsp;주소</td>
<td>&nbsp;<input type="text"  size="10"  value="<%= loginUser.getMADDRESS1()%>" id="buyeraddr1">-
<input type="text"  size="10" value="<%= loginUser.getMADDRESS2()%>" id="buyeraddr2">
 <input type="button" value="우편번호" onclick="searchZipcode();" ><p>
&nbsp;<input type="text"  size="50" value="<%= loginUser.getMADDRESS3()%>" id="buyeraddr3"> 기본주소<p>
&nbsp;<input type="text"  size="50" value="<%= loginUser.getMADDRESS4()%>" id="buyeraddr4"> 나머지주소</td>
<tr><td class="delivery_td">&nbsp;연락처</td><td >&nbsp;<input type="tel" value="<%= loginUser.getMPHONE1()%>" size="4" id="buyertel1">-
<input type="tel" value="<%= loginUser.getMPHONE2()%>" " size="4" id="buyertel2">-
<input type="tel" value="<%= loginUser.getMPHONE3()%>" " size="4" id="buyertel3">
</td></tr>
<tr><td class="delivery_td">&nbsp;이메일</td><td >&nbsp;<input type="email" value="<%= loginUser.getMEMAIL() %>" id="buyeremail"></td></tr>	
</table>

<div id="delivery_label2">배송지 정보</div><p>
<div>
<font size="2.5" >
<input type="radio" name="choice" id="a" class="zzz">주문자 정보와 동일 
<input type="radio" name="choice" id="b" class="zzz">새로운 배송지
</font>
</div>
<table class="delivery_table" id="table2">
<tr><td class="delivery_td">&nbsp;받으시는분</td><td><input type="text" id="receviername" name="recevier" required></td></tr>
<tr><td class="delivery_td">&nbsp;주소</td><td><input type="text" id="addr1" size="10"  name="recevier" required>-<input type="text" id="addr2" size="10"  name="recevier" required>
<input type="button" value="우편번호" onclick="searchZipcode();"><p>
<input type="text" id="addr3" size="50" name="recevier" required> 기본주소<p><input  name="recevier" type="text" id="addr4" size="50" required> 나머지주소</td>
<tr><td class="delivery_td">&nbsp;연락처</td>
<td >&nbsp;<input type="tel" value="" size="4" name="recevier" id="tel1" required>-
<input type="tel" value=" " size="4" name="recevier" id="tel2" required>-
<input type="tel" value="" " size="4" name="recevier" id="tel3" required></td></tr>
<tr><td class="delivery_td">&nbsp;이메일</td><td >&nbsp;<input type="email"  id="recevieremail" name="recevier" required></td></tr>
</table>
</section>

<section id="pay_section">	
<label id="pay_label">결제예정금액</label><p>
<table id="pay1_table">
<tr id="pay1_tr1"><td>총 주문 금액</td><td>사용한 적립금</td><td>총 결제 예정 금액</td></tr>
<tr id="pay1_tr2"><td><% int total = 0; for(int i = 0; i < cl.size(); i++){ total += (pl.get(i).getpPrice() * cl.get(i).getQuantity());}; out.print(total);%>원</td>
<td class="paytable1_td"><input type="text" id="dc_td" value="0점" readonly></td>
<td class="paytable1_td"><input type="text" id="totalpay" value="<%= total+2500%>원" readonly></td></tr>
<tr id="pay1_tr3"><td colspan="3" align="left">적용할 적립금<input style="text-align:right; " value="0" size="6"  id="point"  required> 점 <button id="insert">적용</button><br>
보유 적립금 : <input style="text-align:right; border : none;" type="text" value="<%= member.getMTOTALMILEAGE() %>" size="6" readonly id="afterm"> 점<br>
-최대 사용금액은 제한이 없습니다.</td></tr>
</table>





<div ><label id="label_pay">결제정보</label></div>
<table id="pay2_table">
<tr ><td align="left"><input type="radio" id="pay_card" name="payway">카드결제 <input type="radio" id="pay_cash" name="payway">무통장입금</td>
<td id="pay_final_td"rowspan="2" align="center"><font size="2">최종 결제 금액</font><p>
<div><input type="text"  id="final_pay_input" value="<%= total+2500%>원" readonly></div><p>
<input type="button" id="final_pay_button" value="결제하기"></td></tr>
<tr><td></td></tr>
</table>
</section>

<footer></footer>
</body>
</html>
<body>

</body>
</html>
// JavaScript Document

$(function() { // = $(document).ready(function(){
	 $("#afterConfirm").hide(); 
	 $("#trPhone").hide(); 
	

	 
	 	 
	 $('input[name="choiceWay"]').change(function(){
		    if($(this).val() == "phone"){
		    	$("#userEmailDiv").val("");
		    	$("#trEmail").hide();
		    	$("#trPhone").show();	
		    	$("#foundId").text("");
		    	$("#userEmail").val("");
		    	$('#userName').val("");
		    }
		    else{
		    	$("#userPhoneDiv").val("");
		    	$("#trPhone").hide();
		    	$("#trEmail").show();		 
		    	$("#foundId").text("");
		    	$("#userPhone1").val("");
		    	$("#userPhone2").val("");
		    	$("#userPhone3").val("");	    	
		    	$('#userName').val("");
		    }
		});	 
	
});


function bringId() {
		alert("뭐여");
		var userName = $('#userName').val();
		var userEmail = $('#userEmail').val();
		var userPhone1 = $('#userPhone1').val();
		var userPhone2 = $('#userPhone2').val();
		var userPhone3 = $('#userPhone3').val();
		
			$.ajax({
				url : '/semi/findId',
				type : 'post',
				data : {
						userName : userName,
						userEmail : userEmail,
						userPhone1 : userPhone1,
						userPhone2 : userPhone2,
						userPhone3 : userPhone3		
						},    
				dataType : "json",
				success : function(data) {
					if ((data.Id) != "" && (data.Id) != null  ) {
						$('#foundId2').text("아이디 : "+data.Id);
						$('#foundId3').text("가입일자 : "+data.Edate);
						$("#beforConfirm").hide(); 
						$("#afterConfirm").show(); 
						
					} else {					
						$('#foundId').text("일치하는 정보가 없습니다.");
					}
				}
					
			});
}

// JavaScript Document
$(function(){  // = $(document).ready(function(){
	 $("#afterConfirm").hide(); 
	 $("#trPhone").hide(); 

	 
	 	 
	 $('input[name="choiceWay"]').change(function(){
		    if($(this).val() == "phone"){
		    	$("#userEmailDiv").val("");
		    	$("#trEmail").hide();
		    	$("#trPhone").show();	
		    	$("#foundId").text("");
		    	$('#userName').val("");
		    	$('#userid').val("");
		    }
		    else{
		    	$("#userPhoneDiv").val("");
		    	$("#trPhone").hide();
		    	$("#trEmail").show();		 
		    	$("#foundId").text("");
		    	$('#userName').val("");
		    	$('#userid').val("");
		    }
		});	 
});

function bringId() {
	var userId = $('#userId').val();
	var userName = $('#userName').val();
	var userEmail = $('#userEmail').val();
	var userPhone1 = $('#userPhone1').val();
	var userPhone2 = $('#userPhone2').val();
	var userPhone3 = $('#userPhone3').val();
	
		$.ajax({
			url : '/semi/findPwd',
			type : 'post',
			data : {		
					userId : userId,
					userName : userName,
					userEmail : userEmail,
					userPhone1 : userPhone1,
					userPhone2 : userPhone2,
					userPhone3 : userPhone3		
					},    
			dataType : "json",
			success : function(data) {
				if ((data.Id) != "" && (data.Id) != null  ) {
					$("#beforConfirm").hide(); 
					$("#afterConfirm").show(); 
					$("#afteruserId").val(data.Id);
					
				} else {					
					$('#foundId').text("일치하는 정보가 없습니다.");
				}
			}
				
		});
}




function validate() {
	var passwordRules = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
	
	if ($("#updatePwdRepeat").val() != $("#updatePwd").val()) {
		$("#warningPwd").text("입력하신 비밀번호와 일치 하지 않습니다. ");
		setTimeout(function() {
			$("#updatePwdRepeat").val("");
			$("#updatePwdRepeat").focus();
		}, 0);
		return false;
	} else {
		$("#checkPwdResult").text("");
	}
	
	var idval = document.getElementById("updatePwd").value;
	
	if (!passwordRules.test(idval)) {
		$("#warningPwd").text("영문,숫자,특수문자 포함  8글자 이상 20글자이하만 가능합니다.");
		setTimeout(function() {
			$("#updatePwd").val("");
			$("#updatePwd").focus();
		}, 0);
		return false;
	} else {
		$("#warningPwd").text("");
		return true;
	}

	 

}
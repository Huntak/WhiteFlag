
var idRules = /^[a-z0-9]{5,12}$/;
var passwordRules = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;



function infoConfirmRepeat(){
    var idval = $("#userId").val();
	if (idval.length < 5 || idval.length > 12 || (!idRules.test(idval)) || (!passwordRules.test($("#userPwd").val())) ) {
		
		alert(idval.length  +" "+ idRules.test(idval)+" "+passwordRules.test($("#userPwd").val()));
		$("#userId").val("아이디와 패스워드 형식을 확인해주세요");
		return false;
	} 
	
}



// JavaScript Document

var nameRules = /^[가-힣]{2,5}|[a-zA-Z]{2,9}\s[a-zA-Z]{2,9}$/;
var idRules = /^[a-z0-9]{5,12}$/;
var passwordRules = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
var emailRules1 = /^[a-zA-Z0-9]{3,16}$/;
var emailRules2 = /^(?=.*[0-9a-zA-Z])(?=.*[.])(?=.*[a-zA-Z]).{7,20}$/;
var birthCheck = /^[0-9]{6}$/;
var phoneRules1 = /^\d{3,4}$/;
var phoneRules2 = /^\d{4}$/

 

$(function() { // = $(document).ready(function(){
	transferFocus();
	$("#userName").focus();
 
	 
});

function transferFocus() {
	$("input").not($(":submit")).keypress(
			function(e) {
				if (e.keyCode == 13) {
					var selectTag = $('select');
					var fields = $(this).parents("form, body").find(
							"button, input, selectTag");
					var index = fields.index(this);
					if (index > -1 && (index + 1) < fields.length) {
						fields.eq(index + 1).focus();
					}
					return false;
				}
			});
}

$(function() {
	$('#selectEmail').change(function() {
		if ($('#selectEmail').val() == 'direct') {
			$('#userEmail2').val("");
			$('#userEmail2').attr('readonly', false);
		} else {
			$('#userEmail2').attr('readonly', true);
			$('#userEmail2').val($(this).val());
		}
	});
});


$(function() {
	var selectedVal = $('#hiddenhp1').text();
	if( selectedVal == '02') {	$("select option[value=02]").attr("selected","selected");	}
	else if( selectedVal == '031') {	$("select option[value=031]").attr("selected","selected");	}
	else if( selectedVal == '032') {	$("select option[value=032]").attr("selected","selected");	}
	else if( selectedVal == '033') {	$("select option[value=033]").attr("selected","selected");	}
	else if( selectedVal == '041') {	$("select option[value=041]").attr("selected","selected");	}
	else if( selectedVal == '042') {	$("select option[value=042]").attr("selected","selected");	}
	else if( selectedVal == '043') {	$("select option[value=043]").attr("selected","selected");	}
	else if( selectedVal == '044') {	$("select option[value=044]").attr("selected","selected");	}
	else if( selectedVal == '051') {	$("select option[value=051]").attr("selected","selected");	}
	else if( selectedVal == '052') {	$("select option[value=052]").attr("selected","selected");	}
	else if( selectedVal == '053') {	$("select option[value=053]").attr("selected","selected");	}
	else if( selectedVal == '054') {	$("select option[value=054]").attr("selected","selected");	}
	else if( selectedVal == '055') {	$("select option[value=055]").attr("selected","selected");	}
	else if( selectedVal == '061') {	$("select option[value=061]").attr("selected","selected");	}
	else if( selectedVal == '062') {	$("select option[value=062]").attr("selected","selected");	}
	else if( selectedVal == '063') {	$("select option[value=063]").attr("selected","selected");	}
	else if( selectedVal == '064') {	$("select option[value=064]").attr("selected","selected");	}
	else if( selectedVal == '0502') {	$("select option[value=0502]").attr("selected","selected");	}
	else if( selectedVal == '0503') {	$("select option[value=0503]").attr("selected","selected");	}
	else if( selectedVal == '0504') {	$("select option[value=0504]").attr("selected","selected");	}
	else if( selectedVal == '0505') {	$("select option[value=0505]").attr("selected","selected");	}
	else if( selectedVal == '0506') {	$("select option[value=0506]").attr("selected","selected");	}
	else if( selectedVal == '0507') {	$("select option[value=0507]").attr("selected","selected");	}
	else if( selectedVal == '010') {	$("select option[value=010]").attr("selected","selected");	}
	else if( selectedVal == '011') {	$("select option[value=011]").attr("selected","selected");	}
	else if( selectedVal == '016') {	$("select option[value=016]").attr("selected","selected");	}
	else if( selectedVal == '017') {	$("select option[value=017]").attr("selected","selected");	}
	else if( selectedVal == '018') {	$("select option[value=018]").attr("selected","selected");	}
	else if( selectedVal == '019') {	$("select option[value=019]").attr("selected","selected");	}
	
	$('#hiddenhp1').text(""); 

});

$(function() {
	$("input")
			.change(
					function() {
						var currentInput = $(this).attr("id");
						var currentInputValue = $(this).val();

						 if (currentInput == "userPwd") {
							if (attempRegularExpression(passwordRules,
									currentInputValue) == false) {
								$("#pwd").text(
										"영문,숫자,특수문자 포함  8글자 이상 20글자이하만 가능합니다.");
								setTimeout(function() {
									$("#userPwd").val("");
									$("#userPwd").focus();
								}, 0);
							} else {
								$("#pwd").text("");
							}
						} else if (currentInput == "userPwdCheck") {
							var beforepassword = $("#userPwd").val();
							if (currentInputValue != beforepassword) {
								$("#checkPwdResult").text(
										"입력하신 비밀번호와 일치 하지 않습니다. ");
								setTimeout(function() {
									$("#userPwdCheck").val("");
									$("#userPwdCheck").focus();
								}, 0);
							} else {
								$("#checkPwdResult").text("");
							}
						} else if (currentInput == "userEmail1") {
							if (attempRegularExpression(emailRules1,
									currentInputValue) == false) {
								$("#email1").text("올바른 이메일 형식을 입력해주세요.");
								setTimeout(function() {
									$("#userEmail1").val("");
									$("#userEmail1").focus();
								}, 0);
							} else {
								$("#email1").text("");
							}
						} else if (currentInput == "userEmail2") {
							if (attempRegularExpression(emailRules2,
									currentInputValue) == false) {
								$("#email2").text("올바른 이메일 형식을 입력해주세요.");
								setTimeout(function() {
									$("#userEmail2").val("");
									$("#userEmail2").focus();
								}, 0);
							} else {
								$("#email2").text("");
							}
						}  else if (currentInput == "hp2") {
							if (attempRegularExpression(phoneRules1,
									currentInputValue) == false) {
								$("#phoneError").text("올바른 형식으로 입력해주세요.");
								setTimeout(function() {

									$("#hp2").val("");
									$("#hp2").focus();
								}, 0);
							} else {
								$("#phoneError").text("");
							}
						} else if (currentInput == "hp3") {
							if (attempRegularExpression(phoneRules2,
									currentInputValue) == false) {
								$("#phoneError").text("올바른 형식으로 입력해주세요.");
								setTimeout(function() {

									$("#hp3").val("");
									$("#hp3").focus();
								}, 0);
							} else {
								$("#phoneError").text("");
							}
						} else if (currentInput == "post1") {
							if (currentInputValue == ""
									|| currentInputValue == null) {
								$("#postError")
										.text("우편번호 검색을 클릭하여 주소를 입력하세요.");

							} else {
								$("#phoneError").text("");
							}
						} else if (currentInput == "address2") {
							alret(currentInputValue);
							if (currentInputValue == ""
									|| currentInputValue == null
									|| currentInputValue == "상세주소입력") {
								alert("왜");
								$("#addressError").text("상세 주소를 입력하세요.");

							} else {
								$("#addressError").text("");
							}
						}

					});
});

function attempRegularExpression(rule, value) {
	return rule.test(value);
}

function searchZipcode() {
	new daum.Postcode({
		oncomplete : function(data) {
			document.getElementById("post1").value = data.postcode1;
			document.getElementById("post2").value = data.postcode2;
			document.getElementById("address").value = data.address;
			document.getElementById("address2").focus();
		}
	}).open();
}


function validate() {

	var address2 = $("#address2").val();

	if (address2 == "" || address2 == null || address2 == "상세주소입력") {
		$("#addressError").text("상세 주소를 입력하세요.");
		return false;
	} else {
		$("#addressError").text("");
	}

	if ($("#post1").val() == "" || $("#post1").val() == null) {
		$("#postError").text("우편번호 검색을 클릭하여 주소를 입력하세요.");
		return false;
	} else {
		$("#phoneError").text("");
	}

	if (attempRegularExpression(phoneRules2, $("#hp3").val()) == false) {
		$("#phoneError").text("올바른 형식으로 입력해주세요.");
		setTimeout(function() {
			$("#hp3").val("");
			$("#hp3").focus();
		}, 0);
		return false;
	} else {
		$("#phoneError").text("");
	}

	if (attempRegularExpression(phoneRules1, $("#hp2").val()) == false) {
		$("#phoneError").text("올바른 형식으로 입력해주세요.");
		setTimeout(function() {
			$("#hp2").val("");
			$("#hp2").focus();
		}, 0);
		return false;
	} else {
		$("#phoneError").text("");
	}

	if (attempRegularExpression(emailRules2, $("#userEmail2").val()) == false) {
		$("#email2").text("올바른 이메일 형식을 입력해주세요.");
		setTimeout(function() {
			$("#userEmail2").val("");
			$("#userEmail2").focus();
		}, 0);
		return false;
	} else {
		$("#email2").text("");
	}

	if (attempRegularExpression(emailRules1, $("#userEmail1").val()) == false) {
		$("#email1").text("올바른 이메일 형식을 입력해주세요.");
		setTimeout(function() {
			$("#userEmail1").val("");
			$("#userEmail1").focus();
		}, 0);
		return false;
	} else {
		$("#email1").text("");
	}

	if ($("#userPwdCheck").val() != $("#userPwd").val()) {
		$("#checkPwdResult").text("입력하신 비밀번호와 일치 하지 않습니다. ");
		setTimeout(function() {
			$("#userPwdCheck").val("");
			$("#userPwdCheck").focus();
		}, 0);
		return false;
	} else {
		$("#checkPwdResult").text("");
	}

	if (attempRegularExpression(passwordRules, $("#userPwd").val()) == false) {
		$("#pwd").text("영문,숫자,특수문자 포함  8글자 이상 20글자이하만 가능합니다.");
		setTimeout(function() {
			$("#userPwd").val("");
			$("#userPwd").focus();
		}, 0);
		return false;
	} else {
		$("#pwd").text("");
		return true;
	}

	if (attempRegularExpression(nameRules, $("#userName").val()) == false) {
		$("#checkNameResult").text(
				"한글은 2 ~ 5글자만 가능하며, 영문은  공백포함되야하며 6글자 이상 19글자이하만 가능합니다.");
		setTimeout(function() {
			$("#userName").val("");
			$("#userName").focus();
		}, 0);
		return false;
	} else {
		$("#checkNameResult").text("");
		
	}

}



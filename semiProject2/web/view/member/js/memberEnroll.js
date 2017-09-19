// JavaScript Document

var nameRules = /^[가-힣]{2,5}|[a-zA-Z]{2,9}\s[a-zA-Z]{2,9}$/;
var idRules = /^[a-z0-9]{5,12}$/;
var passwordRules = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
var emailRules1 = /^[a-zA-Z0-9]{3,16}$/;
var emailRules2 = /^(?=.*[0-9a-zA-Z])(?=.*[.])(?=.*[a-zA-Z]).{7,20}$/;
var birthCheck = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))$/;
var phoneRules1 = /^\d{3,4}$/;
var phoneRules2 = /^\d{4}$/

//var arr = new Array('userName', 'nameRules', 'CCC', 'ZZZ');

$(function() { // = $(document).ready(function(){
	$("#confirmIdRepeat").hide();
	transferFocus();
	$("#userName").focus();
});

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

	$('#enrollButton').attr('disabled', true);
	$("#inforCheck").text("약관에 동의하여 주세요.");

	$("#agreeCheck1")
			.change(
					function() {
						if ($('input:checkbox[id="agreeCheck1"]')
								.is(":checked")
								&& $('input:checkbox[id="agreeCheck2"]').is(
										":checked")) {
							$('#enrollButton').attr('disabled', false);
							$("#inforCheck").text("");
						} else {
							$('#enrollButton').attr('disabled', true);
							$("#inforCheck").text("약관에 동의하여 주세요.");
						}
					});

	$("#agreeCheck2")
			.change(
					function() {
						if ($('input:checkbox[id="agreeCheck1"]')
								.is(":checked")
								&& $('input:checkbox[id="agreeCheck2"]').is(
										":checked")) {
							$('#enrollButton').attr('disabled', false);
							$("#inforCheck").text("");
						} else {
							$('#enrollButton').attr('disabled', true);
							$("#inforCheck").text("약관에 동의하여 주세요.");
						}
					});

});

$(function() {
	$("input")
			.change(
					function() {
						var currentInput = $(this).attr("id");
						var currentInputValue = $(this).val();

						if (currentInput == "userName") {
							if (attempRegularExpression(nameRules,
									currentInputValue) == false) {
								$("#checkNameResult")
										.text(
												"한글은 2 ~ 5글자만 가능하며, 영문은  공백포함되야하며 6글자 이상 19글자이하만 가능합니다.");
								setTimeout(function() {
									$("#userName").val("");
									$("#userName").focus();
								}, 0);
							} else {
								$("#checkNameResult").text("");
								return true;
							}
						} else if (currentInput == "userPwd") {
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
						} else if (currentInput == "birth") {
							if (attempRegularExpression(birthCheck,
									currentInputValue) == false) {
								$("#birthError").text("주민번호앞 6자리를 입력해주세요.");
								setTimeout(function() {
									$("#birth").val("");
									$("#birth").focus();
								}, 0);
							} else {
								$("#birthError").text("");
							}
						} else if (currentInput == "hp2") {
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

function idCheck() {
	$("#confirmIdRepeat").show();
	
	

}

function exitClick() {
	$("#confirmIdRepeat").hide();
}

function confirmId() {
	var idval = document.getElementById("confirmText").value;
	if (idval.length < 5 || idval.length > 12) {
		$("#afterCheck").text("5글자 이상 12글자 이하로 사용 가능합니다.");
		return false;
	} else if (!idRules.test(idval)) {
		$("#afterCheck").text("영문과 숫자만 가능합니다.");
		return false;
	} else {
		$("#afterCheck").text("");
		var userid = $('#confirmText').val();
		if (userid != "") {
			//서버로 부터 중복체크   

			$.ajax({
				url : '/semi/idCheck',
				type : 'get',
				data : {
					parameteruserid : userid
				},
				dataType : "json",
				success : function(data) {
					if (Number(data.result) == 0) {
						$('#afterCheck').text('사용 가능한 아이디 입니다.');
						document.getElementById("useid").disabled = false;
					} else {
						$('#userId').css('border', '3px solid red');
						$('#afterCheck').text('사용 불가능한 아이디 입니다.');
						document.getElementById("useid").disabled = true;
					}
				}
			});
		} else {
			alert("아이디를 입력해주세요");
		}
	}
}

function useTheId() {
	document.getElementById("userId").value = document
			.getElementById("confirmText").value;
	$("#confirmIdRepeat").hide();
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

	if (attempRegularExpression(birthCheck, $("#birth").val()) == false) {
		$("#birthError").text("주민번호앞 6자리를 입력해주세요.");
		setTimeout(function() {
			$("#birth").val("");
			$("#birth").focus();
		}, 0);
		return false;
	} else {
		$("#birthError").text("");
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
		return true;
	}

}

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

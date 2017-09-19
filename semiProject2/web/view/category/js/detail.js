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
	$('#topmenu').css('width', screen.width);
	$('#bottom').css('width', screen.width);
});

$(function(){
	listR(1);
	listQ(1);
});

$(function(){
	$(document).on("mouseover", ".overa", function(e){
		$(this).next().css({"top": e.clientY + $(window).scrollTop(),"left": e.clientX + $(window).scrollLeft(),"position": "absolute", "z-index" : "9000"}).show();
	});
	$(document).on("mouseleave", ".overa", function(e){
		$(this).next().hide();
	});
});

function a(index){
	if(index==1){
		var colorVal = $("#selectColor").val();
		var sizeVal = $("#selectSize").val();
		
		if(colorVal == "")
			alert("색상를 선택해주세요");
		else if(sizeVal == "")
			alert("사이즈를 선택해주세요");
		else{
			var selectedQuantity = $("#selectQuantity").val();
			var size = $("#selectSize option:selected").text().trim();
			var quantity1 = size.split(": ");
			var quantity2 = quantity1[1].split("개");
			
			if(quantity2[0] >= selectedQuantity){
				document.mysubmit.action="/semi/orderDetail";
				document.mysubmit.submit();
			}else{
				alert("수량을 확인해주세요.");
			}
		}
	}
	
	if(index==2){
		var colorVal = $("#selectColor").val();
		var sizeVal = $("#selectSize").val();
		
		if(colorVal == "")
			alert("색상를 선택해주세요");
		else if(sizeVal == "")
			alert("사이즈를 선택해주세요");
		else{
			var selectedQuantity = $("#selectQuantity").val();
			var size = $("#selectSize option:selected").text().trim();
			var quantity1 = size.split(": ");
			var quantity2 = quantity1[1].split("개");
			
			if(quantity2[0] >= selectedQuantity){
				document.mysubmit.action="/semi/addcart";
				document.mysubmit.submit();
			}else{
				alert("수량을 확인해주세요.");
			}
		}
	}
}

function printListR(data, loginUser) {
	//alert();
	$("#reviewTable tr").remove();
	
	var out = "";
	
	var listCountR = data.listCountR;
	var currentPageR = data.currentPageR;
	var startPageR = data.startPageR;
	var endPageR = data.endPageR;
	var maxPageR = data.maxPageR;
	
	out += "<tr><th id=\"th1\">no</th><th id=\"th2\">subject</th><th id=\"th3\">name</th><th id=\"th4\">hit</th></tr>";
	if(data.listR.length >= 5){
		for(var i in data.listR){
			//console.log(data.listR[i].rNumber);
			out += "<tr><td>" + data.listR[i].rNumber + "</td><td class=\"td2\">";
			if(loginUser != null){
				if(loginUser == data.listR[i].mId || loginUser == "admin"){
					out += "<a href=\"/semi/rdetail?rnumber=" + data.listR[i].rNumber + "\">" + data.listR[i].rTitle + "</a>"; 
				}else{
					out += data.listR[i].rTitle;
				}
			}else{
				out += "<a href=\"/semi/view/member/loginForm.jsp\">" + data.listR[i].rTitle + "</a>"; 
			}
			if(data.listR[i].rReadcount >= 100){
				out += " <img src=\"/semi/view/category/images/icon_hit.gif\" width=\"18\" height=\"9\" border=\"0\">";
			}
			if(data.listR[i].rImage != null){
				out += " <a href=\"#\" class=\"overa\"><img src=\"/semi/view/category/images/icon_file.gif\" width=\"13\" height=\"13\" border=\"0\"></a>";
				out += "<DIV class=\"Text1\" STYLE=\"DISPLAY:none;\"><TABLE BOERDER=0 CELLPADDING=0 CELLSPACING=10>";
				out += "<TR><TD><img src=\"/semi/reviewUploadFiles/" + data.listR[i].rImage + "\" width=\"100\" height=\"143\" border=\"0\"></TD></TR></TABLE></DIV>";
			}
			out += "</td><td>" + data.listR[i].mId + "</td><td>" + data.listR[i].rReadcount + "</td></tr>";
		}
	}else{
		for(var i in data.listR){
			out += "<tr><td>" + data.listR[i].rNumber + "</td><td class=\"td2\">";
			if(loginUser != null){
				if(loginUser == data.listR[i].mId || loginUser == "admin"){
					out += "<a href=\"/semi/rdetail?rnumber=" + data.listR[i].rNumber + "\">" + data.listR[i].rTitle + "</a>"; 
				}else{
					out += data.listR[i].rTitle;
				}
			}else{
				out += "<a href=\"/semi/view/member/loginForm.jsp\">" + data.listR[i].rTitle + "</a>"; 
			}
			if(data.listR[i].rReadcount >= 100){
				out += " <img src=\"/semi/view/category/images/icon_hit.gif\" width=\"18\" height=\"9\" border=\"0\">";
			}
			if(data.listR[i].rImage != null){
				out += " <a href=\"#\" class=\"overa\"><img src=\"/semi/view/category/images/icon_file.gif\" width=\"13\" height=\"13\" border=\"0\"></a>";
				out += "<DIV class=\"Text1\" STYLE=\"DISPLAY:none;\"><TABLE BOERDER=0 CELLPADDING=0 CELLSPACING=10>";
				out += "<TR><TD><img src=\"/semi/reviewUploadFiles/" + data.listR[i].rImage + "\" width=\"100\" height=\"143\" border=\"0\"></TD></TR></TABLE></DIV>";
			}
			out += "</td><td>" + data.listR[i].mId + "</td><td>" + data.listR[i].rReadcount + "</td></tr>";
		}
		
		for(var i = 0; i < 5 - data.listR.length; i++){
			out += "<tr><td></td><td class=\"td2\"></td><td></td><td></td></tr>";
		}
	}
	out += "<tr><td COLSPAN=4 align=right valign=bottom id=\"buttonTd\">";
	if(loginUser != null){
		out += "<a href=\"/semi/reviewToWrite\"><button>WRITE A REVIEW</button></a>&nbsp;&nbsp;";
	}else{
		out += "<a href=\"/semi/view/member/loginForm.jsp\"><button>WRITE A REVIEW</button></a>&nbsp;&nbsp;";
	}
	out += "<a href=\"/semi/rlist\"><button>GO TO REVIEW BOARD</button></a></td></tr>";
	out += "<tr><td COLSPAN=4 align=center id=\"lastTd\"><table id=\"pageTableR\"><tr><td>";
	if(currentPageR <= 1){
		out += "<img src=\"/semi/view/category/images/DoubleChevronLeft.png\" width=\"16\" height=\"16\" border=\"0\">";
	}else{
		out += "<a class=\"drPrevList\" onclick=\"listR(" + (currentPageR - 1) + ")\">";
		out += "<img src=\"/semi/view/category/images/DoubleChevronLeft.png\" width=\"16\" height=\"16\" border=\"0\" style=\"cursor : pointer;\"></a>";
	}
	out += "</td>";
	if(endPageR == 0){
		out += "<td><b>1</b></td>";
	}else{
		for(var u = startPageR; u <= endPageR; u++){ 
			if(u==currentPageR){
				out += "<td><b>" + u + "</b></td>";
			}else{
				out += "<td><a class=\"drSelectList\" onclick=\"listR(" + u + ")\">" + u + "</a></td>";
			}
		}
	}
	if(currentPageR >= maxPageR){
		out += "<td><img src=\"/semi/view/category/images/DoubleChevronRight.png\" width=\"16\" height=\"16\" border=\"0\"></td>";
	}else{
		out += "<td><a class=\"drNextList\" onclick=\"listR(" + (currentPageR + 1) + ")\">"
		out += "<img src=\"/semi/view/category/images/DoubleChevronRight.png\" width=\"16\" height=\"16\" border=\"0\" style=\"cursor : pointer;\"></a></td>"
	}
	out += "</tr></table></td></tr>";
	
	$("#reviewTable").html(out);
}

function printListQ(data, loginUser) {
	$("#qnaTable tr").remove();
	
	var out = "";
	
	var listCountQ = data.listCountQ;
	var currentPageQ = data.currentPageQ;
	var startPageQ = data.startPageQ;
	var endPageQ = data.endPageQ;
	var maxPageQ = data.maxPageQ;
	
	out += "<tr><th id=\"th1\">no</th><th id=\"th2\">category</th><th id=\"th3\">subject</th><th id=\"th4\">name</th><th id=\"th5\">date</th><th id=\"th6\">hit</th></tr>";
	if(data.listQ.length >= 5){
		for(var i in data.listQ){
			out += "<tr><td>" + data.listQ[i].qNumber + "</td><td>";
			if(data.listQ[i].qImage != null){
				out += "<a href=\"#\" class=\"overa\"><img src=\"/semi/view/category/images/icon_file.gif\" width=\"13\" height=\"13\" border=\"0\"></a>";
				out += "<DIV class=\"Text1\" STYLE=\"DISPLAY:none;\"><TABLE BOERDER=0 CELLPADDING=0 CELLSPACING=10><TR><TD>";
				out += "<img src=\"/semi/qnaUploadFiles/" + data.listQ[i].qImage + "\" width=\"100\" height=\"143\" border=\"0\"></TD></TR></TABLE></DIV>";
			}
			out += "</td><td class=\"td3\"><img src=\"/semi/view/category/images/icon_lock.gif\" width=\"10\" height=\"12\" border=\"0\"> ";
			if(loginUser != null){
				if(loginUser == data.listQ[i].mId || loginUser == "admin"){
					out += "<a href=\"/semi/qdetail?qnumber=" + data.listQ[i].qNumber + "\">" + data.listQ[i].qTitle + "</a>";
				}else{
					out += data.listQ[i].qTitle;
				}
			}else{
				out += "<a href=\"/semi/view/member/loginForm.jsp\">" + data.listQ[i].qTitle + "</a>"; 
			}
			if(data.listQ[i].qReadcount >= 100){
				out += " <img src=\"/semi/view/category/images/icon_hit.gif\" width=\"18\" height=\"9\" border=\"0\">";
			}
			out += "</td><td>" + data.listQ[i].mId + "</td><td>" + data.listQ[i].qDate + "</td><td>" + data.listQ[i].qReadcount + "</td></tr>";
		}
	}else{
		for(var i in data.listQ){
			out += "<tr><td>" + data.listQ[i].qNumber + "</td><td>";
			if(data.listQ[i].qImage != null){
				out += "<a href=\"#\" class=\"overa\"><img src=\"/semi/view/category/images/icon_file.gif\" width=\"13\" height=\"13\" border=\"0\"></a>";
				out += "<DIV class=\"Text1\" STYLE=\"DISPLAY:none;\"><TABLE BOERDER=0 CELLPADDING=0 CELLSPACING=10><TR><TD>";
				out += "<img src=\"/semi/qnaUploadFiles/" + data.listQ[i].qImage + "\" width=\"100\" height=\"143\" border=\"0\"></TD></TR></TABLE></DIV>";
			}
			out += "</td><td class=\"td3\"><img src=\"/semi/view/category/images/icon_lock.gif\" width=\"10\" height=\"12\" border=\"0\"> ";
			if(loginUser != null){
				if(loginUser == data.listQ[i].mId || loginUser == "admin"){
					out += "<a href=\"/semi/qdetail?qnumber=" + data.listQ[i].qNumber + "\">" + data.listQ[i].qTitle + "</a>";
				}else{
					out += data.listQ[i].qTitle;
				}
			}else{
				out += "<a href=\"/semi/view/member/loginForm.jsp\">" + data.listQ[i].qTitle + "</a>"; 
			}
			if(data.listQ[i].qReadcount >= 100){
				out += " <img src=\"/semi/view/category/images/icon_hit.gif\" width=\"18\" height=\"9\" border=\"0\">";
			}
			out += "</td><td>" + data.listQ[i].mId + "</td><td>" + data.listQ[i].qDate + "</td><td>" + data.listQ[i].qReadcount + "</td></tr>";
		}
		
		for(var i = 0; i < 5 - data.listQ.length; i++){
			out += "<tr><td></td><td></td><td class=\"td3\"></td><td></td><td></td><td></td></tr>";
		}
	}
	out += "<tr><td COLSPAN=6 align=right valign=bottom id=\"buttonTd2\">";
	if(loginUser != null){
		out += "<a href=\"/semi/qnaToWrite\"><button>WRITE A Q&A</button></a>&nbsp;&nbsp;";
	}else{
		out += "<a href=\"/semi/view/member/loginForm.jsp\"><button>WRITE A Q&A</button></a>&nbsp;&nbsp;";
	}
	
	out += "<a href=\"/semi/qlist\"><button>GO TO Q&A BOARD</button></a></td></tr><tr><td COLSPAN=6 align=center id=\"lastTd2\">";
	out += "<table id=\"pageTableQ\"><tr><td>";
	if(currentPageQ <= 1){
		out += "<img src=\"/semi/view/category/images/DoubleChevronLeft.png\" width=\"16\" height=\"16\" border=\"0\">";
	}else{
		out += "<a class=\"drPrevList\" onclick=\"listQ(" + (currentPageQ - 1) + ")\">";
		out += "<img src=\"/semi/view/category/images/DoubleChevronLeft.png\" width=\"16\" height=\"16\" border=\"0\" style=\"cursor : pointer;\"></a>";
	}
	out += "</td>";
	if(endPageQ == 0){
		out += "<td><b>1</b></td>";
	}else{
		for(var u = startPageQ; u <= endPageQ; u++){ 
			if(u==currentPageQ){
				out += "<td><b>" + u + "</b></td>";
			}else{
				out += "<td><a class=\"drSelectList\" onclick=\"listQ(" + u + ")\">" + u + "</a></td>";
			}
		}
	}
	if(currentPageQ >= maxPageQ){
		out += "<td><img src=\"/semi/view/category/images/DoubleChevronRight.png\" width=\"16\" height=\"16\" border=\"0\"></td>";
	}else{
		out += "<td><a class=\"drNextList\" onclick=\"listQ(" + (currentPageQ + 1) + ")\">"
		out += "<img src=\"/semi/view/category/images/DoubleChevronRight.png\" width=\"16\" height=\"16\" border=\"0\" style=\"cursor : pointer;\"></a></td>"
	}
	out += "</tr></table></td></tr>";
	
	$("#qnaTable").html(out);
}
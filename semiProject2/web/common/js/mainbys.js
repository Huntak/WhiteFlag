// JavaScript Document

var start = Number(0);
var current = Number(0);
var myVar;
$(function(){  // = $(document).ready(function(){
	$("#center ul>li>img").hide(); 
	$("#center>ul>li>img").first().show();  
	 keepChange(); 
});


function keepChange(){
	myVar = setInterval(changingImg,2000);
}

function changingImg(){	
	$("#center ul>li>img").hide(); 
    $("#center ul>li>img").eq(current).show();     
    current += 1;
    if(current==4) current =0;
}

 
function beforeImg(){	
	if(myVar !=null) {  clearInterval(myVar); }
	if(current!=0){
		$("#center ul>li>img").hide(); 
	    $("#center ul>li>img").eq(current-1).show();      
	}else {
		$("#center ul>li>img").hide(); 
	    $("#center ul>li>img").eq(3).show();     	 
	} 
	current -= 1;
	if(current ==-1){ current=3;}
	
}

function afterImg(){
	if(myVar !=null) {  clearInterval(myVar); }
	if(current!=3){
		$("#center ul>li>img").hide(); 
	    $("#center ul>li>img").eq(current+1).show();      
	}else {
		$("#center ul>li>img").hide(); 
	    $("#center ul>li>img").eq(0).show();     	 
	}
	current += 1;
	if(current ==4){ current=0;}
}



$(function(){  // = $(document).ready(function(){	
	 $("header").load("/semi/mainTop.jsp");
	 $("header").css("width", screen.width-20);
	 $("header").css("height", 50);
	
	 
	 $("#center").css("width", screen.width-20);
	 $("#center").css("height", screen.height-250);
	 
	 $("footer").load("/semi/mainFooter.jsp");
	 $("footer").css("width", screen.width-20);  
	 $("footer").css("height", 50);
});
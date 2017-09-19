// JavaScript Document

$(function(){  // = $(document).ready(function(){
   if($("#hiddenErrormessage").val()!="" ) {
      
      $("#loginFailLabel").text("아이디와 비밀번호를 확인해주세요");
   }
   
   else { $("#loginFailLabel").text(""); }
 
  
});
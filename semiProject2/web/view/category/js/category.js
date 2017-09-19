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
	
		$('#productImgDiv .image').mouseenter(function(){
			$(this).find('.textNo').css({
				'font-size' : '.8em',
				'position' : 'absolute',
				'top' : '40%',
				'left' : '43%',
				'visibility' : 'visible'
			});
			
			$(this).find('.text').css({
				'font-size' : '.8em',
				'position' : 'absolute',
				'top' : '40%',
				'left' : '35%',
				'visibility' : 'visible'
			});
			
			$(this).find('img').css({
				'opacity' : '0.2',
				'transition' : 'all 0.1s ease-out',
				'-webkit-transition' : 'all 0.1s ease-out',
				'-moz-transition' : 'all 0.1s ease-out',
				'-o-transition' : 'all 0.1s ease-out',
				'-ms-transition' : 'all 0.1s ease-out'
			});
		});
		
		$('#productImgDiv .image').mouseleave(function(){
			$(this).find('.textNo').css({
				'font-size' : '.8em',
				'position' : 'absolute',
				'top' : '40%',
				'left' : '43%',
				'visibility' : 'hidden'
			});
			
			$(this).find('.text').css({
				'font-size' : '.8em',
				'position' : 'absolute',
				'top' : '40%',
				'left' : '35%',
				'visibility' : 'hidden'
			});
			
			$(this).find('img').css({
				'opacity' : '1',
				'transition' : 'all 0.1s ease-out',
				'-webkit-transition' : 'all 0.1s ease-out',
				'-moz-transition' : 'all 0.1s ease-out',
				'-o-transition' : 'all 0.1s ease-out',
				'-ms-transition' : 'all 0.1s ease-out'
			});
		});
	});
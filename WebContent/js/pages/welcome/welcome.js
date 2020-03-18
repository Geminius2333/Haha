// JavaScript Document

//设置背景自适应浏览器宽度
window.onload = function() {

	var winHeight = document.documentElement.clientHeight;
	var winWidth = window.screen.width;
	document.getElementById("backgrounds").style.height = winHeight + "px";
	document.getElementById("backgrounds").style.width = winWidth + "px";
	$(".backgrounds").css("background",
	"White");
	$(".title").css("backgroundImage",
	"url(bg_img/5.jpg)");
	$(".second_li1,.second_li2,.second_li3").hide();
}

$(function(){
	$(".second_li1,.second_li2,.second_li3").hide();
	
	$(".first_li").hover(function(){
		$(".second_li1,.second_li2,.second_li3").hide();
	    },function(){
	    	
	});
	
    $(".first_li1").hover(function(){
     $(".second_li1").show();
     $(".second_li2,.second_li3").hide();
    },function(){
   
    });
    
    $(".first_li2").hover(function(){
     $(".second_li2").show();
     $(".second_li1,.second_li3").hide();
    },function(){
     
    });
    
    $(".first_li3").hover(function(){
     $(".second_li3").show();
     $(".second_li1,.second_li2").hide();
    },function(){
     
    });
    
 });



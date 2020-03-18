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
	$(".menu_div").hide();
	
	
	
//	document.getElementById("sender_text").style.display="none";
//	document.getElementById("sender_text").style.display="inline";
//	$("#person_manage").css("visibility","hidden");
}

$("#person_li").click(function(){
	$(".menu_div").toggle();
})





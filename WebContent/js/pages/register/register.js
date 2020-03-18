// JavaScript Document

//设置背景自适应浏览器宽度
window.onload = function() {

	var winHeight = document.documentElement.clientHeight;
	var winWidth = window.screen.width;
	document.getElementById("backgrounds").style.height = winHeight + "px";
	document.getElementById("backgrounds").style.width = winWidth + "px";

	// 设置登录框垂直居中
	document.getElementById("div_position").style.marginTop = winHeight / 2
			- 170 + "px";
}
$(document).ready(
		function() {

			/* 刷新时随机更换页面背景图片 开始  */
			// 图片数组
			var imgArr = [ "bg_img/1.jpg", "bg_img/2.jpg", "bg_img/3.jpg"];
			var index = parseInt(Math.random() * (imgArr.length));
			// 定时更换背景
			$(".backgrounds").css("backgroundImage",
					"url(" + imgArr[index] + ")");
			/* 刷新时随机更换页面背景图片 结束  */

			/*  账号、密码、验证码初始状态 开始  */
			$("#tid").focus(function() {
				if ($(this).val() == '您的用户名*')
					$(this).val("");
				$(this).css("color", "#333333");
			}).blur(function() {
				if ($(this).val() == '') {
					$(this).val("您的用户名*");
					$(this).attr("style", "");
				}
			});
			$("#pwd").focus(function() {
				if ($(this).val() == '您的密码*') {
					$(this).val("");
					$(this).attr("type", "password");
				}
				$(this).css("color", "#333333");
			}).blur(function() {
				if ($(this).val() == '') {
					$(this).val("您的密码*");
					$(this).attr("style", "");
					$(this).attr("type", "text");
				}
			});

			$("#confirm_pwd").focus(function() {
				if ($(this).val() == '确认密码*') {
					$(this).val("");
					$(this).attr("type", "text");
				}
				$(this).css("color", "#333333");
			}).blur(function() {
				if ($(this).val() == '') {
					$(this).val("确认密码*");
					$(this).attr("style", "");
					$(this).attr("type", "text");
				}
			});
			$("#email").focus(function() {
				if ($(this).val() == '您的邮箱') {
					$(this).val("");
					$(this).attr("type", "text");
				}
				$(this).css("color", "#333333");
			}).blur(function() {
				if ($(this).val() == '') {
					$(this).val("您的邮箱");
					$(this).attr("style", "");
					$(this).attr("type", "text");
				}
			});
			$("#phone").focus(function() {
				if ($(this).val() == '您的电话') {
					$(this).val("");
					$(this).attr("type", "text");
				}
				$(this).css("color", "#333333");
			}).blur(function() {
				if ($(this).val() == '') {
					$(this).val("您的电话");
					$(this).attr("style", "");
					$(this).attr("type", "text");
				}
			});
			$("#youbian").focus(function() {
				if ($(this).val() == '您的邮编') {
					$(this).val("");
					$(this).attr("type", "text");
				}
				$(this).css("color", "#333333");
			}).blur(function() {
				if ($(this).val() == '') {
					$(this).val("您的邮编");
					$(this).attr("style", "");
					$(this).attr("type", "text");
				}
			});
			
			
			$("#button").click(function() {
				register("registerForm");
					
			});
		})
		

/**
 * 此处是点击注册进入注册页面后修改表单提交的action
 * 切换到注册模式
 * @param formName 表单名称
 */
function register(formName) {
	if (check()) {
		var url = $("#log_reg_form").attr("action");
		var start = url.lastIndexOf("/");
		var end = url.indexOf(";");
		if(end == -1)
			var newurl = url.replace(url.substring(start),"/register.jhtml");
		else
			var newurl = url.replace(url.substring(start,end),"/register.jhtml");
		$("#log_reg_form").attr("action", newurl);
		window.document.forms[formName].submit();
	}
}

/**
 * 对用户名、密码、验证码进行验证。
 * @returns {Boolean} 返回是否验证通过
 */
function check() {
	// 点击登录按钮时，对输入框的值进行验证
	if ($("#pwd").is(":visible")) {
		var pwd = $("#pwd").val();
		var tid = $("#tid").val();
		var confirm_pwd = $("#confirm_pwd").val();
		var email = $("#email").val();
		var phone = $("#phone").val();
		if (tid == "您的用户名" && pwd == "您的密码") {
			$("#contents").html("账号或密码不能为空");
			return false;
		} else if (tid == "您的用户名" || tid == " ") {
			$("#contents").html("账号不能为空");
			return false;
		} else if (pwd == "您的密码") {
			$("#contents").html("密码不能为空");
			return false;
		}else if(!tid.match(/(^[A-Za-z0-9]+$)/)){
			$("#contents").html("您的用户名格式不正确");
			return false;
		} else if(pwd!=confirm_pwd){
			$("#contents").html("两次密码不相同");
			return false;
		}else if(!pwd.match(/(^[A-Za-z0-9]{6,12}$)/)){
			$("#contents").html("密码只能由数字或字母组成，长度为6~12位");
			return false;
		}else if(!email.match(/(^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$)/)){
			$("#contents").html("您的邮箱格式不正确");
			return false;
		}else if(!phone.match(/(^[0-9]{11}$)/)){
			$("#contents").html("电话格式不正确");
			return false;
		}else if(!phone.match(/(^[0-9]{6}$)/)){
			$("#contents").html("邮编号码不正确");
			return false;
		}
		return true;
	}

	// 点击注册按钮时，对输入框的值进行验证
	if ($("#pwd").is(":hidden")) {
		var tid = $("#tid").val();
		if (tid == "您的用户名" || tid == " ") {
			$("#contents").html("账号或密码不能为空");
			return false;
		}
		return true;
	}
}
// js获取项目根路径，如： http://localhost:8080/Haha
function getRootPath() {
	// 获取当前网址，如： http://localhost:8080/Haha/pages/index.jsp
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： Haha/pages/index.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8080
	var localhostPaht = curWwwPath.substring(0, pos);
	// 获取带"/"的项目名，如：/Haha
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业二 企业信息管理系统</title>

<link type="text/css" rel="stylesheet" href="css/picUpdate.css" />
<script type="text/javascript" src="js/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/pages/welcome/welcome.js"></script>

<link href="css/bootstrap/fileinput.min.css" rel="stylesheet">
<!--引入bootstrap -->
<link rel="stylesheet" href="js/bootstrap-3.3.7-dist/css/bootstrap.css">
<script type="text/javascript" src="js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap-3.3.7-dist/js/fileinput.js"></script>
<script src="js/bootstrap-3.3.7-dist/js/zh.js" type="text/javascript"></script>
</head>
<body>

	<div class="backgrounds" id="backgrounds">
		<div class="title">
			<span class="title-span">企业管理系统</span>
			<div class="user">Hello!当前用户${User.username}</div>	
		</div>
		<div class="main">
			<!-- menu 开始 -->
			<div class="list_menu">
				<ul>
					<li class="first_li">
						<a href="pageforward.jhtml?page=welcome.jsp">首页面 🏠</a>
					</li>
					<li class=" first_li1">通讯管理  💬</li>
					<li class="second_li1">
						<a id="userlist" href="contactslist.jhtml" >&nbsp;&nbsp;&nbsp;通讯录管理</a>
					</li>
					<li class="second_li1">
						<a href="messagelist.jhtml">&nbsp;&nbsp;&nbsp;短消息管理</a>
					</li>
					<li class=" first_li2">个人管理  😉‍</li>
						<li class="second_li2">
							<a href="pageforward.jhtml?page=userDetail.jsp">&nbsp;&nbsp;&nbsp;个人信息</a>
						</li>
						<li class="second_li2">
							<a href="pageforward.jhtml?page=picUpdate.jsp">&nbsp;&nbsp;&nbsp;头像设置</a>
						</li>
					<li class="second_li2">
						<a href="pageforward.jhtml?page=userUpdate.jsp">&nbsp;&nbsp;&nbsp;信息修改</a>
					</li>
					<li class=" first_li3">企业管理  🏦</li>
					<li class="second_li3">
						<a href="#">&nbsp;&nbsp;&nbsp;公司公告</a>
					</li>
					<li class="second_li3">
						<a href="#">&nbsp;&nbsp;&nbsp;工作会议</a>
					</li>
					<li class=" first_li"><a href="/Haha/login.jsp">退出 📴</a></li>			
				</ul>
			</div>
			<!-- menu 结束 -->
			<div class="alert alert-info information" style="padding: 10px">
				<strong>当前位置：</strong>
				个人信息/上传头像
			</div>
			<!-- update start -->
			<div id="pic_update_div">
				<form method="post" action="/Haha/picupdate.jhtml" enctype="multipart/form-data">
					<div class="form-group picupdate_div">
				   		<span class="label label-primary" style="position: relative;left: -30px;font-size: 150%;">上传头像</span>
				   		<div class="col-sm-10" style="margin-top:25px">
				      	<input id="myFile" type="file" name="myFile" class="fileloading">
				   		</div>
				  		<input type="hidden" name="user.logo" id="logo">
				  	</div>
				</form>					
			</div>
			<!-- update end -->		
		</div>
	</div>
</body>
<script type="text/javascript">
$("#myFile").fileinput({
    language : 'zh',
    uploadUrl : "",
    autoReplace : true,
    maxFileCount : 1,
    allowedFileExtensions : [ "jpg", "png", "gif" ],
    browseClass : "btn btn-primary", //按钮样式 
    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
}).on("fileuploaded", function(e, data) {
    var res = data.response;
    alert(res.success);
    $("#logo").attr("value", res.success);
})
</script>
</html>
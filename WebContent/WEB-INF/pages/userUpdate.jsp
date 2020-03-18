<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业二 企业信息管理系统</title>
<link type="text/css" rel="stylesheet" href="css/personUpdate.css" />
<script type="text/javascript" src="js/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/pages/welcome/welcome.js"></script>
<!--引入bootstrap -->
<link rel="stylesheet" href="js/bootstrap-3.3.7-dist/css/bootstrap.css">
<script type="text/javascript" src="js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body>

	<div class="backgrounds" id="backgrounds">
		<div class="title">
			<span class="title-span">企业管理系统</span>
			<div class="user">Hello!当前用户${User.username}</div>	
		</div>
		<div class="main">
			<!-- menu start -->
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
			<!-- menu end -->
			<div  class="alert alert-info " id="information">
				<strong>当前位置：</strong>
				个人管理/信息修改 
			</div>
			<div id="person_manage">
				<!-- update start -->
				<form id="person_edit_form" name="person_form" method="post" action="${pageContext.request.contextPath }/<%=response.encodeURL("userupdate.jhtml")%>">
					<span class="label label-primary" style="position: relative;left: -120px;font-size: 150%;">信息修改</span>
					<div class="peoson_div">
						<ul>
							<li><span class="label label-info person_span">用户名称：</span>
								<input id="tid" class="input_text" name="name" type="text" class="text"
								value="${User.username }" /><br /></li>
						</ul>
						<ul>
							<li><span class="label label-info person_span">用户密码：</span>
								<input class="input_text" type="password" name="password" id="pwd" class="text"
								value="${User.password }" /></li>
						</ul>
						<ul>
							<li><span class="label label-info person_span">确认密码：</span>
								<input class="input_text" id="confirm_pwd" name="confirm_password" type="password" class="text"
								value="${User.password}" /></li>
						</ul>
						<ul>
							<li><span class="label label-info person_span">电话号码：</span>
								<input class="input_text" id="phone_text" type="text" name="phone" value="${User.phone }"/></li>
						</ul>
						<ul>
							<li><span class="label label-info person_span">邮箱地址：</span>
								<input class="input_text" id="email_text" type="text" name="email" value="${User.email }"/></li>
						</ul>
						<ul>
							<li><span class="label label-info person_span">邮编号码：</span>
								<input class="input_text" id="youbian_text" type="text" name="youbian" value="${User.youbian }"/></li>
						</ul>
						<ul>
							<li><input class="btn btn-warning" type="submit" value="确认修改" />	
								<input id="reset_btn" class="btn btn-danger" type="button" value="重置" style="margin-left: 30px;"/>	</li>
						</ul>
						<div>	
						</div>
					</div>
				</form>			
				<!-- update end -->						
			</div>

		</div>
	</div>

</body>
<script>
$('#reset_btn').click(function(){
	$("#tid").val("");
	$("#pwd").val("");
	$("#confirm_pwd").val("");
	$("#phone_text").val("");
	$("#email_text").val("");
	$("#youbian_text").val("");
});
</script>
</html>
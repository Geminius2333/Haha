<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业二 企业信息管理系统</title>
<link type="text/css" rel="stylesheet" href="css/welcome.css" />
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
				通讯管理/短消息管理/新建短消息
			</div>
			<%@page import="com.haha.dao.jdbc.ContactsDaoImpl"%>
			<%@page import="com.haha.entity.Contact" %>
			<%@page import="com.haha.entity.User" %>
			<%
				int id = Integer.parseInt(request.getParameter("id"));
				User user = (User)request.getSession().getAttribute("User");
				int user_id = user.getId();
				ContactsDaoImpl contactDao = new ContactsDaoImpl();
				Contact contact = new Contact();
				if(id>0)
				 	contact = contactDao.getContactById(id, user_id);	
				
				List<Contact> datalist = new ArrayList();
				datalist = contactDao.getContactListWithoutId(id, user_id);%>
			<div id="person_manage">
				<!-- update start -->
				<form method="post" action="/Haha/newmessage.jhtml" enctype="multipart/form-data">
					<div class="peoson_div">
						<ul>
							<li><span class="label label-info person_span">收件人员：</span>
								<select name="reciever"> 
									<c:if  test="${id!='0'}"> <option><%=contact.getName()%></option></c:if> 

										<c:forEach items="${datalist}" var="data">
										<option>${data.name}</option>
									</c:forEach>
								</select>
						</ul>
						<ul>
							<li><span class="label label-info person_span">消息内容：</span>
								<textarea name="content"  rows="5" columns="3"></textarea></li>
						</ul>
						<ul>
							<li>				
							<div class="form-group"id="new_message_div">
						      	<input id="myFile" type="file" name="myFile">
						  		<input type="hidden" name="user.logo" id="logo">
						  	</div></li>
						</ul>
						<ul>
							<li><input class="btn btn-warning" type="submit" value="发送" />	</li>
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
<script type="text/javascript">
$("#myFile").fileinput({
    language : 'zh',
    uploadUrl : "",
    autoReplace : true,
    maxFileCount : 1,
    allowedFileExtensions : [  ],
    browseClass : "btn btn-primary", //按钮样式 
    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
}).on("fileuploaded", function(e, data) {
    var res = data.response;
    alert(res.success);
    $("#logo").attr("value", res.success);
})
</script>
</html>
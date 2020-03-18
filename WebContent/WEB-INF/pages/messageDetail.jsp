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
			<!-- 菜单 -->
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
			<!-- 菜单结束 -->
			<div  class="alert alert-info " id="information">
				<strong>当前位置：</strong>
				通讯管理/消息管理/消息详细
			</div>	
			<%@page import="com.haha.dao.jdbc.MessageDaoImpl"%>
			<%@page import="com.haha.entity.Message" %>
			<%@page import="com.haha.entity.User" %>
			<%
				int id = Integer.parseInt(request.getParameter("id"));
				User user = (User)request.getSession().getAttribute("User");
				int user_id = user.getId();
				MessageDaoImpl msgDaoImpl = new MessageDaoImpl();
				Message msg = msgDaoImpl.getMessageById(id);
				msgDaoImpl.updateIsread(id);
				String checked = "Checked=\"checked\"";				
			%>
			<!-- 个人信息管理 -->
			<div id="person_manage">
				<div class="personDetail_div">
				<span class="label label-primary" style="position: relative;left: -125px;font-size: 150%;">消息详细</span>
					<table class="table" id="personDetail" style="width:50%">
							<tr>
								<td>发信人：</td>
								<td>
									<%=msg.getSender() %>
								</td>
							</tr>	
							<tr>
								<td>收件人：</td>
								<td><%=msg.getReciever() %></td>
							</tr>
							<tr>
								<td>附件：</td>
								<%String href= "localhost:8080/Haha/upfile/"+msg.getUpfile(); %>
								<td><a href="<%=href%>"><%=msg.getUpfile() %></a></td>
							</tr>					
							<tr>
								<td>内容：</td>
								<td><%=msg.getContent()%></td>
							</tr>	
					</table>		
				</div>
			</div>
			<!-- 个人信息结束 -->					
		</div>
	</div>
</body>
</html>


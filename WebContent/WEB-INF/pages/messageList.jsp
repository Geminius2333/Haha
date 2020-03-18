<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.haha.entity.Message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业二 企业信息管理系统</title>
<link type="text/css" rel="stylesheet" href="css/welcome.css" />
<script type="text/javascript" src="js/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/pages/welcome/welcome.js"></script>
<!--引入bootstrap -->
<link rel="stylesheet" href="css/bootstrap/bootstrap.css">
<script type="text/javascript" src="js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body>

	<div class="backgrounds" id="backgrounds">
		<div class="title">
			<span class="title-span">企业管理系统</span>
			<div class="user">Hello!当前用户${User.username}</div>	
		</div>
		<div class="main">
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
			<div  class="alert alert-info " id="information">
				<span>当前位置：通讯管理/短消息管理 </span>
			</div>
			<div class="addMessage"><a id="addMessage" href="pageforward.jhtml?page=newMessage.jsp?id=0" class="label label-default">新建短消息</a></div>
			<!-- table 开始 -->
			<div class="table_div">
				<table class="table table-striped" style="width:80%">
					<tr>
						<th>接收人</th>
						<th class="message_content">内容</th>
						<th>发送者</th>
						<th>是否已读</th>
						<th>操作</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${messages}" var="message">
						<tr>
							<td>
								${message.reciever}
							</td>
							<td class="message_content">
								${message.content}
							</td>
							<td>
								${message.sender}
							</td>
							<td> 
								<c:if  test="${message.isread eq '1'}"> 已读 </c:if> 
								<c:if  test="${message.isread eq '0'}"> 未读 </c:if>
							</td>
							<td>
								<a href="pageforward.jhtml?page=messageDetail.jsp?id=${message.id}" class="label label-info">详细</a>
							</td>
							<td>
								<a href="messagedelete.jhtml?id=${message.id }" class="label label-danger">删除</a>
							</td>				
						</tr>
					</c:forEach>			
				</table>		
			</div>
			<!-- table结束 -->
			<!-- footer开始 -->
			<div class="page_footer">
				<a id="firstPage" href="messagelist.jhtml?pageNow=1">首页</a>
				<c:choose>
					<c:when test="${pageNow>1}">
						<a class="previousPage"
							href="messagelist.jhtml?pageNow=${pageNow-1}">上一页</a>
					</c:when>
					<c:otherwise>
						<a class="previousPage" href="">上一页</a>
					</c:otherwise>
				</c:choose>

				<span class="">当前第${pageNow}页</span> <span class="">/</span> <span
					class="">总共${pageCount}页</span>

				<c:choose>
					<c:when test="${pageNow<pageCount}">
						<a class="nextPage" href="messagelist.jhtml?pageNow=${pageNow+1}">下一页</a>
					</c:when>
					<c:otherwise>
						<a class="nextPage" href="">下一页</a>
					</c:otherwise>
				</c:choose>
				<a id="lastPage" href="messagelist.jhtml?pageNow=${pageCount}">尾页</a>
			</div>
			<!-- footer结束 -->
		</div>
	
	</div>

</body>
</html>
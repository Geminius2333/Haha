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
				通讯管理/通讯录管理
			</div>
			<!-- 通讯录管理 -->
			<div id="person_manage">
				<div style="margin-top:5px">
				<form action="contactsearch.jhtml" class="form-search">
					<strong>请输入用户名：</strong><input name="name" type="text" style="outline: none;border: 1px solid #cccccc; border-radius: 15px;"/>&nbsp;
					<input class="btn btn-sm btn-success" type="submit" value="查找"/>
				</form>
				</div>
				<div class="addPerson"><a class="label label-default" id="addPerson" href="pageforward.jhtml?page=contactAdd.jsp">新建联系人</a></div>
				<div class="table_div">
					<table class="table table-striped" style="width:80%">
						<tr>					
							<th>人员姓名</th>
							<th>性别</th>
							<th>联系邮箱</th>
							<th>联系电话</th>
							<th>邮编地址</th>
							<th>工作单位</th>
							<th>地址</th>
							<th>消息</th>
							<th>操作</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${persons}" var="person">
							<tr>
								<td>
									${person.name}
								</td>
								<td>														
									<c:if  test="${person.sex eq '1'}"> 男 </c:if> 
									<c:if  test="${person.sex eq '0'}"> 女</c:if>
								</td>
								<td>
									${person.email}
								</td>
								<td>
									${person.phone}
								</td>
								<td>
									${person.youbian}
								</td>
								<td>
									${person.work_location }
								</td>
								<td>
									${person.address }
								</td>
								<td>
									<a href="pageforward.jhtml?page=newMessage.jsp?id=${person.id}" class="label label-primary">发消息</a>
								</td>
								<td>
									<a href="pageforward.jhtml?page=contactUpdate.jsp?id=${person.id}" class="label label-warning">编辑</a>
								</td>		
								<td>
									<a href="contactdelete.jhtml?id=${person.id}" class="label label-danger">删除</a>
								</td>		
							</tr>
						</c:forEach>			
					</table>		
				</div>
				<div class="page_footer">
					<a id="firstPage"href="contactslist.jhtml?pageNow=1">首页</a>
					<c:choose>
						<c:when test="${pageNow>1}">
							<a class="previousPage" href="contactslist.jhtml?pageNow=${pageNow-1}">上一页</a>
						</c:when>
						<c:otherwise>
							<a class="previousPage" href="">上一页</a>
						</c:otherwise>
					</c:choose>						
					<span class="">当前第${pageNow}页</span>	
					<span class="">/</span>			
					<span class="">总共${pageCount}页</span>						
					<c:choose>
						<c:when test="${pageNow<pageCount}">
							<a class="nextPage" href="contactslist.jhtml?pageNow=${pageNow+1}">下一页</a>
						</c:when>
						<c:otherwise>
							<a class="nextPage" href="">下一页</a>
						</c:otherwise>
					</c:choose>												
					<a id="lastPage" href="contactslist.jhtml?pageNow=${pageCount}">尾页</a>
				</div>
			</div>
			<!-- 通讯录管理结束 -->
		</div>		

	</div>

</body>
</html>


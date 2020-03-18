<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>哈哈_登录</title>
<link type="text/css" rel="stylesheet" href="css/login.css" />

<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script type="text/javascript" src="js/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/pages/login/login.js"></script>

</head>
<body>
	<!-- 背景  开始 -->
	<div class="backgrounds" id="backgrounds">
		<!-- 表单框 -->
		<div class="content" id="div_position">

			<div class="login_framework">
			
				<form id="log_reg_form" method="post" name="loginForm" action="${pageContext.request.contextPath }/<%=response.encodeURL("login.jhtml")%>">
				
					<div class="content2">
						<div id="contents" class="contents">${err_msg}</div>
						<ul>
							<li><input id="tid" name="name" type="text" class="text"
								value="您的用户名" /><br /></li>
						</ul>
						<ul id="pwd_p">
							<li><input type="password" name="password" id="pwd" class="text"
								value="您的密码" /></li>
						</ul>
						<!-- 验证码获取的时候，点击刷新，需要不同的请求才会刷新，因此我们给一个时间参数，使请求每一时刻都不同，达到刷新验证码的目的 -->
						<ul id="identfy_all">
							<li>
								<span class="identifying">
							
									<img src="${pageContext.request.contextPath }/<%=response.encodeURL("code.jhtml")%>" width="120px" height="40px"
									onclick="javascript:this.src='${pageContext.request.contextPath }/<%=response.encodeURL("code.jhtml")%>?id='+new Date().getMilliseconds()">
									
								</span>
								<input type="text" name="code" id="ident" class="text identify" value="验证码" />
							</li>
						</ul>
						<span class="rem_password"> <a href="#" class="texts">忘记密码?</a>
						</span> <input class="login_button" id="button" value="登  录"
							type="button"/>
					</div>
				</form>	
			</div>
			<div class="regist" id="regesits">
				还没有帐号?<span> <a href="/Haha/register.jsp" class="texts cursors">马上注册</a></span>
			</div>
		</div>
	</div>
	<!-- 背景  结束 -->
</body>
</html>
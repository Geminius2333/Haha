<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>哈哈_注册</title>

<script type="text/javascript" src="js/jquery/jquery-1.11.0.min.js"></script>

<link type="text/css" rel="stylesheet" href="css/register.css" />
<script type="text/javascript" src="js/pages/register/register.js"></script>
<!--引入bootstrap -->
<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css"/>
<script src="js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>


</head>
<body>
	<!-- 背景  开始 -->
	<div class="backgrounds" id="backgrounds">
		<!-- 表单框 -->
		<div class="content" id="div_position">

			<div class="register_framework">
			
				<form id="log_reg_form"method="post" name="registerForm" action="${pageContext.request.contextPath }/<%=response.encodeURL("register.jhtml")%>">
				
					<div class="content2">
						<div id="contents" class="contents">${err_msg}</div>
						<ul>
							<li><input id="tid" name="name" type="text" class="text"
								value="您的用户名*" /><br /></li>
						</ul>
						<ul id="pwd_p">
							<li><input type="text" name="password" id="pwd" class="text"
								value="您的密码*" /></li>
						</ul>
						<ul id="identfy_all">
							<li><input id="confirm_pwd" name="confirm_password" type="text" class="text"
								value="确认密码*" /></li>
						</ul>
						<ul id="identfy_all">
							<li><input id="email" name="email" type="text" class="text"
								value="您的邮箱" /></li>
						</ul>
						<ul id="identfy_all">
							<li><input id="phone" name="phone" type="text" class="text"
								value="您的电话" /></li>
						</ul>
						<ul id="identfy_all">
							<li><input id="youbian" name="youbian" type="text" class="text"
								value="您的邮编" /></li>
						</ul>	
						<ul>
								<div class="form-group">
							        <label class="col-sm-1 control-label" style="color:white">省</label>
							        <div class="form-group col-sm-2">
							            <select id="provinces" name="province" class="form-control ">
							            </select>
							        </div>
							    </div>
						</ul>
						<ul class="cities">
							    <div class="form-group cities">
							        <label class="col-sm-1 control-label" style="color:white">市</label>
							        <div class="form-group col-sm-2">
							            <select id="cities" class="form-control " name="city">
							            </select>
							        </div>
							    </div>
						</ul>    
						<ul class="areas">
							    <div class="form-group areas">
							        <label class="col-sm-1 control-label" style="color:white">县</label>
							        <div class="form-group col-sm-2">
							            <select id="areas" class="form-control " name="area">
							            </select>
							        </div>
							    </div>

						</ul>

						<span class="rem_password"> <a href="/Haha/login.jsp" class="texts">登录系统</a></span> 
						<input class="register_button" id="button" value="注册"
							type="button"/>
					</div>
				</form>					
			</div>
		</div>
	</div>
	<!-- 背景  结束 -->
</body>
<script>
$(document).ready(function(){
    var provinces;   //省的数据
    var cities; //市
    var areas; //县
    $('.cities').hide();
    $('.areas').hide();
    //先自动获取省的数据
    $.ajax({
        url:'AddressSelectAction?type=provinces',       
        success:function(result){
            var obj = JSON.parse(result);          
            provinces = obj;   
              for(var key in obj){
                $('#provinces').append("<option>"+key+"</option>")
             }
        }
    })
    //根据选择的省获取市
    $('#provinces').change(function(){
    	$("#cities option").remove();
    	$("#areas option").remove();
        var v = $(this).find("option:selected").text();       
        $.ajax({      	
            url:'AddressSelectAction?type=cities&id='+provinces[v],
            success:function(result){
                var obj = JSON.parse(result);
                cities = obj;
                $('.cities').show();
                  for(var key in obj){
                    $('#cities').append("<option>"+key+"</option>")
                 }
            }
    })
    })
    //根据选择的市获取县
    $('#cities').change(function(){
    	$("#areas option").remove();
        var v = $(this).find("option:selected").text();        
        $.ajax({       	
            url:'AddressSelectAction?type=areas&id='+cities[v],
            success:function(result){
                var obj = JSON.parse(result);
                areas = obj;
                $('.areas').show();
                
                  for(var key in obj){
                    $('#areas').append("<option>"+key+"</option>")
                 }
        }
    })
    })

});
</script>
</html>
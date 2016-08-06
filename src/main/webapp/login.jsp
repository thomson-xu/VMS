<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String rand = String.valueOf(Math.random());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	    <base href="<%=basePath%>">
	    <title>${sysConfig.webapp.displayName}</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<link rel="stylesheet" href="css/login.css" type="text/css"></link>
		<script type="text/javascript" src="js/login.js"></script>
	</head>
<body>
	<!-- SystemConfigLoad:${applicationScope.SystemConfigLoad} -->
	<div class="x-login-panel">
		<div class="x-login-center">
			<div class="x-login-left">
				<div class="x-login-top">${sysConfig.webapp.displayName}</div>
				<img src="images/login-img02.png" class="x-login-left-img" />
				<div id="message" class="x-login-message"></div>
			</div>
			<div class="x-login-logform">
				<div class="x-login-form-title">用户登陆</div>
				<div class="x-login-form">
					<form action="login.do"  method="post" id="loginForm" >
						<div class="x-list">
							<label class="x-label">用户名：</label>
							<input class="x-input" type="text" class="x-form-input" id="VUsername" name="j_username" autofocus/>
						</div>
						<div class="x-list">
							<label class="x-label">密&nbsp;&nbsp;码：</label>
							<input class="x-input" type="password" class="x-form-input" id="VPassword" name="j_password"/>
						</div>
						<div class="x-list">
							<label class="x-label">验证码：</label>
							<input class="x-input" type="text" style="width: 90px;" class="x-form-input" id="code" name="j_captcha" maxlength="4"/>
							<img align="top" id="imgCode" title="刷新验证码" alt="刷新验证码" class="x-form-imgCode" src="getCode?id=<%=rand%>" onclick="refreshImgCode()" />
						</div>
						<div class="x-list">
	                        <input type="checkbox" name="_spring_security_remember_me" id="_spring_security_remember_me" 
	                        	class="stuts-checkbox" checked="checked"/>免登录
	                        <a href="javascript:void(0)" style="margin-left: 50px;">忘记密码？</a>
                		</div>
                		<div class="x-login-errormessage" id="errorMessage">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</div>
                		<div class="x-login-form-btn">
                			<a href="javascript:void(0)" class="x-btn x-btn-left" id="btn_submit" onclick="doLogin()">登陆</a>
                			<a href="javascript:void(0)" class="x-btn x-btn-right" id="btn_reset" onclick="doReset()">重置</a>
                		</div>
					</form>
				</div><!--End login form -->
			</div><!--End login logform  -->
		</div><!--End login center  -->
	</div><!--End login panel  -->
	
	<div class="login-bottom">
	 	<div class="login-bottom-split"></div>
	 	<div class="login-bottom-info">
	 		<p>
	 			${sysConfig.copyright} <a href="${sysConfig.developers.url}">${sysConfig.developers.name}</a>  版权所有
	 		</p>
	 		<p>
				<span class="login-bottom-model">售后服务QQ：
					<c:forEach var="qq" items="${sysConfig.postSales}">
						${qq}
					</c:forEach>
				</span>
				<span class="login-bottom-model">QQ技术支持：
					<c:forEach var="qq" items="${sysConfig.technicalSupport}">
						${qq}
					</c:forEach>
				</span>
				<span class="login-bottom-model"></span>
			</p>
			<p>
				<span class="login-bottom-model">
					客服电话：
					<c:forEach var="dh" items="${sysConfig.serviceHotline}">
						${dh}
					</c:forEach>
				</span>
				<span class="login-bottom-model">公司邮箱：${sysConfig.email}</span>
			</p>
			
	 	</div>
	</div>
	
</body>
</html>

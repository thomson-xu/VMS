<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
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

		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="cache-control" content="no-cache">
		<link href="<c:url value='/resources/css/bootstrap.css' />"  rel="stylesheet"/>
		<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"/>
		<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
	</head>
	<!-- SystemConfigLoad:${applicationScope.SystemConfigLoad} -->
	<body>
	<div id="mainWrapper">
		<div class="login-container">

			<div class="login-card">

				<div class="label label-info">${sysConfig.webapp.displayName}Login Page</div>
				<div class="login-form form-horizontal">
					<h:form id="loginForm" prependId="false">
						<c:if test="${param.error != null}">
							<div class="alert alert-danger">
								<p>Invalid username and password.</p>
							</div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert alert-success">
								<p>You have been logged out successfully.</p>
							</div>
						</c:if>
						<div class="input-group input-sm">
							<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
							<input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
						</div>
						<div class="input-group input-sm">
							<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
							<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
						</div>

						<div class="" id="errorMessage">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</div>

						<div class="input-group input-sm">
							<div class="checkbox">
								<label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							   value="${_csrf.token}" />

						<div class="form-actions">
							<h:commandButton type="submit" id="login" action="#{loginBean.doLogin}" value="登录" styleClass="btn btn-block btn-primary btn-default" />
						</div>
					</h:form>
				</div>

			</div>
		</div>
	</div>

</body>
</html>

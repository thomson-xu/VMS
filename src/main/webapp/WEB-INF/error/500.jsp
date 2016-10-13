<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '404.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  	<link rel="stylesheet" href="css/error.css" type="text/css"></link>
  </head>
  
  <body>
	<div class="x-border x-404-panel ">
		<div class="x-404-header">管理系统</div>
		<div class="x-404-center">
			<div class="x-404-top">
				<img src="images/pic_dot_jz.gif" border="0" class="x-500-top_img"/>
				<div class="x-500-error">Internal Server Error</div>
				<div class="x-500-info">内部服务器错误，您查找的资源存在问题，无法正常显示！</div>
			</div>
			<div class="x-404-bottom">
				<a href="javascript:history.back()">◂返回上一页</a>
				<a href="#" style="margin-left: 20px;">◂返回首页</a>
				<a href="login" style="margin-left: 20px;">◂返回登陆页面</a>
			</div>
		</div>
	</div>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String serverDate = (new java.sql.Date(System.currentTimeMillis())).toString();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>${sysConfig.webapp.displayName}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<div id="loadmsg">loading......</div>
  	<link rel="stylesheet" href="css/loading.css" type="text/css"></link>
  </head>
<body>
	<!--
		应用程序主入口
		
		---------------------华丽的分割线------------------
		
		动态加载项目所需主要Js文件及Css文件，在加载成功之后须删除loadingWindow，
		否则会导致项目在resize时不正常
	-->
	<div class="x-me-loading" id="loadingWindow">
 		<div class="x-me-loading-header">系统正在初始化</div>
 		<div class="x-me-center-info" id="xLoadInfo">
 			正在加载系统图标文件....
 		</div>
 		<div class="x-me-progress">
 			<div class="x-me-progress-body" id="progressBody"></div>
 		</div>
 	</div>
</body>
<script type="text/javascript" src="js/loading.min.js"></script>
</html>
<script type="text/javascript">
	var Ext = Ext ||{};
	Ext.CONTEXT = "<%=basePath%>"
	Ext.SERVER_DATE = "<%=serverDate%>"
	Ext.WEB_APP_NAME="${sysConfig.webapp.displayName}"
</script>

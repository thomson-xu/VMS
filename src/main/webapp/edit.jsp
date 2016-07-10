<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
 		<link href="style.css" rel="stylesheet" type="text/css"/>
 		<title>编辑员工信息</title>
	</head>
<f:view>
<body>
	<h2>JSF1.2+EJB3的一个CRUD实例</h2>
	<h3>--编辑员工信息</h3>
	<h:messages errorClass="errorMsg" />
	<h:form id="edit">
	<input type="hidden" name="emplId" value="${emplBean.employee.id}"/>
	<table>
	  <tr>
	    <td>姓名:</td>
	    <td>
	      <h:inputText id="name" value="#{emplBean.employee.name}" required="true">
	        <f:validateLength minimum="2"/>
	      </h:inputText>
	    </td>
	  </tr>
	
	  <tr>
	    <td>年龄:</td>
	    <td>
	      <h:inputText id="age" value="#{emplBean.employee.age}">
	      	<f:validateLongRange minimum="18" maximum="60" />
	      </h:inputText>
	    </td>
	  </tr>
	  <tr>
	    <td>地址:</td>
	    <td>
	      <h:inputTextarea id="address" value="#{emplBean.employee.address}" cols="35">
	      	<f:validateLength minimum="2" maximum="128"/>
	      </h:inputTextarea>
	    </td>
	  </tr>
	
	</table>
	<h:commandButton type="submit" id="update" value="更新"
                 action="#{emplBean.updateAction}"/>
    <h:outputText value=" "/>
    <h:commandButton type="submit" id="delete" value="删除"
    			action="#{emplBean.deleteAction}"
    			onclick="if(!confirm('你确定删除?')){return false;}"/>
	</h:form>
</body>
</f:view>
</html>

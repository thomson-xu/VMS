<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="style.css" rel="stylesheet" type="text/css" />
		<title>所有员工列表</title>
	</head>
	<f:view>
		<body>
			<h2>JSF1.2+EJB3的一个CRUD实例</h2>
				<h3>--所有员工列表</h3>
			<h:form>
				<h:commandButton action="create" value="新增一个员工" type="submit" />
				
				<h:dataTable value="#{emplBean.allEmployees}" var="empl" 
						styleClass="dt"
						headerClass="dt-head"
						rowClasses="dt-r1,dt-r2"
						cellpadding="4"
						border="1"
						binding="#{emplBean.table}">
					<h:column>
						<f:facet name="header">
							<h:outputText value="序号" />
						</f:facet>   
     					<h:outputText value="#{emplBean.table.rowIndex + 1}"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="姓名" />
						</f:facet>  
						<h:outputText value="#{empl.name}"/>
  					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="年龄" />
						</f:facet> 
						<h:outputText value="#{empl.age}"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="住址" />
						</f:facet>
						<h:outputText value="#{empl.address}"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="录入时间" />
						</f:facet>
						<h:outputText value="#{empl.registerTime}">
							<f:convertDateTime pattern="yyyy-MM-dd EEE" timeZone="GMT+8"/>
						</h:outputText>
					</h:column>
					
					<h:column>
						<h:panelGroup>
							<h:outputLink value="edit.faces?emplId=#{empl.id}">
								<h:outputText value="编辑"/>
							</h:outputLink>
							<h:outputText value=" "/>
							<h:commandLink action="#{emplBean.deleteAction}"
								onclick="if(!confirm('你确定删除?')){return false;}">
								<h:outputText value="删除"/>
								<f:param name="emplId" value="#{empl.id}"/>
							</h:commandLink>
						</h:panelGroup>
					</h:column>
				</h:dataTable>
			</h:form>
		</body>
	</f:view>
</html>
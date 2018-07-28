<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<jsp:include page="inc.jsp"></jsp:include>

<title>芜湖市环保局机动车尾气检测管理系统----登录</title>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="style/login.css" type="text/css" rel="stylesheet">
</HEAD>
<body>
	
<%
	session.setAttribute("userRole", "superAdmin");
	
%>
<jsp:forward page="main.jsp"/>
</body>
</HTML>


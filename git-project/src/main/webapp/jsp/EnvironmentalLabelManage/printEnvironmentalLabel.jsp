<%@page import="com.dbsoft.whjd.pageModel.EnvironmentalLabelPage"%>
<%@page import="com.dbsoft.whjd.action.EnvironmentalLabelAction"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../../inc.jsp"></jsp:include>
<title>打印环保标志</title>
</head>
<body>
	<%
		EnvironmentalLabelPage labelPage = (EnvironmentalLabelPage) request
				.getSession().getAttribute("labelPage");
		String emissionStandard = "国";
		if (labelPage.getEmissionStandard().equals("1"))
			emissionStandard += "Ⅰ";
		if (labelPage.getEmissionStandard().equals("2"))
			emissionStandard += "Ⅱ";
		if (labelPage.getEmissionStandard().equals("3"))
			emissionStandard += "Ⅲ";
		if (labelPage.getEmissionStandard().equals("4"))
			emissionStandard += "Ⅳ";
	%>
	<div id="licenceReal">
	<div id="qrdiv" align="center">
		<img name="qr" id="QR"
			src="/whjd/environmentalLabelAction!outPutQRcode.action?labelId=<%=labelPage.getLabelId()%>&licence=<%=labelPage.getLicence()%>" />
	</div>
	<p align="center">环保检验合格标志（正本）</p>
	<table width="400" border="0" align="center" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td></td>
		</tr>
		<tr>
			<td align="center">标志编号：</td>
			<td align="center"><%=labelPage.getLabelId()%></td>
		</tr>
		<tr>
			<td align="center">车牌照号：</td>
			<td align="center"><%=labelPage.getLicence()%></td>
		</tr>
		<tr>
			<td align="center">排放标准：</td>
			<td align="center"><%=emissionStandard%></td>
		</tr>
		<tr>
			<td align="center">有效期至：</td>
			<td align="center"><%=labelPage.getValidPeriod()%></td>
		</tr>
		<tr>
			<td align="center">核发日期：</td>
			<td align="center"><%=labelPage.getIssueDate()%></td>
		</tr>
	</table>
	</div>
	<p align="center">环保检验合格标志（副本）</p>
	<table width="400" border="0" align="center" cellspacing="0">
		<tr>
			<td align="center">标志编号：</td>
			<td align="center"><%=labelPage.getLabelId()%></td>
		</tr>
		<tr>
			<td align="center">车辆牌照号：</td>
			<td align="center"><%=labelPage.getLicence()%></td>
		</tr>
		<tr>
			<td align="center">车辆登记日期：</td>
			<td align="center"><%=labelPage.getVehicleRegisterTime()%></td>
		</tr>
		<tr>
			<td align="center">车辆燃油种类：</td>
			<td align="center"><%=labelPage.getFuelType()%></td>
		</tr>
		<tr>
			<td align="center">车辆排放标准：</td>
			<td align="center"><%=emissionStandard%></td>
		</tr>
		<tr>
			<td align="center">有效期至：</td>
			<td align="center"><%=labelPage.getValidPeriod()%></td>
		</tr>
		<tr>
			<td align="center">核发日期：</td>
			<td align="center"><%=labelPage.getIssueDate()%></td>
		</tr>
	</table>
</body>
</html>
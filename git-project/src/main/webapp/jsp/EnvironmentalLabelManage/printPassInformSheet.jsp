<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dbsoft.whjd.pageModel.EnvironmentalLabelPage"%>
<%@page import="com.dbsoft.whjd.action.EnvironmentalLabelAction"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../../inc.jsp"></jsp:include>
<title>打印检验合格通知单</title>

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
		if (labelPage.getEmissionStandard().equals("5"))
			emissionStandard += "Ⅴ";
		if (labelPage.getEmissionStandard().equals("6"))
			emissionStandard += "Ⅵ";
		
		Date date1=new Date();	
	%>
	<div id="licenceReal">
	
	<p align="center">芜湖市在用车辆环保检验合格通知单</p>
	<table width="750"  align="center" cellspacing="0"   >
		<tr>
			<td width="168"><div align="left"></div></td>
			<td colspan="3"><div align="left"></div></td>
			<td width="57"><div align="left">编号：</div></td>
			<td width="190"><%=labelPage.getLabelId()%></td>
		</tr>
</table>
<table width="750" border="1" align="center" cellspacing="0"   >
		<tr   >
			<td width="125" ><div align="left">车辆号码：</div></td>
			<td width="125" ><%=labelPage.getLicence()%></td>
			<td width="125" ><div align="left">车牌颜色：</div></td>
			<td width="125" ><%=labelPage.getLicenseColor()%></td>
			<td width="125" ><div align="left">燃料种类：</div></td>
			<td width="125" ><%=labelPage.getFuelType()%></td>
		</tr>
		<tr>
			<td align="center"><div align="left">车辆识别代码：</div></td>
			<td colspan="5" align="center"><%=labelPage.getVinNo()%></div></td>
		</tr>
		<tr>
			<td ><div align="left">车辆注册日期：</div></td>
			<td ><%=labelPage.getVehicleRegisterTime()%></td>
		  <td ><div align="left">排放标准：</div></td>
			<td ><%=emissionStandard%></td>
		  <td ><div align="left">检验有效期至：</div></td>
			<td align="left"><%=labelPage.getValidPeriod()%></td>
		</tr>
		<tr>
			<td height="242" align="center">检验结论：</td>
			<td colspan="5" align="center"><div align="left">  
			    <p>&nbsp;&nbsp;根据检验单位提供的检验数据，对照国家和安徽省相关机动车排放标准，该机动车排气浓度符合排放限值要求，检验结论：合格。</p>
			  <div id="qrdiv" align="left" leftmargin="50"  style="position: relative; ">
				  <img name="qr" id="QR"
				  src="/whjd/newCarAdmitAction!outPutQRcode.action?licence=<%=labelPage.getLicence()%>&licenseColor=<%=labelPage.getLicenseColor()%>&emissionStandard=<%=labelPage.getEmissionStandard()%>&vin=<%=labelPage.getVinNo()%>" 
				  style="padding-left: 30px;"/>
				  
				 <img align="right" alt="芜湖市环保局" src="/whjd/images/zhang.jpg" height = "150" width = "150"  style="padding-right: 30px;bottom:10px" >
                 <span style="position: absolute; top: 120px; right:5px;font-size:15px"><%=(new SimpleDateFormat("yyyy")).format(date1) %>年<%=(new SimpleDateFormat("MM")).format(date1) %>月<%=(new SimpleDateFormat("dd")).format(date1) %>日</span> 
			  </div>
			</div></td>
		</tr>
</table>
<table width="750" border="0" align="center" cellspacing="0"   >
		<tr border = "0">
			<td width="90" align="center">检验单位：</td>
			<td colspan="3" align="left"><%=labelPage.getStationName()%></td>
			<td width="155" align="center">填表人：</td>
			<td width="204" align="left"><%=session.getAttribute("userName")%></td>
		</tr>
	</table>
	</div>
</body>
</html>
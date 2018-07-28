<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dbsoft.whjd.pageModel.NewCarAdmitSheetPage"%>
<%@page import="com.dbsoft.whjd.action.NewCarAdmitAction"%>
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
		NewCarAdmitSheetPage newCarAdmitSheetPage = (NewCarAdmitSheetPage) request
				.getSession().getAttribute("newCarAdmitSheetPage");
		System.out.println("------------"+newCarAdmitSheetPage.getCertificateID());
		
		String emissionStandard = "国";
		if (newCarAdmitSheetPage.getEmissionStandard()==1)
			emissionStandard += "Ⅰ";
		if (newCarAdmitSheetPage.getEmissionStandard()==2)
			emissionStandard += "Ⅱ";
		if (newCarAdmitSheetPage.getEmissionStandard()==3)
			emissionStandard += "Ⅲ";
		if (newCarAdmitSheetPage.getEmissionStandard()==4)
			emissionStandard += "Ⅳ";
		if (newCarAdmitSheetPage.getEmissionStandard()==5)
			emissionStandard += "Ⅴ";
		if (newCarAdmitSheetPage.getEmissionStandard()==6)
			emissionStandard += "Ⅵ";
		
		String emissionAdmitStandard = "国";
		if (newCarAdmitSheetPage.getEmissionAdmitStandard()==1)
			emissionAdmitStandard += "Ⅰ";
		if (newCarAdmitSheetPage.getEmissionAdmitStandard()==2)
			emissionAdmitStandard += "Ⅱ";
		if (newCarAdmitSheetPage.getEmissionAdmitStandard()==3)
			emissionAdmitStandard += "Ⅲ";
		if (newCarAdmitSheetPage.getEmissionAdmitStandard()==4)
			emissionAdmitStandard += "Ⅳ";
		if (newCarAdmitSheetPage.getEmissionAdmitStandard()==5)
			emissionAdmitStandard += "Ⅴ";
		if (newCarAdmitSheetPage.getEmissionAdmitStandard()==6)
			emissionAdmitStandard += "Ⅵ";
		
		
		String change= "第";
		if (newCarAdmitSheetPage.getEmissionStandard()==1)
			change += "一";
		if (newCarAdmitSheetPage.getEmissionStandard()==2)
			change += "二";
		if (newCarAdmitSheetPage.getEmissionStandard()==3)
			change += "三";
		if (newCarAdmitSheetPage.getEmissionStandard()==4)
			change += "四";
		if (newCarAdmitSheetPage.getEmissionStandard()==5)
			change += "五";
		if (newCarAdmitSheetPage.getEmissionStandard()==6)
			change += "六";
		
		String motortype = null;
		if(newCarAdmitSheetPage.getVehicleType()==0)
			motortype = "机动车";
		else if(newCarAdmitSheetPage.getVehicleType()==1)
			motortype = "机动车";
		else if(newCarAdmitSheetPage.getVehicleType()==2)
			motortype = "摩托车";
		
		
		
		
		
		Date date1=new Date();	
		String vehicleType = null;
		if(newCarAdmitSheetPage.getVehicleType()==0)
			vehicleType = "新注册车辆";
		else if(newCarAdmitSheetPage.getVehicleType()==1)
			vehicleType = "外地转入车辆";
		else if(newCarAdmitSheetPage.getVehicleType()==2)
			vehicleType = "新购及外地转入摩托车";
	%>
	<div id="licenceReal">
	
	<p align="center">芜湖市<%= vehicleType %>环保准入证明</p>
	<table width="750"  align="center" cellspacing="0"   >
		<tr>
			<td width="168"><div align="left"></div></td>
			<td colspan="3"><div align="left"></div></td>
			<td width="57"><div align="left">编号：</div></td>
			<td width="190"><%=newCarAdmitSheetPage.getCertificateID()%></td>
		</tr>
</table>
<table width="750" border="1" align="center" cellspacing="0"   >
		<tr>
			<td align="center"><div align="left">机动车所有人：</div></td>
			<td colspan="5" align="left"><%=newCarAdmitSheetPage.getVehicleOwnerName()%></div></td>
		</tr>
		<tr>
			<td><div align="left">住址：</div></td>
			<td colspan="3" ><%=newCarAdmitSheetPage.getVehicleOwnerAddress()%></td>
			<td ><div align="left">联系电话：</div></td>
			<td ><%=newCarAdmitSheetPage.getPhoneNum()%></td>
		</tr>
		<tr>
			<td align="center"><div align="left">车辆识别代码：</div></td>
			<td colspan="2" align="left"><%=newCarAdmitSheetPage.getVin()%></div>
			<td align="center"><div align="left">车辆型号：</div></td>
			<td colspan="2" align="left"><%=newCarAdmitSheetPage.getVehicleModelCode()%></div></td></td>
		</tr>
		<tr>
			<td width="125" ><div align="left">发动机号：</div></td>
			<td width="125" ><%=newCarAdmitSheetPage.getEngineCode()%></td>
			<td width="125" ><div align="left">发动机型号：</div></td>
			<td width="125" ><%=newCarAdmitSheetPage.getEngineModel()%></td>
			<td width="125" ><div align="left">总质量(Kg)：</div></td>
			<td width="125" ><%=newCarAdmitSheetPage.getMaxTotalQuality()%></td>
		</tr>
		<tr>
			<td ><div align="left">车辆注册日期：</div></td>
			<td ><%=newCarAdmitSheetPage.getVehicleRegisterDate()%></td>
			<td ><div align="left">燃料种类：</div></td>
			<td ><%=newCarAdmitSheetPage.getFuelType()%></td>
		  <td ><div align="left">排放标准：</div></td>
			<td ><%=emissionStandard%></td>
		</tr>
		<tr>
			<td height="200" align="center">查询结果:：</td>
			<td colspan="5" align="center"><div align="left">
			    <p>&nbsp;&nbsp;经查询，该<%= motortype %>污染物排放标准达到国家<%=change%>阶段排放标准，符合<%= motortype %>环保准入相关政策规定。</p>
			  <div id="qrdiv" align="left" leftmargin="50"  style="position: relative; ">
				  <img name="qr" id="QR"
				  src="/whjd/newCarAdmitAction!outPutQRcode.action?licence=<%=newCarAdmitSheetPage.getLicence()%>&licenseColor=<%=newCarAdmitSheetPage.getLicenseColor()%>&emissionStandard=<%=newCarAdmitSheetPage.getEmissionStandard()%>&vin=<%=newCarAdmitSheetPage.getVin()%>" 
				  style="padding-left: 30px;"/>
				 <img align="right" alt="芜湖市环保局" src="/whjd/images/zhang.jpg" height = "150" width = "150"  style="padding-right: 30px;bottom:10px" >
                 <span style="position: absolute; top: 115px; right:5px;font-size:15px"><%=(new SimpleDateFormat("yyyy")).format(date1) %>年<%=(new SimpleDateFormat("MM")).format(date1) %>月<%=(new SimpleDateFormat("dd")).format(date1) %>日</span> 
			  </div>
			</div></td>
		</tr>
</table>
<table width="750" border="0" align="center" cellspacing="0"   >
		<tr border = "0">
			<td width="90" align="center">填表单位：</td>
			<td colspan="3" align="left"><%=newCarAdmitSheetPage.getFillStationName()%></td>
			<td width="155" align="center">填表人：</td>
			<td width="204" align="left"><%=newCarAdmitSheetPage.getFillUserName()%></td>
		</tr>
	</table>
	</div>
</body>
</html>
<%@ page language="java"
	import="java.util.*,com.dbsoft.whjd.pageModel.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.dbsoft.whjd.pageModel.EnvironmentalLabelPage"%>
<%@page import="com.dbsoft.whjd.action.EnvironmentalLabelAction"%>
<%@page import="com.dbsoft.whjd.pageModel.InspectionStationPage"%>
<%@page import="com.dbsoft.whjd.action.InspectionStationAction"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>自由加速法检测报告打印</title>
<script src='http://localhost:8000/CLodopfuncs.js'></script>
<!--  
<script type="text/javascript" src="../../jslib/LodopFuncs.js"></script>
-->
</head>
<body>
	<div style="width:1000px;height:800px">
		<%
			FreeAccelerationMethodPage methodPage = (FreeAccelerationMethodPage) request
					.getSession().getAttribute("methodPage");
		
		//添加环保标志用
		    EnvironmentalLabelPage labelPage = (EnvironmentalLabelPage) request
				.getSession().getAttribute("labelPage");
		//加的
		    InspectionStationPage stationPage= (InspectionStationPage) request
		    	.getSession().getAttribute("stationPage");
		
			String conclusion = "";
			String emissionProcessDevice = "";
			//String SA = "";
			//String RE = "";
			if (methodPage.getIntHasEmissionProcessDevice() != null) {
				if (methodPage.getIntHasEmissionProcessDevice() == 1)
					emissionProcessDevice = "有";
				else
					emissionProcessDevice = "无";
			}
			if (methodPage.getConclusion() != null) {
				if (methodPage.getConclusion() == 0)
					conclusion = "合格";
				else
					conclusion = "不合格";
			}
			//if (methodPage.getStationName() != null){ 
				
					//SA = methodPage.getStationAddress();
					//RE = methodPage.getRemarks();
				//}
				//else{
					//SA = "   ";
					//RE = "   ";
				//}
			
			
		%>
		
		<DIV id="div_to_print" style="LINE-HEIGHT: 20px" align=center>
			<form id="frm_to_print" style="width:700px">
		
				<div align="center">
				    <div align="left" style="position:relative" >
				        <img align="left" alt="CMA标志" src="/whjd/images/cma1.jpg"  height = "60" width = "100" />
						<span style="position:absolute;left:0;margin-top:50px;margin-left:5px;font-size:12px;color:red;">
							&nbsp;&nbsp;<%=methodPage.getInstitutionNum()%>&nbsp;号
						</span>
			        </div> 
					<h3 style="display:inline-block;margin-right:150px">在用汽车自由加速试验（不透光烟度法）检测报告</h3>
					<div align="right">
						<p>
							检测报告编号：&nbsp;&nbsp;<%=methodPage.getReportNumber()%>&nbsp;&nbsp;
						</p>
					</div>
				</div>
				<table border="0" align="center" style="width:700px">
					<tr>
						<td width="300px" align="left">检测站名称（盖章）：&nbsp;&nbsp;<%=methodPage.getStationName()%>
						</td>
						<td width="50%" align="left">检测日期：&nbsp;&nbsp;<%=methodPage.getStrDetectionTime()%>
						</td>	
					</tr>
					<tr>
						<td width="300px" align="left">检测站地址：&nbsp;&nbsp;<%=methodPage.getStationAddress()%>
						</td>
						<td width="50%" align="left">检测站联系电话：&nbsp;&nbsp;<%=methodPage.getRemarks()%>
						</td>	
					</tr>
										
					<tr>
						<td width="50%" align="left">1. 测试车辆信息</td>
						<td width="50%" align="left">&nbsp;</td>
					</tr>
					<tr>
						<td width="50%" align="left">车辆号牌：&nbsp;&nbsp;<%=methodPage.getLicence()%>&nbsp;（<%=methodPage.getLicenseColor()%>）</td>
						<td width="50%" align="left">车辆识别代码（VIN）：&nbsp;&nbsp;<%=methodPage.getVin()%></td>
					</tr>
					<tr>
						<td width="50%" align="left">登记年月：&nbsp;&nbsp;<%=methodPage.getStrVehicleRegisterDate()%></td>
						<td width="50%" align="left">车型和生产厂：&nbsp;&nbsp;<%=methodPage.getVehicleModelCode()%>&nbsp;&nbsp;<%=methodPage.getVehicleManufacturer()%></td>
					</tr>
					<tr>
						<td width="50%" align="left">基准质量：&nbsp;&nbsp;&nbsp;<%=methodPage.getBaseQuality()%>公斤</td>
						<td width="50%" align="left">最大总质量：&nbsp;&nbsp;&nbsp;<%=methodPage.getMaxTotalQuality()%>Kg</td>
					</tr>
					<tr>
						<td width="50%" align="left">发动机型号/排量：&nbsp;&nbsp;<%=methodPage.getEngineModel()%>/<%=methodPage.getEngineemissionAmount()%>升</td>
						<td width="50%" align="left">试验用燃料牌号：&nbsp;&nbsp;<%=methodPage.getFuelGrade()%></td>
					</tr>
					<tr>
						<td width="50%" align="left">进气方式：&nbsp;&nbsp;<%=methodPage.getAirInletMode()%></td>
						<td width="50%" align="left">累计行驶里程：&nbsp;&nbsp;<%=methodPage.getTotalMiles()%>公里</td>
					</tr>
					<tr>
						<td width="50%" align="left">供油系统型式：&nbsp;&nbsp;<%=methodPage.getFuelSupplySystemModel()%></td>
						<td width="50%" align="left">排气后处理装置：&nbsp;&nbsp;<%=emissionProcessDevice%></td>
					</tr>	
				</table>
				<table style="width:700px" align="center">
				    <tr>
						<td width="230" align="left">2. 车主姓名：&nbsp;&nbsp;<%=methodPage.getVehicleOwnerName()%></td>
						<td align="left">车主地址：&nbsp;&nbsp;<%=methodPage.getVehicleOwnerAddress()%></td>
						<td width="230" align="left">联系电话：&nbsp;&nbsp;<%=methodPage.getPhoneNum()%></td>
					</tr>
					<tr>
						<td width="230" align="left">3. 测试现场环境条件</td>
						<td>&nbsp;</td>
						<td width="230" align="left">&nbsp;</td>
					</tr>
					<tr>
						<td width="230" align="left">温度：&nbsp;&nbsp;&nbsp;<%=methodPage.getTemperature()%>°C</td>
						<td align="left">大气压：&nbsp;&nbsp;&nbsp;<%=methodPage.getAirPressure()%>kPa</td>
						<td width="230" align="left">相对湿度：&nbsp;&nbsp;&nbsp;<%=methodPage.getRelativeHumidity()%>%</td>
					</tr>
					<tr>
						<td width="230" align="left">4. 检测结果</td>
						<td>&nbsp;</td>
						<td width="230" align="left">&nbsp;</td>
					</tr>
				</table>
				<table border="1" align="center" cellpadding="5"
					bordercolor="#000000" cellspacing="0"
					style="border-collapse: collapse" width="700px">
					<tr align="center">
						<td rowspan="2">怠速转速/（r/min）</td>
						<td colspan="3">最后三次测量值/m<sup>-1</sup></td>
						<td rowspan="2">平均值/m<sup>-1</sup></td>
						<td rowspan="2">限值/m<sup>-1</sup></td>
						<td rowspan="2">合格判定</td>
					</tr>
					<tr>
						<td align="center">1</td>
						<td align="center">2</td>
						<td align="center">3</td>
					</tr>
					<tr align="center">
						<td><%=methodPage.getRpm()%></td>
						<td><%=methodPage.getLastOneTest()%></td>
						<td><%=methodPage.getLastTwoTest()%></td>
						<td><%=methodPage.getLastThreeTest()%></td>
						<td><%=methodPage.getAverage()%></td>
						<td><%=methodPage.getZjLimit()%></td>
						<td><%=conclusion%></td>
					</tr>
				</table>
							
				
				<!--  
				<div align="right">
						<p>
							检测依据&nbsp;&nbsp;GB3847-2005&nbsp;&nbsp;
						</p>
				</div>
				-->
				
				<table border="0" align="center" style="width:700px">
				      <tr>
						<td width="50%" align="left">检测依据&nbsp;&nbsp;GB3847-2005&nbsp;&nbsp;</td>
						<td width="50%" align="left">&nbsp;</td>
					  </tr>
				</table>	
				<br />			
				<!--  
				<table width="700" border="0" align="center">
					<tr>
						<td align="left">测试仪器型号：&nbsp;&nbsp;<%=methodPage.getTestDeviceModel()%></td>
					</tr>
					<tr>
						<td align="left">检测员：&nbsp;&nbsp;<%=methodPage.getInspectorName()%></td>
					</tr>
					<tr>
						<td align="left">审核员：&nbsp;&nbsp;<%=methodPage.getAccessorName()%></td>
					</tr>
					<tr>
						<td align="left">批准人：&nbsp;&nbsp;<%=methodPage.getApproverName()%></td>
					</tr>
				</table>
				-->
				<table width="700" border="0" align="center">
					<tr>
						<td align="left">测试仪器型号：&nbsp;&nbsp;<%=methodPage.getTestDeviceModel()%></td>
					</tr>
					<tr>
						<td align="left">检测员：&nbsp;&nbsp;<%=methodPage.getInspectorName()%></td>
					</tr>
					<tr>
						<td width="230" align="left">审核员签名：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td align="left">批准人签名：&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td width="230" align="left">客户签名：</td>
					</tr>
				</table>
				
			</form>
		</DIV>
	</div>
</body>
</html>

<%@ page language="java"
	import="java.util.*,com.dbsoft.whjd.pageModel.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>双怠速法检测报告打印</title>
<script src='http://localhost:8000/CLodopfuncs.js'></script>
<!--
<script type="text/javascript" src="../../jslib/LodopFuncs.js"></script>
-->
</head>
<body>
	<div style="width:1000px;height:800px">
		<%
			TwoSpeedIdleMethodPage methodPage = (TwoSpeedIdleMethodPage) request
					.getSession().getAttribute("methodPage");
		%>
		<DIV id="div_to_print" style="LINE-HEIGHT: 20px" align=center>
			<form id="frm_to_print" style="width:700px">
				<div align="center">
					<div align="left" style="position:relative">
				         <img align="left" alt="CMA标志" src="/whjd/images/cma1.jpg"  height = "60" width = "100"  >
				         <span style="position:absolute;left:0;margin-top:50px;margin-left:5px;font-size:12px;color:red;">
							&nbsp;&nbsp;<%=methodPage.getInstitutionNum()%>&nbsp;号
						 </span>      
			        </div>
					<h3 style="display:inline-block;margin-right:150px">点燃式发动机汽车双怠速法排气污染检测报告</h3>
					<div align="right">
						<p>
							检测报告编号：&nbsp;&nbsp;<%=methodPage.getReportNumber()%>&nbsp;&nbsp;
						</p>
					</div>
				</div>
				<table border="0" align="center" style="width:700px">
					<tr>
						<td width="300" align="left">检测站名称（盖章）：&nbsp;&nbsp;<%=methodPage.getStationName()%>
						</td>
						<td width="50%" align="left">检测日期：&nbsp;&nbsp;<%=methodPage.getDetectionTime()%>
						</td>
					</tr>
					<tr>
						<td width="300px" align="left">检测站地址：&nbsp;&nbsp;<%=methodPage.getStationAddress()%>
						</td>
						<td width="50%" align="left">检测站联系电话：&nbsp;&nbsp;<%=methodPage.getRemarks()%>
						</td>	
					</tr>
					
					<tr>
						<td align="left">检测操作员：&nbsp;&nbsp;<%=methodPage.getSysUserByInspecOperatorName()%></td>
						<td align="left">检测驾驶员：&nbsp;&nbsp;<%=methodPage.getSysUserByInspecDriverName()%></td>
					</tr>
					<tr>
						<td width="300" align="left">1. 车辆信息</td>
						<td width="50%" align="left">&nbsp;</td>
					</tr>
					<tr>
						<td width="300" align="left">车辆号牌：&nbsp;&nbsp;<%=methodPage.getLicence()%>&nbsp;（<%=methodPage.getLicenseColor()%>）</td>
						<td width="50%" align="left">生产企业：&nbsp;&nbsp;<%=methodPage.getVehicleManufacturer()%></td>
					</tr>
					<tr>
						<td width="300" align="left">基准质量：&nbsp;&nbsp;<%=methodPage.getBaseQuality()==null?"":methodPage.getBaseQuality()%>公斤</td>
						<td width="50%" align="left">最大总质量：&nbsp;&nbsp;<%=methodPage.getMaxTotalQuality()==null?"":methodPage.getMaxTotalQuality()%>Kg</td>
					</tr>
					<tr>
						<td width="300" align="left">单车轴重：&nbsp;&nbsp;&nbsp;<%=methodPage.getSingleAxleLoad()==null?"":methodPage.getSingleAxleLoad()%>公斤</td>
						<td width="50%" align="left">底盘型号：&nbsp;&nbsp;&nbsp;<%=methodPage.getChassisModel()%></td>
					</tr>
					<tr>
						<td width="300" align="left">驱动方式：&nbsp;&nbsp;<%=methodPage.getDriveMode()%></td>
						<td width="50%" align="left">驱动轮胎气压：&nbsp;&nbsp;<%=methodPage.getTirePressure()==null?"":methodPage.getTirePressure()%>kPa</td>
					</tr>
					<tr>
						<td width="300" align="left">变速器型式：&nbsp;&nbsp;<%=methodPage.getTransmissionForm()%></td>
						<td width="50%" align="left">档位数：&nbsp;&nbsp;<%=methodPage.getGearNumber()==null?"":methodPage.getGearNumber()%></td>
					</tr>
					<tr>
						<td width="300" align="left">发动机型号：&nbsp;&nbsp;<%=methodPage.getEngineModel()%></td>
						<td width="50%" align="left">生产企业：&nbsp;&nbsp;<%=methodPage.getEngineManufacturer()%></td>
					</tr>
					<tr>
						<td align="left">汽缸数：&nbsp;&nbsp;<%=methodPage.getCylinderNumber()==null?"":methodPage.getCylinderNumber()%></td>
						<td align="left">发动机排量：&nbsp;&nbsp;<%=methodPage.getEngineemissionAmount()==null?"":methodPage.getEngineemissionAmount()%>升</td>
					</tr>
					<tr>
						<td align="left">燃油型式：&nbsp;&nbsp;<%=methodPage.getFuelType()%></td>
						<td align="left">催化转化器情况：&nbsp;&nbsp;<%=methodPage.getCatalyticConverter()%></td>
					</tr>
					<tr>
						<td align="left">累计行驶里程：&nbsp;&nbsp;<%=methodPage.getTotalMiles()==null?"":methodPage.getTotalMiles()%>千米</td>
						<td align="left">燃油规格：&nbsp;&nbsp;<%=methodPage.getFuelSpecification()%></td>
					</tr>
					<tr>
						<td align="left">车牌号码：&nbsp;&nbsp;<%=methodPage.getLicence()%></td>
						<td align="left">车辆识别码：&nbsp;&nbsp;<%=methodPage.getVin()%></td>
					</tr>
					<tr>
						<td align="left">车辆登记日期：&nbsp;&nbsp;<%=methodPage.getVehicleRegisterDate()%></td>
						<td align="left">车主姓名及其联系方式：&nbsp;&nbsp;<%=methodPage.getVehicleOwnerName()%>&nbsp;&nbsp;&nbsp;&nbsp;<%=methodPage.getVehicleOwnerAddress()%></td>
					</tr>
				</table>
				<table width="700" border="0" align="center" cellspacing="0">
					<tr>
						<td width="300" align="left">2. 检测设备</td>
						<td align="left">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td width="300" align="left">设备认证编码：&nbsp;&nbsp;<%=methodPage.getDeviceAuthNumber()%></td>
						<td align="left">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td width="300" align="left">设备名称：&nbsp;&nbsp;<%=methodPage.getDeviceName()%></td>
						<td align="left">型号：&nbsp;&nbsp;<%=methodPage.getDeviceModel()%></td>
						<td align="left">制造厂：&nbsp;&nbsp;<%=methodPage.getDeviceManufacturer()%></td>
					</tr>
				</table>
				<table style="width:700px" align="center">
					<tr>
						<td width="230" align="left">3. 车主姓名：&nbsp;&nbsp;<%=methodPage.getVehicleOwnerName()%></td>
						<td align="left">车主地址：&nbsp;&nbsp;<%=methodPage.getVehicleOwnerAddress()%></td>
						<td width="230" align="left">联系电话：&nbsp;&nbsp;<%=methodPage.getPhoneNum()%></td>
					</tr>
					<tr>
						<td width="230" align="left">4. 检测环境状态</td>
						<td>&nbsp;</td>
						<td width="230" align="left">&nbsp;</td>
					</tr>
					<tr>
						<td width="230" align="left">温度：&nbsp;&nbsp;&nbsp;<%=methodPage.getTemperature()==null?"":methodPage.getTemperature()%>℃</td>
						<td align="left">大气压：&nbsp;&nbsp;&nbsp;<%=methodPage.getAirPressure()==null?"":methodPage.getAirPressure()%>KPa</td>
						<td width="230" align="left">相对湿度：&nbsp;&nbsp;&nbsp;<%=methodPage.getRelativeHumidity()==null?"":methodPage.getRelativeHumidity()%>%</td>
					</tr>
					<tr>
						<td width="230" align="left">5. 检测结果及裁决</td>
						<td>&nbsp;</td>
						<td width="230" align="left">&nbsp;</td>
					</tr>
				</table>
				<table border="1" align="center" cellpadding="5"
					bordercolor="#000000" cellspacing="0"
					style="border-collapse: collapse" width="700px">
					<tr>
						<td width="100" rowspan="2" align="center">内容</td>
						<td width="200" rowspan="2" align="center">过量空气系数（λ）</td>
						<td colspan="2" align="center">低怠速</td>
						<td colspan="2" align="center">高怠速</td>
					</tr>
					<tr>
						<td width="100" align="center">CO（%）</td>
						<td width="100" align="center">HC（×10<sup>-6</sup>）</td>
						<td width="100" align="center">CO（%）</td>
						<td width="100" align="center">HC（×10<sup>-6</sup>）</td>
					</tr>
					<tr>
						<td width="100" align="center">测试结果</td>
						<td width="200" align="center"><%=methodPage.getSdsLambda()==null?"":methodPage.getSdsLambda()%></td>
						<td width="100" align="center"><%=methodPage.getSdsLCo()==null?"":methodPage.getSdsLCo()%></td>
						<td width="100" align="center"><%=methodPage.getSdsLHc()==null?"":methodPage.getSdsLHc()%></td>
						<td width="100" align="center"><%=methodPage.getSdsHCo()==null?"":methodPage.getSdsHCo()%></td>
						<td width="100" align="center"><%=methodPage.getSdsHHc()==null?"":methodPage.getSdsHHc()%></td>
					</tr>
					<tr>
						<td width="100" align="center">限值</td>
						<td width="200" align="center"><%=methodPage.getSdsLambdaLimit()==null?"":methodPage.getSdsLambdaLimit()%></td>
						<td width="100" align="center"><%=methodPage.getSdsLCoLimit()==null?"":methodPage.getSdsLCoLimit()%></td>
						<td width="100" align="center"><%=methodPage.getSdsLHcLimit()==null?"":methodPage.getSdsLHcLimit()%></td>
						<td width="100" align="center"><%=methodPage.getSdsHCoLimit()==null?"":methodPage.getSdsHCoLimit()%></td>
						<td width="100" align="center"><%=methodPage.getSdsHHcLimit()==null?"":methodPage.getSdsHHcLimit()%></td>
					</tr>
					<tr>
						<td width="100" align="center">判定结果</td>
						<td width="200" align="center"><%=methodPage.getSdsLambdaConclusion()%></td>
						<td colspan="2" align="center"><%=methodPage.getSdsLConclusion()%></td>
						<td colspan="2" align="center"><%=methodPage.getSdsHConclusion()%></td>
					</tr>
					<tr>
						<td width="100" align="center">裁决</td>
						<td colspan="5" align="center"><%=methodPage.getConclusion()==null?"":methodPage.getConclusion()%></td>
					</tr>
				</table>
					
				<table border="0" align="center" style="width:700px">
				      <tr>
						<td width="50%" align="left">检测依据&nbsp;&nbsp;GB18285-2005&nbsp;&nbsp;</td>
						<td width="50%" align="left">&nbsp;</td>
					  </tr>
				</table>	
				<br />
				
				<table width="700" border="0" align="center">
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

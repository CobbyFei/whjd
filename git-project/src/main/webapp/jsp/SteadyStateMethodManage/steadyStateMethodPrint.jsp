<%@ page language="java"
	import="java.util.*,com.dbsoft.whjd.pageModel.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>稳态工况法检测报告打印</title>
<script src='http://localhost:8000/CLodopfuncs.js'></script>
<!--  
<script type="text/javascript" src="../../jslib/LodopFuncs.js"></script>
-->
</head>
<body>
	<div style="width:1000px;height:800px">
		<%
			SteadyStateMethodPage methodPage = (SteadyStateMethodPage) request
					.getSession().getAttribute("methodPage");
			//结果
			String hc50Conclusion = "";
			if (methodPage.getWtHcAsm5025Conclusion() != null) {
				if (methodPage.getWtHcAsm5025Conclusion())
					hc50Conclusion = "合格";
				else
					hc50Conclusion = "不合格";
			}
			String hc25Conclusion = "";
			if (methodPage.getWtHcAsm2540Conclusion() != null) {
				if (methodPage.getWtHcAsm2540Conclusion())
					hc25Conclusion = "合格";
				else
					hc25Conclusion = "不合格";
			}
			String co50Conclusion = "";
			if (methodPage.getWtCoAsm5025Conclusion() != null) {
				if (methodPage.getWtCoAsm5025Conclusion())
					co50Conclusion = "合格";
				else
					co50Conclusion = "不合格";
			}
			String co25Conclusion = "";
			if (methodPage.getWtCoAsm2540Conclusion() != null) {
				if (methodPage.getWtCoAsm2540Conclusion())
					co25Conclusion = "合格";
				else
					co25Conclusion = "不合格";
			}
			String no50Conclusion = "";
			if (methodPage.getWtNoAsm5025Conclusion() != null) {
				if (methodPage.getWtNoAsm5025Conclusion())
					no50Conclusion = "合格";
				else
					no50Conclusion = "不合格";
			}
			String no25Conclusion = "";
			if (methodPage.getWtNoAsm2540Conclusion() != null) {
				if (methodPage.getWtNoAsm2540Conclusion())
					no25Conclusion = "合格";
				else
					no25Conclusion = "不合格";
			}
			//裁决
			String hc50Judge = "";
			if (methodPage.getWtHcAsm5025Judge() != null) {
				if (methodPage.getWtHcAsm5025Judge())
					hc50Judge = "通过";
				else
					hc50Judge = "未通过";
			}
			String hc25Judge = "";
			if (methodPage.getWtHcAsm2540Judge() != null) {
				if (methodPage.getWtHcAsm2540Judge())
					hc25Judge = "通过";
				else
					hc25Judge = "未通过";
			}
			String co50Judge = "";
			if (methodPage.getWtCoAsm5025Judge() != null) {
				if (methodPage.getWtCoAsm5025Judge())
					co50Judge = "通过";
				else
					co50Judge = "未通过";
			}
			String co25Judge = "";
			if (methodPage.getWtCoAsm2540Judge() != null) {
				if (methodPage.getWtCoAsm2540Judge())
					co25Judge = "通过";
				else
					co25Judge = "未通过";
			}
			String no50Judge = "";
			if (methodPage.getWtNoAsm5025Judge() != null) {
				if (methodPage.getWtNoAsm5025Judge())
					no50Judge = "通过";
				else
					no50Judge = "未通过";
			}
			String no25Judge = "";
			if (methodPage.getWtNoAsm2540Judge() != null) {
				if (methodPage.getWtNoAsm2540Judge())
					no25Judge = "通过";
				else
					no25Judge = "未通过";
			}
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
					<h3 style="display:inline-block;margin-right:150px">点燃式发动机汽车稳态工况法排气污染检测报告</h3>
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
						<td align="left">检测操作员：&nbsp;&nbsp;<%=methodPage.getInspecOperatorName()%></td>
						<td align="left">检测驾驶员：&nbsp;&nbsp;<%=methodPage.getInspecDriverName()%></td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td width="300" align="left">1. 车辆信息</td>
						<td width="50%" align="left">&nbsp;</td>
					</tr>
					<tr>
						<td width="300" align="left">车辆号牌：&nbsp;&nbsp;<%=methodPage.getLicence()%>&nbsp;（<%=methodPage.getLicenseColor()%>）</td>
						<td width="50%" align="left">生产企业：&nbsp;&nbsp;<%=methodPage.getVehicleManufacturer()%></td>
					</tr>
					<tr>
						<td width="300" align="left">基准质量：&nbsp;&nbsp;<%=methodPage.getBaseQuality()%>公斤</td>
						<td width="50%" align="left">最大总质量：&nbsp;&nbsp;<%=methodPage.getMaxTotalQuality()%>Kg</td>
					</tr>
					<tr>
						<td width="300" align="left">单车轴重：&nbsp;&nbsp;&nbsp;<%=methodPage.getSingleAxleLoad()%>公斤</td>
						<td width="50%" align="left">底盘型号：&nbsp;&nbsp;&nbsp;<%=methodPage.getChassisModel()%></td>
					</tr>
					<tr>
						<td width="300" align="left">驱动方式：&nbsp;&nbsp;<%=methodPage.getDriveMode()%></td>
						<td width="50%" align="left">驱动轮胎气压：&nbsp;&nbsp;<%=methodPage.getTirePressure()%></td>
					</tr>
					<tr>
						<td width="300" align="left">变速器型式：&nbsp;&nbsp;<%=methodPage.getTransmissionForm()%></td>
						<td width="50%" align="left">挡位数：&nbsp;&nbsp;<%=methodPage.getGearNumber()%></td>
					</tr>
					<tr>
						<td width="300" align="left">发动机型号：&nbsp;&nbsp;<%=methodPage.getEngineModel()%></td>
						<td width="50%" align="left">生产企业：&nbsp;&nbsp;<%=methodPage.getEngineManufacturer()%></td>
					</tr>
					<tr>
						<td align="left">汽缸数：&nbsp;&nbsp;<%=methodPage.getCylinderNumber()%></td>
						<td align="left">发动机排量：&nbsp;&nbsp;<%=methodPage.getEngineemissionAmount()%>升</td>
					</tr>
					<tr>
						<td align="left">燃油型式：&nbsp;&nbsp;<%=methodPage.getFuelType()%></td>
						<td align="left">催化转化器情况：&nbsp;&nbsp;<%=methodPage.getCatalyticConverter()%></td>
					</tr>
					<tr>
						<td align="left">累计行驶里程：&nbsp;&nbsp;<%=methodPage.getTotalMiles()%>公里</td>
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
				<br>
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
					<tr>
						<td width="300" align="left">底盘测功机：&nbsp;&nbsp;<%=methodPage.getChassisDynamometer()%></td>
						<td align="left">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
					<tr>
						<td width="300" align="left">排气分析仪：&nbsp;&nbsp;<%=methodPage.getExhaustGasAnalyser()%></td>
						<td align="left">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>
				</table>
				<br>
				<table style="width:700px" align="center">
					<tr>
						<td width="230" align="left">3. 车主姓名：&nbsp;&nbsp;<%=methodPage.getVehicleOwnerName()%></td>
						<td align="left">车主地址：&nbsp;&nbsp;<%=methodPage.getVehicleOwnerAddress()%></td>
						<td width="230" align="left">联系电话：&nbsp;&nbsp;<%=methodPage.getPhoneNum()%></td>
					</tr>
					<tr style="height:10px"></tr>
					<tr>
						<td width="230" align="left">4. 检测环境状态</td>
						<td>&nbsp;</td>
						<td width="230" align="left">&nbsp;</td>
					</tr>
					<tr>
						<td width="230" align="left">温度：&nbsp;&nbsp;&nbsp;<%=methodPage.getTemperature()%>℃</td>
						<td align="left">大气压：&nbsp;&nbsp;&nbsp;<%=methodPage.getAirPressure()%>kPa</td>
						<td width="230" align="left">相对湿度：&nbsp;&nbsp;&nbsp;<%=methodPage.getRelativeHumidity()%>%</td>
					</tr>
					<tr style="height:10px"></tr>
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
						<td rowspan="2" align="center">排气污染物</td>
						<td colspan="2" align="center">HC<br> （×10<sup>-6</sup>）</td>
						<td colspan="2" align="center">CO<br>（%）</td>
						<td colspan="2" align="center">NO<br> （×10<sup>-6</sup>）</td>
					</tr>
					<tr>
						<td align="center">ASM5025</td>
						<td align="center">ASM2540</td>
						<td align="center">ASM5025</td>
						<td align="center">ASM2540</td>
						<td align="center">ASM5025</td>
						<td align="center">ASM2540</td>
					</tr>
					<tr>
					<!-- xuningli -->
						<td align="center">测试结果</td>
						<td align="center"><%=methodPage.getWtHcAsm5025()%></td>
						<td align="center"><%=(methodPage.getWtHcAsm5025()<=methodPage.getWtHcAsm5025Limit()/2)&&(methodPage.getWtCoAsm5025()<=methodPage.getWtCoAsm5025Limit()/2)&&(methodPage.getWtNoAsm5025()<=methodPage.getWtNoAsm5025Limit()/2)?"————":methodPage.getWtHcAsm2540() %></td>
						<td align="center"><%=methodPage.getWtCoAsm5025()%></td>
						<td align="center"><%=(methodPage.getWtHcAsm5025()<=methodPage.getWtHcAsm5025Limit()/2)&&(methodPage.getWtCoAsm5025()<=methodPage.getWtCoAsm5025Limit()/2)&&(methodPage.getWtNoAsm5025()<=methodPage.getWtNoAsm5025Limit()/2)?"————":methodPage.getWtCoAsm2540() %></td>
						<td align="center"><%=methodPage.getWtNoAsm5025()%></td>
						<td align="center"><%=(methodPage.getWtHcAsm5025()<=methodPage.getWtHcAsm5025Limit()/2)&&(methodPage.getWtCoAsm5025()<=methodPage.getWtCoAsm5025Limit()/2)&&(methodPage.getWtNoAsm5025()<=methodPage.getWtNoAsm5025Limit()/2)?"————":methodPage.getWtNoAsm2540() %></td>
					</tr>
					<tr>
						<td align="center">排放限值</td>
						<td align="center"><%=methodPage.getWtHcAsm5025Limit()%></td>
						<td align="center"><%=methodPage.getWtHcAsm2540Limit()==null?"————":methodPage.getWtHcAsm2540Limit()%></td>
						<td align="center"><%=methodPage.getWtCoAsm5025Limit()%></td>
						<td align="center"><%=methodPage.getWtCoAsm2540Limit()==null?"————":methodPage.getWtCoAsm2540Limit()%></td>
						<td align="center"><%=methodPage.getWtNoAsm5025Limit()%></td>
						<td align="center"><%=methodPage.getWtNoAsm2540Limit()==null?"————":methodPage.getWtNoAsm2540Limit()%></td>
					</tr>
					<tr>
						<td align="center">判定结果（合格/不合格）</td>
						<td align="center"><%=hc50Conclusion%></td>
						<td align="center"><%=hc25Conclusion==null?"————":hc25Conclusion%></td>
						<td align="center"><%=co50Conclusion%></td>
						<td align="center"><%=co25Conclusion==null?"————":co25Conclusion%></td>
						<td align="center"><%=no50Conclusion%></td>
						<td align="center"><%=no25Conclusion==null?"————":no25Conclusion%></td>
					</tr>
					<tr>
						<td align="center">裁决（通过/未通过）</td>
						<td align="center"><%=hc50Judge%></td>
						<td align="center"><%=hc25Judge==null?"————":hc25Judge%></td>
						<td align="center"><%=co50Judge%></td>
						<td align="center"><%=co25Judge==null?"————":co25Judge%></td>
						<td align="center"><%=no50Judge%></td>
						<td align="center"><%=no25Judge==null?"————":no25Judge%></td>
					</tr>
				</table>
					
				<table border="0" align="center" style="width:700px">
				      <tr>
						<td width="50%" align="left">《确定点燃式发动机在用汽车简易工况法排气污染物排放限值的原则和方法》&nbsp;&nbsp;（HJ/T 240-2005）&nbsp;&nbsp;</td>
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

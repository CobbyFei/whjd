<%@ page language="java"
	import="java.util.*,com.dbsoft.whjd.pageModel.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>摩托车双怠速法检测报告打印</title>
<script src='http://localhost:8000/CLodopfuncs.js'></script>
<!--  
<script type="text/javascript" src="../../jslib/LodopFuncs.js"></script>
-->
</head>
<body>
	<div style="width:1000px;height:800px">
		<%
			MotorTwoSpeedIdleMethodPage methodPage = (MotorTwoSpeedIdleMethodPage) request
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
					<h3 style="display:inline-block;margin-right:150px">在用摩托车双怠速法排气污染检测报告</h3>
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
						<td width="300" align="left">1. 车辆信息</td>
						<td width="50%" align="left"></td>
					</tr>
					<tr>
						<td width="50%" align="left">车辆号牌：&nbsp;&nbsp;<%=methodPage.getLicence()%>&nbsp;（<%=methodPage.getLicenseColor()%>）</td>
						<td width="50%" align="left">车辆型号：&nbsp;&nbsp;<%=methodPage.getVehicleModelCode()%></td>
					</tr>
					<tr>
						<td width="300" align="left">车架编号：&nbsp;&nbsp;<%=methodPage.getVin()%></td>
						<td width="50%" align="left">发动机编号：&nbsp;&nbsp;<%=methodPage.getEngineCode()%></td>
					</tr>
					<tr>
						<td width="50%" align="left">冲程数：&nbsp;&nbsp;&nbsp;<%=methodPage.getStrokes()%></td>
						<td width="50%" align="left">最大净功率转速（r/min）：&nbsp;&nbsp;&nbsp;<%=methodPage.getMaxRpm()%></td>
					</tr>
					<tr>
						<td width="50%" align="left">怠速（r/min）：&nbsp;&nbsp;&nbsp;<%=methodPage.getIdleSpeedRpm()%></td>
						<td width="50%" align="left">高怠速转速（r/min）：&nbsp;&nbsp;&nbsp;<%=methodPage.getHighIdleSpeedRpm()==null?"":methodPage.getHighIdleSpeedRpm()%></td>
					</tr>
					<tr>
						<td width="50%" align="left">燃料规格：&nbsp;&nbsp;<%=methodPage.getFuelSpecification()%></td>
						<td width="50%" align="left">润滑油规格：&nbsp;&nbsp;<%=methodPage.getLubeSpecification()%></td>
					</tr>
					<tr>
						<td width="50%" align="left">燃油供给方式：&nbsp;&nbsp;<%=methodPage.getFuelSupplyStyle()%></td>
						<td width="50%" align="left">燃油喷射系统：&nbsp;&nbsp;<%=methodPage.getFuelJetSystem()%></td>
					</tr>
					<tr>
						<td width="50%" align="left">污染控制装置：&nbsp;&nbsp;<%=methodPage.getPollutionControlDevice()%></td>
						<td width="50%" align="left">生产企业：&nbsp;&nbsp;<%=methodPage.getVehicleManufacturer()%></td>
					</tr>
				</table>
				<table border="0" align="center" style="width:700px">
					<tr>
						<td width="300" align="left">2. 检测仪器</td>
						<td width="50%" align="left"></td>
					</tr>
					<tr>
						<td width="50%" align="left">排气分析仪型号：&nbsp;&nbsp;<%=methodPage.getExhaustAnalyzerModel()%></td>
						<td width="50%" align="left">转速计型号：&nbsp;&nbsp;<%=methodPage.getTachometerModel()%></td>
					</tr>
				</table>
				<table style="width:700px" align="center">
					<tr>
						<td width="230" align="left">3. 车主姓名：&nbsp;&nbsp;<%=methodPage.getVehicleOwnerName()%></td>
						<td align="left">车主地址：&nbsp;&nbsp;<%=methodPage.getVehicleOwnerAddress()%></td>
						<td width="230" align="left">联系电话：&nbsp;&nbsp;<%=methodPage.getPhoneNum()%></td>
					</tr>
					<tr>
						<td width="230" align="left">4. 检测环境</td>
						<td>&nbsp;</td>
						<td width="230" align="left">&nbsp;</td>
					</tr>
					<tr>
						<td align="left">温度：&nbsp;&nbsp;&nbsp;<%=methodPage.getTemperature()%>℃</td>
						<td width="230" align="left">大气压力：&nbsp;&nbsp;&nbsp;<%=methodPage.getAirPressure()%>kPa</td>
						<td width="230" align="left">相对湿度：&nbsp;&nbsp;&nbsp;<%=methodPage.getHumidity()%>%</td>
					</tr>
					<tr>
						<td align="left">试验地点：&nbsp;&nbsp;<%=methodPage.getExperimentAddress()%></td>
						<td align="left">试验日期：&nbsp;&nbsp;<%=methodPage.getDetectionTime()%></td>
						<td align="left">试验人员：&nbsp;&nbsp;<%=methodPage.getSysUserName()%></td>
					</tr>
					<tr>
						<td width="230" align="left">5. 检测结果</td>
						<td>&nbsp;</td>
						<td width="230" align="left">&nbsp;</td>
					</tr>
				</table>
				<table width="700" border="1" cellspacing="0" align="center">
					<tr>
						<td rowspan="2" align="center">内容</td>
						<td colspan="4" align="center">高怠速</td>
						<td colspan="4" align="center">怠速</td>
					</tr>
					<tr>
						<td align="center">转速/<br>（r/min）</td>
						<td align="center">CO/%</td>
						<td align="center">CO<sub>2</sub>/%</td>
						<td align="center">HC/10<sup>-6</sup></td>
						<td align="center">转速/<br>（r/min）</td>
						<td align="center">CO/%</td>
						<td align="center">CO<sub>2</sub>/%</td>
						<td align="center">HC/10<sup>-6</sup></td>
					</tr>
					<tr>
						<td align="center">测量结果</td>
						<td align="center"><%=methodPage.getHighIdleSpeedRpm()==null?"":methodPage.getHighIdleSpeedRpm()%></td>
						<td align="center"><%=methodPage.getHCoresult()==null?"":methodPage.getHCoresult()%></td>
						<td align="center"><%=methodPage.getHCo2result()==null?"":methodPage.getHCo2result()%></td>
						<td align="center"><%=methodPage.getHHcresult()==null?"":methodPage.getHHcresult()%></td>
						<td align="center"><%=methodPage.getIdleSpeedRpm()%></td>
						<td align="center"><%=methodPage.getCoresult()%></td>
						<td align="center"><%=methodPage.getCo2result()%></td>
						<td align="center"><%=methodPage.getHcresult()%></td>
					</tr>
					<tr>
						<td align="center">结果修正</td>
						<td align="center">——</td>
						<td align="center"><%=methodPage.getHCoreviseResult()==null?"":methodPage.getHCoreviseResult()%></td>
						<td align="center">——</td>
						<td align="center">——</td>
						<td align="center">——</td>
						<td align="center"><%=methodPage.getCoreviseResult()==null?"":methodPage.getCoreviseResult()%></td>
						<td align="center">——</td>
						<td align="center">——</td>
					</tr>
					<tr>
						<td align="center">结果修约</td>
						<td align="center">——</td>
						<td align="center"><%=methodPage.getHCoroundResult()==null?"":methodPage.getHCoroundResult()%></td>
						<td align="center"><%=methodPage.getHCo2roundResult()==null?"":methodPage.getHCo2roundResult()%></td>
						<td align="center"><%=methodPage.getHHcroundResult()==null?"":methodPage.getHHcroundResult()%></td>
						<td align="center">——</td>
						<td align="center"><%=methodPage.getCoroundResult()==null?"":methodPage.getCoroundResult()%></td>
						<td align="center"><%=methodPage.getCo2roundResult()==null?"":methodPage.getCo2roundResult()%></td>
						<td align="center"><%=methodPage.getHcroundResult()==null?"":methodPage.getHcroundResult()%></td>
					</tr>
					<tr>
						<td align="center">排放限值</td>
						<td align="center">——</td>
						<td align="center"><%=methodPage.getHColimit()==null?"":methodPage.getHColimit()%></td>
						<td align="center">——</td>
						<td align="center"><%=methodPage.getHHclimit()==null?"":methodPage.getHHclimit()%></td>
						<td align="center">——</td>
						<td align="center"><%=methodPage.getColimit()==null?"":methodPage.getColimit()%></td>
						<td align="center">——</td>
						<td align="center"><%=methodPage.getHclimit()==null?"":methodPage.getHclimit()%></td>
					</tr>
					<tr>
						<td align="center">结果</td>
						
						<td align="center" colspan="8"><%=methodPage.getConclusion()==null?"":methodPage.getConclusion()%></td>
						
					</tr>
				</table>
				
				
				<table border="0" align="center" style="width:700px">
				      <tr>
						<td width="50%" align="left">检测依据&nbsp;&nbsp;GB14621-2011&nbsp;&nbsp;</td>
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

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	$(function() {
		$("#LineId")
				.combobox(
						{
							required : true,
							editable : false,
							url : sy.bp()
									+ '/detectionLineAction!getDetectionLineNameCarlibrated.action',
							mode : 'remote',
							valueField : 'lineId',
							textField : 'lineName'
						});
		$("#VehicleManufacturer").validatebox({
			required : true
		});
		$("#FuelGrade").validatebox({
			required : true
		});
		$("#FuelSupplySystemModel").validatebox({
			required : true
		});
		$("#TestDeviceModel").validatebox({
			required : true
		});
		//相关人员
		$("#InspectorId").combobox(
				{
					editable : false,
					url : sy.bp()
							+ '/manageUserAction!getSysUsers.action?stationId='
							+ $("#StationId").val(),
					mode : 'remote',
					valueField : 'userId',
					textField : 'userName',
					required : true
				});
		$("#AccessorId").combobox(
				{
					editable : false,
					url : sy.bp()
							+ '/manageUserAction!getSysUsers.action?stationId='
							+ $("#StationId").val(),
					mode : 'remote',
					valueField : 'userId',
					textField : 'userName',
					required : true
				});
		$("#ApproverId").combobox(
				{
					editable : false,
					url : sy.bp()
							+ '/manageUserAction!getSysUsers.action?stationId='
							+ $("#StationId").val(),
					mode : 'remote',
					valueField : 'userId',
					textField : 'userName',
					required : true
				});
	});
</script>
<div data-options="region:'center',border:false,title:'修改检测信息'"
	style="overflow: hidden;" align="left">
	<form id="frm_add_record">
		<input name="stationId" id="StationId" type="hidden" value='1' /> <input
			name="reportStatus" id="ReportStatus" type="hidden" value='0' /> <input
			name="id" id="Id" type="hidden" /> <input name="methodId"
			id="MethodId" type="hidden" /><input name="conclusion"
			id="Conclusion" type="hidden">
		<table style="padding-top:10px" cellspacing="5">
			<tr>
				<td align="right">检测报告编号：</td>
				<td align="left"><input name="reportNumber" id="ReportNumber"
					style="width:175px" readonly disabled /></td>
				<td align="right">车牌号：</td>
				<td align="left"><input name="licence" id="Licence"
					style="width:175px" readonly disabled /></td>
				<td align="right">车主姓名：</td>
				<td align="left"><input name="vehicleOwnerName"
					id="VehicleOwnerName" style="width:153px" readonly disabled />
				</td>
			</tr>
		</table>
		<hr>
		<table style="paddeing-top:10px" cellspacing="5">
			<tr>
				<td align="right">检测线：</td>
				<td align="left"><select id="LineId" name="lineId"
					style="width:175px"></select></td>
				<td align="right">制造厂商：</td>
				<td align="left"><input name="vehicleManufacturer"
					id="VehicleManufacturer" style="width:175px" /></td>
				<td>试用燃料牌号：</td>
				<td><input name="fuelGrade" id="FuelGrade" style="width:175px" />
				</td>
			</tr>
			<tr>
				<td align="right">进气方式：</td>
				<td align="left"><select id="AirInletMode" name="airInletMode"
					style="width:175px"><option value="自然吸气">自然吸气</option>
						<option value="涡轮增压">涡轮增压</option>
				</select>
				</td>
				<td>供油系统形式：</td>
				<td><input name="fuelSupplySystemModel"
					id="FuelSupplySystemModel" style="width:175px" />
				</td>
				<td>排气后处理装置：</td>
				<td><select name="intHasEmissionProcessDevice"
					id="IntHasEmissionProcessDevice" style="width:175px">
						<option value="1" selected>有</option>
						<option value="0">无</option>
				</select>
			</tr>
			<tr>
				<td align="right">测试仪器型号：</td>
				<td align="left"><input id="TestDeviceModel"
					name="testDeviceModel" style="width:175px" />
				</td>
			</tr>
		</table>
		<hr>
		<table style="padding-top:10px" cellspacing="5">
			<tr>
				<td>测试现场环境</td>
				<td align="right">温度：</td>
				<td align="left"><input name="temperature" id="Temperature"
					style="width:175px" readonly disabled />°C</td>
				<td align="right">大气压：</td>
				<td align="left"><input name="airPressure" id="AirPressure"
					style="width:175px" readonly disabled />kPa</td>
				<td align="right">相对湿度：</td>
				<td align="left"><input name="relativeHumidity"
					id="RelativeHumidity" style="width:153px" readonly disabled />%</td>
			</tr>
			<tr>
				<td>最后三次测量值</td>
				<td align="right">1：</td>
				<td><input name="lastOneTest" id="LastOneTest" readonly disabled />m<sup>-1</sup>
				</td>
				<td align="right">2：</td>
				<td><input name="lastTwoTest" id="LastTwoTest" readonly disabled />m<sup>-1</sup>
				</td>
				<td align="right">3：</td>
				<td><input name="lastThreeTest" id="LastThreeTest" readonly disabled />m<sup>-1</sup>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>怠速转速：</td>
				<td><input name="rpm" id="Rpm" readonly disabled />r/min</td>
			</tr>
		</table>
		<hr>
		<table style="paddeing-top:10px" cellspacing="5">
			<tr>
				<td></td>
				<td align="right">检测员：</td>
				<td><select name="inspectorId" id="InspectorId"
					style="width:175px"></select></td>
				<td align="right">审核员：</td>
				<td><select name="accessorId" id="AccessorId"
					style="width:175px"></select></td>
				<td align="right">批准人：</td>
				<td><select name="approverId" id="ApproverId"
					style="width:175px"></select></td>
			</tr>
		</table>
	</form>
</div>
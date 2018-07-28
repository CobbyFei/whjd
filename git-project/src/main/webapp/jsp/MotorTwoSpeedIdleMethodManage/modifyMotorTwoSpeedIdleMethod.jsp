<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
$(function() {
	$("#detectionLineId").combobox(
					{
						required : true,
						editable : false,
						url : sy.bp()
								+ '/detectionLineAction!getDetectionLineNameCarlibrated.action',	
						mode : 'remote',
						valueField : 'lineId',
						textField : 'lineName'
					});
	$("#sysUserId").combobox(
			{
				required : true,
				url : sy.bp()+ '/manageUserAction!getSysUsers.action?stationId=${sessionScope.stationId}',
				mode : 'remote',
				valueField : 'userId',
				textField : 'userName'
			});

});
</script>
<div data-options="region:'center',border:false,title:'修改检测信息'"
	style="overflow: hidden;" align="left">
	<form id="frm_add_record">
		<input name="stationId" id="stationId" type="hidden" value='1' /> 
		<input name="reportStatus" id="reportStatus" type="hidden" value='0' />
		<input name="recordId" id="recordId"  type="hidden"/>  
			<input name="id" id="id"  type="hidden"/>
		<table style="padding-top:10px" cellspacing="5">
			<tr>
				<td align="right" >检测报告编号：</td>
				<td align="left" ><input name="reportNumber" id="reportNumber" style="width:175px;background-color:#CCCCCC" readonly /></td>
				<td align="right" >车牌号：</td>
				<td align="left" >
				<input name="licence" id="licence" style="width:175px;background-color:#CCCCCC" readonly /></td>
				<td align="right">车主姓名：</td>
				<td align="left"><input name="vehicleOwnerName" id="vehicleOwnerName" style="width:153px;background-color:#CCCCCC" readonly />
				</td>
			</tr>
		</table>
		<hr>
		<table style="paddeing-top:10px" cellspacing="5">
			<tr>
			    <td align="right">检测站：</td>
				<td align="left"><input id="stationName" name="stationName" style="width:120px;background-color:#CCCCCC"  readonly/></td>
				<td align="right">检测线：</td>
				<td align="left"><input id="detectionLineId" name="detectionLineId" style="width:120px"/></td>	
				<td align="right">生产企业：</td>
				<td align="left"><input name="vehicleManufacturer" id="vehicleManufacturer" style="width:120px" /></td>				
			</tr>

			<tr>
			    <td align="right">污染控制装置：</td>
				<td align="left"><input name="pollutionControlDevice" id="pollutionControlDevice" style="width:120px" /></td>
				<td align="right">试验地点：</td>
				<td align="left"><input name="experimentAddress" id="experimentAddress" style="width:120px" /></td>
			</tr>
			
			</table>
			<hr>

		<table style="padding-top:10px" cellspacing="5">			
			<tr>
				<td>检测环境</td>
			</tr>
			<tr>
				<td align="right" >温度：</td>
				<td align="left" ><input name="temperature" id="temperature" style="width:100px" class="easyui-numberbox" data-options="min:0,precision:2"/>℃</td>
				<td align="right" >大气压：</td>
				<td align="left" ><input name="airPressure" id="airPressure" style="width:100px" class="easyui-numberbox" data-options="min:0,precision:2"/>kPa</td>
				<td align="right" >相对湿度：</td>
				<td align="left" ><input name="humidity" id="humidity" style="width:100px" class="easyui-numberbox" data-options="min:0,precision:2"/>%</td>
			</tr>
			<tr>
			<td>检测仪器</td>	
			</tr>
			<tr>			
				<td align="right">排气分析仪型号：</td>
				<td align="left"><input name="exhaustAnalyzerModel" id="exhaustAnalyzerModel" style="width:100px" /></td>
				<td align="right">转速计型号：</td>
				<td align="left"><input id="tachometerModel" name="tachometerModel"  style="width:100px"></td>
			</tr>
			</table>
			<hr>
			<table style="paddeing-top:10px" cellspacing="5">
			<tr>
				<td align="right">燃料规格：</td>
				<td align="left"><input id="fuelSpecification" name="fuelSpecification" style="width:100px" maxlength="15"/></td>
				<td align="right">润滑油规格：</td>
				<td align="left"><input name="lubeSpecification" id="lubeSpecification"  style="width:100px" /></td>
				<td align="right">燃油供给方式：</td>
				<td align="left"><input name="fuelSupplyStyle" id="fuelSupplyStyle"  style="width:100px" />
				<td align="right">燃油喷射系统：</td>
				<td align="left"><input name="fuelJetSystem" id="fuelJetSystem"  style="width:100px" />
				</td>
			</tr>
			<tr>
				<td align="right">冲程数：</td>
				<td align="left">
				<select  class="easyui-combobox" name="strokes" id="strokes" style="width:80px;" data-options="editable:false">
                <option value=2>2</option>
                <option value=4>4</option>
                </select>
				</td>			
				<td align="right">车轮数：</td>
				<td align="left">				
				<select  class="easyui-combobox" name="wheelNums" id="wheelNums" style="width:80px;" data-options="editable:false">
                <option value=2>2</option>
                <option value=3>3</option>
                </select></td>	
			</tr>
			<tr>
				<td align="right">最大净功率转速：</td>
				<td align="left"><input id="maxRpm" name="maxRpm" class="easyui-numberbox" data-options="min:0,precision:2" style="width:100px"/>r/min</td>
			    <td align="right">怠速：</td>
				<td align="left"><input name="idleSpeedRpm" id="idleSpeedRpm" style="width:100px" class="easyui-numberbox" data-options="min:0,precision:2" />r/min
				<td align="right">高怠速转速：</td>
				<td align="left"><input name="highIdleSpeedRpm" id="highIdleSpeedRpm" style="width:100px" class="easyui-numberbox" data-options="min:0,precision:2" />r/min
			</tr>
		</table>
		<hr>
			<table style="padding-top:10px" cellspacing="5">
			<tr>
				<td>检测数据</td>
			</tr>
			<tr>
				<td align="right" style="width：20%">怠速CO：</td>
				<td align="left" style="width：20%"><input name="coresult" id="coresult" class="easyui-numberbox" data-options="min:0,precision:5" style="width:100px" disabled="disabled"/>%</td>
				<td align="right" style="width：20%">怠速HC：</td>
				<td align="left" style="width：20%"><input name="hcresult" id="hcresult" style="width:100px" class="easyui-numberbox" data-options="min:0,precision:5" disabled="disabled"/>10<sup>-6</sup></td>
				<td align="right" style="width：20%">怠速CO2：</td>
				<td align="left" style="width：20%"><input name="co2result" id="co2result" style="width:100px" class="easyui-numberbox" data-options="min:0,precision:5" disabled="disabled"/>%</td>
		    </tr>
		    <tr>
				<td align="right" style="width：20%">高怠速CO：</td>
				<td align="left" style="width：20%"><input name="HCoresult" id="HCoresult" style="width:100px" class="easyui-numberbox" data-options="min:0,precision:5" disabled="disabled"/>%</td>
				<td align="right" style="width：20%">高怠速HC：</td>
				<td align="left" style="width：20%"><input name="HHcresult" id="HHcresult" style="width:100px" class="easyui-numberbox" data-options="min:0,precision:5" disabled="disabled"/>10<sup>-6</sup></td>
			    <td align="right" style="width：20%">高怠速CO2：</td>
				<td align="left" style="width：20%"><input name="HCo2result" id="HCo2result" style="width:100px" class="easyui-numberbox" data-options="min:0,precision:5" disabled="disabled"/>%</td>
			</tr>
		</table>
		<hr>
		<table style="paddeing-top:10px" cellspacing="5">
			<tr>
				<td></td>
				<td align="right">检测员：</td>
				<td>  
				<input name="sysUserId" id="sysUserId" class="easyui-combobox" style="width:120px"/>  
                </td>
			</tr>
		</table>
	</form>
</div>
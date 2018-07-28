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
	$("#sysUserByInspecDriverId").combobox(
			{
				required : true,
				url : sy.bp()+ '/manageUserAction!getSysUsers.action?stationId=${sessionScope.stationId}',
				mode : 'remote',
				valueField : 'userId',
				textField : 'userName'
			});
	$("#sysUserByInspecOperatorId").combobox(
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
				<td align="right">发动机生产企业：</td>
				<td align="left"><input name="engineManufacturer" id="engineManufacturer" style="width:120px" maxlength="40"/></td>
				<td align="right">底盘型号：</td>
				<td align="left"><input name="chassisModel" id="chassisModel" style="width:120px" maxlength="30"/></td>
			</tr>
			<tr>
				<td align="right">驱动方式：</td>
				<td align="left"><select name="driveMode" id="driveMode" style="width:120px">
					  <option value ="两轮摩托车">两轮摩托车</option>
                      <option value ="三轮摩托车">三轮摩托车</option>
                      <option value ="4x2后驱后驻车" selected>4x2后驱后驻车</option>
                      <option value ="4x2前驱前驻车">4x2前驱前驻车</option>
                      <option value ="4x4后驱后驻车">4x4后驱后驻车</option>
                      <option value ="4x4全连驱后驻车">4x4全连驱后驻车</option>
                      <option value ="6x2中驱中驻车半挂">6x2中驱中驻车半挂</option>
                      <option value ="6x2双后浮动桥中驻车">6x2双后浮动桥中驻车</option>
                      <option value ="6x4双后桥连驱双后驻车">6x4双后桥连驱双后驻车</option>
                      <option value ="6x4双驱双后驻车">6x4双驱双后驻车</option>
                      <option value ="6x6全连驱双后驻车">6x6全连驱双后驻车</option>
                      <option value ="8x4双后驱双后驻车">8x4双后驱双后驻车</option>
                      <option value ="8x2中驱中驻车半挂">8x2中驱中驻车半挂</option>
                      <option value ="8x2后驱后驻车全挂">8x2后驱后驻车全挂</option>
                      <option value ="8x2中驱三后驻车半挂">8x2中驱三后驻车半挂</option>
                      <option value ="10x6三后驱三后驻车">10x6三后驱三后驻车</option>
                      <option value ="10x2中驱中驻车半挂">10x2中驱中驻车半挂</option>
                      <option value ="10x4双中驱双中驻车半挂">10x4双中驱双中驻车半挂</option>
                      <option value ="10x4双后驱双后驻车全挂">10x4双后驱双后驻车全挂</option>
                      <option value ="12x4双中驱双中驻车半挂">12x4双中驱双中驻车半挂</option>
					</select></td>			
				<td align="right">驱动轮胎气压：</td>
				<td align="left"><input id="tirePressure" name="tirePressure" class="easyui-numberbox" data-options="min:0,precision:2" style="width:120px"/></td>
			    <td align="right">催化转化器情况：</td>
				<td align="left"><input name="catalyticConverter" id="catalyticConverter" style="width:175px" maxlength="40"/>
			</tr>
			<tr>
				<td align="right">制造厂商：</td>
				<td align="left"><input name="vehicleManufacturer" id="vehicleManufacturer" style="width:120px" maxlength="30"/></td>
				<td align="right">变速器型式：</td>
				<td align="left"><input name="transmissionForm" id="transmissionForm" style="width:120px" maxlength="30"/></td>
				<td align="right">档位数：</td>
				<td align="left"><input id="gearNumber" name="gearNumber" class="easyui-numberbox" data-options="min:0" style="width:75px"></td>
			</tr>
			<tr>
				<td align="right">燃油规格：</td>
				<td align="left"><input id="fuelSpecification" name="fuelSpecification" style="width:120px" maxlength="15"/></td>
				<td align="right">单车轴重：</td>
				<td align="left"><input name="singleAxleLoad" id="singleAxleLoad" class="easyui-numberbox" data-options="min:0,precision:2" style="width:120px" />公斤</td>
				<td align="right">汽缸数：</td>
				<td align="left"><input name="cylinderNumber" id="cylinderNumber" class="easyui-numberbox" data-options="min:0" style="width:75px" />
				
				
				<td align="right">车轮数：</td>
				<td align="left"><input id="wheelNums" name="wheelNums"  class="easyui-numberbox"  data-options="required:true,min:0" style="width:75px" />
				</td>
			</tr>
			<tr>
				<td align="right">设备认证编码：</td>
				<td align="left"><input id="deviceAuthNumber" name="deviceAuthNumber" class="easyui-validatebox" data-options="required:false" style="width:120px" maxlength="30"/>
				</td>
				<td align="right">设备名称：</td>
				<td align="left"><input id="deviceName" name="deviceName" style="width:120px" maxlength="30"/>
				</td>
				<td align="right">设备型号：</td>
				<td align="left"><input id="deviceModel" name="deviceModel" style="width:120px" maxlength="30"/>
				</td>
				<td align="right">设备制造商：</td>
				<td align="left"><input id="deviceManufacturer" name="deviceManufacturer" style="width:120px" maxlength="30"/>
				</td>
			</tr>
		</table>
		<hr>
		<table style="padding-top:10px" cellspacing="5">
			<tr>
				<td>测试现场环境</td>
				<td align="right" style="width：20%">温度：</td>
				<td align="left" style="width：30%"><input name="temperature" id="temperature" style="width:100px" class="easyui-numberbox" data-options="precision:2"/>℃</td>
				<td align="right" style="width：20%">大气压：</td>
				<td align="left" style="width：30%"><input name="airPressure" id="airPressure" style="width:100px" class="easyui-numberbox" data-options="precision:2"/>kPa</td>
				<td align="right" style="width：20%">相对湿度：</td>
				<td align="left" style="width：30%"><input name="relativeHumidity" id="relativeHumidity" style="width:100px" class="easyui-numberbox" data-options="precision:2"/>%</td>
			</tr>
			</table>
			<hr>
			<table style="padding-top:10px" cellspacing="5">
			<tr>
				<td><h2>检测数据</h2></td>
			</tr>
			<tr>
				<td align="right" >过量空气系数：</td>
				<td align="left" ><input name="sdsLambda" id="sdsLambda" class="easyui-numberbox" data-options="precision:5" style="width:100px" disabled="disabled" /></td>
			</tr>
			<tr>
				<td align="right" >低怠速CO：</td>
				<td align="left" ><input name="sdsLCo" id="sdsLCo" class="easyui-numberbox" data-options="precision:5" style="width:100px" disabled="disabled"/>%</td>
				<td align="right" >低怠速HC：</td>
				<td align="left" ><input name="sdsLHc" id="sdsLHc" style="width:100px" class="easyui-numberbox" data-options="precision:5" disabled="disabled"/>10<sup>-6</sup></td>
			</tr>
			<tr>
				<td align="right" >高怠速CO：</td>
				<td align="left" ><input name="sdsHCo" id="sdsHCo" style="width:100px" class="easyui-numberbox" data-options="precision:5" disabled="disabled"/>%</td>
				<td align="right" >高怠速HC：</td>
				<td align="left" ><input name="sdsHHc" id="sdsHHc" style="width:100px" class="easyui-numberbox" data-options="precision:5" disabled="disabled"/>10<sup>-6</sup></td>
			</tr>
		</table>
		<hr>
		<table style="paddeing-top:10px" cellspacing="5">
			<tr>
				<td></td>
				<td align="right">检测员：</td>
				<td><input name="sysUserByInspecDriverId" id="sysUserByInspecDriverId" class="easyui-combobox" style="width:120px" /></td>
				<td align="right">审核员：</td>
				<td><input name="sysUserByInspecOperatorId" id="sysUserByInspecOperatorId" class="easyui-combobox" style="width:120px"/></td>
			</tr>
		</table>
	</form>
</div>
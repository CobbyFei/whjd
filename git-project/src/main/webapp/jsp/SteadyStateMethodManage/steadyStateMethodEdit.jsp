<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
$(function() {
	$("#lineId").combobox(
					{
						required : true,
						editable : false,
						url : sy.bp()
								+ '/detectionLineAction!getDetectionLineNameCarlibrated.action',	
						mode : 'remote',
						valueField : 'lineId',
						textField : 'lineName'
					});
	$("#inspecDriverId").combobox(
			{
				required : true,
				url : sy.bp()+ '/manageUserAction!getSysUsers.action',
				mode : 'remote',
				valueField : 'userId',
				textField : 'userName'
			});
	$("#inspecOperatorId").combobox(
			{
				required : true,				
				url : sy.bp()+ '/manageUserAction!getSysUsers.action',
				mode : 'remote',
				valueField : 'userId',
				textField : 'userName'
			});
			
	$('#vehicleRegisterDate').datebox({   
    	required:true,
    	disabled:true
	});  
});

</script>
<div data-options="region:'center',border:false,title:'修改检测信息'"
	style="overflow: hidden;" align="left">
	<form id="frm_add_record">
		<input name="reportStatus" id="reportStatus" type="hidden" value='0' />
		<input name="sheetId" id="sheetId" type="hidden" />
		<input name="recordId" id="recordId"  type="hidden"/>  
		<input name="commisionSheetStatus" id="commisionSheetStatus" type="hidden"/>
		<table style="padding-top:10px" cellspacing="5">
			<tr>
			<td align="right" >检测报告编号：</td>
				<td align="left" ><input name="reportNumber" id="reportNumber" style="width:175px; background-color:#CCCCCC" readonly/></td>
			</tr>
			<tr>
				<td align="right" >车牌号：</td>
				<td align="left" >
					<input name="licence" id="licence" style="width:175px; background-color:#CCCCCC" readonly />
				</td>
				<td align="right" >车辆识别码：</td>
				<td align="left" >
					<input name="vin" id="vin" style="width:175px; background-color:#CCCCCC" readonly />
				</td>
				<td align="right" >车辆登记日期：</td>
				<td align="left" >
					<input name="vehicleRegisterDate" id="vehicleRegisterDate" style="width:175px; background-color:#CCCCCC" readonly/>
				</td>
				<td align="right">车主姓名：</td>
				<td align="left"><input name="vehicleOwnerName" id="vehicleOwnerName" style="width:153px; background-color:#CCCCCC" readonly />
				</td>
			</tr>
		</table>
		<hr>
		<table style="paddeing-top:10px" cellspacing="5">
			<tr>
				<td align="right">车辆型号：</td>
				<td align="left"><input name="vehicleModelCode" id="vehicleModelCode" style="width:120px; background-color:#CCCCCC" readonly/></td>
				<td align="right">生产企业：</td>
				<td align="left"><input name="vehicleManufacturer" id="VehicleManufacturer" style="width:120px" 
										class="easyui-validatebox" required="true"/></td>
				<td align="right">基准质量：</td>
				<td align="left"><input name="baseQuality" id="baseQuality" style="width:100px; background-color:#CCCCCC" readonly/>公斤</td>
				
				<td align="right">最大总质量：(单位:Kg)</td>
				<td align="left"><input name="maxTotalQuality" id="maxTotalQuality" style="width:120px; background-color:#CCCCCC" readonly />吨</td>
			
			</tr>	
			<tr>
				<td align="right">单车轴重：</td>
				<td align="left"><input name="singleAxleLoad" id="singleAxleLoad" class="easyui-numberbox" required="true" data-options="min:0" style="width:120px" />公斤</td>
				<td align="right">底盘型号：</td>
				<td align="left"><input name="chassisModel" id="chassisModel" style="width:120px"
										class="easyui-validatebox" /></td>	
				<td align="right">驱动方式：</td>
				<td align="left">
					<select name="driveMode" id="driveMode" style="width:120px">
						  <option value ="两轮摩托车">两轮摩托车</option>
	                      <option value ="三轮摩托车">三轮摩托车</option>
	                      <option value ="4x2后驱后驻车" selected>4x2后驱后驻车</option>
	                      <option value ="4x2前驱前驻车">4x2前驱前驻车</option>
	                      <option value ="4x2前驱前驻车">4x2前驱后驻车</option>
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
	                </select>
				</td>			
				<td align="right">驱动轮胎气压：</td>
				<td align="left"><input id="tirePressure" name="tirePressure" class="easyui-numberbox"  data-options="min:0" style="width:120px"/></td>
				
				
			</tr>
			<tr>
				<td align="right">变速器型式：</td>
				<td align="left"><input name="transmissionForm" id="transmissionForm" style="width:120px" 
									class="easyui-validatebox" required="true"/></td>
				<td align="right">档位数：</td>
				<td align="left"><input id="gearNumber" name="gearNumber" class="easyui-numberbox" data-options="min:0, required:true" style="width:75px"></td>
				<td align="right">发动机型号：</td>
				<td align="left"><input name="engineModel" id="engineModel" style="width:120px; background-color:#CCCCCC" readonly/></td>
				<td align="right">发动机生产企业：</td>
				<td align="left"><input name="engineManufacturer" id="engineManufacturer" style="width:120px" 
									class="easyui-validatebox" required="true"/></td>
			</tr>
			<tr>
				<td align="right">燃油型式：</td>
				<td align="left"><input id="fuelType" name="fuelType" style="width:120px; background-color:#CCCCCC" readonly/></td>
				
				<td align="right">汽缸数：</td>
				<td align="left"><input name="cylinderNumber" id="cylinderNumber" class="easyui-numberbox" data-options="min:0, required:true" style="width:75px" />
				</td>
				<td align="right">发动机排量：</td>
				<td align="left"><input name="engineemissionAmount" id="engineemissionAmount" style="width:75px; background-color:#CCCCCC" readonly/>升
				</td>
			
				<td align="right">轮数：</td>
				<td align="left"><input name="wheelNums" id="wheelNums" class="easyui-numberbox" data-options="min : 0, precision : 0, value : 0, required : true"style="width:75px"/>
				</td>
				
			</tr>
			<tr>
				<td align="right">催化转化器情况：</td>
				<td align="left"><input name="catalyticConverter" id="catalyticConverter" style="width:175px" 
								class="easyui-validatebox" required="true"/></td>
				<td align="right">燃油规格：</td>
				<td align="left"><input id="fuelSpecification" name="fuelSpecification" style="width:120px"
									class="easyui-validatebox" required="true"/></td>
				<td align="right">累计行驶里程：</td>
				<td align="left"><input id="totalMiles" name="totalMiles" style="width:120px; background-color:#CCCCCC" readonly/>公里</td>
			</tr>
			<tr>
				<td align="right">设备认证编码：</td>
				<td align="left"><input id="deviceAuthNumber" name="deviceAuthNumber" class="easyui-validatebox" style="width:120px"/>
				</td>
				<td align="right">设备名称：</td>
				<td align="left"><input id="deviceName" name="deviceName" style="width:120px" 
						class="easyui-validatebox"/>
				</td>
				<td align="right">设备型号：</td>
				<td align="left"><input id="deviceModel" name="deviceModel" style="width:120px" 
								class="easyui-validatebox"/>
				</td>
				<td align="right">设备制造商：</td>
				<td align="left"><input id="deviceManufacturer" name="deviceManufacturer" style="width:120px"
				 					class="easyui-validatebox"/>
				</td>
			</tr>
			
			<tr>
				<td align="right">底盘测功机：</td>
				<td align="left"><input id="chassisDynamometer" name="chassisDynamometer" style="width:120px" 
									class="easyui-validatebox"/>
				</td>
				<td align="right">排气分析仪：</td>
				<td align="left"><input id="exhaustGasAnalyser" name="exhaustGasAnalyser" style="width:120px" 
									class="easyui-validatebox" />
				</td>
			</tr>
			 
			
		</table>
		<hr>
			<table style="padding-top:10px" cellspacing="5">
			<tr>
				<td>测试现场环境</td>
				<td align="right" style="width：20%">温度：</td>
				<td align="left" style="width：30%"><input name="temperature" id="Temperature" style="width:100px" class="easyui-numberbox" data-options="precision:2,min:0"/>℃</td>
				<td align="right" style="width：20%">大气压：</td>
				<td align="left" style="width：30%"><input name="airPressure" id="AirPressure" style="width:100px" class="easyui-numberbox" data-options="precision:2,min:0"/>kPa</td>
				<td align="right" style="width：20%">相对湿度：</td>
				<td align="left" style="width：30%"><input name="relativeHumidity" id="RelativeHumidity" style="width:100px" class="easyui-numberbox" data-options="precision:2,min:0"/>%</td>
			</tr>
			</table>
			<hr>
		
			<a style="color:#0000FF">注意：ASM5025工况为必检项目，不可为空。ASM2540工况未进行的话，应全部空白！</a>
		<table style="padding-top:10px" cellspacing="5">
			<tr>
				<td>检测数据:</td>
			</tr>
			<tr>
				<td align="right" style="width：20%">HC ASM5025：</td>
				<td align="left" style="width：20%"><input name="wtHcAsm5025" id="wtHcAsm5025" class="easyui-numberbox" data-options="precision:2,min:0" style="width:100px; background-color:#CCCCCC" />10<sup>-6</sup></td>
				
				<td align="right" style="width：20%">CO ASM5025：</td>
				<td align="left" style="width：20%"><input name="wtCoAsm5025" id="wtCoAsm5025" class="easyui-numberbox" data-options="precision:2,min:0" style="width:100px; background-color:#CCCCCC"  />%</td>
				
				<td align="right" style="width：20%">NO ASM5025：</td>
				<td align="left" style="width：20%"><input name="wtNoAsm5025" id="wtNoAsm5025" class="easyui-numberbox" data-options="precision:2,min:0" style="width:100px; background-color:#CCCCCC"  />10<sup>-6</sup></td>		
				
			</tr>
			
			<tr>
				<td align="right" style="width：20%">HC ASM2540：</td>
				<td align="left" style="width：20%"><input name="wtHcAsm2540" id="wtHcAsm2540" class="easyui-numberbox" data-options="precision:2,min:0" style="width:100px; background-color:#CCCCCC"  />10<sup>-6</sup></td>
				
				<td align="right" style="width：20%">CO ASM2540：</td>
				<td align="left" style="width：20%"><input name="wtCoAsm2540" id="wtCoAsm2540" class="easyui-numberbox" data-options="precision:2,min:0" style="width:100px; background-color:#CCCCCC" />%</td>
				
				<td align="right" style="width：20%">NO ASM2540：</td>
				<td align="left" style="width：20%"><input name="wtNoAsm2540" id="wtNoAsm2540" class="easyui-numberbox" data-options="precision:2,min:0" style="width:100px; background-color:#CCCCCC" />10<sup>-6</sup></td>
			</tr>
			
		</table>
		<hr>
		<table style="paddeing-top:10px" cellspacing="5">
			<tr>
				<td></td>
				<td align="right">检测线：</td>
				<td align="left"><input id="lineId" name="lineId" style="width:120px"/></td>
				<td align="right">检测驾驶员：</td>
				<td><input name="inspecDriverId" id="inspecDriverId" class="easyui-combobox" style="width:120px" /></td>
				<td align="right">检测操作员：</td>
				<td><input name="inspecOperatorId" id="inspecOperatorId" class="easyui-combobox" style="width:120px"/></td>
			</tr>
		</table>
	</form>
</div>
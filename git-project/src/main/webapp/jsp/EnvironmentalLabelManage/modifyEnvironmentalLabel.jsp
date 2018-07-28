<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false,title:'修改环保标志发放记录'" style="overflow: hidden;" align="left">
		<form id="addEnvironmentalLabelCollarInfo">
		<input name="id" id="id"  type="hidden"/>
		<table style="paddeing-top:10px" cellspacing="5">
			<tr>
				<td align="right">标志编号：</td>
				<td align="left"><input id="labelId" name="labelId" style="width:120px;background-color:#CCCCCC" readOnly/></td>
				<td align="right">车牌号：</td>
				<td align="left"><input name="licence" id="licence" style="width:120px;background-color:#CCCCCC" readOnly/></td>
				
				<td align="right">标志类型</td>
			    <td align="left"><input id="labelType" name="labelType"  style="width:120px" readOnly/>
					
			</tr>
			<tr>
				<td align="right">检测站：</td>
				<td align="left"><input name="stationName" id="stationName" style="width:120px" readOnly/></td>			
				<td align="right">车主姓名：</td>
				<td align="left"><input id="vehicleOwner" name="vehicleOwner"  style="width:120px;background-color:#CCCCCC"/></td>
			    <td align="right">车辆识别码：</td>
				<td align="left"><input name="vinNo" id="vinNo" style="width:120px" readOnly/>
			</tr>
			<tr>
				<td align="right">车辆型号：</td>
				<td align="left"><input name="vehicleModelCode" id="vehicleModelCode" style="width:120px" readOnly/></td>
				<td align="right">车辆品牌：</td>
				<td align="left"><input name="vehicleBrand" id="vehicleBrand" style="width:120px" readOnly/></td>
				<td align="right">车辆来源：</td>
				<td align="left"><input id="vehicleClassification" name="vehicleClassification"  style="width:120px" readOnly/></td>
			</tr>
			<tr>
				<td align="right">燃油类型：</td>
				<td align="left"><input id="fuelType" name="fuelType" style="width:120px" readOnly/></td>
				<td align="right">排放标准：</td>
				<td align="left"><input name="emissionStandard" id="emissionStandard"  style="width:120px" readOnly/></td>
				<td align="right">打印情况：</td>
				<td align="left">
				<select name="isPrint" id="isPrint" class="easyui-combobox" style="width:100px">
							<option value='已打印'>已打印</option>
							<option value='未打印'>未打印</option>
				</select></td>
				<td align="right">标志状态</td>
				<td align="left"><select name="isCancel" id="isCancel" class="easyui-combobox" style="width:100px">
						<option value='注销'>注销</option>
						<option value='正常'>正常</option>
				</select></td>
			</tr>
			<tr>
				<td align="right">核发日期：</td>
				<td align="left"><input name="issueDate" id="issueDate" class="easyui-datebox"  data-options="required:true,missingMessage:'请输入核发时间',editable:false" style="width:120px" disabled/>
				</td>
				<td align="right">有效期：</td>
				<td align="left"><input id="validPeriod" name="validPeriod" class="easyui-datebox"  data-options="required:true,missingMessage:'请输入有效期',editable:false" style="width:120px" disabled/>
				</td>
				<td align="right">车辆登记时间：</td>
				<td align="left"><input id="vehicleRegisterTime" name="vehicleRegisterTime" class="easyui-datebox" data-options="required:true,missingMessage:'请输入车辆登记时间',editable:false" style="width:120px" disabled/>
				</td>
				<td align="right">发证日期：</td>
				<td align="left"><input id="distributionCertTime" name="distributionCertTime" class="easyui-datebox" data-options="required:true,missingMessage:'请输入发证日期',editable:false" style="width:120px" disabled/>
				</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>

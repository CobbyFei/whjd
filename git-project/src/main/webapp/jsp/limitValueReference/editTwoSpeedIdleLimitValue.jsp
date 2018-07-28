<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div data-options="region:'center',border:false,title:'修改检测信息'"
	style="overflow: hidden;" align="left">
	<form id="frm_ZJ_modify">
		<input name="id" id="id" type="hidden" />
		<table id="ZJ_time" style="padding-top:10px" cellspacing="5">
				<tr>
					<td align="right" style="width：20%">生产时间自</td>
					<td align="left" style="width：30%"><input name="sDSMinRegisterTime" id="sDSMinRegisterTime" style="width:125px" class="easyui-datebox" data-options="required:true"/></td>
					<td align="center" style="width：20%">起，在</td>
					<td align="left" style="width：30%"><input name="sDSMaxRegisterTime" id="sDSMaxRegisterTime" style="width:125px" class="easyui-datebox" data-options="required:true"/></td>
					<td align="left" style="width：20%">之前的</td>
					<td align="left">
						<select name="vehicleDetailType"
						id="VehicleDetailType" class="easyui-combobox"
						data-options="editable:false" style="width:175px">
							<option value="1" selected>第一类轻型汽车</option>
							<option value="2">第二类轻型汽车</option>
							<option value="3">重型汽车</option>
						</select>
					</td>
					<td>汽车</td>
				</tr>
				<tr>
					<td align="right" style="width：20%">过量空气系数：</td>
					<td align="left" style="width：30%"><input name="sdsLambda" id="sdsLambda" style="width:125px" class="easyui-numberbox" data-options="required:true,min:0,precision:2"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width：20%">低怠速CO：</td>
					<td align="left" style="width：30%"><input name="sdsLCo" id="sdsLCo" style="width:125px" class="easyui-numberbox" data-options="required:true,min:0,precision:2"/></td>
					<td align="right" style="width：20%">低怠速HC：</td>
					<td align="left" style="width：30%"><input name="sdsLHc" id="sdsLHc" style="width:125px" class="easyui-numberbox" data-options="required:true,min:0,precision:2"/></td>
				</tr>
				<tr>
					<td align="right" style="width：20%">高怠速CO：</td>
					<td align="left" style="width：30%"><input name="sdsHCo" id="sdsHCo" style="width:125px" class="easyui-numberbox" data-options="required:true,min:0,precision:2"/></td>
					<td align="right" style="width：20%">高怠速HC：</td>
					<td align="left" style="width：30%"><input name="sdsHHc" id="sdsHHc" style="width:125px" class="easyui-numberbox" data-options="required:true,min:0,precision:2"/></td>
					
					
				</tr>
		</table>
	</form>
</div>
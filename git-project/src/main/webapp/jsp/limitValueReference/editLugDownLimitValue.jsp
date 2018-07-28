<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	
</script>

<div data-options="region:'center',border:false,title:'修改限值信息'"
	style="overflow: hidden;" align="left">
	<form id="frm_JZ_modify">
		<input name="id" id="id" type="hidden" />
		<table id="JZ_time" style="padding-top:10px" cellspacing="5">
			<tr>
				<td align="right">生产时间自</td>
				<td align="left"><input name="jZMinRegisterTime"
					id="JZMinRegisterTime" class="easyui-datebox" style="width:175px" />
				</td>
				<td align="center">起，在</td>
				<td align="left" style="width:30%"><input
					name="jZMaxRegisterTime" id="JZMaxRegisterTime"
					class="easyui-datebox" style="width:175px" /></td>
				<td align="center">之前生产的</td>
				<td align="left"><select name="vehicleDetailType"
					id="VehicleDetailType" class="easyui-combobox"
					data-options="editable:false" style="width:175px">
						<option value="1" selected>第一类轻型汽车</option>
						<option value="2">第二类轻型汽车</option>
						<option value="3">重型汽车</option>
				</select></td>
			</tr>
		</table>
		<table id="JZ" style="padding-top:10px" cellspacing="5">
			<tr>
				<td align="right">限值：</td>
				<td align="left"><input name="jzLimit" id="JZ_Limit"
					class="easyui-numberbox" data-options="min:0,precision:2"
					style="width:175px" />
				</td>
		</table>
	</form>
</div>

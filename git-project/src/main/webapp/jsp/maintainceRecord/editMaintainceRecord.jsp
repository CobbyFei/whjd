<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	$(function() {
		$("#UserId").combobox({
			required : true,
			editable : false,
			url : sy.bp() + '/manageUserAction!getSysUsers.action',
			mode : 'remote',
			valueField : 'userId',
			textField : 'userName'
		});
		$("#StrMaintainceTime").datebox({
			required : true,
			editable : false
		});
		$("#Measures").validatebox({
			required : true
		});
		$("#MaintainceOutcome").validatebox({
			required : true
		});
	});
</script>
<div data-options="region:'center',border:false,title:'修改检测设备维护记录'"
	style="overflow: hidden;" align="left">
	<form id="frm_add_record">
		<input name="stationName" id="StationName" type="hidden"
			value=${sessionScope.stationName } /> <input name="recordId"
			id="RecordId" type="hidden" /> <input name="status" id="Status"
			type="hidden">
		<table style="padding-top:10px" cellspacing="5">
			<tr>
				<td align="right">所属检测线：</td>
				<td align="left"><input name="lineName"
					id="LineName" style="width:175px" disabled /></td>
				<td align="right">设备名称：</td>
				<td align="left"><input name="deviceName"
					id="DeviceName" style="width:175px" disabled></td>
				<td align="right">维护人员：</td>
				<td align="left"><select name="userId" id="UserId"
					style="width:153px"></select></td>
			</tr>
			<tr>
				<td align="right">维护时间：</td>
				<td align="left"><input type="text" id="StrMaintainceTime"
					name="strMaintainceTime" style="width:177px" /></td>
				<td align="right">是否正常：</td>
				<td align="left"><select name="intIsNormal" id="IntIsNormal"
					style="width:175px"><option value='0'>正常</option>
						<option value='1'>不正常</option>
				</select></td>
			</tr>
			<tr>
				<td align="right">采取措施：</td>
				<td align="left" colspan="5"><textarea
						style="height:60px;font-size:12px" id="Measures" name="measures"
						cols="47" rows="2" maxlength="500"></textarea></td>
			</tr>
			<tr>
				<td align="right">维护结果：</td>
				<td align="left" colspan="5"><textarea
						style="height:60px;font-size:12px" id="MaintainceOutcome"
						name="maintainceOutcome" cols="47" rows="2" maxlength="500"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
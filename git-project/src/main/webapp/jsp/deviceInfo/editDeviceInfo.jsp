<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	$(function() {
		$("#DeviceName").validatebox({
			required : true
		});
		$("#Manufacturer").validatebox({
			required : true
		});
		$("#DeviceModel").validatebox({
			required : true
		});
		$('#LineName').combobox({
			required : true,
			editable : false,
			url : sy.bp() + '/detectionLineAction!getDetectionLineName.action',
			mode : 'remote',
			valueField : 'lineId',
			textField : 'lineName'
		});
	});
</script>
<div style="padding:5px">
	<form id="referenceMaterialsRecordForm" method="post"
		enctype="multipart/form-data">
		<input name="id" type="hidden" /> <input name="deviceStatus"
			id="DeviceStatus" type="hidden" />
		<table style="padding-top:10px" cellspacing="5">
			<tr>
				<td align="right">设备名称：</td>
				<td align="left"><input name="deviceName" id="DeviceName"
					style="width:175px" /></td>
				<td align="right">制造厂商：</td>
				<td align="left"><input name="manufacturer" id="Manufacturer"
					style="width:175px" /></td>
				<td align="right">型号规格：</td>
				<td align="left"><input name="deviceModel" id="DeviceModel"
					style="width:153px" /></td>
			</tr>
			<tr>
				<td align="right">所属检测线：</td>
				<td align="left"><select name="lineId" id="LineName"
					style="width:175px">
				</select></td>
				<td align="right">所属检测站：</td>
				<td align="left"><input name="stationName" id="StationName"
					style="width:153px" disabled="true" />
				</td>
			</tr>
			<tr>
				<td align="right">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
				<td align="left" colspan="5"><textarea
						style="height:60px;font-size:12px" id="Remarks" name="remarks"
						cols="47" rows="2" maxlength="500"></textarea></td>
			</tr>
		</table>
	</form>
</div>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>

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
		$("#LineId").combobox({
			required : true,
			editable : false,
			url : sy.bp() + '/detectionLineAction!getDetectionLineName.action',
			mode : 'remote',
			valueField : 'lineId',
			textField : 'lineName'
		});
		$('#CancelBtn').bind('click', function() {
			cancel();
		});
		$('#AddBtn').bind('click', function() {
			savereg();
		});
	});

	function cancel() {
		$('#frm_add_record').form('clear');
	}

	function savereg() {
		$('#frm_add_record').form('submit', {
			url : sy.bp() + '/deviceInfoAction!saveInfo.action',
			onSubmit : function() {
				//alert('onSubmit');
				return $('#frm_add_record').form('validate');
			},
			success : function(data) {
				var result = eval("(" + data + ")");
				if (result.success) {
					$.messager.alert('提示！', '添加成功', 'info');
				} else {
					$.messager.alert('添加失败！', result.msg, 'error');
				}
			}
		});
	}
</script>

</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false,title:'新增检测设备信息'"
		style="overflow: hidden;" align="left">
		<form id="frm_add_record">
			<input name="deviceStatus" type="hidden" value='0' />
			<table style="padding-top:10px" cellspacing="5">
				<tr>
					<td align="right">设备名称：</td>
					<td align="left"><input name="deviceName" id="DeviceName"
						style="width:175px" /></td>
					<td align="right">制造厂商：</td>
					<td align="left"><input name="manufacturer" id="Manufacturer"
						style="width:175px" /></td>
					<td align="right">型号：</td>
					<td align="left"><input name="deviceModel" id="DeviceModel"
						style="width:153px" /></td>
				</tr>
				<tr>
					<td align="right">所属检测站：</td>
					<td align="left"><input name="stationName" id="StationName"
						style="width:153px" disabled value=${sessionScope.stationName } />
					</td>
					<td align="right">所属检测线：</td>
					<td align="left"><select name="lineId" id="LineId"
						style="width:175px">
					</select></td>
				</tr>
				<tr>
					<td align="right">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					<td align="left" colspan="5"><textarea
							style="height:60px;font-size:12px" id="Remarks" name="remarks"
							cols="47" rows="2" maxlength="500"></textarea></td>
				</tr>
				<tr>
					<td></td>
					<td><a class="easyui-linkbutton" icon="icon-add" id="AddBtn"
						name="Add" href="javascript:void(0)">添加</a></td>
					<td></td>
					<td><a class="easyui-linkbutton" icon="icon-reload"
						id="CancelBtn" name="Refresh" href="javascript:void(0)">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

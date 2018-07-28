<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript" charset="utf-8">
	$(function() {
		$("#LineId")
				.combobox(
						{
							required : true,
							editable : false,
							url : sy.bp()
									+ '/detectionLineAction!getDetectionLineName.action',
							mode : 'remote',
							valueField : 'lineId',
							textField : 'lineName',
							onSelect : function(rec) {
								var url = sy.bp()
										+ '/deviceInfoAction!getDeviceInfo.action?lineId='
										+ rec.lineId;
								$("#DeviceId").combobox('reload', url);
							}
						});
		$("#DeviceId").combobox({
			required : true,
			editable : false,
			valueField : 'id',
			textField : 'deviceName'
		});
		$("#UserId").combobox({
			required : true,
			editable : false,
			url : sy.bp() + '/manageUserAction!getSysUsers.action',
			mode : 'remote',
			valueField : 'userId',
			textField : 'userName'
		});
		$("#StrMaintainceTime").datebox({
			required : true
		});
		$("#Measures").validatebox({
			required : true
		});
		$("#MaintainceOutcome").validatebox({
			required : true
		});
		$("#IntIsNormal").validatebox({
			required : true
		});
		$('#CancelBtn').bind('click', function() {
			cancel();
		});
		$('#AddBtn').bind('click', function() {
			savereg();
		});
	});

	function cancel() {
		$('#frm_add_record').form('reset');
	}

	function savereg() {
		$('#frm_add_record').form('submit', {
			url : sy.bp() + '/maintainceRecordAction!saveRecord.action',
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
	<div data-options="region:'center',border:false,title:'新增检测设备维护记录'"
		style="overflow: hidden;" align="left">
		<form id="frm_add_record">
			<input name="stationName" id="StationName" type="hidden"
				value=${sessionScope.stationName } /> <input name="status"
				id="Status" type="hidden" value='0'>
			<table style="padding-top:10px" cellspacing="5">
				<tr>
					<td align="right">所属检测线：</td>
					<td align="left"><select name="lineId" id="LineId"
						style="width:175px"></select></td>
					<td align="right">设备名称：</td>
					<td align="left"><select name="deviceId" id="DeviceId"
						style="width:175px"></select></td>
					<td align="right">维护人员：</td>
					<td align="left"><select name="userId" id="UserId"
						style="width:153px"></select></td>
				</tr>
				<tr>
					<td align="right">维护时间：</td>
					<td align="left"><input type="text" id="StrMaintainceTime"
						name="strMaintainceTime" style="width:177px" editable="false" />
					</td>
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

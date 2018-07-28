<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript" charset="utf-8">
	$(function() {
		var str='${sessionScope.stationName}';		
		if(str!="市局"){
			//alert(str);
			$("#inspectionStationId").combobox('setValue', str);
			$("#inspectionStationId").combobox({
				disabled:true
			});
		}else{
		$("#inspectionStationId").combobox({
			url : sy.bp() + '/inspectionStationAction!getInspectionStationName.action',
			mode : 'remote',
			delay : 500,
			valueField : 'stationId',
			required : true,
			textField : 'stationName',
		});
		}
		$('#CancelBtn').bind('click', function() {
			cancel();
		});
		$('#AddBtn').bind('click', function() {
			savereg();
		});
		
	});
	
	function cancel() {
		$('#addDevicePurchaseInfo').form('clear');
	}
	 
	function savereg() {
		$('#addDevicePurchaseInfo').form('submit', {
			url : sy.bp() + '/devicePurchaseRecordAction!addDevicePurchaseRecord.action?userStationName=${sessionScope.stationName}',
			onSubmit : function() {
				//alert('onSubmit');
				if ($('#deviceName').val() == '') {
					$.messager.alert('提示', '请输入检测设备名称！', 'info');
					return false;
				}
				
				return $('#addDevicePurchaseInfo').form('validate');
			},
			success : function(data) {
				var result = eval("(" + data + ")");
				if (result.success) {
					$.messager.alert('提示！', '添加成功', 'info');
					cancel();
				} else {
					$.messager.alert('添加失败！', result.msg, 'error');
				}
			}
		});
	}
	
</script>

</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false,title:'新增设备采购记录'" style="overflow: hidden;" align="left">
		<form id="addDevicePurchaseInfo">
			<table style="width:700px; height:400px; padding-top:10px" cellspacing="5">
				<tr height="30px">
					<td align="right" style="width：20%">检测设备名称：</td>
					<td align="left" style="width：30%"><input name="deviceName" id="deviceName" style="width:175px" maxlength="20"/></td>
					<td align="right" style="width：20%">制造厂商：</td>
					<td align="left" style="width：30%"><input name="manufacturer" id="manufacturer" style="width:175px" maxlength="40"/></td>
				</tr>
				<tr height="30px">
				    <td align="right">型号：</td>
					<td align="left"><input name="deviceModel" id="deviceModel" style="width:175px" maxlength="20"/></td>
					<td align="right">规格：</td>
					<td align="left"><input name="specification" id="specification" style="width:175px" maxlength="10"/></td>
				</tr>
				<tr height="30px">
					<td align="right">采购时间：</td>
					<td align="left"><input type="text" id="selectPurchaseTime" name="selectPurchaseTime" class="easyui-datebox" style="width:177px"
						required="true" editable="false"/></td>
				<td align="right">采购数量：</td>
					<td align="left"><input name="purchaseNum" id="purchaseNum" class="easyui-numberbox" data-options="value:0,min:0,groupSeparator:','" style="width:153px" /></td>
				</tr>
				<tr height="30px">
					<td align="right">所属检测站：</td>
					<td align="left"><!--  <select id="stationName" name="sName" class="easyui-combobox" style="width:177px" required="true">
							
					</select>-->
					<input name="inspectionStationId" id="inspectionStationId" class="easyui-combobox" style="width:175px" />
					</td>
				</tr>
				<tr height="30px">
					<td align="right">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					<td align="left" colspan="3">
					<textarea style="height:60px;font-size:12px" id="remarks" name="remarks" cols="47" rows="2" maxlength="50"></textarea></td>
				</tr>
				<tr height="50px">
					<td></td>
					<td><a class="easyui-linkbutton" icon="icon-add" id="AddBtn" name="Add" href="javascript:void(0)">添加</a></td>
					<td></td>
					<td><a class="easyui-linkbutton" icon="icon-reload" id="CancelBtn" name="Refresh" href="javascript:void(0)">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

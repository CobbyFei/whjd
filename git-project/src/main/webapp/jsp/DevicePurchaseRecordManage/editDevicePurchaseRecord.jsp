<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript" charset="utf-8">
/*	$(function() {	
		$('#CancelBtn').bind('click', function() {
			cancel();
		});
		$('#EditBtn').bind('click', function() {
			savereg();
		});
		
	});
	
	function cancel() {
		$('#DevicePurchaseinfo').form('clear');
	}
	
	function savereg() {
		$('#updateDevicePurchaseInfo').form('submit', {
			url : sy.bp() + '/devicePurchaseRecordAction!updateDevicePurchaseRecord.action',
			onSubmit : function() {
				
				if ($('#deviceName').val() == '') {
					$.messager.alert('提示', '请输入检测设备名称！', 'info');
					return false;
				}
				
				return $('#updateDevicePurchaseInfo').form('validate');
			},
			success : function(data) {
				var result = eval("(" + data + ")");
				if (result.success) {
					$.messager.alert('提示！', '编辑成功', 'info');
				} else {
					$.messager.alert('编辑失败！', result.msg, 'error');
				}
			}
		});
	}
	*/
</script>

</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false,title:'编辑设备采购记录'" style="overflow: hidden;" align="left">
		<form id="updateDevicePurchaseInfo">
			<table style="width:600px; height:280px; padding-top:10px" cellspacing="5">
			<tr><td align="left"><input name="recordId" id="recordId" type="hidden"/></td></tr>
				<tr height="30px">
					<td align="right" style="width：20%">检测设备名称：</td>
					<td align="left" style="width：20%"><input name="deviceName" id="deviceName" style="width:175px" /></td>
					<td align="right" style="width：20%">制造厂商：</td>
					<td align="left" style="width：20%"><input name="manufacturer" id="manufacturer" style="width:175px" /></td>
				</tr>
				<tr height="30px">
				    <td align="right">型号：</td>
					<td align="left"><input name="deviceModel" id="deviceModel" style="width:175px" /></td>
					<td align="right">规格：</td>
					<td align="left"><input name="specification" id="specification" style="width:175px" /></td>
				</tr>
				<tr height="30px">
					<td align="right">采购时间：</td>
					<td align="left"><input type="text" id="selectPurchaseTime" name="selectPurchaseTime" class="easyui-datebox" style="width:177px" required="true" editable="false"/></td>
				<td align="right">采购数量：</td>
					<td align="left"><input name="purchaseNum" id="purchaseNum" class="easyui-numberbox" data-options="value:0,min:0,groupSeparator:','" style="width:153px" /></td>
				</tr>
				<tr height="30px">
					<td align="right">所属检测站：</td>
					<td align="left">
					<input name="stationName" id="stationName" style="width:175px" readonly/>
					</td>
				</tr>
				<tr height="30px">
					<td align="right">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					<td align="left" colspan="3"><textarea style="height:60px;font-size:12px" id="remarks" name="remarks"
							cols="47" rows="2" maxlength="500"></textarea></td>
				</tr>
	
			</table>
		</form>
	</div>
</body>
</html>

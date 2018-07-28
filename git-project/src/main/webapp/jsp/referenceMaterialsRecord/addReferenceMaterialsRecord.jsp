<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript" charset="utf-8">
	$(function() {
		$("#MaterialName").validatebox({
			required : true
		});
		$("#Manufacturer").validatebox({
			required : true
		});
		$("#Model").validatebox({
			required : true
		});
		$("#Specification").validatebox({
			required : true
		});
		$("#PurchaseNum").numberbox({
			value : 0,
			min : 0,
			groupSeparator : ',',
			required : true
		});
		$("#PurchaseTime").datebox({
			required : true,
			editable : false
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
		$('#frm_add_record')
				.form(
						'submit',
						{
							url : sy.bp()
									+ '/referenceMaterialsRecordAction!saveRecord.action',
							onSubmit : function() {
								//alert('onSubmit');
								return $('#frm_add_record').form('validate');
							},
							success : function(data) {
								var result = eval("(" + data + ")");
								if (result.success) {
									$.messager.alert('提示！', '添加成功', 'info');
								} else {
									$.messager.alert('添加失败！', result.msg,
											'error');
								}
							}
						});
		$('#frm_add_record').form('reset');
	}
</script>

</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false,title:'新增标准物质采购记录'"
		style="overflow: hidden;" align="left">
		<form id="frm_add_record">
			<input name="stationName" id="StationName" type="hidden"
				value=${sessionScope.stationName } /> <input name="status"
				id="Status" type="hidden" value='0'>
			<table style="padding-top:10px" cellspacing="5">
				<tr>
					<td align="right">标准物质名称：</td>
					<td align="left"><input name="materialName" id="MaterialName"
						style="width:175px" />
					</td>
					<td align="right">制造厂商：</td>
					<td align="left"><input name="manufacturer" id="Manufacturer"
						style="width:175px" />
					</td>
					<td align="right">型号：</td>
					<td align="left"><input name="model" id="Model"
						style="width:153px" />
					</td>
				</tr>
				<tr>
					<td align="right">规格：</td>
					<td align="left"><input name="specification"
						id="Specification" style="width:175px" />
					</td>
					<td align="right">采购数量：</td>
					<td align="left"><input name="purchaseNum" id="PurchaseNum"
						style="width:175px" />
					</td>
					<td align="right">采购时间：</td>
					<td align="left"><input type="text" id="PurchaseTime"
						name="strPurchaseTime" style="width:177px" />
					</td>
				</tr>
				<tr>
					<td align="right">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					<td align="left" colspan="5"><textarea
							style="height:60px;font-size:12px" id="Remarks" name="remarks"
							cols="47" rows="2" maxlength="500"></textarea>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><a class="easyui-linkbutton" icon="icon-add" id="AddBtn"
						name="Add" href="javascript:void(0)">添加</a>
					</td>
					<td></td>
					<td><a class="easyui-linkbutton" icon="icon-reload"
						id="CancelBtn" name="Refresh" href="javascript:void(0)">重置</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

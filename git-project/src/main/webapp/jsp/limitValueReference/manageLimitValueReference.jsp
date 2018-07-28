<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript" charset="utf-8">
	$(function() {
		$("#DetectionMedhodName")
				.combobox(
						{
							required : true,
							editable : false,
							url : sy.bp()
									+ '/detectionMethodReferenceAction!getDetectionMethodList.action',
							mode : 'remote',
							valueField : 'id',
							textField : 'detectionMedhodName',
							onSelect : function(rec) {
								if (rec.detectionMedhodName == "稳态工况法") {
									$("#WT_div").css("display", "");
									$("#ZJ_div").css("display", "none");
									$("#SDS_div").css("display", "none");
									$("#JZ_div").css("display", "none");
									$("#WTName").val(rec.id);
									var url = sy.bp()
											+ '/limitValueReferenceAction!getReferenceValues.action?detectionMethodId='
											+ rec.id;
									$("#frm_WT_modify").form('load', url);
								}
								if (rec.detectionMedhodName == "自由加速法") {
									$("#WT_div").css("display", "none");
									$("#ZJ_div").css("display", "");
									$("#SDS_div").css("display", "none");
									$("#JZ_div").css("display", "none");
									$("#ZJName").val(rec.id);
									var url = sy.bp()
											+ '/limitValueReferenceAction!getReferenceValues.action?detectionMethodId='
											+ rec.id;
									$("#frm_ZJ_modify").form('load', url);
								}
								if (rec.detectionMedhodName == "双怠速法") {
									$("#WT_div").css("display", "none");
									$("#ZJ_div").css("display", "none");
									$("#SDS_div").css("display", "");
									$("#JZ_div").css("display", "none");
									$("#SDSName").val(rec.id);
									var url = sy.bp()
											+ '/limitValueReferenceAction!getReferenceValues.action?detectionMethodId='
											+ rec.id;
									$("#frm_SDS_modify").form('load', url);
								}
								if (rec.detectionMedhodName == "加载减速法") {
									$("#WT_div").css("display", "none");
									$("#ZJ_div").css("display", "none");
									$("#SDS_div").css("display", "none");
									$("#JZ_div").css("display", "");
									$("#JZName").val(rec.id);
									var url = sy.bp()
											+ '/limitValueReferenceAction!getReferenceValues.action?detectionMethodId='
											+ rec.id;
									$("#frm_JZ_modify").form('load', url);
								}
							}
						});
		//稳态工况法
		$("#WT_HC_ASM5025").numberbox({
			value : 0,
			min : 0,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$("#WT_CO_ASM5025").numberbox({
			value : 0,
			min : 0,
			max : 100,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$("#WT_NO_ASM5025").numberbox({
			value : 0,
			min : 0,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$("#WT_HC_ASM2540").numberbox({
			value : 0,
			min : 0,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$("#WT_CO_ASM2540").numberbox({
			value : 0,
			min : 0,
			max : 100,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$("#WT_NO_ASM2540").numberbox({
			value : 0,
			min : 0,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$('#AddBtnWT').bind('click', function() {
			saveregWT();
		});
		//自由加速法
		$("#ZJ_Limit").numberbox({
			value : 0,
			min : 0,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$('#AddBtnZJ').bind('click', function() {
			saveregZJ();
		});
		//双怠速法
		$("#SDS_L_CO").numberbox({
			value : 0,
			min : 0,
			max : 100,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$("#SDS_L_HC").numberbox({
			value : 0,
			min : 0,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$("#SDS_lambda").numberbox({
			value : 0,
			min : 0,
			max : 10,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$("#SDS_H_CO").numberbox({
			value : 0,
			min : 0,
			max : 100,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$("#SDS_H_HC").numberbox({
			value : 0,
			min : 0,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$('#AddBtnSDS').bind('click', function() {
			saveregSDS();
		});
		//加载减速法
		$("#JZ_Limit").numberbox({
			value : 0,
			min : 0,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$("#JZ_MaxRpm").numberbox({
			value : 0,
			min : 0,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$('#AddBtnJZ').bind('click', function() {
			saveregJZ();
		});
	});
	//稳态工况法
	function saveregWT() {
		$('#frm_WT_modify').form('submit', {
			url : sy.bp() + '/limitValueReferenceAction!saveReference.action',
			onSubmit : function() {
				return $('#frm_WT_modify').form('validate');
			},
			success : function(data) {
				var result = eval("(" + data + ")");
				if (result.success) {
					$.messager.alert('提示！', '保存成功', 'info');
				} else {
					$.messager.alert('保存失败！', result.msg, 'error');
				}
			}
		});
	}
	//自由加速法
	function saveregZJ() {
		$('#frm_ZJ_modify').form('submit', {
			url : sy.bp() + '/limitValueReferenceAction!saveReference.action',
			onSubmit : function() {
				return $('#frm_ZJ_modify').form('validate');
			},
			success : function(data) {
				var result = eval("(" + data + ")");
				if (result.success) {
					$.messager.alert('提示！', '保存成功', 'info');
				} else {
					$.messager.alert('保存失败！', result.msg, 'error');
				}
			}
		});
	}
	//双怠速法
	function saveregSDS() {
		$('#frm_SDS_modify').form('submit', {
			url : sy.bp() + '/limitValueReferenceAction!saveReference.action',
			onSubmit : function() {
				return $('#frm_SDS_modify').form('validate');
			},
			success : function(data) {
				var result = eval("(" + data + ")");
				if (result.success) {
					$.messager.alert('提示！', '保存成功', 'info');
				} else {
					$.messager.alert('保存失败！', result.msg, 'error');
				}
			}
		});
	}
	//加载减速法
	function saveregJZ() {
		$('#frm_JZ_modify').form('submit', {
			url : sy.bp() + '/limitValueReferenceAction!saveReference.action',
			onSubmit : function() {
				return $('#frm_JZ_modify').form('validate');
			},
			success : function(data) {
				var result = eval("(" + data + ")");
				if (result.success) {
					$.messager.alert('提示！', '保存成功', 'info');
				} else {
					$.messager.alert('保存失败！', result.msg, 'error');
				}
			}
		});
	}
</script>

</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false,title:'修改检测方法限值'"
		style="overflow: hidden;" align="left">
		<table id="WT" style="padding-top:10px" cellspacing="5">
			<tr>
				<td align="right" style="width：20%">检测方法：</td>
				<td align="right" style="width：20%"><select
					name="detectionMedhodName" id="DetectionMedhodName"
					style="width:175px"></select>
				</td>
			</tr>
		</table>
		<div id="WT_div" style="display:none">
			<form id="frm_WT_modify">
				<input name="detectionMethodId" id="WTName" type="hidden" /> <input
					name="id" id="Id" type="hidden" />
				<table id="WT" style="padding-top:10px" cellspacing="5">
					<tr>
						<td align="right" style="width：20%">ASM5025：</td>
						<td align="right" style="width：20%">HC：</td>
						<td align="left" style="width：30%"><input name="wtHcAsm5025"
							id="WT_HC_ASM5025" style="width:175px" />
						</td>
						<td align="right" style="width：20%">CO：</td>
						<td align="left" style="width：30%"><input name="wtCoAsm5025"
							id="WT_CO_ASM5025" style="width:175px" />
						</td>
						<td align="right">NO：</td>
						<td align="left"><input name="wtNoAsm5025" id="WT_NO_ASM5025"
							style="width:153px" />
						</td>
					</tr>
					<tr>
						<td align="right" style="width：20%">ASM2540：</td>
						<td align="right">HC：</td>
						<td align="left"><input type="text" id="WT_HC_ASM2540"
							name="wtHcAsm2540" style="width:175px" />
						</td>
						<td align="right" style="width：20%">CO：</td>
						<td align="left" style="width：30%"><input name="wtCoAsm2540"
							id="WT_CO_ASM2540" style="width:175px" />
						</td>
						<td align="right">NO：</td>
						<td align="left"><input name="wtNoAsm2540" id="WT_NO_ASM2540"
							style="width:153px" />
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><a class="easyui-linkbutton" icon="icon-add"
							id="AddBtnWT" name="Add" href="javascript:void(0)">保存</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="ZJ_div" style="display:none">
			<form id="frm_ZJ_modify">
				<input name="detectionMethodId" id="ZJName" type="hidden" /> <input
					name="id" id="Id" type="hidden" />
				<table id="ZJ" style="padding-top:10px" cellspacing="5">
					<tr>
						<td align="right" style="width：20%">限值：</td>
						<td align="left" style="width：30%"><input name="zjLimit"
							id="ZJ_Limit" style="width:175px" />
						</td>
						<td><a class="easyui-linkbutton" icon="icon-add"
							id="AddBtnZJ" name="Add" href="javascript:void(0)">保存</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="SDS_div" style="display:none">
			<form id="frm_SDS_modify">
				<input name="detectionMethodId" id="SDSName" type="hidden" /> <input
					name="id" id="Id" type="hidden" />
				<table id="SDS" style="padding-top:10px" cellspacing="5">
					<tr>
						<td align="right" style="width：20%">低怠速：</td>
						<td align="right" style="width：20%">CO：</td>
						<td align="left" style="width：30%"><input name="sdsLCo"
							id="SDS_L_CO" style="width:175px" />
						</td>
						<td align="right" style="width：20%">HC：</td>
						<td align="left" style="width：30%"><input name="sdsLHc"
							id="SDS_L_HC" style="width:175px" />
						</td>
						<td align="right">过量空气系数λ：</td>
						<td align="left"><input name="sdsLambda" id="SDS_lambda"
							style="width:153px" />
						</td>
					</tr>
					<tr>
						<td align="right" style="width：20%">高怠速：</td>
						<td align="right">CO：</td>
						<td align="left"><input type="text" id="SDS_H_CO"
							name="sdsHCo" style="width:175px" />
						</td>
						<td align="right" style="width：20%">HC：</td>
						<td align="left" style="width：30%"><input name="sdsHHc"
							id="SDS_H_HC" style="width:175px" />
						</td>
						<td></td>
						<td align="right"><a class="easyui-linkbutton"
							icon="icon-add" id="AddBtnSDS" name="Add"
							href="javascript:void(0)">保存</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="JZ_div" style="display:none">
			<form id="frm_JZ_modify">
				<input name="detectionMethodId" id="JZName" type="hidden" /> <input
					name="id" id="Id" type="hidden" />
				<table id="JZ" style="padding-top:10px" cellspacing="5">
					<tr>
						<td align="right" style="width：20%">限值：</td>
						<td align="left" style="width：30%"><input name="jzLimit"
							id="JZ_Limit" style="width:175px" />
						</td>
						<td align="right" style="width：20%">实测最大轮边功率：</td>
						<td align="left" style="width：30%"><input name="jzMaxRpm"
							id="JZ_MaxRpm" style="width:175px" />
						</td>
						<td><a class="easyui-linkbutton" icon="icon-add"
							id="AddBtnJZ" name="Add" href="javascript:void(0)">保存</a></td>
				</table>
			</form>
		</div>
	</div>
</body>
</html>

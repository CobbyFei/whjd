<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		datagrid = $('#datagrid')
				.datagrid(
						{
							url : sy.bp()
									+ '/steadyStateLimitValueReferenceAction!getLimitValues.action',
							title : '稳态工况法限值信息',
							iconCls : 'icon-save',
							pagination : true,
							pagePosition : 'bottom',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40 ],
							fit : true,
							fitColumns : false,
							nowrap : false,
							border : false,
							idField : 'id',
							sortName : 'id',
							sortOrder : 'asc',
							checkOnSelect : false,
							selectOnCheck : true,
							frozenColumns : [ [ {
								title : '编号',
								field : 'id',
								width : 150,
								sortable : true,
								checkbox : true
							} ] ],
							columns : [ [ {
								title : '起始生产时间（包含）',
								field : 'wTMinRegisterTime',
								width : 150
							}, {
								title : '截止生产时间（不包含）',
								field : 'wTMaxRegisterTime',
								width : 150
							}, {
								title : '起始基准质量（不包含）',
								field : 'wTMinBaseQuality',
								width : 150
							}, {
								title : '终止基准质量（包含）',
								field : 'wTMaxBaseQuality',
								width : 130
							}, {
								title : '车辆分类',
								field : 'vehicleDetailType',
								width : 80,
								formatter : function(value, rowData, rowIndex){
									if(value == 1){		
										return "<span>"+"第一类轻型汽车"+"</span>";
									}else if (value == 2){ //单位注销
										return "<span>"+"第二类轻型汽车"+"</span>";
									}
								}
							}, {
								title : 'HC ASM5025',
								field : 'wtHcAsm5025',
								width : 100
							}, {
								title : 'CO ASM5025',
								field : 'wtCoAsm5025',
								width : 100
							}, {
								title : 'NO ASM5025',
								field : 'wtNoAsm5025',
								width : 100
							} , {
								title : 'HC ASM2540',
								field : 'wtHcAsm2540',
								width : 100
							}, {
								title : 'CO ASM2540',
								field : 'wtCoAsm2540',
								width : 100
							}, {
								title : 'NO ASM2540',
								field : 'wtNoAsm2540',
								width : 100
							} ] ],
							toolbar : [ {
								text : '删除',
								iconCls : 'icon-remove',
								handler : function() {
									remove();
								}
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									edit();
								}
							}, '-',{
								text : '刷新',
								iconCls : 'icon-reload',
								handler : function() {
									datagrid.datagrid('reload');
								}
							}, '-', {
								text : '取消选中',
								iconCls : 'icon-undo',
								handler : function() {
									cancelSelect(datagrid);
								}
							}, '-' ],
							onRowContextMenu : function(e, rowIndex, rowData) {
								e.preventDefault();
								$('#menu').menu('show', {
									left : e.pageX,
									top : e.pageY
								});
							},
							onClickRow : function(rowIndex, rowData) {
								var flag = true;
								var rows = datagrid.datagrid('getChecked');
								if (rows != undefined && rows != null
										&& rows.length > 0) {
									for ( var i = 0; i < rows.length; i++) {
										if (rows[i].id == rowData.id) {
											flag = false;
											break;
										}
									}
									if (flag == false) {
										datagrid.datagrid('unselectRow',
												rowIndex);
										datagrid.datagrid('uncheckRow',
												rowIndex);
									} else {
										datagrid
												.datagrid('selectRow', rowIndex);
										datagrid.datagrid('checkRow', rowIndex);
									}
								} else {
									datagrid.datagrid('checkRow', rowIndex);
								}
							}
						});
						
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
		$("#WTMinRegisterTime").datebox({
			required : true
		});
		$("#WTMaxRegisterTime").datebox({
			required : true
		});
		$("#WTMinBaseQuality").numberbox({
			value : 0,
			min : 0,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$("#WTMaxBaseQuality").numberbox({
			value : 0,
			min : 0,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$('#AddBtnWT').bind('click', function() {
			saveregWT();
		});
	});
	
	function edit() {
		var rows = datagrid.datagrid('getChecked');
		if(rows.length == 1){
			var p = parent.sy.dialog({
				title : '修改限值信息',
				href : sy.bp()
								+ '/steadyStateLimitValueReferenceAction!limitEdit.action',
				width : 900,
				height : 600,
				resizable : true,
				buttons : [ {
					text : '修改',
					handler : function() {
						var f = p.find('#frm_WT_modify');
						f.form('submit', {
							url : sy.bp()+'/steadyStateLimitValueReferenceAction!editLimit.action',
							dataType:'json',
							success : function(d) {
								var json = $.parseJSON(d);
								if (json.success) {
									cancelSelect(datagrid);
									datagrid.datagrid('reload'); // 完成之后刷新列表
									p.dialog('close');
								}
								parent.sy.messagerShow({
									msg : json.msg,
									title : '提示'
								});
							}
						});
					}
				} ],
				
				onLoad : function() {
					var f = p.find('form');
					f.form('load', {
					
						'id' : rows[0].id,
						'wTMinRegisterTime' : rows[0].wTMinRegisterTime,
						'wTMaxRegisterTime' : rows[0].wTMaxRegisterTime,
						'wTMinBaseQuality' : rows[0].wTMinBaseQuality,
						'wTMaxBaseQuality' : rows[0].wTMaxBaseQuality,
						'vehicleDetailType' : rows[0].vehicleDetailType,
						
						'wtHcAsm5025' : rows[0].wtHcAsm5025,
						'wtCoAsm5025' : rows[0].wtCoAsm5025,
						'wtNoAsm5025' : rows[0].wtNoAsm5025,
						
						'wtHcAsm2540' : rows[0].wtHcAsm2540,
						'wtCoAsm2540' : rows[0].wtCoAsm2540,
						'wtNoAsm2540' : rows[0].wtNoAsm2540,
						
					});
					
				}
			});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示', '同一时间只能编辑一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示', '请选择要编辑的记录！', 'error');
		}
	}
	
	function remove() {
		var rows = datagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy
					.messagerConfirm(
							'请确认',
							'确定要删除所选记录？（您可能需要修改其他限值信息的起止时间）',
							function(r) {
								if (r) {
									for ( var i = 0; i < rows.length; i++) {
										ids.push(rows[i].id);
									}
									if (ids.length == 0) {
										$.messager.show('提示', '所选限值信息均已删除',
												'error');
										return;
									}
									$
											.ajax({
												url : sy.bp()
														+ '/steadyStateLimitValueReferenceAction!deleteLimitValue.action',
												data : {
													ids : ids.join(',')
												},
												dataType : 'json',
												success : function(d) {
													cancelSelect(datagrid);
													datagrid.datagrid('load');
													parent.sy.messagerShow({
														title : '提示',
														msg : d.msg
													});
												}
											});
								}
							});
		} else {
			parent.sy.messagerAlert('提示', '请选择要删除的记录！', 'error');
		}
	}
	
	function saveregWT() {
		$('#frm_WT_modify')
				.form(
						'submit',
						{
							url : sy.bp()
									+ '/steadyStateLimitValueReferenceAction!saveLimitValue.action',
							onSubmit : function() {
								return $('#frm_WT_modify').form('validate');
							},
							success : function(data) {
								var result = eval("(" + data + ")");
								if (result.success) {
									$.messager.alert('提示！', '添加成功', 'info');
									datagrid.datagrid('load', {});
								} else {
									$.messager.alert('添加失败！', result.msg,
											'error');
								}
							}
						});
	}

	//search是保留的字符匹配方法
	function searchSth() {
		datagrid.datagrid('load', sy.serializeObject($('#searchForm')));
	}
	function clean() {
		datagrid.datagrid('load', {});
		$('#searchForm').form('clear');
		$('#DeviceStatus').val('2');
	}
	function cancelSelect(datagrid) {
		datagrid.datagrid('clearSelections');
		datagrid.datagrid('clearChecked');
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'添加稳态工况法限值'"
		style="overflow:hidden;height:180px" align="left">
		<form id="frm_WT_modify">
			<input name="detectionMethodName" id="WTName" type="hidden"
				value="稳态工况法" />
			<table id="WT_time" style="padding-top:10px" cellspacing="5">
				<tr>
					<td align="right" style="width：20%">生产时间自</td>
					<td align="left" style="width：30%"><input
						name="wTMinRegisterTime" id="WTMinRegisterTime"
						style="width:175px" /></td>
					<td align="center" style="width：20%">（包含）起，  在</td>
					<td align="left" style="width：30%"><input
						name="wTMaxRegisterTime" id="WTMaxRegisterTime"
						style="width:175px" /></td>
					<td align="left" style="width：20%">（不含）之前生产的</td>
					
					<td align="left" style="width：30%"><select name="vehicleDetailType"
						id="vehicleDetailType" style="width:125px">
							<option value="1">第一类轻型汽车</option>
							<option value="2">第二类轻型汽车</option>
					</select></td>
					
				</tr>
				<tr>
					<td align="right" style="width：20%">基准质量自</td>
					<td align="left" style="width：30%"><input
						name="wTMinBaseQuality" id="WTMinBaseQuality"
						style="width:175px" /></td>
					<td align="center" style="width：20%">（不含）起，到</td>
					<td align="left" style="width：30%"><input
						name="wTMaxBaseQuality" id="WTMaxBaseQuality"
						style="width:175px" /></td>
					<td align="left" style="width：20%">（包含）止</td>
				</tr>
				<tr>
					
					<td align="right" style="width：20%">HC ASM5025：</td>
					<td align="left" style="width：30%"><input name="wtHcAsm5025"
						id="WT_HC_ASM5025" style="width:175px" /></td>
					<td align="right" style="width：20%">CO ASM5025：</td>
					<td align="left" style="width：30%"><input name="wtCoAsm5025"
						id="WT_CO_ASM5025" style="width:175px" /></td>
					<td align="right" style="width：20%">NO ASM5025：</td>
					<td align="left" style="width：30%"><input name="wtNoAsm5025"
						id="WT_NO_ASM5025" style="width:175px" /></td>
				</tr>
				<tr>
					
					<td align="right" style="width：20%">HC ASM2540：</td>
					<td align="left" style="width：30%"><input name="wtHcAsm2540"
						id="WT_HC_ASM2540" style="width:175px" /></td>
					<td align="right" style="width：20%">CO ASM2540：</td>
					<td align="left" style="width：30%"><input name="wtCoAsm2540"
						id="WT_CO_ASM2540" style="width:175px" /></td>
					<td align="right" style="width：20%">NO ASM2540：</td>
					<td align="left" style="width：30%"><input name="wtNoAsm2540"
						id="WT_NO_ASM2540" style="width:175px" /></td>
					<td align="right"><a class="easyui-linkbutton" icon="icon-add"
						id="AddBtnWT" name="Add" href="javascript:void(0)">添加</a>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow:hidden">
		<table id="datagrid"></table>
	</div>
	<div id="menu" class="easyui-menu" style="width:120px;display:none">
		<div onclick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
		<div onclick="remove()" data-options="iconCls:'icon-remove'">删除</div>
	</div>
</body>
</html>

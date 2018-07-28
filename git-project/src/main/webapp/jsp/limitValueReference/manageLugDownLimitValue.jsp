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
									+ '/lugDownLimitValueAction!getAllLimitValues.action',
							title : '加载减速法限值信息',
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
								field : 'jZMinRegisterTime',
								width : 150
							}, {
								title : '截止生产时间（不包含）',
								field : 'jZMaxRegisterTime',
								width : 150
							}, {
								title : '车辆分类',
								field : 'vehicleDetailType',
								width : 150,
								formatter : function(value, rowData, rowIndex) {
									if (value == 1) {
										return "第一类轻型汽车";
									}
									if (value == 2) {
										return "第二类轻型汽车";
									}
									if (value == 3) {
										return "重型汽车";
									}
								}
							}, {
								title : '限值',
								field : 'jzLimit',
								width : 80
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
		$("#JZ_Limit").numberbox({
			value : 0,
			min : 0,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$("#JZMinRegisterTime").datebox({
			required : true,
			editable : false
		});
		$("#JZMaxRegisterTime").datebox({
			required : true,
			editable : false
		});
		$('#AddBtnJZ').bind('click', function() {
			saveregJZ();
		});
	});
	function saveregJZ() {
		$('#frm_JZ_modify')
				.form(
						'submit',
						{
							url : sy.bp()
									+ '/lugDownLimitValueAction!addLugDownLimitValue.action',
							onSubmit : function() {
								var minTime = new Date(Date.parse($(
										"#JZMinRegisterTime").datebox(
										'getValue')));
								var maxTime = new Date(Date.parse($(
										"#JZMaxRegisterTime").datebox(
										'getValue')));
								if (minTime > maxTime) {
									$.messager.alert('提示', '起始时间不得晚于终止时间！',
											'error');
									return false;
								}
								return $('#frm_JZ_modify').form('validate');
							},
							success : function(data) {
								var result = eval("(" + data + ")");
								if (result.success) {
									$.messager.alert('提示！', '添加加载减速法限值成功',
											'info');
									$('#datagrid').datagrid('load', {});
								} else {
									$.messager.alert('添加失败！', result.msg,
											'error');
								}
							}
						});
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
														+ '/lugDownLimitValueAction!deleteLimitValues.action',
												data : {
													ids : ids.join(',')
												},
												dataType : 'json',
												success : function(d) {
													if (d.success) {
														cancelSelect(datagrid);
														datagrid
																.datagrid('load');
														$.messager.alert('提示',
																'删除加载减速法限值成功!',
																'info');
													} else {
														$.messager.alert('提示',
																d.msg, 'error');
													}

												}
											});
								}
							});
		} else {
			parent.sy.messagerAlert('提示', '请选择要删除的记录！', 'error');
		}
	}
	function edit() {
		var rows = datagrid.datagrid('getChecked');
		if (rows.length == 1) {
			var p = parent.sy
					.dialog({
						title : '修改限值信息',
						href : sy.bp() + '/lugDownLimitValueAction!edit.action',
						width : 800,
						height : 300,
						resizable : true,
						buttons : [ {
							text : '修改',
							handler : function() {
								var f = p.find('form');
								f
										.form(
												'submit',
												{
													url : sy.bp()
															+ '/lugDownLimitValueAction!modifyLimitValue.action',
													success : function(d) {
														var json = $
																.parseJSON(d);
														if (json.success) {
															cancelSelect(datagrid);
															datagrid
																	.datagrid('reload');
															p.dialog('close');
															$.messager.alert(
																	'提示',
																	'修改限值记录成功',
																	'info');
														} else {
															p.dialog('close');
															$.messager.alert(
																	'提示',
																	json.msg,
																	'error');
														}
													}
												});
							}
						} ],
						onLoad : function() {
							var f = p.find('form');
							f.form('load', {
								id : rows[0].id,
								jZMinRegisterTime : rows[0].jZMinRegisterTime,
								jZMaxRegisterTime : rows[0].jZMaxRegisterTime,
								vehicleDetailType : rows[0].vehicleDetailType,
								jzLimit : rows[0].jzLimit
							});
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示', '同一时间只能编辑一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示', '请选择要编辑的记录！', 'error');
		}
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
	<div data-options="region:'north',border:false,title:'查询检测设备信息'"
		style="overflow:hidden;height:150px" align="left">
		<form id="frm_JZ_modify">
			<table id="JZ_time" style="padding-top:10px" cellspacing="5">
				<tr>
					<td align="right">生产时间自</td>
					<td align="left"><input name="jZMinRegisterTime"
						id="JZMinRegisterTime" style="width:175px" /></td>
					<td align="center">起，在</td>
					<td align="left"><input name="jZMaxRegisterTime"
						id="JZMaxRegisterTime" style="width:175px" /></td>
					<td align="center">之前生产的</td>
					<td align="left"><select name="vehicleDetailType"
						id="VehicleDetailType" style="width:175px">
							<option value="1">第一类轻型汽车</option>
							<option value="2">第二类轻型汽车</option>
							<option value="3">重型汽车</option>
					</select></td>
				</tr>
			</table>
			<table id="JZ" style="padding-top:10px" cellspacing="5">
				<tr>
					<td align="right">限值:</td>
					<td align="left"><input name="jzLimit" id="JZ_Limit"
						style="width:175px" />
					</td>
					<td><a class="easyui-linkbutton" icon="icon-add" id="AddBtnJZ"
						name="Add" href="javascript:void(0)">保存</a></td>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow:hidden">
		<table id="datagrid"></table>
	</div>
	<div id="menu" class="easyui-menu" style="width:120px;display:none">
		<div onclick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
		<div onclick="remove()" data-options="iconCls:'icon-remove'">注销</div>
	</div>
</body>
</html>
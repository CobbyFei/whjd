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
									+ '/motorTwoSpeedIdleLimitValueReferenceAction!getLimitValues.action',
							title : '摩托车双怠速法限值信息',
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
								field : 'sDSMinRegisterTime',
								width : 150
							}, {
								title : '截止生产时间（不包含）',
								field : 'sDSMaxRegisterTime',
								width : 150
							}, {
								title : '冲程数',
								field : 'strokes',
								width : 100
							}, {
								title : '车轮数',
								field : 'wheelNums',
								width : 100
							},{
								title : '低怠速CO限值',
								field : 'sdsLCo',
								width : 100
							}, {
								title : '低怠速HC限值',
								field : 'sdsLHc',
								width : 100
							}, {
								title : '高怠速CO限值',
								field : 'sdsHCo',
								width : 100
							}, {
								title : '高怠速HC限值',
								field : 'sdsHHc',
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
		$('#AddBtnZJ').bind('click', function() {
			saveregZJ();
		});
	});
	function saveregZJ() {
		$('#frm_ZJ_modify').form(
						'submit',
						{
							url : sy.bp()
									+ '/motorTwoSpeedIdleLimitValueReferenceAction!saveLimitValue.action',
							onSubmit : function() {
								return $('#frm_ZJ_modify').form('validate');
							},
							success : function(data) {
								var result = eval("(" + data + ")");
							 	if (result.success) {
									$.messager.alert('提示！', '添加成功', 'info');
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
									$.ajax({
												url : sy.bp()
														+ '/motorTwoSpeedIdleLimitValueReferenceAction!deleteLimitValue.action',
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
	function edit() {
		var rows = datagrid.datagrid('getChecked');
		if (rows.length == 1) {
			var p = parent.sy
					.dialog({
						title : '修改限值信息',
						href : sy.bp()
								+ '/motorTwoSpeedIdleLimitValueReferenceAction!limitEdit.action',
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
															+ '/motorTwoSpeedIdleLimitValueReferenceAction!editLimit.action',
													success : function(d) {
														var json = $
																.parseJSON(d);
														if (json.success) {
															cancelSelect(datagrid);
															datagrid
																	.datagrid('reload');
															p.dialog('close');
															$.messager.show({
																msg : json.msg,
																title : '提示'
															});
														} else {
															p.dialog('close');
															$.messager.alert(
																	'修改失败！',
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
								sDSMinRegisterTime : rows[0].sDSMinRegisterTime,
								sDSMaxRegisterTime : rows[0].sDSMaxRegisterTime,
								strokes : rows[0].strokes,
								wheelNums : rows[0].wheelNums,
								sdsLCo : rows[0].sdsLCo,
								sdsLHc : rows[0].sdsLHc,
								sdsHCo : rows[0].sdsHCo,
								sdsHHc : rows[0].sdsHHc
							
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
	<div data-options="region:'north',border:false,title:'添加摩托车双怠速法限值'"
		style="overflow:hidden;height:180px" align="left">
		<form id="frm_ZJ_modify">
			<table id="ZJ_time" style="padding-top:10px" cellspacing="5">
				<tr>
					<td align="right" style="width：20%">生产时间自</td>
					<td align="left" style="width：30%"><input name="sDSMinRegisterTime" id="sDSMinRegisterTime" style="width:125px" class="easyui-datebox" data-options="required:true"/></td>
					<td align="center" style="width：20%">起，在</td>
					<td align="left" style="width：30%"><input name="sDSMaxRegisterTime" id="sDSMaxRegisterTime" style="width:125px" class="easyui-datebox" data-options="required:true"/></td>
					<td align="left" style="width：20%">之前的在用汽车</td>
				</tr>
				<tr>
					<td align="right" style="width：20%">冲程数：</td>
					<td align="left" style="width：30%"><input name="strokes" id="strokes" style="width:125px" class="easyui-numberbox" data-options="required:true,min:0"/></td>
					<td align="right" style="width：20%">车轮数：</td>
					<td align="left" style="width：30%"><input name="wheelNums" id="wheelNums" style="width:125px" class="easyui-numberbox" data-options="required:true,min:0"/></td>
				</tr>
				<tr>
					<td align="right" style="width：20%">低怠速CO：</td>
					<td align="left" style="width：30%"><input name="sdsLCo" id="sdsLCo" style="width:125px" class="easyui-numberbox" data-options="required:true,min:0,precision:2"/></td>
					<td align="right" style="width：20%">低怠速HC：</td>
					<td align="left" style="width：30%"><input name="sdsLHc" id="sdsLHc" style="width:125px" class="easyui-numberbox" data-options="required:true,min:0,precision:2"/></td>
				</tr>
				<tr>
					<td align="right" style="width：20%">高怠速CO：</td>
					<td align="left" style="width：30%"><input name="sdsHCo" id="sdsHCo" style="width:125px" class="easyui-numberbox" data-options="min:0,precision:2"/></td>
					<td align="right" style="width：20%">高怠速HC：</td>
					<td align="left" style="width：30%"><input name="sdsHHc" id="sdsHHc" style="width:125px" class="easyui-numberbox" data-options="min:0,precision:2"/></td>
					<td align="right"><a class="easyui-linkbutton" icon="icon-add" id="AddBtnZJ" name="Add" href="javascript:void(0)">添加</a>
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
		<div onclick="remove()" data-options="iconCls:'icon-remove'">注销</div>
	</div>
</body>
</html>
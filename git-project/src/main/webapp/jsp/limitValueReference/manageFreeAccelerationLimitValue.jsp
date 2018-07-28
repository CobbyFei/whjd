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
									+ '/freeAccelerationLimitValueReferenceAction!getLimitValues.action',
							title : '自由加速法限值信息',
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
								field : 'zJMinRegisterTime',
								width : 150
							}, {
								title : '截止生产时间（不包含）',
								field : 'zJMaxRegisterTime',
								width : 150
							}, {
								title : '进气方式',
								field : 'airInletMode',
								width : 100
							}, {
								title : '限值',
								field : 'zjLimit',
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
		$("#ZJ_Limit").numberbox({
			value : 0,
			min : 0,
			precision : 2,
			groupSeparator : ',',
			required : true
		});
		$("#ZJMinRegisterTime").datebox({
			required : true,
			editable : false

		});
		$("#ZJMaxRegisterTime").datebox({
			required : true,
			editable : false
		});
		$('#AddBtnZJ').bind('click', function() {
			saveregZJ();
		});
	});
	function saveregZJ() {
		$('#frm_ZJ_modify')
				.form(
						'submit',
						{
							url : sy.bp()
									+ '/freeAccelerationLimitValueReferenceAction!saveLimitValue.action',
							onSubmit : function() {
								var minTime = new Date(Date.parse($(
										"#ZJMinRegisterTime").datebox(
										'getValue')));
								var maxTime = new Date(Date.parse($(
										"#ZJMaxRegisterTime").datebox(
										'getValue')));
								if (minTime > maxTime) {
									$.messager.alert('提示', '起始时间不得晚于终止时间！',
											'error');
									return false;
								}
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
									$
											.ajax({
												url : sy.bp()
														+ '/freeAccelerationLimitValueReferenceAction!deleteLimitValue.action',
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
								+ '/freeAccelerationLimitValueReferenceAction!limitEdit.action',
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
															+ '/freeAccelerationLimitValueReferenceAction!editLimit.action',
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
								zJMinRegisterTime : rows[0].zJMinRegisterTime,
								zJMaxRegisterTime : rows[0].zJMaxRegisterTime,
								airInletMode : rows[0].airInletMode,
								zjLimit : rows[0].zjLimit
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
	<div data-options="region:'north',border:false,title:'查询自由加速法限值信息'"
		style="overflow:hidden;height:150px" align="left">
		<form id="frm_ZJ_modify">
			<table id="ZJ_time" style="padding-top:10px" cellspacing="5">
				<tr>
					<td align="right">生产时间自</td>
					<td align="left"><input name="zJMinRegisterTime"
						id="ZJMinRegisterTime" style="width:175px" /></td>
					<td align="center">起，在</td>
					<td align="left"><input name="zJMaxRegisterTime"
						id="ZJMaxRegisterTime" style="width:175px" /></td>
					<td align="left">之前的在用汽车</td>
				</tr>
				<tr>
					<td align="right">进气方式：</td>
					<td align="left"><select name="airInletMode" id="AirInletMode"
						style="width:175px">
							<option value="null">不限</option>
							<option value="自然吸气">自然吸气</option>
							<option value="涡轮增压">涡轮增压</option>
					</select></td>
					<td align="right">限值：</td>
					<td align="left"><input name="zjLimit" id="ZJ_Limit"
						style="width:175px" /></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td align="right"><a class="easyui-linkbutton" icon="icon-add"
						id="AddBtnZJ" name="Add" href="javascript:void(0)">添加</a>
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
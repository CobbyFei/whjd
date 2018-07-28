<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	var stationName = '${sessionScope.stationName}';
	$(function() {
		if (stationName == "市局") {
			$("#StationName").val("");
			$("#StationName")
					.combobox(
							{
								url : sy.bp()
										+ '/inspectionStationAction!getInspectionStationName.action',
								mode : 'remote',
								delay : 200,
								valueField : 'stationName',
								required : false,
								textField : 'stationName',
							});
		} else {
			$("#StationName").val(stationName);
			$("#StationName").attr("disabled", true);
		}
		datagrid = $('#datagrid')
				.datagrid(
						{
							url : sy.bp()
									+ '/referenceMaterialsRecordAction!getAllRecords.action',
							title : '标准物质采购记录',
							iconCls : 'icon-save',
							pagination : true,
							pagePosition : 'bottom',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40 ],
							fit : true,
							fitColumns : false,
							nowrap : false,
							border : false,
							idField : 'recordId',
							sortName : 'recordId',
							sortOrder : 'asc',
							checkOnSelect : false,
							selectOnCheck : true,
							frozenColumns : [ [ {
								title : '编号',
								field : 'recordId',
								width : 150,
								sortable : true,
								checkbox : true
							} ] ],
							columns : [ [ {
								title : '标准物质名称',
								field : 'materialName',
								width : 150
							}, {
								title : '制造厂商',
								field : 'manufacturer',
								width : 150
							}, {
								title : '型号',
								field : 'model',
								width : 100
							}, {
								title : '规格',
								field : 'specification',
								width : 100
							}, {
								title : '采购数量',
								field : 'purchaseNum',
								width : 80
							}, {
								title : '采购时间',
								field : 'strPurchaseTime',
								width : 150
							}, {
								title : '所属检测站',
								field : 'stationName',
								width : 150
							}, {
								title : '状态',
								field : 'status',
								width : 80,
								formatter : function(value, rowData, rowIndex) {
									if (value == 0) {
										return "正常";
									}
									if (value == 1) {
										return "注销";
									}
								}
							}, {
								title : '备注',
								field : 'remarks',
								width : 200
							} ] ],
							toolbar : [ {
								text : '注销',
								iconCls : 'icon-remove',
								handler : function() {
									remove();
								}
							}, '-', {
								text : '修改',
								iconCls : 'icon-edit',
								handler : function() {
									edit();
								}
							}, '-', {
								text : '导出',
								iconCls : 'icon-redo',
								handler : function() {
									exp();
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
										if (rows[i].recordId == rowData.recordId) {
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
							},
							rowStyler : function(index, row) {
								if (row.status == 0) {
									return "color:green";
								} else if (row.status == 1) {
									return "color:red";
								}
							}
						});
		$("#MaterialName")
				.combobox(
						{
							url : sy.bp()
									+ '/referenceMaterialsRecordAction!getMaterialNames.action',
							mode : 'remote',
							delay : 500,
							valueField : 'materialName',
							textField : 'materialName',
							onSelect : function(rec) {
								var url = sy.bp()
										+ '/referenceMaterialsRecordAction!getSpecifications.action?materialName='
										+ rec.materialName;
								$("#Specification").combobox('reload', url);
								var url2 = sy.bp()
										+ '/referenceMaterialsRecordAction!getManufacturers.action?materialName='
										+ rec.materialName;
								$("#Manufacturer").combobox('reload', url2);
							}
						});
		$("#Manufacturer")
				.combobox(
						{
							url : sy.bp()
									+ '/referenceMaterialsRecordAction!getManufacturers.action',
							mode : 'remote',
							delay : 500,
							valueField : 'manufacturer',
							textField : 'manufacturer'
						});
		$("#Model").combobox({
			url : sy.bp() + '/referenceMaterialsRecordAction!getModels.action',
			mode : 'remote',
			delay : 500,
			valueField : 'specification',
			textField : 'specification'
		});
	});
	function edit() {
		if (stationName == "市局") {
			parent.sy.messagerAlert('提示', '采购记录只能由检测站人员修改！', 'error');
		} else {
			var rows = datagrid.datagrid('getChecked');
			if (rows.length == 1) {
				var p = parent.sy
						.dialog({
							title : '修改采购记录',
							href : sy.bp()
									+ '/referenceMaterialsRecordAction!recordEdit.action',
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
																+ '/referenceMaterialsRecordAction!saveRecord.action',
														success : function(d) {
															var json = $
																	.parseJSON(d);
															if (json.success) {
																cancelSelect(datagrid);
																datagrid
																		.datagrid('reload');
																p
																		.dialog('close');
															}
															parent.sy
																	.messagerShow({
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
									recordId : rows[0].recordId,
									materialName : rows[0].materialName,
									manufacturer : rows[0].manufacturer,
									specification : rows[0].specification,
									purchaseNum : rows[0].purchaseNum,
									strPurchaseTime : rows[0].strPurchaseTime,
									status : rows[0].status,
									remarks : rows[0].remarks,
									stationName : rows[0].stationName
								});
							}
						});
			} else if (rows.length > 1) {
				parent.sy.messagerAlert('提示', '同一时间只能编辑一条记录！', 'error');
			} else {
				parent.sy.messagerAlert('提示', '请选择要编辑的记录！', 'error');
			}
		}
	}
	function exp() {
		var data = datagrid.datagrid('getData');
		var rows = datagrid.datagrid('getData').rows;
		var ids = [];
		if (rows.length > 0) {
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].recordId);
			}
			$
					.ajax({
						url : sy.bp()
								+ '/referenceMaterialsRecordAction!exportRecord.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(d) {
							cancelSelect(datagrid);
							$('#filePath').val(d.obj);
							$('#frm_down').submit();
							datagrid.datagrid('load');
						}
					});

		} else {
			parent.sy.messagerAlert('提示', '当前没有可导出的记录！', 'error');
		}
	}
	function remove() {
		if (stationName == "市局") {
			parent.sy.messagerAlert('提示', '采购记录只能由检测站人员注销！', 'error');
		} else {
			var rows = datagrid.datagrid('getChecked');
			var ids = [];
			if (rows.length > 0) {
				parent.sy
						.messagerConfirm(
								'请确认',
								'确定要注销所选记录？',
								function(r) {
									if (r) {
										for ( var i = 0; i < rows.length; i++) {
											ids.push(rows[i].recordId);
										}
										$
												.ajax({
													url : sy.bp()
															+ '/referenceMaterialsRecordAction!deleteRecords.action',
													data : {
														ids : ids.join(',')
													},
													dataType : 'json',
													success : function(d) {
														cancelSelect(datagrid);
														datagrid
																.datagrid('load');
														parent.sy
																.messagerShow({
																	title : '提示',
																	msg : d.msg
																});
													}
												});
									}
								});
			} else {
				parent.sy.messagerAlert('提示', '请选择要注销的记录！', 'error');
			}
		}
	}
	//search是javascript的字符匹配方法
	function searchSth() {
		datagrid.datagrid('load', sy.serializeObject($('#frm_search_record')));
	}
	function clean() {
		datagrid.datagrid('load', {});
		$('#frm_search_record').form('reset');
	}
	function cancelSelect(datagrid) {
		datagrid.datagrid('clearSelections');
		datagrid.datagrid('clearChecked');
	}
</script>
</head>

<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询标准物质采购记录'"
		style="overflow:hidden;height:150px" align="left">
		<form id="frm_down" method="post"
			action="/whjd/downloadFileAction!export.action" target="_self">
			<input id="filePath" name="filePath" type="hidden" />
		</form>
		<form id="frm_search_record">
			<table cellspacing="5">
				<tr>
					<td align="right">所属检测站</td>
					<td><input id="StationName" name="stationName"
						style="width:175px" />
					</td>
					<td align="right">标准物质名称</td>
					<td><input id="MaterialName" name="materialName"
						style="width:175px" /></td>
					<td align="right">型号</td>
					<td><input id="Model" name="model" style="width:175px" /></td>
				</tr>
				<tr>
					<td align="right">制造厂商</td>
					<td><input id="Manufacturer" name="manufacturer"
						style="width:175px" /></td>
					<td align="right">采购时间从</td>
					<td><input name="beginTime" style="width:175px"
						class="easyui-datebox" editable="false" /></td>
					<td align="center">至</td>
					<td><input name="endTime" style="width:175px"
						class="easyui-datebox" editable="false" />
					</td>
				</tr>
				<tr>
					<td align="right">状态</td>
					<td><select name="status" id="Status" style="width:175px">
							<option value='2' selected>--全部--</option>
							<option value='0'>正常</option>
							<option value='1'>注销</option>
					</select>
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td align="right"><a href="javascript:void(0);"
						class="easyui-linkbutton" iconCls="icon-search"
						onclick="searchSth()">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-cancel" onclick="clean()">清空</a></td>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow:hidden">
		<table id="datagrid"></table>
	</div>
	<div id="menu" class="easyui-menu" style="width:120px;display:none;">
		<div onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
		<div onclick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
	</div>
</body>
</html>

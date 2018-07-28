<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
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
								onSelect : function(rec) {
									var url = sy.bp()
											+ '/detectionLineAction!getDetectionLineName.action?stationName='
											+ rec.stationName;
									$("#LineId").combobox('reload', url);
								}
							});
		} else {
			$("#StationName").val(stationName);
			$("#StationName").attr("disabled", true);
		}
		datagrid = $('#datagrid').datagrid({
			url : sy.bp() + '/deviceInfoAction!getAllInfos.action',
			title : '检测设备信息',
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
				title : '设备名称',
				field : 'deviceName',
				width : 150
			}, {
				title : '制造厂商',
				field : 'manufacturer',
				width : 150
			}, {
				title : '型号规格',
				field : 'deviceModel',
				width : 150
			}, {
				title : '所属检测站',
				field : 'stationName',
				width : 100
			}, {
				title : '所属检测线',
				field : 'lineName',
				width : 100
			}, {
				title : '状态',
				field : 'deviceStatus',
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
				if (rows != undefined && rows != null && rows.length > 0) {
					for ( var i = 0; i < rows.length; i++) {
						if (rows[i].id == rowData.id) {
							flag = false;
							break;
						}
					}
					if (flag == false) {
						datagrid.datagrid('unselectRow', rowIndex);
						datagrid.datagrid('uncheckRow', rowIndex);
					} else {
						datagrid.datagrid('selectRow', rowIndex);
						datagrid.datagrid('checkRow', rowIndex);
					}
				} else {
					datagrid.datagrid('checkRow', rowIndex);
				}
			},
			rowStyler : function(index, row) {
				if (row.deviceStatus == 0) {
					return "color:green";
				} else if (row.deviceStatus == 1) {
					return "color:red";
				}
			}
		});
		$("#DeviceName")
				.combobox(
						{
							url : sy.bp()
									+ '/deviceInfoAction!getDeviceNames.action',
							mode : 'remote',
							delay : 500,
							valueField : 'deviceName',
							textField : 'deviceName',
							onSelect : function(rec) {
								var url = sy.bp()
										+ '/deviceInfoAction!getDeviceModels.action?deviceName='
										+ rec.deviceName;
								$("#DeviceModel").combobox('reload', url);
								var url2 = sy.bp()
										+ '/deviceInfoAction!getManufacturers.action?deviceName='
										+ rec.deviceName;
								$("#Manufacturer").combobox('reload', url2);
							}
						});
		$("#Manufacturer").combobox({
			url : sy.bp() + '/deviceInfoAction!getManufacturers.action',
			mode : 'remote',
			delay : 500,
			valueField : 'manufacturer',
			textField : 'manufacturer'
		});
		$("#DeviceModel").combobox({
			url : sy.bp() + '/deviceInfoAction!getDeviceModels.action',
			mode : 'remote',
			delay : 500,
			valueField : 'deviceModel',
			textField : 'deviceModel'
		});
		$("#LineId")
				.combobox(
						{
							url : sy.bp()
									+ '/detectionLineAction!getDetectionLineName.action',
							mode : 'remote',
							valueField : 'lineId',
							textField : 'lineName',
							onSelect : function(rec) {
								var url = sy.bp()
										+ '/deviceInfoAction!getDeviceNames.action?lineId='
										+ rec.lineId;
								$("#DeviceName").combobox('reload', url);
							}
						});
	});
	function edit() {
		if (stationName == "市局") {
			parent.sy.messagerAlert('提示', '设备信息只能由检测站人员修改！', 'error');
		} else {
			var rows = datagrid.datagrid('getChecked');
			if (rows.length == 1) {
				var p = parent.sy.dialog({
					title : '修改设备信息',
					href : sy.bp() + '/deviceInfoAction!infoEdit.action',
					width : 800,
					height : 300,
					resizable : true,
					buttons : [ {
						text : '修改',
						handler : function() {
							var f = p.find('form');
							f.form('submit', {
								url : sy.bp()
										+ '/deviceInfoAction!editInfo.action',
								success : function(d) {
									var json = $.parseJSON(d);
									if (json.success) {
										cancelSelect(datagrid);
										datagrid.datagrid('reload');
										p.dialog('close');
										$.messager.show({
											msg : json.msg,
											title : '提示'
										});
									} else {
										p.dialog('close');
										$.messager.alert('修改失败！', json.msg,
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
							deviceName : rows[0].deviceName,
							manufacturer : rows[0].manufacturer,
							deviceModel : rows[0].deviceModel,
							lineName : rows[0].lineName,
							lineId : rows[0].lineId,
							deviceStatus : rows[0].deviceStatus,
							remarks : rows[0].remarks,
							stationName : rows[0].stationName,
							deviceStatus : rows[0].deviceStatus
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
				ids.push(rows[i].id);
			}
			$.ajax({
				url : sy.bp() + '/deviceInfoAction!exportDeviceInfo.action',
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
			parent.sy.messagerAlert('提示', '设备信息只能由检测站人员注销！', 'error');
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
											if (rows[i].deviceStatus == 0)
												ids.push(rows[i].id);
										}
										if (ids.length == 0) {
											$.messager.alert('提示',
													'所选设备信息均已注销', 'error');
											return;
										}
										$
												.ajax({
													url : sy.bp()
															+ '/deviceInfoAction!cancelDeviceInfo.action',
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
		<form id="frm_down" method="post"
			action="/whjd/downloadFileAction!export.action" target="_self">
			<input id="filePath" name="filePath" type="hidden" />
		</form>
		<form id="searchForm">
			<table style="padding-top:10px" cellspacing="5">
				<tr>
					<td align="right">所属检测站：</td>
					<td><input id="StationName" name="stationName"
						style="width:175px" /></td>
					<td align="right">所属检测线：</td>
					<td><select name="lineId" id="LineId" style="width:175px"></select>
					</td>
					<td align="right">状态：</td>
					<td><select id="DeviceStatus" name="deviceStatus"
						style="width:175px">
							<option value="2" selected="true">--全部--</option>
							<option value="1">注销</option>
							<option value="0">正常</option>
					</select></td>
				</tr>
				<tr>
					<td align="right">设备名称：</td>
					<td><input name="deviceName" id="DeviceName"
						style="width:175px" /></td>
					<td align="right">制造厂商：</td>
					<td><input name="manufacturer" id="Manufacturer"
						style="width:175px" /></td>
					<td align="right">型号：</td>
					<td><input name="deviceModel" id="DeviceModel"
						style="width:175px">
					</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td algin="right"><a href="javascript:void(0);"
						class="easyui-linkbutton" iconCls="icon-search"
						onclick="searchSth()">查询</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-cancel" onclick="clean()">清空</a>
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
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	$(function() {

		$("#stationId").combobox(
						{
							url : sy.bp()
									+ '/inspectionStationAction!getInspectionStationName.action',
							mode : 'remote',
							delay : 200,
							valueField : 'stationId',
							required : false,
							textField : 'stationName',
						});

		$("#directorId").combobox({
			url : sy.bp() + '/manageUserAction!getSysUsers.action',
			mode : 'remote',
			delay : 500,
			valueField : 'userId',
			required : false,
			textField : 'userName',
		});
		
		datagrid = $('#datagrid').datagrid(
						{
							url : '${pageContext.request.contextPath}/inspectionStationAction!getInspectionStation.action',//
							title : '标志核发点基本信息列表',
							iconCls : 'icon-save',
							pagination : true,
							pagePosition : 'bottom',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40 ],
							fit : true,
							fitColumns : false,
							nowrap : false,
							border : false,
							idField : 'stationId',
							sortName : 'stationId',
							sortOrder : 'asc',
							checkOnSelect : false,
							selectOnCheck : true,
							frozenColumns : [ [ {
								title : '编号',
								field : 'recordId',
								width : 100,
								sortable : true,
								checkbox : true
							} ] ],
							columns : [ [ {
								title : '标志核发点名称',
								field : 'stationName',
								width : 100
							}, {
								title : '核发点地址',
								field : 'stationAddress',
								width : 200
							}, {
								title : '负责人',
								field : 'userName',
								width : 80
							}, {
								title : '注册时间',
								field : 'registrationTime',
								width : 110
							}, {
								title : '资质种类',
								field : 'qulificationType',
								width : 60
							}, {
								title : '资质取得时间',
								field : 'qulificationTime',
								width : 110
							}, {
								title : '有效期',
								field : 'validPeriod',
								width : 110
							}, {
								title : '状态',
								field : 'status',
								width : 50
							},{
								title : '备注',
								field : 'remarks',
								width : 300
							} ] ],
							toolbar : [ {
								text : '注销',
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
							}, '-' ,{
								text : '刷新',
								iconCls : 'icon-reload',
								handler : function() {
									searchSth();
								}
							}, '-'],
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
							},
							/* 改变列数据字的颜色 */
							rowStyler: function(index, row){
								if (row.status == "注销") {
									return 'background-color:#F08080;color:#000;';
								} else if (row.status == 5) {
									return 'background-color:#fff;color:#00a;'; // return inline style
								} else if (row.status == 6) {
									return 'background-color:#A2CD5A;color:#000;'; // return inline style
								}
							}
						});
	});

	// 修改，不支持批量

	function edit() {
		var rows = datagrid.datagrid('getChecked');
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '编辑标志核发点基本信息',
						href : '${pageContext.request.contextPath}/inspectionStationAction!editInspectionStation.action',
						width : 800,
						height : 500,
						resizable : true,
						buttons : [ {
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var f = p.find('form');
								f.form('submit',
												{
													url : '${pageContext.request.contextPath}/inspectionStationAction!updateInspectionStation.action',
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

							f.form('load',
											{
												stationId : rows[0].stationId,
												stationName : rows[0].stationName,
												stationAddress : rows[0].stationAddress,
												directorId : rows[0].userName,
												registrationTime : rows[0].registrationTime,
												qulificationType : rows[0].qulificationType,
												selectPurchaseTime : rows[0].selectPurchaseTime,
												qulificationTime : rows[0].qulificationTime,
												validPeriod : rows[0].validPeriod,
												status:rows[0].status,
												remarks : rows[0].remarks
											});

							f.find('input[name=directorId]').combobox(
											{
												url : sy.bp()
														+ '/manageUserAction!getSysUsers.action',
												mode : 'remote',
												delay : 500,
												valueField : 'userId',
												required : false,
												textField : 'userName',
											});
							var flag = false;
									f.find('input[name=stationName]').bind('focus keyup blur',
											function() {
												if ($(this).val() == "") {
															f.find('input[name=stationNameCheck]').html("<font color='red'><b>请输入核发点名称</b><font>");
												} else {
													$.ajax({
																url : sy.bp()+ '/inspectionStationAction!hasInspectionStation.action',
																dataType : 'json',
																data : {
																	q : $(this).val()
																},
																success : function(data) {
																	if (data.success) {
																		flag = false;
																		f.find('input[name=stationNameCheck]').html("<font color='red'><b>该核发点已存在</b><font>");
																		//$("#stationNameCheck").html("<font color='red'><b>检测站已存在</b><font>");
																	} else {
																		flag = true;
																		f.find('input[name=stationNameCheck]').html("<font color='red'><b>该名称可用</b><font>");
																	}
																}
															});
												}
											});

						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示', '同一时间只能编辑一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示', '请选择要编辑的记录！', 'error');
		}
	}

	// 删除，支持批量
	function remove() {
		var rows = datagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy
					.messagerConfirm(
							'请确认',
							'您要注销当前标志核发点？',
							function(r) {
								if (r) {
									for ( var i = 0; i < rows.length; i++) {
										ids.push(rows[i].stationId);
									}
									$.ajax({
												url : '${pageContext.request.contextPath}/inspectionStationAction!cancelInspectionStation.action',
												data : {
													ids : ids.join(',')
												},
												dataType : 'json',
												success : function(d) {
													cancelSelect(datagrid);
													datagrid.datagrid('load',sy.serializeObject($("#searchForm")));
													sy.messagerShow({
														title : '提示',
														msg : d.msg
													});
												}
											});
								}
							});
		} else {
			parent.sy.messagerAlert('警告', '请勾选要删除的标志核发点！', 'error');
		}
	}

	function searchSth() {
		datagrid.datagrid('load', sy.serializeObject($("#searchForm")));
	}

	function cleanSearch() {
		// load方法是可以携带参数的，这里为了增加重用性，没有写
		datagrid.datagrid('load', {});
		$('#searchForm input').val('');
	}

	function cancelSelect(datagrid) {
		// 跨页数据也支持，只支持jQuery-easyui-1.3.2
		datagrid.datagrid('clearSelections');
		datagrid.datagrid('clearChecked');
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查找条件'" style="height: 80px;overflow: hidden;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th>标志核发点名称</th>
					<td><input name="stationId" id="stationId" style="width:120px" /></td>
					<th>核发点负责人</th>
					<td><input name="directorId" id="directorId" style="width:120px" /></td>
					<th>核发点状态</th>
					<td><select name="status" id="status" style="width:80px">
					<option value ="全部">--全部--</option>
					  <option value ="正常">正常</option>
                      <option value ="注销">注销</option>
					</select></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="searchSth();">查询</a> <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="remove();" data-options="iconCls:'icon-remove'">注销</div>
		<div onclick="edit();" data-options="iconCls:'icon-edit'">编辑</div>
	</div>
</body>
</html>
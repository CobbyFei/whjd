<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	$(function() {
		var str='${sessionScope.stationName}';		
		if(str!="市局"){
			//alert(str);
			$("#stationId").combobox({
				disabled:true
			});
			$("#stationId").combobox('setValue', str);			
		}else{
		$("#stationId").combobox(
						{
							url : sy.bp()+ '/inspectionStationAction!getInspectionStationName.action',
							mode : 'remote',
							delay : 200,
							valueField : 'stationId',
							required : false,
							textField : 'stationName',
						});
		}
		$("#userId").combobox({
			url : sy.bp() + '/manageUserAction!getSysUsers.action?stationId=${sessionScope.stationId}',
			mode : 'remote',
			delay : 500,
			valueField : 'userId',
			required : false,
			textField : 'userName',
		});

		datagrid = $('#datagrid').datagrid(
						{
							url : '${pageContext.request.contextPath}/environmentalLabelCollarAction!getEnvironmentalLabelCollar.action',//
							title : '环保标志领用记录列表',
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
							sortOrder : 'desc',
							checkOnSelect : false,
							selectOnCheck : true,
							frozenColumns : [ [ {
								title : '编号',
								field : 'id',
								width : 100,
								sortable : true,
								checkbox : true
							} ] ],
							columns : [ [ {
								title : '核发点名称',
								field : 'stationName',
								width : 100
							}, {
								title : '领用人',
								field : 'userName',
								width : 200
							}, {
								title : '领用时间',
								field : 'collarTime',
								width : 110
							}, {
								title : '标志年度号',
								field : 'year',
								width : 100
							}, {
								title : '状态',
								field : 'status',
								width : 100
							}, {
								title : '绿标数',
								field : 'greenLabelNum',
								width : 110
							}, {
								title : '黄标数',
								field : 'yellowLabelNum',
								width : 110
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
							}, '-' , {
							    text:'导出',
							    iconCls:'icon-redo',
							    handler:function(){
							          myExport();
							    }
							},'-',{
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
							// 改变列数据字的颜色 
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
		var str='${sessionScope.stationName}';
		if(str=="市局"){
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '编辑环保标志领用记录',
						href : '${pageContext.request.contextPath}/environmentalLabelCollarAction!editEnvironmentalLabelCollar.action',
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
													url : '${pageContext.request.contextPath}/environmentalLabelCollarAction!updateEnvironmentalLabelCollar.action',
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
												id : rows[0].id,
												stationId : rows[0].stationName,
												userId : rows[0].userName,
												collarTime : rows[0].collarTime,
												year : rows[0].year,
												status:rows[0].status,
												greenLabelNum : rows[0].greenLabelNum,
												yellowLabelNum : rows[0].yellowLabelNum,
												remarks : rows[0].remarks
											});
							/*f.find('input[name=userId]').combobox(
									{
										url : sy.bp()
												+ '/manageUserAction!getSysUsers.action',
										mode : 'remote',
										delay : 500,
										valueField : 'userId',
										required : false,
										textField : 'userName'
				
									//editable:false
									});
							f.find('input[name=stationId]').combobox(
									{
										url : sy.bp()
												+ '/inspectionStationAction!getInspectionStationName.action',
										mode : 'remote',
										delay : 500,
										valueField : 'stationId',
										required : false,
										textField : 'stationName',
										onSelect : function(rec) {
											alert("dfd");
											var url = sy.bp()
													+ '/manageUserAction!getSysUsers.action?stationId='
													+ rec.stationId;
											f.find('input[name=userId]').combobox('reload', url);
										}
									});*/
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示', '同一时间只能编辑一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示', '请选择要编辑的记录！', 'error');
		}
		}else{
			parent.sy.messagerAlert('提示', '您的权限不足！', 'error');
		}
	}

	// 删除，支持批量
	function remove() {
		var rows = datagrid.datagrid('getChecked');
		var str='${sessionScope.stationName}';
		if(str=="市局"){
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm(
							'请确认',
							'您要注销当前环保标志领用记录？',
							function(r) {
								if (r) {
									for ( var i = 0; i < rows.length; i++) {
										ids.push(rows[i].id);
									}
									$.ajax({
												url : '${pageContext.request.contextPath}/environmentalLabelCollarAction!cancelEnvironmentalLabelCollar.action',
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
			parent.sy.messagerAlert('警告', '请勾选要注销的标志领用记录！', 'error');
		}
		}else{
			parent.sy.messagerAlert('提示', '您的权限不足！', 'error');
		}
	}
	//导出信息
	function myExport(){
	    var rows = datagrid.datagrid('getData').rows;
		var ids = [];
		if (rows.length > 0) {
		   $.messager.confirm('提示','确定要导出环保标志领用记录？',function(r){
		      for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			 }
				$.ajax({
					url : sy.bp() + '/environmentalLabelCollarAction!exportEnvironmentalLabelCollar.action',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(d) {
						sy.messagerShow({
							title:'提示',
							msg:d.msg
						});
						cancelSelections(datagrid);
						$('#filePath').val(d.obj);
						$('#frm_down').submit();
					}
				});
		   });
			

		} else {
			parent.sy.messagerAlert('提示', '数据为空，不能执行导出操作！', 'error');
		}
	}
	function searchSth() {
		datagrid.datagrid('load', sy.serializeObject($('#searchForm')));
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
		<form id="frm_down" method="post"
			action="/whjd/downloadFileAction!export.action" target="_self">
			<input id="filePath" name="filePath" type="hidden" />
		</form>
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th>核发点名称</th>
					<td><input name="stationId" id="stationId" style="width:120px" /></td>
					<th>标志领用人</th>
					<td><input name="userId" id="userId" style="width:120px" /></td>
					<th>领用年度号</th>
					<td><input name="year" id="year" style="width:80px" class="easyui-numberbox" data-options="min:0" style="width:153px"/>
				    </td>
				    <th>状态</th>
				    <td><select name="status" id="status" style="width:80px">
				    <option value ="全部">--全部--</option>
					  <option value ="正常">正常</option>
                      <option value ="注销">注销</option>
					</select></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="searchSth();">确定</a> <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
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
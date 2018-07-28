<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	$(function() {
		$("#userId").combobox({
			url : sy.bp() + '/manageUserAction!getSysUsers.action',
			mode : 'remote',
			delay : 500,
			valueField : 'userName',
			required : false,
			textField : 'userName',
		});

		datagrid = $('#datagrid').datagrid(
						{
							url : '${pageContext.request.contextPath}/agencyCollarAction!getAgencyCollarRecord.action',//
							title : '市局环保标志领用记录列表',
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
								width : 100,
								sortable : true,
								checkbox : true
							} ] ],
							columns : [ [ {
								title : '领用人编号',
								field : 'userId',
								width : 100
							}, {
								title : '领用人',
								field : 'userName',
								width : 150
							}, {
								title : '领用时间',
								field : 'collarTime',
								width : 110
							}, {
								title : '标志年度号',
								field : 'year',
								width : 100
							},  {
								title : '绿标数',
								field : 'greenLabelNum',
								width : 110
							}, {
								title : '黄标数',
								field : 'yellowLabelNum',
								width : 110
							},{
								title : '状态',
								field : 'status',
								width : 100,
								formatter:function(value,rowData,rowIndex)
								   {
								       if(value=="0")
								       {
								          return "正常";
								       }
								       else
								       {
								          return "注销";
								       }
								   }
							},{
								title : '备注',
								field : 'remarks',
								width : 300
								 
							} ] ],
							toolbar : [{
								text : '编辑',
								iconCls : 'icon-edit',
								handler : function() {
									edit();
								}
							}, '-', {
								text : '注销',
								iconCls : 'icon-remove',
								handler : function() {
									cancel();
								}
							}, '-', {
								text : '删除',
								iconCls : 'icon-cancel',
								handler : function() {
									remove();
								}
							}, '-',{
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
							},
							// 改变列数据字的颜色 
							rowStyler: function(index, row){
								if (row.status == "1") {
									return 'color:red';
								} 
							}
						});
	});

	// 修改，不支持批量

	function edit() {
		var rows = datagrid.datagrid('getChecked');
		
		if (rows.length == 1) {
			var p = parent.sy.dialog({
						title : '编辑环保标志领用记录',
						href : '${pageContext.request.contextPath}/agencyCollarAction!edit.action',
						width : 500,
						height : 500,
						resizable : true,
						buttons : [ {
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var f = p.find('form');
								f.form('submit',
												{
													url : '${pageContext.request.contextPath}/agencyCollarAction!updateAgencyCollar.action',
													success : function(d) {
														var json = $.parseJSON(d);
														if (json.success) {
														    $.messager.alert("提示","修改标志领用记录成功！","info");
															cancelSelect(datagrid);
															datagrid.datagrid('reload'); // 完成之后刷新列表
															p.dialog('close');
															
														}
														else
														{
														   $.messager.alert('提示',json.msg,'error');
														}
													}
												});
							}
						} ],

						onLoad : function() {
							var f = p.find('form');

							f.form('load',
											{
												id : rows[0].id,
												userId:rows[0].userId,
												userId1:rows[0].userId,
												collarTime : rows[0].collarTime,
												year : rows[0].year,
												status:rows[0].status,
												greenLabelNum : rows[0].greenLabelNum,
												yellowLabelNum : rows[0].yellowLabelNum,
												status:rows[0].status,
												remarks : rows[0].remarks
											});
							f.find("input[name=userId]").combobox({
										url : sy.bp()+ '/manageUserAction!getSysUsers.action',
										mode : 'remote',
										delay : 500,
										valueField : 'userId',
										required : false,
										textField : 'userName'
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
	function cancel() {
		var rows = datagrid.datagrid('getChecked');
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
												url : '${pageContext.request.contextPath}/agencyCollarAction!cancelCollarRecord.action',
												data : {
													ids : ids.join(',')
												},
												dataType : 'json',
												success : function(d) {
												   if(d.success)
												   {
												     cancelSelect(datagrid);
													 datagrid.datagrid('load',sy.serializeObject($("#searchForm")));
													 $.messager.alert("提示","注销市局标志领用记录成功！","info");
												   }
												   else
												   {
												      $.messager.alert("提示",d.msg,"error");
												   }
												}
											});
								}
							});
		} else {
			parent.sy.messagerAlert('警告', '请勾选要注销的标志领用记录！', 'error');
		}
	}
	
	function remove() {
		var rows = datagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm(
							'请确认',
							'您要删除当前环保标志领用记录？',
							function(r) {
								if (r) {
									for ( var i = 0; i < rows.length; i++) {
										ids.push(rows[i].id);
									}
									$.ajax({
												url : '${pageContext.request.contextPath}/agencyCollarAction!removeCollarRecord.action',
												data : {
													ids : ids.join(',')
												},
												dataType : 'json',
												success : function(d) {
												   if(d.success)
												   {
												     cancelSelect(datagrid);
													 datagrid.datagrid('load',sy.serializeObject($("#searchForm")));
													 $.messager.alert("提示","删除市局标志领用记录成功！","info");
												   }
												   else
												   {
												      $.messager.alert("提示",d.msg,"error");
												   }
												}
											});
								}
							});
		} else {
			parent.sy.messagerAlert('警告', '请勾选要删除的标志领用记录！', 'error');
		}
	}

	function searchData() {
		datagrid.datagrid('load', sy.serializeObject($('#searchForm')));
	}

	function clearSearch() {
		datagrid.datagrid('load', {});
		$('#searchForm').form('reset');
	}

	function cancelSelect(datagrid) {
		// 跨页数据也支持，只支持jQuery-easyui-1.3.2
		datagrid.datagrid('clearSelections');
		datagrid.datagrid('clearChecked');
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查找条件'" style="height: 120px;overflow: hidden;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width:100%;height: 100%;">
				<tr>
					<th>标志领用人</th>
					<td><input name="userId" id="userId" style="width:175PX" /></td>
					<th>领用年度号</th>
					<td><input name="year" id="year" style="width:175px" class="easyui-numberbox" data-options="min:0"/>
				    </td>
				</tr>
				<tr>
					<th>开始时间</th>
					<td><input name="beginTime" id="beginTime"style="width:175px"  class="easyui-datebox"  /></td>
					<th>------</th>
					<td><input name="endTime" id="endTime" style="width:175px" class="easyui-datebox" />
				    </td>
				</tr>
				<tr>
				    <th>状态</th>
				    <td><select name="status" id="status" style="175px">
				    <option value ="2">--全部--</option>
					  <option value ="0">正常</option>
                      <option value ="1">注销</option>
					</select>
					</td>
					<td colspan="2">
					   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="searchData();">查询</a> <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearSearch();">取消</a>
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
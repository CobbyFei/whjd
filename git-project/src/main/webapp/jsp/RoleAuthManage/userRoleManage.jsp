<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	$(function() {
		datagrid = $('#datagrid').datagrid({
			url : '${pageContext.request.contextPath}/manageRoleAuthAction!getUserRoleDatagrid.action',
			title : '用户角色列表',
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
				title : '系统用户名',
				field : 'userName',
				width : 100
			}, {
				title : '角色名',
				field : 'roleName',
				width : 100
			}, {
				title : '系统用户id',
				field : 'userId',
				width : 80,
				hidden : true
			}, {
				title : '角色id',
				field : 'roleId',
				width : 80,
				hidden : true
			}, {
				title : '状态',
				field : 'relationStatus',
				width : 100,
				formatter : function(value, rowData, rowIndex) {
					if (value == 1) { //用户正常
						return "<span> " + "正常" + "</span>";
					} else if (value == 0) { //用户注销
						return "<span>" + "注销" + "</span>";
					}
				}
			} ] ],
			
			toolbar : [ {
				text : '注销',
				iconCls : 'icon-remove',
				handler : function() {
					var changeToStatus = 0;
					changeStatus(changeToStatus);
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-', {
				text : '恢复',
				iconCls : 'icon-ok',
				handler : function() {
					var changeToStatus = 1;
					changeStatus(changeToStatus);
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
				if (row.relationStatus == 0) {
					return 'color:#FF0000;';
				}
			}
		
		});
		
		$('#userName').combobox({
			url : sy.bp() + '/manageRoleAuthAction!getUserSimplifyNames.action',
			mode : 'remote',
			delay : 500,
			valueField : 'simplifyName',
			textField : 'simplifyName',
		});
		
		$('#roleName').combobox({
			url : sy.bp() + '/manageRoleAuthAction!getRoleNames.action',
			mode : 'remote',
			delay : 500,
			valueField : 'roleName',
			textField : 'roleName',
		});
	});
	
	// 修改，不支持批量
	function edit() {
		var rows = datagrid.datagrid('getChecked');
		if (rows.length == 1) {
			var p = parent.sy
					.dialog({
						title : '修改权限资源信息',
						href : '${pageContext.request.contextPath}/manageRoleAuthAction!userRoleEdit.action',
						width : 750,
						height : 400,
						resizable : true,
						buttons : [ {
							text : '修改',
							handler : function() {
								var f = p.find('form');
								if (f.form('validate')) {
									f
											.form(
													'submit',
													{
														url : '${pageContext.request.contextPath}/manageRoleAuthAction!editUserRoleRecord.action',
														dataType : 'json',
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
								} else {
									parent.sy.messagerShow({
										msg : "请补全信息或修改名称",
										title : '提示'
									});
								}
								
							}
						} ],
						
						onLoad : function() {
							var f = p.find('form');
							var userCom;
							var roleCom;
							f.form('load', {
								'id' : rows[0].id,
								'roleId' : rows[0].roleName,
								'userName' : rows[0].userName,
								'userId' : rows[0].userId,
								'relationStatus' : rows[0].relationStatus,
							});
							
							roleCom = f.find('input[name=roleId]').combobox({
								url : '${pageContext.request.contextPath}/manageRoleAuthAction!getRoleNames.action',
								mode : 'remote',
								delay : 500,
								valueField : 'roleId',
								textField : 'roleName',
								required : true
							});
							
							f.find('input[name="relationStatus"]').combobox({
								required : true,
								panelHeight : 40
							});
							
							roleCom.combobox('setValue', rows[0].roleId);
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示', '同一时间只能编辑一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示', '请选择要编辑的记录！', 'error');
		}
	}
	
	// 更改状态，支持批量
	function changeStatus(changeToStatus) {
		var rows = datagrid.datagrid('getChecked');
		var ids = [];
		
		if (rows.length > 0) {
			var infoCancel = '您要注销当前所选项目？';
			var infoRecover = '您要恢复当前所选项目？';
			
			if (changeToStatus == 1) {
				param = infoRecover;
			} else {
				param = infoCancel;
			}
			parent.sy.messagerConfirm('请确认', param, function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/manageRoleAuthAction!changeUserRoleStatus.action',
						data : {
							ids : ids.join(','),
							relationStatus : changeToStatus
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
			parent.sy.messagerAlert('提示', '请勾选要操作的记录！', 'error');
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
	<div data-options="region:'north',border:false,title:'过滤条件'" style="height: 80px;overflow: hidden;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">

				<tr>
					<th>系统用户名：</th>
					<td><input name="userName" id="userName" style="width:175px" />
					</td>
					<th>角色名:</th>
					<td><input name="roleName" id="roleName" style="width:175px" />
					</td>
				</tr>
				<tr>
					<th>状态:</th>
					<td><select name="relationStatus" style="width:100px" class="easyui-combobox"
						data-options="required:true , editable:false, panelHeight:65">
							<option value="-1" selected="true">全部</option>
							<option value="1">正常</option>
							<option value="0">注销</option>
					</td>
					<td></td>
					<td></select> <a href="javascript:void(0);" class="easyui-linkbutton" onclick="searchSth();">查询</a> <a
						href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a></td>
				</tr>

			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="changeStatus();" data-options="iconCls:'icon-remove'">注销</div>

		<div onclick="edit();" data-options="iconCls:'icon-edit'">修改</div>
	</div>
</body>
</html>
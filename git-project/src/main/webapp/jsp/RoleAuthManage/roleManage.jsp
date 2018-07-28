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
			url : '${pageContext.request.contextPath}/manageRoleAuthAction!getRoleDatagrid.action',
			title : '角色列表',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			idField : 'roleId',
			sortName : 'roleId',
			sortOrder : 'asc',
			checkOnSelect : false,
			selectOnCheck : true,
			frozenColumns : [ [ {
				title : '编号',
				field : 'roleId',
				width : 150,
				sortable : true,
				checkbox : true
			} ] ],
			columns : [ [ {
				title : '角色名',
				field : 'roleName',
				width : 80
			}, {
				title : '描述',
				field : 'roleDescription',
				width : 400
			}, {
				title : '状态',
				field : 'roleStatus',
				width : 80,
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
						if (rows[i].roleId == rowData.roleId) {
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
				if (row.roleStatus == 0) {
					return 'color:#FF0000;';
				}
			}
		
		});
		
		$('#roleName').combobox({
			url : sy.bp() + '/manageRoleAuthAction!getAuthRoleNames.action',
			mode : 'remote',
			delay : 500,
			valueField : 'roleName',
			required : true,
			textField : 'roleName',
		});
	});
	
	// 修改，不支持批量
	function edit() {
		var rows = datagrid.datagrid('getChecked');
		var nameValide = false;
		if (rows.length == 1) {
			var p = parent.sy.dialog({
				title : '修改权限资源信息',
				href : '${pageContext.request.contextPath}/manageRoleAuthAction!roleEdit.action',
				width : 750,
				height : 400,
				resizable : true,
				buttons : [ {
					text : '修改',
					handler : function() {
						var f = p.find('form');
						if (f.form('validate') && nameValide) {
							nameValide = false;
							f.form('submit', {
								url : '${pageContext.request.contextPath}/manageRoleAuthAction!editRoleRecord.action',
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
					f.form('load', {
						'roleId' : rows[0].roleId,
						'roleName' : rows[0].roleName,
						'roleDescription' : rows[0].roleDescription,
						'roleStatus' : rows[0].roleStatus,
						'myRoleName' : rows[0].roleName
					});
					
					var myRoleName = f.find('input[name=myRoleName]');
					
					var roleNameVB = f.find('input[name=roleName]').validatebox({
						required : true
					});
					
					var nameCheck = f.find('span[name=nameCheck]');
					f.find('input[name=roleDescription]').validatebox({
						required : true
					});
					
					/* 	urlCombobox.combobox('', function() {
						 = f.find('input[name=privilegeName]')	
						}); */

					roleNameVB.bind('focus keyup blur', function() {
						$.ajax({
							url : sy.bp() + '/manageRoleAuthAction!hasTheRoleDiff.action',
							dataType : 'json',
							data : {
								q : roleNameVB.val(),
								roleName : myRoleName.val()
							},
							success : function(data) {
								if (data.success) {
									//资源名已存在
									nameValide = false;
									nameCheck.text(data.msg);
									
								} else {
									nameValide = true;
									nameCheck.text(data.msg);
								}
							},
							
							error : function(data) {
								nameValide = false;
								nameCheck.text(data.msg + "请重新输入！");
							}
						});
					});
					
					f.find('input[name=roleStatus]').combobox({
						required : true,
						panelHeight : 40
					});
					
					roleNameVB.trigger('focus');
					
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
						ids.push(rows[i].roleId);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/manageRoleAuthAction!changeRoleStatus.action',
						data : {
							ids : ids.join(','),
							roleStatus : changeToStatus
						},
						dataType : 'json',
						success : function(d) {
							cancelSelect(datagrid);
							datagrid.datagrid('load');
							parent.sy.messagerShow({
								title : '提示',
								msg : d.msg
							});
						},
						error : function(data) {
							roleNameValide = false;
							$('#nameCheck').text("传输数据失败");
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
					<th>角色名：</th>
					<td><input id="roleName" name="roleName" style="width:200px;" /></td>
					<th>状态:</th>
					<td><select id="roleStatus" name="roleStatus" style="width:100px" class="easyui-combobox"
						data-options="required:true , editable:false, panelHeight:65">
							<option value="-1" selected="true">全部</option>
							<option value="1">正常</option>
							<option value="0">注销</option>

					</select> <a href="javascript:void(0);" class="easyui-linkbutton" onclick="searchSth();">查询</a> <a
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
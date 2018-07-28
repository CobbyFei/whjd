<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var treegrid;
	var roleNameCom;
	var iconData;
	$(function() {
		iconData = [ {
			value : '',
			text : '默认'
		}, {
			value : 'icon-add',
			text : 'icon-add'
		}, {
			value : 'icon-edit',
			text : 'icon-edit'
		}, {
			value : 'icon-remove',
			text : 'icon-remove'
		}, {
			value : 'icon-save',
			text : 'icon-save'
		}, {
			value : 'icon-cut',
			text : 'icon-cut'
		}, {
			value : 'icon-ok',
			text : 'icon-ok'
		}, {
			value : 'icon-no',
			text : 'icon-no'
		}, {
			value : 'icon-cancel',
			text : 'icon-cancel'
		}, {
			value : 'icon-reload',
			text : 'icon-reload'
		}, {
			value : 'icon-search',
			text : 'icon-search'
		}, {
			value : 'icon-print',
			text : 'icon-print'
		}, {
			value : 'icon-help',
			text : 'icon-help'
		}, {
			value : 'icon-undo',
			text : 'icon-undo'
		}, {
			value : 'icon-redo',
			text : 'icon-redo'
		}, {
			value : 'icon-back',
			text : 'icon-back'
		}, {
			value : 'icon-sum',
			text : 'icon-sum'
		}, {
			value : 'icon-tip',
			text : 'icon-tip'
		} ];
		
		treegrid = $('#treegrid')
				.treegrid(
						{
							url : '${pageContext.request.contextPath}/menuAction!treegrid.action',
							toolbar : [ {
								text : '展开全部',
								iconCls : 'icon-redo',
								handler : function() {
									//	var node = treegrid.treegrid('getSelected');
									treegrid.treegrid('expandAll');
									/* if (node) {
										treegrid.treegrid('expandAll', node.id);
									} else {
										treegrid.treegrid('expandAll');
									} */
								}
							}, '-', {
								text : '折叠全部',
								iconCls : 'icon-undo',
								handler : function() {
									//var node = treegrid.treegrid('getSelected');
									treegrid.treegrid('collapseAll');
									/* if (node) {
										treegrid.treegrid('collapseAll', node.id);
									} else {
										treegrid.treegrid('collapseAll');
									} */
								}
							}, '-', {
								text : '刷新',
								iconCls : 'icon-reload',
								handler : function() {
									treegrid.treegrid('reload');
								}
							}, '-', {
								text : '取消选中',
								iconCls : 'icon-undo',
								handler : function() {
									treegrid.treegrid('unselectAll');
								}
							}, '-' ],
							title : '',
							iconCls : 'icon-save',
							fit : true,
							fitColumns : true,
							nowrap : false,
							animate : false,
							border : false,
							singleSelect : false,
							idField : 'id',
							treeField : 'text',
							frozenColumns : [ [ {
								title : 'id',
								field : 'id',
								width : 150,
								hidden : true
							}, {
								field : 'text',
								title : '菜单名称',
								width : 250,
								formatter : function(value) {
									if (value) {
										return sy.fs('<span title="{0}">{1}</span>', value, value);
									}
								}
							} ] ],
							columns : [ [
									{
										field : 'iconCls',
										title : '菜单图标',
										width : 70,
										formatter : function(value) {
											if (!value) {
												return '';
											} else {
												return sy
														.fs(
																'<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>',
																value);
											}
										}
									}, {
										field : 'url',
										title : '菜单地址',
										width : 250,
										formatter : function(value) {
											if (value) {
												return sy.fs('<span title="{0}">{1}</span>', value, value);
											}
										}
									}, {
										field : 'seq',
										title : '排序',
										width : 40
									}, {
										field : 'pid',
										title : '上级菜单ID',
										width : 150,
										hidden : true
									}, {
										field : 'pname',
										title : '上级菜单',
										width : 150
									} ] ],
							onLoadSuccess : function(row, data) {
								var t = $(this);
								t.treegrid('expandAll');
								/*
								if (data) {
									$(data).each(function(index, d) {
										if (this.state == 'closed') {
											t.treegrid('expandAll');
										}
									});
								}*/
							},
							onExpand : function(row) {
								/* treegrid.treegrid('unselectAll'); */
							},
							onCollapse : function(row) {
								/* treegrid.treegrid('unselectAll'); */
							},
							onClickRow : function(row) {
								var selNodes = treegrid.treegrid('getSelections');
								var ids = [];
								for ( var i = 0; i < selNodes.length; i++) {
									ids[i] = selNodes[i].id;
								}
								
								if ($.inArray(row.id, ids) != -1) {
									//自己未被选中
									//取得父节点
									var fNode = treegrid.treegrid('getParent', row.id);
									if (fNode) {
										//如果有父节点，则吧父节点添加
										treegrid.treegrid('select', fNode.id);
									}
									
									//取得子节点
									var cNodes = treegrid.treegrid('getChildren', row.id);
									if (cNodes) {
										//如果有子节点，则把所有子节点添加
										for ( var i = 0; i < cNodes.length; i++) {
											treegrid.treegrid('select', cNodes[i].id);
										}
									}
								} else {
									//自己被选中，现在准备取消选中
									
									//取得子节点
									var cNodes = treegrid.treegrid('getChildren', row.id);
									if (cNodes) {
										//如果有子节点，则把所有子节点添加
										for ( var i = 0; i < cNodes.length; i++) {
											treegrid.treegrid('unselect', cNodes[i].id);
										}
									}
									
								}
								
							}
						});
		
		roleNameCom = $('#roleName').combobox({
			url : sy.bp() + '/manageRoleAuthAction!getRoleNames.action',
			mode : 'remote',
			delay : 500,
			valueField : 'roleId',
			required : true,
			textField : 'roleName',
			onSelect : function(record) {
				$.ajax({
					url : '${pageContext.request.contextPath}/manageRoleAuthAction!getRolePrivilegs.action',
					data : {
						roleId : roleNameCom.combobox('getValue')
					},
					type : 'POST',
					cache : false,
					dataType : 'JSON',
					success : function(data) {
						if (data.success) {
							treegrid.treegrid('selectAll');
							var ids = data.ids;
							var nodesAll = treegrid.treegrid('getSelections');
							for ( var i = 0; i < nodesAll.length; i++) {
								if ($.inArray(nodesAll[i].id, ids) == -1) {
									//如果id不在ids数组中,则表明不该选中他
									treegrid.treegrid('unselect', nodesAll[i].id);
								}
							}
						} else {
							parent.sy.messagerShow({
								msg : "获取权限失败",
								title : '提示'
							});
						}
						
					},
					error : function(data) {
						roleNameValide = false;
						$('#nameCheck').text("获取权限失败");
					}
				});
			}
		
		});
		
	});
	
	function addRolePrivileg() {
		var nodes = treegrid.treegrid('getSelections');
		var rolename = roleNameCom.combobox('getText');
		var roleid = roleNameCom.combobox('getValue');
		var ids = [];
		if (nodes && roleid) {
			parent.sy.messagerConfirm('询问', '您确定要添加选中资源给角色 ' + rolename + ' 吗？', function(b) {
				if (b) {
					for ( var i = 0; i < nodes.length; i++) {
						//添加自己
						var thisId = nodes[i].id;
						if ($.inArray(thisId, ids) == -1) {
							//如果自己的id还未添加到ids数组中
							ids.push(thisId);
						}
						
						//取得父节点
						var fNode = treegrid.treegrid('getParent', thisId);
						if (fNode) {
							//如果有父节点，则吧父节点添加
							if ($.inArray(fNode.id, ids) == -1) {
								//如果父节点id还未添加到ids数组中
								ids.push(fNode.id);
							}
						}
						
						/* //取得子节点
						var cNodes = treegrid.treegrid('getChildren', thisId);
						if (cNodes) {
							//如果有子节点，则把所有子节点添加
							for ( var i = 0; i < cNodes.length; i++) {
								if ($.inArray(cNodes[i].id, ids) == -1) {
									//如果子节点id还未添加到ids数组中
									ids.push(cNodes[i].id);
								}
							}
						} */

					}
					$.ajax({
						url : '${pageContext.request.contextPath}/manageRoleAuthAction!addRolePrivilegs.action',
						data : {
							ids : ids.join(','),
							roleId : roleid
						},
						type : 'POST',
						cache : false,
						dataType : 'JSON',
						success : function(r) {
							if (r.success) {
								treegrid.treegrid('unselectAll');
								treegrid.treegrid('reload');
								roleNameCom.combobox('clear');
							}
							parent.sy.messagerShow({
								msg : r.msg,
								title : '提示'
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
			parent.sy.messagerShow({
				msg : '请选择角色和资源！',
				title : '提示'
			});
		}
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'选择角色'" style="height: 56px;overflow: hidden;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th>角色名：</th>
					<td><input id="roleName" name="roleName" style="width:200px;" />
					</td>
					<td></select> <a href="javascript:void(0);" class="easyui-linkbutton" onclick="addRolePrivileg();">添加权限资源</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="treegrid"></table>
	</div>
</body>
</html>
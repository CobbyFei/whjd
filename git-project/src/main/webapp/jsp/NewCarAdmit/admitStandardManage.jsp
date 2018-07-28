<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>准入标准管理</title>

<script type="text/javascript" charset="utf-8">
var datagrid;
$(function() {
	datagrid = $('#datagrid')
	.datagrid(
			{
				url : '${pageContext.request.contextPath}/admitStandardAction!getAdmitStandard.action',
				title : '区域列表',
				iconCls : 'icon-save',
				pagination : true,
				pagePosition : 'bottom',
				pageSize : 10,
				pageList : [ 10, 20, 30, 40 ],
				fit : true,
				fitColumns : false,
				nowrap : false,
				border : false,
				idField : 'iD',
				sortName : 'ID',
				sortOrder : 'asc',
				checkOnSelect : false,
				selectOnCheck : true,
				frozenColumns : [ [ {
					title : '编号',
					field : 'iD',
					width : 150,
					sortable : true,
					checkbox : true
				} ] ],
				columns : [ [
						{
							title : '车辆类别',
							field : 'cLLB',
							width : 150
						},
						{
							title : '用途',
							field : 'yT',
							width : 150,
						},
						{
							title : '准入标准',
							field : 'zRBZ',
							width : 150,
							formatter : function(value, rowData,
									rowIndex) {
								if (value == 1) { 
									return "<span> " + "国1"
											+ "</span>";
								} else if (value == 2) { 
									return "<span>" + "国2"
											+ "</span>";
								}else if (value == 3) { 
									return "<span>" + "国3"
									+ "</span>";
								}else if (value == 4) { 
									return "<span>" + "国4"
									+ "</span>";
								}else if (value == 5) { 
									return "<span>" + "国5"
									+ "</span>";
								}else if (value == 6) { 
									return "<span>" + "国6"
									+ "</span>";
								}
							}
						},
						{
							title : '实施时间',
							field : 'sSSJ',
							width : 150
						}
						] ],

				toolbar : [ {
					text : '修改',
					iconCls : 'icon-edit',
					handler : function() {
						edit();
					}
				},'-',{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						add();
					}
				},'-',{
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						remove();
					}
				}, 
				],
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
						for (var i = 0; i < rows.length; i++) {
							if (rows[i].ID == rowData.ID) {
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
			});
});
function remove() {
	var rows = datagrid.datagrid('getChecked');
	var ids = [];
	if (rows.length > 0) {
		parent.sy
				.messagerConfirm(
						'请确认',
						'确定要删除所选记录？',
						function(r) {
							if (r) {
								for ( var i = 0; i < rows.length; i++) {
									ids.push(rows[i].iD);
								}
								if (ids.length == 0) {
									$.messager.show('提示', '所选信息均已删除',
											'error');
									return;
								}
								$
										.ajax({
											url : sy.bp()
													+ '/admitStandardAction!deleteAdmitStandardValue.action',
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
	if(rows.length == 1){
		var p = parent.sy.dialog({
			title : '修改车辆准入条件库',
			href : '${pageContext.request.contextPath}/admitStandardAction!admitStandardEdit.action',
			width : 750,
			height : 250,
			resizable : true,
			buttons : [ {
				text : '修改',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/admitStandardAction!editAdmitStandardRecord.action',
						dataType:'json',
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
				f.form('load', {
					'ID' : rows[0].iD,
					'CLLB' : rows[0].cLLB,
					'YT' : rows[0].yT,
					'ZRBZ' : rows[0].zRBZ,
					'SSSJ' : rows[0].sSSJ,
				});
				
			}
		});
	} else if (rows.length > 1) {
		parent.sy.messagerAlert('提示', '同一时间只能编辑一条记录！', 'error');
	} else {
		parent.sy.messagerAlert('提示', '请选择要编辑的记录！', 'error');
	}
}

function cancelSelect(datagrid) {
	// 跨页数据也支持，只支持jQuery-easyui-1.3.2
	datagrid.datagrid('clearSelections');
	datagrid.datagrid('clearChecked');
}

function add(){
	var p = parent.sy.dialog({
		title : '新增车辆准入条件库',
		href : '${pageContext.request.contextPath}/admitStandardAction!openAddAdmitStandardPage.action',
		width : 750,
		height : 250,
		resizable : true,
		buttons : [ {
			text : '新增',
			handler : function() {
				var f = p.find('form');
				f.form('submit', {
					url : '${pageContext.request.contextPath}/admitStandardAction!AddAdmitStandard.action',
					dataType:'json',
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
	});
}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>
</body>
</html>
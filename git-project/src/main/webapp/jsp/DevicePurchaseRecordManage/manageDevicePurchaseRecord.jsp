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
			$("#inspectionStationId").combobox('setValue', str);
			$("#inspectionStationId").combobox({
				disabled:true
			});
		}else{
		$("#inspectionStationId").combobox({
			url : sy.bp() + '/inspectionStationAction!getInspectionStationName.action',
			mode : 'remote',
			delay : 500,
			valueField : 'stationId',
			required : true,
			textField : 'stationName',
		});
		}
		datagrid = $('#datagrid').datagrid({
			url : '${pageContext.request.contextPath}/devicePurchaseRecordAction!getAllDevicePurchaseRecord.action?userStationName=${sessionScope.stationName}',//
			title : '设备采购管理记录列表',
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
				width : 100,
				sortable : true,
				checkbox : true
			} ] ],
			columns : [ [ {
				title : '检测设施名称',
				field : 'deviceName',
				width : 130
			}, {
				title : '制造厂商',
				field : 'manufacturer',
				width : 100
			}, {
				title : '型号',
				field : 'deviceModel',
				width : 111
			} , {
				title : '规格',
				field : 'specification',
				width : 111
			}, {
				title : '采购数量',
				field : 'purchaseNum',
				width : 80
			} , {
				title : '采购日期',
				field : 'selectPurchaseTime',
				width : 120
			} , {
				title : '所属检测站',
				field : 'stationName',
				width : 100
			} , {
				title : '备注',
				field : 'remarks',
				width : 580
			}  ] ],
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
			}
		});
	});
	
	// 修改，不支持批量
	
	function edit() {
		var rows = datagrid.datagrid('getChecked');
		if (rows.length == 1) {
			var p = parent.sy.dialog({
				title : '编辑设备采购记录',
				href : '${pageContext.request.contextPath}/devicePurchaseRecordAction!editDevicePurchaseRecord.action',
				width : 650,
				height : 400,
				resizable : true,
				buttons : [ {
					text : '修改',
					iconCls:'icon-edit',
					handler : function() {
						var f = p.find('form');
						f.form('submit', {
							url : '${pageContext.request.contextPath}/devicePurchaseRecordAction!updateDevicePurchaseRecord.action',
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
						recordId : rows[0].recordId,
						deviceName: rows[0].deviceName,
						manufacturer : rows[0].manufacturer,
						deviceModel : rows[0].deviceModel,
						purchaseNum : rows[0].purchaseNum,
						selectPurchaseTime : rows[0].selectPurchaseTime,
						stationName : rows[0].stationName,
						remarks : rows[0].remarks
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
			parent.sy.messagerConfirm('请确认', '您要删除当前所选设备采购记录？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].recordId);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/devicePurchaseRecordAction!deleteDevicePurchaseRecord.action',
						data : {
							ids:ids.join(',')	
						},
						dataType:'json',
						success:function(d) {
							cancelSelect(datagrid);
							datagrid.datagrid('load',sy.serializeObject($("#searchForm")));
							sy.messagerShow({
								title:'提示',
								msg:d.msg
							});
						}
					});
				}
			}); 
		} else {
			parent.sy.messagerAlert('警告','请勾选要删除的设备采购记录！','error');
		}
	}
	//导出信息
	function myExport(){
	    var rows = datagrid.datagrid('getData').rows;
		var ids = [];
		if (rows.length > 0) {
		   $.messager.confirm('提示','确定要导出设备采购记录？',function(r){
		      for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].recordId);
			 }
				$.ajax({
					url : sy.bp() + '/devicePurchaseRecordAction!exportDevicePurchaseRecord.action',
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
					<th>检测站名称</th>
					<td><input name="inspectionStationId" id="inspectionStationId" style="width:200px" class="easyui-combobox"/></td>
					<th>采购日期范围</th>
					<td><input type="text" id="beforePurchaseTime" name="beforePurchaseTime" class="easyui-datebox" style="width:170px" required="true" editable="false"/>至 
					<input type="text" id="afterPurchaseTime" name="afterPurchaseTime" class="easyui-datebox" style="width:170px" required="true" editable="false"/>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search"
						onclick="searchSth();">查询</a> <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="remove();" data-options="iconCls:'icon-remove'">删除</div>
		<div onclick="edit();" data-options="iconCls:'icon-edit'">编辑</div>
	</div>
</body>
</html>
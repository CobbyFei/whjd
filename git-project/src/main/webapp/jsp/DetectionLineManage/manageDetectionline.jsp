<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>检测线信息管理</title>
<script type="text/javascript">
	var datagrid;
	var is_data = [ {
		"value" : "1",
		"label" : "注销"
	}, {
		"value" : "0",
		"label" : "正常"
	} ];
	
	function is_unitformatter(value, rowData, rowIndex) {
		for ( var j = 0; j < is_data.length; j++) {
			if (is_data[j].value == value)
				return is_data[j].label;
		}
		return is_data[0].label;
	}
	
	$(function() {
	    var stationId='${sessionScope.stationId}';
	    var stationName='${sessionScope.stationName}';
	    if(stationId!="0")
	    {
	       $("#stationName").combobox('setValue',stationName);
	       $("#stationName").combobox('setText',stationName);
	       $("#stationName").combobox({
	           disabled:true
	       });
	       
			$("#lineName").combobox({
				url : sy.bp() + '/detectionLineAction!getDetectionLineName.action?stationName=${sessionScope.stationName}',
				mode : 'remote',
				delay : 500,
				valueField : 'lineName',
				required : true,
				textField : 'lineName'
			});
	    }
	    else
	    {
		    $("#stationName").combobox(
						{
							url : sy.bp() + '/inspectionStationAction!getInspectionStationName.action',
							mode : 'remote',
							delay : 500,
							valueField : 'stationName',
							required : true,
							textField : 'stationName',
							onSelect : function(rowData) {
								var url = sy.bp() + '/detectionLineAction!getDetectionLineName.action?stationName='
										+ rowData.stationName;
								$("#lineName").combobox('reload', url);
							}
						});
		    $("#lineName").combobox({
				url : sy.bp() + '/detectionLineAction!getDetectionLineName.action',
				mode : 'remote',
				delay : 500,
				valueField : 'lineName',
				required : true,
				textField : 'lineName'
			
			});
	    }
			
		
		datagrid = $("#datagrid").datagrid({
			url : '${pageContext.request.contextPath}/detectionLineAction!getAllDetectionLine.action',
			title : '检测线信息列表',
			iconCls : 'icon-save',
			pagination : true,
			pagePosition : 'bottom',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			fit : true,
			fitColumns : false,
			nowrap : false,
			border : false,
			idField : 'lineId',
			sortName : 'lineId',
			sortOrder : 'asc',
			checkOnSelect : false,
			selectOnCheck : true,
			frozenColumns : [ [ {
				title : '编号',
				field : 'lineId',
				width : 150,
				sortable : true,
				checkbox : true
			} ] ],
			columns : [ [ {
				title : '检测站编号',
				field : 'stationId',
				width : 100,
				formatter:function(value, rowData, rowIndex){
						if(value==0){		//检测站未知
							return "<font color='red'>"+value+"</font>";
						}else{ 
							return value;
						}
				}
			}, {
				title : '检测站名称',
				field : 'stationName',
				width : 150,
				formatter:function(value, rowData, rowIndex){
						if(value.trim() =="未知检测站"){		//检测站未知
							return "<font color='red'>"+value+"</font>";
						}else{ 
							return value;
						}
				}
			}, {
				title : '检测线名称',
				field : 'lineName',
				width : 120
			}, {
				title : '站内编号',
				field : 'localeId',
				width : 100
			}, {
				title : '最大检测量',
				field : 'maxDetectionNum',
				width : 80
			}, {
				title : '状态',
				field : 'lineStatus',
				width : 80,
				formatter : is_unitformatter,
				editor : {
					type : 'combobox',
					options : {
						required : true,
						editable : false,
						valueField : 'value',
						textField : 'label',
						data : is_data
					}
				}
			} ] ],
			toolbar : [ {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-', {
				text : '注销',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			
			}, '-', {
			    text:'导出',
			    iconCls:'icon-redo',
			    handler:function(){
			          myExport();
			    }
			},'-',{
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					cancelSelections(datagrid);
				}
			}, '-',{
			    text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					searchData();
				}
			} ],
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$("#menu").menu('show', {
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
			rowStyler:function(index,row){
				     if(row.lineStatus==1)
				     {
				        return "color:red";
				     }
				     
				}
		
		});
		
	});
	function searchData() {
		datagrid.datagrid('load', sy.serializeObject($("#searchForm")));
	}
	function clearSearch() {
		datagrid.datagrid('load', {});
		$("#searchForm").form("reset");
		
	}
	
	function cancelSelections(datagrid) {
		datagrid.datagrid('clearSelections');
		datagrid.datagrid('clearChecked');
	}
	
	function remove() {
		var rows = datagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('请确认', '确认要注销选中检测线信息?', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						if (rows[i].lineStatus == "0") {
							ids.push(rows[i].lineId);
						}
					}
					if (ids.length == 0) {
						$.messager.alert('提示', '所选检测线信息均已注销', 'error');
						return;
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/detectionLineAction!cancelDetectionLine.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(d) {
							if (d.success) {
								sy.messagerAlert('提示', '注销检测线信息信息成功', 'info');
								datagrid.datagrid('load', sy.serializeObject($("#searchForm")));
								cancelSelections(datagrid);
								P.dialog('close');
							}
						}
					});
					
				}
				
			});
		}
		
	}
	
	//导出信息
	function myExport(){
	    var rows = datagrid.datagrid('getData').rows;
		var ids = [];
		if (rows.length > 0) {
		   $.messager.confirm('提示','确定要导出检测线信息？',function(r){
		      for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].lineId);
			 }
				$.ajax({
					url : sy.bp() + '/detectionLineAction!exportDetectionLine.action',
					data : {
						ids : ids.join(',')
					},
					dataType : 'json',
					success : function(d) {
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
	//编辑信息
	function edit() {
		var rows = datagrid.datagrid("getChecked");
		if (rows.length == 1) {
		    if(rows[0].stationName.trim()=="未知检测站")
		    {
		         $.messager.alert('提示','该线所在检测站信息已经被删除，此线作废，不能编辑','error');
		    }
		    else{
			        var p = sy.dialog({
					title : '修改检测线信息',
					href : '${pageContext.request.contextPath}/detectionLineAction!editDetectioLine.action',
					width : 500,
					height : 450,
					resizable : true,
					buttons : [ {
						text : '修改',
						iconCls : 'icon-edit',
						handler : function() {
							var f = p.find('form');
							f.form('submit', {
								url : '${pageContext.request.contextPath}/detectionLineAction!modifyDetectioLine.action',
							/* 	onSubmit : function() {
									var isValid = f.form('validate');
									if (flag1 && flag2 && isValid)
										return true;
									else {
										$.messager.alert('提示', "站内编号或者检测线名称不可用", 'error');
										return false;
									}
								}, */
								success : function(d) {
									
									var json = $.parseJSON(d);
									
									if (json.success) {
										$.messager.alert('提示', '修改检测线信息成功', 'info');
										datagrid.datagrid('load', sy.serializeObject($("#searchForm")));
										cancelSelections(datagrid);
										p.dialog('close');
										
									} else {
										/* p.dialog('close'); */
										$.messager.alert('提示', json.msg, 'error');
									}
									
								}
							});
						}
					} ],
					onLoad : function() {
						var flag1 = false;
						var flag2 = false;
						var f = p.find('form');
						f.form('load', {
							lineId : rows[0].lineId,
							stationId : rows[0].stationId,
							stationName : rows[0].stationName,
							lineName : rows[0].lineName,
							localeId : rows[0].localeId,
							maxDetectionNum : rows[0].maxDetectionNum,
							lineStatus : rows[0].lineStatus
						});
						
					}
				});
		    
		    }

		} else if (rows.length > 1) {
			sy.messagerAlert('提示', '同一时间只能编辑同一条记录', 'error');
		} else {
			sy.messagerAlert('提示', '请选择要编辑的记录', 'error');
		}
		
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'过滤条件'" style="height:100px;overflow:hidden;width:700px"
		align="left">
		<form id="frm_down" method="post"
			action="/whjd/downloadFileAction!export.action" target="_self">
			<input id="filePath" name="filePath" type="hidden" />
		</form>
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width:100%;height:100%">
				<tr>
					<th>检测站名称:</th>
					<td><input name="stationName" id="stationName" class="easyui-combobox" data-options="" style="width:175px" />
					</td>
					<th>检测线名称:</th>
					<td><input name="lineName" id="lineName" class="easyui-combobox" style="width:175px" /></td>
				</tr>
				<tr>
					<th>检测线状态:</th>
					<td><select id="lineStatus" name="lineStatus" class="easyui-comobox" style="width:175px">
							<option value="2" selected="true">--全部--</option>
							<option value="1">注销</option>
							<option value="0">正常</option>
					</select></td>
					<th algin="right"><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search"
						onclick="searchData()">查询</a>
					</th>
					<td align="left">&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel"
						onclick="clearSearch()">清空条件</a></td>
				</tr>
			</table>

		</form>

	</div>

	<div data-options="region:'center',border:false" style="overflow:hidden">
		<table id="datagrid"></table>
	</div>
	<div id="menu" class="easyui-menu" style="width:120px;display:none">
		<div onclick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
		<div onclick="remove()" data-options="iconCls:'icon-remove'">注销</div>
	</div>
</body>
</html>
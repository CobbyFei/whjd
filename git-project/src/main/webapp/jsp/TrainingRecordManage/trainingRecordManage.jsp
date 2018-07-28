<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var datagrid;
	$(function() {
		//判断用户身份并对单位stationName搜索框进行相应处理
		var sessionStationName = '${sessionScope.stationName}';
		if(sessionStationName != "市局"){
			$("#stationName").combobox({
				disabled:true
			});
			$("#stationName").combobox('setValue',sessionStationName);
		}
	
		datagrid = $('#datagrid').datagrid({
			url : '${pageContext.request.contextPath}/trainingRecordAction!getTrainingRecordDatagrid.action',
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
			idField : 'trainingRecordId',
			sortName : 'trainingRecordId',
			sortOrder : 'asc',
			checkOnSelect : false,
			selectOnCheck : true,
			frozenColumns : [ [ {
				title : '编号',
				field : 'trainingRecordId',
				width : 150,
				sortable : true,
				checkbox : true
			} ] ],
			columns : [ [ {
				title : '姓名',
				field : 'userName',
				width : 80
			}, {
				title : '所在单位',
				field : 'stationName',
				width : 150,
				formatter:function(value, rowData, rowIndex){
						if(rowData.stationStatus == 0){		//单位正常
							return "<span> "+value+"</span>";
						}else if (rowData.stationStatus == 1){ //单位注销
							return "<span style='color: #FF0000'>"+value+"</span>";
						}
				}
			} , {
				title:'单位编号',
				field:'stationId',
				hidden : true
			}, {
				title : '状态',
				field : 'trainingRecordStatus',
				width : 80,
				formatter:function(value, rowData, rowIndex){
						if(value == 0){		//记录正常
							return "<span> "+"正常"+"</span>";
						}else if (value== 1){ //记录注销
							return "<span>"+"注销"+"</span>";
						}
				}
			},{
				title : '时间',
				field : 'strTrainingTime',
				width : 80
			},{
				title : '地点',
				field : 'trainingAddress',
				width : 80
			},{
				title : '类别',
				field : 'trainingType',
				width : 80,
				formatter:function(value, rowData, rowIndex){
						if(value == 0){		//用户正常
							return "<span> "+"培训"+"</span>";
						}else if (value== 1){ //用户注销
							return "<span>"+"考核"+"</span>";
						}
				}
			},{
				title : '培训类容',
				field : 'trainingDetail',
				width : 400
			}] ],
			
			toolbar : [ {
				text : '注销',
				iconCls : 'icon-remove',
				handler : function() {
					var changeToStatus = 1;
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
					var changeToStatus = 0;
					changeStatus(changeToStatus);
				}
			}, '-', {
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					datagrid.datagrid('reload');
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
						if (rows[i].userId == rowData.userId) {
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
			
			rowStyler: function(index, row){
				if (row.trainingRecordStatus == 1) {
					return 'color:#FF0000;';
				}
			}
			
		});
	});
	
	// 修改，不支持批量
	function edit() {
		var rows = datagrid.datagrid('getChecked');
		if(rows.length == 1){
			var p = parent.sy.dialog({
				title : '修改记录信息',
				href : '${pageContext.request.contextPath}/trainingRecordAction!trainingRecordEdit.action',
				width : 750,
				height : 600,
				resizable : true,
				buttons : [ {
					text : '修改',
					handler : function() {
						var f = p.find('form');
						f.form('submit', {
							url : '${pageContext.request.contextPath}/trainingRecordAction!editTrainingRecord.action',
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
						'trainingRecordId' : rows[0].trainingRecordId,
						'stationId' : rows[0].stationId,
						'userId' : rows[0].userId,
						'userName' : rows[0].userName,
						'strTrainingTime' : rows[0].strTrainingTime,
						'trainingAddress' : rows[0].trainingAddress,
						'trainingType': rows[0].trainingType,
						'trainingDetail' : rows[0].trainingDetail
					});
					
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
			var infoCancel ='您要注销当前所选项目？';
			var infoRecover ='您要恢复当前所选项目？';
			
			if(changeToStatus == 0){
				param = infoRecover;
			}else{
				param = infoCancel;
			}
			parent.sy.messagerConfirm('请确认', param, function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].trainingRecordId);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/trainingRecordAction!changeTrainingRecordStatus.action',
						data : {
							ids : ids.join(','),
							changeToStatus : changeToStatus
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
					<th>姓名:</th>
					<td><input name="userName" style="width:200px;" /></td>
					<th>单位:</th>
					<td><input name="stationName" id ="stationName" style="width:260px;" /></td>
					<th>状态:</th>
					<td>
						<select name="trainingRecordStatus" style="width:50px" class="easyui-combobox" data-options="required:true , editable:false, panelHeight:65">
							<option value="-1"selected="true">全部</option>
							<option value="0">正常</option>
							<option value="1">注销</option>
						</select>
						
						<a href="javascript:void(0);" class="easyui-linkbutton"
							onclick="searchSth();">查询</a> <a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a>
					</td>
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

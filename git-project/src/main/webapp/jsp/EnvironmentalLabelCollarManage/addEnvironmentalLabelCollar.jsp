<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript" charset="utf-8">
var flag=false;
	$(function() {
		$("#userId").combobox({
			url : sy.bp() + '/manageUserAction!getSysUsers.action',
			mode : 'remote',
			delay : 500,
			valueField : 'userId',
			required : true,
			textField : 'userName',
			editable:false
		});
		$("#stationId").combobox({
			url : sy.bp() + '/inspectionStationAction!getInspectionStationName.action',
			mode : 'remote',
			delay : 500,
			valueField : 'stationId',
			required : true,
			textField : 'stationName',
			onSelect : function(rec) {
				var url = sy.bp()
						+ '/manageUserAction!getSysUsers.action?stationId='
						+ rec.stationId;
				$("#userId").combobox('reload', url);
			}
		});
		$('#CancelBtn').bind('click', function() {
			cancel();
		});
		$('#AddBtn').bind('click', function() {
			savereg();
		});
		$("#notice").html("<font color='red'><b>标志领用数量请参照列表中显示的相应年份的库存标志数量</b><font>");
		
		datagrid = $('#datagrid').datagrid(
				{
					url : '${pageContext.request.contextPath}/environmentalLabelCollarAction!getLeaveEnvironmentalLabelCollar.action',//
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
						title : '标志年度号',
						field : 'year',
						width : 100
					},  {
						title : '库存绿标数',
						field : 'greenLabelNum',
						width : 110
					}, {
						title : '库存黄标数',
						field : 'yellowLabelNum',
						width : 110
					} ] ],
					toolbar : [ {
						text : '取消选中',
						iconCls : 'icon-undo',
						handler : function() {
							cancelSelect(datagrid);
						}
					}, '-',{
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
	
	

	function cancel() {
		$('#addEnvironmentalLabelCollarInfo').form('clear');
	}
	function searchSth() {
		datagrid.datagrid('load', sy.serializeObject($('#searchForm')));
	}

	function savereg() {

	$('#addEnvironmentalLabelCollarInfo').form('submit',
				{
				url : sy.bp()+ '/environmentalLabelCollarAction!addEnvironmentalLabelCollar.action',
				onSubmit : function() {
				if ($('#stationId').combobox('getText').trim() == '') {
					$.messager.alert('提示', '请选择标志核发点名称！', 'info');
					return false;
					}
				if ($('#userId').combobox('getText').trim() == '') {
					$.messager.alert('提示', '请选择领用人！', 'info');
					return false;
					}
				return $('#addEnvironmentalLabelCollarInfo').form('validate');
					},
					success : function(data) {
						var result = eval("(" + data + ")");
						if (result.success) {
							$.messager.alert('提示！', '添加标志领用记录成功', 'info');
								cancel();
							} else {
							$.messager.alert('添加标志领用记录失败！', result.msg,'error');
								}
							}
				});
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
	<div data-options="region:'north',border:false,title:'市局环保标志使用记录'" style="overflow: hidden;height: 300px" align="left">
		<form id="addEnvironmentalLabelCollarInfo">
			<table style="width:800px; height:300px; padding-top:10px" cellspacing="5">
				<tr height="30px">
					<td align="right" style="width：20%">核发点名称：</td>
					<td align="left" style="width：30%"><input name="stationId" id="stationId" style="width:175px" />
					</td>
					<td align="right" style="width：20%">领用人：</td>
					<td align="left" style="width：30%"><input name="userId" id="userId" style="width:175px" />
					</td>
				</tr>
				<tr height="30px">
					<td align="right">领用时间：</td>
					<td align="left"><input type="text" name="collarTime" id="collarTime" class="easyui-datebox"  style="width:175px" required="true" editable="false"/></td>
				<td align="right">标志年度号：</td>
					<td align="left"><input name="year" id="year" class="easyui-numberbox" data-options="min:0" style="width:175px" required="true"/></td>
				</tr>

				<tr height="30px">
					<td align="right">绿标数量：</td>
					<td align="left"><input name="greenLabelNum" id="greenLabelNum" class="easyui-numberbox" data-options="value:0,min:0,groupSeparator:','" style="width:175px" required="true"/></td>
					<td align="right">黄标数量：</td>
					<td align="left"><input name="yellowLabelNum" id="yellowLabelNum" class="easyui-numberbox" data-options="value:0,min:0,groupSeparator:','" style="width:175px" required="true"/></td>
				</tr>
				
				<tr height="10px">
					<td align="right">备&nbsp;&nbsp;注：</td>
					<td align="left" colspan="3">
					<textarea style="height:60px;font-size:12px" id="remarks" name="remarks" cols="47" rows="2" maxlength="50"></textarea></td>
				</tr>
				<tr height="10px">
				<td align="right">友情提示：</td>
				<td align="left"><span id="notice"></span></td>
					<td></td>
					<td><a class="easyui-linkbutton" icon="icon-add" id="AddBtn" name="Add" href="javascript:void(0)" >添加</a>
					&nbsp;&nbsp;
					<a class="easyui-linkbutton" icon="icon-reload" id="CancelBtn" name="Refresh" href="javascript:void(0)">重置</a>
					</td>
				</tr>
				<tr height="30px">

				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>

</body>
</html>

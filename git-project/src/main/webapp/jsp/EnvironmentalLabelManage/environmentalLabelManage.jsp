<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript" src="../../jslib/LodopFuncs.js"></script>
<script type="text/javascript" src="../../jslib/printer.js"></script>
<object id="LODOP_OB"
	classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0
		pluginspage="install_lodop32.exe"></embed>
</object>

<script type="text/javascript" charset="utf-8">
	var datagrid;
	$(function() {
		var str = '${sessionScope.stationName}';
		if (str != "市局") {
			//alert(str);
			$("#inspectionStationId").combobox('setValue', str);
			$("#inspectionStationId").combobox({
				disabled : true
			});
		} else {
			$("#inspectionStationId")
					.combobox(
							{
								url : sy.bp()
										+ '/inspectionStationAction!getInspectionStationName.action',
								mode : 'remote',
								delay : 500,
								valueField : 'stationId',
								required : false,
								textField : 'stationName',
							});
		}
		datagrid = $('#datagrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/environmentalLabelAction!getEnvironmentalLabel.action?userStationName=${sessionScope.stationName}',//
							title : '环保标志发放记录列表 ',
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
								title : '标志编号',
								field : 'labelId',
								width : 100
							}, {
								title : '车牌号',
								field : 'licence',
								width : 100
							}, {
								title : '检测站',
								field : 'stationName',
								width : 100
							}, {
								title : '车主',
								field : 'vehicleOwner',
								width : 80
							}, {
								title : '车辆识别码',
								field : 'vinNo',
								width : 100
							}, {
								title : '车辆型号',
								field : 'vehicleModelCode',
								width : 100
							}, {
								title : '车辆类型',
								field : 'vehicleClassification',
								width : 70
							}, {
								title : '车辆登记日期',
								field : 'vehicleRegisterTime',
								width : 120
							}, {
								title : '燃油类型',
								field : 'fuelType',
								width : 70
							}, {
								title : '排放标准',
								field : 'emissionStandard',
								width : 100,
								formatter : function(value, rowData, rowIndex) {
									return "国" + value;
								}
							}, {
								title : '核发日期',
								field : 'issueDate',
								width : 120
							}, {
								title : '有效期',
								field : 'validPeriod',
								width : 120
							},  {
								title : '是否已打印',
								field : 'isPrint',
								width : 100
							}, {
								title : '状态',
								field : 'isCancel',
								width : 60
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
								text : '打印',
								iconCls : 'icon-print',
								handler : function() {
									print();
								}
							}, '-', {
								text : '取消选中',
								iconCls : 'icon-undo',
								handler : function() {
									cancelSelect(datagrid);
								}
							}, '-', {
								text : '刷新',
								iconCls : 'icon-reload',
								handler : function() {
									searchSth();
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
							rowStyler : function(index, row) {
								if (row.isCancel == "注销") {
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
		if (rows.length == 1) {
			var p = parent.sy
					.dialog({
						title : '编辑标志数据',
						href : '${pageContext.request.contextPath}/environmentalLabelAction!editEnvironmentalLabel.action',
						width : 900,
						height : 300,
						resizable : true,
						buttons : [ {
							text : '确认修改',
							iconCls : 'icon-edit',
							handler : function() {
								var f = p.find('form');
								f
										.form(
												'submit',
												{
													url : '${pageContext.request.contextPath}/environmentalLabelAction!updateEnvironmentalLabel.action',
													success : function(d) {
														var json = $
																.parseJSON(d);
														if (json.success) {
															cancelSelect(datagrid);
															datagrid
																	.datagrid('reload'); // 完成之后刷新列表
															p.dialog('close');
														}
														parent.sy
																.messagerShow({
																	msg : json.msg,
																	title : '提示'
																});
													}
												});
							}
						} ],

						onLoad : function() {
							var f = p.find('form');
							f
									.form(
											'load',
											{
												id : rows[0].id,
												stationName : rows[0].stationName,
												labelId : rows[0].labelId,
												licence : rows[0].licence,
												vehicleModelCode : rows[0].vehicleModelCode,
												vehicleBrand : rows[0].vehicleBrand,
												fuelType : rows[0].fuelType,
												vehicleClassification : rows[0].vehicleClassification,
												vehicleOwner : rows[0].vehicleOwner,
												vinNo : rows[0].vinNo,
												emissionStandard : rows[0].emissionStandard,
												issueDate : rows[0].issueDate,
												validPeriod : rows[0].validPeriod,
												vehicleRegisterTime : rows[0].vehicleRegisterTime,
												distributionCertTime : rows[0].distributionCertTime,
												labelType : rows[0].labelType,
												isPrint : rows[0].isPrint,
												isCancel : rows[0].isCancel
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
			parent.sy
					.messagerConfirm(
							'请确认',
							'您要对当前标志发放数据记录注销？',
							function(r) {
								if (r) {
									for ( var i = 0; i < rows.length; i++) {
										ids.push(rows[i].id);
									}
									$
											.ajax({
												url : '${pageContext.request.contextPath}/environmentalLabelAction!cancelEnvironmentalLabel.action',
												data : {
													ids : ids.join(',')
												},
												dataType : 'json',
												success : function(d) {
													cancelSelect(datagrid);
													datagrid
															.datagrid(
																	'load',
																	sy
																			.serializeObject($("#searchForm")));
													sy.messagerShow({
														title : '提示',
														msg : d.msg
													});
												}
											});
								}
							});
		} else {
			parent.sy.messagerAlert('警告', '请勾选要注销的的标志发放记录！', 'error');
		}
	}
	//打印，只允许一条
	function print() {
		var rows = datagrid.datagrid('getChecked');
		if (rows.length == 1) {
			var p = parent.sy
					.dialog({
						title : '打印检验合格通知单',
						href : sy.bp()
								+ '/environmentalLabelAction!labelPrint.action?id='
								+ rows[0].id,
						width : 1000,
						height : 600,
						resizable : true,
						buttons : [ {
							text : '打印',
							handler : function() {
								var LODOP = getLodop(document
										.getElementById('LODOP_OB'), document
										.getElementById('LODOP_EM'));
								//var LODOP = getCLodop();
								
								var flag1 = false;
								var flag2 = false;
								LODOP.PRINT_INITA(0, 0, "210mm", "130mm", "打印合格通知单");
								var f = p.find('div[id=licenceReal]');
								LODOP.SET_PRINT_PAGESIZE(1, "210mm", "130mm", "打印合格通知单");
								LODOP.ADD_PRINT_HTM("0mm", "0mm", "98%", "98%", f.html());
								LODOP.PRINT_DESIGN();
								flag1 = true;
								if (flag1 == true) {
									$
											.ajax({
												url : sy.bp()
														+ '/environmentalLabelAction!setPrinted.action?id='
														+ rows[0].id,
												dataType : 'json',
												success : function(d) {
													cancelSelect(datagrid);
													datagrid
															.datagrid(
																	'load',
																	sy
																			.serializeObject($("#searchForm")));
													sy.messagerShow({
														title : '提示',
														msg : d.msg
													});
												}
											});
								}
							}
						} ]
					});
		} else if (rows.length > 1) {
			messagerAlert('提示', '一次只能打印一条记录！', 'error');
		} else {
			messagerAlert('提示', '请选择要打印的记录！', 'error');
		}
	}
	function formatStandard(value) {
		var showValue = "国";
		if (value == 1)
			showValue += "Ⅰ";
		if (value == 2)
			showValue += "Ⅱ";
		if (value == 3)
			showValue += "Ⅲ";
		if (value == 4)
			showValue += "Ⅳ";
		return showValue;
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
	<div data-options="region:'north',border:false,title:'查询环保标志发放记录'"
		style="overflow:hidden;height:140px" align="left">
		<form id="searchForm">
			<table style="padding-top:10px" cellspacing="5">
				<tr>
					<td align="right">标志编号</td>
					<td align="left"><input id="labelId" name="labelId"
						style="width:120px" /></td>
					<td align="right">车牌号</td>
					<td align="left"><input id="licence" name="licence"
						style="width:120px" /></td>
					<td align="right">车主姓名</td>
					<td align="left"><input id="vehicleOwner" name="vehicleOwner"
						style="width:120px" /></td>
					<td align="right">标志状态</td>
					<td align="left"><select name="isCancel" id="isCancel"
						style="width:75px">
							<option value="全部">--全部--</option>
							<option value='注销'>注销</option>
							<option value='正常' selected>正常</option>
					</select>
					</td>
				</tr>
				<tr>
					<td align="right">检测站名称</td>
					<td align="left"><input name="inspectionStationId"
						id="inspectionStationId" style="width:120px"
						class="easyui-combobox" />
					</td>
					<!-- 
					<td align="right">标志类型</td>
					<td align="left"><select id="labelType" name="labelType"
						style="width:120px">
							<option value="全部" selected>--全部--</option>
							<option value='绿标'>绿标</option>
							<option value='黄标'>黄标</option>
					</select></td>
					 -->
					<td align="right">打印情况</td>
					<td align="left"><select name="isPrint" id="isPrint"
						style="width:75px">
							<option value="全部" selected>--全部--</option>
							<option value='已打印'>已打印</option>
							<option value='未打印'>未打印</option>
					</select>
					</td>
				</tr>
				<tr>
					<td align="right">发证时间</td>
					<td align="left"><input name="beforeIssueDate"
						style="width:120px" class="easyui-datebox" editable="false" />
					</td>
					<td>到</td>
					<td align="left"><input name="afterIssueDate"
						style="width:120px" class="easyui-datebox" editable="false" /></td>
					<td></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-search" onclick="searchSth()">查询</a>
					<td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-cancel" onclick="cleanSearch()">清空</a></td>
				</tr>

			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow:hidden">
		<table id="datagrid"></table>
	</div>
	<div id="menu" class="easyui-menu" style="width:120px;display:none;">
		<div onclick="remove();" data-options="iconCls:'icon-remove'">注销</div>
		<div onclick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
	</div>
</body>
</html>
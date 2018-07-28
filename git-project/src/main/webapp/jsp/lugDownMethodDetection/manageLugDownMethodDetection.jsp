<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script src='http://localhost:8000/CLodopfuncs.js'></script>
<!--  
<script type="text/javascript" src="../../jslib/LodopFuncs.js"></script>
<script type="text/javascript" src="../../jslib/printer.js"></script>
<object id="LODOP_OB"
	classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0
		pluginspage="install_lodop32.exe"></embed>
</object>
-->
<script type="text/javascript" charset="utf-8">
	var datagrid;
	var stationName = '${sessionScope.stationName}';
	$(function() {
		if (stationName == "市局") {
			$("#StationName").val("");
			$("#StationName")
					.combobox(
							{
								url : sy.bp()
										+ '/inspectionStationAction!getInspectionStationName.action',
								mode : 'remote',
								delay : 200,
								valueField : 'stationName',
								required : false,
								textField : 'stationName'
							});
		} else {
			$("#StationName").val(stationName);
			$("#StationName").combobox({
				disabled : true
			});
		}
		datagrid = $('#datagrid')
				.datagrid(
						{
							url : sy.bp()
									+ '/lugDownMethodAction!getAllMethodDetections.action',
							title : '委托单基本信息',
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
							columns : [ [
									{
										title : '检测报告编号',
										field : 'reportNumber',
										width : 150
									},
									{
										title : '委托单状态',
										field : 'commisionSheetStatus',
										width : 80,
										formatter : function(value, rowData,
												rowIndex) {
											if (value == 0) {
												return "<font color='red'>已注销</font>";
											}
											if (value == 1) {
												return "未检测";
											}
											if (value == 2) {
												return "已检测";
											}
											if (value == 3) {
												return "<font color='green'>已完工</font>";
											}
										},
										editor : {
											type : 'combobox',
											options : {
												required : true,
												editable : false,
												valueField : 'value',
												textField : 'label'
											}
										}
									},
									{
										title : '检测结果',
										field : 'conclusion',
										width : 100,
										formatter : function(value, rowData,
												rowIndex) {
											if (value == 0) {
												return "<font color='green'>合格</font>";
											}
											if (value == 1) {
												return "<font color='red'>不合格</font>";
											}
											if (value == 2) {
												return "未下结论";
											}
										}
									},
									{
										title : '所属检测站',
										field : 'stationName',
										width : 100
									},
									{
										title : '车牌号',
										field : 'licence',
										width : 100
									},
									{
										title : '车辆识别码',
										field : 'vin',
										width : 80
									},
									{
										title : '检测时间',
										field : 'strDetectionTime',
										width : 150
									},
									{
										title : '车主姓名',
										field : 'vehicleOwnerName',
										width : 80
									},
									{
										title : '车主联系方式',
										field : 'phoneNum',
										width : 80
									},
									{
										title : '车主地址',
										field : 'vehicleOwnerAddress',
										width : 150
									},
									{
										title : '发动机号',
										field : 'engineCode',
										width : 150
									},
									{
										title : '发动机型号',
										field : 'vehicleModelCode',
										width : 100
									},
									{
										title : '车辆型号',
										field : 'vehicleModelCode',
										width : 100
									},
									{
										title : '燃油类型',
										field : 'fuelType',
										width : 80
									},
									{
										title : '基准质量（公斤）',
										field : 'baseQuality',
										width : 100
									},
									{
										title : '最大总质量(单位:Kg)',
										field : 'maxTotalQuality',
										width : 100
									},
									{
										title : '车身长度',
										field : 'vehicleLength',
										width : 80
									},
									{
										title : '发动机排量（升）',
										field : 'engineemissionAmount',
										width : 100
									},
									{
										title : '累计行驶里程（公里）',
										field : 'totalMiles',
										width : 120
									},
									{
										title : '车辆登记日期',
										field : 'strVehicleRegisterDate',
										width : 100
									},
									{
										title : '排放标准',
										field : 'emissionStandard',
										width : 100
									},
									{
										title : '车辆类型',
										field : 'vehicleType',
										width : 100,
										formatter : function(value, rowData,
												rowIndex) {
											if (value == 0) {
												return "新车";
											}
											if (value == 1) {
												return "外地车";
											}
											if (value == 2) {
												return "其它";
											}
										},
										editor : {
											type : 'combobox',
											options : {
												required : true,
												editable : false,
												valueField : 'value',
												textField : 'label'
											}
										}
									},
									{
										title : '发标类型',
										field : 'labelDistributeType',
										width : 100,
										formatter : function(value, rowData,
												rowIndex) {
											if (value == 0) {
												return "核发";
											}
											if (value == 1) {
												return "换发";
											}
											if (value == 2) {
												return "补发";
											}
										},
										editor : {
											type : 'combobox',
											options : {
												required : true,
												editable : false,
												valueField : 'value',
												textField : 'label'
											}
										}
									}, {
										title : '检测方法名称',
										field : 'detectionMethod',
										width : 100
									}, {
										title : '年度检测次数',
										field : 'yearCount'
									}, {
										title : '检测员',
										field : 'inspectorName',
										width : 100
									}, {
										title : '审核员',
										field : 'accessorName',
										width : 100
									}, {
										title : '批准人',
										field : 'approverName',
										width : 100
									} ] ],
							toolbar : [ {
								text : '批量下结论',
								iconCls : 'icon-tip',
								handler : function() {
									conclusion();
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
									clean();
								}
							} ],
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
							rowStyler : function(index, row) {
								if (row.commisionSheetStatus == 3) {
									return "color:green";
								} else if (row.commisionSheetStatus == 4) {
									return "color:red";
								}
							}
						});
	});
	function conclusion() {
		var rows = datagrid.datagrid('getChecked');
		//alert(rows[0].id);
		var ids = [];
		if (rows.length > 0) {
			parent.sy
					.messagerConfirm(
							'请确认',
							'您要对所选数据记录下结论？',
							function(r) {
								if (r) {
									for ( var i = 0; i < rows.length; i++) {
										if (rows[i].conclusion == 2
												&& rows[i].commisionSheetStatus == 2)
											ids.push(rows[i].id);
									}
									if (ids.length == 0) {
										$.messager.alert('提示',
												'所选数据记录均已有结论或未检测，无法下结论',
												'error');
										return;
									}
									$
											.ajax({
												url : sy.bp()
														+ '/lugDownMethodAction!batchConclusion.action',
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
																			.serializeObject($("#frm_search_record")));
													sy.messagerShow({
														title : '提示',
														msg : d.msg
													});
												}
											});
								}
							});
		} else {
			parent.sy.messagerAlert('警告', '请勾选要下结论的检测数据！', 'error');
		}
	}
	function print() {
		var rows = datagrid.datagrid('getChecked');
		if (rows.length == 1) {
			if (rows[0].commisionSheetStatus != 3) {
				$.messager.alert('提示', '所选数据记录未完工或已注销，无法打印', 'error');
				return;
			}
			var p = parent.sy.dialog({
				title : '打印检测报告',
				href : sy.bp() + '/lugDownMethodAction!methodPrint.action?id='
						+ rows[0].id,
				width : 1000,
				height : 500,
				resizable : true,
				buttons : [ {
					text : '打印',
					handler : function() {
						var LODOP = getCLodop();
						LODOP.PRINT_INIT("HELLO");
						var f = p.find('form[id=frm_to_print]');
						LODOP.ADD_PRINT_HTM(150, "1%", "98%", "98%", f.html());
						LODOP.PREVIEW();
					}
				} ]
			});
		} else if (rows.length > 1) {
			messagerAlert('提示', '一次只能打印一条记录！', 'error');
		} else {
			messagerAlert('提示', '请选择要打印的记录！', 'error');
		}
	}
	function edit() {
		if (stationName == "市局") {
			parent.sy.messagerAlert('提示', '数据记录只能由检测站人员修改！', 'error');
		} else {
			var rows = datagrid.datagrid('getChecked');
			if (rows.length == 1) {
				if (rows[0].commisionSheetStatus == 3) {
					$.messager.alert('提示', '所选数据记录已完工，无法编辑', 'error');
					return;
				}
				if (rows[0].reportStatus == 1 || rows[0].reportStatus == 2) {
					$.messager.alert('提示', '正在等待检测，无法编辑', 'error');
					return;
				}
				var p = parent.sy
						.dialog({
							title : '编辑检测信息',
							href : sy.bp()
									+ '/lugDownMethodAction!methodEdit.action',
							width : 1000,
							height : 500,
							resizable : true,
							buttons : [
									{
										text : '确认修改',
										handler : function() {
											var f = p.find('form');
											f
													.form(
															'submit',
															{
																url : sy.bp()
																		+ '/lugDownMethodAction!updateMethod.action',
																success : function(
																		d) {
																	var json = $
																			.parseJSON(d);
																	if (json.success) {
																		cancelSelect(datagrid);
																		datagrid
																				.datagrid('reload');
																		p
																				.dialog('close');
																	}
																	parent.sy
																			.messagerShow({
																				msg : json.msg,
																				title : '提示'
																			});
																}
															});
										}
									},
									{
										text : '开始检测',
										iconCls : 'icon-redo',
										handler : function() {
											var f = p.find('form');
											var rStatus = rows[0].reportStatus;
											if (rStatus == 1 || rStatus == 2) {
												parent.sy.messagerAlert('提示',
														'已在检测队列！', 'error');
												p.dialog('close');
												return;
											}
											f
													.form(
															'submit',
															{
																url : sy.bp()
																		+ '/lugDownMethodAction!beginDetection.action',
																success : function(
																		d) {
																	var json = $
																			.parseJSON(d);
																	if (json.success) {
																		cancelSelect(datagrid);
																		datagrid
																				.datagrid('reload'); // 完成之后刷新列表
																		p
																				.dialog('close');
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
													reportNumber : rows[0].reportNumber,
													conclusion : rows[0].conclusion,
													licence : rows[0].licence,
													vehicleOwnerName : rows[0].vehicleOwnerName,
													methodId : rows[0].methodId,
													lineId : rows[0].lineId,
													vehicleManufacturer : rows[0].vehicleManufacturer,
													fuelGrade : rows[0].fuelGrade,
													airInletMode : rows[0].airInletMode,
													fuelSupplySystemModel : rows[0].fuelSupplySystemModel,
													intEmissionProcessDevice : rows[0].intEmissionProcessDevice,
													testDeviceModel : rows[0].testDeviceModel,
													wheelNums : rows[0].wheelNums,
													temperature : rows[0].temperature,
													airPressure : rows[0].airPressure,
													relativeHumidity : rows[0].relativeHumidity,
													hundredPoint : rows[0].hundredPoint,
													nintyPoint : rows[0].nintyPoint,
													eightyPoint : rows[0].eightyPoint,
													kwResult : rows[0].kwResult,
													kwLimit : rows[0].kwLimit,
													inspectorId : rows[0].inspectorId,
													accessorId : rows[0].accessorId,
													approverId : rows[0].approverId
												});
							}
						});
			} else if (rows.length > 1) {
				parent.sy.messagerAlert('提示', '同一时间只能编辑一条记录！', 'error');
			} else {
				parent.sy.messagerAlert('提示', '请选择要编辑的记录！', 'error');
			}
		}
	}
	//search是javascript的字符匹配方法
	function searchSth() {
		datagrid.datagrid('load', sy.serializeObject($('#frm_search_record')));
	}
	function clean() {
		datagrid.datagrid('load', {});
		$('#frm_search_record').form('reset');
	}
	function cancelSelect(datagrid) {
		datagrid.datagrid('clearSelections');
		datagrid.datagrid('clearChecked');
	}
</script>
</head>

<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查询自由加速法检测记录'"
		style="overflow:hidden;height:160px" align="left">
		<form id="frm_search_record">
			<input name="detectionMethod" id="DetectionMethod" type="hidden"
				value="自由加速法" />
			<table style="padding-top:10px" cellspacing="5">
				<tr>
					<td align="right">报告编号</td>
					<td><input id="ReportNumber" name="reportNumber"
						style="width:175px" />
					</td>
					<td align="right">车牌号</td>
					<td><input id="Licence" name="licence" style="width:120px" />
					</td>
					<td>车主姓名</td>
					<td><input id="VehicleOwnerName" name="vehicleOwnerName"
						style="width:120px" />
					</td>
					<td>车架号</td>
					<td><input id="vin" name="vin" style="width:150px" />
					</td>
				</tr>			
				<tr>
					<td>车辆登记日期从</td>
					<td><input name="beginRegisterDate" style="width:120px"
						class="easyui-datebox" />到
					<input name="endRegisterDate" style="width:120px"
						class="easyui-datebox" />
					</td>
					<td>最大总质量</td>
					<td><input name="maxTotalQualityLowerBound" id="maxTotalQualityLowerBound" style="width:80px"/>到
					<input name="maxTotalQualityUpperBound" id="maxTotalQualityUpperBound" style="width:80px"/>
					</td>
					 <td>排放标准</td>
				    <td><select name="emissionStandard" id="emissionStandard" style="width:120px">
				    <option value ="-1">--全部--</option>
					  <option value ="1">国1</option>
                      <option value ="2">国2</option>
                      <option value ="3">国3</option>
                      <option value ="4">国4</option>
                      <option value ="5">国5</option>
                      <option value ="6">国6</option>
					</select></td>
				</tr>
				<tr>
					<td align="right">检测时间从</td>
					<td><input name="beginTime" style="width:120px"
						class="easyui-datebox" />到
					<input name="endTime" style="width:120px"
						class="easyui-datebox" />
					</td>
					<td align="right">状态</td>
					<td align="left"><select name="commisionSheetStatus"
						id="CommisionSheetStatus" style="width:120px"><option
								value="-1" selected>--全部--</option>
							<option value='0'>已注销</option>
							<option value='1'>未检测</option>
							<option value='2'>已检测</option>
							<option value='3'>已完工</option>
					</select></td>
					<td>检测结果</td>
					<td><select name="conclusion" id="conclusion" style="width:120px">
				    <option value ="-1">--全部--</option>
					  <option value ="0">合格</option>
                      <option value ="1">不合格</option>
                      <option value ="2">未下结论</option>
					</select></td>
				</tr>
				<tr>
					<td align="right">所属检测站</td>
					<td><input name="stationName" id="StationName"
						style="width:175px" /></td>
					<td></td>
					<td></td>
					<td></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-search" onclick="searchSth()">查询</a>&nbsp;&nbsp;<a
						href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-cancel" onclick="clean()">清空</a></td>
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

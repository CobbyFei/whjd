<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="../../inc.jsp"></jsp:include>
<!-- <script src='http://localhost:8000/CLodopfuncs.js'></script>-->

<script type="text/javascript" src="../../jslib/LodopFuncs.js"></script>
<script type="text/javascript" src="../../jslib/printer.js"></script>
<object id="LODOP_OB"
	classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0
		pluginspage="install_lodop32.exe"></embed>
</object>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>新车准入管理</title>

<script type="text/javascript" charset="utf-8">
	var datagrid;
	$(function() {
		//判断用户身份并对单位stationName搜索框进行相应处理
		var sessionStationName = '${sessionScope.stationName}';
		if (sessionStationName != "市局") {
			$("#fillStationName").combobox({
				disabled : true
			});
			$("#fillStationName").combobox('setValue', sessionStationName);
		}else{
			$("#fillStationName").val("");
			$("#fillStationName")
					.combobox(
							{
								url : sy.bp()
										+ '/inspectionStationAction!getInspectionStationName.action',
								mode : 'remote',
								delay : 200,
								valueField : 'stationName',
								required : false,
								textField : 'stationName',
							});
		}

		//datagrid相关
		datagrid = $('#datagrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/newCarAdmitAction!getNewCarAdmitSheet.action',
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
										title : '证明编号',
										field : 'certificateID',
										width : 150
									},
									{
										title : '车主姓名',
										field : 'vehicleOwnerName',
										width : 100,
									},
									{
										title : '车辆来源',
										field : 'vehicleType',
										width : 100,
										formatter : function(value, rowData,
												rowIndex) {
											if (value == 0) { 
												return "<span> " + "新注册车辆"
														+ "</span>";
											} else if (value == 1) { 
												return "<span>" + "外地转入车辆"
														+ "</span>";
											} else if (value == 2) { 
												return "<span>" + "新注册摩托车"
												+ "</span>";
											}
										}
										
									},
									{
										title : '车主住址',
										field : 'vehicleOwnerAddress',
										width : 150,
									},
									{
										title : '联系方式',
										field : 'phoneNum',
										width : 150
									},
									{
										title : '车辆识别码',
										field : 'vin',
										width : 150,
									},
									{
										title : '车牌号码',
										field : 'licence',
										width : 150,
									},
									{
										title : '车牌颜色',
										field : 'licenseColor',
										width : 150,
									},
									{
										title : '车辆型号',
										field : 'vehicleModelCode',
										width : 150,
									},
									{
										title : '发动机型号',
										field : 'engineModel',
										width : 80
									},
									{
										title : '发动机号',
										field : 'engineCode',
										width : 150
									},
									{
										title : '最大总质量(单位:Kg)',
										field : 'maxTotalQuality',
										width : 80
									},
									{
										title : '车辆出厂时间',
										field : 'vehicleRegisterDate',
										width : 100
									},
									{
										title : '燃料类型',
										field : 'fuelType',
										width : 80
									},
									{
										title : '排放标准',
										field : 'emissionStandard',
										width : 80,
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
										title : '准入排放标准',
										field : 'emissionAdmitStandard',
										width : 80,
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
										title : '发证时间',
										field : 'vehicleIssueCertDate',
										width : 80
									},
									{
										title : '发证单位',
										field : 'fillStationName',
										width : 80
									},
									{
										title : '是否打印',
										field : 'isPrint',
										width : 80
									},
									{
										title : '状态',
										field : 'status',
										width : 80,
										formatter : function(value, rowData,
												rowIndex) {
											if (value == 1) { 
												return "<span> " + "正常"
														+ "</span>";
											} else if (value == 0) { 
												return "<span>" + "注销"
														+ "</span>";
											}
										}
									}
									
									] ],

							toolbar : [ {
								text : '注销',
								iconCls : 'icon-remove',
								handler : function() {
									var changeToStatus = 0;
									changeStatus(changeToStatus);
								}
							}, '-', {
								text : '恢复',
								iconCls : 'icon-ok',
								handler : function() {
									var changeToStatus = 1;
									changeStatus(changeToStatus);
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

							rowStyler : function(index, row) {
								if (row.status == 0) {
									return 'color:#FF0000;';
								}
							}

						});
	});

	//打印，只允许一条
	function print() {
		var rows = datagrid.datagrid('getChecked');
		if (rows.length == 1) {
			var p = parent.sy
					.dialog({
						title : '打印准入证明',
						href : sy.bp()
								+ '/newCarAdmitAction!newCarAdmitPrint.action?ID='
								+ rows[0].iD,
						width : 1000,
						height : 600,
						resizable : true,
						buttons : [ {
							text : '打印',
							handler : function() {
							    var LODOP = getLodop(document
										.getElementById('LODOP_OB'), document
										.getElementById('LODOP_EM'));
								//var LODOP=getLodop();  
								//var LODOP = getCLodop();
								var flag1 = false;
								LODOP.PRINT_INITA(0, 0, "210mm", "130mm", "打印");
								var f = p.find('div[id=licenceReal]');
								LODOP.SET_PRINT_PAGESIZE(1, "210mm", "130mm", "打印准入证明");
								LODOP.ADD_PRINT_HTM("0mm", "0mm", "98%", "98%", f.html());
								LODOP.PRINT_DESIGN();
								flag1 = true;
								if (flag1 == true) {
									$.ajax({
												url : sy.bp()
														+ '/newCarAdmitAction!setPrinted.action?id='
														+ rows[0].id,
												dataType : 'json',
												success : function(d) {
													cancelSelect(datagrid);
													datagrid.datagrid('load',
																	sy.serializeObject($("#searchForm")));
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
			parent.sy
					.messagerConfirm(
							'请确认',
							param,
							function(r) {
								if (r) {
									for (var i = 0; i < rows.length; i++) {
										ids.push(rows[i].iD);
									}
									$
											.ajax({
												url : '${pageContext.request.contextPath}/newCarAdmitAction!changeNewCarAdmitStatus.action',
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
	<div data-options="region:'north',border:false,title:'过滤条件'"
		style="height: 120px; overflow: hidden;width:700px" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar"
				style="width: 100%; height: 100%;">
				<tr height="25px">
					<th>发证单位:</th>
					<td><input name="fillStationName" id="fillStationName"
						style="width: 200px;" /></td>
					<th>编号:</th>
					<td><input name="certificateID" id="certificateID"
						style="width: 200px;" /></td>
					<th>机动车所有人:</th>
					<td><input name="vehicleOwnerName" id="vehicleOwnerName"
						style="width: 200px;" /></td>
					<th>排放标准:</th>
					<td><select name="emissionStandard" style="width: 150px"
						class="easyui-combobox"
						data-options="required:true , editable:false, panelHeight:65">
							<option value="0" selected="true">请选择</option>
							<option value="1">国1</option>
							<option value="2">国2</option>
							<option value="3">国3</option>
							<option value="4">国4</option>
							<option value="5">国5</option>
							<option value="6">国6</option>

					</select></td>
				</tr>
				<tr height="25px">
					<th>车辆识别码:</th>
					<td><input name="vin" id="vin" style="width: 200px;" /></td>
					<th>发动机号:</th>
					<td><input name="engineCode" id="engineCode"
						style="width: 260px;" /></td>
					<th>发证日期：从</th>
					<td><input name="issueBeginTime" style="width: 175px"
						class="easyui-datebox" /></td>
					<th>到</th>
					<td><input name="issueEndTime" style="width: 175px"
						class="easyui-datebox" /></td>

				</tr>
				<tr height = "25px">
				<th>车牌号码:</th>
				<td><input name="licence" id="licence" style="width: 200px;" /></td>
				
				<th>车辆来源:</th>
					<td><select name="vehicleType" style="width: 150px"
						class="easyui-combobox"
						data-options="required:true , editable:false, panelHeight:65">
							<option value="-1" selected="true">请选择</option>
							<option value="0">新注册车辆</option>
							<option value="1">外地转入车辆</option>
							<option value="2">新注册摩托车</option>
					</select></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
						onclick="searchSth();">查询</a> <a href="javascript:void(0);"
						class="easyui-linkbutton" onclick="cleanSearch();">取消</a></td>
				</tr>
				
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>

</body>
</html>
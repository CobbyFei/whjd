<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
	<!--$(function() {
		var sessionStationName = '${sessionScope.stationName}';
		if(sessionStationName != "市局"){
			$("#stationName").combobox({
				disabled:true
			});
			$("#stationName").combobox('setValue',sessionStationName);
		}
	-->
	
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
			valueField : 'stationName',
			required : false,
			textField : 'stationName',
		});
		}
		
		$("#sysUserByInspecDriverName").combobox({
			url : sy.bp() + '/manageUserAction!getSysUsers.action',
			mode : 'remote',
			delay : 500,
			valueField : 'userId',
			required : false,
			textField : 'userName',
		});

		datagrid = $('#datagrid').datagrid(
						{
							url : '${pageContext.request.contextPath}/steadyStateMethodAction!getCommisionSheetsOfSteadyStateMethod.action',//
							title : '稳态工况法检测数据列表',
							iconCls : 'icon-save',
							pagination : true,
							pagePosition : 'bottom',
							pageSize : 10,
							pageList : [ 10, 20, 30, 40 ],
							fit : true,
							fitColumns : false,
							nowrap : false,
							border : false,
							idField : 'sheetId',
							sortName : 'sheetId',
							sortOrder : 'desc',
							checkOnSelect : false,
							selectOnCheck : true,
							frozenColumns : [ [ {
								title : '编号',
								field : 'sheetId',
								width : 100,
								sortable : true,
								checkbox : true
							} ] ],
							columns : [ [ {
								title : '检测报告编号',
								field : 'reportNumber',
								width : 120
							}, {
								title : '车牌号',
								field : 'licence',
								width : 100
							}, {
								title : '检测时间',
								field : 'detectionTime',
								width : 130
							}, {
								title : '检测站',
								field : 'stationName',
								width : 100
							},{
								title : '车主姓名',
								field : 'vehicleOwnerName',
								width : 80
							},
							{
								title : '车主联系方式',
								field : 'phoneNum',
								width : 80
							}, {
								title : '车主地址',
								field : 'vehicleOwnerAddress',
								width : 150
							},{
								title : '委托单状态',
								field : 'commisionSheetStatus',
								width : 100,
								formatter : function(value, rowData, rowIndex){
									if(value == 0){		
										return "<span style='color: #FF0000'>"+"已注销"+"</span>";
									}else if (value == 1){ //单位注销
										return "<span>"+"未检测"+"</span>";
									}else if (value == 2){
										return "<span>"+"已检测"+"</span>";
									}else if (value == 3){
										return "<span>"+"已完工"+"</span>";
									}
								}
							},{
								title : '结论',
								field : 'conclusion',
								width : 100,
								 formatter:function(value,rowData,rowIndex)
								 {
								       if(value==0)
								       {
								          return "合格";
								       }
								       else if(value==1)
								       {
								          return "不合格";
								       }
								       else
								       {
								         return "";
								       }
								  }
								
							},{
								title : '发动机号',
								field : 'engineCode',
								width : 150
							}, {
								title : '车辆识别码',
								field : 'vin',
								width : 150
							},{
								title : '车辆型号',
								field : 'vehicleModelCode',
								width : 100
							},{
								title : '发动机型号',
								field : 'engineModel',
								width : 100
							},{
								title : '燃油类型',
								field : 'fuelType',
								width : 70
							},{
								title : '基准质量',
								field : 'baseQuality',
								width : 70
							}, {
								title : '最大总质量(单位:Kg)',
								field : 'maxTotalQuality',
								width : 70
							},{
								title : '车长',
								field : 'vehicleLength',
								width : 70
							},{
								title : '发动机排量',
								field : 'engineemissionAmount',
								width : 70
							},{
								title : '累计里程数',
								field : 'totalMiles',
								width : 70
							},{
								title : '车辆注册时间',
								field : 'vehicleRegisterDate',
								width : 100
							},{
								title : '车型',
								field : 'vehicleType',
								width : 100
							},{
								title : '标志发放类型',
								field : 'labelDistributeType',
								width : 100,
								formatter : function(value, rowData, rowIndex){
									if(value == 0){		
										return "<span>"+"绿标"+"</span>";
									}else if (value == 1){ //单位注销
										return "<span>"+"黄标"+"</span>";
									}
								}
							},{
								title : '检测方法',
								field : 'detectionMethod',
								width : 100
							},{
								title : '本年度检测次数',
								field : 'yearCount',
								width : 100
							},{
								title : '排放标准',
								field : 'emissionStandard',
								width : 100
							},{
								title : '检测驾驶员id',
								hidden: true,
								field : 'inspecDriverId',
								width : 100
							},{
								title : '检测驾驶员姓名',
								field : 'inspecDriverName',
								width : 100
								
							},{
								title : '检测操作员id',
								hidden: true,
								field : 'inspecOperatorId',
								width : 100
							},{
								title : '检测操作员',
								field : 'inspecOperatorName',
								width : 100
								
							} , {
								title : '数据表id',
								hidden: true,
								field : 'recordId',
								width : 100
							} ,{
								title : '检测线id',
							   	hidden : true,
							   	field : 'lineId',
							   	width : 100
							},{
								title : '生产厂商',
								hidden : true,
								field : 'vehicleManufacturer',
								width : 100
							},{
								title : '发动机生产企业',
								field : 'engineManufacturer',
								hidden: true,
								width : 100
							}, {
								title : '燃油规格',
								field : 'fuelSpecification',
								hidden: true,
								width : 100
							}, {
								title : '单车轴重',
								field : 'singleAxleLoad',
								hidden: true,
								width : 100
							}, {
								title : '底盘型号',
								field : 'chassisModel',
								hidden: true,
								width : 100
							}, {
								title : '驱动方式',
								field : 'driveMode',
								hidden: true,
								width : 100
							}, {
								title : '驱动轮胎气压',
								field : 'tirePressure',
								hidden: true,
								width : 100
							}, {
								title : '变速器型式',
								field : 'transmissionForm',
								hidden: true,
								width : 100
							}, {
								title : '档位数',
								field : 'gearNumber',
								hidden: true,
								width : 100
							}, {
								title : '汽缸数',
								field : 'cylinderNumber',
								hidden: true,
								width : 50
							}, {
								title : '轮数',
								field : 'wheelNums',
								hidden: true,
								width : 50
							}, {
								title : '催化转化器情况',
								field : 'catalyticConverter',
								hidden: true,
								width : 120
							}, {
								title : '设备认证编码',
								field : 'deviceAuthNumber',
								hidden: true,
								width : 110
							}, {
								title : '设备名称',
								field : 'deviceName',
								hidden: true,
								width : 100
							}, {
								title : '设备型号',
								field : 'deviceModel',
								hidden: true,
								width : 100
							},{
								title : '设备制造商',
								field : 'deviceManufacturer',
								hidden: true,
								width : 100
							},{
								title : '底盘测功机',
								field : 'chassisDynamometer',
								hidden: true,
								width : 100
							},{
								title : '排气分析仪',
								field : 'exhaustGasAnalyser',
								hidden: true,
								width : 100
							},{
								title : '温度',
								field : 'temperature',
								hidden: true,
								width : 100
							},{
								title : '大气压',
								field : 'airPressure',
								hidden: true,
								width : 100
							},{
								title : '相对湿度',
								field :  'relativeHumidity',
								hidden : true,
								width : 100
							},{
								title : 'HCASM5025',
								field : 'wtHcAsm5025',
								hidden : true,
								width : 100
							},{
								title : 'HCASM2540',
								field : 'wtHcAsm2540',
								hidden : true,
								width : 100
							},{
								title : 'COASM5025',
								field : 'wtCoAsm5025',
								hidden : true,
								width : 100
							},{
								title : 'COASM2540',
								field : 'wtCoAsm2540',
								hidden : true,
								width : 100
							},{
								title : 'NOASM5025',
								field : 'wtNoAsm5025',
								hidden : true,
								width : 100
							},{
								title : 'NOASM2540',
								field : 'wtNoAsm2540',
								hidden : true,
								width : 100
							},{
								title : 'HCASM5025限值',
								field : 'wtHcAsm5025Limit',
								hidden : true,
								width : 100
							},{
								title : 'HCASM2540限值',
								field : 'wtHcAsm2540Limit',
								hidden : true,
								width : 100
							},{
								title : 'COASM5025限值',
								field : 'wtCoAsm5025Limit',
								hidden : true,
								width : 100
							},{
								title : 'COASM2540限值',
								field : 'wtCoAsm2540Limit',
								hidden : true,
								width : 100
							},{
								title : 'NOASM5025限值',
								field : 'wtNoAsm5025Limit',
								hidden : true,
								width : 100
							},{
								title : 'NOASM2540限值',
								field : 'wtNoAsm2540Limit',
								hidden : true,
								width : 100
							},{
								title : '检测状态',
								field : 'reportStatus',
								width : 80,
								formatter:function(value,rowData,rowIndex)
								   {
								      if(value==0)
								      {
								         return "未检测";
								      }
								      else if(value==1) {
								         return "已申请检测";
								      }else if(value==2){
								    	  return "已接受申请";
								      }else if(value==3){
								    	  return "已处理";
								      }else if(value==4){
								    	  return "出现错误";
								      }
								   } 
							}] ],
							toolbar : [ {
								text : '批量下结论',
								iconCls : 'icon-tip',
								handler : function() {
									batchConclusion();
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
							},'-', {
								text : '取消选中',
								iconCls : 'icon-undo',
								handler : function() {
									cancelSelect(datagrid);
								}
							}, '-', {
								text : '刷新',
								iconCls : 'icon-reload',
								handler : function() {
									cleanSearch();
								}
							}, '-'  ],
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
										if (rows[i].sheetId == rowData.sheetId) {
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
							rowStyler:function(index,row){
								if(row.reportStatus == 1||row.reportStatus == 2){
							     	return "color: green";
							     }
							     
							     if(row.commisionSheetStatus==0)
							     {
							        return "color:red";
							     }else if(row.commisionSheetStatus==2)
							     {
							        return "color: blue";
							     }else if(row.commisionSheetStatus==3){
							     	return "color:purple";
							     }
							}
						});
	});

	// 修改，不支持批量

	function edit() {
		var sessionStationNameEdit = '${sessionScope.stationName}';
		if(sessionStationNameEdit=='市局'){
				$.messager.alert('提示', '您不具有编辑权限!', 'error');
				return;
		}
		var rows = datagrid.datagrid('getChecked');
		if (rows.length == 1) {
			if (rows[0].commisionSheetStatus == 3) {
				$.messager.alert('提示', '所选数据记录已完工，无法编辑', 'error');
				return;
			}
			if (rows[0].commisionSheetStatus == 0) {
				$.messager.alert('提示', '所选数据记录已注销，无法编辑', 'error');
				return;
			}
			var p = parent.sy.dialog({
						title : '编辑稳态工况法检测数据',
						href : '${pageContext.request.contextPath}/steadyStateMethodAction!steadyStateMethodEdit.action',
						width : 1100,
						height : 600,
						maxmixzable : true,
						resizable : true,
						buttons : [ {
							text : '确认修改',
							iconCls : 'icon-edit',
							handler : function() {
								var f = p.find('form');
								f.form('submit',
												{
													url : '${pageContext.request.contextPath}/steadyStateMethodAction!editSteadyStateMethod.action',
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
						},{
							text : '开始检测',
							iconCls : 'icon-redo',
							handler : function() {
								var f = p.find('form');
								var sheetId=rows[0].sheetId;
								if(sheetId==1||sheetId==2){
									alert(sheetId);
									parent.sy.messagerAlert('提示', '已在检测队列！', 'error');
									p.dialog('close');
									return ;
								}
									
								f.form('submit',
												{
													url : '${pageContext.request.contextPath}/steadyStateMethodAction!beginDetection.action',
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

					f.form('load',
						{
						sheetId:rows[0].sheetId,
						//emissionStandard :rows[0].emissionStandard,
						recordId : rows[0].recordId,
						reportNumber: rows[0].reportNumber,
						licence: rows[0].licence,
						stationName: rows[0].stationName,
						vehicleOwnerName: rows[0].vehicleOwnerName,
						lineId:rows[0].lineId,
						vin:rows[0].vin,
						vehicleRegisterDate:rows[0].vehicleRegisterDate,
						vehicleModelCode:rows[0].vehicleModelCode,
						inspecDriverId:rows[0].inspecDriverId,
						inspecOperatorId:rows[0].inspecOperatorId,
						vehicleManufacturer:rows[0].vehicleManufacturer,
						engineManufacturer:rows[0].engineManufacturer,
						baseQuality:rows[0].baseQuality,
						maxTotalQuality:rows[0].maxTotalQuality,
						fuelSpecification:rows[0].fuelSpecification,
						singleAxleLoad:rows[0].singleAxleLoad,
						chassisModel:rows[0].chassisModel,
						driveMode:rows[0].driveMode,
						tirePressure:rows[0].tirePressure,
						transmissionForm:rows[0].transmissionForm,
						gearNumber:rows[0].gearNumber,
						engineModel:rows[0].engineModel,
						engineCode:rows[0].engineCode,
						driveMode:rows[0].driveMode,
						engineManufacturer:rows[0].engineManufacturer,
						fuelType:rows[0].fuelType,
						cylinderNumber:rows[0].cylinderNumber,
						wheelNums: rows[0].wheelNums,
						engineemissionAmount:rows[0].engineemissionAmount,
						catalyticConverter:rows[0].catalyticConverter,
						totalMiles:rows[0].totalMiles,
						deviceAuthNumber:rows[0].deviceAuthNumber,
						deviceName:rows[0].deviceName,
						deviceModel:rows[0].deviceModel,
						deviceManufacturer:rows[0].deviceManufacturer,
						chassisDynamometer:rows[0].chassisDynamometer,
						exhaustGasAnalyser:rows[0].exhaustGasAnalyser,
						temperature:rows[0].temperature,
						airPressure:rows[0].airPressure,
						relativeHumidity:rows[0].relativeHumidity,
						wtHcAsm5025:rows[0].wtHcAsm5025,
						wtHcAsm2540:rows[0].wtHcAsm2540,
						wtCoAsm5025:rows[0].wtCoAsm5025,
						wtCoAsm2540:rows[0].wtCoAsm2540,
						wtNoAsm5025:rows[0].wtNoAsm5025,
						wtNoAsm2540:rows[0].wtNoAsm2540,
						wtHcAsm5025Limit:rows[0].wtHcAsm5025Limit,
						wtHcAsm2540Limit:rows[0].wtHcAsm2540Limit,
						wtCoAsm5025Limit:rows[0].wtCoAsm5025Limit,
						wtCoAsm2540Limit:rows[0].wtCoAsm2540Limit,
						wtNoAsm5025Limit:rows[0].wtNoAsm5025Limit,
						wtNoAsm2540Limit:rows[0].wtNoAsm2540Limit,
						reportStatus:rows[0].reportStatus,
						conclusion:rows[0].conclusion,
						inspecDriverId:rows[0].inspecDriverId,
						inspecDriverName:rows[0].inspecDriverName,
						inspecOperatorName:rows[0].inspecOperatorName,
						inspecOperatorId:rows[0].inspecOperatorId,
						commisionSheetStatus:rows[0].commisionSheetStatus
						});
							/*f.find('table[name=pg]').propertygrid({
								url: '${pageContext.request.contextPath}/detectionCommisionSheetAction!getAllDetectionCommisionSheet.action?id='+rows[0].id,
								method:'remote',
								showGroup: true,
								scrollbarSize: 0,
								row:[[    
								          {name:'AddName',   
									           value:'',   
									           group:'Marketing Settings',   
									           editor:'text'},   
									           {name:'AddName',   
										           value:'',   
										           group:'Marketing Settings',   
										           editor:'text'}
								      ]] 

								});*/				
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示', '同一时间只能编辑一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示', '请选择要编辑的记录！', 'error');
		}
	}

	function print() {
		var rows = datagrid.datagrid('getChecked');
		if (rows.length == 1) {
			if (rows[0].commisionSheetStatus != 3) {
				$.messager.alert('提示', '所选数据记录未完工或已注销，无法打印', 'error');
				return;
			}
			var p = parent.sy
					.dialog({
						title : '打印检测报告',
						href : sy.bp()
								+ '/steadyStateMethodAction!methodPrint.action?sheetId='
								+ rows[0].sheetId,
						width : 1000,
						height : 500,
						resizable : true,
						buttons : [ {
							text : '打印',
							handler : function() {
								var LODOP = getCLodop();
								LODOP.PRINT_INIT("HELLO");
								var f = p.find('form[id=frm_to_print]');
								LODOP.ADD_PRINT_HTM(50, "1%", "98%", "98%", f.html());
								LODOP.PREVIEW();
							}
						} ]
					});
		} else if (rows.length > 1) {
			messagerAlert('提示', '一次只能打印一条记录！', 'error');
		} else {
			messagerAlert('提示', '请选择要编辑的记录！', 'error');
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
	function batchConclusion(){
		var rows = datagrid.datagrid('getChecked');
		//alert(rows[0].id);
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm(
							'请确认',
							'您要对该数据记录下结论？',
							function(r) {
								if (r) {
									for ( var i = 0; i < rows.length; i++) {
										if(rows[i].commisionSheetStatus == 2){
											ids.push(rows[i].sheetId);
										}
									}
									if(ids.length < 1){
										parent.sy.messagerAlert('警告', '请勾选状态为已检测的检测数据！', 'error');
										return  false;
									}
									$.ajax({
												url : '${pageContext.request.contextPath}/steadyStateMethodAction!batchConclusion.action',
												data : {
													ids : ids.join(',')
												},
												dataType : 'json',
												success : function(d) {
													cancelSelect(datagrid);
													datagrid.datagrid('load',sy.serializeObject($("#searchForm")));
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
	function cancelSelect(datagrid) {
		// 跨页数据也支持，只支持jQuery-easyui-1.3.2
		datagrid.datagrid('clearSelections');
		datagrid.datagrid('clearChecked');
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false,title:'查找条件'" style="height: 120px;overflow: hidden;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th>报告单号</th>
					<td><input name="reportNumber" id="reportNumber" style="width:120px" /></td>
					<th>检测时间</th>
					<td><input name="beginSearchTimeString" id="beginSearchTimeString" class="easyui-datebox" style="width:120px"/>至
					<input name="endSearchTimeString" id="endSearchTimeString" class="easyui-datebox" style="width:120px" /></td>
					<th>检测站名称</th>
						<td><input name="inspectionStationId" id="inspectionStationId" style="width:120px" class="easyui-combobox"/></td>		 
					<th>车架号</th>
					<td><input name="vin" id="vin" style="width:150px" /></td>
				</tr>
				<tr>
					<th>最大总质量</th>
					<td><input name="maxTotalQualityLowerBound" id="maxTotalQualityLowerBound" style="width:80px"/>到
					<input name="maxTotalQualityUpperBound" id="maxTotalQualityUpperBound" style="width:80px"/>
					</td>
					<th>车辆登记日期</th>
					<td><input name="beginRegisterDate" style="width:120px"
						class="easyui-datebox" />到
					<input name="endRegisterDate" style="width:120px"
						class="easyui-datebox" />
					</td>
					 <th>排放标准</th>
				    <td><select name="emissionStandard" id="emissionStandard" style="width:80px">
				    <option value ="-1">--全部--</option>
					  <option value ="1">国1</option>
                      <option value ="2">国2</option>
                      <option value ="3">国3</option>
                      <option value ="4">国4</option>
                      <option value ="5">国5</option>
                      <option value ="6">国6</option>
					</select></td>
					<th>检测结果</th>
					<td><select name="conclusion" id="conclusion" style="width:80px">
				    <option value ="-1">--全部--</option>
					  <option value ="0">合格</option>
                      <option value ="1">不合格</option>
                      <option value ="2">未下结论</option>
					</select></td>
					</tr>
				<tr>	
					<th>车主姓名</th>
					<td><input name="vehicleOwnerName" id="vehicleOwnerName" style="width:120px" />
				    </td>
				    <th>车牌号</th>
					<td><input name="licence" id="licence" style="width:150px" />
				    </td>
				    <th>状态</th>
				    <td><select name="commisionSheetStatus" id="commisionSheetStatus" style="width:80px">
				    <option value ="-1">--全部--</option>
					  <option value ="0">已注销</option>
                      <option value ="1">未检测</option>
                      <option value ="2">已检测</option>
                      <option value ="3">已完工</option>
					</select></td>
					 <th></th>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" onclick="searchSth();">确定</a> <a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="edit();" data-options="iconCls:'icon-edit'">编辑</div>
	</div>
</body>
</html>
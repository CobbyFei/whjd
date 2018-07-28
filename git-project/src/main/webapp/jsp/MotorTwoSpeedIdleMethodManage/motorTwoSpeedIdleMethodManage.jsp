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
							url : '${pageContext.request.contextPath}/motorTwoSpeedIdleMethodAction!getMotorTwoSpeedIdleMethod.action',//
							title : '摩托车双怠速法检测数据列表',
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
							sortOrder : 'desc',
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
							},{
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
								width : 100
							}, {
								title : '检测结果',
								field : 'conclusion',
								width : 100
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
								title : '车辆登记日期',
								field : 'vehicleRegisterDate',
								width : 100
							},{
								title : '车辆类型',
								field : 'vehicleType',
								width : 100,
								formatter:function(value,rowData,rowIndex)
								   {
								       if(value==0)
								          return "新车";
								       else if(value==1)
								       {
								          return "外地转入车";
								       }
								       else
								       {
								          return "在用车";
								       }
								   }
							},{
								title : '检测类型',
								field : 'labelDistributeType',
								width : 100,
								formatter:function(value,rowData,rowIndex)
								   {
								      if(value==0)
								      {
								         return "首发";
								      }
								      else{
								         return "换发";
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
								width : 100,
								formatter:function(value,rowData,rowIndex)
								{
								       //return "国"+value;
								       return value;
								}
							},{
								title : '试验人员',
								field : 'sysUserName',
								width : 100
							}, {
								title : '数据表id',
								field : 'recordId',
								hidden: true,
								width : 100
							} ,{
								title : '生产企业',
								field : 'vehicleManufacturer',
								hidden: true,
								width : 100
							},{
								title : '车轮数',
								field : 'wheelNums',
								hidden: true,
								width : 100
							}, {
								title : '冲程数',
								field : 'strokes',
								hidden: true,
								width : 100
							}, {
								title : '最大净功率转速',
								field : 'maxRpm',
								hidden: true,
								width : 100
							}, {
								title : '怠速',
								field : 'idleSpeedRpm',
								hidden: true,
								width : 100
							}, {
								title : '高怠速转速',
								field : 'highIdleSpeedRpm',
								hidden: true,
								width : 100
							},{
								title : '燃料规格',
								field : 'fuelSpecification',
								hidden: true,
								width : 100
							}, {
								title : '润滑油规格',
								field : 'lubeSpecification',
								hidden: true,
								width : 100
							}, {
								title : '燃油供给方式',
								field : 'fuelSupplyStyle',
								hidden: true,
								width : 100
							}, {
								title : '燃油喷射系统',
								field : 'fuelJetSystem',
								hidden: true,
								width : 100
							}, {
								title : '污染控制装置',
								field : 'pollutionControlDevice',
								hidden: true,
								width : 100
							}, {
								title : '排气分析仪型号',
								field : 'exhaustAnalyzerModel',
								hidden: true,
								width : 50
							}, {
								title : '转速计型号',
								field : 'tachometerModel',
								hidden: true,
								width : 120
							}, {
								title : '试验地点',
								field : 'experimentAddress',
								hidden: true,
								width : 110
							},  {
								title : '温度',
								field : 'temperature',
								hidden: true,
								width : 100
							}, {
								title : '气压',
								field : 'airPressure',
								hidden: true,
								width : 80
							}, {
								title : '相对湿度',
								field : 'relativeHumidity',
								hidden: true,
								width : 80
							},{
								title : '高怠速CO',
								field : 'hCoresult',
								hidden: true,
								width : 80
							},{
								title : '高怠速CO2',
								field : 'hCo2result',
								hidden: true,
								width : 80
							},{
								title : '高怠速HC',
								field : 'hHcresult',
								hidden: true,
								width : 80
							},{
								title : '怠速CO',
								field : 'coresult',
								hidden: true,
								width : 80
							},{
								title : '怠速CO2',
								field : 'co2result',
								hidden: true,
								width : 80
							},{
								title : '怠速HC',
								field : 'hcresult',
								hidden: true,
								width : 80
							},{
								title : '检测状态',
								field : 'reportStatus',
								//hidden: true,
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
							},'-', {
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
								if(row.reportStatus == 1||row.reportStatus == 2){
							     	return "color: green";
							     }
							     
								if (row.commisionSheetStatus == "已注销") {
									return "color:red";
								} else if (row.commisionSheetStatus == "已完工") {
									return "color:purple";
								} else if (row.commisionSheetStatus == "已检测") {
									return "color: blue";
								} 
							     
								if (row.conclusion == "合格") {
									return "color:red";
								} else if (row.conclusion == "不合格") {
									return "color:purple";
								} else if (row.conclusion == "未下结论") {
									return "color: blue";
								} 
							}
						});
	});

	// 修改，不支持批量

	function edit() {
		var rows = datagrid.datagrid('getChecked');
		var status=rows[0].commisionSheetStatus;
		var str='${sessionScope.stationName}';
		if(str!="市局"){
		if(status=="已完工"){
			parent.sy.messagerAlert('提示', '已完工记录无法编辑！', 'error');
			return false;
		}
		if(status=="已注销"){
			parent.sy.messagerAlert('提示', '已注销记录无法编辑！', 'error');
			return false;
		}
		if (rows.length == 1) {
			var rStatus=rows[0].reportStatus;
			if(rStatus==1||rStatus==2){
				parent.sy.messagerAlert('提示', '已在检测队列！', 'error');
				p.dialog('close');
				return ;
				}
			var p = parent.sy.dialog({
						title : '编辑摩托车双怠速法检测数据',
						href : '${pageContext.request.contextPath}/motorTwoSpeedIdleMethodAction!editMotorTwoSpeedIdleMethod.action',
						width : 1000,
						height : 600,
						resizable : true,
						buttons : [ {
							text : '确认修改',
							iconCls : 'icon-edit',
							handler : function() {
								var f = p.find('form');
								f.form('submit',
												{
													url : '${pageContext.request.contextPath}/motorTwoSpeedIdleMethodAction!updateMotorTwoSpeedIdleMethod.action',
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
								var rStatus=rows[0].reportStatus;
								if(rStatus==1||rStatus==2){
									parent.sy.messagerAlert('提示', '已在检测队列！', 'error');
									p.dialog('close');
									return ;
									}
								f.form('submit',
												{
													url : '${pageContext.request.contextPath}/motorTwoSpeedIdleMethodAction!beginDetection.action',
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
						id : rows[0].id,
						recordId : rows[0].recordId,
						reportNumber: rows[0].reportNumber,
						licence: rows[0].licence,
						stationName: rows[0].stationName,
						vehicleOwnerName: rows[0].vehicleOwnerName,
						detectionLineId:rows[0].detectionLineId,
						vehicleManufacturer:rows[0].vehicleManufacturer,
						
						fuelSpecification:rows[0].fuelSpecification,
						strokes:rows[0].strokes,
						wheelNums:rows[0].wheelNums,
						maxRpm:rows[0].maxRpm,
						idleSpeedRpm:rows[0].idleSpeedRpm,
						highIdleSpeedRpm:rows[0].highIdleSpeedRpm,
						lubeSpecification:rows[0].lubeSpecification,
						fuelSupplyStyle:rows[0].fuelSupplyStyle,
						fuelJetSystem:rows[0].fuelJetSystem,
						pollutionControlDevice:rows[0].pollutionControlDevice,
						exhaustAnalyzerModel:rows[0].exhaustAnalyzerModel,
						tachometerModel:rows[0].tachometerModel,
						experimentAddress:rows[0].experimentAddress,
						
						temperature:rows[0].temperature,
						airPressure:rows[0].airPressure,
						humidity:rows[0].humidity,
						
						HCoresult:rows[0].hCoresult,
						HCo2result:rows[0].hCo2result,
						HHcresult:rows[0].hHcresult,
						coresult:rows[0].coresult,
						co2result:rows[0].co2result,
						hcresult:rows[0].hcresult,
						
						conclusion:rows[0].conclusion,
						sysUserId:rows[0].sysUserName
						});				
						}
					});
		} else if (rows.length > 1) {
			parent.sy.messagerAlert('提示', '同一时间只能编辑一条记录！', 'error');
		} else {
			parent.sy.messagerAlert('提示', '请选择要编辑的记录！', 'error');
		}
	} else {
		parent.sy.messagerAlert('提示', '您的权限不足！', 'error');
	}
		
	}

	// 下结论，支持批量
	function remove() {
		var rows = datagrid.datagrid('getChecked');
		var rStatus=rows[0].reportStatus;
		if(rStatus==1){
			parent.sy.messagerAlert('提示', '已在检测队列，请耐心等候！', 'error');
			
			return ;
			}
		if(rStatus==2){
			parent.sy.messagerAlert('提示', '正在检测，请耐心等候！', 'error');
			
			return ;
			}
		if(rStatus==4){
			parent.sy.messagerAlert('提示', '检测发生错误，不允许下结论！', 'error');
			
			return ;
			}
		var ids = [];
		if (rows.length > 0) {
			parent.sy.messagerConfirm(
							'请确认',
							'您要对该数据记录下结论？',
							function(r) {
								if (r) {
									for ( var i = 0; i < rows.length; i++) {
										ids.push(rows[i].id);
									}
									$.ajax({
												url : '${pageContext.request.contextPath}/motorTwoSpeedIdleMethodAction!batchConclusion.action',
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
	//打印
	function print() {
		var rows = datagrid.datagrid('getChecked');
		if (rows.length == 1) {
			if (rows[0].commisionSheetStatus!="已完工") {
				$.messager.alert('提示', '所选数据记录未完工或已注销，无法打印', 'error');
				return;
			}
			var p = parent.sy
					.dialog({
						title : '打印检测报告',
						href : sy.bp()
								+ '/motorTwoSpeedIdleMethodAction!methodPrint.action?id='
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
	<div data-options="region:'north',border:false,title:'查找条件'" style="height: 120px;overflow: hidden;" align="left">
		<form id="searchForm">
			<table class="tableForm datagrid-toolbar" style="width: 100%;height: 100%;">
				<tr>
					<th>报告单号</th>
					<td><input name="reportNumber" id="reportNumber" style="width:120px" /></td>
					<th>检测时间</th>
					<td><input name="beforeDetectionTime" id="beforeDetectionTime" class="easyui-datebox" style="width:120px" editable="true"/>至
					<input name="afterDetectionTime" id="afterDetectionTime" class="easyui-datebox" style="width:120px" editable="true"/></td>
				    <th>状态</th>
				    <td>
				    <select name="commisionSheetStatus" id="commisionSheetStatus" style="width:120px">
				    <option value ="全部 "  selected>--全部--</option>
					  <option value ="已注销">已注销</option>
                      <option value ="未检测">未检测</option>
                      <option value ="已检测">已检测</option>
                      <option value ="已完工">已完工</option>
					</select></td>
					<th>车架号</th>
					<td><input name="vin" id="vin" style="width:150px" /></td>
				</tr>
				<tr>	
				    <th>车主姓名</th>
					<td><input name="vehicleOwnerName" id="vehicleOwnerName" style="width:120px" />
				    </td>
				    <th>车牌号</th>
					<td><input name="licence" id="licence" style="width:120px" />
				    </td>
                    <th>检测站名称</th>
					<td><input name="inspectionStationId" id="inspectionStationId" style="width:120px" class="easyui-combobox"/></td>
				</tr>
				
				<tr>
					<th>车辆登记日期</th>
					<td><input name="beginRegisterDate" id="beginRegisterDate" class="easyui-datebox" style="width:120px" editable="true"/>至
					<input name="endRegisterDate" id="endRegisterDate" class="easyui-datebox" style="width:120px" editable="true"/></td>
				    
					<th>检测结果</th>
					<td><select name="conclusion" id="conclusion" style="width:120px">
				    <option value ="全部" selected>--全部--</option>
					  <option value ="合格">合格</option>
                      <option value ="不合格">不合格</option>
                      <option value ="未下结论">未下结论</option>
					</select></td>
					 <th>排放标准</th>
				    <td><select name="emissionStandard" id="emissionStandard" style="width:120px">
				    <option value ="-1" selected>--全部--</option>
					  <option value ="1">国1</option>
                      <option value ="2">国2</option>
                      <option value ="3">国3</option>
                      <option value ="4">国4</option>
                      <option value ="5">国5</option>
                      <option value ="6">国6</option>
					</select></td>
					<th></th>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="searchSth();">查询</a> <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanSearch();">取消</a>
					</td>
				</tr>			
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<table id="datagrid"></table>
	</div>

	<div id="menu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="remove();" data-options="iconCls:'icon-remove'">注销</div>
		<div onclick="edit();" data-options="iconCls:'icon-edit'">编辑</div>
	</div>
</body>
</html>
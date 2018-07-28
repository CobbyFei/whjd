<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>检测委托单管理</title>
  <script type="text/javascript">
      var datagrid;
      $(function(){
          var stationId='${sessionScope.stationId}';
	      var stationName='${sessionScope.stationName}';
	      if(stationId!=0)
	      {
	         //此时表示不在市局
	         $("#stationName").combobox('setValue',stationId);
	         $("#stationName").combobox('setText',stationName);
	         $("#stationName").combobox({
	             disabled:true
	         });
	      }
		  else
		  {
			  $("#stationName").combobox({
					url : sy.bp() + '/inspectionStationAction!getInspectionStationName.action',
					mode : 'remote',
					delay : 500,
					valueField : 'stationName',
					required : true,
					textField : 'stationName'
				});
		  }
            datagrid=$("#datagrid").datagrid({
                url:'${pageContext.request.contextPath}/detectionCommisionSheetAction!getAllDetectionCommisionSheet.action',
                title : '检测委托单信息',
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
			    columns:[[{
			       title : '检测单编号',
				   field : 'reportNumber',
				   width : 100
			    },{
			       title:'检测站名称',
			       field:'stationName',
			       width:120
			    },{
			       title : '车牌号',
				   field : 'licence',
				   width : 100
			    },{
			       title:'车牌颜色',
			       field:'licenseColor',
			       width:100
			    },{
			       title : '车主姓名',
				   field : 'vehicleOwnerName',
				   width : 100
			    },{
			       title : '车主联系方式',
				   field : 'phoneNum',
				   width : 100
				},{
			       title:'车主住址',
			       field : 'vehicleOwnerAddress',
				   width : 130
			    },{
			       title:'车辆识别码',
			       field : 'vin',
				   width : 100
			    },{
			       title:'检测时间',
			       field : 'detectionTime',
				   width : 140
			    },{
			       title:'发动机型号',
			       field : 'engineModel',
				   width : 100
			    },{
			       title:'发动机号',
			       field : 'engineCode',
				   width : 100
			    },{
			       title:'车辆型号',
			       field : 'vehicleModelCode',
				   width : 100
			    },{
			       title:'车辆类别',
			       field : 'vehicleClass',
				   width : 100
			    },{
			       title:'燃油类型',
			       field : 'fuelType',
				   width : 100
			    },{
			       title:'基准质量',
			       field : 'baseQuality',
				   width : 70
			    },{
			       title:'最大总质量',
			       field : 'maxTotalQuality',
				   width : 70
			    },{
			       title:'发动机排量',
			       field : 'engineemissionAmount',
				   width : 70
			    },{
			       title:'累计行驶里程',
			       field : 'totalMiles',
				   width : 70
			    },{
			       title:'车身长度',
			       field : 'vehicleLength',
				   width : 70
			    },{
			       title:'检测类型',
			       field : 'labelDistributeType',
				   width : 80,
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
			       title:'车辆来源',
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
			       title:'车辆登记日期',
			       field : 'vehicleRegisterDate',
				   width : 100
			    },{
			       title:'车辆发证日期',
			       field : 'vechileIssueCertTime',
				   width : 100
			    
			    },{
			       title:'有效期',
			       field : 'validatePeriod',
				   width : 100
			    
			    },{
			       title:'最大载客量',
			       field : 'vehicleLoadNum',
				   width : 80
			    
			    },{
			       title:'检测方法',
			       field : 'detectionMethod',
				   width : 100
			    },{
			       title:'排放标准',
			       field : 'emissionStandard',
				   width : 80,
				   formatter:function(value,rowData,rowIndex)
				   {
				       return "国"+value;
				   }
			    },{
			       title:'年度检测次数',
			       field : 'yearCount',
				   width : 80
			    },{
			       title:'委托单状态',
			       field : 'commisionSheetStatus',
				   width : 80,
				   formatter:function(value,rowData,rowIndex)
				   {
				       if(value==0)
				          return "已注销";
				       else if(value==1)
				       {
				          return "未检测";
				       }
				       else if(value==2)
				       {
				          return "已检测";
				       }else{
				          return "已完成";
				       }
				   }
			    },{
			       title:'结论',
			       field : 'conclusion',
				   width : 80,
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
			        title:'发标请求状态',
			        field:'requestStatus',
			        width:80,
			        formatter:function(value,rowData,rowIndex)
			        {
			             if(value==0)
			                 return "未申请";
			             else if(value==1)
			                 return "已申请";
			             else if(value==2)
			                 return "已发标";
			             else if(value==3)
			                 return "发标出现错误";
			        }
			    },{
			       title:'处理状态',
			       field:'receiveStatus',
			       width:80,
			       formatter:function(value,rowData,rowIndex)
			       {
			             if(value==0)
			                 return "未排队";
			             else if(value==1)
			                 return "正在排队";
			             else if(value==2)
			                 return "正在处理";
			             else
			                 return "处理完成";
			       }
			    },{
			       title:'标志号',
			       field:'labelId',
			       width:100,
			       formatter:function(value,rowData,rowIndex){
			           if(value=="未发标")
			               return "";
			           else 
			           {
			               return value;
			           }
			       }
			    },{
			       title:'错误原因',
			       field : 'errorReason',
				   width : 120
			       }
			    
			    ]],
			    toolbar : [ {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
				   if(stationName!="市局")
				   {
				        edit();
				   }
				   else
				   {
				       $.messager.alert('提示','抱歉，您没有权限执行编辑操作!','error')
				   }
					
				}
				}, '-', {
					text : '注销',
					iconCls : 'icon-remove',
					handler : function() {
						if(stationName!="市局")
					    {
					        cancel();
					    }
					    else
					    {
					       $.messager.alert('提示','抱歉，您没有权限执行注销操作!','error')
					    }
					}
				
				},'-',{
				    text : '挡回',
					iconCls : 'icon-ok',
					handler : function() {
						if(stationName!="市局")
					    {
					        revoke();
					    }
					    else
					    {
					       $.messager.alert('提示','抱歉，您没有权限执行挡回操作!','error')
					    }
					}
				},'-', {
					text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						cancelSelections(datagrid);
					}
				}, '-',{
				    text:'发标',
				    iconCls:'icon-ok',
				    handler:function(){
				       if(stationName!="市局")
					    {
					        distributeRemark();
					    }
					    else
					    {
					       $.messager.alert('提示','抱歉，您没有权限执行挡回操作!','error')
					    }
				       
				    }
				},'-',{
				    text:'重新发标',
				    iconCls:'icon-ok',
				    handler:function(){
				        if(stationName!="市局")
					    {
					        reDistributeRemark();
					    }
					    else
					    {
					       $.messager.alert('提示','抱歉，您没有权限执行挡回操作!','error')
					    }
				    }
				
				},'-',{
				    text:'导出',
				    iconCls:'icon-redo',
				    handler:function(){
				        myExport();
				    }
				},'-',{
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
				     if(row.commisionSheetStatus==0)
				     {
				        return "color:red";
				     }else if(row.commisionSheetStatus==3)
				     {
				        return "color:green";
				     }else if(row.requestStatus==3)
				     {
				        return "color:yellow";
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
	
	function reDistributeRemark()
	{
	    var rows=datagrid.datagrid('getChecked');
	    if(rows.length==1)
	    {
	       
	       if(rows[0].labelId=="未发标")
	       {
	        
	         if(rows[0].vehicleType==1)
	         {
	             $.messager.alert('提示','外地车辆不予发放标志！','error');
	             return;
	         }
	         
	         if(rows[0].conclusion==1)
	         {
	             $.messager.alert('提示','检测不合格，不予发放标志！','error');
	             return;
	         }
	         if(rows[0].conclusion!=0)
	         {
	             $.messager.alert('提示','检测未下结论，不予发放标志','error');
	             return;
	         }
	         if(rows[0].requestStatus==1 && rows[0].receiveStatus==1)
	         {
	             $.messager.alert('提示','任务已经在排队中,请稍候!','error');
	             return;
	         }
	         if(rows[0].requestStatus==0)
	         {
	             $.messager.alert('提示','该委托单还未申请发标，请先点击发标按钮!','error');
	             return;
	         }
	        
	         //正式地进行发放标志
		       $.messager.confirm('请确定','您确定要对选中检测委托单重新发放标志么？',function(r){
		            if(r)
		            {
		               $.ajax({
						url : '${pageContext.request.contextPath}/detectionCommisionSheetAction!reDistributeLabel.action',
						dataType : 'json',
						data:rows[0],
						success : function(d) {
							if (d.success) {
								sy.messagerAlert('提示', '重新申请发标任务成功', 'info');
								datagrid.datagrid('load', sy.serializeObject($("#searchForm")));
								cancelSelections(datagrid);
							}
							else{
							     sy.messagerAlert('提示', d.msg, 'error');
							}
						}
					});  
		            
		            }
		       });
           
	       }
	       else{
	          $.messager.alert('提示','所选中的检测委托单的标志已经发放!','error');
	       }
	    }
	    else if(rows.length>1)
	    {
	        $.messager.alert('提示','同时只能对一张检测委托单重新发放标志','error');
	        return;
	    }
	    else
	    {
	       $.messager.alert('提示','请选择需要重新发放标志的检测委托单','error');
	       return;
	    }
	}
	//发放标志
	function distributeRemark(){
	    var rows=datagrid.datagrid('getChecked');
	    if(rows.length==1)
	    {
	       
	       if(rows[0].labelId=="未发标")
	       {
	         if(rows[0].requestStatus==1 || rows[0].requestStatus==2)
	         {
	             $.messager.alert('提示','已经申请发标，不能重复申请！','error');
	             return;
	         }
	         if(rows[0].vehicleType==1)
	         {
	             $.messager.alert('提示','外地车辆不予发放标志！','error');
	             return;
	         }
	         
	         if(rows[0].conclusion==1)
	         {
	             $.messager.alert('提示','检测不合格，不予发放标志！','error');
	             return;
	         }
	         if(rows[0].conclusion!=0)
	         {
	             $.messager.alert('提示','检测未下结论，不予发放标志','error');
	             return;
	         }
	         //正式地进行发放标志
		       $.messager.confirm('请确定','您确定要对选中检测委托单发放标志么？',function(r){
		            if(r)
		            {
		               $.ajax({
						url : '${pageContext.request.contextPath}/detectionCommisionSheetAction!distributeLabel.action',
						dataType : 'json',
						data:rows[0],
						success : function(d) {
							if (d.success) {
								sy.messagerAlert('提示', '添加发标任务成功', 'info');
								datagrid.datagrid('load', sy.serializeObject($("#searchForm")));
								cancelSelections(datagrid);
							}
							else{
							     sy.messagerAlert('提示', d.msg, 'error');
							}
						}
					});  
		            
		            }
		       });
           
	       }
	       else{
	          $.messager.alert('提示','所选中的检测委托单的标志已经发放!','error');
	       }
	    }
	    else if(rows.length>1)
	    {
	        $.messager.alert('提示','同时只能对一张检测委托单发放标志','error');
	        return;
	    }
	    else
	    {
	       $.messager.alert('提示','请选择需要发放标志的检测委托单','error');
	       return;
	    }
	}
	function edit(){
	     var rows=datagrid.datagrid("getChecked");
	     if(rows.length==1)
	     {
	          var p=sy.dialog({
	               title : '修改检测委托单信息(带*的为关键信息，不允许修改)',
	               href: '${pageContext.request.contextPath}/detectionCommisionSheetAction!edit.action',
	               width:600,
	               height:550,
	               resizable:false,
	               buttons:[{
	                   text:'修改',
	                   iconCls:'icon-edit',
	                   handler:function(){
	                       var f = p.find('form');
	                       f.form('submit', {
								url : '${pageContext.request.contextPath}/detectionCommisionSheetAction!modifyDetectionCommisionSheet.action',
								success : function(d) {
									var json = $.parseJSON(d);
									if (json.success) {
										$.messager.alert('提示', '修改检测委托单信息成功', 'info');
										datagrid.datagrid('load', sy.serializeObject($("#searchForm")));
										cancelSelections(datagrid);
										p.dialog('close');
									} else {
										$.messager.alert('提示', json.msg, 'error');
									}
									
								}
							});
	                   
	                   }
	               }],
	               onLoad:function(){
	                   var f = p.find('form');
	                    f.form('load', {
							id : rows[0].id,
							licence : rows[0].licence,
							stationName:rows[0].stationName,
							vin : rows[0].vin,
							vehicleOwnerName : rows[0].vehicleOwnerName,
							vehicleOwnerAddress : rows[0].vehicleOwnerAddress,
							detectionTime : rows[0].detectionTime,
							vehicleRegisterDate : rows[0].vehicleRegisterDate,
							engineemissionAmount : rows[0].engineemissionAmount,
							vehicleType : rows[0].vehicleType,
							engineModel : rows[0].engineModel,
							engineCode : rows[0].engineCode,
							vehicleModelCode : rows[0].vehicleModelCode,
							fuelType : rows[0].fuelType,
							baseQuality : rows[0].baseQuality,
							maxTotalQuality : rows[0].maxTotalQuality,
							totalMiles : rows[0].totalMiles,
							vehicleLength : rows[0].vehicleLength,
							emissionStandard : rows[0].emissionStandard,
							detectionMethod : rows[0].detectionMethod,
							labelDistributeType : rows[0].labelDistributeType,
							commisionSheetStatus:rows[0].commisionSheetStatus,
							reportNumber:rows[0].reportNumber,
					        vechileIssueCertTime:rows[0].vechileIssueCertTime,
						    validatePeriod:rows[0].validatePeriod,
						    licenseColor:rows[0].licenseColor,
						    vehicleLoadNum:rows[0].vehicleLoadNum,
						    labelId:rows[0].labelId,
						    requestStatus:rows[0].requestStatus,
						    yearCount:rows[0].yearCount,
						    conclusion:rows[0].conclusion,
						    errorReason:rows[0].errorReason,
						    vehicleClass:rows[0].vehicleClass,
						    phoneNum:rows[0].phoneNum
						});
	               }
	          });
	     }
	     else if(rows.length>1){
	         $.messager.alert('提示','同一时间只能编辑同一条记录!','error');
	     }
	     else{
	         $.messager.alert('提示','请选择要编辑的记录','error');
	     }
	     
	}
   //导出信息
	function myExport(){
	    var rows = datagrid.datagrid('getData').rows;
		var ids = [];
		if (rows.length > 0) {
		  $.messager.confirm("提示","确定要导出表格中数据？",function(r){
		      if(r)
		      {
		        for ( var i = 0; i < rows.length; i++) {
				   ids.push(rows[i].id);
			      }
			    $.ajax({
				  url : sy.bp() + '/detectionCommisionSheetAction!exportDetectionCommisionSheet.action',
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
		      }
		  });

		} else {
			parent.sy.messagerAlert('提示', '数据为空，不能执行导出操作！', 'error');
		}
	}
	//注销检测委托单信息
	function cancel(){
	   var rows=datagrid.datagrid('getChecked');
	   var ids=[];
	   if(rows.length>0)
	   {
	       $.messager.confirm('请确认','确认要注销选中的检测委托单?',function(r){
	           if(r){
	               for(var i=0;i<rows.length;i++)
	               {
	                   if(rows[i].commisionSheetStatus!=0)
	                   {
	                      if(rows[i].receiveStatus==1||rows[i].receiveStatus==2)
	                    {
	                    	  $.messager.alert('提示', '委托单正在排队处理，不能注销！', 'error');
	                    	   return;
	                    }
	                      ids.push(rows[i].id);
	                      
	                   }
	                   else
	                   {
	                      $.messager.alert('提示','不能对已注销记录进行重复注销,请重新选择！','error');
	                      return;
	                   }
	               }
	               if(ids.length==0)
	               {
	                 	$.messager.alert('提示', '所选检测委托单信息均已注销', 'error');
						return;
	               }
	               $.ajax({
						url : '${pageContext.request.contextPath}/detectionCommisionSheetAction!cancelDetectionCommistionSheet.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(d) {
							if (d.success) {
								sy.messagerAlert('提示', '注销检测委托单信息成功', 'info');
								datagrid.datagrid('load', sy.serializeObject($("#searchForm")));
								cancelSelections(datagrid);
							}
							else{
							     sy.messagerAlert('提示', d.msg, 'error');
							}
						}
					});
	           }
	       });
	   }
	   else
	   {
	      $.messager.alert('提示','请选择需要注销的检测委托单','error');
	      return;
	   }
	}
	
	function revoke(){
	  var rows=datagrid.datagrid('getChecked');
	   var ids=[];
	   if(rows.length>0)
	   {
	       $.messager.confirm('请确认','确认要挡回选中的已注销检测委托单?',function(r){
	           if(r){
	               for(var i=0;i<rows.length;i++)
	               {
	                   if(rows[i].commisionSheetStatus==0)
	                   {
	                      
	                      ids.push(rows[i].id);
	                   }
	                   else
	                   {
	                      $.messager.alert('提示','不能对未注销记录进行挡回,请重新选择！','error');
	                      return;
	                   }
	               }
	               if(ids.length==0)
	               {
	                 	$.messager.alert('提示', '所选检测委托单信息均未注销', 'error');
						return;
	               }
	               $.ajax({
						url : '${pageContext.request.contextPath}/detectionCommisionSheetAction!revokeDetectionCommistionSheet.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(d) {
							if (d.success) {
								sy.messagerAlert('提示', '挡回已注销检测委托单信息成功', 'info');
								datagrid.datagrid('load', sy.serializeObject($("#searchForm")));
								cancelSelections(datagrid);
							}
							else{
							     sy.messagerAlert('提示', d.msg, 'error');
							}
						}
					});
	           }
	       });
	   }
	   else
	   {
	      $.messager.alert('提示','请选择需要注销的检测委托单','error');
	      return;
	   }
	
	
	}
      
  </script>
</head>
<body class="easyui-layout" data-options="fit:true">
  <div data-options="region:'north',border:false,title:'过滤条件'" style="height:190px;overflow:hidden;width:700px"
		align="left">
		<form id="frm_down" method="post"
			action="/whjd/downloadFileAction!export.action" target="_self">
			<input id="filePath" name="filePath" type="hidden" />
		</form>
		<form id="searchForm">
		    <table class="tableForm datagrid-toolbar" style="width:100%;height:100%">
		       <tr>
					<th>报告单号:</th>
					<td><input name="reportNumber" id="reportNumber" class="easyui-validatebox" data-options="" style="width:175px" />
					</td>
					<th>车牌号:</th>
					<td><input name="licence" id="licence" class="easyui-validatebox" style="width:175px" /></td>
				</tr>
				<tr>
					<th>车主姓名:</th>
					<td><input name="vehicleOwnerName" id="vehicleOwnerName" class="easyui-validatebox" data-options="" style="width:175px" />
					</td>
					<th>检测方法:</th>
					<td><input name="detectionMethod" id="detectionMethod" class="easyui-validatebox" style="width:175px" /></td>
				</tr>
				<tr>
					<th>检测日期:</th>
					<td><input name="beginTime" id="beginTime" class="easyui-datetimebox" data-options="editable:false" style="width:175px" />
					</td>
					<th>---------</th>
					<td><input name="endTime" id="endTime" class="easyui-datetimebox" style="width:175px" data-options="editable:false"/></td>
				</tr>
				<tr>
					<th>检测委托单状态:</th>
					<td>
                       <select name="commisionSheetStatus" id="commisionSheetStatus" class="easyui-combobox" style="width:175px">
                          <option value="-1" selected="true">----全部----</option>
                          <option value="0" >已注销</option>
                          <option value="1">未检测</option>
                          <option value="2">已检测</option>
                          <option value="3" >已完成</option>
                       </select>
					</td>
					<th algin="right">结论:</th>
					<td align="left">
					<select name="conclusion" id="conclusion" class="easyui-combobox" style="width:175px" editable="false">
                          <option value="-1" selected="true">----全部----</option>
                          <option value="0" >合格</option>
                          <option value="1">不合格</option>
                          <option value="2">未检测</option>
                     </select>
				   </td>
				</tr>
				<tr>
				    <th>发标状态:</th>
				    <td>
				    <select name="labelId" id="labelId" class="easyui-combobox" style="width:175px" editable="false">
                          <option value="-1" selected="true">----全部----</option>
                          <option value="0" >未发标</option>
                          <option value="1">已发标</option>
                     </select>
				    </td>
					<th algin="right">检测站名称</th>
					<td align="left">
					  <input name="stationName" id="stationName" class="easyui-combobox" style="wdith:175px"/>
				   </td>
				</tr>
				<tr>
				    <th></th>
				    <td></td>
					<th algin="right">&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search"
						onclick="searchData()">查询</a>
					</th>
					<td align="left">&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel"
						onclick="clearSearch()">清空条件</a>
				   </td>
				</tr>
		    </table>
		</form>
  </div>
  <div data-options="region:'center',border:false" style="overflow:hidden">
		<table id="datagrid"></table>
	</div>
	<div id="menu" class="easyui-menu" style="width:120px;display:none">
		<div onclick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
		<div onclick="cancel()" data-options="iconCls:'icon-remove'">注销</div>
	</div>
</body>
</html>
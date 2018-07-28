<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>检测线标定记录管理</title>
<script type="text/javascript">
     var datagrid;
     $(function(){
        var stationId='${sessionScope.stationId}';
        var stationName='${sessionScope.stationName}';
     	$("#userId").combobox({
			url : sy.bp() + '/manageUserAction!getSysUsers.action',
			mode : 'remote',
			delay : 500,
			valueField : 'userId',
			required : false,
			textField : 'userName',
	    });
        if(stationId!="0")
        {
           $("#stationName").combobox('setValue',stationName);
           $("#stationName").combobox('setText',stationName);
           $("#stationName").combobox({
              disabled:true
           });
        }
        else{
	            $("#stationName").combobox(
					{
						url : sy.bp() + '/inspectionStationAction!getInspectionStationName.action',
						mode : 'remote',
						delay : 500,
						valueField : 'stationName',
						textField : 'stationName',
						onSelect : function(rowData) {
							var url = sy.bp() + '/detectionLineAction!getDetectionLineName.action?stationName='
									+ rowData.stationName;
							$("#lineName").combobox('reload', url);
						}
					});

        }
        	$("#lineName").combobox({
					url : sy.bp() + '/detectionLineAction!getDetectionLineName.action',
					mode : 'remote',
					delay : 500,
					valueField : 'lineName',
					required : true,
					textField : 'lineName'
				
				});   
     
          datagrid=$("#datagrid").datagrid({
                url:sy.bp()+'/calibrationDetectionLineAction!getAllCalibrationRecord.action',
                title:'检测线标定记录列表',
                iconCls:'icon-save',
                pagination:true,
		        pagePosition:'bottom',
		        pageSize:10,
		        pageList:[10,20,30,40],
		        fit:true,
		        fitColumns:false,
		        nowrap:false,
		        border:false,
		        idField:'recordId',
		        sortName:'recordId',
		        sortOrder:'asc',
		        checkOnSelect:false,
		        selectOnCheck:true,
		        frozenColumns:[[{
		              title:'编号',
		              field:'recordId',
		              width:150,
		              sortable:true,
		              checkbox:true
		           }]],
	           columns:[[{
		           title:'检测站编号',
		           field:'stationId',
	               width:70
		           },{
		            title:'检测站名称',
		            field:'stationName',
	                width:90
		           },{
		           	title:'检测线编号',
		            field:'lineId',
	                width:80
		           },{
		             title:'检测线名称',
		             field:'lineName',
	                 width:150
		           },{
		             title:'标定人编号',
		             field:'userId',
		             hidden:true,
		             width:80
		           },{
		             title:'标定人',
		             field:'userName',
	                 width:110
		           },{
		             title:'标定时间',
		             field:'calibrationTimePage',
		             width:150
		           },{
		             title:'NO标准值',
		             field:'nostandardValue',
		             width:100
		           },{
		              title:'NO标定后值',
		              field:'noafterValue',
		              width:100
		           },{
		              title:'HC标准值',
		              field:'hcstandardValue',
		              width:100
		           },{
		              title:'HC标定后值',
		              field:'hcafterValue',
		              width:100
		           },{
		             title:'CO标准值',
		             field:'costandardValue',
		             width:100
		           },{
		           
		              title:'CO标定后值',
		              field:'coafterValue',
		              width:80
		           },{
		              title:'CO2标准值',
		              field:'co2standardValue',
		              width:100
		           },{
		              title:'CO2标定后值',
		              field:'co2afterValue',
		              width:100
		           },{
		             title:'状态',
		             field:'status',
		             width:80,
		             formatter:function(value,rowData,rowIndex)
					           {
					              if(value==0)
					                    return "正常";
					               else
					                    return "注销";
					           }
		           }]],
	            toolbar:[{
	              text:'编辑',
	              iconCls:'icon-edit',
	              handler:function(){
	                  edit();
	              }
	           },'-',{
	              text:'删除',
	              iconCls:'icon-remove',
	              handler:function(){
	                  remove();
	              }
	
	           },'-',{
	              text:'注销',
	              iconCls:'icon-cancel',
	              handler:function(){
	                  cancelRecord();
	              }
	           },'-',{
			      text:'导出',
			      iconCls:'icon-redo',
			      handler:function(){
			          myExport();
			    }
			   },'-',{
	              text:'取消选中',
	              iconCls:'icon-undo',
	              handler:function(){
	                  cancelSelections(datagrid);
	              }
	           },'-',{
	              text:'刷新',
	              iconCls:'icon-reload',
	              handler:function(){
	                  searchData();
	              }
	           }],
	            onRowContextMenu:function(e,rowIndex,rowData)
	           {
	               e.preventDefault();
	               $("#menu").menu('show',{
	                   left:e.pageX,
	                   top:e.pageY
	               });
	           },
	           onClickRow:function(rowIndex,rowData){
	               var flag=true;
	               var rows=datagrid.datagrid('getChecked');
	               if(rows!=undefined && rows!=null && rows.length>0)
	               {
	                   for(var i=0;i<rows.length;i++)
	                   {
	                       if(rows[i].id==rowData.id)
	                       {
	                          flag=false;
	                          break;
	                       }
	                   }
	                   if(flag==false)
	                   {
	                       datagrid.datagrid('unselectRow',rowIndex);
	                       datagrid.datagrid('uncheckRow',rowIndex);
	                   }
	                   else
	                   {
	                       datagrid.datagrid('selectRow',rowIndex);
	                       datagrid.datagrid('checkRow',rowIndex);
	                   
	                   }
	               }
	               else
	               {
	                   datagrid.datagrid('checkRow',rowIndex);
	               }
	           },
	           rowStyler: function(index, row){
				 if (row.status == 1) 
				  {
					return 'color:#FF0000;';
				  }
				}
          });
     
     });
     //清空条件
     function clearSearch(){
         $("#searchForm").form('reset');
         datagrid.datagrid('load',{});
         var stationId='${sessionScope.stationId}';
         var stationName='${sessionScope.stationName}';
         if(stationId!="0")
        {
           $("#stationName").combobox('setValue',stationName);
           $("#stationName").combobox('setText',stationName);
           $("#stationName").combobox({
              disabled:true
           });
        }
     }
     //过滤查找
     function searchData(){
        datagrid.datagrid('load',sy.serializeObject($("#searchForm")));
     }
     
    //导出信息
	function myExport(){
	    var rows = datagrid.datagrid('getData').rows;
		var ids = [];
		if (rows.length > 0) {
		   $.messager.confirm('提示','确定要导出excel信息？',function(r){
		       if(r)
		       {
		          for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].recordId);
				}
				$.ajax({
					url : sy.bp() + '/calibrationDetectionLineAction!exportLineCarlibration.action',
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
     //编辑信息
     function edit(){
          var rows=datagrid.datagrid('getChecked');
          if(rows.length==1)
          {
              var p=sy.dialog({
                    title:'修改检测线标定记录信息',
                    href:'${pageContext.request.contextPath}/calibrationDetectionLineAction!editCalibrationRecord.action',
                    width:350,
	                height:550,
	                resizable:true,
                   buttons:[{
                    text:'修改',
                    iconCls:'icon-edit',
                    handler:function(){
                        
                         var f=p.find('form');
                        
                         f.form('submit',{
                              url:'${pageContext.request.contextPath}/calibrationDetectionLineAction!modifyCalibrationRecord.action',
                              onSubmit:function(){
					                var nostandardvalue=parseFloat(f.find("input[name=nostandardValue]").val());
								       var noafetervalue=parseFloat(f.find("input[name=noafterValue]").val());
								       if(noafetervalue>nostandardvalue*1.05 || noafetervalue<nostandardvalue*0.95)
								       {
								           $.messager.alert('提示','氮氧化物标定后值和标准值的误差不能超过5%！请重新标定该项目','error');
								           return false;
								       }
								       var hcstandardValue=parseFloat(f.find("input[name=hcstandardValue]").val());
								       var hcafterValue=parseFloat(f.find("input[name=hcafterValue]").val());
								       if(hcafterValue>hcstandardValue*1.05 || hcafterValue<hcstandardValue*0.95)
								       {
								           $.messager.alert('提示','碳氢化合物标定后值和标准值的误差不能超过5%！请重新标定该项目','error');
								           return false;
								       }
								       var costandardValue=parseFloat(f.find("input[name=costandardValue]").val());
								       var coafterValue=parseFloat(f.find("input[name=coafterValue]").val());
								       if(coafterValue>costandardValue*1.05 || coafterValue<costandardValue*0.95)
								       {
								           $.messager.alert('提示','一氧化碳标定后值和标准值的误差不能超过5%！请重新标定该项目','error');
								           return false;
								       }
								       var co2standardValue=parseFloat(f.find("input[name=co2standardValue]").val());
								       var co2afterValue=parseFloat(f.find("input[name=co2afterValue]").val());
								       if(co2afterValue>co2standardValue*1.05 || co2afterValue<co2standardValue*0.95)
								       {
								           $.messager.alert('提示','二氧化碳标定后值和标准值的误差不能超过5%！请重新标定该项目','error');
								           return false;
								       }
								       return f.form('validate');
					                
					           },
                              success:function(d){
                                    var  json=$.parseJSON(d);
                                    if(json.success)
                                    {
                                        cancelSelections(datagrid);
                                        datagrid.datagrid('reload');
                                        p.dialog('close');
                                    }
                                    sy.messagerAlert('提示',json.msg,'info');
		                            }
		                         });
		                    }
		                }],
	                onLoad:function(){
	                    var f= p.find('form');
	                    f.form('load',{
	                         recordId:rows[0].recordId,
	                         stationId:rows[0].stationId,
	                         stationName:rows[0].stationName,
	                         lineId:rows[0].lineId,
	                         lineName:rows[0].lineName,
	                         userId:rows[0].userName,
	                         userId1:rows[0].userId,
	                         calibrationTimePage:rows[0].calibrationTimePage,
	                         nostandardValue:rows[0].nostandardValue,
	                         noafterValue:rows[0].noafterValue,
	                         hcstandardValue:rows[0].hcstandardValue,
	                         hcafterValue:rows[0].hcafterValue,
	                         costandardValue:rows[0].costandardValue,
	                         coafterValue:rows[0].coafterValue,
	                         co2standardValue:rows[0].co2standardValue,
	                         co2afterValue:rows[0].co2afterValue,
	                         status:rows[0].status
	                    });
	                f.find("input[name=userId]").combobox({
						url : sy.bp() + '/manageUserAction!getSysUsers.action',
						mode : 'remote',
						delay : 500,
						valueField : 'userId',
						required:true,
						textField : 'userName',
					});
					 
	                }
	            });
          }
          else if(rows.length>1){
              sy.messagerAlert('提示','同一时间只能编辑同一条!','error');
          }else{
              sy.messagerAlert('提示','请选择要编辑的记录','error');
          }
     }
     //删除信息
     function remove(){
          var rows=datagrid.datagrid("getChecked");
          var ids=[];
          if(rows.length>0)
          {
             var date=new Date();
             var time=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
             $.messager.confirm('请确认','确认要删除选中的检测线标定记录？',function(r){
                  if(r){
                      for(var i=0;i<rows.length;i++)
                      {
                         if(rows[i].calibrationTimePage.trim()==time)
                         {
                            ids.push(rows[i].recordId);
                         }
                         else
                         {
                             $.messager.alert('提示','只能删除当天的标定记录！','error');
                             return;
                         }
                      }
                     $.ajax({
						url : '${pageContext.request.contextPath}/calibrationDetectionLineAction!deleteCalibrationRecord.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(d) {
							if (d.success) {
								sy.messagerAlert('提示', '删除检测线标定记录成功！', 'info');
								datagrid.datagrid('load', sy.serializeObject($("#searchForm")));
								cancelSelections(datagrid);
							}
							else{
							   sy.messagerAlert('提示',d.msg, 'error');
							}
						}
					});
                  
                  
                  }
             });
             
          }else{
              $.messager.alert('提示','请先选择要删除的检测线标定记录','error');
          }
     }
     function cancelRecord(){
          var rows=datagrid.datagrid("getChecked");
          var ids=[];
          if(rows.length>0)
          {
             $.messager.confirm('请确认','确认要注销选中的检测线标定记录？',function(r){
                  if(r){
                      for ( var i = 0; i < rows.length; i++) {
						if (rows[i].status == "0") {
							ids.push(rows[i].recordId);
						}
					 }
					if (ids.length == 0) {
						$.messager.alert('提示', '所选检测线标定信息均已注销', 'error');
						return;
					}
                     $.ajax({
						url : '${pageContext.request.contextPath}/calibrationDetectionLineAction!cancelRecord.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(d) {
							if (d.success) {
								sy.messagerAlert('提示', '注销检测线标定记录成功！', 'info');
								datagrid.datagrid('load', sy.serializeObject($("#searchForm")));
								cancelSelections(datagrid);
							}
							else{
							   sy.messagerAlert('提示',d.msg, 'error');
							}
						}
					});
                  
                  
                  }
             });
             
          }else{
              $.messager.alert('提示','请先选择要注销的检测线标定记录','error');
          }
     
     }
     
     //
     function  cancelSelections(datagrid){
           datagrid.datagrid("clearSelections");
           datagrid.datagrid("clearChecked");
     }
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
     <div data-options="region:'north',border:false,title:'过滤条件'" style="height:150px;overflow:hidden;width:100%" align="left">
         <form id="frm_down" method="post"
			action="/whjd/downloadFileAction!export.action" target="_self">
			<input id="filePath" name="filePath" type="hidden" />
		</form>
          <form id="searchForm">
               <table class="tableForm datagird-toolbar" style="width:100%;height:100%">
                   <tr>
					<th>检测站名称:</th>
					<td><input name="stationName" id="stationName" class="easyui-combobox"  style="width:317px" />
					</td>
					<th>检测线名称:</th>
					<td><input name="lineName" id="lineName" class="easyui-combobox" style="width:317px" /></td>
				</tr>
				<tr>
                     <th>标定时间:</th>
                     <td> <input id="beginTime" name="beginTime" class="easyui-datetimebox" style="width:317px"/></td>
                     <th>------------</th>
                     <td><input id="endTime" name="endTime" class="easyui-datetimebox" style="width:317px" /></td>
                </tr>
                <tr>
                     <th>标定人:</th>
                     <td> <input id="userId" name="userId" class="easyui-combobox" style="width:317px" editable="false"/></td>
                     <th>状态:</th>
                     <td  align="left">
	                     <select name="status" id="status" class="easyui-comobox" style="width:317px">
	                         <option value="2" selected="true">---全部---</option>
	                         <option value="0">正常</option>
	                         <option value="1">注销</option>
	                     </select>
                     </td>
                </tr>
                <tr>
                     <th></th>
                     <td style="width:317px"></td>
                     <th></th>
                     <td  align="center">
	                    &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="searchData()">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearSearch()">清空条件</a>
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
          <div onclick="remove()" data-options="iconCls:'icon-remove'">删除</div>
     </div>
</body>
</html>
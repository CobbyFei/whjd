<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆限行信息管理</title>
<script type="text/javascript">
    var datagrid;
   var vt_data=[{"value":"2","label":"超标排放"},{"value":"1","label":"违规限行"}];
   var is_data=[{"value":"1","label":"是"},{"value":"0","label":"否"}];
    function vt_unitformatter(value,rowData,rowIndex)
    {
        for(var i=0;i<vt_data.length;i++)
        {
            
            var valueStr=value.trim();
            if(vt_data[i].value==valueStr)
            {
              
               return vt_data[i].label;
            }
        }
        return vt_data[0].label;
    }
    
    function is_unitformatter(value,rowData,rowIndex)
    {
        for(var j=0;j<is_data.length;j++)
        {
            if(is_data[j].value==value)
               return is_data[j].label;
        }
        return is_data[0].label;
    }
    
    $(function(){
    
          $('#dd').dialog({ 
            title:'从文件导入',
            width:300,
            height:100,
			collapsible: true, 
			minimizable: true, 
			maximizable: true, 
			closed:true,
			buttons: [{ 
			text: '提交', 
			iconCls: 'icon-ok', 
			handler: function() { 
			     importFile();
			} 
			}, { 
			text: '取消', 
			handler: function() { 
			        $('#dd').dialog('close'); 
			} 
			}] 
	   }); 

       datagrid=$('#datagrid').datagrid({
           url:'${pageContext.request.contextPath}/vehicleLimitAction!getAllVehicleLimitRecord.action',
           title:'限行车辆信息列表',
           iconCls:'icon-save',
           pagination:true,
           pagePosition:'bottom',
           pageSize:10,
           pageList:[10,20,30,40],
           fit:true,
           fitColumns:false,
           nowrap:false,
           border:false,
           idField:'id',
           sortName:'id',
           sortOrder:'asc',
           checkOnSelect:false,
           selectOnCheck:true,
           frozenColumns:[[{
              title:'编号',
              field:'id',
              width:150,
              sortable:true,
              checkbox:true
           }]],
           columns:[[{
              title:'车牌号',
              field:'licence',
              width:150
           },{
              title:'车牌颜色',
              field:'licenceColor',
              width:50
           },{
              title:'违规类别',
              field:'violationType',
              width:150,
              formatter:vt_unitformatter,
              editor:{
                  type:'combobox',
                  options:{
                      required:true,
                      editable:false,
                      valueField:'value',
                      textField:'label',
                      data:vt_data
                  }
              }
           },{
               title:'违规时间',
               field:'violationTimePage',
               width:150
           },{
               title:'违规地点',
               field:'violationAddress',
               width:150
           },{
               title:'是否已接受处罚',
               field:'isPunishedPage',
               width:150,
               formatter:is_unitformatter,
               editor:{
                 type:'combobox',
                 options:{
                      required:true,
                      editable:false,
                      valueField:'value',
                      textField:'label',
                      data:is_data
                 }
             }
           },{
               title:'是否已注销',
               field:'isCancelPage',
               width:150,
               formatter:is_unitformatter,
               editor:{
                 type:'combobox',
                 options:{
                      required:true,
                      editable:false,
                      valueField:'value',
                      textField:'label',
                      data:is_data
                 }
             }
               
           },{
               title:'备注',
               field:'remarks',
               width:150
           }]],
           toolbar:[{
              text:'编辑',
              iconCls:'icon-edit',
              handler:function(){
                  edit();
              }
           },'-',{
              text:'注销',
              iconCls:'icon-remove',
              handler:function(){
                  remove();
              }

           },'-',{
              text:'从Excel文件导入',
              iconCls:'icon-undo',
              handler:function(){
                  dialog_open();
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
                  cancelSelection(datagrid);
              }
           },'-'],
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
				 if (row.isCancelPage == 1) 
				  {
					return 'color:red;';
				  }
				}
       
       });
    });
    

	//导出信息
	function myExport(){
	    var rows = datagrid.datagrid('getData').rows;
		var ids = [];
		if (rows.length > 0) {
		  $.messager.confirm('提示',"确定要导出excel信息？",function(r){
		      if(r)
		      {
		          for ( var i = 0; i < rows.length; i++) {
				      ids.push(rows[i].id);
			      }
			     $.ajax({
			     	url : sy.bp() + '/vehicleLimitAction!exportVehicleLimit.action',
				   data : {
					  ids : ids.join(',')
				   },
					dataType : 'json',
					success : function(d) {
						cancelSelection(datagrid);
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
	
    function  edit()
    {
       var rows=datagrid.datagrid("getChecked");
       if(rows.length==1)
       {
            var p=sy.dialog({
                title:'修改限行车辆信息',
                href:'${pageContext.request.contextPath}/vehicleLimitAction!vehicleLimitRecordEdit.action',
                width:350,
                height:450,
                resizable:true,
                buttons:[{
                    text:'修改',
                    iconCls:'icon-edit',
                    handler:function(){
                        // alert("将要进行修改操作");
                         var f=p.find('form');
                         f.form('submit',{
                              url:'${pageContext.request.contextPath}/vehicleLimitAction!modifyVehicleLimitRecord.action',
                              success:function(d){
                                    var  json=$.parseJSON(d);
                                    if(json.success)
                                    {
                                        cancelSelection(datagrid);
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
                         id:rows[0].id,
                         licence:rows[0].licence,
                         licenceColor:rows[0].licenceColor,
                         violationType:rows[0].violationType.trim(),
                         violationTimePage:rows[0].violationTimePage,
                         violationAddress:rows[0].violationAddress,
                         isPunishedPage:rows[0].isPunishedPage,
                         isCancelPage:rows[0].isCancelPage,
                         remarks:rows[0].remarks
                    });
                    
                }
            });
       }else if(rows.length>1)
       {
           sy.messagerAlert('提示','同一时间只能编辑同一条!','error');
       }else{
           sy.messagerAlert('提示','请选择要编辑的记录','error');
       }
    }
   //注销，支持批量限行车辆信息的注销
   function remove()
   {
   		var rows=datagrid.datagrid('getChecked');
   		var ids=[];
   		if(rows.length>0)
   		{
   		   sy.messagerConfirm('请确认','您确定要注销选中车辆信息？',function(r){
   		        if(r){
   		           for(var i=0;i<rows.length;i++)
   		           {
   		              if(rows[i].isCancelPage=="0")   //过滤掉已经注销的车辆限行信息
   		              {
   		                  ids.push(rows[i].id);
   		              }
   		           }
                   if(ids.length==0)
                   {
                     sy.messagerAlert('提示','所选车辆信息均已注销','error');
                      return;
                   }
   		           $.ajax({
   		               url:'${pageContext.request.contextPath}/vehicleLimitAction!cancelVehicleLimitRecord.action',
   		               data:{ids:ids.join(',')},
   		               dataType:'json',
   		               success:function(d){
   		                   sy.messagerAlert('提示','注销限行车辆信息成功','info');
   		                   datagrid.datagrid('load',sy.serializeObject($("#searchForm")));
   		               }
   		           });
   		        }
   		   });
   		}else
   		{
   		    sy.messagerAlert('提示','请勾选要删除的记录','error');
   		}
   }
   

   
   function searchData(){
       datagrid.datagrid('load',sy.serializeObject($("#searchForm")));
   }
   function clearSearch()
   {
       datagrid.datagrid('load',{});
       $("#searchForm").form('clear');
   }
   //跨页数据也支持
   function cancelSelection(datagrid)
   {
       datagrid.datagrid('clearSelections');
       datagrid.datagrid('clearChecked');
   }
   
   function dialog_open()
   {
       $("#dd").dialog("open");

   }
  
  function importFile()
  {
      $.messager.confirm('请确定','确定要导入数据吗？',function(r){
           if(r)
           {            
             $("#upload").form('submit',{
		            url:sy.bp()+'/vehicleLimitAction!importExcel.action',
		            onSubmit:function(){
		                if($("#myFile").val().trim()=="")
		                {
		                    $.messager.alert("提示","请选择要导入的文件","error");
		                    return false;
		                }
		                var index=$("#myFile").val().trim().lastIndexOf(".");
		                if($("#myFile").val().trim().substring(index+1)!="xls" && $("#myFile").val().trim().substring(index+1)!="xlsx")
		                {
		                    $.messager.alert("提示","必须选择xls或者xlsx格式的文件","error");
		                    return false;
		                }
		                return true;
		            },
		            success:function(data){
		                var result=eval("("+data+")");
		                if(result.success)
		                {
		                   $("#dd").dialog("close");
		                   $.messager.alert('提示','导入成功','info');
		                   clearSearch();
		                   
		                }
		                else
		                {
		                    $.messager.alert('提示',result.msg,'error');
		                }
		            }
		        });
            
           }
      });
  
  }
  
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false,title:'过滤条件'" style="height:150px;overflow:hidden;" align="left">
       <form id="frm_down" method="post"
			action="/whjd/downloadFileAction!export.action" target="_self">
			<input id="filePath" name="filePath" type="hidden" />
		</form>
       <form id="searchForm">
            <table class="tableForm datagrid-toolbar" style="width:100%;height:100%">
                <tr>
                     <th>车牌号:</th>
                     <td><input name="licence" style="width:317px"/></td>
                     <th>违规类别:</th>
                     <td>
	                     <select id="violationType" name="violationType" class="easyui-comobox" style="width:317px">
		                      <option value="0" selected="true">--全部--</option>
		                      <option value="1">违规限行</option>
		                      <option value="2">超标排放</option>
	                     </select>
                     </td>
                </tr>
                <tr>
                     <th>违规时间:</th>
                     <td> <input id="beginTime" name="beginTime" class="easyui-datetimebox" style="width:317px"/></td>
                     <th>------------</th>
                     <td><input id="endTime" name="endTime" class="easyui-datetimebox" style="width:317px" /></td>
                </tr>
                <tr>
                    <th>是否注销:</th>
                     <td>
	                     <select id="isCancelPage" name="isCancelPage" class="easyui-comobox" style="width:317px">
		                      <option value="2" selected="true">--全部--</option>
		                      <option value="1">已注销</option>
		                      <option value="0">未注销</option>
	                     </select>
                     </td>
                     <th>是否已接受处罚:</th>
                     <td>
	                     <select id="isPunishedPage" name="isPunishedPage" class="easyui-comobox" style="width:317px">
		                      <option value="2" selected="true">--全部--</option>
		                      <option value="1">已接受处罚</option>
		                      <option value="0">未接受处罚</option>
	                     </select>
                     </td>
                </tr>
                <tr>
                     <th></th>
                     <td align="right">
	                   
                     </td>
                     <th></th>
                     <td  align="center">
	                    &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="searchData()">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearSearch()">清空条件</a>
                     </td>
                </tr>
            </table>
       </form>
    </div>
    
    <div data-options="region:'center',border:false" style="overflow:hidden;">
        <table id="datagrid"></table>
    </div>
    
    <div id="menu" class="easyui-menu" style="width:120px;display:none">
        <div onclick="remove()" data-options="iconCls:'icon-remove'" >注销</div>
        <div onclick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
    </div>
	<div id="dd">
        <form id="upload" enctype="multipart/form-data"  method="post">
           <input type="file" name="myFile" id="myFile" />
        </form>
   </div>
    </body>
</html>
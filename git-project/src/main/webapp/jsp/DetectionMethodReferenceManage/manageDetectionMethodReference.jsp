<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>检测方法参照信息管理</title>
<script type="text/javascript">
    var datagrid;
    $(function(){
	    datagrid=$("#datagrid").datagrid({
	         url:sy.bp() + '/detectionMethodReferenceAction!getAllDetectionMethodReferenceRecord.action',
	         title : '检测方法参照列表',
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
			       title:'检测方法名称',
			       field:'detectionMedhodName',
			       width:150
			 },{
			       title:'燃油类型',
			       field:'fuelType',
			       width:150
			 },{
			       title:'车身最小长度',
			       field:'lengthMin',
			       width:150
			 },{
			       title:'车身最大长度',
			       field:'lengthMax',
			       width:150
			 },{
			       title:'车身最小重量',
			       field:'weightMin',
			       width:150
			 },{
			      title:'车身最大重量',
			       field:'weightMax',
			       width:150
			 }]],
			toolbar : [ {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			
			}, '-', {
				text : '取消选中',
				iconCls : 'icon-undo',
				handler : function() {
					cancelSelections(datagrid);
				}
			}, '-',{
			    text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					searchData();
				}
			},'-'],
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
    function edit(){
          var rows=datagrid.datagrid("getChecked");
          if(rows.length==1)
          {
            var flag=true;
           var p = sy.dialog({
					title : '修改检测方法参照信息',
					href : '${pageContext.request.contextPath}/detectionMethodReferenceAction!edit.action',
					width : 500,
					height : 450,
					resizable : true,
					buttons : [ {
						text : '修改',
						iconCls : 'icon-edit',
						handler : function() {
							var f = p.find('form');
							
					        f.form('submit', {
								url : '${pageContext.request.contextPath}/detectionMethodReferenceAction!modifyDetectionMethodReferenceRecord.action',
							 	onSubmit : function() {
							 	    var fuelType= p.find('form').find("input[name=fuelType]").val().trim();
							 	    var methodName=p.find('form').find("input[name=detectionMedhodName]").val().trim();
							 	    if(methodName!="双怠速法"&&methodName!="加载减速法"&&methodName!="稳态工况法"&&methodName!="摩托车双怠速法"&&methodName!="自由加速法")
			                       {
			                           $.messager.alert('提示','检测方法输入不正确,请看输入提示！','error');
			                           return false;
			                       }
			                       if(fuelType!="汽油" && fuelType!="柴油" && fuelType!="天然气")
			                       {
			                           $.messager.alert('提示','燃油类型必须是：汽油、柴油、天然气中的一种，请重新输入！','error');
			                           return false;
			                       }
			                       var isValid=p.find('form').form("validate");
			                       if(parseFloat(p.find('form').find("input[name=lengthMax]").val())<parseFloat(p.find('form').find("input[name=lengthMin]").val()))
			                       {
			                           $.messager.alert('提示','车身最大长度不能小于最小长度','error');
			                           return false;
			                       }
			                       if(parseFloat(p.find('form').find("input[name=weightMax]").val())<parseFloat(p.find('form').find("input[name=weightMin]").val()))
			                       {
			                           $.messager.alert('提示','车身最大重量不能小于最小重量','error');
			                           return false;
			                       }
			                       if(flag && isValid)
			                       {
			                           return true;
			                       }
								   else
									 {
									    $.messager.alert('提示','检测方法名称不可用','error');
									    return false;
									 }
								}, 
								success : function(d) {
									var json = $.parseJSON(d);
									
									if (json.success) {
										$.messager.alert('提示', '修改检测线信息成功', 'info');
										datagrid.datagrid('load', sy.serializeObject($("#searchForm")));
										cancelSelections(datagrid);
										p.dialog('close');
										
									} else {
										$.messager.alert('提示', json.msg, 'error');
									}
									
								}
							});
						}
					} ],
					onLoad : function() {
						var f = p.find('form');
						f.form('load', {
							id : rows[0].id,
							detectionMedhodName : rows[0].detectionMedhodName,
							fuelType : rows[0].fuelType,
							lengthMin : rows[0].lengthMin,
							lengthMax : rows[0].lengthMax,
							weightMin : rows[0].weightMin,
							weightMax : rows[0].weightMax
						});
						
					f.find("input[name=detectionMedhodName]").bind("keyup",function(){
					   $.ajax({
							url : sy.bp() + '/detectionMethodReferenceAction!hasMethodName.action',
							dataType : 'json',
							data:{q:$(this).val(),id:rows[0].id},
							success : function(data) {
								if (data.success) {
								     flag=false;
								     $("#methodName_check").html("<font color='red'><b>名称不可用</b><font>");
								} 
								else {
								   flag=true;
								   $("#methodName_check").html("<font color='red'><b>名称可用</b><font>");
								}
							}	
						});
			         
			           });
					}
				});
              
          }else if(rows.length>1)
          {
              sy.messagerAlert('提示','同一时间只能编辑同一条!','error');
          }
          else{
             sy.messagerAlert('提示','请选择要编辑的记录','error');
          }
     }
    
    function remove(){
          var rows=datagrid.datagrid("getChecked");
          if(rows.length>0)
          {
             var ids=[];
             $.messager.confirm('请确认','确认要删除选中的检测线信息?',function(r){
                  if(r){
                    for ( var i = 0; i < rows.length; i++)
                    {
				      ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/detectionMethodReferenceAction!deleteDetectionMethodReference.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(d) {
							if (d.success) {
								sy.messagerAlert('提示', '删除检测方法参照信息成功!', 'info');
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
          }
          else{
             $.messager.alert('提示','请先选中相应要删除的记录！','error');
          }
     }
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false,title:'过滤条件'" style="height:100px;overflow:hidden;width:700px" align="left">
       <form id="searchForm">
         <table class="tableForm datagrid-toolbar" style="width:100%;height:100%">
             <tr>
					<th>检测方法名称:</th>
					<td><input name="detectionMedhodName" id="detectionMedhodName" class="easyui-validatebox"  style="width:175px" />
					</td>
					<th></th>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search"
						onclick="searchData()">查询</a>&nbsp;&nbsp;<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel"
						onclick="clearSearch()">清空条件</a></td>
				</tr>
         </table>
       </form>
    </div>
    
    <div data-options="region:'center',border:false" style="overflow:hidden">
       <table id="datagrid"></table>
    </div>
    
    <div id="menu" class="easyui-menu" style="width:120px;display:none">
       <div onclick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
	   <div onclick="remove()" data-options="iconCls:'icon-remove'">注销</div>
    </div>
</body>
</html>
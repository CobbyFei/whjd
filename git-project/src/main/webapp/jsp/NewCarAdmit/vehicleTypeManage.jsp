<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车型库信息管理</title>
  <script type="text/javascript">
      var datagrid;
      $(function(){
	      
            datagrid=$("#datagrid").datagrid({
                url:'${pageContext.request.contextPath}/emissionStandardAction!getAllVehicleType.action',
                title : '车型库信息',
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
			    columns:[[{
			    	title:'车辆型号',
				    field : 'cLXH',
					width : 140
			    },{
			    	title:'车辆名称',
				    field:'cLMC',
				    width:120
			    },{
			    	title:'发动机型号',
				    field : 'fDJXH',
					width : 100
			    },{
			       title : '发动机生产厂',
				   field : 'fDJSCC',
				   width : 180
			    },{
			       title : '制造商',
				   field : 'mANUF',
				   width : 180
				},{
			       title:'车辆商标',
			       field : 'cLSB',
				   width : 120
			    },{
			       title:'核准日期',
			       field : 'fILENAME',
				   width : 100
			    },{
			       title:'排放标准',
			       field : 'pF',
				   width : 80,
				   formatter:function(value,rowData,rowIndex)
				   {
					   return "国"+value;
				   }
			    },{
				   title:'车辆类别',
				   field : 'cLLB',
				   width : 100
				},
			    
			    ]],
			    toolbar : [ {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
				        edit();
				}
				},'-',{
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						remove();
					}
				},'-', {
					text : '取消选中',
					iconCls : 'icon-undo',
					handler : function() {
						cancelSelections(datagrid);
					}
				},'-',{
				    text : '刷新',
					iconCls : 'icon-reload',
					handler : function() {
						searchData();
					}
				} 
				],
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
							if (rows[i].ID == rowData.ID) {
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
	
	//编辑修改车型信息
	function edit(){
	     var rows=datagrid.datagrid("getChecked");
	     if(rows.length==1)
	     {
	    	 var p=sy.dialog({
	               title : '修改车型信息(带*的为关键信息，不允许修改)',
	               href: '${pageContext.request.contextPath}/emissionStandardAction!edit.action',
	               width:600,
	               height:300,
	               resizable:false,
	               buttons:[{
	                   text:'修改',
	                   iconCls:'icon-edit',
	                   handler:function(){
	                       var f = p.find('form');
	                       f.form('submit', {
								url : '${pageContext.request.contextPath}/emissionStandardAction!modifyVehicleType.action',
								success : function(d) {
									var json = $.parseJSON(d);
									if (json.success) {
										$.messager.alert('提示', '修改车型信息成功', 'info');
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
							'ID' : rows[0].iD,
							'CLXH': rows[0].cLXH,
							'CLMC':rows[0].cLMC,
							'FDJXH': rows[0].fDJXH,
							'FDJSCC': rows[0].fDJSCC,
							'MANUF': rows[0].mANUF,
							'CLSB': rows[0].cLSB,
							'FILENAME': rows[0].fILENAME,
							'PF': rows[0].pF,
							'CLLB': rows[0].cLLB	
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
   
	
    //删除车型信息
	function remove() {
		var rows = datagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.sy
					.messagerConfirm(
							'请确认',
							'确定要删除所选记录？',
							function(r) {
								if (r) {
									for ( var i = 0; i < rows.length; i++) {
										ids.push(rows[i].iD);
									}
									if (ids.length == 0) {
										$.messager.show('提示', '所选信息均已删除',
												'error');
										return;
									}
									$
											.ajax({
												url : sy.bp()
														+ '/emissionStandardAction!deleteVehicleType.action',
												data : {
													ids : ids.join(',')
												},
												dataType : 'json',
												success : function(d) {
													cancelSelections(datagrid);
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
			parent.sy.messagerAlert('提示', '请选择要删除的记录！', 'error');
		}
	}
	
  </script>
</head>
<body class="easyui-layout" data-options="fit:true">
  <div data-options="region:'north',border:false,title:'过滤条件'" style="height:190px;overflow:hidden;width:700px"
		align="left">
		<form id="searchForm">
		    <table class="tableForm datagrid-toolbar" style="width:100%;height:100%">
		       <tr>					
					<th>车辆型号:</th>
					<td><input name="CLXH" id="CLXH" class="easyui-validatebox" style="width:175px" /></td>
					<th>车辆名称:</th>
					<td><input name="CLMC" id="CLMC" class="easyui-validatebox"  style="width:175px" /></td>
				</tr>
				<tr>					
					<th>发动机型号:</th>
					<td><input name="FDJXH" id="CDJXH" class="easyui-validatebox" style="width:175px" /></td>
					<th>发动机生产厂:</th>
					<td><input name="FDJSCC" id="FDJSCC" class="easyui-validatebox"  style="width:175px" /></td>
				</tr>
				<tr>					
					<th>制造商:</th>
					<td><input name="MANUF" id="MANUF" class="easyui-validatebox" style="width:175px" /></td>
					<th>车辆商标:</th>
					<td><input name="CLSB" id="CLSB" class="easyui-validatebox"  style="width:175px" /></td>
				</tr>
				<tr>
					<th>核准日期:</th>
					<td><input name="beginTime" id="beginTime" class="easyui-datebox" data-options="editable:false" style="width:175px" />
					</td>
					<th>---------</th>
					<td><input name="endTime" id="endTime" class="easyui-datebox" style="width:175px" data-options="editable:false"/></td>
				</tr>
				<tr>
					<th>排放标准:</th>
					<td>
					   <select name="PF" id="PF" style="width:175px" class="easyui-combobox" 
					   data-options="required:true , editable:false, panelHeight:65">
							<option value="0" selected="true">----全部----</option>
							<option value="1">国1</option>
							<option value="2">国2</option>
							<option value="3">国3</option>
							<option value="4">国4</option>
							<option value="5">国5</option>
							<option value="6">国6</option>
					   </select>
				   </td>
					
				   <th algin="right">车辆类别:</th>				   
				   <td align="left">
				        <select name="CLLB" id="CLLB" style="width:175px" class="easyui-combobox"
						data-options="required:false , editable:false, panelHeight:65">
							<option value="" selected="true">----全部----</option>
							<option value="轻型汽油车">轻型汽油车</option>
							<option value="轻型柴油车">轻型柴油车</option>
							<option value="重型汽油车">重型汽油车</option>
							<option value="重型柴油车">重型柴油车</option>
							<option value="天燃气车">天燃气车</option>
							<option value="混合动力车">混合动力车</option>
							<option value="摩托车">摩托车</option>
							<option value="其他">其他</option>
					</select></td>   
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
	
</body>
</html>
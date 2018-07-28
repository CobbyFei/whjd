<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript" charset="utf-8">
var flag=false;
	$(function() {
	     var date=new Date();
	     var dateStr=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	     $("#collarTime").datebox('setValue',dateStr);
		 
		 $("#userId").combobox({
			url : sy.bp() + '/manageUserAction!getSysUsers.action',
			mode : 'remote',
			delay : 500,
			valueField : 'userId',
			required : true,
			textField : 'userName',
			editable:false
		});
		
		$('#CancelBtn').bind('click', function() {
			cancel();
		});
		$('#AddBtn').bind('click', function() {
			add();
		});

	});

	function cancel()
	{
		$('#addEnvironmentalLabelCollarInfo').form('reset');
		var date=new Date();
	    var dateStr=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	    $("#collarTime").datebox('setValue',dateStr);
	}
	
    function add()
    {
       $.messager.confirm('请确定','确定要添加这条记录？',function(r){
         if(r)
         {
             	$('#addEnvironmentalLabelCollarInfo').form('submit',
				{
					url : sy.bp()+ '/agencyCollarAction!addAgencyCollarRecord.action',
					onSubmit : function() {
					       var flag=check();
					       var isValid=$(this).form('validate');
					       return isValid && flag;
						},
						success : function(data) {
							var result = eval("(" + data + ")");
							if (result.success) 
							{
								   $.messager.alert('提示！', '添加市局标志领用记录成功', 'info');
									cancel();
							} 
							else 
							{
							    $.messager.alert('添加标志领用记录失败！', result.msg,'error');
							}
				        }
				});
         }
       });
    }

	function check()
	{
	   if ($('#userId').combobox('getText').trim() == '') 
	   {
			$.messager.alert('提示', '请选择领用人！', 'error');
			return false;
	   }
	    return true;
	}
	
</script>

</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false,title:'新增市局标志领用记录'" style="overflow: hidden;" align="left">
		<form id="addEnvironmentalLabelCollarInfo">
			<table style="width:700px; height:400px; padding-top:10px" cellspacing="5">
				<tr height="30px">
					<td align="right" style="width：20%">领用人：</td>
					<td align="left" style="width：30%"><input name="userId" id="userId" style="width:175px" />
					</td>
				</tr>
				<tr height="30px">
					<td align="right">领用时间：</td>
					<td align="left"><input type="text" name="collarTime" id="collarTime" class="easyui-datebox"  style="width:175px" required="true" editable="false"/></td>
				</tr>
				<tr height="30px">
					<td align="right">标志年度号：</td>
					<td align="left"><input name="year" id="year" class="easyui-numberbox" data-options="min:0" style="width:153px" required="true"/></td>
				</tr>
				<tr height="30px">
					<td align="right">绿标数量：</td>
					<td align="left"><input name="greenLabelNum" id="greenLabelNum" class="easyui-numberbox" data-options="value:0,min:0,groupSeparator:','" style="width:153px" required="true"/></td>
				</tr>
				<tr height="30px">
				    <td align="right">黄标数量：</td>
					<td align="left"><input name="yellowLabelNum" id="yellowLabelNum" class="easyui-numberbox" data-options="value:0,min:0,groupSeparator:','" style="width:153px" required="true"/></td>
				</tr>
				<tr height="30px">
				    <td align="right">状态：</td>
					<td align="left">
					    <select name="status" id="status" class="easyui-combobox"  editable="false">
	                        <option value="0" selected="true">正常</option>
	                        <option value="1">注销</option>
	                    </select>
					</td>
				</tr>
				
				<tr height="30px">
					<td align="right">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					<td align="left" colspan="3">
					<textarea style="height:60px;font-size:12px" id="remarks" name="remarks" cols="47" rows="2" maxlength="500"></textarea></td>
				</tr>
				<tr height="50px">
					<td></td>
					<td><a class="easyui-linkbutton" icon="icon-add" id="AddBtn" name="Add" href="javascript:void(0)" >添加</a></td>
					<td></td>
					<td><a class="easyui-linkbutton" icon="icon-reload" id="CancelBtn" name="Refresh" href="javascript:void(0)">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

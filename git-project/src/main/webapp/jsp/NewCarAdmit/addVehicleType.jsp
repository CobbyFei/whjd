<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加车辆类型</title>

<script type="text/javascript">
	/*
	$(function(){
		$('#CLLB').combobox({
			onSelect: function(param){
				if(param.text=='其他'){
					//$('#otherCLLBdiv').css("display", "none"); 
					//document.getElementById('otherCLLB').style.display = "";
					document.getElementById("otherCLLB").style.visibility="visible";
				}else{
					//$('#otherCLLBdiv').css("display", "block"); 
					document.getElementById('otherCLLB').style.display = "none";
				}
			}
		});	
	}); */

	function reset_form() {
		$("#add_vehicle_type_info").form('reset');
	}
	
	function add(){
		if($("#CLXH").val().trim()=="")
        {
        		$.messager.alert('提示','车辆型号不能为空。','info');
        		return false;
        }
    	if($("#FDJXH").val().trim()=="")
        {
        		$.messager.alert('提示','发动机型号不能为空','info');
        		return false;
        }
    	if($('#FILENAME').datebox('getValue').trim()=="")
        {
        		$.messager.alert('提示','核准日期不能为空.','info');
        		return false;
        }
    	if($("#PF").combobox('getValue').trim()=="0")
        {
    		$.messager.alert('提示','排放标准不能为空.','info');
     		return false;
        }
    	
    	 $.messager.confirm("请确认","您添加的车辆型号为："+ $("#CLXH").val().trim()+",发动机型号为："+$("#FDJXH").val().trim()+",核准日期为："+$('#FILENAME').datebox('getValue').trim()+",排放标准为:"+$("#PF").combobox('getValue').trim()+",提交后将不能更改！",function(r){
        		if(r)
        		{
		           		$("#add_vehicle_type_info").form('submit',{
				             url:sy.bp()+'/emissionStandardAction!addVehicletype.action',
				             onSubmit:function(){
				                       var isValid=$(this).form("validate");
				                       if(isValid==false)
				                           return false;
				                       if(isValid)
				                           return true;
				                       return false;
				                        
				                 },
				            success:function(data){
				                    var result=eval("("+data+")");
				                    if(result.success)
					                {
					                   $.messager.alert('提示','添加车型信息成功','info');
					                   reset_form();
					                }
					                else
					                {
					                    $.messager.alert('提示',result.msg,'error');
					                   // reset_form();
					                }
				                 }
				          });
        		}
        });
    	
	}

</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false,title:'添加车辆类型'"
		style="overflow: hidden;" align="left">
		<form id="add_vehicle_type_info">
			<table style="width: 700px; height: 400px; padding-top: 10px"
				cellspacing="5">
				<tr height="30px">
					<td align="right" style="width: 20%">车辆型号:</td>
					<td align="left" style="width: 30%"><input name="CLXH"
						id="CLXH" class="easyui-validatebox" data-options="required:true"
						maxLength='50' style="width: 175px" /></td>
				</tr>
				<tr height="30px">
					<td align="right" style="width: 20%">车辆名称:</td>
					<td align="left" style="width: 30%"><input name="CLMC"
						id="CLMC" class="easyui-validatebox" data-options="required:false"
						maxLength='50' style="width: 175px" /></td>
				</tr>
				<tr height="30px">
					<td align="right" style="width: 20%">发动机型号:</td>
					<td align="left" style="width: 30%"><input name="FDJXH"
						id="FDJXH" class=""
						easyui-validatebox""
						data-options="required:true"
						style="width: 175px" /></td>
				</tr>
				<tr height="30px">
					<td align="right" style="width: 20%">发动机生产厂:</td>
					<td align="left" style="width: 30%"><input name="FDJSCC"
						id="FDJSCC" class=""
						easyui-validatebox""
						data-options="required:false"
						style="width: 175px" /></td>
				</tr>
				<tr height="30px">
					<td align="right" style="width: 20%">制造商:</td>
					<td align="left" style="width: 30%"><input name="MANUF"
						id="MANUF" class=""
						easyui-validatebox""
						data-options="required:false"
						style="width: 175px" /></td>
				</tr>
				<tr height="30px">
					<td align="right" style="width: 20%">车辆商标:</td>
					<td align="left" style="width: 30%"><input name="CLSB"
						id="CLSB" class=""
						easyui-validatebox""
						data-options="required:false"
						style="width: 175px" /></td>
				</tr>
				<tr height="30px">
					<td align="right" style="width: 15%">核准日期:</td>
					<td align="left" style="width: 25%"><input name="FILENAME"
						id="FILENAME" class="easyui-datebox"
						data-options="required:true,editable:false" style="width: 175px" /></td>
				</tr>
				<tr height="30px">
					<td align="right" style="width: 20%">排放标准:</td>
					<td><select name="PF" id="PF" style="width: 150px"
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
				<tr height="30px">
					<td align="right" style="width: 20%">车辆类别:</td>
					<td><select name="CLLB" id="CLLB" style="width: 150px"
						class="easyui-combobox"
						data-options="required:false , editable:false, panelHeight:65">
							<option value="" selected="true">请选择</option>
							<option value="轻型汽油车">轻型汽油车</option>
							<option value="轻型柴油车">轻型柴油车</option>
							<option value="重型汽油车">重型汽油车</option>
							<option value="重型柴油车">重型柴油车</option>
							<option value="天燃气车">天燃气车</option>
							<option value="混合动力车">混合动力车</option>
							<option value="摩托车">摩托车</option>
							<option value="其他">其他</option>
					<td align="right" style="width: 15% ;display: none ">请输入:</td>
					<td align="left" style="width: 25% ;display: none"><input name="otherCLLB"
						id="otherCLLB" class="easyui-datebox"
						data-options="required:false,editable:false" style="width: 175px" /></td>
					</select></td>
					
				</tr>

				<tr height="30px">
					<td colspan="2" align="center" style="width: 100%"><a
						class="easyui-linkbutton" icon="icon-add" name="add"
						href="javascript:void(0)" onclick="add()">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
						class="easyui-linkbutton" icon="icon-cancel" name="reset"
						href="javascript:void(0)" onclick="reset_form()">重置</a></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
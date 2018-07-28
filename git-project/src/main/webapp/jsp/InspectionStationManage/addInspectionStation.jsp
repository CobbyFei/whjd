<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript" charset="utf-8">
var flag=false;
	$(function() {
		$("#directorId").combobox({
			url : sy.bp() + '/manageUserAction!getSysUsers.action',
			mode : 'remote',
			delay : 500,
			valueField : 'userId',
			required : false,
			textField : 'userName',
		});
		  $("#stationName").bind('focus keyup blur',function(){
		         if($("#stationName").val()=="")
		         {
		             //$.messager.alert('提示',"请输入检测站名称",'error');
		             $("#stationNameCheck").html("<font color='red'><b>请输入站名称</b><font>");
		         }
		         else
		         {
				   $.ajax({
						url : sy.bp() + '/inspectionStationAction!hasInspectionStation.action',
						dataType : 'json',
						data : {
							q:$(this).val()
						},
						success : function(data) {
							if (data.success) {
							     flag=false;
							     $("#stationNameCheck").html("<font color='red'><b>名称已存在</b><font>");
							} 
							else {
							   flag=true;
							   $("#stationNameCheck").html("<font color='red'><b>名称可用</b><font>");
							}
						}	
					});
		         }
		});
	   /* $('#stationName').bind("blur", function() {//对stationName做校验
			if ($('#stationName').val() == '') {
				$.messager.alert('提示', "请输入检测站名称", 'error');
			}
		});*/
		$('#CancelBtn').bind('click', function() {
			cancel();
		});
		$('#AddBtn').bind('click', function() {
			savereg();
		});

	});

	function cancel() {
		$('#addInspectionStationInfo').form('clear');
	}


	function savereg() {

	$('#addInspectionStationInfo')
				.form('submit',
				{
				url : sy.bp()+ '/inspectionStationAction!addInspectionStation.action',
				onSubmit : function() {
					if(flag==false){
						$.messager.alert('提示', '该标志核发点名称已存在！', 'info');
						return false;
					}
				if ($('#stationName').val() == '') {
					$.messager.alert('提示', '请输入标志核发点名称！', 'info');
					return false;
					}
				return $('#addInspectionStationInfo').form('validate');
					},
					success : function(data) {
						var result = eval("(" + data + ")");
						if (result.success) {
							$.messager.alert('提示！', '添加标志核发点成功', 'info');
								cancel();
							} else {
							$.messager.alert('添加标志核发点失败！', result.msg,'error');
								}
							}
				});
	}
	
</script>

</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false,title:'新增标志核发点'" style="overflow: hidden;" align="left">
		<form id="addInspectionStationInfo">
			<table style="width:700px; height:400px; padding-top:10px" cellspacing="5">
				<tr height="30px">
					<td align="right" style="width:10%">标志核发点名称：</td>
					<td align="left" style="width:50%"><input name="stationName" id="stationName" style="width:175px" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入站名称'" maxlength="20"/><span id="stationNameCheck"></span>			
					</td>
			
				<td align="right" style="width:20%">核发点地址：</td>
				<td align="left" style="width:20%"><input name="stationAddress" id="stationAddress" style="width:175px" maxlength="40"/></td>
				</tr>
				<tr height="30px">
				    <td align="right" style="width:20%">资质种类：</td>
					<td align="left" style="width:30%">
							<select name="qulificationType" id="qulificationType" style="width:175px">
							  <option value ="A">A</option>
		                      <option value ="B">B</option>
		                      <option value ="X">X</option>
							</select>
					</td>
					<td align="right" style="width:20%">资质取得时间：</td>
					<td align="left" style="width:30%"><input type="text" name="qulificationTime" id="qulificationTime" class="easyui-datebox"  style="width:175px" required="true" editable="false"/></td>
				</tr>
				<tr height="5px">
					<td></td>
					<td>
						<font color="#FF0000" size="2px">*其他核发点类型请选择X</font> 
					</td>
				</tr>
				<tr height="30px">
				<td align="right" style="width:20%">负责人：</td>
				<td align="left" style="width:30%">
				<input name="directorId" id="directorId" style="width:175px" />
				</td>
					<td align="right" style="width:20%">注册时间：</td>
					<td align="left" style="width:30%"><input type="text" id="registrationTime" name="registrationTime" class="easyui-datebox" style="width:175px" required="true" editable="false"/></td>
				</tr>
				<tr height="30px">
					<td align="right" style="width:20%">有效期：</td>
					<td align="left" style="width:30%">
					<input type="text" name="validPeriod" id="validPeriod" class="easyui-datebox" style="width:175px" required="true" editable="false"/>
					</td>
				</tr>
				<tr height="5px">
					<td></td>
					<td>
						<font color="#FF0000" size="2px">*请将有效期选择至2099年1月1日</font> 
					</td>
				</tr>
				<tr height="30px">
					<td align="right" style="width:20%">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					<td align="left" colspan="3" style="width:30%">
					<textarea style="height:60px;font-size:12px" id="remarks" name="remarks" cols="47" rows="2" maxlength="40"></textarea></td>
				</tr>
				<tr height="5px">
					<td></td>
					<td>
						<font color="#FF0000" size="2px">*请在备注中填写联系电话</font> 
					</td>
				</tr>
				<tr height="50px">
					<td></td>
					<td><a class="easyui-linkbutton" icon="icon-add" id="AddBtn" name="Add" href="javascript:void(0)">添加</a></td>
					<td></td>
					<td><a class="easyui-linkbutton" icon="icon-reload" id="CancelBtn" name="Refresh" href="javascript:void(0)">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

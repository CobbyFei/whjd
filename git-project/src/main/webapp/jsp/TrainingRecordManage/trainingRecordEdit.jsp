<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	$(function(){
		$("#userInfoId").combobox({
				url : sy.bp()+ '/manageUserAction!getSysUsers.action',
				mode : 'remote',
				delay : 500,
				valueField : 'userId',
				required : true,
				textField : 'userName',
			});
			
		$("#strTrainingTime").datebox({
			required:true,
			editable:false
		});
		
		$("#trainingAddress").validatebox({
			required:true
		});
		
		$("#trainingType").combobox({
			required:true,
			editable:false,
			panelHeight:40
		});
		
		$("#trainingDetail").validatebox({
			required:true,
		});
				
	});
</script>

<div style="padding: 5px;">

	<form id="editSysUserinfo" method="post" enctype="multipart/form-data">

		<input name="stationId" type="hidden"/>
		<input name="trainingRecordId" type="hidden"/>

		<table style="width:700px; height:400px; padding-top:10px" cellspacing="5">

				<tr height="30px">
					<td align="left" style="width：20%">姓名：</td>
					<td align="left" style="width：30%"><input name="userId" id="userInfoId"  style="width:175px" />
					</td>
					
					<td align="left" style="width：20%">类型：</td>
					<td align="left" style="width：30%"><select name="trainingType" id="trainingType" style="width:175px">
							<option value="0" selected="true">培训</option>
							<option value="1">考核</option>
					</select></td>
				</tr>

				<tr height="30px">

					<td align="left">时间</td>
					<td align="left"><input name="strTrainingTime" id="strTrainingTime"/>
					</td>
					<td align="left">地点</td>
					<td align="left"><input name="trainingAddress" id="trainingAddress"></td>
				
				</tr>


				<tr height="30px">
					
					<td align="left">内容:</td>
					<td align="left"><input id="trainingDetail" name="trainingDetail" style="width:177px"/>
					

			</table>

	</form>
</div>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/letter.js" charset="GBK"></script>
<script type="text/javascript" charset="utf-8">
	$(function() {
		$("#userNameId").combobox({
			url : sy.bp()+ '/manageUserAction!getSysUsers.action',
			mode : 'remote',
			delay : 500,
			valueField : 'userId',
			textField : 'userName',
			required:true
		});	
		
		
			
		$('#trainingType').combobox({
			panelHeight:45,
			editable:false,
			required:true
		});
		
		$('#strTrainingTime').datebox({
			editable:false,
			required:true
		});
		
		$('#trainingAddress').validatebox({
			required:true
		});
		
		$('#trainingDetail').validatebox({
			required:true
		});
		

		$('#CancelBtn').bind('click', function() {
			cancel();
		});
		

		$('#AddBtn').bind('click', function() {
			savereg();
		});

	});
	

	function cancel() {
		$('#frm_add_trainingRecord').form('clear');
	}

	function savereg() {
		$('#frm_add_trainingRecord').form('submit', {
			url : sy.bp() + '/trainingRecordAction!addTrainingRecord.action',
			onSubmit : function() {
				var flag = $(this).form('validate');
				if(flag == false){
					parent.sy.messagerShow({
						msg : '请修改',
						title : '提示'
					});
				}else{
					return true;
				}
			},
			success : function(data) {
				var json = $.parseJSON(data);
				if (json.success) {
					parent.sy.messagerShow({
						msg : json.msg,
						title : '提示'
					});
					cancel();
				} else {
					parent.sy.messagerShow({
						msg : json.msg,
						title : '提示'
					});
				}
			}
		});
	}
</script>

</head>
<body class="easyui-layout" data-options="fit:true">

	<div data-options="region:'center',border:false,title:'新增培训或考核记录'" style="overflow: hidden;" align="center">
	
		<form id="frm_add_trainingRecord">
			
			<table style="width:750px; height:400px; padding-top:10px" cellspacing="5">
				<tr height="30px">
					<td align="left" style="width：20%">姓名：</td>
					<td align="left" style="width：30%">
						<input name="userId" id="userNameId" style="width:175px" />
						<input name="sationId" id="stationId" hidden="true">
					</td>
					<td align="left" style="width：20%">培训或考核类型：</td>
				
					<td align="left" style="width：30%"><select name="trainingType" id="trainingType" style="width:175px">
							<option value="0" selected="true">培训</option>
							<option value="1">考核</option>
					</select></td>
				</tr>
				
				<tr height="30px">
					<td align="left" style="width：20%">培训或考核时间：</td>
					<td align="left"><input name="strTrainingTime" id="strTrainingTime" style="width:175px" /></td>
					<td align="left" style="width：20%">培训或考核地址：</td>
					<td align="left" style="width：30%"><input name="trainingAddress" id="trainingAddress" style="width:175px" /></td>
				</tr>
				
				
				<tr height="100px">
					<td align="left">培训或考核具体内容：</td>
					<td align="left"><input style="width:200px" id="trainingDetail"  name="trainingDetail" />
					</td>
				</tr>				
				<tr height="50px">
					<td></td>
					<td><a class="easyui-linkbutton" icon="icon-add" id="AddBtn" name="Add" href="javascript:void(0)">添加</a>
					</td>
					<td></td>
					<td><a class="easyui-linkbutton" icon="icon-reload" id="CancelBtn" name="Refresh" href="javascript:void(0)">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	$(function() {
		
		$('#userName').combobox({
			url : '${pageContext.request.contextPath}/manageRoleAuthAction!getUserSimplifyNames.action',
			mode : 'remote',
			delay : 500,
			valueField : 'userId',
			textField : 'simplifyName',
			required : true
		});
		
		$('#roleName').combobox({
			url : '${pageContext.request.contextPath}/manageRoleAuthAction!getRoleNames.action',
			mode : 'remote',
			delay : 500,
			valueField : 'roleId',
			textField : 'roleName',
			required : true
		});
		
		$('#relationStatus').combobox({
			required : true,
			panelHeight : 40
		});
		
		$('#CancelBtn').bind('click', function() {
			cancel();
		});
		
		$('#AddBtn').bind('click', function() {
			savereg();
		});
		
	});
	
	function cancel() {
		$('#frm_add_user_role').form('clear');
	}
	
	function savereg() {
		$('#frm_add_user_role').form('submit', {
			url : sy.bp() + '/manageRoleAuthAction!addUserRole.action',
			onSubmit : function() {
				var flag = $(this).form('validate');
				if (flag) {
					return true;
				}
				parent.sy.messagerShow({
					msg : '请填写完整',
					title : '提示'
				});
				return false;
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
	<div data-options="region:'center',border:false,title:'新增用户角色'" style="overflow: hidden" align="center">
		<form id="frm_add_user_role">

			<table style="width:700px; height:400px; padding-top:10px" cellspacing="5">
				<tr height="15px">
					<td align="left" style="width：15%">系统用户名：</td>
					<td align="left" style="width：40%"><input name="userId" id="userName" style="width:175px" /></td>
				</tr>
				<tr height="15px">
					<td align="left" style="width：15%">授予角色：</td>
					<td align="left" style="width：30%"><input name="roleId" id="roleName" style="width:175px">
					</td>
				</tr>

				<tr height="15px">


					<td align="left" style="width：20%">状态：</td>

					<td align="left" style="width：30%"><select name="relationStatus" id="relationStatus" style="width:175px" editable="false">
							<option value="0">注销</option>
							<option value="1" selected="true">正常</option>
					</select>
					</td>

				</tr>

				<tr height="20px">
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

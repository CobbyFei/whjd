<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	var roleNameValide = false;
	$(function() {
		
		$('#roleName').validatebox({
			required : true
		});
		
		$('#roleName').bind('focus keyup blur', function() {
			$.ajax({
				url : sy.bp() + '/manageRoleAuthAction!hasTheRole.action',
				dataType : 'json',
				data : {
					q : $(this).val()
				},
				success : function(data) {
				   
					if (data.success) {
						roleNameValide = false;
						$('#nameCheck').html("<font color='red'><b>"+data.msg+"</b><font>");
						
					} else {
						if ($('#roleName').val() != '') {
							roleNameValide = true;
							$('#nameCheck').html("<font color='red'><b>"+data.msg+"</b><font>");
						} else {
							roleNameValide = false;
							$('#nameCheck').html("<font color='red'><b>"+data.msg+"</b><font>");
						}
					}
				},
				
				error : function(data) {
					roleNameValide = false;
					$('#nameCheck').html("<font color='red'><b>"+data.msg+ + "请重新输入！"+"</b><font>");
				}
			});
		});
		
		$('#roleDescription').validatebox({
			required : true
		});
		
		$('#roleStatus').combobox({
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
		$('#frm_add_new_role').form('clear');
	}
	
	function savereg() {
		$('#frm_add_new_role').form('submit', {
			url : sy.bp() + '/manageRoleAuthAction!addNewRole.action',
			onSubmit : function() {
				var flag = $(this).form('validate');
				if (flag && roleNameValide) {
					roleNameValide = false;
					return true;
				}
				parent.sy.messagerShow({
					msg : '请修改角色名',
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
	<div data-options="region:'center',border:false,title:'新增角色信息'" style="overflow: hidden;" align="center">
		<form id="frm_add_new_role">

			<table style="width:700px; height:400px; padding-top:10px" cellspacing="5">
				<tr height="30px">
					<td align="left" style="width：15%">角色名：</td>
					<td align="left" style="width：40%"><input name="roleName" id="roleName" style="width:175px" /><span id="nameCheck" name="nameCheck" style="width:60px"></span>
					</td>
					<td align="left" style="width：15%">描述：</td>
					<td align="left" style="width：30%"><input name="roleDescription" id="roleDescription" style="width:175px">
					</td>

				</tr>
				<tr height="30px">


					<td align="left" style="width：20%">状态：</td>

					<td align="left" style="width：30%"><select name="roleStatus" id="roleStatus" style="width:175px"  editable="false">
							<option value="0">注销</option>
							<option value="1" selected="true">正常</option>
					</select></td>

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

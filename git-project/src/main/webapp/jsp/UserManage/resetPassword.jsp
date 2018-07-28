
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/letter.js" charset="GBK"></script>
<script type="text/javascript" charset="utf-8">
	$(function() {

		$('#CancelBtn').bind('click', function() {
			cancel();
		});

		$('#AddBtn').bind('click', function() {
			savereg();
		});

	});
	

	function cancel() {
		$('#frm_rs_pwd').form('clear');
	}

	function savereg() {
		$('#frm_rs_pwd').form('submit', {
			url : sy.bp() + '/passwordAction!resetPassword.action',
			onSubmit : function() {
				var flag = $(this).form('validate');
				if (flag ) {
					return true;
				}
				parent.sy.messagerShow({
					msg : '提交失败！请检查所填写的信息！',
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
	<div data-options="region:'center',border:false,title:'重置口令'" style="overflow: hidden;" align="center">
		<form id="frm_rs_pwd">
			
			<h3 style="color:#0000FF">提示： 请谨慎操作！</h3>
			<table style="width:700px; height:400px; padding-top:10px" cellspacing="5">
				<tr height="30px">
					<td align="center" style="width：20%">用户名：</td>
					<td align="center" style="width：30%"><input  name="simplifyName" id="simplifyName" class="easyui-validatebox" required="required" style="width:175px" />
					</td>
					<td align="left"><a class="easyui-linkbutton" icon="icon-add" id="AddBtn" name="Add" href="javascript:void(0)">重置口令</a>
					</td>
					<td align="left"><a class="easyui-linkbutton" icon="icon-reload" id="CancelBtn" name="Refresh" href="javascript:void(0)">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

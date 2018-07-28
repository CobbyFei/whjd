<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/letter.js" charset="GBK"></script>
<script type="text/javascript" charset="utf-8">
	var simplifyNameValide= false;
	$(function() {
				
		$('#userName').validatebox({
			required : true
		});
	
		/*
		Author moxuan*/
		$('#simplifyName').bind('focus keyup blur', function() {
			$.ajax({
				url : sy.bp() + '/manageUserAction!hasUserOfSimplifyName.action',
				dataType : 'json',
				data : {
					q : $(this).val()
				},
				success : function(data) {
					if (data.success) {
						simplifyNameValide= false;
						$('#simplifyNameComment').linkbutton({
							disabled : true,
							text : data.msg
						});
						
					} else {
						if ($('#simplifyName').val() != '') {
							simplifyNameValide= true;
							$('#simplifyNameComment').linkbutton({
								disabled : true,
								text : data.msg
							});
						} else {
							simplifyNameValide= false;
							$('#simplifyNameComment').linkbutton({
								disabled : true,
								text : data.msg
							});
						}
						
					}
				},
				
				error : function(data) {
					simplifyNameValide= false;
					$('#simplifyNameComment').linkbutton({
						disabled : true,
						text : "请重新输入！"
					});
				}
			});
		});
		
	
		
	
		$('#simplifyName').focus(function() {
			$('#simplifyName').val(makePy($('#userName').val()));
		});
		
		
		
		$('#sex').combobox({
			required:true,
			editable:false,
			panelHeight:40
		});
		
		
		$('#degree').combobox({
			required:true,
			editable:false
		});
		
		$('#certificateName').validatebox({
			required : true
			
		});
		
		$('#strCertificateTime').datebox({
			required : true,
			editable : false
		});
		
		//取得用户信息并操作stationId combobox 状态 市局可用其余固定
		var sessionStationName = '${sessionScope.stationName}';
		if(sessionStationName != "市局"){
			$("#stationInfo").replaceWith('<a>'+sessionStationName+'</a>');	
		}else{	
			$("#stationId").combobox({
				url : sy.bp()+ '/inspectionStationAction!getInspectionStationName.action',
				mode : 'remote',
				delay : 500,
				valueField : 'stationId',
				textField : 'stationName',
			});
		}	
	

		$('#CancelBtn').bind('click', function() {
			cancel();
		});

		$('#AddBtn').bind('click', function() {
			savereg();
		});

	});
	

	function cancel() {
		$('#frm_add_new_user').form('clear');
	}

	function savereg() {
		$('#frm_add_new_user').form('submit', {
			url : sy.bp() + '/manageUserAction!addNewUser.action',
			onSubmit : function() {
				var flag = $(this).form('validate');
				if (flag &&simplifyNameValide) {
					simplifyNameValide= false;
					return true;
				}
				if(flag== false){
					parent.sy.messagerShow({
						msg : '请修改错误信息',
						title : '提示'
					});
				}
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
	<div data-options="region:'center',border:false,title:'新增人员信息'" style="overflow: hidden;" align="center">
		<form id="frm_add_new_user">
			
			<h3 style="color:#0000FF">提示： 1. 用户名应为以字母开头的2-16个字母下划线或数字的组合,并且不区分大小写！</h3>
			<h3 style="color:#0000FF">     2. 默认口令为用户名的小写形式！</h3>
			<table style="width:700px; height:400px; padding-top:10px" cellspacing="5">
				<tr height="30px">
					<td align="left" style="width：20%">姓名：</td>
					<td align="left" style="width：30%"><input name="userName" id="userName" style="width:175px" />
					</td>
					<td align="left" style="width：20%">用户名：</td>
					<td align="left" style="width：30%">
						<input name="simplifyName" id="simplifyName" style="width:175px">
					</td>
					<td align="left" style="width：30%">
						<div align="right" id="simplifyNameComment" class="easyui-linkbutton" style="width:100px ; color:red"></div></td>
				</tr>
				<tr height="30px">

					<td align="left">身份证号：</td>
					<td align="left"><input name="idcard" id="idcard" class="easyui-validatebox"
						validType="idcard" data-options="required:true"  style="width:175px" />
					</td>
					<td align="left" style="width：20%">性别：</td>

					<td align="left" style="width：30%"><select name="sex" id="sex" style="width:175px">
							<option value="男" selected="true">男</option>
							<option value="女">女</option>
					</select></td>

				</tr>

				<tr height="30px">
					<td align="left">所获证书名称:</td>
					<td align="left"><input id="certificateName" name="certificateName" style="width:175px" />
					</td>
					<td align="left">取得证书日期：</td>
					<td align="left"><input type="text" name="strCertificateTime" id="strCertificateTime" style="width:175px" />
					</td>
				</tr>

				<tr height="30px">
					<td align="left">学历：</td>
					<td align="left"><select id="degree" name="degree" style="width:177px">
							<option value="小学" selected="true">小学</option>
							<option value="初中">初中</option>
							<option value="高中">高中</option>
							<option value="中职">中职</option>
							<option value="高职">高职</option>
							<option value="本科">本科</option>
							<option value="硕士">硕士</option>
							<option value="博士">博士</option>
							<option value="其他">其他</option>
					</select>
					</td>
						<td align="left">职称：</td>
						<td align="left"><input name="jobTitle" id="jobTitle" style="width:175px" />
					</td>
				</tr>

				<tr height="30px">
					<td align="left">所属单位:</td>
					<td align="left">
						<a id="stationInfo"><input id="stationId" name="stationId" style="width:177px"></a>
					</td>
					<td align="left">联系电话:</td>
					<td align="left"><input id="tel" name="tel" style="width:177px" class="easyui-validatebox" validType="numberOnly" data-options="required:true"/>
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

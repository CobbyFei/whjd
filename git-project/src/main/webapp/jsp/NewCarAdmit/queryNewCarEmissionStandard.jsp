<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新车排放标准查询</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jslib/letter.js"
	charset="utf-8"></script>
<script type="text/javascript">
	var flag = false;
	

	function reset_form() {
		$("#form_query_new_car_emmission_standard").form('reset');
	}
	
	function addInfo(vehMsg){
		var vehMsgJson = JSON.parse(vehMsg);
		var p = parent.sy
		.dialog({
			title : '补充车辆信息',
			href : '${pageContext.request.contextPath}/emissionStandardAction!addVehicleInfo.action',
			width : 900,
			height : 500,
			resizable : true,
			buttons : [ {
				text : '车辆信息录入完毕',
				iconCls : 'icon-edit',
				handler : function() {
					var f = p.find('form');
					f
							.form(
									'submit',
									{
										url : '${pageContext.request.contextPath}/detectionCommisionSheetAction!addNewCarAdmitInfo.action',
										success : function(data) {
											var json = $.parseJSON(data);
											if (json.success) {
												//$.messager.alert('提示',json.msg,'info');
												parent.sy.messagerShow({
																	msg : json.msg,
																	title : '提示'
																});
												p.dialog('close');
												reset_form;
											} else {
												parent.sy.messagerShow({
													msg : json.msg,
													title : '出错'
												});
												p.dialog('close');
											}
										}
									});
				}
			} ],

			onLoad : function() {
				var f = p.find('form');
				f
						.form(
								'load',
								{
									vehicleModelCode : vehMsgJson.CLXH,
									engineModel : vehMsgJson.FDJXH,
									maxTotalQuality : vehMsgJson.maxQuality,
									fuelType : vehMsgJson.fuelType,
									vehicleRegisterDate : vehMsgJson.FILENAME,
									emissionStandard : vehMsgJson.PF,
									emissionAdmitStandard : vehMsgJson.ZRBZ,
									vechileIssueCertTime : vehMsgJson.vechileIssueCertTime
								});
			}
		});
	}

	function add() {
		$('#form_query_new_car_emmission_standard').form('submit', {
			url : sy.bp() + '/emissionStandardAction!judgeAdmit.action',
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
					addInfo(json.obj);
				} else {
					$.messager.alert('提示',json.msg,'error');
				}
			}
		});

	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false,title:'查询车辆环保标准'"
		style="overflow: hidden;" align="left">
		<form id="form_query_new_car_emmission_standard">
			<table style="width: 700px; height: 400px; padding-top: 10px"
				cellspacing="5">
				<tr height="30px">
					<td align="right" style="width: 20%">车辆型号:</td>
					<td align="left" style="width: 30%"><input name="CLXH"
						id="CLXH" class="easyui-validatebox" data-options="required:true"
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
					<td align="right" style="width: 15%">车辆生产日期:</td>
					<td align="left" style="width: 25%"><input name="FILENAME"
						id="FILENAME" class="easyui-datebox"
						data-options="required:true,editable:false" style="width: 175px" /></td>
				</tr>
				<tr height="30px">
					<td align="right" style="width: 15%">燃油类型:</td>
					<td align="left" style="width: 25%"><select name="fuelType"
						id="fuelType" class="easyui-combobox" editable="false">
							<option value="汽油" selected>汽油</option>
							<option value="柴油">柴油</option>
							<option value="天然气">天然气</option>
							<option value="混合动力">混合动力</option>
							<option value="其他">其他</option>
					</select></td>
				</tr>
				<tr height="30px">
					<td align="right" style="width: 20%">总质量(单位Kg):</td>
					<td align="left" style="width: 30%"><input name="maxQuality"
						id="maxQuality" class="easyui-numberbox"
						data-options="required:true,precision:2,min:0"
						style="width: 175px" /></td>
				</tr>
				<tr height="30px">
					<td align="right" style="width: 20%">用途:</td>
					<td align="left" style="width: 25%"><select name="application"
						id="application" class="easyui-combobox" editable="false">
							<option value="其他" selected>其他</option>
							<option value="客车">客车</option>
							<option value="公交">公交</option>
							<option value="环卫">环卫</option>
							<option value="邮政">邮政</option>
					</select></td>
				</tr>

				<tr height="30px">
					<td colspan="2" align="center" style="width: 100%"><a
						class="easyui-linkbutton" icon="icon-add" name="add"
						href="javascript:void(0)" onclick="add()">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
						class="easyui-linkbutton" icon="icon-cancel" name="reset"
						href="javascript:void(0)" onclick="reset_form()">重置</a></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
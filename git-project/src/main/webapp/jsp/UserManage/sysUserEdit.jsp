<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	$(function(){
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
	
			
		$("#sex").combobox({
			required:true,
			panelHeight:40
		});
		
		$("#certificateName").validatebox({
			required:true
		});
		
		$("#degree").combobox({
			required:true,
			editable:false
		});
		
		$("#strCertificateTime").datebox({
			editable:false,
			required:true
		});
				
	});
</script>

<div style="padding: 5px;">

	<form id="editSysUserinfo" method="post" enctype="multipart/form-data">

		<input name="userId" type="hidden" />

		<table style="width:700px; height:400px; padding-top:10px" cellspacing="5">

				<tr height="30px">
					<td align="left" style="width：20%">姓名：</td>
					<td align="left" style="width：30%"><input name="userName" id="userName" class="easyui-validatebox"  data-options="required:true" style="width:175px" />
					</td>
					
					<td align="left">所属单位:</td>
					<td align="left">
						<a id="stationInfo"><input id="stationId" name="stationId" style="width:177px"></a>
					</td>
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
					<td align="left"><input id="certificateName" name="certificateName" style="width:177px" />
					</td>
					<td align="left">取得证书日期：</td>
					<td align="left"><input type="text" name="strCertificateTime" id="strCertificateTime" style="width:175px" />
					</td>
				</tr>

				<tr height="30px">
					<td align="left">学历：</td>
					<td align="left"><select id="degree" name="degree"  style="width:177px">
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
						<td align="left"><input name="jobTitle" id="jobTitle" style="width:175px"/>
					</td>
				</tr>

				<tr height="30px">
					
					<td align="left">联系电话:</td>
					<td align="left"><input id="tel" name="tel" style="width:177px" class="easyui-validatebox" validType="numberOnly" data-options="required:true"/>
					</td>
				</tr>

			</table>

	</form>
</div>
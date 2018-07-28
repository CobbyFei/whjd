<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false,title:'修改检测站信息'" style="overflow: hidden;" align="left">
		<form id="modifyInspectionStationInfo">
			<table style="width:700px; height:400px; padding-top:10px" cellspacing="5">
				<input name="stationId" type="hidden"/>
				<tr height="30px">
					<td align="right" style="width：20%">标志核发点名称：</td>
					<td align="left" style="width：30%"><input name="stationName" id="stationName" style="width:175px" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入检测站名称'" readonly/>
					<span id="stationNameCheck" name="stationNameCheck"></span>
					</td>
					<td align="right" style="width：20%">核发点地址：</td>
					<td align="left" style="width：30%"><input name="stationAddress" id="stationAddress" style="width:175px" maxlength="40"/></td>
				</tr>
				<tr height="30px">
				    <td align="right">资质种类：</td>
					<td align="left">
					<select name="qulificationType" id="qulificationType" class="easyui-combobox" data-options="required:true" style="width:175px">
					  <option value ="A">A</option>
                      <option value ="B">B</option>
					</select>
					</td>
					<td align="right">资质取得时间：</td>
					<td align="left"><input type="text" name="qulificationTime" id="qulificationTime" class="easyui-datebox"  style="width:175px" required="true" editable="false"/></td>
				</tr>
				<tr height="30px">
				<td align="right">负责人：</td>
				<td align="left">
				<input name="directorId" id="directorId" style="width:175px" />
				</td>
					<td align="right">注册时间：</td>
					<td align="left"><input type="text" id="registrationTime" name="registrationTime" class="easyui-datebox" style="width:175px" required="true" editable="false" /></td>

				</tr>
				<tr height="30px">
					<td align="right">有效期：</td>
					<td align="left">
					<input type="text" name="validPeriod" id="validPeriod" class="easyui-datebox" style="width:175px" required="true" editable="false"/>
					</td>
					<td align="right">状态：</td>
					<td align="left">
					<select name="status" id="status" class="easyui-combobox" data-options="required:true" style="width:175px">
					  <option value ="正常">正常</option>
                      <option value ="注销">注销</option>
					</select>
					</td>
				</tr>
				<tr height="30px">
					<td align="right">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					<td align="left" colspan="3">
					<textarea style="height:60px;font-size:12px" id="remarks" name="remarks" cols="47" rows="2" maxlength="40"></textarea></td>

				</tr>

			</table>
		</form>
	</div>
</body>
</html>

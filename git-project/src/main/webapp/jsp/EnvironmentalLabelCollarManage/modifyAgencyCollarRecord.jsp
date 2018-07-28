<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false,title:'修改环保标志领用记录'" style="overflow: hidden;" align="left">
		<form id="addEnvironmentalLabelCollarInfo">
			<table style="width:700px; height:400px; padding-top:10px" cellspacing="5">
				<input name="id" type="hidden"/>
				<input id="userId1" name="userId1" type="hidden" value="3"/>
				<tr height="30px">
					<td align="right" style="width：20%">领用人：</td>
					<td align="left" style="width：30%"><input name="userId" id="userId"  style="width:175px" />
					</td>
				</tr>
				<tr height="30px">
					<td align="right">领用时间：</td>
					<td align="left"><input type="text" name="collarTime" id="collarTime" class="easyui-datebox"  style="width:175px" required="true" editable="false"/></td>
				</tr>
				<tr height="30px">
					<td align="right">标志年度号：</td>
					<td align="left"><input name="year" id="year" class="easyui-numberbox" data-options="min:0" style="width:175px" required="true"/></td>
				</tr>
				<tr height="30px">
					<td align="right">绿标数量：</td>
					<td align="left"><input name="greenLabelNum" id="greenLabelNum" class="easyui-numberbox" data-options="value:0,min:0,groupSeparator:','" style="width:175px" required="true"/></td>
				</tr>
				<tr height="30px">
				    <td align="right">黄标数量：</td>
					<td align="left"><input name="yellowLabelNum" id="yellowLabelNum" class="easyui-numberbox" data-options="value:0,min:0,groupSeparator:','" style="width:175px" required="true"/></td>
				</tr>
				<tr height="30px">
				    <td align="right">状态：</td>
					<td align="left">
					    <input type="radio" name="status" value="0" checked/>正常&nbsp;&nbsp;&nbsp;<input type="radio" name="status" value="1"/>注销
					</td>
				</tr>
				<tr height="30px">
					<td align="right">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					<td align="left" colspan="3">
					<textarea style="height:60px;width:175px;font-size:12px" id="remarks" name="remarks"  rows="2" maxlength="500"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

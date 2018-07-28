<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false,title:'修改环保标志领用记录'" style="overflow: hidden;" align="left">
		<form id="addEnvironmentalLabelCollarInfo">
			<table style="width:700px; height:400px; padding-top:10px" cellspacing="5">
				<input name="id" type="hidden"/>
				<tr height="30px">
					<td align="right" style="width：20%">检测站名称：</td>
					<td align="left" style="width：30%"><input name="stationId" id="stationId" style="width:175px" readonly/>
					</td>
					<td align="right" style="width：20%">领用人：</td>
					<td align="left" style="width：30%"><input name="userId" id="userId" style="width:175px" readonly/>
					</td>
				</tr>
				<tr height="30px">
					<td align="right">领用时间：</td>
					<td align="left"><input type="text" name="collarTime" id="collarTime" class="easyui-datebox"  style="width:175px" required="true" editable="false"/></td>
				</tr>
				<tr height="30px">
					<td align="right">标志年度号：</td>
					<td align="left"><input name="year" id="year" class="easyui-numberbox" data-options="value:2014,min:0" style="width:153px" required="true"/></td>
					<td align="right">状态：</td>
					<td align="left">
					<select name="status" id="status" class="easyui-combobox" data-options="required:true,editable:false" style="width:175px">
					  <option value ="正常">正常</option>
                      <option value ="注销">注销</option>
					</select>
					</td>
				</tr>
				<tr height="30px">
					<td align="right">绿标数量：</td>
					<td align="left"><input name="greenLabelNum" id="greenLabelNum" class="easyui-numberbox" data-options="value:0,min:0,groupSeparator:','" style="width:153px" required="true" readOnly/></td>
					<td align="right">黄标数量：</td>
					<td align="left"><input name="yellowLabelNum" id="yellowLabelNum" class="easyui-numberbox" data-options="value:0,min:0,groupSeparator:','" style="width:153px" required="true" readOnly/></td>
				</tr>
				<tr height="30px">
					<td align="right">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					<td align="left" colspan="3">
					<textarea style="height:60px;font-size:12px" id="remarks" name="remarks" cols="47" rows="2" maxlength="500"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

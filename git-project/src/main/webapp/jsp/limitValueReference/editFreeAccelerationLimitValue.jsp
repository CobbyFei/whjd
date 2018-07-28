<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div data-options="region:'center',border:false,title:'修改自由加速法限值信息'"
	style="overflow: hidden;" align="left">
	<form id="frm_ZJ_modify">
		<input name="id" id="Id" type="hidden" />
		<table id="ZJ_time" style="padding-top:10px" cellspacing="5">
			<tr>
				<td align="right">生产时间自</td>
				<td align="left"><input name="zJMinRegisterTime"
					id="ZJMinRegisterTime" style="width:175px" class="easyui-datebox"
					data-options="required:true,editable:false" /></td>
				<td align="center">起，在</td>
				<td align="left"><input name="zJMaxRegisterTime"
					id="ZJMaxRegisterTime" style="width:175px" class="easyui-datebox"
					data-options="required:true,editable:false" /></td>
				<td align="left">之前的在用汽车</td>
			</tr>
			<tr>
				<td align="right">进气方式：</td>
				<td align="left"><select name="airInletMode" id="AirInletMode"
					style="width:175px">
						<option value="自然吸气">自然吸气</option>
						<option value="涡轮增压">涡轮增压</option>
				</select>
				</td>
				<td align="right">限值：</td>
				<td align="left"><input name="zjLimit" id="ZJ_Limit"
					style="width:175px" class="easyui-numberbox"
					data-options="value : 0,min : 0,precision : 2,groupSeparator : ',',required : true" />
				</td>
			</tr>
		</table>
	</form>
</div>
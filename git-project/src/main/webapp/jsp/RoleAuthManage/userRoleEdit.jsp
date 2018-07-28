<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;">

	<form id="privilegeForm" method="post" enctype="multipart/form-data">

		<input name="id" type="hidden" />
		<input name="userId" type="hidden" />
		<table style="padding-top:10px" cellspacing="5">
			<tr height="15px">
				<td align="left" style="width：15%">系统用户名：</td>
				<td align="left" style="width：40%"><input name="userName" id="userName" style="width:175px" readonly="true"/>
				</td>
			</tr>
			<tr height="15px">
				<td align="left" style="width：15%">授予角色：</td>
				<td align="left" style="width：30%"><input name="roleId" id="roleName" style="width:175px"></td>
			</tr>

			<tr height="15px">
				<td align="left" style="width：20%">状态：</td>
				<td align="left" style="width：30%"><select name="relationStatus" id="relationStatus" style="width:175px">
						<option value="0">注销</option>
						<option value="1" selected="true">正常</option>
				</select></td>
			</tr>
		</table>

	</form>
</div>
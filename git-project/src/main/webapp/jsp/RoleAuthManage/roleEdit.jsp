<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding: 5px;">

	<form id="form" method="post" enctype="multipart/form-data">

		<input name="roleId" type="hidden" />
		<input name="myRoleName" type="hidden" />
		<table style="padding-top:10px" cellspacing="5">
			<tr height="30px">
				<td align="left" style="width：15%">角色名：</td>
				<td align="left" style="width：40%"><input name="roleName" id="roleName" style="width:175px" /><span
					id="nameCheck" name="nameCheck" style="width:60px"></span>
				</td>
				<td align="left" style="width：15%">描述：</td>
				<td align="left" style="width：30%"><input name="roleDescription" id="roleDescription" style="width:175px">
				</td>

			</tr>
			<tr height="20px">
				<td align="left" style="width：20%">状态：</td>

				<td align="left" style="width：30%"><select name="roleStatus" id="roleStatus" style="width:175px">
						<option value="0">注销</option>
						<option value="1" selected="true">正常</option>
				</select>
				</td>

			</tr>
		</table>

	</form>
</div>
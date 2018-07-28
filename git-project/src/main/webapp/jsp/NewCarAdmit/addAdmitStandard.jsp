<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增准入条件库</title>
</head>
<body>
<div style="padding: 5px;">

	<form id="editAdmitStandard" method="post" enctype="multipart/form-data">

		<input name="userId" type="hidden" />

		<table style="width:700px; height:400px; padding-top:10px" cellspacing="5">

				<tr height="30px">
					<td align="left" style="width：20%">车辆类别：</td>
					<td align="left"><select id="CLLB" name="CLLB"  style="width:177px">
							<option value="轻型汽油车">轻型汽油车</option>
							<option value="轻型柴油车">轻型柴油车</option>
							<option value="重型汽油车">重型汽油车</option>
							<option value="重型柴油车">重型柴油车</option>
							<option value="天然气车">天然气车</option>
							<option value="混合动力车">混合动力车</option>
							<option value="摩托车">摩托车</option>  
							<!-- <option value="摩托车">摩托车</option> -->
					</select>
					</td>
					
					<td align="left">用途:</td>
					<td align="left"><select id="YT" name="YT"  style="width:177px">
							<option value="不论何种用途">不论何种用途</option>
							<option value="客车">客车</option>
							<option value="公交">公交</option>
							<option value="环卫">环卫</option>
							<option value="邮政">邮政</option>
							<option value="其他">其他</option>
							
					</select>
					</td>
				</tr>

				<tr height="30px">

					<td align="left">准入标准：</td>
					 <td align="left" style="width:25%">
	                    <select name="ZRBZ" id="ZRBZ" class="easyui-combobox"  editable="false">
	                        <option value="1">国1</option>
	                        <option value="2">国2</option>
	                        <option value="3">国3</option>
	                        <option value="4">国4</option>
	                        <option value="5">国5</option>
	                        <option value="6">国6</option>
	                    </select>
	                </td>
					<td align="left" style="width：20%">实施时间：</td>

					<td align="left" style="width:25%">
	                   <input name="SSSJ" id="SSSJ" class="easyui-datebox" data-options="required:true" style="width:175px"/>
	                </td>
				</tr>
					<input name="ID" id="ID" hidden/>
				<tr>
					
				
				</tr>
				
				
			</table>

	</form>
</div>
</body>
</html>
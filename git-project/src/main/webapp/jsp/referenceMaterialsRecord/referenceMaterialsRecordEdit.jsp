<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	$(function() {
		$("#MaterialName").validatebox({
			required : true
		});
		$("#Manufacturer").validatebox({
			required : true
		});
		$("#Specification").validatebox({
			required : true
		});
		$("#PurchaseNum").numberbox({
			value : 0,
			min : 0,
			groupSeparator : ',',
			required : true
		});
		$("#PurchaseTime").datebox({
			required : true,
			editable : false
		});
	});
</script>
<div style="padding:5px">
	<form id="referenceMaterialsRecordForm" method="post"
		enctype="multipart/form-data">
		<input name="recordId" type="hidden" /> <input name="stationName"
			id="StationName" type="hidden" /><input name="status" id="Status"
			type="hidden" />
		<table style="padding-top:10px" cellspacing="5">
			<tr>
				<td align="right">标准物质名称：</td>
				<td align="left"><input name="materialName" id="MaterialName"
					style="width:175px" /></td>
				<td align="right">制造厂商：</td>
				<td align="left"><input name="manufacturer" id="Manufacturer"
					style="width:175px" /></td>
				<td align="right">型号规格：</td>
				<td align="left"><input name="specification" id="Specification"
					style="width:153px" /></td>
			</tr>
			<tr>
				<td align="right">采购数量：</td>
				<td align="left"><input name="purchaseNum" id="PurchaseNum"
					style="width:175px" /></td>
				<td align="right">采购时间：</td>
				<td align="left"><input type="text" id="PurchaseTime"
					name="strPurchaseTime" style="width:177px" /></td>
			</tr>
			<tr>
				<td align="right">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
				<td align="left" colspan="5"><textarea
						style="height:60px;font-size:12px" id="Remarks" name="remarks"
						cols="47" rows="2" maxlength="500"></textarea></td>
			</tr>
		</table>
	</form>
</div>
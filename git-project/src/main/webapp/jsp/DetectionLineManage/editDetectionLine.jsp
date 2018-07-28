<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function(){
	 var stationId='${sessionScope.stationId}';
	      if(stationId!=0)
	      {
	         //此时表示不在市局
	         $("#maxDetectionNum").numberbox({
	             disabled:true
	         });
	         
	      }
		      
	});
	
	$("#localeId").bind("keyup", function() {
		$.ajax({
			url : sy.bp() + '/detectionLineAction!hasLocaleId.action',
			dataType : 'json',
			data : {
				stationId : $("#stationId").val(),
				q : $(this).val(),
				lineId : $("#lineId").val()
			},
			success : function(data) {
				if (data.success) {
					$("#localeIdCheck").html("<font color='red'><b>站内编号不可用</b><font>");
				} else {
					$("#localeIdCheck").html("<font color='red'><b>站内编号可用</b><font>");
				}
			}
		});
	});
	
/* 	$("#stationName").bind("keyup", function() {
		$.ajax({
			url : sy.bp() + '/detectionLineAction!hasLineName.action',
			dataType : 'json',
			data : {
				stationId : $("#stationId").val(),
				q : $(this).val(),
				lineId : $("#lineId").val()
			},
			success : function(data) {
				if (data.success) {
					$("#lineNameCheck").html("<font color='red'><b>名称不可用</b><font>");
				} else {
					$("#lineNameCheck").html("<font color='red'><b>名称可用</b><font>");
				}
			}
		});
	}); */
</script>

<div style="padding:5px" align="left">
	<form id="detectionLineEdit">
		<input id="lineId" name="lineId" type="hidden" value="1" /> <input id="stationId" name="stationId" type="hidden"
			value="2" />
		<table style="align:left;padding-top:10px" cellspacing="5px">
			<tr height="30px">
				<td align="right" style="width:20%">检测站名称:</td>
				<td align="left" style="width:30%;"><input name="stationName" id="stationName" class="easyui-validatebox"
					data-options="required:true,missingMessage:'请输入检测站'" readOnly="true" style="width:175px" />
				</td>
			</tr>
			<tr height="30px">
				<td align="right" style="width:20%">检测线名称:</td>
				<td align="left" style="width:40%"><input name="lineName" id="lineName" class="easyui-validatebox"
					data-options="required:true,missingMessage:'请输入检测线名称'" style="width:175px" maxLength='20' /><span id="lineNameCheck"
					name="lineNameCheck"></span>
				</td>
			</tr>
			<tr height="30px">
				<td align="right" style="width:20%">站内编号:</td>
				<td align="left" style="width:30%"><input name="localeId" id="localeId" class="easyui-numberbox"
					data-options="required:true,missingMessage:'请输入检测线站内编号',min:0" style="width:175px" /><span id="localeIdCheck"
					name="localeIdCheck"></span>
				</td>
			</tr>
			<tr height="30px">
				<td align="right" style="width:20%">最大检测量:</td>
				<td align="left" style="width:30%"><input name="maxDetectionNum" id="maxDetectionNum" class="easyui-numberbox"
					data-options="required:true,missingMessage:'请输入最大检测量',min:0" style="width:175px" />
				</td>
			</tr>
			<tr height="30px">
				<td align="right" style="width:20%">状态:</td>
				<td align="left" style="width:30%"><input type="radio" name="lineStatus" value="0" checked />正常&nbsp;<input
					type="radio" name="lineStatus" value="1" />注销</td>
			</tr>

		</table>
	</form>
</div>


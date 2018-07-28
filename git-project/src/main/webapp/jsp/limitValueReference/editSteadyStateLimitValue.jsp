<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" charset="utf-8">
	$(function(){

				
	});
	
	
</script>

<div data-options="region:'center',border:false,title:'修改限值'"
	style="overflow: hidden;" align="left"s>

	<form id="frm_WT_modify" method="post" enctype="multipart/form-data">

		<input name="id" type="hidden" />

		<table style="width:900px; height:400px; padding-top:10px" cellspacing="5">
				<tr>
					<td align="right" style="width：20%">生产时间自</td>
					<td align="left" style="width：30%"><input
						name="wTMinRegisterTime" id="WTMinRegisterTime"
						style="width:125px" class="easyui-datebox"/></td>
					<td align="center" style="width：20%">（包含）起，  在</td>
					<td align="left" style="width：30%"><input
						name="wTMaxRegisterTime" id="WTMaxRegisterTime"
						style="width:125px" class="easyui-datebox"/></td>
					<td align="left" style="width：20%">（不含）之前生产的汽车</td>
					
				</tr>
				<tr>
					<td align="right" style="width：20%">车辆分类：</td>
					<td align="left" style="width：30%"><select name="vehicleDetailType"
						id="VehicleDetailType" style="width:125px">
							<option value="1">第一类轻型汽车</option>
							<option value="2">第二类轻型汽车</option>
					</select></td>
				</tr>
				<tr>
					<td align="right" style="width：20%">基准质量自</td>
					<td align="left" style="width：30%"><input
						name="wTMinBaseQuality" id="WTMinBaseQuality"
						style="width:125px" class="easyui-numberbox" 
						data-options="min:0, value:0,  precision:2, groupSeparator : ',', required : true"/></td>
					<td align="center" style="width：20%">（不含）起，到</td>
					<td align="left" style="width：30%"><input
						name="wTMaxBaseQuality" id="WTMaxBaseQuality"
						style="width:125px" class="easyui-numberbox" 
						data-options="min:0, value:0,  precision:2, groupSeparator : ',', required : true"/></td>
					<td align="left" style="width：20%">（包含）止</td>
				</tr>
				<tr>
					
					<td align="right" style="width：20%">HC ASM5025：</td>
					<td align="left" style="width：30%"><input name="wtHcAsm5025"
						id="WT_HC_ASM5025" style="width:125px" class="easyui-numberbox" 
						data-options="min:0, value:0,  precision:2, groupSeparator : ',', required : true"/></td>
						
					<td align="right" style="width：20%">CO ASM5025：</td>
					<td align="left" style="width：30%"><input name="wtCoAsm5025"
						id="WT_CO_ASM5025" style="width:125px" class="easyui-numberbox" 
						data-options="min:0, value:0,  precision:2, groupSeparator : ',', required : true"/></td>
						
					<td align="right" style="width：20%">NO ASM5025：</td>
					<td align="left" style="width：30%"><input name="wtNoAsm5025"
						id="WT_NO_ASM5025" style="width:125px" class="easyui-numberbox" 
						data-options="min:0, value:0,  precision:2, groupSeparator : ',', required : true"/></td>
				</tr>
				<tr>
					
					<td align="right" style="width：10%">HC ASM2540：</td>
					<td align="left" style="width：24%"><input name="wtHcAsm2540"
						id="WT_HC_ASM2540" style="width:125px" class="easyui-numberbox" 
						data-options="min:0, value:0,  precision:2, groupSeparator : ',', required : true"/></td>
					<td align="right" style="width：10%">CO ASM2540：</td>
					<td align="left" style="width：23%"><input name="wtCoAsm2540"
						id="WT_CO_ASM2540" style="width:125px" class="easyui-numberbox" 
						data-options="min:0, value:0,  precision:2, groupSeparator : ',', required : true"/></td>
					<td align="right" style="width：10%">NO ASM2540：</td>
					<td align="left" style="width：23%"><input name="wtNoAsm2540"
						id="WT_NO_ASM2540" style="width:125px" class="easyui-numberbox" 
						data-options="min:0, value:0,  precision:2, groupSeparator : ',', required : true"/></td>
				</tr>
				
			</table>
	</form>
</div>
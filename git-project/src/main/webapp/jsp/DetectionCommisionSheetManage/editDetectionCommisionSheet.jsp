<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding:5px" align="left">
    <form id="detectionCommisionSheetEdit">
        <input type="hidden" name="id" value="-1"/>
        <table style="align:left;padding-top:10px" cellspacing="5px">
              <input type="hidden" name="yearCount" />
              <input type="hidden" name="conclusion" />
              <input type="hidden" name="requestStatus" />
              <tr height="30px">
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>检测站名称:</td>
	                <td align="left" style="width:25%">
	                   <input name="stationName" id="stationName" class="easyui-validatebox" readOnly="true" data-options="required:true" style="width:175px"/>
	                </td>
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>检测报告编号:</td>
	                <td align="left" style="width:25%">
	                   <input name="reportNumber" id="reportNumber" class="easyui-validatebox" data-options="required:true" readOnly="true"  style="width:175px"/>
	                </td>
	          </tr> 
	           <tr height="30px">
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>车牌号:</td>
	                <td align="left" style="width:25%">
	                   <input name="licence" id="licence" class="easyui-validatebox" data-options="required:true" readOnly="true" style="width:175px"/>
	                </td>
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>车辆识别码:</td>
	                <td align="left" style="width:25%">
	                   <input name="vin" id="vin" class="easyui-validatebox" data-options="required:true" readOnly="true"  style="width:175px"/>
	                </td>
	            </tr> 
	         <tr height="30px">
	                <td align="right" style="width:20%">车主姓名:</td>
	                <td align="left" style="width:25%">
	                   <input name="vehicleOwnerName" id="vehicleOwnerName" class="easyui-validatebox" data-options="required:true" maxLength='20' style="width:175px"/>
	                </td>
	                <td align="right" style="width:20%">车主住址:</td>
	                <td align="left" style="width:25%">
	                   <input name="vehicleOwnerAddress" id="vehicleOwnerAddress" class="easyui-validatebox" maxLength='50' style="width:175px"/>
	                </td>
	         </tr> 
	         <tr height="30px">
	         	<td align="right" style="width:20%">车主联系方式:</td>
	            <td align="left" style="width:25%">
	               <input name="phoneNum" id="phoneNum" class="easyui-validatebox" data-options="required:false" maxLength='20' style="width:175px"/>
	            </td>
	         </tr> 
	         <tr height="30px">
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>检测日期:</td>
	                <td align="left" style="width:25%">
	                   <input name="detectionTime" id="detectionTime" class="easyui-datetimebox" data-options="required:true,disabled:true"  style="width:175px"/>
	                </td>
	                <td align="right" style="width:20%">车辆初始登记日期:</td>
	                <td align="left" style="width:25%">
	                   <input name="vehicleRegisterDate" id="vehicleRegisterDate" class="easyui-datebox" data-options="required:true,editable:false" style="width:175px"/>
	                </td>
	          </tr>
	         <tr height="30px">
	                <td align="right" style="width:20%">车辆发证日期:</td>
	                <td align="left" style="width:25%">
	                   <input name="vechileIssueCertTime" id="vechileIssueCertTime" class="easyui-datebox" data-options="required:true,editable:false"  style="width:175px"/>
	                </td>
	                <td align="right" style="width:20%">有效日期:</td>
	                <td align="left" style="width:25%">
	                   <input name="validatePeriod" id="validatePeriod" class="easyui-datebox" data-options="required:true,editable:false" style="width:175px"/>
	                </td>
	          </tr>
	         <tr height="30px">
	                <td align="right" style="width:20%">最大载客数:</td>
	                <td align="left" style="width:25%">
	                   <input name="vehicleLoadNum" id="vehicleLoadNum" class="easyui-numberbox" data-options="required:true,min:1"  style="width:175px"/>
	                </td>
	                <td align="right" style="width:20%">车牌颜色:</td>
	                <td align="left" style="width:25%">
	                 <select name="licenseColor" id="licenseColor" class="easyui-combobox" editable="false" width="175px">
	                        <option value="蓝牌" selected="true">蓝牌</option>
	                        <option value="黄牌">黄牌</option>
	                        <option value="黑牌">黑牌</option>
	                        <option value="白牌">白牌</option>
	                    </select>
	                </td>
	          </tr>
	          <tr height="30px">
	                <td align="right" style="width:20%">排放量:</td>
	                <td align="left" style="width:25%">
	                   <input name="engineemissionAmount" id="engineemissionAmount" class="easyui-numberbox" data-options="required:true,precision:2,min:0" style="width:175px"/>
	                </td>
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>车辆来源:</td>
	                <td align="left" style="width:25%">
	                   <select name="vehicleType" id="vehicleType" class="easyui-combobox" width="317px" data-options="disabled:true">
	                        <option value="0" selected="true">新车</option>
	                        <option value="1">外地转入车</option>
	                        <option value="2">其他车</option>
	                    </select><span><font color="red">*</font></span>
	                </td>
	           </tr>
	             <tr height="30px">
	                <td align="right" style="width:20%">发动机型号:</td>
	                <td align="left" style="width:25%">
	                   <input name="engineModel" id="engineModel" class="easyui-validatebox" data-options="required:true" maxLength='20' style="width:175px"/>
	                </td>
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>发动机号:</td>
	                <td align="left" style="width:25%">
	                   <input name="engineCode" id="engineCode" class="easyui-validatebox" data-options="required:true" readOnly="true" style="width:175px"/>
	                </td>
	            </tr>  
	            <tr height="30px">
	                <td align="right" style="width:20%">车辆型号:</td>
	                <td align="left" style="width:25%">
	                   <input name="vehicleModelCode" id="vehicleModelCode" class="easyui-validatebox" data-options="required:true" style="width:175px"/>
	                </td>
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>燃油类型:</td>
	                <td align="left" style="width:25%">
	                    <input name="fuelType" id="fuelType" class="easyui-validatebox" data-options="required:true" readOnly="true" style="width:175px"/>
	                </td>
	            </tr> 
	            <tr height="30px">
	                <td align="right" style="width:20%">基准质量:</td>
	                <td align="left" style="width:40%">
	                   <input name="baseQuality" id="baseQuality" class="easyui-numberbox" data-options="required:true,precision:2,min:0" style="width:175px"/><span><font color='red'><b>（基准质量=整备质量+100公斤）</b></font></span>
	                </td>
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>最大总质量:(单位:Kg)</td>
	                <td align="left" style="width:25%">
	                    <input name="maxTotalQuality" id="maxTotalQuality" class="easyui-numberbox" data-options="required:true,precision:2,min:0" readOnly="true" style="width:175px"/>
	                </td>
	            </tr>  
	            <tr height="30px">
	                <td align="right" style="width:20%">累计行驶里程数:</td>
	                <td align="left" style="width:25%">
	                   <input name="totalMiles" id="totalMiles" class="easyui-numberbox" data-options="required:true,precision:2,min:0" style="width:175px"/>
	                </td>
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>车身长度:</td>
	                <td align="left" style="width:25%">
	                   <input name="vehicleLength" id="vehicleLength" class="easyui-numberbox" data-options="required:true,precision:2,min:0" readOnly="true" style="width:175px"/>
	                </td>
	            </tr> 
	             <tr height="30px">
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>排放标准:</td>
	                <td align="left" style="width:25%">
	                    <select name="emissionStandard" id="emissionStandard" class="easyui-combobox" data-options="disabled:true">
	                        <option value="1" selected="true">国1</option>
	                        <option value="2">国2</option>
	                        <option value="3">国3</option>
	                        <option value="4">国4</option>
	                        <option value="5">国5</option>
	                        <option value="6">国6</option>
	                    </select>
	                </td>
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>检测方法:</td>
	                <td align="left" style="width:40%">
	                   <input name="detectionMethod" id="detectionMethod" class="easyui-validatebox" data-options="required:true" readOnly="true" style="width:175px"/>
	                </td>
	            </tr> 
	             <tr height="30px">
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>检测类型:</td>
	                <td align="left" style="width:25%">
	                   	 <select name="labelDistributeType" id="labelDistributeType" class="easyui-combobox" data-options="disabled:true" >
	                        <option value="0" selected="true">首发</option>
	                        <option value="1">换发</option>
	                    </select>
	                </td>
	                <td align="right" style="width:20%">环保标志ID:</td>
	                <td align="left" colspan="3" style="width:80%">
	                     <input name="labelId" id="labelId" class="easyui-validatebox" readOnly="true" style="width:175px"/>
	                </td>
	            </tr> 
	             <tr height="30px">
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>车辆类别:</td>
	                <td align="left" style="width:25%">
	                   	 <select name="vehicleClass" id="vehicleClass" class="easyui-combobox" data-options="disabled:true">
	                        <option value="普通车">普通车</option>
	                        <option value="摩托车">摩托车</option>
	                        <option value="全时四驱车">全时四驱车</option>
	                        <option value="紧密型多驱动车">紧密型多驱动车</option>
	                        <option value="双层客车">双层客车</option>
	                        <option value="铰接客车">铰接客车</option>
	                        <option value="半挂牵引车">半挂牵引车</option>
	                        <option value="全挂牵引车">全挂牵引车</option>
	                        <option value="铰接列车">铰接列车</option>
	                        <option value="无法进行加载减速法的车辆">无法进行加载减速法的车辆</option>
	                    </select>
	                </td>
	                <td align="right" style="width:20%">错误原因:</td>
	                <td align="left" colspan="3" style="width:80%">
	                     <input name="errorReason" id="errorReason" class="easyui-validatebox" style="width:175px"/>
	                </td>
	            </tr> 
	             <tr height="30px">
	                <td align="right" style="width:20%">委托单状态:</td>
	                <td align="left" colspan="3" style="width:80%">
	                   	 <input type="radio" name="commisionSheetStatus" value="1" checked/>未检测&nbsp;&nbsp;<input type="radio" name="commisionSheetStatus" value="2"/>已检测&nbsp;&nbsp;<input type="radio" name="commisionSheetStatus" value="3" />已完成&nbsp;&nbsp;<input type="radio" name="commisionSheetStatus" value="0"/>已注销
	                </td>
	                
	                
	            </tr> 
        </table>
    </form>
</div>



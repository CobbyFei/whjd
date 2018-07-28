<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="padding:5px" align="left">
   <form id="detectionLineCalibrationEdit">
       <input id="stationId" name="stationId" type="hidden" value="1" />
       <input id="recordId" name="recordId" type="hidden" value="0"/>
       <input id="lineId" name="lineId" type="hidden" value="2"/>
       <input id="userId1" name="userId1" type="hidden" value="3"/>
       <table style="align:left;padding-top:10px" cellspacing="5px">
           <tr height="30px">
               <td align="right" style="width:20%">检测站名称:</td>
               <td align="left" style="width:30%;">
               <input name="stationName" id="stationName" class="easyui-validatebox"
					data-options="required:true,missingMessage:'请输入检测站'" readOnly="true" style="width:175px" />
				</td>
           </tr>
           <tr height="30px">
				<td align="right" style="width:20%">检测线名称:</td>
				<td align="left" style="width:40%">
				<input name="lineName" id="lineName" class="easyui-validatebox"
					data-options="required:true,missingMessage:'请输入检测线名称'" readOnly="true" style="width:175px" />
				</td>
			</tr>
			<tr height="30px">
				<td align="right" style="width:20%">标定人:</td>
				<td align="left" style="width:40%">
				<input name="userId" id="userId"
					data-options="required:true,missingMessage:'请输入标定人'" style="width:175px" />
				</td>
			</tr>
			<tr height="30px">
				<td align="right" style="width:20%">标定时间:</td>
				<td align="left" style="width:40%">
				<input name="calibrationTimePage" id="calibrationTimePage" class="easyui-datetimebox"
					data-options="required:true,missingMessage:'请输入标定时间',editable:false"  readOnly="true" style="width:175px" />
				</td>
			</tr>
			<tr height="30px">
                   <td align="right" style="width:20%">氮氧化物标准值:</td>
                   <td align="left" style="width:40%">
                       <input name="nostandardValue" id="nostandardValue" class="easyui-numberbox" data-options="required:true,precision:2"  style="width:175px"/>
                   </td>
                </tr>
                <tr height="30px">
                   <td align="right" style="width:20%">氮氧化物标定后值:</td>
                   <td align="left" style="width:40%">
                       <input name="noafterValue" id="noafterValue" class="easyui-numberbox" data-options="required:true,precision:2" style="width:175px"/>
                   </td>
                </tr>
               <tr height="30px">
                   <td align="right" style="width:20%">碳氢化合物标准值:</td>
                   <td align="left" style="width:40%">
                       <input name="hcstandardValue" id="hcstandardValue" class="easyui-numberbox" data-options="required:true,precision:2"  style="width:175px"/>
                   </td>
                </tr>
                <tr height="30px">
                   <td align="right" style="width:20%">碳氢化合物标定后值:</td>
                   <td align="left" style="width:40%">
                       <input name="hcafterValue" id="hcafterValue" class="easyui-numberbox" data-options="required:true,precision:2"   style="width:175px"/>
                   </td>
                </tr>
               <tr height="30px">
                   <td align="right" style="width:20%">一氧化碳标准值:</td>
                   <td align="left" style="width:40%">
                       <input name="costandardValue" id="costandardValue" class="easyui-numberbox" data-options="required:true,precision:2"  style="width:175px"/>
                   </td>
                </tr>
                <tr height="30px">
                   <td align="right" style="width:20%">一氧化碳标定后值:</td>
                   <td align="left" style="width:40%">
                       <input name="coafterValue" id="coafterValue" class="easyui-numberbox" data-options="required:true,precision:2"  style="width:175px"/>
                   </td>
                </tr>
               <tr height="30px">
                   <td align="right" style="width:20%">二氧化碳标准值:</td>
                   <td align="left" style="width:40%">
                       <input name="co2standardValue" id="co2standardValue" class="easyui-numberbox" data-options="required:true,precision:2"  style="width:175px"/>
                   </td>
                </tr>
                <tr height="30px">
                   <td align="right" style="width:20%">二氧化碳标定后值:</td>
                   <td align="left" style="width:40%">
                       <input name="co2afterValue" id="co2afterValue" class="easyui-numberbox" data-options="required:true,precision:2"  style="width:175px"/>
                   </td>
                </tr>
			<tr height="30px">
				<td align="right" style="width:20%">状态:</td>
				<td align="left" style="width:40%">
				   <input type="radio" name="status" value="0" checked/>正常 &nbsp;&nbsp;<input type="radio" name="status" value="1"/>注销
				</td>
			</tr>
       </table>
   </form>
</div>
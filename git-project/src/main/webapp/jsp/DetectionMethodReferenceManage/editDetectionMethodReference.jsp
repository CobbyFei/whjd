<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding:5px" align="left">
	<form id="detectionMethodEdit">
	    <input name="id" type="hidden" value="-1"/>
		<table style="align:left;padding-top:10px" cellspacing="5px">
			<tr height="30px">
                   <td align="right" style="width:20%">检测方法名称:</td>
                   <td align="left" style="width:30%">
	                  <input name="detectionMedhodName" id="detectionMedhodName" class="easyui-validatebox" data-options="required:true" maxLength='50' style="width:175px"/><span name="methodName_check" id="methodName_check"></span>
	               </td>
               </tr>
			 <tr height="30px">
                   <td align="right" style="width:20%">燃油类型:</td>
                   <td align="left" style="width:30%">
	                  <input name="fuelType" id="fuelType" class="easyui-validatebox" data-options="required:true" maxLength='50' style="width:175px"/>
	               </td>
              </tr>
			<tr height="30px">
                   <td align="right" style="width:20%">车身长度范围(最小值):</td>
                   <td align="left" style="width:30%">
	                  <input name="lengthMin" id="lengthMin" class="easyui-numberbox" data-options="required:true,precision:2,min:0" style="width:175px"/>
	               </td>
               </tr>
               <tr height="30px">
                   <td align="right" style="width:20%">车身长度范围(最大值):</td>
                   <td align="left" style="width:30%">
	                  <input name="lengthMax" id="lengthMax" class="easyui-numberbox" data-options="required:true,precision:2,min:0" style="width:175px"/>
	               </td>
               </tr>
               <tr height="30px">
                   <td align="right" style="width:20%">车身重量范围(最小值):</td>
                   <td align="left" style="width:30%">
	                  <input name="weightMin" id="weightMin" class="easyui-numberbox" data-options="required:true,precision:2,min:0" style="width:175px"/>
	               </td>
               </tr>
               <tr height="30px">
                   <td align="right" style="width:20%">车身重量范围(最大值):</td>
                   <td align="left" style="width:30%">
	                  <input name="weightMax" id="weightMax" class="easyui-numberbox" data-options="required:true,precision:2,min:0" style="width:175px"/>
	               </td>
               </tr>
		</table>
	</form>
</div>


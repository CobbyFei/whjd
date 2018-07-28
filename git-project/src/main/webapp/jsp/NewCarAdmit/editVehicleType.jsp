<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding:5px" align="left">
    <!--  <form id="detectionCommisionSheetEdit">
    -->
        <form id="vehicleTypeEdit">
        <!--  <input type="hidden" name="ID" value="-1"/>   -->
        <table style="align:left;padding-top:10px" cellspacing="5px">
              <!--  <input type="hidden" name="yearCount" />
              <input type="hidden" name="conclusion" />
              <input type="hidden" name="requestStatus" />  
              -->
              <tr height="30px">  
	                <td align="right" style="width:20%">车辆型号:</td>
	                <td align="left" style="width:25%">
	                   <input name="CLXH" id="CLXH" class="easyui-validatebox" data-options="required:true"   style="width:175px"/>
	                </td>
	                <td align="right" style="width:20%">车辆名称:</td>
	                <td align="left" style="width:25%">
	                   <input name="CLMC" id="CLMC" class="easyui-validatebox" data-options="required:true"  style="width:175px"/>
	                </td>
	          </tr> 
	           <tr height="30px">	           
	                <td align="right" style="width:20%">发动机型号:</td>
	                <td align="left" style="width:25%">
	                   <input name="FDJXH" id="FDJXH" class="easyui-validatebox" data-options="required:true"   style="width:175px"/>
	                </td>
	                <td align="right" style="width:20%">发动机生产厂:</td>
	                <td align="left" style="width:25%">
	                   <input name="FDJSCC" id="FDJSCC" class="easyui-validatebox" data-options="required:true" maxLength='50' style="width:175px"/>
	                </td>
	            </tr> 
	         <tr height="30px">	               
	                <td align="right" style="width:20%">制造商:</td>
	                <td align="left" style="width:25%">
	                   <input name="MANUF" id="MANUF" class="easyui-validatebox" data-options="required:true" maxLength='50' style="width:175px"/>
	                </td>
	                <td align="right" style="width:20%">车辆商标:</td>
	                <td align="left" style="width:25%">
	                   <input name="CLSB" id="CLSB" class="easyui-validatebox" data-options="required:true" maxLength='20' style="width:175px"/>
	                </td>
	         </tr> 
	        
	        
	             <tr height="30px">
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>排放标准:</td>
	                <td align="left" style="width:25%">
	                    <select name="PF" id="PF" style="width:175px" class="easyui-combobox" data-options="disabled:true">
	                        <option value="1" selected="true">国1</option>
	                        <option value="2">国2</option>
	                        <option value="3">国3</option>
	                        <option value="4">国4</option>
	                        <option value="5">国5</option>
	                        <option value="6">国6</option>
	                    </select>
	                </td>
	                
	                <td align="right" style="width:20%"><span><font color="red">*</font></span>车辆类别:</td>
	                <td align="left" style="width:25%">
	                    <select name="CLLB" id="CLLB" style="width:175px" class="easyui-combobox" data-options="disabled:true">
							<option value="轻型汽油车">轻型汽油车</option>
							<option value="轻型柴油车">轻型柴油车</option>
							<option value="重型汽油车">重型汽油车</option>
							<option value="重型柴油车">重型柴油车</option>
							<option value="天燃气车">天燃气车</option>
							<option value="混合动力车">混合动力车</option>
							<option value="摩托车">摩托车</option>
							<option value="其他">其他</option>
					   </select>
	                </td>
	            </tr> 
	            
	            <tr height="30px">	         	    
	                <td align="right" style="width:20%">核准日期:</td>
	                <td align="left" style="width:25%">
	                   <input name="FILENAME" id="FILENAME" class="easyui-datebox" data-options="required:true,editable:false" style="width:175px"/>
	                </td>
	            </tr> 
	            </tr>
					<input name="ID" id="ID" hidden/>
				<tr>           
        </table>
    </form>
</div>



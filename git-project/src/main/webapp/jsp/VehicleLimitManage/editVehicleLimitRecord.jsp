<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div style="padding:5px" align="left">
      <form id="vehicleLimitEditForm">
         <input name="id" type="hidden"/>
        <table  style="align:left;padding-top:10px" cellspacing="5">
            <tr height="30px">
                <td align="right" style="width:20%">车牌号:</td>
                <td align="left" style="width:30%">
                   <input name="licence" id="licence" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入车牌号'" readOnly="true"  style="width:175px"/>
                </td>
            </tr>
            <tr height="30px">
                <td align="right" style="width:15%">车牌颜色:</td>
                <td align="left" style="width:25%">
                   <select name="licenceColor" id="licenceColor" class="easyui-combobox" width="317px" editable="true">
                        <option value="蓝牌" selected="true">蓝牌</option>
                        <option value="黄牌">黄牌</option>
                        <option value="黑牌">黑牌</option>
                        <option value="白牌">白牌</option>
                    </select>
                </td>
            </tr> 
            <tr height="30px">
                <td align="right" style="width:20%">违规类别</td>
                <td align="left" style="width:20%">
                  <input type="radio" name="violationType" value="1" checked>违规限行&nbsp;<input type="radio" name="violationType" value="2">超标排放
                </td>
            </tr>
           <tr height="30px">
              <td align="right" style="width:20%">违规时间:</td>
              <td align="left" style="width:175px">
                <input id="violationTimePage" name="violationTimePage" class="easyui-datetimebox" style="width:175px" required="true" editable="false"/>
              </td>
           </tr>
           <tr height="30px">
                <td align="right" style="width:20%">违规地址:</td>
                <td align="left" style="width:30%">
                   <input name="violationAddress" id="violationAddress" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入违规地址'" maxLength='100' style="width:175px"/>
                </td>
           </tr>
           <tr height="30px">
               <td align="right" style="width:20%">是否已经接受处罚:</td>
               <td align="left" style="width:175px">
               <input type="radio" name="isPunishedPage" value="1">是&nbsp;<input type="radio" name="isPunishedPage" value="0" checked>否
               </td>
           </tr>
           <tr height="30px">
               <td align="right" style="width:20%">是否已经注销:</td>
               <td align="left" style="width:175px">
               <input type="radio" name="isCancelPage" value="1">是&nbsp;<input type="radio" name="isCancelPage" value="0" checked>否
               </td>
           </tr>
   
           <tr height="30px">
             <td  align="right" style="width:20%">备注:</td>
              <td align="left"style="width:175px">
                <textarea name="remarks"rows="4" cols="20" maxLength='100'></textarea>
              </td>
           </tr>

        </table>
      
      </form>

</div>
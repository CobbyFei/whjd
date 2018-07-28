<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="easyui-layout" data-options="fit:true">
     <div data-options="region:'center',border:false,title:'车辆信息补充'" style="" align="left">
        <form id="frm_add_detectionCommisionSheet">
            <table style="width:90%;height:100%;padding-top:10px" cellspacing="5">
                <tr height="30px">
	                
	                
	                <td align="right" style="width:15%">车架号:</td>
	                <td align="left" style="width:25%">
	                   <input name="vin" id="vin" class="easyui-validatebox" data-options="required:true" maxLength='30' style="width:175px"/>
	                </td>
	                
	                <td align="right" style="width:15%">车辆类别:</td>
	                <td align="left" style="width:30%">
	                   <select name="vehicleClass" id="vehicleClass" class="easyui-combobox" editable="false">
	                        <option value="普通车" selected>普通车</option>
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
	            </tr> 
               <tr height="30px">
                    
	                <td align="right" style="width:15%">车牌号:</td>
	                <td align="left" style="width:25%">
	                   <input name="licence" id="licence" class="easyui-validatebox" data-options="required:true" maxLength='20' style="width:175px"/>
	                </td>
	                
	                <td align="right" style="width:15%">车主姓名:</td>
	                <td align="left" style="width:25%">
	                   <input name="vehicleOwnerName" id="vehicleOwnerName" class="easyui-validatebox" data-options="required:true"  maxLength='20' style="width:175px"/>
	                </td>
	            </tr> 
	            <tr height="30px">
	                <td align="right" style="width:15%">车主联系方式:</td>
	                <td align="left" style="width:25%">
	                   <input name="phoneNum" id="phoneNum" class="easyui-validatebox" data-options="required:true"  maxLength='20' style="width:175px"/>
	                </td>
	                <td align="right" style="width:15%">车主住址:</td>
	                <td align="left" style="width:25%">
	                   <input name="vehicleOwnerAddress" id="vehicleOwnerAddress" class="easyui-validatebox"  maxLength='50' style="width:175px"/>
	                </td>
	            </tr> 
                   <tr height="30px">
	                <td align="right" style="width:15%">发动机型号:</td>
	                <td align="left" style="width:25%">
	                   <input name="engineModel" id="engineModel" class="easyui-validatebox" readOnly="true" data-options="required:true,disabled:true" maxLength='20' style="width:175px"/>
	                </td>
	                <td align="right" style="width:15%">发动机号:</td>
	                <td align="left" style="width:25%">
	                   <input name="engineCode" id="engineCode" class="easyui-validatebox" data-options="required:true" maxLength='20' style="width:175px"/>
	                </td>
	            </tr> 
               
               <tr height="30px">
               
               <td align="right" style="width:15%">车辆初始登记日期:</td>
	                <td align="left" style="width:25%">
	                   <input name="vehicleRegisterDate" id="vehicleRegisterDate" class="easyui-datebox" data-options="required:true,disabled:true" style="width:175px"/>
	                </td>
	                
	                
	                <td align="right" style="width:15%">车辆发证日期:</td>
	                <td align="left" style="width:25%">
	                   <input name="vechileIssueCertTime" id="vechileIssueCertTime" class="easyui-datebox" data-options="required:true,editable:false" style="width:175px"/>
	                </td>
	                
	            </tr> 
	            <tr height="30px">
	                <td align="right" style="width:15%">车牌颜色:</td>
	                <td align="left" style="width:25%">
	                   <select name="licenseColor" id="licenseColor" class="easyui-combobox" width="317px" editable="false">
	                        <option value="蓝牌" selected="true">蓝牌</option>
	                        <option value="黄牌">黄牌</option>
	                        <option value="黑牌">黑牌</option>
	                        <option value="白牌">白牌</option>
	                    </select>
	                </td>
	                <td align="right" style="width:15%">最大载客数:</td>
	                <td align="left" style="width:25%">
	                   <input name="vehicleLoadNum" id="vehicleLoadNum" class="easyui-numberbox" data-options="required:true,min:1"  style="width:175px"/>
	                </td>
	            </tr> 
               <tr height="30px">
	                <td align="right" style="width:15%">排放量:</td>
	                <td align="left" style="width:25%">
	                   <input name="engineemissionAmount" id="engineemissionAmount" class="easyui-numberbox" data-options="required:true,precision:2,min:0" style="width:175px"/>
	                </td>
	                 <td align="right" style="width:15%">车辆型号:</td>
	                <td align="left" style="width:25%">
	                   <input name="vehicleModelCode" id="vehicleModelCode" class="easyui-validatebox" readOnly="true" data-options="required:true,disabled:true" maxLength='40' style="width:175px"/>
	                </td>
	            </tr> 
                <tr height="30px">
	                
	                <td align="right" style="width:15%">排放标准:</td>
	                <td align="left" style="width:25%">
	                    <select name="emissionStandard" id="emissionStandard" class="easyui-combobox"  editable="false" data-options="required:true,disabled:true">
	                        <option value="1" selected="true">国1</option>
	                        <option value="2">国2</option>
	                        <option value="3">国3</option>
	                        <option value="4">国4</option>
	                        <option value="5">国5</option>
	                        <option value="6">国6</option>
	                    </select>
	                </td>
	                
	                <td align="right" style="width:15%">排放准入标准:</td>
	                <td align="left" style="width:25%">
	                    <select name="emissionAdmitStandard" id="emissionAdmitStandard" class="easyui-combobox"  editable="false" data-options="required:true,disabled:true">
	                        <option value="1" selected="true">国1</option>
	                        <option value="2">国2</option>
	                        <option value="3">国3</option>
	                        <option value="4">国4</option>
	                        <option value="5">国5</option>
	                        <option value="6">国6</option>
	                    </select>
	                </td>
	                
	                
	            </tr>
               <tr height="30px">
	                <td align="right" style="width:15%">基准质量:</td>
	                <td align="left" style="width:40%">
	                   <input name="baseQuality" id="baseQuality" class="easyui-numberbox" data-options="required:true,precision:2,min:0" style="width:175px"/><span><font color='red'><b>&nbsp;&nbsp;(基准质量=整备质量+100公斤)</b></font></span>
	                </td>
	                <td align="right" style="width:15%">累计行驶里程数:</td>
	                <td align="left" style="width:30%">
	                   <input name="totalMiles" id="totalMiles" value="0" class="easyui-numberbox" data-options="required:true,precision:2,min:0" style="width:175px"/>
	                </td>
	            </tr>
	            <tr height="30px">
	               <td align="right" style="width:15%">车辆来源:</td>
	                <td align="left" style="width:25%">
	                   <select name="vehicleType" id="vehicleType" class="easyui-combobox" width="317px" editable="false" data-options="required:true,disabled:true">
	                        <option value="0" >新车</option>
	                        <option value="1" selected="true">外地转入车</option>
	                    </select>
	                </td>
	                <td align="right" style="width:15%">燃油类型:</td>
	                <td align="left" style="width:25%">
	                    <!-- <select name="fuelType" id="fuelType" class="easyui-combobox" editable="false" data-options="required:true"> -->
	                    <select name="fuelType" id="fuelType" class="easyui-combobox" editable="false" data-options="required:true,disabled:true">
	                        <option value="无" selected>---请选择---</option>
	                        <option value="汽油">汽油</option>
	                        <option value="柴油">柴油</option>
	                        <option value="天然气">天然气</option>
	                        <option value="混合动力">混合动力</option>
	                    </select>
	                </td>
	            </tr> 
               <tr height="30px">
	                
	                <td align="right" style="width:15%">最大总质量(单位:Kg):</td>
	                <td align="left" style="width:30%">
	                   <input name="maxTotalQuality" id="maxTotalQuality" class="easyui-numberbox" data-options="required:true,precision:2,min:0" style="width:175px"/>
	                </td>
	                <td align="right" style="width:15%">车身长度:</td>
	                <td align="left" style="width:30%">
	                   <input name="vehicleLength" id="vehicleLength" class="easyui-numberbox" data-options="required:true,precision:2,min:0" style="width:175px"/>
	                </td>
	            </tr> 
            </table>
        </form>
     </div>

</body>
</html>
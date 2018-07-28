<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp" ></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加检测线标定记录</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/letter.js" charset="utf-8"></script>
<script type="text/javascript">
   $(function(){
        var date=new Date();
        var time=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
        $("#calibrationTimePage").datebox('setValue',time);
        
	    $("#lineId").combobox({
					url : sy.bp() + '/detectionLineAction!getDetectionLineName.action',
					mode : 'remote',
					delay : 500,
					valueField : 'lineId',
					required : true,
					textField : 'lineName'
				});
	    $("#userId").combobox({
			url : sy.bp() + '/manageUserAction!getSysUsers.action',
			mode : 'remote',
			delay : 500,
			valueField : 'userId',
			required : false,
			textField : 'userName',
		});
   });
   
   function add()
   {
       $.messager.confirm('请确定','确定要添加该条检测线标定记录？',function(r){
             if(r){
			      $("#frm_add_calibrationRecord").form('submit',{
			           url:sy.bp()+'/calibrationDetectionLineAction!addCalibrationRecord.action',
			           onSubmit:function(){
			                var result=check();
			                return $(this).form('validate')&&result;
			           },
			           success:function(data){
			                
			                var res=$.parseJSON(data);
			                if(res.success)
			                {
			                     $.messager.alert('提示','添加检测线标定记录成功','info');
			                     $("#nostandardValue").val("");
			                     $("#noafterValue").val("");
			                     $("#hcstandardValue").val("");
			                     $("#hcafterValue").val("");
			                     $("#costandardValue").val("");
			                     $("#coafterValue").val("");
			                     $("#co2standardValue").val("");
			                     $("#co2afterValue").val("");
			                }
			                else
			                {
			                     $.messager.alert('提示',res.msg,'info');
			                }
			           
			           }
			       });	
             }
       });
   }
   function check()
   {
       var nostandardvalue=parseFloat($("#nostandardValue").val());
       var noafetervalue=parseFloat($("#noafterValue").val());
       if(noafetervalue>nostandardvalue*1.05 || noafetervalue<nostandardvalue*0.95)
       {
           $.messager.alert('提示','氮氧化物标定后值和标准值的误差不能超过5%！请重新标定该项目','error');
           return false;
       }
       var hcstandardValue=parseFloat($("#hcstandardValue").val());
       var hcafterValue=parseFloat($("#hcafterValue").val());
       if(hcafterValue>hcstandardValue*1.05 || hcafterValue<hcstandardValue*0.95)
       {
           $.messager.alert('提示','碳氢化合物标定后值和标准值的误差不能超过5%！请重新标定该项目','error');
           return false;
       }
       var costandardValue=parseFloat($("#costandardValue").val());
       var coafterValue=parseFloat($("#coafterValue").val());
       if(coafterValue>costandardValue*1.05 || coafterValue<costandardValue*0.95)
       {
           $.messager.alert('提示','一氧化碳标定后值和标准值的误差不能超过5%！请重新标定该项目','error');
           return false;
       }
       var co2standardValue=parseFloat($("#co2standardValue").val());
       var co2afterValue=parseFloat($("#co2afterValue").val());
       if(co2afterValue>co2standardValue*1.05 || co2afterValue<co2standardValue*0.95)
       {
           $.messager.alert('提示','二氧化碳标定后值和标准值的误差不能超过5%！请重新标定该项目','error');
           return false;
       }
       return true;
   }
   function reset_form(){
      $("#frm_add_calibrationRecord").form('reset');
      var date=new Date();
      var time=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
      $("#calibrationTimePage").datebox('setValue',time);
   }
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false,title:'新增检测线记录信息'" style="overflow:hidden" align="left">
       <form id="frm_add_calibrationRecord">
           <table style="width:700px;height:400px;padding-top:10px" cellspacing="5">
                <tr height="30px">
                   <td align="right" style="width:20%">所属检测线:</td>
                   <td align="left" style="width:30%">
                       <input name="lineId" id="lineId" class="easyui-combobox" data-options="required:true" style="width:175px"/>
                   </td>
                </tr>
                <tr height="30px">
                   <td align="right" style="width:20%">标定人员:</td>
                   <td align="left" style="width:30%">
                       <input name="userId" id="userId" class="easyui-combobox" data-options="required:true" style="width:175px"/>
                   </td>
                </tr>
                <tr height="30px">
                   <td align="right" style="width:20%">标定时间:</td>
                   <td align="left" style="width:30%">
                       <input name="calibrationTimePage" id="calibrationTimePage" class="easyui-datetimebox" data-options="required:true,disabled:true" style="width:175px"/>
                   </td>
                </tr>
         
               <tr height="30px">
                   <td align="right" style="width:20%">氮氧化物标准值:</td>
                   <td align="left" style="width:30%">
                       <input name="nostandardValue" id="nostandardValue" class="easyui-numberbox" data-options="required:true,min:0,precision:2" style="width:175px"/>
                   </td>
                </tr>
                <tr height="30px">
                   <td align="right" style="width:20%">氮氧化物标定后值:</td>
                   <td align="left" style="width:30%">
                       <input name="noafterValue" id="noafterValue" class="easyui-numberbox" data-options="required:true,min:0,precision:2" style="width:175px"/>
                   </td>
                </tr>
               <tr height="30px">
                   <td align="right" style="width:20%">碳氢化合物标准值:</td>
                   <td align="left" style="width:30%">
                       <input name="hcstandardValue" id="hcstandardValue" class="easyui-numberbox" data-options="required:true,min:0,precision:2" style="width:175px"/>
                   </td>
                </tr>
                <tr height="30px">
                   <td align="right" style="width:20%">碳氢化合物标定后值:</td>
                   <td align="left" style="width:30%">
                       <input name="hcafterValue" id="hcafterValue" class="easyui-numberbox" data-options="required:true,min:0,precision:2" style="width:175px"/>
                   </td>
                </tr>
               <tr height="30px">
                   <td align="right" style="width:20%">一氧化碳标准值:</td>
                   <td align="left" style="width:30%">
                       <input name="costandardValue" id="costandardValue" class="easyui-numberbox" data-options="required:true,min:0,precision:2" style="width:175px"/>
                   </td>
                </tr>
                <tr height="30px">
                   <td align="right" style="width:20%">一氧化碳标定后值:</td>
                   <td align="left" style="width:30%">
                       <input name="coafterValue" id="coafterValue" class="easyui-numberbox" data-options="required:true,min:0,precision:2" style="width:175px"/>
                   </td>
                </tr>
               <tr height="30px">
                   <td align="right" style="width:20%">二氧化碳标准值:</td>
                   <td align="left" style="width:30%">
                       <input name="co2standardValue" id="co2standardValue" class="easyui-numberbox" data-options="required:true,min:0,precision:2" style="width:175px"/>
                   </td>
                </tr>
                <tr height="30px">
                   <td align="right" style="width:20%">二氧化碳标定后值:</td>
                   <td align="left" style="width:30%">
                       <input name="co2afterValue" id="co2afterValue" class="easyui-numberbox" data-options="required:true,min:0,precision:2" style="width:175px"/>
                   </td>
                </tr>
                <tr height="30px">
                   <td  colspan="2" align="center">
                        <a class="easyui-linkbutton" icon="icon-add" name="add" href="javascript:void(0)" onclick="add()">添加</a>&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" icon="icon-cancel" name="reset" href="javascript:void(0)" onclick="reset_form()">重置</a>
                   </td>
                </tr>
           </table>
       </form>
    </div>
</body>
</html>
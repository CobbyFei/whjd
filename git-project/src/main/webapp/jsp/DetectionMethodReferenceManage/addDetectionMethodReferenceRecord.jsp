<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加检测方法参照信息</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/letter.js" charset="utf-8"></script>
<script type="text/javascript">
    var flag=false;
	 $(function(){
		 $("#detectionMedhodName").bind("keyup",function(){
					   $.ajax({
							url : sy.bp() + '/detectionMethodReferenceAction!hasMethodName.action',
							dataType : 'json',
							data:{q:$(this).val()},
							success : function(data) {
								if (data.success) {
								     flag=false;
								     $("#methodName_check").html("<font color='red'><b>名称不可用</b><font>");
								} 
								else {
								   flag=true;
								  
								   $("#methodName_check").html("<font color='red'><b>名称可用</b><font>");
								}
							}	
						});
			         
			});
	 });
	 
	 function reset_form()
	 {
	     $("#frm_add_detectionMethodReference").form('reset');
	 }
	 
	 function add(){
	     $.messager.confirm('请确认','确定要添加检测方法参照信息？',function(r){
                if(r){
	                $("#frm_add_detectionMethodReference").form('submit',{
		                 url:sy.bp()+'/detectionMethodReferenceAction!addDetectionMethodReference.action',
		                 onSubmit:function(){
		                       if($("#detectionMedhodName").val().trim()!="双怠速法"&&$("#detectionMedhodName").val().trim()!="加载减速法"&&$("#detectionMedhodName").val().trim()!="自由加速法"&&$("#detectionMedhodName").val().trim()!="稳态工况法"&&$("#detectionMedhodName").val().trim()!="摩托车双怠速法")
		                       {
		                           $.messager.alert('提示','检测方法输入不正确,请看输入提示！','error');
		                           return false;
		                       }
		                       var fuelType=$("#fuelType").val().trim();
		                       if(fuelType!="汽油" && fuelType!="柴油" && fuelType!="天然气")
		                       {
		                           $.messager.alert('提示','燃油类型必须是：汽油、柴油、天然气中的一种，请重新输入！','error');
		                           return false;
		                       }
		                       var isValid=$(this).form("validate");
		                       if(parseFloat($("#lengthMax").val())<parseFloat($("#lengthMin").val()))
		                       {
		                           $.messager.alert('提示','车身最大长度不能小于最小长度','error');
		                           return false;
		                       }
		                       if(parseFloat($("#weightMax").val())<parseFloat($("#weightMin").val()))
		                       {
		                           $.messager.alert('提示','车身最大重量不能小于最小重量','error');
		                           return false;
		                       }
		                       if(flag && isValid)
		                            return true;
		                       return false;
		                 },
		                 success:function(data){
		                    var result=eval("("+data+")");
		                    if(result.success)
			                {
			                   $.messager.alert('提示','添加检测方法参照信息成功','info');
			                   reset_form();
			                }
			                else
			                {
			                    $.messager.alert('提示',result.msg,'error');
			                }
		                 }
		                 
		          });
                }
	     });
	 
	 }
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false,title:'新增检测方法参照信息'" style="overflow:hidden;" align="left">
       <form id="frm_add_detectionMethodReference">
           <table style="width:700px;height:400px;padding-top:10px" cellspacing="5">
               <tr height="30px">
                   <td align="right" style="width:20%">检测方法名称:</td>
                   <td align="left" style="width:30%">
	                  <input name="detectionMedhodName" id="detectionMedhodName" class="easyui-validatebox" data-options="required:true" maxLength='50' style="width:175px"/><span name="methodName_check" id="methodName_check"></span>
	               </td>
               </tr>
               <tr height="30px">
                   <td align="right" style="width:20%;color:red"><span><font><b>友情提示:</b></font></span></td>
                   <td align="left" style="width:30%">
	                           <span><font color='red'><b>检测方法名称只能是:双怠速法、稳态工况法、自由加速法、加载减速法或者摩托车双怠速法</b></font></span>
	               </td>
               </tr>
               <tr height="30px">
                   <td align="right" style="width:20%">燃油类型:</td>
                   <td align="left" style="width:30%">
	                  <input name="fuelType" id="fuelType" class="easyui-validatebox" data-options="required:true" maxLength='50' style="width:175px"/><span><font color='red'><b>请输入燃油类型：汽油、柴油、天然气</b></font></span>
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
	                  <input name="lengthMax" id="lengthMax" class="easyui-numberbox" data-options="required:true,precision:2,min:0"  style="width:175px"/>
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
               <tr height="30px">
	             <td colspan="2" align="center" style="width:100%">
	                 <a class="easyui-linkbutton" icon="icon-add" name="add" href="javascript:void(0)" onclick="add()">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" icon="icon-cancel" name="reset" href="javascript:void(0)" onclick="reset_form()">重置</a>
	             </td>
	           </tr> 
           </table>
       </form>
    </div>

</body>
</html>
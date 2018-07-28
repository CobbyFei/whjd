<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加检测线信息</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/letter.js" charset="utf-8"></script>
<script type="text/javascript">
    var flag1=false;
    var flag2=false;
	$(function(){
	      var stationId='${sessionScope.stationId}';
	      var stationName='${sessionScope.stationName}';
	      if(stationId!=0)
	      {
	         //此时表示不在市局
	         $("#stationId").combobox('setValue',stationId);
	         $("#stationId").combobox('setText',stationName);
	         $("#stationId").combobox({
	             disabled:true
	         });
	         

	         $("#maxDetectionNum").numberbox('setValue',12);
	         $("#maxDetectionNum").numberbox({
	             disabled:true
	         });
	         
	      }
		  else
		  {
			  $("#stationId").combobox({
					url : sy.bp() + '/inspectionStationAction!getInspectionStationName.action',
					mode : 'remote',
					delay : 500,
					valueField : 'stationId',
					required : true,
					textField : 'stationName'
				});
		  }
			
		  $("#lineName").bind("keyup",function(){
		         if($("#stationId").combobox('getText').trim()=="")
		         {
		             $.messager.alert('提示',"请选择所属检测站",'error');
		         }
		         else
		         {
		          // $.messager.alert('提示',"正在执行验证",'info');
				   $.ajax({
						url : sy.bp() + '/detectionLineAction!hasLineName.action',
						dataType : 'json',
						data : {
							stationId: '${sessionScope.stationId}',q:$(this).val()
						},
						success : function(data) {
							if (data.success) {
							     flag1=false;
							     $("#lineNameCheck").html("<font color='red'><b>名称不可用</b><font>");
							} 
							else {
							   flag1=true;
							   $("#lineNameCheck").html("<font color='red'><b>名称可用</b><font>");
							}
						}	
					});
		         }
		});
		 
		  $("#localeId").bind("keyup",function(){
		         if($("#stationId").combobox('getText').trim()=="")
		         {
		             $.messager.alert('提示',"请选择所属检测站",'error');
		         }
		         else
		         {
		          // $.messager.alert('提示',"正在执行验证",'info');
				   $.ajax({
						url : sy.bp() + '/detectionLineAction!hasLocaleId.action',
						dataType : 'json',
						data : {
							stationId:'${sessionScope.stationId}',q:$(this).val()
						},
						success : function(data) {
							if (data.success) {
							     flag2=false;
							     $("#localeIdCheck").html("<font color='red'><b>站内编号不可用</b><font>");
							} 
							else {
							   flag2=true;
							   $("#localeIdCheck").html("<font color='red'><b>站内编号可用</b><font>");
							}
						}	
					});
		         }
		});
	});

	function add()
	{
	   $.messager.confirm('请确认','确定要添加检测线信息？',function(r){
	      if(r)
	      {
	          $("#frm_add_detectionLine").form('submit',{
	                 url:sy.bp()+'/detectionLineAction!addDetectionLine.action',
	                 onSubmit:function(){
	                       var isValid=$(this).form("validate");
	                       if(flag1 && flag2 && isValid)
	                            return true;
	                       return false;
	                 },
	                 success:function(data){
	                    var result=eval("("+data+")");
	                    if(result.success)
		                {
		                   $.messager.alert('提示','添加检测线信息成功','info');
		                   reset_form();
		                }
		                else
		                {
		                    $.messager.alert('提示',result.msg,'error');
		                    $("#localeId").val("");
		                    $("#lineName").val("");
		                }
	                 }
	                 
	          });
	      }
	   });
	}
	
    function reset_form()
    {
       $("#frm_add_detectionLine").form('reset');
        var stationId='${sessionScope.stationId}';
	    var stationName='${sessionScope.stationName}';
	    if(stationId!=0)
	    {
	       //此时表示不在市局
	       $("#stationId").combobox('setValue',stationId);
	       $("#stationId").combobox('setText',stationName);
	       $("#stationId").combobox({
	           disabled:true
	        });
	    }
    }
    

    
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false,title:'新增检测线信息'" style="overflow:hidden;" align="left">
	    <form id="frm_add_detectionLine">
	        <table style="width:700px;height:400px;padding-top:10px" cellspacing="5">
	        	<tr height="30px">
	                <td align="right" style="width:20%">所属检测站:</td>
	                <td align="left" style="width:30%">
	                   <input name="stationId" id="stationId" class="easyui-combobox" data-options="required:true" style="width:175px" editable="false"/>
	                </td>
	            </tr> 
		        <tr height="30px">
	                <td align="right" style="width:20%">检测线名称:</td>
	                <td align="left" style="width:30%">
	                   <input name="lineName" id="lineName" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入检测线名称'" maxLength='20' style="width:175px"/><span id="lineNameCheck"></span>
	                </td>
	            </tr>    
		        <tr height="10px">
	                <td align="right" style="width:20%"></td>
	                <td align="left" style="width:30%;color:red"><b>检测线名称格式如：汽油车一号线</b></td>
	            </tr>    
		        <tr height="30px">
	                <td align="right" style="width:20%">站内编号:</td>
	                <td align="left" style="width:30%">
	                   <input name="localeId" id="localeId" class="easyui-numberbox" data-options="required:true,missingMessager:'请输入检测线站内编号',invalidMessage:'请输入数字',min:0" style="width:175px"/><span id="localeIdCheck"></span>
	                </td>
	            </tr>
		        <tr height="30px">
	                <td align="right" style="width:20%">每小时最大检测量:</td>
	                <td align="left" style="width:30%">
	                   <input name="maxDetectionNum" id="maxDetectionNum" class="easyui-numberbox" data-options="required:true,missingMessager:'请输入最大检测数量',invalidMessage:'请输入数字',min:0" style="width:175px"/>
	                </td>
	            </tr>
		        <tr height="30px">
	                <td align="right" style="width:20%">状态:</td>
	                <td align="left" style="width:30%">
	                   <input type="radio" name="lineStatus" id="lineStatus"  value="0" checked/>正常&nbsp;&nbsp;<input type="radio" name="lineStatus" id="lineStatus" value="1"/>注销
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
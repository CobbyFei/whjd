<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加车辆违规限行信息</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/letter.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function(){
     
    });
    
    function add(){
          $.messager.confirm('请确认','确定要添加该限行记录信息?',function(r){
               if(r)
               {
                
		          $("#frm_add_vehicleLimit").form('submit',{
		            url:sy.bp()+'/vehicleLimitAction!addVehicleLimitRecord.action',
		            onSubmit:function(){
		              //这里对车牌号不做校验，原因是有可能新车在车牌号里面填的是车架号
		              /*  var pattern=/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
			           if(!pattern.test($("#licence").val().trim()))
			           {
			             $.messager.alert('提示','车牌号的格式不正确!','error');
			             return  false;
			           } */
		               var isValid=$(this).form("validate");
		               var violationType=$("#violationType").val();
		               if(isValid && violationType!="0")
		               {
		                  return true;
		               }
		               else if(violationType=="0")
		               {
		                   $.messager.alert("提示","请选择违规类别","error");
		                   return false;
		               }
		               return false;
		            },
		            success:function(data){
		                var result=eval("("+data+")");
		                if(result.success)
		                {
		                   $.messager.alert('提示','添加成功','info');
		                   cancel();
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
    function cancel(){
        $("#frm_add_vehicleLimit").form('reset');
     }
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false,title:'新增黑名单车辆信息'" style="overflow:hidden;" align="left">
      <form id="frm_add_vehicleLimit">
         <table  style="width:700px;height:400px;padding-top:10px" cellspacing="5">
            <tr height="30px">
                <td align="right" style="width:20%">车牌号:</td>
                <td align="left" style="width:30%">
                   <input name="licence" id="licence" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入车牌号'" maxLength='10' style="width:175px"/>
                </td>
            </tr>
            <tr height="30px">
	                <td align="right" style="width:15%">车牌颜色:</td>
	                <td align="left" style="width:25%">
	                   <select name="licenceColor" id="licenceColor" class="easyui-combobox" width="317px" editable="false">
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
                   <select id="violationType" name="violationType"  style="width:175px" required="true">
                      <option value="0" selected="true">--请选择--</option>
                      <option value="1">违规限行</option>
                      <option value="2">超标排放</option>
                   </select>
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
             <td  align="right" style="width:20%">备注:</td>
              <td align="left"style="width:175px">
                <textarea name="remarks"rows="4" cols="20" maxLength='100'></textarea>
              </td>
           </tr>
          
           <tr height="30px">
             <td colspan="2" align="center" style="width:100%">
                 <a class="easyui-linkbutton" icon="icon-add" name="add" href="javascript:void(0)" onclick="add()">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" icon="icon-cancel" name="reset" href="javascript:void(0)" onclick="cancel()">重置</a>
             </td>

           </tr>
        </table>
     </form>
   </div>
</body>
</html>
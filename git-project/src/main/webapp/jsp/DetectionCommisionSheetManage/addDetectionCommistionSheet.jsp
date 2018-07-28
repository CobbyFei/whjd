<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建检测委托单</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/letter.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function(){
    
        var stationId='${sessionScope.stationId}';
        var stationName='${sessionScope.stationName}';
        if(stationId!="0")
        {
           $("#stationName").val(stationName);
           $("#stationName").attr("disabled",true);
        }
        var date=new Date();
        var time=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
        $("#detectionTime").datebox('setValue',time);
      //  $("#detectionMethod").val("自动生成检测方法");
      
      //  $("#detectionMethod").bind("onselect",function(){
            
            
             /*
             if($("#vehicleClass").combobox('getValue').trim()=="摩托车")
             {
                   $("#detectionMethod").val("摩托车双怠速法");
                   return;
             }
             if(($("#fuelType").combobox('getValue').trim()=="汽油"||$("#fuelType").combobox('getValue').trim()=="天然气") &&$("#vehicleClass").combobox('getValue').trim()=="全时四驱车")
             {
                  $("#detectionMethod").val("双怠速法");
                  return;
             }
             if($("#fuelType").combobox('getValue').trim()=="柴油" && $("#vehicleClass").combobox('getValue').trim()!="摩托车" && $("#vehicleClass").combobox('getValue').trim()!="普通车" && $("#vehicleClass").combobox('getValue').trim()!="全时四驱车")
             {
                  $("#detectionMethod").val("自由加速法");
                  return;
             }
            
             if($("#vehicleClass").combobox('getValue').trim()=="普通车")
             {
	              $.ajax({
					url : sy.bp() + '/detectionMethodReferenceAction!getDetectionMethod.action',
					dataType : 'json',
					data : {
						fuelType: $("#fuelType").combobox('getValue').trim(),vehicleLength:$("#vehicleLength").val().trim(),maxTotalQuality:$("#maxTotalQuality").val().trim()
					},
					success : function(data) {
					//true为核发类型
						if (data.success) {
						     $("#detectionMethod").val(data.msg);
						} 
						else {
						     $.messager.alert('提示',"获取方法失败",'error');
						}
					}	
				});
			    return;
             }
             */
           
             
       // });
        
         $("#frm_add_detectionCommisionSheet").find("input[name=vin]").keydown(function(e){
                 var curKey = e.which; 
		         if(curKey == 13){
		               $.ajax({
							url : sy.bp() + '/detectionCommisionSheetAction!getVehibleInfo.action',
							dataType : 'json',
							data : {
								vin: $("#vin").val().trim()
							},
							success : function(data) {
							     if(data.total>0)
							     {
							         $("#licence").val(data.rows[0].licence);
							         $("#fuelType").combobox('setValue',data.rows[0].fuelType);
							         $("#vehicleOwnerName").val(data.rows[0].vehicleOwnerName);
							         $("#vehicleOwnerAddress").val(data.rows[0].vehicleOwnerAddress);
							         $("#engineCode").val(data.rows[0].engineCode);
							         $("#vehicleModelCode").val(data.rows[0].vehicleModelCode);
							         $("#engineModel").val(data.rows[0].engineModel);
							         $("#fuelType").val(data.rows[0].fuelType);
							         $("#baseQuality").numberbox('setValue',data.rows[0].baseQuality);
							         $("#maxTotalQuality").numberbox('setValue',data.rows[0].maxTotalQuality);
							         $("#engineemissionAmount").numberbox('setValue',data.rows[0].engineemissionAmount);
							         $("#totalMiles").numberbox('setValue',data.rows[0].totalMiles);
							         $("#vehicleRegisterDate").val(data.rows[0].vehicleRegisterDate);
							         $("#vehicleType").combobox('setValue',data.rows[0].vehicleType);
							         $("#labelDistributeType").combobox('setValue',data.rows[0].labelDistributeType);
							         $("#detectionMethod").val(data.rows[0].detectionMethod);
							         //$("#emissionStandard").combobox('setValue',data.rows[0].emissionStandard);
							         $("#vehicleClass").combobox('setValue',data.rows[0].vehicleClass);
							         $("#vechileIssueCertTime").datebox('setValue',data.rows[0].vechileIssueCertTime);
							         $("#validatePeriod").datebox('setValue',data.rows[0].validatePeriod);
							         $("#vehicleRegisterDate").datebox('setValue',data.rows[0].vehicleRegisterDate);
							         $("#licenseColor").val(data.rows[0].licenseColor);
							         $("#vehicleLoadNum").numberbox('setValue',data.rows[0].vehicleLoadNum);
							         $("#vehicleLength").numberbox('setValue',data.rows[0].vehicleLength);
							        
							     }
							}	
						});
				  }
         });
        
        
    });
    
    function check(){
          
    	//稳态工况法不支持基准质量2500以上的车辆
    	 if($("#detectionMethod").combobox('getValue').trim()=="稳态工况法" && $("#baseQuality").numberbox('getValue') > 2500)
         {
             $.messager.alert('提示','稳态工况法不支持基准质量2500Kg以上的车辆','error');
             return false;
         }
    	 
    	 
         //验证车牌号  暂时车牌号放开  
        /*   var pattern=/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
          if(!pattern.test($("#licence").val().trim()))
          {
            if($("#vehicleType").val()!="0" || $("#vin").val().trim()!=$("#licence").val().trim())
            {
                $.messager.alert('提示','车牌号的格式不正确!','error');
                return  false;
            }
          } */
          
         //检测是否是新车,如果是新车，必须不低于国四标准
         //if($("#vehicleType").combobox('getValue').trim()=="0" && parseInt($("#emissionStandard").combobox('getValue').trim()) < 4)
         //{
         //    $.messager.alert('提示','新车的排放标准不能低于国四!','error');
         //    return false;
         //}
         //检测是否是新车,如果是摩托车新车，必须不低于国三标准；如果是汽车新车，必须不低于国四标准
          if($("#vehicleType").combobox('getValue').trim()=="0" && $("#vehicleClass").combobox('getValue').trim()=="摩托车" && parseInt($("#emissionStandard").combobox('getValue').trim()) < 3)
          {
        		  $.messager.alert('提示','摩托车新车的排放标准不能低于国三!','error');
        	      return false; 
          }
          if($("#vehicleType").combobox('getValue').trim()=="0" && $("#vehicleClass").combobox('getValue').trim()!="摩托车" && parseInt($("#emissionStandard").combobox('getValue').trim()) < 4)
          {
        		  $.messager.alert('提示','汽车新车的排放标准不能低于国四!','error');
        	      return false; 
          }
         
        /*   if($("#licence").val().trim().startWith("皖B") || $("#licence").val().trim().startWith("皖Q"))
         {
              if($("#vehicleType").combobox('getValue').trim()=="1")
              {
                 $.messager.alert('提示','该车牌是本地车，请重新选择车辆类型','error');
                 return false;
              }
         }  */
         if($("#licence").val().trim().substring(0,2)=="皖B"||$("#licence").val().trim().substring(0,2)=="皖Q")
         {
             if($("#vehicleType").combobox('getValue').trim()=="1")
              {
                 $.messager.alert('提示','该车牌是本地车，请重新选择车辆类型','error');
                 return false;
              }
         }
         
         if($("#fuelType").combobox('getValue').trim()=="0")
         {
             $.messager.alert('提示','请选择燃油类型','error');
             return false;
         }
      
         if($("#vehicleType").combobox('getValue').trim()=="0")
         {
             if($("#labelDistributeType").combobox('getValue').trim()=="1")
             {
                 $.messager.alert('提示','新车应该是首发检测类型','error');
                 return false;
             }
         }
         if($("#vehicleType").combobox('getValue').trim()=="2")
         {
             if($("#labelDistributeType").combobox('getValue').trim()=="0")
             {
                 $.messager.alert('提示','其他车应该属于换发检测类型','error');
                 return false;
             }
         }
       
         if($("#licence").val().trim()==$("#vin").val().trim())
         {
              if($("#vehicleType").combobox('getValue').trim()=="1" || $("#vehicleType").combobox('getValue').trim()=="2")
              {
                 $.messager.alert('提示','车架号和车牌号相同，这辆车应该是新车！请重新选择车辆类型','error');
                 return false;
              }
         }
         
         if($("#fuelType").combobox('getValue').trim()=="无")
         {
             $.messager.alert('提示','请输入燃油类型！','error');
             return false;
         }
         if($("#vehicleClass").combobox('getValue').trim()=="无")
         {
             $.messager.alert('提示','请选择车辆类别！','error');
             return  false;
         }
        
         if($("#maxTotalQuality").val().trim()=="")
         {
             $.messager.alert('提示','请输入最大总质量！','error');
             return false;
         }
        
         if($("#vehicleLength").val().trim()=="")
         {
             $.messager.alert('提示','请输入车身长度！','error');
             return false;
         }
          if($("#fuelType").combobox('getValue').trim()=="无")
             {
                 $.messager.alert('提示','请输入燃油类型！','error');
                 return;
             }
             if($("#vehicleClass").combobox('getValue').trim()=="无")
             {
                 $.messager.alert('提示','请选择车辆类别！','error');
                 return;
             }
           
             if($("#maxTotalQuality").val().trim()=="")
             {
                 $.messager.alert('提示','请输入最大总质量！','error');
                 return;
             }
           
             if($("#vehicleLength").val().trim()=="")
             {
                 $.messager.alert('提示','请输入车身长度！','error');
                 return;
             }
             if($("#fuelType").combobox('getValue').trim()=="汽油"||$("#fuelType").combobox('getValue').trim()=="天然气")
             {
                 if($("#vehicleClass").combobox('getValue').trim()!="普通车" && $("#vehicleClass").combobox('getValue').trim()!="全时四驱车"&&$("#vehicleClass").combobox('getValue').trim()!="摩托车")
                  {
                     $.messager.alert('提示','燃油类型为汽油或者天然气时，车辆类别只能是普通车、摩托车或者全时四驱车!请重新选择车辆类别','error');
                     return;
                  }
             }
             if($("#fuelType").combobox('getValue').trim()=="柴油")
             {
                 if($("#vehicleClass").combobox('getValue').trim()=="全时四驱车")
                  {
                     $.messager.alert('提示','燃油类型为柴油时，车辆类别不能选择全时四驱车!请重新选择车辆类别','error');
                     return;
                  }
             }
         
         
         
/*        	  $.ajax({
				url : sy.bp() + '/detectionCommisionSheetAction!isFirstDetecetd.action',
				dataType : 'json',
				data : {
					vin: $("#vin").val().trim()
				},
				success : function(data) {
				//true为核发类型
					if (data.success) {
					    if($("#labelDistributeType").combobox('getValue').trim()=="1")
					    {
					        flag1=true;
					    }
					} 
					else {
					    if($("#labelDistributeType").combobox('getValue').trim()=="0")
					    {
					        flag2=true;
					    }
					}
				}	
			});
		if(flag1)
		{
		    $.messager.alert('提示',"该车不存在检测记录，应该属于核发类型！请重新选择检测类型",'error');
		    return false;
		}
		if(flag2)
		{
		    $.messager.alert('提示',"该车存在检测记录，应该属于换发类型！请重新选择检测类型",'error');
		    return false;
		} */
	   
        return true;
    }
    
    //根据环保厅接口查询车辆信息
    function searchVehicleInfoByHBTing(){
    	if($("#licence").val().trim()=="")
        {
        		$.messager.alert('提示','请填写车牌号。','info');
        		return false;
        }
    	
    	$.ajax({
    		url : sy.bp() + '/detectionCommisionSheetAction!getVehicleInfoByHBTing.action',
    		dataType : 'json',
    		data : {
    			licence: $("#licence").val().trim(),
    			licenseColor: $("#licenseColor").val().trim(),
    		},
    		success : function(data) {
    		     if(data.total==1)
    		     {
    		    	 $("#vin").val(data.rows[0].vin);
    		    	 //$("#licence").val(data.rows[0].licence);
			         $("#fuelType").combobox('setValue',data.rows[0].fuelType);
			         $("#vehicleOwnerName").val(data.rows[0].vehicleOwnerName);
			         $("#vehicleOwnerAddress").val(data.rows[0].vehicleOwnerAddress);
			         $("#engineCode").val(data.rows[0].engineCode);
			         $("#vehicleModelCode").val(data.rows[0].vehicleModelCode);
			         $("#engineModel").val(data.rows[0].engineModel);
			         $("#fuelType").val(data.rows[0].fuelType);
			         $("#baseQuality").numberbox('setValue',data.rows[0].baseQuality);
			         $("#maxTotalQuality").numberbox('setValue',data.rows[0].maxTotalQuality);
			         $("#engineemissionAmount").numberbox('setValue',data.rows[0].engineemissionAmount);
			         $("#totalMiles").numberbox('setValue',data.rows[0].totalMiles);
			         $("#vehicleRegisterDate").val(data.rows[0].vehicleRegisterDate);
			         $("#vehicleType").combobox('setValue',data.rows[0].vehicleType);
			         $("#labelDistributeType").combobox('setValue',data.rows[0].labelDistributeType);
			         $("#detectionMethod").val(data.rows[0].detectionMethod);
			         $("#emissionStandard").combobox('setValue',data.rows[0].emissionStandard);
			         $("#vehicleClass").combobox('setValue',data.rows[0].vehicleClass);
			         $("#vechileIssueCertTime").datebox('setValue',data.rows[0].vechileIssueCertTime);
			         $("#validatePeriod").datebox('setValue',data.rows[0].validatePeriod);
			         $("#vehicleRegisterDate").datebox('setValue',data.rows[0].vehicleRegisterDate);
			         //$("#licenseColor").val(data.rows[0].licenseColor);
			         $("#vehicleLoadNum").numberbox('setValue',data.rows[0].vehicleLoadNum);
			         $("#vehicleLength").numberbox('setValue',data.rows[0].vehicleLength); 
    		     }else{
    		    	 $.messager.alert('提示','查询车辆基本信息失败，请检查车牌号、车牌颜色等信息，或者手工录入。','info');
    		     }
    		}	
    	});
    }
    
    
    //用户无法手动选择环保标准，点击查询按钮，根据初始登记日期、车辆型号、发动机型号查询。
    function searchEmissionStandard(){
    	if($("#vehicleModelCode").val().trim()=="")
        {
        		$.messager.alert('提示','请填写查询排放标准需要的车辆型号。','info');
        		return false;
        }
    	if($("#engineModel").val().trim()=="")
        {
        		$.messager.alert('提示','请填写查询排放标准需要的发动机号。','info');
        		return false;
        }
    	if($('#vehicleRegisterDate').datebox('getValue').trim()=="")
        {
        		$.messager.alert('提示','请填写查询排放标准需要的初始登记日期。','info');
        		return false;
        }
    	
       	$.ajax({
		url : sy.bp() + '/detectionCommisionSheetAction!getEmissionStandard.action',
		dataType : 'json',
		data : {
			vehicleModelCode: $("#vehicleModelCode").val().trim(),
			engineModel: $("#engineModel").val().trim(),
			vehicleRegisterDate: $('#vehicleRegisterDate').datebox('getValue').trim(),
		},
		success : function(data) {
		     if(data.total>=1)
		     {
		         $("#emissionStandard").combobox('setValue',data.rows[0].engineemissionAmount); 
		     }else{
		    	 $.messager.alert('提示','查询车辆排放标准失败，请检查车辆型号、发动机型号、注册日期等信息','info');
		     }
		}	
	});
    }
    
    function add(){	
    if($("#detectionMethod").combobox('getValue').trim()=="请选择")
      {
      		$.messager.alert('提示','请选择要使用的检测方法','info');
      		return false;
      }
    if($("#emissionStandard").combobox('getValue').trim()=="0")
    {
    		$.messager.alert('提示','请点击查询按钮，查询车辆排放标准。','info');
    		return false;
    }
     $.messager.confirm('请确认','确定要添加检测委托单信息？',function(r){
	      if(r)
	      {  
	           
	           $.messager.confirm("请确认","本车使用的是"+ $("#detectionMethod").combobox('getValue').trim()+",提交后将不能更改！",function(r){
	           		if(r)
	           		{
			           		$("#frm_add_detectionCommisionSheet").form('submit',{
					             url:sy.bp()+'/detectionCommisionSheetAction!addDetectionCommisionSheet.action',
					             onSubmit:function(){
					                       var isValid=$(this).form("validate");
					                       if(isValid==false)
					                           return false;
					                       var check_form=check();
					                       if(check_form && isValid)
					                           return true;
					                       return false;
					                        
					                 },
					            success:function(data){
					                    var result=eval("("+data+")");
					                    if(result.success)
						                {
						                   $.messager.alert('提示','新建检测委托单信息成功','info');
						                   reset_form();
						                }
						                else
						                {
						                    $.messager.alert('提示',result.msg,'error');
						                   // reset_form();
						                }
					                 }
					          });
	           		}
	           });
          }
        });
    }
    
    function reset_form(){
       $("#frm_add_detectionCommisionSheet").form('reset');
       var date=new Date();
       var time=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
       $("#detectionTime").datebox('setValue',time);
       
       var stationId='${sessionScope.stationId}';
        var stationName='${sessionScope.stationName}';
        if(stationId!="0")
        {
           $("#stationName").val(stationName);
           $("#stationName").attr("disabled",true);
        }
    }   
    
</script>
<script type="text/javascript">
function scanqrcode() {
    var qrcode = $("#qrcode").val().toString();
    if (qrcode == "") {
      alert("输入框不能为空！");
        return false;
    }
    var data = qrcode.split("|");
    $("#vin").val(data[7]);
    var fuelclass = "";
    if (data[18] == "A") {
        fuelclass = "汽油";
    } else if (data[18] == "B") {
        fuelclass = "柴油";
    } else if (data[18] = "E") {
        fuelclass = "天然气";
    } else if (data[18] == "O") {
        fuelclass = "混合动力";
    } else { };
    $("#fuelType").combobox('setValue',fuelclass);  
    $("#engineCode").val(data[9]);
    $("#vehicleModelCode").val(data[6]);
    $("#engineModel").val(data[10]); 
    $("#baseQuality").val(parseInt(data[20])+100);
    $("#maxTotalQuality").val( parseInt(data[19]));
    $("#engineemissionAmount").val(parseInt(data[12]));
    var standard = "";
    if (data[11].indexOf("GB18352.3-2005")!=-1) {
        standard = "4";
    } else if (data[11].indexOf("")) {
        standard="1"
    } else if (data[11].indexOf("")) {
        standard = "2"
    } else if (data[11].indexOf("")) {
        standard = "3"
    } else if (data[11].indexOf("")) {
        standard = "5"
    } else if (data[11].indexOf("")) {
        standard = "6"
    }
    $("#emissionStandard").combobox('setValue',standard);
    $("#vehicleClass").combobox('setValue',data[4]);  
    $("#vehicleLoadNum").val(parseInt(data[15]));
   
}
function clearinput(){
    $("#qrcode").val("");
}
</script>
</head>
<body class="easyui-layout" data-options="fit:true">
     <div data-options="region:'center',border:false,title:'新建检测委托单信息'" style="" align="left">
     
        <form id="frm_add_detectionCommisionSheet">
            <table style="width:90%;height:100%;padding-top:10px" cellspacing="5">
                <tr height="30px">
	                <td align="right" style="width:15%">检测站名称:</td>
	                <td align="left" style="width:25%">
	                   <input name="stationName" id="stationName" class="easyui-validatebox" data-options="required:true" maxLength='20' style="width:175px"/>
	                </td>
	                
	                <td align="right" style="width:15%">车架号:</td>
	                <td align="left" style="width:25%">
	                   <input name="vin" id="vin" class="easyui-validatebox" data-options="required:true" maxLength='30' style="width:175px"/>
	                </td>
	            </tr> 
               <tr height="30px">
                    
	                <td align="right" style="width:15%">车牌号:</td>
	                <td align="left" style="width:25%">
	                   <input name="licence" id="licence" class="easyui-validatebox" data-options="required:true" maxLength='20' style="width:175px"/>
	                </td>
	                
	                <td align="right" style="width:15%">车主姓名:</td>
	                <td align="left" style="width:25%">
	                   <input name="vehicleOwnerName" id="vehicleOwnerName" class="easyui-validatebox" data-options="required:true"  maxLength='30' style="width:175px"/>
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
	                   <input name="engineModel" id="engineModel" class="easyui-validatebox" data-options="required:true" maxLength='20' style="width:175px"/>
	                </td>
	                <td align="right" style="width:15%">发动机号:</td>
	                <td align="left" style="width:25%">
	                   <input name="engineCode" id="engineCode" class="easyui-validatebox" data-options="required:true" maxLength='20' style="width:175px"/>
	                </td>
	            </tr> 
               <tr height="30px">
	                <td align="right" style="width:15%">检测日期:</td>
	                <td align="left" style="width:25%">
	                   <input name="detectionTime" id="detectionTime" class="easyui-datetimebox" data-options="required:true,disabled:true" style="width:175px"/>
	                </td>
	                <td align="right" style="width:15%">车辆初始登记日期:</td>
	                <td align="left" style="width:25%">
	                   <input name="vehicleRegisterDate" id="vehicleRegisterDate" class="easyui-datebox" data-options="required:true,editable:false" style="width:175px"/>
	                </td>
	            </tr>
               <tr height="30px">
	                <td align="right" style="width:15%">车辆发证日期:</td>
	                <td align="left" style="width:25%">
	                   <input name="vechileIssueCertTime" id="vechileIssueCertTime" class="easyui-datebox" data-options="required:true,editable:false" style="width:175px"/>
	                </td>
	                <td align="right" style="width:15%">有效期:</td>
	                <td align="left" style="width:25%">
	                   <input name="validatePeriod" id="validatePeriod" class="easyui-datebox" data-options="required:true,editable:false" style="width:175px"/>
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
	                   <input name="vehicleModelCode" id="vehicleModelCode" class="easyui-validatebox" data-options="required:true" maxLength='40' style="width:175px"/>
	                </td>
	            </tr> 
                   <tr height="30px">
	                <td align="right" style="width:15%">检测类型:</td>
	                <td align="left" style="width:25%">
	                   	 <select name="labelDistributeType" id="labelDistributeType" class="easyui-combobox" editable="false" >
	                        <option value="0" selected="true">首发</option>
	                        <option value="1">换发</option>
	                    </select>
	                </td>
	                <td align="right" style="width:15%">排放标准:</td>
	                <td align="left" style="width:25%">
	                    <select name="emissionStandard" id="emissionStandard" class="easyui-combobox"  editable="false" data-options="required:true,disabled:true">
	                    	<option value="0" selected="true">请查询</option>
	                        <option value="1">国1</option>
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
	                   <input name="baseQuality" id="baseQuality" class="easyui-numberbox" data-options="required:true,min:0" style="width:175px"/><span><font color='red'><b>&nbsp;&nbsp;(基准质量=整备质量+100公斤)</b></font></span>
	                </td>
	                <td align="right" style="width:15%">累计行驶里程数:</td>
	                <td align="left" style="width:30%">
	                   <input name="totalMiles" id="totalMiles" class="easyui-numberbox" data-options="required:true,precision:2,min:0" style="width:175px"/>
	                </td>
	            </tr>
	            <tr height="30px">
	               <td align="right" style="width:15%">车辆来源:</td>
	                <td align="left" style="width:25%">
	                   <select name="vehicleType" id="vehicleType" class="easyui-combobox" width="317px" editable="false">
	                        <option value="0" selected="true">新车</option>
	                        <option value="1">外地转入车</option>
	                        <option value="2">在用车</option>
	                    </select>
	                </td>
	                <td align="right" style="width:15%">燃油类型:</td>
	                <td align="left" style="width:25%">
	                   <select name="fuelType" id="fuelType" class="easyui-combobox" editable="false">
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
	                <td align="right" style="width:15%">车身长度(单位:mm):</td>
	                <td align="left" style="width:30%">
	                   <input name="vehicleLength" id="vehicleLength" class="easyui-numberbox" data-options="required:true,precision:2,min:0" style="width:175px"/>
	                </td>
	            </tr> 
               <tr height="30px">
	               <td align="right" style="width:15%">车辆类别:</td>
	                <td align="left" style="width:30%">
	                   <select name="vehicleClass" id="vehicleClass" class="easyui-combobox" editable="false">
	                        <option value="无" selected>---请选择---</option>
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
	                <td align="right" style="width:15%">检测方法:</td>
	                <td align="left" style="width:40%">
	                   <select name="detectionMethod" id="detectionMethod"  class="easyui-combobox" editable="false" style="width:175px">
	                        <option value="请选择" selected="true">---请选择---</option>
	                        <option value="自由加速法">自由加速法</option>
	                        <option value="双怠速法">双怠速法</option>
	                        <option value="稳态工况法">稳态工况法</option>
	                        <option value="加载减速法">加载减速法</option>
	                        <option value="摩托车双怠速法">摩托车双怠速法</option>
	                   </select>
	                </td>
	            </tr> 
	            
                <tr height="30px">
                
	                <td colspan="6" align="center" >
	                
	                <!--  
	                <a class="easyui-linkbutton" icon="icon-search" name="searchVehicleInfoByHBTing" href="javascript:void(0)" onclick="searchVehicleInfoByHBTing()">根据车牌查询查车辆信息</a>
	                    &nbsp;&nbsp;&nbsp;&nbsp;-->
	                <a class="easyui-linkbutton" icon="icon-search" name="searchEmissionStandard" href="javascript:void(0)" onclick="searchEmissionStandard()">查询车辆排放标准</a>
	                    &nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" icon="icon-add" name="add" href="javascript:void(0)" onclick="add()">添加</a>
	                    &nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" icon="icon-cancel" name="reset" href="javascript:void(0)" onclick="reset_form()">重置</a>
	                </td>
	            </tr> 
            </table>
        </form>
        <!-- xuningli -->
        <div style="margin-left:auto;margin-right:auto">
        <input name="qrcode" id="qrcode" style="width:10px;height:25px;color:#fff" ><button neme="scanbutton" id="scanbutton" onclick="scanqrcode()">二维码输入</button><button name="clearinput" onclick="clearinput()">清空</button>
            </div>
     </div>

</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>安徽省芜湖市机动车尾气检测管理系统</title>
<jsp:include page="inc.jsp"></jsp:include>

<script type="text/javascript">
	//var isCloseWindows = true;
    $(function(){
       
        setInterval("clock()", 1000);
        setInterval("sendHeartBets()",1000*60*2);
        
        
       
    });
    function clock(){
           var t=new Date();
           var month=(t.getMonth()+1)>9?(t.getMonth()+1):("0"+(t.getMonth()+1));
           var year=t.getFullYear();
           var date=t.getDate()>9?t.getDate():("0"+t.getDate());
           var hour=t.getHours()>9?t.getHours():("0"+t.getHours());
           var minute=t.getMinutes()>9?t.getMinutes():("0"+t.getMinutes());
           var second=t.getSeconds()>9?t.getSeconds():("0"+t.getSeconds());
           
           var time=year+"年"+month+"月"+date+"日 "+hour+":"+minute+":"+second+"      "+getDayOfWeek();
          
           $("#myDate").html(time);
          
       }
    function doLogout()
    {
         $.messager.confirm('请确认','确定要安全退出系统？',function(r){
            if(r)
            {
            	//isCloseWindows = false;
                 $.ajax({
					url : sy.bp() + '/userLoginAction!logOut.action',
					dataType : 'json',
					success : function(data) {
					//true为核发类型
						if (data.success) {
						     window.location.href="/whjd/index.jsp";
						} 
						else {
						     $.messager.alert('提示',data.msg,'error');
						}
					}	
				});
            }
         });
    }
    
    function eraseOnlineState()
    {
        $.ajax({
		url : sy.bp() + '/userLoginAction!logOut.action',
		dataType : 'json',
		success : function(data) {
		//true为核发类型
			if (data.success) {
			     return true;
			} 
			else {
			     alert('提示',data.msg,'error');
			     return false;
			}
		}	
	    });
    }
    
    /*window.onbeforeunload = function(event) { 
		//-----你要执行的代码-------------
		if(isCloseWindows)
		{
			eraseOnlineState();
			return '正在使用安全退出'; 
		}	
	} ;*/

    function getDayOfWeek()
    {
        var date=new Date();
        var day=date.getDay();
        var result="";
        
        switch(day)
        {
            case 0:
                result="星期日";
                break;
            case 1:
                result="星期一";
                break;
            case 2:
                result="星期二";
                break;
            case 3:
                result="星期三";
                break;
            case 4:
                result="星期四";
                break;
            case 5:
                result="星期五";
                break;
            case 6:
                result="星期六";
                break;
            default:
                result="";
                break;
        }
        return result;
    }
    
    function sendHeartBets()
    {
        $.ajax({
			url : sy.bp() + '/userLoginAction!sendBeatHearts.action',
			dataType : 'json',
			success : function(data) {
			//true为核发类型
				if (data.success) {
				} 
				else {
				   setTimeout("delay()", 3000);
				   window.location.href="/whjd/index.jsp";
				}
			}	
		});
    }
   function delay()
   {
       $.messager.alert('与服务器链接出现异常，3秒后返回登录界面');
   }
</script>

<style type="text/css">
	TD.TopLinkBott {
		PADDING-RIGHT: 10px;
		PADDING-LEFT: 3px;
		PADDING-BOTTOM: 10px;
		VERTICAL-ALIGN:bottom;
		COLOR: #000000;
		PADDING-TOP: 3px;
		FONT-FAMILY: Arial, Verdana, Helvetica, sans-serif
	}
	TD.TopLink {
		PADDING-RIGHT: 3px;
		PADDING-LEFT: 3px;
		PADDING-BOTTOM: 1px;
		VERTICAL-ALIGN: middle;
		COLOR: #000000;
		PADDING-TOP: 3px;
		FONT-FAMILY: Arial, Verdana, Helvetica, sans-serif
	}
	TD.TopLink_over {
		BORDER-RIGHT: #adc9ff 1px solid;
		PADDING-RIGHT: 2px;
		BORDER-TOP: #adc9ff 1px solid;
		PADDING-LEFT: 2px;
		PADDING-BOTTOM: 0px;
		VERTICAL-ALIGN: middle;
		BORDER-LEFT: #adc9ff 1px solid;
		COLOR: #000000;
		PADDING-TOP: 2px;
		BORDER-BOTTOM: #adc9ff 1px solid;
		FONT-FAMILY: Arial, Verdana, Helvetica, sans-serif;
		BACKGROUND-COLOR: #5781d5
	}
	TD.TopLink_down {
		BORDER-RIGHT: #adc9ff 1px solid;
		PADDING-RIGHT: 2px;
		BORDER-TOP: #adc9ff 1px solid;
		PADDING-LEFT: 2px;
		PADDING-BOTTOM: 0px;
		VERTICAL-ALIGN: middle;
		BORDER-LEFT: #adc9ff 1px solid;
		COLOR: #ffffff;
		PADDING-TOP: 2px;
		BORDER-BOTTOM: #adc9ff 1px solid;
		FONT-FAMILY: Arial, Verdana, Helvetica, sans-serif;
		BACKGROUND-COLOR: #3063c9
	}
	TD.ToolbarSeparator {
		FONT: 10px verdana;
		MARGIN-LEFT: 3px;
		VERTICAL-ALIGN: middle;
		COLOR: #ffffff;
		MARGIN-RIGHT: 3px
	}
	#loading{
		position:absolute;
		left:0px;
		top:0px;
		padding:2px;
		z-index:20001;
		height:100%;
		width:100%;
		text-align:center;
		background:#FFFFFF;
	}
	#loading a {
		color:#225588;
	}
	#loading .loading-indicator{
		position: absolute;
		top: 50%; 
		left: 50%;
		background:white;
		color:#444;
		font:bold 13px tahoma,arial,helvetica;
		padding:10px;
		margin-left:-100px;
		margin-top:-50px;
		width:200px;
		height:100px;
	}
	#loading-msg {
		font: normal 10px arial,tahoma,sans-serif;
	}
</style>

</head>

<body class="easyui-layout">
   <div region="north" split="false" border="false" style="height:70px;overflow:hidden">
        <div class="easyui-layout" fit="true" scroll="no" border="false">
			<div region="south" split="false" border="false" style="height:5px; background-color:#D2E0F2; padding-top:0px; padding-bottom:0px" align="center">
				<div style="margin-top:0px; margin-bottom:0px; padding-top:0px; padding-bottom:0px"><img id="top-spilter-img" src="../images/datagrid_sort_asc.gif" width="13px" height="5px" onclick="doTopWin()" title="点击隐藏" border="0" style="cursor:pointer; margin-top:0px; margin-bottom:0px" /></div>
			</div>
			<div region="center" style="overflow:hidden;">
				<table id="01" height="70px" width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="799px">
							<img src="images/222.png" width="799" height="70" alt="安徽省芜湖市机动车尾气检测信息管理系统" />
						</td>
						<td style="BACKGROUND-REPEAT: repeat-x" width="100%" background="/whjd/images/tc.gif" align="right">
							<TABLE height="100%" cellSpacing="0" cellPadding="0" border="0">
							  <TR>
								<TD vAlign="top" align="right"><TABLE cellSpacing="0" cellPadding="0" border="0">
									<TR>									  
									  <TD onMouseUp="this.className='TopLink_over'" class="TopLink" onMouseDown="this.className='TopLink_down'" onMouseOver="this.className='TopLink_over'" style="CURSOR: hand" onMouseOut="this.className='TopLink'" Align="center" valign="middle"><a href="/whjd/docs/helper.pdf" target="_blank"><FONT 
								color="#E5E5E5"><NOBR>帮助</NOBR></FONT></a></TD>
									  <TD class="ToolbarSeparator">|</TD>
									  
									  <TD onMouseUp="this.className='TopLink_over'" class="TopLink" onMouseDown="this.className='TopLink_down'" onMouseOver="this.className='TopLink_over'" style="CURSOR: hand" onMouseOut="this.className='TopLink'" Align="center" valign="middle"><a href="javascript:void(0)" onclick="doLogout()"><FONT color="#FF0000" style="font-size:15px; font-weight:bold"><NOBR>安全退出</NOBR></FONT></a></TD>
									  <TD>&nbsp;</TD>
									</TR>
								  </TABLE></TD>
							  </TR>
							  <TR>
								<TD class="TopLinkBott"  valign="bottom"><NOBR><span id="myDate"></span>&nbsp;&nbsp;&nbsp;&nbsp;欢迎您，<%=session.getAttribute("userName")==null?"":(session.getAttribute("userName")) %></NOBR></TD>
							  </TR>
							</TABLE>
							
						</td>
					</tr>
				</table>
			</div>
		</div>
    </div>
	<!-- <div data-options="region:'south', title:'south title'" style="height: 20px;"></div> -->
	<div data-options="region:'west', title:'功能导航', href:'${pageContext.request.contextPath}/layout/west.jsp'" style="width:200px;overflow:hidden;"></div>
	<!-- <div data-options="region:'east', split: true, title:'east title'" style="width: 200px;"></div> -->
	<div data-options="region:'center', title:'欢迎使用芜湖市机动车尾气检测管理系统', href:'${pageContext.request.contextPath}/layout/center.jsp'" style="overflow:hidden;"></div>
</body>
</html>

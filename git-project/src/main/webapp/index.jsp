<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
	<jsp:include page="inc.jsp"></jsp:include>
	<title>芜湖市环保局机动车尾气检测管理系统----登录</title>
	<META http-equiv=Content-Type content="text/html; charset=UTF-8">
	<LINK href="style/login.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
   $(function(){
         $("#form1").find("input").keydown(function(e){
                 var curKey = e.which; 
		         if(curKey == 13){
		              login();
				  }
         });
   
   });
   
   function check(){
       var frm=document.form1;
       if(frm.simplifyName.value=="")
       {
           $.messager.alert('提示','用户名不能为空！','error');
           frm.simplifyName.focus();
           return false;
       }
       if(frm.pwd.value=="")
       {
          $.messager.alert('提示','请输入密码','error');
          frm.pwd.focus();
          return false;
       }
       return true;
   }
   
  function login()
  {
     $("#form1").form('submit',{
          url:sy.bp() + '/userLoginAction!checkUserLogin.action',
          onSubmit:function(){
              return check();
          },
          success:function(data){
              var result=eval('('+data+')');
              if(result.success)
              {
                   window.parent.location.href="/whjd/main.jsp";
              }
              else{
                   $.messager.alert('提示',result.msg,'error');
              }
          }
     });
  }
  
  function forget_pwd(){
     $.messager.alert('提示','请联系管理员！','info');
  }

</script>
		
	</HEAD>
	<BODY bgColor=#ffffff onLoad="javascript:document.form1.simplifyName.focus();">
		<CENTER>
			
			<DIV id=content>
				<DIV class=module_darkgray>
					<DIV class=bottomedge_darkgray>
						<DIV class=topleft_darkgray></DIV>
						<DIV class=topright_darkgray></DIV>
						<DIV class=moduleborder>
							<DIV class=module_inset_darkgray>
								<DIV class=bottomedge_inset_darkgray>
									<DIV class=topleft_inset_darkgray></DIV>
									<DIV class=topright_inset_darkgray></DIV>
									<DIV style="LEFT: 735px; PADDING-TOP: 5px; POSITION: absolute">
										<IMG height=20 src="images/white_lock.gif" width=14 border=0>									</DIV>
									<DIV style="PADDING-LEFT: 35px; PADDING-BOTTOM: 20px; PADDING-TOP: 40px; align: left">
										<IMG src="images/text_dotmaclogin.jpg" border=0>									</DIV>
									<DIV style="PADDING-RIGHT: 15px; PADDING-LEFT: 35px">
										<TABLE cellSpacing=0 cellPadding=0 width=689 border=0>
											<TBODY>
												<TR>
													<TD style="PADDING-RIGHT: 20px" width=318>
														<TABLE cellSpacing=0 cellPadding=0 border=0>
															<TBODY>
																<TR >
																	<TD style="MARGIN-BOTTOM: 10px" vAlign=top height=15>
																		<FONT SIZE="3" ><strong>用户登录</strong></FONT>																</TD>
																</TR>
																<TR >
																	<TD class=content_gray_bold height=30>
																		<FONT SIZE="3" >请输入您的用户名和密码登录系统。</FONT>																</TD>
																</TR>
															</TBODY>
														</TABLE>
														<!-- Begin Form -->
														<form method="post" id="form1" name="form1" theme="simple">
															<TABLE cellSpacing=0 cellPadding=0 width=318 border=0>
																<TBODY>
																	<TR>
																		<TD height=15>																		</TD>
																	</TR>
																	<TR>
																		<TD align=left>
																			<SPAN class=content_black_bold><FONT SIZE="3" >用户名</FONT></SPAN>
																			<BR>
																			<FONT class=form><INPUT class=form value=""
																					autocomplete="off" style="WIDTH: 250px"
																					maxLength=28 name="simplifyName" id="simplifyName" class="easyui-validatebox" required="true"> </FONT>
																			<font color="red"></font>																		</TD>
																	</TR>
																	<TR>
																		<TD height=8>																		</TD>
																	</TR>
																	<TR>
																		<TD align=left>
																			<SPAN class=content_black_bold ><FONT SIZE="3" >密码</FONT></SPAN>
																			<BR>
																			<FONT class=form><INPUT class=form value=""
																					style="WIDTH: 250px" type="password" maxLength=32
																					name="pwd" minlength="6" class="easyui-validatebox" required="true"> </FONT>																		</TD>
																	</TR>
																	<TR>
																		<TD height=10>
																			<IMG height=10 alt=""
																				src="images/spacer.gif"
																				width=1 border=0>																		</TD>
																	</TR>
																	<TR>
																		<TD class=content_gray vAlign=top align=left>
																			<A  href="javascript:forget_pwd()">忘记密码?</A>
																		</TD>
																	</TR>

																	<TR>
																		<TD noWrap align=right style="padding-right:68px">
																			<input type="button" id="loginbtn" iconCls="icon-ok" href="javascript:void(0)" onClick="login()" value="登录"/>
																		</TD>
																	</TR>
																</TBODY>
															</TABLE>
														</form>													</TD>
													<!-- End Form -->
													<TD vAlign=top>
														<TABLE cellSpacing=0 cellPadding=0 border=0>
															<TBODY>
																<TR>
																	<TD style="BACKGROUND-COLOR: #e3e3e3" width=2
																		height=200></TD>
																</TR>
															</TBODY>
														</TABLE>													</TD>
													<TD style="PADDING-LEFT: 30px" vAlign=top width=318>
														<!-- Message 2 -->
														<TABLE cellSpacing=0 cellPadding=0 border=0 id="table1">
															<TBODY>
																<TR>
																	<TD class=content_gray vAlign=top >
																		<param name="wmode" value="transparent">
																		<embed width="330" height="152" src="images/banner.swf"  menu="false" type="application/octet-stream" wmode="transparent"/>
																		<p style="margin-top:5px">
																		为了更合理的开展检测工作,让员工可以轻松高效地完成工作,促
																		<br />
																		进芜湖市环保检测信息化建设的发展.</p>
																																			</TD>
																</TR>
															</TBODY>
														</TABLE>													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</DIV>
							</DIV>
						</DIV>
					</DIV>
				</DIV>
			</DIV>
			<DIV>
				<TABLE cellSpacing=0 cellPadding=0 width=776 align=center border=0>
					<TBODY>
						<TR>
							<TD vAlign=top align="center" width=776>
								芜湖市环保局机动车尾气检测管理系统						</TD>
						</TR>
					</TBODY>
				</TABLE>
			</DIV>
			<!-- END content_gray -->
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<TD align="center">
							<FONT class=disclaimer face="Geneva, Verdana, Arial, Helvetica"
								color=#999999 size=1>©&nbsp;Copyright 2014 .大邦软件
								All rights reserved.</FONT>
							<BR>
							<BR>						</TD>
					</TR>
				</TBODY>
			</TABLE>
		</CENTER>
    <!--<object CLASSID="clsid:2A3D0646-E5FD-4D18-9DD1-59FB5E9855E0"
        CODEBASE="/jlyw/Dongle/files/ET299.cab#Version=1,00,0000"
        BORDER="0" VSPACE="0" HSPACE="0" ALIGN="TOP" HEIGHT="0" WIDTH="0">
    </object>-->
	</BODY>
</HTML>


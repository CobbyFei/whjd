package com.dbsoft.whjd.service.impl;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.enterprise.inject.New;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import com.dbsoft.whjd.dao.IBaseDao;
import com.dbsoft.whjd.model.SysUser;
import com.dbsoft.whjd.model.UserRole;
import com.dbsoft.whjd.pageModel.UserLoginPage;
import com.dbsoft.whjd.service.IUserLoginService;
import com.dbsoft.whjd.util.Encrypt;
import com.dbsoft.whjd.util.XMLUtils;
@Service("userLoginService") 
public class UserLoginServiceImpl implements IUserLoginService{
    private IBaseDao<SysUser> sysUserDao; 
    private IBaseDao<UserRole> userRoleDao;

	@Override
	public UserLoginPage checkUserLogin(UserLoginPage userLoginPage) {
		UserLoginPage res=new UserLoginPage();
		
		Date now_date=new Date();
		/*Date due_date=new Date(115,2,1,0,0,0);
		long due_date_time=due_date.getTime();
		long now_date_time=now_date.getTime();
		System.out.println(due_date_time);
		System.out.println(now_date_time);
		if(now_date_time>=due_date_time)
		{
			res.setSuccess(false);
    		res.setMsg("系统已经过期，请联系软件开发商");
    		return res;
		}*/
       
        //检测用户输入的用户名和密码是否正确
        String hql=" from SysUser as sys where sys.simplifyName=:userName and sys.status=0 ";
        Map<String, Object> tMap=new HashMap<String,Object>();
        tMap.put("userName", userLoginPage.getSimplifyName().trim().toUpperCase());
        List<SysUser> list=sysUserDao.find(hql, tMap);
        if(list!=null && list.size()>0)
        {
        	SysUser sysUser=list.get(0);
        	String pwd=sysUser.getPassword();
        	String enctype_pwd=Encrypt.md5(userLoginPage.getPwd().trim()).substring(0, 16);
        	if(!pwd.equals(enctype_pwd))
        	{
        		res.setSuccess(false);
        		res.setMsg("用户密码不正确");
        		return res;
        	}
        	else {
				res.setSimplifyName(sysUser.getSimplifyName());
				res.setUserName(sysUser.getUserName());
				if(sysUser.getInspectionStation()!=null)
				{
				   res.setStationName(sysUser.getInspectionStation().getStationName());
				   res.setStationId(sysUser.getInspectionStation().getStationId());
				}
				else {
					res.setStationName("市局");
					res.setStationId(0);
				}
			}
        }
        else {
			res.setMsg("该用户不存在！");
			res.setSuccess(false);
			return res;
		}
       
        //查询用户的角色
        tMap.clear();
        hql=" from UserRole as ur where ur.sysUser.simplifyName=:username and ur.relationStatus=1";
        tMap.put("username",userLoginPage.getSimplifyName());
        List<UserRole> ur_list=userRoleDao.find(hql, tMap);
        if(ur_list!=null && ur_list.size()>0)
        {
        	UserRole uRole=ur_list.get(0);
        	res.setUserRoleName(uRole.getRoles()!=null?uRole.getRoles().getRoleName():"未分配角色");
        	res.setUserRoleId(uRole.getRoles()!=null?uRole.getRoles().getRoleId():-1);
        }
        else {
			res.setSuccess(false);
			res.setMsg("加载用户权限失败！");
			return res;
		}
        
        //查询用户是否可以登录，具体的表现在查询呢资源文件的xml文件中的用户信息是否符合要求
        UserLoginPage resFromXML = null;
		try {
			resFromXML = XMLUtils.getInstance().readFromXML(userLoginPage.getSimplifyName().toUpperCase());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(resFromXML==null)
        {
        	//表示xml文件中没有记录，用户可以直接登录
        	userLoginPage.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        	userLoginPage.setLoginState("在线");
        	try {
				XMLUtils.getInstance().writeIntoXML(userLoginPage, 1);
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        else {
			if(resFromXML.getLoginState()!=null && resFromXML.getLoginState().equals("离线"))
			{
				//这种情况，用户也可以登录，修改登录时间
				userLoginPage.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	        	userLoginPage.setLoginState("在线");
	        	try {
					XMLUtils.getInstance().writeIntoXML(userLoginPage, 0);
				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				long time=System.currentTimeMillis();
				try {
					time = sd.parse(resFromXML.getRefreshTime()).getTime();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				long diff=(System.currentTimeMillis()-time)/1000;
				//正式上线后改为20（原120）
				if(diff<=20)
				{
					res.setMsg("该用户已经登录!");
					res.setSuccess(false);
					return res;
				}
				//正式上线后改为30（原180）
				else if(diff>30)
				{
					//系统已经在某种异常情况下推出系统三分钟，允许登录
					userLoginPage.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		        	userLoginPage.setLoginState("在线");
		        	try {
						XMLUtils.getInstance().writeIntoXML(userLoginPage, 0);
					} catch (DocumentException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else{
					res.setMsg("两次登录时间相隔太短，请稍后登录");
					res.setSuccess(false);
					return res;
				}
			}
		}
        res.setSuccess(true);
		return res;
	}
	
	
	public IBaseDao<SysUser> getSysUserDao() {
		return sysUserDao;
	}
	@Resource(name="baseDao")
	public void setSysUserDao(IBaseDao<SysUser> sysUserDao) {
		this.sysUserDao = sysUserDao;
	}


	public IBaseDao<UserRole> getUserRoleDao() {
		return userRoleDao;
	}

    @Resource(name="baseDao")
	public void setUserRoleDao(IBaseDao<UserRole> userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

   /**
    * 修改用户信息文件（用于发送心跳包）
    */
	@Override
	public UserLoginPage sendHeartsBeat(UserLoginPage userLoginPage) {
		UserLoginPage res=new UserLoginPage();
		
		try {
			res=XMLUtils.getInstance().readFromXML(userLoginPage.getSimplifyName().toUpperCase());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		res.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        res.setLoginState("在线");
    	try {
			XMLUtils.getInstance().writeIntoXML(res, 0);
			res.setSuccess(true);
		} catch (Exception e) {
			res.setSuccess(false);
			res.setMsg("修改用户登录信息文件出现异常");
			e.printStackTrace();
		} 
    	
		return res;
	}

/**
 * 用户退出系统
 */
@Override
public UserLoginPage logOut(UserLoginPage userLoginPage) {
	UserLoginPage res=new UserLoginPage();
	
	try {
		res=XMLUtils.getInstance().readFromXML(userLoginPage.getSimplifyName().toUpperCase());
		if(res!=null)
		{
		   res.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	       res.setLoginState("离线");
		}
		
	} catch (DocumentException e) {
		e.printStackTrace();
		res.setSuccess(false);
		res.setMsg("读取配置文件出现异常");
		return res;
	}
	
	
    try {
		XMLUtils.getInstance().writeIntoXML(res, 0);
	} catch (Exception e) {
		res.setSuccess(false);
		res.setMsg("退出系统发生异常");
		e.printStackTrace();
		return res;
	} 
    res.setSuccess(true);
	return res;
}
	

}

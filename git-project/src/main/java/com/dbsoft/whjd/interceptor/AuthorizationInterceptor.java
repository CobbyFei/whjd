package com.dbsoft.whjd.interceptor;
import java.util.Map;

import com.opensymphony.xwork2.interceptor.*;
import com.opensymphony.xwork2.*;

public class AuthorizationInterceptor implements Interceptor{
	private String excludeMethods;

   
    
	public String getExcludeMethods() {
		return excludeMethods;
	}

	public void setExcludeMethods(String excludeMethods) {
		this.excludeMethods = excludeMethods;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx=invocation.getInvocationContext();
		Map<String, Object> session=ctx.getSession();
		String simplifyName=(String)session.get("simplifyName");
		String currentMethod = invocation.getProxy().getMethod();
		Boolean ignore=false;
		String[] methods = excludeMethods.split(",");

	        for (String method : methods) {
	            if (currentMethod.matches(method.trim())) {
	                ignore = true;
	                break;
	            }
	        }
		if((simplifyName!=null && !simplifyName.equals(""))||ignore)
		{
			return invocation.invoke();
		}
		else {
			System.out.println("return");
			return Action.LOGIN;
		}
		
	}

}

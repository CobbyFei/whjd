package com.dbsoft.whjd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter extends HttpServlet implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest) req;        
         HttpServletResponse response = (HttpServletResponse) resp;        
         HttpSession session = request.getSession();        
         String url=request.getServletPath();  
         String contextPath=request.getContextPath(); 
         String simplifyName="";
         if(session.getAttribute("simplifyName")!=null)
         {
        	 simplifyName=session.getAttribute("simplifyName").toString();
         }
         System.out.println(simplifyName);
         System.out.println(url);
         System.out.println(contextPath);
         if(simplifyName==null || simplifyName.equals(""))
         {
        	if(!url.endsWith("index.jsp"))
        	{
	        	 response.sendRedirect(contextPath+"/index.jsp"); 
	        	 return;
        	}
         }
         filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

package com.softeem.dailysystem.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorityFilter implements Filter{

	String[] allowPages;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		//获取到初始化参数中的放行页面的集合
		allowPages = config.getInitParameter("allowPages").split(",");
	}

	//shiro
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
	
		//获取请求资源的地址
		String uri = request.getRequestURI();
		//获取最后一个“/”出现的位置
		int lastIndex = uri.lastIndexOf("/");
		//获取最终请求的资源名
		String srcName = uri.substring(lastIndex+1);
		System.out.println("请求资源:"+uri+"===="+srcName);
		
		//假设页面不允许被放行
		boolean isAllow = false;
		//判断请求的资源是否在放行列表中
		for (String page : allowPages) {
			if(Objects.equals(srcName, page)){
				//放行
				isAllow = true;
				break;
			}
		}
		//如果在则放行，如果不在则检查session中是否存在用户数据
		if(isAllow){
			chain.doFilter(request, response);
		}else{
			//检查session
			Object obj = request.getSession().getAttribute("stu");
			//如果session中存在用户数据则放行，否则拦截
			if(obj != null){
				chain.doFilter(request, response);				
			}else{
				request.setAttribute("msg", "请登录后再试!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}

	@Override
	public void destroy() {
		
	}

}

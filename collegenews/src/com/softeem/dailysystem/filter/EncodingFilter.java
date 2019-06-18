package com.softeem.dailysystem.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("*/*;charset=utf-8");
			
			HttpServletResponse response = (HttpServletResponse)resp;
			
			//百度:前端同源策略
			//设置服务端跨域请求
			response.setHeader("Access-Control-Allow-Origin", "*");
	        // 设置允许跨域访问的方法
	        response.setHeader("Access-Control-Allow-Methods","*");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
			
			//放行
			chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}

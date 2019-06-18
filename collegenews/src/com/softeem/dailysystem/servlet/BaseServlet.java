package com.softeem.dailysystem.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取需要执行的方法名称
		String methodName = request.getParameter("method");
		System.out.println(this+"====method-->"+methodName);
		try {
			//根据方法名称获取方法对象
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//执行方法
			Object obj = method.invoke(this, request,response);
			System.out.println("方法执行的返回值:"+obj);
			if(Objects.nonNull(obj)){
				//准备跳转(获取跳转目标)
				String target = obj.toString();
				String prefix = "redirect:";
				if(target.startsWith(prefix)){
					//重定向
					response.sendRedirect(target.substring(prefix.length())); 
				}else{
					//转发
					request.getRequestDispatcher(target).forward(request, response);
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
}

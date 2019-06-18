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
		//��ȡ��Ҫִ�еķ�������
		String methodName = request.getParameter("method");
		System.out.println(this+"====method-->"+methodName);
		try {
			//���ݷ������ƻ�ȡ��������
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//ִ�з���
			Object obj = method.invoke(this, request,response);
			System.out.println("����ִ�еķ���ֵ:"+obj);
			if(Objects.nonNull(obj)){
				//׼����ת(��ȡ��תĿ��)
				String target = obj.toString();
				String prefix = "redirect:";
				if(target.startsWith(prefix)){
					//�ض���
					response.sendRedirect(target.substring(prefix.length())); 
				}else{
					//ת��
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

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
		//��ȡ����ʼ�������еķ���ҳ��ļ���
		allowPages = config.getInitParameter("allowPages").split(",");
	}

	//shiro
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
	
		//��ȡ������Դ�ĵ�ַ
		String uri = request.getRequestURI();
		//��ȡ���һ����/�����ֵ�λ��
		int lastIndex = uri.lastIndexOf("/");
		//��ȡ�����������Դ��
		String srcName = uri.substring(lastIndex+1);
		System.out.println("������Դ:"+uri+"===="+srcName);
		
		//����ҳ�治��������
		boolean isAllow = false;
		//�ж��������Դ�Ƿ��ڷ����б���
		for (String page : allowPages) {
			if(Objects.equals(srcName, page)){
				//����
				isAllow = true;
				break;
			}
		}
		//���������У������������session���Ƿ�����û�����
		if(isAllow){
			chain.doFilter(request, response);
		}else{
			//���session
			Object obj = request.getSession().getAttribute("stu");
			//���session�д����û���������У���������
			if(obj != null){
				chain.doFilter(request, response);				
			}else{
				request.setAttribute("msg", "���¼������!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}

	@Override
	public void destroy() {
		
	}

}

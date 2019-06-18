package com.softeem.dailysystem.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.softeem.dailysystem.dao.impl.StudentDAO;
import com.softeem.dailysystem.dto.ServiceModel;
import com.softeem.dailysystem.dto.Student;
import com.softeem.dailysystem.service.StudentService;
import com.softeem.dailysystem.utils.DBUtils;
import com.softeem.encrypt.MD5;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet({ "/StudentServlet", "/stu" })
public class StudentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    


	/**
	 * 安全退出(注销)
	 * @param request
	 * @param response
	 * @throws IOException
	 */
//	public String validate(HttpServletRequest request,HttpServletResponse response){
//		//获取前端输入的验证码
//		String vcode = request.getParameter("vcode");
//		//获取session中缓存的验证码
//		String code = request.getSession().getAttribute("code").toString();
//		//不区分大小写比较两个字符串
//		if(vcode.equalsIgnoreCase(code)){
//			//验证通过
//			return "redirect:ok.jsp";
//		}
//		request.setAttribute("msg", "验证码错误");
//		//验证码错误
//		return "index.jsp";
//	}
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//将session失效
		request.getSession().invalidate();
		response.sendRedirect("login.jsp");
	}
	public void updatapwd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//将session失效
		  String oldpwd = request.getParameter("pwds");
		  String newpwd = request.getParameter("pwd");
		  Object obj = request.getSession().getAttribute("stu");
    	  Student stu = (Student)obj;
		    ServiceModel  model = new ServiceModel();
		     StudentService  ss = new StudentService();
		            stu.setPassword(oldpwd);
		       ServiceModel m =  ss.updatapwd(stu);
		       System.out.println("1111");
		      if(m.getCode()==1){		    	  
		    	  stu.setPassword(new MD5().getMD5ofStr(newpwd));
		    	  stu.setId(stu.getId());
		    	   boolean f =  new StudentDAO(DBUtils.getConn()).update(stu);
		    	   System.out.println("2222");
		    	    if(f){
		    	    	request.setAttribute("msg", "修改成功");
					    request.getRequestDispatcher("user_update.jsp").forward(request, response);
		    	    }
		      }
		      else{
		    	  System.out.println("3333");
		    	  request.setAttribute("msg", m.getMessage());
			      request.getRequestDispatcher("user_update.jsp").forward(request, response);
		      }
		
	}


	public void del(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("删除");
	}

	/**
	 * 注册
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public  void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String sex = request.getParameter("sex");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("pwd");
		
		Student stu  = new Student();
		stu.setName(name);
		stu.setSex(sex);
		stu.setPhone(phone);
		stu.setPassword(new MD5().getMD5ofStr(pwd));
		
		System.out.println(stu);
		
		StudentService service = new StudentService();
		ServiceModel model = service.register(stu);
		if(model.getCode() == 1){
			request.setAttribute("phone", phone);
			request.setAttribute("pwd", pwd);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			
			request.setAttribute("msg", model.getMessage());
			request.getRequestDispatcher("reg.jsp").forward(request, response);
		}
	}

	
	public  void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("pwd");
		
		String vcode = request.getParameter("vcode");
		//获取session中缓存的验证码
		String code = request.getSession().getAttribute("code").toString();		
		Student stu = new Student();
		stu.setPhone(phone);
		stu.setPassword(new MD5().getMD5ofStr(pwd));
		StudentService service = new StudentService();
		ServiceModel model = service.login(stu);
		if(vcode.equalsIgnoreCase(code)){
			//验证通过
			if(model.getCode() == 1){
				//将当前学生信息缓存到session中
				request.getSession().setAttribute("stu", model.getData());
				response.sendRedirect("user_main.jsp");
				System.out.println("+++"+ model.getData());
			}else{
				request.setAttribute("msg", model.getMessage());
				request.getRequestDispatcher("login.jsp").forward(request, response);
				System.out.println("1111"+ model.getMessage());
			}
			
		}else{
			request.setAttribute("msg", "验证码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			System.out.println("验证码错误");
		}
	}
		public  void fplogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			String phone = request.getParameter("phone");
			String pwd = request.getParameter("pwd");
			
			String vcode = request.getParameter("vcode");
			//获取session中缓存的验证码
			String code = request.getSession().getAttribute("code").toString();		
			Student stu = new Student();
			stu.setPhone(phone);
			stu.setPassword(new MD5().getMD5ofStr(pwd));
			StudentService service = new StudentService();
			ServiceModel model = service.login(stu);
			if(vcode.equalsIgnoreCase(code)){
				//验证通过
				if(model.getCode() == 1){
					//将当前学生信息缓存到session中
					request.getSession().setAttribute("stu", model.getData());
					response.sendRedirect("news?method=findMynews");
					System.out.println("+++"+ model.getData());
				}else{
					request.setAttribute("msg", model.getMessage());
					request.getRequestDispatcher("fp/login.jsp").forward(request, response);
					System.out.println("1111"+ model.getMessage());
				}
				
			}else{
				request.setAttribute("msg", "验证码错误");
				request.getRequestDispatcher("fp/login.jsp").forward(request, response);
				System.out.println("验证码错误");
			}
	
		
	}

}

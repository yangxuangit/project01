package com.softeem.dailysystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.dailysystem.dao.impl.DailyDAO;
import com.softeem.dailysystem.dto.Daily;
import com.softeem.dailysystem.dto.ServiceModel;
import com.softeem.dailysystem.dto.Student;
import com.softeem.dailysystem.service.DailyService;
import com.softeem.dailysystem.utils.DBUtils;

@WebServlet("/daily")
public class DailyServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求编码
		request.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		switch(method){
		case "submit":
			submit(request,response);
			break;
		case "findMyDaily":
			findMyDaily(request,response);
			break;
		case "update":
			update(request,response);
			break;
		case "del":
			del(request,response);
			break;
		case "volumeDel":
			volumeDel(request,response);
			break;
		}
	}

	/**
	 * 批量删除日报
	 * @param request
	 * @param response
	 * @throws ServletException 
	 * @throws IOException 
	 */
	private void volumeDel(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String ids = request.getParameter("ids");
		String[] id = ids.split(",");
		DailyService service = new DailyService();
		ServiceModel model = service.delByIds(id);
		request.setAttribute("msg", model.getMessage());
		findMyDaily(request, response);
	}

	/**
	 * 修改日报
	 * @param request
	 * @param response
	 * @throws ServletException 
	 * @throws IOException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("dailyid");
		String tt = request.getParameter("todaytask");
		String ci = request.getParameter("completeinfo");
		String np = request.getParameter("nextplan");
		
		Daily d = new Daily();
		d.setId(id);
		d.setTodaytask(tt);
		d.setCompleteinfo(ci);
		d.setNextplan(np);
		
		DailyService service = new DailyService();
		ServiceModel model = service.updateDaily(d);
		request.setAttribute("msg", model.getMessage());
		findMyDaily(request, response);
	}

	/**
	 * 根据id删除日报
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void del(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("id");
		DailyService service = new DailyService();
		ServiceModel model = service.delById(id);
		request.setAttribute("msg", model.getMessage());
		findMyDaily(request, response);
	}

	/**
	 * 查询当前登陆用户的日报
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findMyDaily(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//初始化每页数据条数，以及当前页
		int pageSize = 5;		//每页大小
		int currentPage = 1;	//当前页
		int totalNum = 0;		//总记录数
		int totalPage = 4;		//总页数
		
		//获取session中的学生信息
		Object obj = request.getSession().getAttribute("stu");
		if(obj == null){
			response.sendRedirect("login.jsp");
			return;
		}
		Student stu = (Student)obj;
		
		/*分页操作*/
		String cp = request.getParameter("cp");
		currentPage = cp != null ? Integer.parseInt(cp) : 1;
		
		//防止无限上一页
		if(currentPage < 1){
			currentPage = 1;
		}
		
		//得到当前登录学生的总数据条数
		DailyDAO dao = new DailyDAO(DBUtils.getConn());
		totalNum = dao.getDailyCountBySid(stu.getId());
		
		//计算获取总页数
		if(totalNum % pageSize == 0){
			totalPage = totalNum / pageSize;
		}else{
			totalPage = totalNum / pageSize + 1;
		}
		
		//防止无限下一页
		if(currentPage > totalPage){
			currentPage = totalPage;
		}
		
		DailyService service = new DailyService();
		ServiceModel model = service.queryBySid(stu.getId(),pageSize, currentPage);
		request.getSession().setAttribute("dailys", model.getData());
		//将当前页码值传递到下一个页面
		request.getSession().setAttribute("cp", currentPage);
		//将当总页码传递到下一个页面
		request.getSession().setAttribute("tp", totalPage);
		request.getRequestDispatcher("main.jsp").forward(request, response);
        
	}

	private void submit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String tt = request.getParameter("todaytask");
		String ci = request.getParameter("completeinfo");
		String np = request.getParameter("nextplan");
		
		Object obj = request.getSession().getAttribute("stu");
		Student stu = (Student)obj;
		
		Daily d = new Daily();
		d.setTodaytask(tt);
		d.setCompleteinfo(ci);
		d.setNextplan(np);
		d.setSid(stu.getId());
		
		DailyService service = new DailyService();
		ServiceModel model = service.submitDaily(d);
		
		request.setAttribute("msg", model.getMessage());
		//调用查询方法跳转并传递数据到主页
		findMyDaily(request, response);
	}
	
}

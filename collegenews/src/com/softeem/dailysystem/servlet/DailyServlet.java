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
		//�����������
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
	 * ����ɾ���ձ�
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
	 * �޸��ձ�
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
	 * ����idɾ���ձ�
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
	 * ��ѯ��ǰ��½�û����ձ�
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findMyDaily(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//��ʼ��ÿҳ�����������Լ���ǰҳ
		int pageSize = 5;		//ÿҳ��С
		int currentPage = 1;	//��ǰҳ
		int totalNum = 0;		//�ܼ�¼��
		int totalPage = 4;		//��ҳ��
		
		//��ȡsession�е�ѧ����Ϣ
		Object obj = request.getSession().getAttribute("stu");
		if(obj == null){
			response.sendRedirect("login.jsp");
			return;
		}
		Student stu = (Student)obj;
		
		/*��ҳ����*/
		String cp = request.getParameter("cp");
		currentPage = cp != null ? Integer.parseInt(cp) : 1;
		
		//��ֹ������һҳ
		if(currentPage < 1){
			currentPage = 1;
		}
		
		//�õ���ǰ��¼ѧ��������������
		DailyDAO dao = new DailyDAO(DBUtils.getConn());
		totalNum = dao.getDailyCountBySid(stu.getId());
		
		//�����ȡ��ҳ��
		if(totalNum % pageSize == 0){
			totalPage = totalNum / pageSize;
		}else{
			totalPage = totalNum / pageSize + 1;
		}
		
		//��ֹ������һҳ
		if(currentPage > totalPage){
			currentPage = totalPage;
		}
		
		DailyService service = new DailyService();
		ServiceModel model = service.queryBySid(stu.getId(),pageSize, currentPage);
		request.getSession().setAttribute("dailys", model.getData());
		//����ǰҳ��ֵ���ݵ���һ��ҳ��
		request.getSession().setAttribute("cp", currentPage);
		//������ҳ�봫�ݵ���һ��ҳ��
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
		//���ò�ѯ������ת���������ݵ���ҳ
		findMyDaily(request, response);
	}
	
}

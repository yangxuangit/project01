package com.softeem.dailysystem.servlet;

import java.io.File;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSON;
import com.softeem.dailysystem.dao.impl.NewsDAO;
import com.softeem.dailysystem.dao.impl.voteDAO;
import com.softeem.dailysystem.dto.NsNews;
import com.softeem.dailysystem.dto.Student;
import com.softeem.dailysystem.dto.Votenews;
import com.softeem.dailysystem.service.NewsService;
import com.softeem.dailysystem.utils.PageUtils;
@MultipartConfig
@WebServlet("/news")
public class NewsSerlet  extends BaseServlet{
	static HttpServletRequest req =null;
	static HttpServletResponse resp = null;
	public   void findMynews(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		   int pagesize = 5;
		   int currentpage = 1;
		   System.out.println("--------------");
		   Object obj = req.getSession().getAttribute("stu");
		   Student stu = (Student)obj;		   
		   String cp = req.getParameter("cp");
		   currentpage = cp != null ? Integer.parseInt(cp) : 1;
		   PageUtils<NsNews> pu = new NewsService().query(stu.getId(), currentpage, pagesize);

		   System.out.println(pu+"88888");

		   req.getSession().setAttribute("pu", pu);
		      
	    	req.getRequestDispatcher("fp/index.jsp").forward(req, resp);
//		   resp.sendRedirect("test1.jsp");
//		     for(NsNews nn:pu){		    	 
//		    	 System.out.println(nn.getImgpath());
//                   break;		    	 
//		     }

		   
	}
	public  void  findAllMynews(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		   int pagesize = 7;
		   int currentpage = 1;	   
		   String cp = req.getParameter("cp");		   
		   currentpage = cp != null ? Integer.parseInt(cp) : 1;
		   System.out.println(cp);
		   PageUtils<Votenews>  pu = new NewsService().queryAll(currentpage, pagesize);
		   List<Votenews> list  = new voteDAO().findAll(pu.getCurrentPage(), pu.getPageSize());
		   req.setAttribute("pu", pu);
		   System.out.println("55555"+pu);
			   req.getRequestDispatcher("test.jsp").forward(req, resp);		 			   
		   
	}
	public  void  fpfindAllMynews(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		   int pagesize = 5;
		   int currentpage = 1;	            
		   String cid = req.getParameter("cid");
		   String cp = req.getParameter("cp");		   
		   currentpage = cp != null ? Integer.parseInt(cp) : 1;
		   System.out.println(cp);
		   PageUtils<Votenews>  pu = new NewsService().querybycid(Integer.parseInt(cid),currentpage, pagesize);	   
		   req.setAttribute("pu", pu);
		   System.out.println("55555"+pu);    
		   PrintWriter  out =   resp.getWriter();
		   out.print(pu);
//		   req.getRequestDispatcher("fp/index2.html").forward(req, resp);		 			   
		   out.flush();
//		   resp.sendRedirect("fp/index2.html");
		   
	}
	public  void  fpfindAllMynews2(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		   int pagesize = 5;
		   int currentpage = 1;	            
		   String cid = req.getParameter("cid");
		   String cp = req.getParameter("cp");		   
		   currentpage = cp != null ? Integer.parseInt(cp) : 1;
		   System.out.println(cp);
		   PageUtils<Votenews>  pu = new NewsService().querybycid(Integer.parseInt(cid),currentpage, pagesize);	   
		   req.setAttribute("pu", pu);
		   System.out.println("55555"+pu);    
		   req.getRequestDispatcher("fp/index2.jsp").forward(req, resp);		 			   
		   
	}
	public  void  fpfindAllMynews3(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		   int pagesize = 5;
		   int currentpage = 1;	            
		   String cid = req.getParameter("cid");
		   String cp = req.getParameter("cp");		   
		   currentpage = cp != null ? Integer.parseInt(cp) : 1;
		   System.out.println(cp);
		   PageUtils<Votenews>  pu = new NewsService().querybycid(Integer.parseInt(cid),currentpage, pagesize);	   
		   req.setAttribute("pu", pu);
		   System.out.println("55555"+pu);    
		   req.getRequestDispatcher("fp/index3.jsp").forward(req, resp);		 			   
		   
	}public  void  fpfindAllMynews4(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		   int pagesize = 5;
		   int currentpage = 1;	            
		   String cid = req.getParameter("cid");
		   String cp = req.getParameter("cp");		   
		   currentpage = cp != null ? Integer.parseInt(cp) : 1;
		   System.out.println(cp);
		   PageUtils<Votenews>  pu = new NewsService().querybycid(Integer.parseInt(cid),currentpage, pagesize);	   
		   req.setAttribute("pu", pu);
		   System.out.println("55555"+pu);    
		   req.getRequestDispatcher("fp/index4.jsp").forward(req, resp);		 			   
		   
	}
	public  void  fpfindMynews(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		   int pagesize = 5;
		   int currentpage = 1;	   
		   String cp = req.getParameter("cp");		   
		   currentpage = cp != null ? Integer.parseInt(cp) : 1;
		   System.out.println(cp);
		   PageUtils<Votenews>  pu = new NewsService().queryAll(currentpage, pagesize);

		   req.setAttribute("pu", pu);
		   System.out.println("55555"+pu);
		      
		       PrintWriter  out =   resp.getWriter();
		       out.print(pu);
		       out.flush();
//			   req.getRequestDispatcher("test.jsp").forward(req, resp);		 			   
        
		   
	}
	public void  insertNews(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		String cid = req.getParameter("cid");
          Object   obj=   req.getSession().getAttribute("stu");
		  Student stu = (Student) obj;
		  
		  Part part = req.getPart("imgpath");
		  String fname = part.getSubmittedFileName();
		  String base = "d:/file";
		  File dir = new File(base+"\\"+stu.getName());
		    //当目录不存在时，创建
			if(!dir.exists()){
				dir.mkdirs();
			}
			part.write(dir+File.separator+fname);
			String imgpath = stu.getName()+File.separator+fname;			
		      NsNews  ns = new NsNews();
		      ns.setAid(stu.getId());
		      ns.setSubject(subject);
		      ns.setContent(content);
		      ns.setImgpath(imgpath);
		      ns.setCid(Integer.parseInt(cid));
		      System.out.println(ns+"55555555");
		      NewsDAO dao =   new NewsDAO();
		       boolean f =  dao.insert(ns);
		       if(f){
		    	   req.setAttribute("msg", "添加成功");
		    	   req.getRequestDispatcher("newsAdd.jsp").forward(req, resp);
		       }else{
		    	   req.setAttribute("msg", "添加失败");
		    	   req.getRequestDispatcher("newsAdd.jsp").forward(req, resp);		    	   
		       }
	}
//	public static void main(String[] args) {
//		
//		System.out.println(NewsSerlet.findMynews(req, resp) );
//	}
}

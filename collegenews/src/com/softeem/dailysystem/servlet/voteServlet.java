package com.softeem.dailysystem.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.dailysystem.dao.impl.voteDAO;
import com.softeem.dailysystem.dao.impl.votecountDAO;
import com.softeem.dailysystem.dto.ServiceModel;
import com.softeem.dailysystem.dto.Votenews;
import com.softeem.dailysystem.dto.Voteoptions;
import com.softeem.dailysystem.service.VoteService;
import com.softeem.dailysystem.utils.DBUtils;
import com.softeem.dailysystem.utils.PageUtils;

@WebServlet("/votenews")
public class voteServlet extends BaseServlet{
	public void insertVote(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		  String  newname = req.getParameter("newname");
		  String  summary = req.getParameter("summary");
		      Votenews vote =   new Votenews();
		      vote.setSname(newname);
		      vote.setSummary(summary);
		      System.out.println("22"+vote);
		        boolean f = new voteDAO().insert(vote);
		        if(f){
		        	req.setAttribute("msg", "投票成功");
		        	req.getRequestDispatcher("vote.jsp").forward(req, resp);
		        }else{
		        	req.setAttribute("msg", "投票失败");
		        	req.getRequestDispatcher("vote.jsp").forward(req, resp);
		        }
		  
		
	}
	public String querynews(HttpServletRequest req,HttpServletResponse resp){
		   int pagesize = 6;
		   int currentpage = 1;
		   int totalnumber = 0;
		   totalnumber = new voteDAO().totalNumber();
		   String cp = req.getParameter("cp");
           currentpage = cp!=null?Integer.parseInt(cp):1;           
		   PageUtils<Votenews> pu =  new PageUtils<>(currentpage, pagesize, totalnumber);
		     List<Votenews>  list =  new voteDAO().findAll(pu.getCurrentPage(), pu.getPageSize());
		   pu.setList(list);
		   req.setAttribute("pu", pu);
		   System.out.println("55"+pu);
		   if(DBUtils.getConn()!=null){
		    	 try {
					DBUtils.getConn().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     }
		   return "voteList.jsp";
		
	}
	public String fpquerynews(HttpServletRequest req,HttpServletResponse resp){
		   int pagesize = 6;
		   int currentpage = 1;
		   int totalnumber = 0;
		   totalnumber = new voteDAO().totalNumber();
		   String cp = req.getParameter("cp");
        currentpage = cp!=null?Integer.parseInt(cp):1;           
		   PageUtils<Votenews> pu =  new PageUtils<>(currentpage, pagesize, totalnumber);
		     List<Votenews>  list =  new voteDAO().findAll(pu.getCurrentPage(), pu.getPageSize());
		   pu.setList(list);
		   req.setAttribute("pu", pu);
		   System.out.println("55"+pu);
		   if(DBUtils.getConn()!=null){
		    	 try {
					DBUtils.getConn().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     }
		   return "fp/voteList.jsp";
		
	}
	public String queryVotecount(HttpServletRequest req,HttpServletResponse resp){
		       ResultSet rs = null;
		        String sid =  req.getParameter("sid");
		     List<Voteoptions>  options  = new votecountDAO().findAllbysid(Integer.parseInt(sid));
		     List<Votenews>  news =  new voteDAO().findbysid(Integer.parseInt(sid));
		     int v=0;
		     int sum = 0;
		     for(Voteoptions vot:options){
		    	   v = vot.getVotecount();
		    	   sum = sum + v;
		     }	     	     
		     System.out.println(sum);
		     System.out.println(options);
		      req.setAttribute("v", sum);
		     req.setAttribute("options", options);
		     req.setAttribute("subject", news);
		     
		     
		return "voteResult.jsp";
	}
		public void fpinsertVote(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
			  String  newname = req.getParameter("newname");
			  String  summary = req.getParameter("summary");
			      Votenews vote =   new Votenews();
			      vote.setSname(newname);
			      vote.setSummary(summary);
			      System.out.println("22"+vote);
			        boolean f = new voteDAO().insert(vote);
			        if(f){
			        	req.setAttribute("msg", "投票成功");
			        	req.getRequestDispatcher("vote.jsp").forward(req, resp);
			        }else{
			        	req.setAttribute("msg", "投票失败");
			        	req.getRequestDispatcher("vote.jsp").forward(req, resp);
			        }
			  
			
		}
		public String fpqueryVotecount(HttpServletRequest req,HttpServletResponse resp){
		       ResultSet rs = null;
		        String sid =  req.getParameter("sid");
		     List<Voteoptions>  options  = new votecountDAO().findAllbysid(Integer.parseInt(sid));
		     List<Votenews>  news =  new voteDAO().findbysid(Integer.parseInt(sid));
		     int v=0;
		     int sum = 0;
		     for(Voteoptions vot:options){
		    	   v = vot.getVotecount();
		    	   sum = sum + v;
		     }	     	     
		     System.out.println(sum);
		     System.out.println(options);
		      req.setAttribute("v", sum);
		     req.setAttribute("options", options);
		     req.setAttribute("subject", news);
		     
		     
		return "fp/voteResult.jsp";
	} 
		public void Vote(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
			  String  oid = req.getParameter("options");
			  System.out.println("--------"+oid);
                 Connection   conn = null;    
                   conn = DBUtils.getConn(); 
                 int  oid1 = Integer.parseInt(oid);
                 ServiceModel model =  new VoteService().updatevote(oid1);
                 if(model.getCode()==1){
//                	 System.out.println(model.getMessage());
                	 req.setAttribute("msg", model.getMessage());
//                	 req.getRequestDispatcher("fp/voteList.jsp").forward(req, resp);
                	 resp.sendRedirect("votenews?method=fpquerynews");
                 }
                 try {
					conn.close();
				} 
                 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  			
		}		
}

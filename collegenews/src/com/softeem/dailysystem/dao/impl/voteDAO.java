package com.softeem.dailysystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import com.softeem.dailysystem.dao.BaseDAO;
import com.softeem.dailysystem.dto.Votenews;
import com.softeem.dailysystem.utils.DBUtils;

public class voteDAO implements BaseDAO<Votenews>{
        
	    
	@Override
	public boolean insert(Votenews t) {
		String sql="insert into votenews(sname,summary) value(?,?)";
		return DBUtils.exeUpdate(DBUtils.getConn(), sql, t.getSname(),t.getSummary());
	}


	@Override
	public boolean delete(Votenews t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Votenews t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Votenews findOne(Votenews t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Votenews> findAll(int currentPage,int pageSize) {
		  String sql = "select * from votenews limit ?,?";
		  return DBUtils.queryList(Votenews.class, sql,(currentPage-1)*pageSize,pageSize);
	}
	public List<Votenews> find() {
		  String sql = "select * from votenews";
		  return DBUtils.queryList(Votenews.class, sql);
	}
	public List<Votenews> findbysid(int sid) {
		  String sql = "select * from votenews where sid=?";
		  return DBUtils.queryList(Votenews.class, sql,sid);
	}
    public int totalNumber(){
    	  Connection conn = null;
    	  ResultSet rs = null;
    	  PreparedStatement ps = null;
    	  String sql = "select count(sid) from votenews";
    	  try {
    		      
    		  conn = DBUtils.getConn();
                 ps =  DBUtils.getConn().prepareStatement(sql);			     
			       rs = ps.executeQuery();
			       while(rs.next()){
			    	   return rs.getInt(1);
			       }
		} catch (SQLException e) {			
			e.printStackTrace();
		}  finally{
			if(rs!=null){
				try {
				    rs.close();					   
				    DBUtils.close(ps, conn);
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
    return -1;
    	
    }
//	public static void main(String[] args) {
//		   Votenews  v =   new Votenews();
//		   v.setSname("第二次插入");
//		   v.setSummary("非常好");
//		   System.out.println(new voteDAO().find());
////		   System.out.println(new voteDAO().insert(v));
//		   System.out.println(new voteDAO().totalNumber());
//	}
}

package com.softeem.dailysystem.dao.impl;

import java.sql.Connection;
import java.util.List;


import com.softeem.dailysystem.dao.BaseDAO;

import com.softeem.dailysystem.dto.Voteoptions;
import com.softeem.dailysystem.utils.DBUtils;
  
public class votecountDAO implements BaseDAO<Voteoptions>{
       private Connection conn = null;
	@Override
	public boolean insert(Voteoptions t) {
        
		return false;
	}

//	public votecountDAO(Connection conn) {
//		this.conn = conn;
//	}

	@Override
	public boolean delete(Voteoptions t) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean updateoptions(Voteoptions t,Connection conn) {
		  String sql = "update voteoptions set votecount=? where oid=? "; 
		return DBUtils.exeUpdate(conn, sql, t.getVotecount(),t.getOid());
	}

	public List<Voteoptions> findAll() {
		  String sql = "select * from voteoptions";		  
		  return DBUtils.queryList(Voteoptions.class, sql);
	}
	public List<Voteoptions> findAllbysid(int sid) {
		  
		  String sql = "select * from voteoptions where sid =?";
		  return DBUtils.queryList(Voteoptions.class, sql,sid);
	}
	public List<Voteoptions> findAllbyoid(int oid) {
		  
		  String sql = "select * from voteoptions where oid =?";
		  return DBUtils.queryList(Voteoptions.class, sql,oid);
	}

	@Override
	public Voteoptions findOne(Voteoptions t) {
		// TODO Auto-generated method stub
		return null;
	}
//	public static void main(String[] args) {
////		   Votenews  v =   new Votenews();
////		   v.setSname("第二次插入");
////		   v.setSummary("非常好");
//		      Voteoptions t = new Voteoptions();
//		        t.setVotecount(33);
//		        t.setOid(54);
//		    boolean v  =   new votecountDAO(DBUtils.getConn()).update(t) ;
////		   System.out.println(new votecountDAO().findAll());
//		   System.out.println(v);
////		   System.out.println(new voteDAO().totalNumber());
//	}

	@Override
	public List<Voteoptions> findAll(int pageSize, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

@Override
public boolean update(Voteoptions t) {
	// TODO Auto-generated method stub
	return false;
}



}

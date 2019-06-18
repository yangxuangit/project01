package com.softeem.dailysystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.softeem.dailysystem.dao.BaseDAO;
import com.softeem.dailysystem.dto.Daily;
import com.softeem.dailysystem.dto.NsNews;
import com.softeem.dailysystem.utils.DBUtils;

public class NewsDAO implements BaseDAO<NsNews>{
     
	@Override
	public boolean insert(NsNews t) {
		String sql = "insert into ns_news(subject,content,imgpath,aid,cid) values(?,?,?,?,?)";
		return DBUtils.exeUpdate(DBUtils.getConn(), sql, t.getSubject(),t.getContent(),t.getImgpath(),t.getAid(),t.getCid());
	}
//	public static void main(String[] args) {	   
//	      List<NsNews> list =  new NewsDAO().findAllbyaid(19,1,5);
//	       for(NsNews s : list){		    	   
//	    	   System.out.println("==="+list);
//	       }
//		NsNews nn = new NsNews();
//		nn.setSubject("55555");
//		nn.setContent("%西安%");
//		nn.setImgpath("777");
//		nn.setAid(5);
//	    int t = new NewsDAO().findAllTotalNum();
//	    System.out.println(new NewsDAO().findAllbycid(1,1,5));
//}
	public List<NsNews> findAllbycid(int cid,int currentPage,int pageSize) {
		String sql = "select * from ns_news where cid=? limit ?,?";		
		return DBUtils.queryList(NsNews.class, sql, cid,(currentPage-1)*pageSize,pageSize);
	}
	@Override
	public boolean delete(NsNews t) {
		// TODO Auto-generated method stub
		return false;
	}
  
	@Override
	public boolean update(NsNews t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public NsNews findOne(NsNews aid) {
		return null;
	}
	public List<NsNews> findAllbyaid(int aid,int currentPage,int pageSize) {
		String sql = "select * from ns_news where aid=? limit ?,?";		
		return DBUtils.queryList(NsNews.class, sql, aid,(currentPage-1)*pageSize,pageSize);
	}
	
	/**
	 * 查询总记录条数
	 * @return
	 */
	public int findTotalNum(int aid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement("select count(nid) from ns_news where aid=?");
			ps.setInt(1, aid);
			rs = ps.executeQuery();
			if(rs.next()){
			 return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();
				}
				DBUtils.close( ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	public int findTotalNumbycid(int cid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement("select count(nid) from ns_news where cid=?");
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			if(rs.next()){
			 return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();
				}
				DBUtils.close( ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	public int findAllTotalNum( ){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement("select count(nid) from ns_news ");
			rs = ps.executeQuery();
			if(rs.next()){
			 return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();
				}
				DBUtils.close(ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	


	@Override
	public List<NsNews> findAll( int currentPage,int pageSize) {
		String sql = "select * from ns_news  limit ?,?";
		return DBUtils.queryList(NsNews.class, sql, (currentPage-1)*pageSize,pageSize);
	}

}

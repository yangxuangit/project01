package com.softeem.dailysystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.softeem.dailysystem.dao.BaseDAO;
import com.softeem.dailysystem.dto.Daily;
import com.softeem.dailysystem.utils.DBUtils;

public class DailyDAO implements BaseDAO<Daily> {

	Connection conn;
	
	public DailyDAO(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public boolean insert(Daily t) {
		String sql = "insert into daily(id,todaytask,completeinfo,nextplan,subtime,sid) values(?,?,?,?,now(),?)";
		return DBUtils.exeUpdate(conn, 
				sql, 
				t.getId(),
				t.getTodaytask(),
				t.getCompleteinfo(),
				t.getNextplan(),
				t.getSid());
	}

	@Override
	public boolean delete(Daily t) {
		String sql = "delete from daily where id=?";
		return DBUtils.exeUpdate(conn, sql, t.getId());
	}
	
	public boolean volumeDelByIds(String[] ids){
		//声明变量用于存储“？”占位符
		String placeholder = "";
		for(int i = 0;i<ids.length;i++){
			placeholder += "?,";
		}
		//?,?,?,
		placeholder = placeholder.substring(0, placeholder.length()-1);
		String sql = "delete from daily where id in("+placeholder+")";
		return DBUtils.exeUpdate(conn, sql,ids);
	}

	@Override
	public boolean update(Daily t) {
		String sql = "update daily set todaytask=?,completeinfo=?,nextplan=? where id=?";
		return DBUtils.exeUpdate(conn, 
				sql, 
				t.getTodaytask(),
				t.getCompleteinfo(),
				t.getNextplan(),
				t.getId());
	}

	@Override
	public Daily findOne(Daily t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Daily> findAll(int pageSize, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 根据学号查询当前学生提交的日报信息
	 * @param sid	学号
	 * @param pageSize	每页大小
	 * @param currentPage	当前页
	 * @return
	 */
	public List<Daily> findBySid(int sid,int pageSize,int currentPage){
		String sql = "select id,todaytask,completeinfo,nextplan,subtime,lastupdatetime,sid from daily where sid=? limit ?,?";
		return DBUtils.queryList(Daily.class, sql, sid,(currentPage-1)*pageSize,pageSize);
	}
	
	/**
	 * 根据学号查询指定学生提交的日报总数
	 * @param sid
	 * @return
	 */
	public int getDailyCountBySid(int sid){
		int totalNum = 0;
		String sql = "select count(id) from daily where sid=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sid);
			rs = ps.executeQuery();
			if(rs.next()){
				totalNum = rs.getInt(1);
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
		return totalNum;
	}

}

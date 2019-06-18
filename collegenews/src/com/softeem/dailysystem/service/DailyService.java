package com.softeem.dailysystem.service;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import com.softeem.dailysystem.dao.impl.DailyDAO;
import com.softeem.dailysystem.dto.Daily;
import com.softeem.dailysystem.dto.ServiceModel;
import com.softeem.dailysystem.utils.DBUtils;

public class DailyService {

	private DailyDAO dao;
	private Connection conn;
	
	public ServiceModel submitDaily(Daily daily){
		ServiceModel model = new ServiceModel();
		try {
			//1.检查当日是否已提交
			//若已提交过，拒绝提交
			//若未提交，则允许
			conn = DBUtils.getConn();
			dao =  new DailyDAO(conn);
			//生成并为当前日报设置id
			daily.setId(UUID.randomUUID().toString());
			if(dao.insert(daily)){
				model.setCode(1);
				model.setMessage("提交成功");
			}else{
				model.setCode(-1);
				model.setMessage("提交失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return model;
	}
	
	/**
	 * 查询当前登录学生提交的日报
	 * @param sid
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public ServiceModel queryBySid(int sid,int pageSize,int currentPage){
		ServiceModel model = new ServiceModel();
		dao = new DailyDAO(null);
		List<Daily> list = dao.findBySid(sid, pageSize, currentPage);
		if(list != null && list.size()>0){
			model.setData(list);
		}
		return model;
	}
	
	/**
	 * 修改日报
	 * @param d
	 * @return
	 */
	public ServiceModel updateDaily(Daily d){
		ServiceModel model = new ServiceModel();
		try {
			conn = DBUtils.getConn();
			dao = new DailyDAO(conn);
			boolean f = dao.update(d);
			if(f){
				model.setCode(1);
				model.setMessage("修改成功");
			}else{
				model.setCode(-1);
				model.setMessage("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return model;
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public ServiceModel delByIds(String[] ids){
		ServiceModel model = new ServiceModel();
		
		try {
			conn = DBUtils.getConn();
			dao = new DailyDAO(conn);
			boolean f = dao.volumeDelByIds(ids);
			if(f){
				model.setCode(1);
				model.setMessage("删除成功");
			}else{
				model.setCode(-1);
				model.setMessage("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return model;
	}
	
	/**
	 * 根据id删除日报
	 * @param id
	 * @return
	 */
	public ServiceModel delById(String id){
		ServiceModel model = new ServiceModel();
		try {
			conn = DBUtils.getConn();
			dao = new DailyDAO(conn);
			
			Daily d = new Daily();
			d.setId(id);
			
			boolean f = dao.delete(d);
			if(f){
				model.setCode(1);
				model.setMessage("删除成功");
			}else{
				model.setCode(-1);
				model.setMessage("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return model;
		
	}
}

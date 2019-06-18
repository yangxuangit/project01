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
			//1.��鵱���Ƿ����ύ
			//�����ύ�����ܾ��ύ
			//��δ�ύ��������
			conn = DBUtils.getConn();
			dao =  new DailyDAO(conn);
			//���ɲ�Ϊ��ǰ�ձ�����id
			daily.setId(UUID.randomUUID().toString());
			if(dao.insert(daily)){
				model.setCode(1);
				model.setMessage("�ύ�ɹ�");
			}else{
				model.setCode(-1);
				model.setMessage("�ύʧ��");
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
	 * ��ѯ��ǰ��¼ѧ���ύ���ձ�
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
	 * �޸��ձ�
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
				model.setMessage("�޸ĳɹ�");
			}else{
				model.setCode(-1);
				model.setMessage("�޸�ʧ��");
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
	 * ����ɾ��
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
				model.setMessage("ɾ���ɹ�");
			}else{
				model.setCode(-1);
				model.setMessage("ɾ��ʧ��");
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
	 * ����idɾ���ձ�
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
				model.setMessage("ɾ���ɹ�");
			}else{
				model.setCode(-1);
				model.setMessage("ɾ��ʧ��");
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

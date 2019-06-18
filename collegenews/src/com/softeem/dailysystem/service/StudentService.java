package com.softeem.dailysystem.service;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.Objects;

import com.softeem.dailysystem.dao.impl.StudentDAO;
import com.softeem.dailysystem.dto.ServiceModel;
import com.softeem.dailysystem.dto.Student;
import com.softeem.dailysystem.utils.DBUtils;
import com.softeem.encrypt.MD5;

/**
 * ѧ����ع���ҵ���߼�
 * @author mrchai
 *
 */
public class StudentService {

	private StudentDAO sdao;
	private Connection conn;
	
	public StudentService(){
		
	}
	
	public ServiceModel register(Student stu){
		ServiceModel model = new ServiceModel();
		try {
			conn = DBUtils.getConn();
			sdao = new StudentDAO(conn);
			//1.����ֻ����Ƿ��ظ�
			Student s = sdao.findByPhone(stu.getPhone());
			//2.������
			if(Objects.nonNull(s)){
				//���ֻ��Ŷ�Ӧ��ѧ���Ѵ���
				model.setCode(-1);
				model.setMessage("���ֻ����ѱ�ע��");
			}else{
				//�˺ſ������
				boolean f = sdao.insert(stu);
				if(f){
					//�ɹ�
					model.setCode(1);
					model.setMessage("ע��ɹ�");
				}else{
					model.setCode(0);
					model.setMessage("ע��ʧ��");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
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
	public ServiceModel updatapwd (Student stu){
		ServiceModel model = new ServiceModel();
		try {
			conn = DBUtils.getConn();
			sdao = new StudentDAO(conn);
			//1.����ֻ����Ƿ��ظ�
			Student s = sdao.findBypwd(new MD5().getMD5ofStr(stu.getPassword()));
			//2.������
			if(Objects.nonNull(s)){
				//���ֻ��Ŷ�Ӧ��ѧ���Ѵ���
				//��������޸�
				model.setCode(1);
				System.out.println("44444");
			}else{
				model.setMessage("�����벻��ȷ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
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
	 * ѧ��
	 * @param stu
	 * @return
	 */
	public ServiceModel login(Student stu){
		ServiceModel model = new ServiceModel();
	
		try {
			conn = DBUtils.getConn();
			sdao = new StudentDAO(conn);
			Student s = sdao.findByPhone(stu.getPhone());
			
			if(stu.equals(s)){
				//��¼�ɹ�ʱ����ѧ����Ϣ�洢��ģ�Ͷ��󷵻أ��Ա���session�д洢
				model.setCode(1);
				model.setMessage("��¼�ɹ�");
				model.setData(s);
			}else{
				model.setCode(-1);
				model.setMessage("��¼ʧ��");
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

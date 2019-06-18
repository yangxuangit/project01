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
 * 学生相关功能业务逻辑
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
			//1.检查手机号是否重复
			Student s = sdao.findByPhone(stu.getPhone());
			//2.完成添加
			if(Objects.nonNull(s)){
				//该手机号对应的学生已存在
				model.setCode(-1);
				model.setMessage("该手机号已被注册");
			}else{
				//账号可以添加
				boolean f = sdao.insert(stu);
				if(f){
					//成功
					model.setCode(1);
					model.setMessage("注册成功");
				}else{
					model.setCode(0);
					model.setMessage("注册失败");
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
			//1.检查手机号是否重复
			Student s = sdao.findBypwd(new MD5().getMD5ofStr(stu.getPassword()));
			//2.完成添加
			if(Objects.nonNull(s)){
				//该手机号对应的学生已存在
				//密码可以修改
				model.setCode(1);
				System.out.println("44444");
			}else{
				model.setMessage("旧密码不正确");
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
	 * 学生
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
				//登录成功时，将学生信息存储到模型对象返回，以便向session中存储
				model.setCode(1);
				model.setMessage("登录成功");
				model.setData(s);
			}else{
				model.setCode(-1);
				model.setMessage("登录失败");
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

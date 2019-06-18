package com.softeem.dailysystem.dao.impl;

import java.sql.Connection;

import java.util.List;

import com.softeem.dailysystem.dao.BaseDAO;
import com.softeem.dailysystem.dto.Student;
import com.softeem.dailysystem.utils.DBUtils;

public class StudentDAO implements BaseDAO<Student> {

	private Connection conn;
	
	public StudentDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean insert(Student t) {
		String sql = "insert into student(name,phone,password,sex) values(?,?,?,?)";
		return DBUtils.exeUpdate(conn, 
									sql, 
									t.getName(),
									t.getPhone(),
									t.getPassword(),
									t.getSex());
	}

	@Override
	public boolean delete(Student t) {
		  
		return false;
	}

	@Override
	public boolean update(Student t) {
		String sql = "update student set password=? where id=?";
		return DBUtils.exeUpdate(conn, sql, t.getPassword(),t.getId());
	}

	@Override
	public Student findOne(Student t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findAll(int pageSize, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Student findByPhone(String phone){
		String sql = "select id,name,phone,password,sex,regtime from student where phone=?";
		return DBUtils.queryOne(Student.class, sql, phone);
	}
	public Student findBypwd(String pwd){
		String sql = "select id,name,phone,password,sex,regtime from student where password=?";
		return DBUtils.queryOne(Student.class, sql, pwd);
	}

}

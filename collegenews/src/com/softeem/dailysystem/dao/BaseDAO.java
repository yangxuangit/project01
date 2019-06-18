package com.softeem.dailysystem.dao;

import java.util.List;

/**
 * 通用数据访问对象的 CRUD接口
 * @author mrchai
 *
 * @param <T>
 */
public interface BaseDAO<T> {
	/**
	 * 新增对象
	 * @param t
	 * @return
	 */
	boolean insert(T t);
	
	/**
	 * 删除数据
	 * @param t
	 * @return
	 */
	boolean delete(T t);
	
	/**
	 * 更新数据
	 * @param t
	 * @return
	 */
	boolean update(T t);
	
	/**
	 * 检索单条记录
	 * @param t
	 * @return
	 */
	T findOne(T t);
	
	/**
	 * 批量检索
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	List<T> findAll(int pageSize,int currentPage);
	
}

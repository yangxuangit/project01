package com.softeem.dailysystem.dao;

import java.util.List;

/**
 * ͨ�����ݷ��ʶ���� CRUD�ӿ�
 * @author mrchai
 *
 * @param <T>
 */
public interface BaseDAO<T> {
	/**
	 * ��������
	 * @param t
	 * @return
	 */
	boolean insert(T t);
	
	/**
	 * ɾ������
	 * @param t
	 * @return
	 */
	boolean delete(T t);
	
	/**
	 * ��������
	 * @param t
	 * @return
	 */
	boolean update(T t);
	
	/**
	 * ����������¼
	 * @param t
	 * @return
	 */
	T findOne(T t);
	
	/**
	 * ��������
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	List<T> findAll(int pageSize,int currentPage);
	
}

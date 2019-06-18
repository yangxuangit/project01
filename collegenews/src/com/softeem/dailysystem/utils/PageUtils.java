package com.softeem.dailysystem.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * ͨ�÷�ҳ������
 * @author mrchai
 *
 * @param <T>
 */
public class PageUtils<T> {

	/**
	 * ��ǰҳ��
	 */
	private int currentPage;
	/**
	 * ÿҳ��С
	 */
	private int pageSize;
	/**
	 * ����������
	 */
	private int totalNum;
	
	/**
	 * ��ҳ
	 */
	private int first = 1;
	
	/**
	 * βҳ
	 */
	private int last;
	/**
	 * ��ҳ��
	 */
	private int totalPage;
	
	/**
	 * ��һҳ
	 */
	private int prev;
	/**
	 * ��һҳ
	 */
	private int next;
	
	/**
	 * ҳ�������ʾ����ʼλ��
	 */
	private int startNum;
	
	/**
	 * ҳ����ʾ����-��ʼҳ��
	 */
	private int start;
	/**
	 * ҳ����ʾ����-����ҳ��
	 */
	private int end;
	/**
	 * ��ʾҳ�����-����ʾҳ�루��ֹҳ����࣬ҳ����ʾӵ�����⣩
	 */
	private int count = 10;
	
	/**
	 * ����
	 */
	private List<T> list = new ArrayList<>();

	/**
	 * �ڹ������и���ָ���Ĳ��������������������Ե�����ֵ
	 * @param currentPage
	 * @param pageSize
	 * @param totalNum
	 */
	public PageUtils(int currentPage, int pageSize, int totalNum) {
		this.currentPage = currentPage;
		//��ֵÿ����ʾ�ļ�¼����
		this.pageSize = pageSize;
		//��ֵ�ܼ�¼��(����������)
		this.totalNum = totalNum;
		
		//��������ҳ���Լ�βҳ
		this.totalPage = this.last = (int)Math.ceil((double)totalNum/pageSize);
		//��ֹ��ǰҳС��1
		this.currentPage = Math.max(this.currentPage, 1);
		//��ֹ��ǰҳ������ҳ��
		this.currentPage = Math.min(this.totalPage, this.currentPage);
		
		//������һҳ:��һҳ����С��1
		this.prev = Math.max(this.currentPage - 1 , 1);
		//������һҳ:��һҳ���ܳ�����ҳ��
		this.next = Math.min(this.currentPage + 1 , this.totalPage);
		
		//�����ȡ������ʾ�����λ��
		this.startNum = (this.currentPage - 1) * pageSize;
		//������ʾҳ�����ʼλ�ã���ʼλ�ò���С��1
		this.start = Math.max(this.currentPage - this.count/2 , 1);
		//������ʾҳ��Ľ���λ�ã�����λ�ò��ܳ�����ҳ��
		this.end = 	Math.min(this.start + this.count , this.totalPage);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	public String toString(){
		return JSON.toJSONString(this);
	}
	
}

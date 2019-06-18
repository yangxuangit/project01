package com.softeem.dailysystem.service;

import java.util.List;

import com.softeem.dailysystem.dao.impl.NewsDAO;
import com.softeem.dailysystem.dto.NsNews;
import com.softeem.dailysystem.utils.PageUtils;


public class NewsService {
	
	
	public PageUtils query(int aid,int currentPage,int pageSize){
		NewsDAO dao = new NewsDAO();
		//������������
		int totalNum = dao.findTotalNum(aid);
		//�����ṩ�Ĳ�������һ��PageUtils����
		PageUtils<NsNews> pu = new PageUtils<>(currentPage, pageSize, totalNum);
		//�鵱ǰҳ����
		List<NsNews> list = dao.findAllbyaid(aid, pu.getCurrentPage(), pu.getPageSize());
		//����ѯ����ָ��ҳ������ݴ洢����ҳ���߶�����
		pu.setList(list);
		//����ҳ���߶��󷵻�
		return pu;
	}
	public PageUtils queryAll(int currentPage,int pageSize){
		NewsDAO dao = new NewsDAO();
		//������������
		int totalNum = dao.findAllTotalNum();
		//�����ṩ�Ĳ�������һ��PageUtils����
		PageUtils<NsNews> pu = new PageUtils<>(currentPage, pageSize, totalNum);
		//�鵱ǰҳ����
		List<NsNews> list = dao.findAll( pu.getCurrentPage(), pu.getPageSize());
		//����ѯ����ָ��ҳ������ݴ洢����ҳ���߶�����
		pu.setList(list);
		//����ҳ���߶��󷵻�
		return pu;
	}
	public static void main(String[] args) {
		PageUtils pu = new NewsService().query(19,1,10);
		System.out.println(pu.getTotalNum());
		System.out.println(pu);
	}
	public PageUtils querybycid(int cid,int currentPage,int pageSize){
		NewsDAO dao = new NewsDAO();
		//������������
		int totalNum = dao.findTotalNumbycid(cid);
		//�����ṩ�Ĳ�������һ��PageUtils����
		PageUtils<NsNews> pu = new PageUtils<>(currentPage, pageSize, totalNum);
		//�鵱ǰҳ����
		List<NsNews> list = dao.findAllbycid(cid, pu.getCurrentPage(), pu.getPageSize());
		//����ѯ����ָ��ҳ������ݴ洢����ҳ���߶�����
		pu.setList(list);
		//����ҳ���߶��󷵻�
		return pu;
	}
}
	


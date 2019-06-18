package com.softeem.dailysystem.service;

import java.util.List;

import com.softeem.dailysystem.dao.impl.NewsDAO;
import com.softeem.dailysystem.dto.NsNews;
import com.softeem.dailysystem.utils.PageUtils;


public class NewsService {
	
	
	public PageUtils query(int aid,int currentPage,int pageSize){
		NewsDAO dao = new NewsDAO();
		//查总数据条数
		int totalNum = dao.findTotalNum(aid);
		//根据提供的参数构建一个PageUtils对象
		PageUtils<NsNews> pu = new PageUtils<>(currentPage, pageSize, totalNum);
		//查当前页数据
		List<NsNews> list = dao.findAllbyaid(aid, pu.getCurrentPage(), pu.getPageSize());
		//将查询到的指定页码的数据存储到分页工具对象中
		pu.setList(list);
		//将分页工具对象返回
		return pu;
	}
	public PageUtils queryAll(int currentPage,int pageSize){
		NewsDAO dao = new NewsDAO();
		//查总数据条数
		int totalNum = dao.findAllTotalNum();
		//根据提供的参数构建一个PageUtils对象
		PageUtils<NsNews> pu = new PageUtils<>(currentPage, pageSize, totalNum);
		//查当前页数据
		List<NsNews> list = dao.findAll( pu.getCurrentPage(), pu.getPageSize());
		//将查询到的指定页码的数据存储到分页工具对象中
		pu.setList(list);
		//将分页工具对象返回
		return pu;
	}
	public static void main(String[] args) {
		PageUtils pu = new NewsService().query(19,1,10);
		System.out.println(pu.getTotalNum());
		System.out.println(pu);
	}
	public PageUtils querybycid(int cid,int currentPage,int pageSize){
		NewsDAO dao = new NewsDAO();
		//查总数据条数
		int totalNum = dao.findTotalNumbycid(cid);
		//根据提供的参数构建一个PageUtils对象
		PageUtils<NsNews> pu = new PageUtils<>(currentPage, pageSize, totalNum);
		//查当前页数据
		List<NsNews> list = dao.findAllbycid(cid, pu.getCurrentPage(), pu.getPageSize());
		//将查询到的指定页码的数据存储到分页工具对象中
		pu.setList(list);
		//将分页工具对象返回
		return pu;
	}
}
	


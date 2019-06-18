package com.softeem.dailysystem.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.softeem.dailysystem.dao.impl.votecountDAO;
import com.softeem.dailysystem.dto.ServiceModel;
import com.softeem.dailysystem.dto.Voteoptions;
import com.softeem.dailysystem.utils.DBUtils;

public class VoteService {
	   private Connection conn = null;
	public  ServiceModel  updatevote(int oid){
		  ServiceModel model = new ServiceModel();
		  try {
			conn = DBUtils.getConn();
			 List<Voteoptions> vo =  new votecountDAO().findAllbyoid(oid);
			 int count = 0;
			 for(Voteoptions voteo: vo){
			 	 count = voteo.getVotecount();
			 	 break;
			 }
			    count +=1;
			    Voteoptions  t  =  new Voteoptions();
			    t.setOid(oid);
			    t.setVotecount(count);
			    boolean  f =  new votecountDAO().updateoptions(t, conn);
			    if(f){
			    	model.setCode(1);
			    	model.setMessage("投票成功");

			    }else{
			    	model.setCode(-1);
			    	model.setMessage("投票失败");
			    }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
        	if(conn!=null){
        		try {
					conn.close();
				} catch (SQLException e) {			
					e.printStackTrace();
				}
        	}
        }
            
           return model;
	}
	
}

package com.softeem.dailysystem.dto;import java.io.UnsupportedEncodingException;import java.net.URLEncoder;import java.sql.Timestamp;/******************************************************************************* * javaBeans * daily --> Daily  * <table explanation> * @author 2018-08-30 15:21:27 *  */	public class Daily implements java.io.Serializable {	//field	/**  **/	private String id;	/**  **/	private String todaytask;	/**  **/	private String completeinfo;	/**  **/	private String nextplan;	/**  **/	private Timestamp subtime;	/**  **/	private Timestamp lastupdatetime;	/**  **/	private int sid; //Student stu;	//method	public String getId() {		return id;	}	public void setId(String id) {		this.id = id;	}	public String getTodaytask() {		return todaytask;	}	public void setTodaytask(String todaytask) {		this.todaytask = todaytask;	}	public String getCompleteinfo() {		return completeinfo;	}	public void setCompleteinfo(String completeinfo) {		this.completeinfo = completeinfo;	}	public String getNextplan() {		return nextplan;	}	public void setNextplan(String nextplan) {		this.nextplan = nextplan;	}	public Timestamp getSubtime() {		return subtime;	}	public void setSubtime(Timestamp subtime) {		this.subtime = subtime;	}	public Timestamp getLastupdatetime() {		return lastupdatetime;	}	public void setLastupdatetime(Timestamp lastupdatetime) {		this.lastupdatetime = lastupdatetime;	}	public int getSid() {		return sid;	}	public void setSid(int sid) {		this.sid = sid;	}	//override toString Method 	public String toString() {		StringBuffer sb=new StringBuffer();		sb.append("{");		sb.append("'id':'"+this.getId()+"',");		sb.append("'todaytask':'"+this.getTodaytask()+"',");		sb.append("'completeinfo':'"+this.getCompleteinfo()+"',");		sb.append("'nextplan':'"+this.getNextplan()+"',");		sb.append("'subtime':'"+this.getSubtime()+"',");		sb.append("'lastupdatetime':'"+this.getLastupdatetime()+"',");		sb.append("'sid':'"+this.getSid()+"'");		sb.append("}");				return sb.toString();	}}
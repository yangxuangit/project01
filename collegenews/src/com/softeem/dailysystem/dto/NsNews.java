package com.softeem.dailysystem.dto;import java.sql.Timestamp;/******************************************************************************* * javaBeans * ns_news --> NsNews  * <table explanation> * @author 2018-09-12 11:33:37 *  */	public class NsNews implements java.io.Serializable {	//field	/**  **/	private int nid;	/**  **/	private String subject;	/**  **/	private String content;	/**  **/	private Timestamp releasetime;	/**  **/	private String imgpath;	/**  **/	private int clickcount;	/**  **/	private int aid;	/**  **/	private int cid;	//method	public int getNid() {		return nid;	}	public void setNid(int nid) {		this.nid = nid;	}	public String getSubject() {		return subject;	}	public void setSubject(String subject) {		this.subject = subject;	}	public String getContent() {		return content;	}	public void setContent(String content) {		this.content = content;	}	public Timestamp getReleasetime() {		return releasetime;	}	public void setReleasetime(Timestamp releasetime) {		this.releasetime = releasetime;	}	public String getImgpath() {		return imgpath;	}	public void setImgpath(String imgpath) {		this.imgpath = imgpath;	}	public int getClickcount() {		return clickcount;	}	public void setClickcount(int clickcount) {		this.clickcount = clickcount;	}	public int getAid() {		return aid;	}	public void setAid(int aid) {		this.aid = aid;	}	public int getCid() {		return cid;	}	public void setCid(int cid) {		this.cid = cid;	}	//override toString Method 	public String toString() {		StringBuffer sb=new StringBuffer();		sb.append("{");		sb.append("'nid':'"+this.getNid()+"',");		sb.append("'subject':'"+this.getSubject()+"',");		sb.append("'content':'"+this.getContent()+"',");		sb.append("'releasetime':'"+this.getReleasetime()+"',");		sb.append("'imgpath':'"+this.getImgpath()+"',");		sb.append("'clickcount':'"+this.getClickcount()+"',");		sb.append("'aid':'"+this.getAid()+"',");		sb.append("'cid':'"+this.getCid()+"'");		sb.append("}");		return sb.toString();	}}
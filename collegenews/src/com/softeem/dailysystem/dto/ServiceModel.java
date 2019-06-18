package com.softeem.dailysystem.dto;

/**
 * 模型对象，包含丰富执行结果（状态码，消息，数据）
 * @author mrchai
 *
 */
public class ServiceModel {

	/**
	 * 状态码，规则自定义
	 */
	private int code;
	/**
	 * 消息内容
	 */
	private String message;
	/**
	 * 复杂的数据
	 */
	private Object data;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}

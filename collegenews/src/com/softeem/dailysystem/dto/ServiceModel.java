package com.softeem.dailysystem.dto;

/**
 * ģ�Ͷ��󣬰����ḻִ�н����״̬�룬��Ϣ�����ݣ�
 * @author mrchai
 *
 */
public class ServiceModel {

	/**
	 * ״̬�룬�����Զ���
	 */
	private int code;
	/**
	 * ��Ϣ����
	 */
	private String message;
	/**
	 * ���ӵ�����
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

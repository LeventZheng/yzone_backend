package com.spark.common.model;


import com.spark.common.enums.EnumResponseCode;

/**
 * 
* 统一的返回数据
* 
* @author hao.chen
* @date 2016年8月15日 下午2:52:02 
* @param <T>  data实际数据对象类型
 */
public class ResponseInfo<T> {
	private int code;
	private String desc;
	private T data;
	
	public boolean isSuccess() {
		return EnumResponseCode.SUCCESS.getCode() == code;
	}
	
	public ResponseInfo(){}
	
	public ResponseInfo(int code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public ResponseInfo(EnumResponseCode responseCode){
		this.code = responseCode.getCode();
		this.desc = responseCode.getDesc();
	}

	public ResponseInfo(EnumResponseCode responseCode, T data){
		this.code = responseCode.getCode();
		this.desc = responseCode.getDesc();
		this.data = data;
	}
	
	public final int getCode() {
		return code;
	}
	public final void setCode(int code) {
		this.code = code;
	}
	public final String getDesc() {
		return desc;
	}
	public final void setDesc(String desc) {
		this.desc = desc;
	}
	public final T getData() {
		return data;
	}
	public final ResponseInfo<T> setData(T data) {
		this.data = data;
		return this;
	}
	
}

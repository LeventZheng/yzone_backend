package com.spark.modules.base.exception;

/**
 * 
* 资源不存在异常
*
 */
public class ResourceNotFoundExcetion extends RuntimeException{

	private static final long serialVersionUID = 68528677477541161L;

	public ResourceNotFoundExcetion(){}
	
	/**
	 * 资源不存在异常
	 * @param message 异常描述
	 */
	public ResourceNotFoundExcetion(String message){
		super(message);
	}
}

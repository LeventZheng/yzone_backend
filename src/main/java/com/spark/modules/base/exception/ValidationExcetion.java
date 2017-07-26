package com.spark.modules.base.exception;

/**
 * 
* 检验异常
*
 */
public class ValidationExcetion extends RuntimeException{

	private static final long serialVersionUID = 68528677477541161L;

	public ValidationExcetion(){}
	
	/**
	 * 校验异常
	 * @param message 异常描述
	 */
	public ValidationExcetion(String message){
		super(message);
	}
}

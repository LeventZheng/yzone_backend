package com.spark.common.enums;

/**
* 
* 全局异常以100000**开始
* 	1、如10000000表示系统异常  Exception
*   2、如10000001表示资源不存在
*   3、如10000002表示无操作权限
* 模块暂定如下：
* 	资源创建 1001
* 		查看	  100101
* 			查看资源不存在  10010101
* 		创建	  100102
* 		修改	  100103
* 		删除	  100104
* 
* 响应状态枚举值
* @author hao.chen
* @date 2016年8月15日 上午11:52:30 
*
 */
public enum EnumResponseCode {

	/**
	 * 全局异常以100000**开始
	 */
	SUCCESS(200,"成功"),
	SYSTEM_EXCEPTION(10000000,"系统异常"),
	RESOURCE_NOT_FOUND(10000001,"资源不存在"),
	PERMISSION_DENIED (10000002,"无权限"),
	VALIDATE_ERROR (10000003,"参数校验失败"),
	UNAUTHORIZED_OPERATION(10000004,"越权操作"),

	/**
	 * 1008***  角色
	 */
	ROLE_NAME_REPEAT(10080001,"角色名称重复"),

	/**
	 * 1009***   权限
	 */
	RELOGIN(10090001,"校验未通过,请重新登录"),
	UNAUTHORIZED(10090002,"操作未授权")
	;

	private int code;
	private String desc;

	EnumResponseCode(int code, String desc){
		this.code = code;
		this.desc = desc;
	}

	public int getCode(){
		return code;
	}
	
	public String getDesc(){
		return desc;
	}

	public static String code2desc(Integer code){
		EnumResponseCode[] vals =  EnumResponseCode.values();
		for(EnumResponseCode responseCode : vals){
			if(responseCode.getCode() == code){
				return  responseCode.getDesc();
			}
		}
		return  null;
	}
}

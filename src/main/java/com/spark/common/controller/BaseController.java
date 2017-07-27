/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.spark.common.controller;

import com.spark.common.enums.EnumResponseCode;
import com.spark.common.model.ResponseInfo;

/**
 * 控制器支持类
 * @author ThinkGem
 * @version 2013-3-23
 */
public abstract class BaseController {
	/**
	 * 
	*构造成功返回数据对象
	 */
	public <T> ResponseInfo<T> buildSuccessRetunInfo() { 
		ResponseInfo<T> responseInfo = new ResponseInfo<T>(EnumResponseCode.SUCCESS);
		return responseInfo;
	}

	/**
	 *
	 *构造参数校验失败返回数据对象
	 */
	public <T> ResponseInfo<T> buildValidateErrorRetunInfo() {
		ResponseInfo<T> responseInfo = new ResponseInfo<T>(EnumResponseCode.VALIDATE_ERROR);
		return responseInfo;
	}
	public <T> ResponseInfo<T> buildErrorRetunInfo(EnumResponseCode responseCode) {
		ResponseInfo<T> responseInfo = new ResponseInfo<T>(responseCode);
		return responseInfo;
	}
	
}

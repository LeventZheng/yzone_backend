/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.spark.common.controller;

import com.spark.common.enums.EnumResponseCode;
import com.spark.common.model.ResponseInfo;
import com.spark.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

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
	// 资源不存在
	public <T> ResponseInfo<T> buildResourceNoFoundErrorRetunInfo() {
		ResponseInfo<T> responseInfo = new ResponseInfo<T>(EnumResponseCode.RESOURCE_NOT_FOUND);
		return responseInfo;
	}
	// 通用错误返回
	public <T> ResponseInfo<T> buildErrorRetunInfo(EnumResponseCode responseCode) {
		ResponseInfo<T> responseInfo = new ResponseInfo<T>(responseCode);
		return responseInfo;
	}

	public void printRequest(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Enumeration enu=request.getHeaderNames();//取得全部头信息
		while (enu.hasMoreElements()){
			String headerName=(String)enu.nextElement();
			String headerValue=request.getHeader(headerName);//取出头信息内容
			System.out.println(String.format("headerName:%s   headerValue:%s",headerName,headerValue));
		}
	}

	public User getUser() {
		String token = "";
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();

		Enumeration enu=request.getHeaderNames();//取得全部头信息
		while (enu.hasMoreElements()){
			String headerName=(String)enu.nextElement();
			if (headerName.equals("token")) {
				token = request.getHeader(headerName);
				break;
			}
		}
		return (User)session.getAttribute(token);
	}

	/**
	 * 创建分页请求.
	 */
	public PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType, String modelName, Sort.Direction direction) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(direction, modelName + "Id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(direction, modelName + "Title");
		}

		return new PageRequest(pageNumber, pagzSize, sort);
	}
	
}

package com.spark.common.controller;

import com.spark.common.beanvalidator.BeanValidators;
import com.spark.common.enums.EnumResponseCode;
import com.spark.common.mapper.JsonMapper;
import com.spark.common.persistence.Criteria;
import com.spark.common.utils.DateUtils;
import com.spark.common.utils.FastjsonUtil;
import com.spark.modules.base.vo.ResponseInfo;
import com.spark.modules.base.exception.ResourceNotFoundExcetion;
import com.spark.modules.base.exception.ValidationExcetion;
import com.spark.xfw.exception.SystemException;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.*;

/**
 * 控制器支持类
 * @author Lavine
 * @version 2017-7-26
 */
public class BaseController {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 验证Bean实例对象
     */
    @Autowired
    protected Validator validator;

    /**
     * 返回异常类型
     */
    private static final String SYSTEM_ERROR = "系统异常";
    private static final String RESOURCE_NOT_FOUND = "资源不存在";
    private static final String VALIDATION_NOT_PASS = "参数校验不通过";
    private static final String RELOGIN = "校验未通过,请重新登录";
    private static final String UNAUTHORIZED = "操作未授权";

    /**
     * 服务端参数有效性验证
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
     */
    protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
        try{
            BeanValidators.validateWithException(validator, object, groups);
        }catch(ConstraintViolationException ex){
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败：");
            addMessage(model, list.toArray(new String[]{}));
            return false;
        }
        return true;
    }

    /**
     * 服务端参数有效性验证
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
     */
    protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
        try{
            BeanValidators.validateWithException(validator, object, groups);
        }catch(ConstraintViolationException ex){
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败：");
            addMessage(redirectAttributes, list.toArray(new String[]{}));
            return false;
        }
        return true;
    }

    /**
     * 服务端参数有效性验证
     * @param object 验证的实体对象
     * @param groups 验证组，不传入此参数时，同@Valid注解验证
     * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
     */
    protected void beanValidator(Object object, Class<?>... groups) {
        BeanValidators.validateWithException(validator, object, groups);
    }

    /**
     * 添加Model消息
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        model.addAttribute("message", sb.toString());
    }

    /**
     * 添加Flash消息
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        redirectAttributes.addFlashAttribute("message", sb.toString());
    }

    /**
     * 客户端返回JSON字符串
     * @param response
     * @param object
     * @return
     */
    protected String renderString(HttpServletResponse response, Object object) {
        return renderString(response, JsonMapper.toJsonString(object), "application/json");
    }

    /**
     * 客户端返回字符串
     * @param response
     * @param string
     * @return
     */
    protected String renderString(HttpServletResponse response, String string, String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 解析DataTables的分页信息
     */
    protected Criteria parsePageParam(HttpServletRequest request){
        Criteria criteria = new Criteria();

        //1. 解析页码和页面大小
        String no = request.getParameter("pageNum");
        if(no == null) {
            no = request.getParameter("pageNo");
        }

        String size = request.getParameter("pageSize");
        if (StringUtils.isNumeric(no) && StringUtils.isNumeric(size)){
            criteria.setPageNum(Integer.parseInt(no));
            criteria.setPageSize(Integer.parseInt(size));
        }

        //2. 解析排序参数
        String orderBy = request.getParameter("orderby");
        if(StringUtils.isNotEmpty(orderBy)){
            criteria.setOrderBy(orderBy);
        }

        //3. 解析查询参数
        criteria.setParam(request.getParameterMap());

        return criteria;
    }

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }
            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
//			@Override
//			public String getAsText() {
//				Object value = getValue();
//				return value != null ? DateUtils.formatDateTime((Date)value) : "";
//			}
        });
    }

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

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ResponseEntity<String> exception(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        logger.error(SYSTEM_ERROR,exception);
        if(request.getRequestURI().contains("/xfw/") || request.getRequestURI().contains("/api/"))
        {//处理晓服务异常
            Map<String,String> map = new HashMap<>();
            map.put("errorCode","11000000");
            map.put("msg", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            ResponseEntity<String> responseEntity = new ResponseEntity<>(FastjsonUtil.serialize(map),HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
        String responseInfo = FastjsonUtil.serialize(new ResponseInfo<String>(EnumResponseCode.SYSTEM_EXCEPTION));
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseInfo,HttpStatus.OK);
        return responseEntity;
    }

    @ExceptionHandler(ResourceNotFoundExcetion.class)
    public @ResponseBody ResponseInfo<String> exception(ResourceNotFoundExcetion exception, HttpServletRequest request, HttpServletResponse response) {
        logger.error(RESOURCE_NOT_FOUND,exception);
        ResponseInfo<String> responseInfo = new ResponseInfo<String>(EnumResponseCode.RESOURCE_NOT_FOUND);
        return responseInfo;
    }

    @ExceptionHandler(ValidationExcetion.class)
    public @ResponseBody ResponseInfo<String> exception(ValidationExcetion exception, HttpServletRequest request, HttpServletResponse response) {
        logger.error(VALIDATION_NOT_PASS,exception);
        ResponseInfo<String> responseInfo = new ResponseInfo<String>(EnumResponseCode.VALIDATE_ERROR);
        return responseInfo;
    }

    /*ExceptionHandler@ExceptionHandler(AuthenticationException.class)
    public @ResponseBody ResponseInfo<String> exception(AuthenticationException exception, HttpServletRequest request, HttpServletResponse response) {
        logger.error(RELOGIN, exception);
        ResponseInfo<String> responseInfo = new ResponseInfo<String>(EnumResponseCode.RELOGIN);
        return responseInfo;
    }*/

    /*@ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ResponseInfo<String> exception(UnauthorizedException exception, HttpServletRequest request, HttpServletResponse response) {
        logger.error(UNAUTHORIZED, exception);
        ResponseInfo<String> responseInfo = new ResponseInfo<String>(EnumResponseCode.UNAUTHORIZED);
        return responseInfo;
    }*/

    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ResponseInfo<String> exception(SystemException exception, HttpServletRequest request, HttpServletResponse response) {
        logger.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),exception);
        ResponseInfo<String> responseInfo = new ResponseInfo<String>(11000000,HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return responseInfo;
    }

    @ExceptionHandler(ServletException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ResponseInfo<String> exception(ServletException exception, HttpServletRequest request, HttpServletResponse response) {
        logger.error("ServletException",exception);
        ResponseInfo<String> responseInfo = new ResponseInfo<String>(11000001,"ServletException");
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
}

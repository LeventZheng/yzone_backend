package com.spark.common.config;

/**
 * Created by Administrator on 2016/10/17.
 */
public class Constants {
	
	/**
     * 是否测试
     */
	public static final boolean SYS_IS_DEBUG = false;

    /**
     * 删除标记（1：正常；0：删除；）
     */
    public static final String DELETE_FLAG_NORMAL = "1";
    public static final String DELETE_FLAG_DELETE = "0";
    
    /**
     * 是否使用：  1使用  0禁用
     */
    public static final Integer USER_STATUS_DISABLE = 0;
    public static final Integer USER_STATUS_USERD = 1;

    /**
     * 是/否
     */
    public static final String YES = "1";
    public static final String NO = "0";

    /**
     * 对/错
     */
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    /**
     * 内容平台url的key
     */
    public static final String QUESTION_URL_KEY = "dependency.question.url";

    /**
     * boss平台url的key
     */
    public static final String BOSS_URL_KEY = "dependency.boss.url";
    
    /**
     * boss平台url的key
     */
    public static final String PY_BOSS_URL_KEY = "dependency.pyboss.url";
    
    /**
     * swf的url前缀
     */
    public static final String SWF_URL_PREFIX = "sparkteaching/swf/";
    
    /**
     * 一对一教案图片的url前缀
     */
    public static final String O_TEACH_PLAN_PIC_PREFIX = "sparkteaching/oteachplan/";
    
    /** 班课教材默认封面 begin TODO */
    /**
     * 小学语文
     */
    public static final String CLS_COVER_XXYW_URL = "sparkteaching/cls/cover/xxyw.png";
    
    /**
     * 小学数学
     */
    public static final String CLS_COVER_XXSX_URL = "sparkteaching/cls/cover/xxsx.png";
    
    /**
     * 小学英语
     */
    public static final String CLS_COVER_XXYY_URL = "sparkteaching/cls/cover/xxyy.png";
    
    /**
     * 初中语文
     */
    public static final String CLS_COVER_CZYW_URL = "sparkteaching/cls/cover/czyw.png";
    
    /**
     * 初中数学
     */
    public static final String CLS_COVER_CZSX_URL = "sparkteaching/cls/cover/czsx.png";
    
    /**
     * 初中英语
     */
    public static final String CLS_COVER_CZYY_URL = "sparkteaching/cls/cover/czyy.png";
    
    /**
     * 初中政治
     */
    public static final String CLS_COVER_CZZZ_URL = "sparkteaching/cls/cover/czzz.png";
    
    /**
     * 初中历史
     */
    public static final String CLS_COVER_CZLS_URL = "sparkteaching/cls/cover/czls.png";
    
    /**
     * 初中地理
     */
    public static final String CLS_COVER_CZDL_URL = "sparkteaching/cls/cover/czdl.png";
    
    /**
     * 初中物理
     */
    public static final String CLS_COVER_CZWL_URL = "sparkteaching/cls/cover/czwl.png";
    
    /**
     * 初中化学
     */
    public static final String CLS_COVER_CZHX_URL = "sparkteaching/cls/cover/czhx.png";
    
    /**
     * 初中生物
     */
    public static final String CLS_COVER_CZSW_URL = "sparkteaching/cls/cover/czsw.png";
    
    /**
     * 高中语文
     */
    public static final String CLS_COVER_GZYW_URL = "sparkteaching/cls/cover/gzyw.png";
    
    /**
     * 高中数学
     */
    public static final String CLS_COVER_GZSX_URL = "sparkteaching/cls/cover/gzsx.png";
    
    /**
     * 高中英语
     */
    public static final String CLS_COVER_GZYY_URL = "sparkteaching/cls/cover/gzyy.png";
    
    /**
     * 高中政治
     */
    public static final String CLS_COVER_GZZZ_URL = "sparkteaching/cls/cover/gzzz.png";
    
    /**
     * 高中历史
     */
    public static final String CLS_COVER_GZLS_URL = "sparkteaching/cls/cover/gzls.png";
    
    /**
     * 高中地理
     */
    public static final String CLS_COVER_GZDL_URL = "sparkteaching/cls/cover/gzdl.png";
    
    /**
     * 高中物理
     */
    public static final String CLS_COVER_GZWL_URL = "sparkteaching/cls/cover/gzwl.png";
    
    /**
     * 高中化学
     */
    public static final String CLS_COVER_GZHX_URL = "sparkteaching/cls/cover/gzhx.png";
    
    /**
     * 高中生物
     */
    public static final String CLS_COVER_GZSW_URL = "sparkteaching/cls/cover/gzsw.png";
    
    /** 班课教材封面 end TODO */
    
    /**
     * 登录统一接口后查不到boss数据
     */
    public static final String OAUTH_NO_BOSS_DATA = "no boss data";
    
    /**
     * 登录统一接口失败
     */
    public static final String OAUTH_FAIL = "oauth2 fail";
    
    /**
     * 模块表 菜单级别 -1：非菜单
     */
    public static final Integer AUTH_MODULE_NOT_MENU = -1;
    
    /**
     * 用户角色关系表 有访客权限的userId(培优)
     */
    public static final Integer AUTH_USER_ROLE_USER_ID_0 = 0;
    
    /**
     * 用户角色关系表 有访客权限的userId(星火)
     */
    public static final Integer AUTH_USER_ROLE_USER_ID_NEGATIVE_1 = -1;

    /**
     * 晓服务题目答案模板
     */
    public static String ANSWER_TEMPLATE = null;
    /**
     * 晓服务题目选项模板
     */
    public static String OPTION_TEMPLATE = null;
    /**
     * 晓服务题目题干模板
     */
    public static String STEM_TEMPLATE = null;

}

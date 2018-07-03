package org.zmsoft.jfp.framework.constants;

/**
 * 系统常量
 * 
 * @author ZmSoft
 * @since 0.1.0 2018/2/8
 * @version 0.1.0 2018/2/8
 */
public interface IFrameworkConstants {


	public static final String PAGE_LIMIT = "PageLimit";
	
	
	public final static String Token_Can_Use_Hour = "TokenCanUseHour";
	public final static String SYSTEM_OpenId = "1234567890";
	public final static String SYSTEM_CHARSET = "UTF-8";
	public static final String SYSTEM = "SYSTEM";
	public static final String GUEST = "guest";
	public static final String SYSTEM_NAME = "SYSTEM_NAME_JFP";

	public static final String FOLDER_SEPARATOR = "/";

	public static final String WINDOWS_FOLDER_SEPARATOR = "\\";

	public static final String TOP_PATH = "..";

	public static final String CURRENT_PATH = ".";

	public static final char EXTENSION_SEPARATOR = '.';

	public static String Phone_Android = "A";
	public static String Phone_Ios = "P";
	public static String Phone_Windows = "W";

	public static String MAIL = "M";
	public static String SMS = "S";
	public static String PUSH = "P";
	public static String LOG = "L";

	public static String ZERO = "0";
	public static String ONE = "1";
	public static String TWO = "2";
	public static String THREE = "3";
	public static String FOUR = "4";
	public static String FIVE = "5";
	public static String SIX = "6";
	public static String SEVEN = "7";
	public static String EIGHT = "8";
	public static String NINE = "9";
	
	public static String TEN = "10";
	public static String ELEVEN = "11";
	public static String TWELVE = "12";
	
	public static String A = "A";
	public static String B = "B";
	public static String C = "C";

	public static String Public_Key_Cryptography = "";

	public final static String SYSTEM_TOKEN_ERROR_CODE = "99";
	public final static String SYSTEM_ERROR_CODE = "9999";

	public final static String MESSAGE_SECURITY_CODE = "验证码输入错误！！！";
	public final static String MESSAGE_TOKEN_ERROR = "Token已经失效，请从新登录。";
	public final static String MESSAGE_ERROR_SYSTEM = "都什么年代了，网络还不通~";
	public final static String MESSAGE_ERROR_SYNC = "服务端处理异常，请联系管理员！";
	public final static String MESSAGE_ALERT_SYSTEM = "非法请求！！！";
	public static final String MESSAGE_PROC_WAITING = "请求正在处理中";
	public static final String MESSAGE_OK = "OK";
	public final static String TREE_LEVEL_TAG = "@@";

	public final static String undefined = "undefined";

	/**
	 * 用户TONKEN
	 */
	public final static String CONSTANT_SESSION_ID = "sessionid";
	public final static String CONSTANT_USER_TOKEN = "token";
	public final static String CONSTANT_LOGINER = "loginer";
	public final static String CONSTANT_USER = "user";
	public final static String CONSTANT_PASSWORD = "password";
	public final static String CONSTANT_COMPANY = "company";
	public final static String CONSTANT_USER_MAIN = "USERMAIN";
	public final static String CONSTANT_SQL_SESSION = "sqlsession";

	/**
	 * 系统拦截错误页面
	 */
	public final static String SYSTEM_ROOT = "/";
	/**
	 * 系统接口拦截错误页面
	 */
	public final static String SYSTEM_BLANK = "/resources/blank.html";

	/**
	 * 防伪码
	 */
	public final static String CONSTANT_SECURITY_CODE = "CACHE:SecurityCode:";

	/**
	 * 缓存有效时间
	 */
	public final static int CACHE_EXPIRATION = 3600;
	/**
	 * 空字符串
	 */
	public final static String EMPTY = "";
	/**
	 * 半角空格
	 */
	public final static String BLANK = " ";
	/**
	 * 半角冒号
	 */
	public final static String COLON = ":";
	
	/**
	 * 数据锁
	 */
	public final static String LOCK = ".LOCK";

	public final static String SEMICOLON = ";";// semicolon
	public final static String QUOTES = "'";// semicolon
	public final static String AND = "&";// semicolon
	public final static String QUMARK = "?";// semicolon

	public static final String BACKSLASH = "/";// backslash
	public static final String BACKSLASH2 = "//";// backslash
	public static final String FILE_URI = "file://";// FILE_URI

	public final static String DOT = ".";
	public final static String COMMA = ",";
	public final static String TRANSVERSE_LINE = "-";
	public static final String DOWN_LINE = "_";// backslash
	public static final char DOWN_LINE2 = '_';// backslash

	/* 数据基本操作 */
	public enum DBManagerType {
		CREAT, INIT, BUILD, NONE;
	}
}

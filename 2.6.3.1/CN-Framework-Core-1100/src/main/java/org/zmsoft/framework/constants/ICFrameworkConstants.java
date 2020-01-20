package org.zmsoft.framework.constants;

/**
 * 系统常量
 * 
 * @author ZmSoft
 * @since 2.0.0 2018/10/10
 * @version 2.0.0 2018/10/10
 */
public interface ICFrameworkConstants {

	public final static String Page_Model_Name = "PageModel";
	
	public final static int SYSTEM_SUCCESS = 0;//接口请求成功，正确返回
	public final static int SYSTEM_FAIL = 1;//接口请求失败，直接返回
	public final static int SYSTEM_TOKEN_ERROR_CODE = 99;//用户登录过期，token无效
	public final static int SYSTEM_ROLE_ERROR_CODE = 999;//权限不足
	public final static int SYSTEM_ERROR_CODE = 9999;//服务内部错误，异常返回

	public final static String PAGE_LIMIT = "PageLimit";
	
	
	public final static String Token_Can_Use_Hour = "TokenCanUseHour";
	public final static String SYSTEM_OpenId = "1234567890";
	public final static String SYSTEM_CHARSET = "UTF-8";
	public final static String SYSTEM = "SYSTEM";
	public final static String GUEST = "GUEST";
	public final static String SYSTEM_NAME = "SYSTEM_NAME_JFP";

	public final static String FOLDER_SEPARATOR = "/";

	public final static String WINDOWS_FOLDER_SEPARATOR = "\\";

	public final static String TOP_PATH = "..";

	public final static String CURRENT_PATH = ".";

	public final static char EXTENSION_SEPARATOR = '.';

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

	public final static String MESSAGE_ROLE_ERROR = "权限不足，请联系管理员！！！";
	public final static String MESSAGE_LOGIN_FAIL = "登录失效，请重新登录！！！";
	public final static String MESSAGE_SECURITY_CODE = "验证码输入错误！！！";
	public final static String MESSAGE_TOKEN_ERROR = "Token已经失效，请从新登录。";
	public final static String MESSAGE_ERROR_SYSTEM = "服务端处理异常，请联系管理员！";
	public final static String MESSAGE_ERROR_SYNC = "都什么年代了，网络还不通~";
	public final static String MESSAGE_ALERT_SYSTEM = "非法请求！！！";
	public final static String MESSAGE_PROC_WAITING = "请求正在处理中";
	public final static String MESSAGE_OK = "OK";
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
	public final static String SYSTEM_ERROR = "/error";
	/**
	 * 系统拦截错误页面-权限不足
	 */
	public final static String SYSTEM_BLANK = "/resources/blank.html";
	/**
	 * 系统拦截错误页面-未登录
	 */
	public final static String SYSTEM_TOKEN = "/resources/token.html";

	/**
	 * 防伪码
	 */
	public final static String CONSTANT_SECURITY_CODE = "CACHE:WEBSecurityCode:";
	
	/**
	 * 手机验证码redis前缀
	 */
	public final static String  PHONE_SECURITY_CODE = "CACHE:PHONESecurityCode:";
	
	/**
	 * 管理员权限缓存
	 */
	public final static String MANAGER_ROLE_KEY = "CACHE:ManagerRole:";

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
	public final static String URL_SLASH = "/";

	public final static String SEMICOLON = ";";
	public final static String QUOTES = "'";
	public final static String AND = "&";
	public final static String QUMARK = "?";

	public final static String BACKSLASH = "/";
	public final static String BACKSLASH2 = "//";
	public final static String FILE_URI = "file://";
	public final static String HTTP_URI = "http://";
	public final static String HTTPS_URI = "https://";

	public final static String DOT = ".";
	public final static char DOT2 = '.';
	public final static String COMMA = ",";
	public final static String TRANSVERSE_LINE = "-";
	public final static String DOWN_LINE = "_";
	public final static char DOWN_LINE2 = '_';

	/* 数据基本操作 */
	public enum DBManagerType {
		CREAT, INIT, BUILD, NONE;
	}
}

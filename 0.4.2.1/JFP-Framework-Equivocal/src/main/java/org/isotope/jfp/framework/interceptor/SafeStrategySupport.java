package org.isotope.jfp.framework.interceptor;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.isotope.jfp.framework.cache.redis.master.JedisMasterUtil;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.elk.ELKLogData;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.HttpRequestHelper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import redis.clients.jedis.Jedis;

/**
 * IP安全策略
 * 
 * @author Spook
 * @version 4.1.1 2016/12/12
 * @version 2.4.3 2016/1/28
 * @version 2.4.2 2016/1/6
 * @since 2.4.2 2016/1/6
 */
public class SafeStrategySupport extends HandlerInterceptorAdapter implements ISFrameworkConstants {
	public final static String FIREWALL_NAME = "SafeStrategy";
	
	public final static String FIREWALL_CONFIG = "FIREWALL:CONFIG";
	public final static String FIREWALL_PATH_MESSAGE = "FIREWALL:MESSAGE";

	private int index = 0;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 1:开启拦截
	 */
	boolean fireWallFlag = false;
	boolean logDataFlag = false;

	public void setFireWallFlag(boolean fireWallFlag) {
		this.fireWallFlag = fireWallFlag;
	}

	public void setLogDataFlag(boolean logDataFlag) {
		this.logDataFlag = logDataFlag;
	}

	public Jedis getJedis() {
		Jedis cacheService;
		{
			cacheService = jedisMasterUtil.getJedis();
			cacheService.select(index);// 黑名单
		}
		return cacheService;
	}

	/**
	 * Redis缓存
	 */
	@Resource
	private JedisMasterUtil jedisMasterUtil;

	// 最小时间（基数）：最小时间（基数时间）：最大时间（基数的倍数）
	private void setConfigNum(String configNum) {
		try {
			String[] cs = configNum.split(COLON);
			size = Integer.parseInt(cs[0]);
			waitTimeSecond = Integer.parseInt(cs[1]);
			maxSize = Integer.parseInt(cs[2]);
		} catch (Exception e) {
		}
	}

	/**
	 * 设定时间
	 */
	private int size = 300;
	/**
	 * 最大请求次数
	 */
	private int maxSize = 12;
	/**
	 * 进程阻塞时间（秒）
	 */
	private int waitTimeSecond = 1;
	private String message = "系统繁忙，请稍后再来~~~";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean handle = true;
		if (handler instanceof HandlerMethod) {//判断资源路径
			// 记录请求开始时间
			request.setAttribute("_request_start_time_", System.currentTimeMillis());
			// 变量初始化
			String _returnType_ = ZERO;// 页面返回模式, 0：page页面直接返回,1:JSON数据拦截返回
			MyControllerSupport controller = null;
			// 获得接口返回类型
			if (handler instanceof HandlerMethod) {
				HandlerMethod h = (HandlerMethod) handler;
				if ("RESTResultBean".equals(h.getMethod().getReturnType().getSimpleName())) {
					_returnType_ = ONE;
				}
				if (h.getBean() instanceof MyControllerSupport) {
					controller = (MyControllerSupport) h.getBean();
				}
			}
			// 防火墙检测
			if (fireWallFlag) {
				Jedis jedis = getJedis();
				try {
					handle = doFireWall(_returnType_, jedis, request, response);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (jedis != null)
						jedis.close();
				}
			}
			//TODO 切分用户是否登录并点击
			
			// 登录检测
			if (controller != null) {
				if (controller.doCheckLogin(request, response) == false) {
					controller.doTokenFail(request, response, _returnType_);
					handle = false;
				}
			}
		}
		return handle;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if ((logDataFlag) && (handler instanceof HandlerMethod)) {
			try {
				// 计算
				long startTime = (Long) request.getAttribute("_request_start_time_");
				long endTime = System.currentTimeMillis();
				long executeTime = endTime - startTime;

				doLogData(request, response, (HandlerMethod) handler, executeTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 制作请求日志
	 * 
	 * @param elkLogCache
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	public boolean doLogData(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, long executeTime) throws Exception {
		ELKLogData eld = new ELKLogData("用户请求日志跟踪");

		eld.setIpAdress(HttpRequestHelper.getClientRemoteIPAddr(request));//请求地址
		
		eld.setCountType(request.getRequestURI());// 统计分类;平台
		eld.setBusinessName(HttpRequestHelper.getServerAddr(request));// 业务名称;

		eld.setServiceType(handler.getBean().getClass().getSimpleName());// 功能名称;控制层类名称
		eld.setFunctionName(handler.getMethod().getName());// 功能名称;控制层方法名称
		
		eld.setComment(request.getParameterMap());
		eld.setExplain1(executeTime + " ms");

		eld.loadELKLoggerAppender().debug();
		return true;
	}

	/**
	 * 防火墙控制
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean doFireWall(String returnType, Jedis fireWallCache, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int min = Calendar.getInstance().get(Calendar.MINUTE);
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int sec = Calendar.getInstance().get(Calendar.SECOND);
		// 每3分钟更新一次
		if (min % 3 == 0 && sec < 15) {
			// 提示信息
			String msg = (String) fireWallCache.get(FIREWALL_PATH_MESSAGE);
			if (EmptyHelper.isNotEmpty(msg))
				message = msg;
			// 拦截参数
			String config = (String) fireWallCache.get(FIREWALL_CONFIG);
			if (EmptyHelper.isNotEmpty(config))
				setConfigNum(config);
		}
		String requestPath = request.getRequestURI();
		String ipAddress = HttpRequestHelper.getClientRemoteIPAddr(request);
		// ipAddress = "177.85.92.136";
		String requestIpKey = "REQUEST_IP:" + ipAddress;
		String lastTime = (String) fireWallCache.get(requestIpKey);

		// 检查黑白名单
		{
			String safeIp = (String) fireWallCache.get("SAFETY_IP:" + ipAddress);
			if (EmptyHelper.isNotEmpty(safeIp)) {
				return true;
			}
			String dangerIp = (String) fireWallCache.get("DANGER_IP:" + ipAddress);
			if (EmptyHelper.isNotEmpty(dangerIp)) {
				doFireWallFail(request, response);
				return false;
			}
			String path = (String) fireWallCache.get("SAFE_PATH:" + requestPath);
			if (EmptyHelper.isEmpty(path) == true) {
				return true;
			}
		}

		if (EmptyHelper.isEmpty(lastTime)) {
			// IP:频率:拉黑次数
			fireWallCache.set(requestIpKey, System.currentTimeMillis() + DOWN_LINE + 1 + DOWN_LINE + 1 + DOWN_LINE + System.currentTimeMillis());
			// 设定时间
			fireWallCache.expire(requestIpKey, size);
		} else {
			// 开始拦截
			{
				String[] last = lastTime.split(DOWN_LINE);
				long lsp = Long.parseLong(last[0]);// 最后请求时间
				int nsp = Integer.parseInt(last[1]);// 请求次数
				int hsp = Integer.parseInt(last[2]);// 拒绝次数
				long fsp = Long.parseLong(last[3]);// 最后拦截时间
				long sp = System.currentTimeMillis() - lsp;// 间隔时间
				long ssp = System.currentTimeMillis() - fsp;// 统计间隔时间

				// 策略1、1秒内不能访问2次
				if (nsp >= 4 * waitTimeSecond && sp <= 1000 * waitTimeSecond * hsp) {
					if (hsp <= maxSize)
						hsp = hsp + 1;
					fireWallCache.set(requestIpKey, System.currentTimeMillis() + DOWN_LINE + 1 + DOWN_LINE + hsp + DOWN_LINE + System.currentTimeMillis());
					fireWallCache.expire(requestIpKey, size * hsp);
					doFireWallFail(request, response);
					return false;
				}
				// 策略2、5秒内不能访问3次，强制初始化规则
				if (nsp >= 15 * waitTimeSecond && ssp <= 5000 * waitTimeSecond * hsp) {
					// 黑名单检测
					if (hsp <= maxSize)
						hsp = hsp + 1;
					fireWallCache.set(requestIpKey, System.currentTimeMillis() + DOWN_LINE + 1 + DOWN_LINE + hsp + DOWN_LINE + System.currentTimeMillis());
					fireWallCache.expire(requestIpKey, size * hsp);
					doFireWallFail(request, response);
					return false;
				}
				// 策略3、累积请求次数
				if (nsp >= 80 && ssp >= 1000 * 60 * 60 * waitTimeSecond * hsp) {
					// 用户ID判断
					if (EmptyHelper.isEmpty(request.getParameter("UserID")) || EmptyHelper.isEmpty(request.getParameter("userid"))) {
						// 强制拉黑
						fireWallCache.set("DANGER_IP:" + ipAddress, "" + lsp);
						doFireWallFail(request, response);
						return false;
					} else if (nsp >= 150) {
						// 黑名单检测
						if (hsp <= maxSize)
							hsp = hsp + 1;

						fireWallCache.set(requestIpKey, System.currentTimeMillis() + DOWN_LINE + 1 + DOWN_LINE + hsp + DOWN_LINE + System.currentTimeMillis());
						fireWallCache.expire(requestIpKey, size * hsp);
						doFireWallFail(request, response);
						return false;
					} else if ((hour > 7 && hour < 22) == false) {
						// 黑名单检测
						if (hsp <= maxSize)
							hsp = hsp + 1;

						// 强制拉黑
						fireWallCache.set("DANGER_IP:" + ipAddress, "" + lsp);
						doFireWallFail(request, response);
						return false;
					}
				}
				// 策略4、拉黑次数超过，将继续放大
				if (hsp >= maxSize) {
					fireWallCache.set(requestIpKey, System.currentTimeMillis() + DOWN_LINE + 1 + DOWN_LINE + hsp + DOWN_LINE + System.currentTimeMillis());
					fireWallCache.expire(requestIpKey, size * waitTimeSecond * hsp);
					doFireWallFail(request, response);
					return false;
				}
				fireWallCache.set(requestIpKey, System.currentTimeMillis() + DOWN_LINE + (nsp + 1) + DOWN_LINE + hsp + DOWN_LINE + fsp);
				fireWallCache.expire(requestIpKey, size * hsp);
			}
		}
		return true;
	}

	public void doFireWallFail(HttpServletRequest request, HttpServletResponse response) {
		try {

			// 销毁session
			// request.getSession().invalidate();
			// response.sendRedirect("/joke.html");
			// response.sendError(503);
			response.setCharacterEncoding(SYSTEM_CHARSET);
			response.setContentType("text/html;charset=UTF-8");
			response.getOutputStream().write(message.getBytes());
			// 形式： sendError(int errnum)说明：用来向客户端发送错误信息，这对调试程序有很大帮助。常用的常量级错误代码有：
			// SC_CONTINUE， 状态码是100，表示客户端无法连接。
			// SC_SWITHING_PROTOCOLS,状态码是101，表示服务器正向报头中注明的协议切换。
			// SC_OK，状态码是200.表示请求被成功处理。
			// SC_CREATED，状态码是201，表示请求被成功处理，并在服务器方创建了一个新的资源。
			// SC_ACCEPTED，状态码是202，表示请求正在被处理，但尚未完成。
			// SC_NON_AUTHORITATIVE_INFORMATION，状态码是203，表示客户端所表达的mate信息并非来自服务器。
			// SC_NO_CONTENT，状态码是204，表示请求被成功处理，但没有新的信息返回。
			// SC_RESET_CONTENT,状态码是205，表示导致请求被发送的文档视图应该重置。
			// SC_PARTIAL_CONTENT,状态码是206，表示服务器已经完成对资源的GET请求。
			// SC_MULTI_CHOICES，状态码是300，表示对应于一系列表述的被请求资源都有明确的位置。
			// SC_MOVED_PERMANENTLY，状态码是301，表示请求所申请的资源已经被移到一个新的地方，并且将来的参考点在请求中应当使用一个新的URL.
			// SC_MOVED_TEMPORARILY,状态码是302，表示请求所申请的资源已经被移到一个新的地方，并且将来的参考点在请求中仍使用原来的URL.
			// SC_SEE_OTHER,状态码是303，表示请求的响应可以在一个不同的URL中找到。
			// SC_NOT_MODIFIED，状态码是304，表示一个有条件的GET操作发现资源可以利用，且没有被改变。
			// SC_USE_PROXY，状态码是305，表示被请求的资源必须通过特定位置的代理来访问。
			// SC_BAD_REQUEST，状态码是400，表示客户发出的请求句法不正确。
			// SC_UNAUTHORIZED,状态码是401，表示请求HTTP认证。
			// SC_PAYMENT_REQUIRED，状态码是402，表示为以后的使用保留。
			// SC_FORBIDDEN，状态码是403，表示服务器明白客户的请求，但拒绝响应。
			// SC_NOT_FAND，状态码是404，表示所请求的资源不可用。
			// SC_METHOD_NOT_ALLOWED,状态码是405，表示在请求行中标示的方法不允许对请求URL所标明的资源使用。
			// SC_NOT_ACCEPTTABLE,状态码是406，表示被请求的资源只能响应实体，而且此符合请求所发送的可接受头部域的实体的确包含不可接受的内容。
			// SC_PHOXY_AUTHENTICATION_REQUIRED,状态码是407，表示客户端必须先向代理验证。
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

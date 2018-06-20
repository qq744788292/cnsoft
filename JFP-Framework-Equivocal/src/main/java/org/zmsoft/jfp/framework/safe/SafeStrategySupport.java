package org.zmsoft.jfp.framework.safe;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.jfp.framework.cache.client.ClientHelper;
import org.zmsoft.jfp.framework.cache.redis.master.JedisMasterUtil;
import org.zmsoft.jfp.framework.client.ClientBusinessSupport;
import org.zmsoft.jfp.framework.elk.ELKLogData;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.framework.support.MyInterceptorAdapterSupport;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.framework.utils.HttpRequestHelper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

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
public class SafeStrategySupport extends MyInterceptorAdapterSupport {
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

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean handle = true;
		if (handler instanceof HandlerMethod) {// 判断资源路径
			// 记录请求开始时间
			request.setAttribute("_request_start_time_", System.currentTimeMillis());
			// 记录用户信息
			{
				ClientBusinessSupport clent = new ClientBusinessSupport();
				// 用户IP地址
				clent.setClientIp(HttpRequestHelper.getClientRemoteIPAddr(request));
				// 访问域名路径
				clent.setSourceAddress(HttpRequestHelper.getServerAddr(request));
				// 完整路径
				clent.setReferralLink(request.getRequestURL().toString());
				ClientHelper.setClientBusinessSupport(clent);
			}
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
			// 切分用户是否登录并点击

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

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
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
	public boolean doLogData(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler,
			long executeTime) throws Exception {
		ELKLogData eld = new ELKLogData("用户请求日志跟踪");

		eld.setIpAdress(HttpRequestHelper.getClientRemoteIPAddr(request));// 请求地址

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
	public boolean doFireWall(String returnType, Jedis fireWallCache, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
				doFail(request, response, message);
				return false;
			}
			String path = (String) fireWallCache.get("SAFE_PATH:" + requestPath);
			if (EmptyHelper.isEmpty(path) == true) {
				return true;
			}
		}

		if (EmptyHelper.isEmpty(lastTime)) {
			// IP:频率:拉黑次数
			fireWallCache.set(requestIpKey, System.currentTimeMillis() + DOWN_LINE + 1 + DOWN_LINE + 1 + DOWN_LINE
					+ System.currentTimeMillis());
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
					fireWallCache.set(requestIpKey, System.currentTimeMillis() + DOWN_LINE + 1 + DOWN_LINE + hsp
							+ DOWN_LINE + System.currentTimeMillis());
					fireWallCache.expire(requestIpKey, size * hsp);
					doFail(request, response, message);
					return false;
				}
				// 策略2、5秒内不能访问3次，强制初始化规则
				if (nsp >= 15 * waitTimeSecond && ssp <= 5000 * waitTimeSecond * hsp) {
					// 黑名单检测
					if (hsp <= maxSize)
						hsp = hsp + 1;
					fireWallCache.set(requestIpKey, System.currentTimeMillis() + DOWN_LINE + 1 + DOWN_LINE + hsp
							+ DOWN_LINE + System.currentTimeMillis());
					fireWallCache.expire(requestIpKey, size * hsp);
					doFail(request, response, message);
					return false;
				}
				// 策略3、累积请求次数
				if (nsp >= 80 && ssp >= 1000 * 60 * 60 * waitTimeSecond * hsp) {
					// 用户ID判断
					if (EmptyHelper.isEmpty(request.getParameter("UserID"))
							|| EmptyHelper.isEmpty(request.getParameter("userid"))) {
						// 强制拉黑
						fireWallCache.set("DANGER_IP:" + ipAddress, "" + lsp);
						doFail(request, response, message);
						return false;
					} else if (nsp >= 150) {
						// 黑名单检测
						if (hsp <= maxSize)
							hsp = hsp + 1;

						fireWallCache.set(requestIpKey, System.currentTimeMillis() + DOWN_LINE + 1 + DOWN_LINE + hsp
								+ DOWN_LINE + System.currentTimeMillis());
						fireWallCache.expire(requestIpKey, size * hsp);
						doFail(request, response, message);
						return false;
					} else if ((hour > 7 && hour < 22) == false) {
						// 黑名单检测
						if (hsp <= maxSize)
							hsp = hsp + 1;

						// 强制拉黑
						fireWallCache.set("DANGER_IP:" + ipAddress, "" + lsp);
						doFail(request, response, message);
						return false;
					}
				}
				// 策略4、拉黑次数超过，将继续放大
				if (hsp >= maxSize) {
					fireWallCache.set(requestIpKey, System.currentTimeMillis() + DOWN_LINE + 1 + DOWN_LINE + hsp
							+ DOWN_LINE + System.currentTimeMillis());
					fireWallCache.expire(requestIpKey, size * waitTimeSecond * hsp);
					doFail(request, response, message);
					return false;
				}
				fireWallCache.set(requestIpKey,
						System.currentTimeMillis() + DOWN_LINE + (nsp + 1) + DOWN_LINE + hsp + DOWN_LINE + fsp);
				fireWallCache.expire(requestIpKey, size * hsp);
			}
		}
		return true;
	}

}

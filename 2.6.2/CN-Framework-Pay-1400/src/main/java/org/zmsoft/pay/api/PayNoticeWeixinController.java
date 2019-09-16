package org.zmsoft.pay.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.framework.pay.ISOrderCheck;
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.pay.bean.WxPayCallBackBean;
import org.zmsoft.pay.service.CheckOrderService;
import org.zmsoft.pay.service.PayNoticeService;

/**
 * 活动赛选页面接口
 * 
 * @version 0.1.1
 * @since 0.1.1
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PayNoticeWeixinController extends MyTokenCommonSupport { // MyTokenCommonSupport

	@Resource(name = "WeixinOrderCheckService")
	private ISOrderCheck myWeixinOrderCheckService;
	@Resource
	private CheckOrderService myCheckOrderService;
	@Resource
	private PayNoticeService myPayNoticeService;

	@RequestMapping(value = "/api/1.0/pay/txnotice", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doTxpayNotice(HttpServletRequest request, HttpServletResponse response, String ordertoken, WxPayCallBackBean weiXinCallBack) throws Exception {
		return doTxChannelNotice(request, response, ordertoken, weiXinCallBack);
	}

	// 到账通知
	@RequestMapping(value = "/api/1.0/pay/txnotice/{ordertoken}", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doTxChannelNotice(HttpServletRequest request, HttpServletResponse response, @PathVariable String ordertoken, WxPayCallBackBean weiXinCallBack) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/pay/pay-result-wx");
		// 通知结果
		String orderCode = weiXinCallBack.getSdcustomno();
		// 支付成功
		if (ONE.equals(weiXinCallBack.getState())) {
			// 检查订单时长
			if (myCheckOrderService.checkOrder(ordertoken, orderCode) == false) {
				logger.warn("channelNoticeProcess=====合法性验证失败>>>>>" + orderCode);
			}
			// 订单支付检查
			else if (myWeixinOrderCheckService.doUserOrderCheck(weiXinCallBack)) {
				// 异步处理
				myPayNoticeService.channelNoticeProcess(TWO, orderCode, orderCode);
			}
		} else {
			logger.warn("doAliChannelNotice=====签名验证失败>>>>>" + weiXinCallBack);
		}
		modelAndView.addObject("respCode", "1");
		return modelAndView;
	}

}

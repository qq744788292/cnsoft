package org.zmsoft.pay.api.notice;

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
import org.zmsoft.framework.support.MyTokenCommonSupport;
import org.zmsoft.pay.bean.AliPayCallBackBean;
import org.zmsoft.pay.service.PayNoticeService;
import org.zmsoft.pay3.ali.check.AliOrderCheckService;

/**
 * 活动赛选页面接口
 * 
 * @version 0.1.1
 * @since 0.1.1
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping(value = "/pay/1.0/notice", method = {  RequestMethod.GET, RequestMethod.POST })
public class PayNoticeAliController extends MyTokenCommonSupport { // MyTokenCommonSupport

	@Resource
	private AliOrderCheckService myAliOrderCheckService;
	@Resource
	private PayNoticeService myPayNoticeService;

	@RequestMapping(value = "/ali", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doAlipayNotice(HttpServletRequest request, HttpServletResponse response, String ordertoken, AliPayCallBackBean aliPayCallBack) throws Exception {
		return doAliChannelNotice(request, response, ordertoken, aliPayCallBack);
	}

	// 到账通知
	@RequestMapping(value = "/api/1.0/pay/alinotice/{ordertoken}", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doAliChannelNotice(HttpServletRequest request, HttpServletResponse response, @PathVariable String ordertoken, AliPayCallBackBean aliPayCallBack) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/pay/pay-result-ali");
		// 通知结果
		String orderCode = aliPayCallBack.getOut_trade_no();
		// 支付成功
		// if(trade_status.equals("TRADE_FINISHED") ||
		// trade_status.equals("TRADE_SUCCESS")){
		if ("TRADE_SUCCESS".equals(aliPayCallBack.getTrade_status())) {
//			// 检查订单时长
//			if (myPayNoticeService.checkOrder(ordertoken, orderCode) == false) {
//				logger.warn("channelNoticeProcess=====合法性验证失败>>>>>" + orderCode);
//			}
//			// 订单支付检查
//			else if (myAliOrderCheckService.doUserOrderCheck(aliPayCallBack)) {
//				//异步处理
//				myPayNoticeService.channelNoticeProcess(ONE, orderCode, orderCode);
//			}
		} else {
			logger.warn("doAliChannelNotice=====签名验证失败>>>>>" + aliPayCallBack);
		}
		modelAndView.addObject("respCode", "验证成功");
		return modelAndView;
	}

}

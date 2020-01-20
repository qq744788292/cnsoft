package org.zmsoft.pay;

/**
 * 在线支付
 * @author Spook
 * @version 0.0.1 2017/09/11
 * @since 0.0.1 2017/09/11
 */
public class ReadMe {

	//第三方支付路由：统一下单、用户支付、到账检查和通知
	
	//1--下单流程
	//	1.1 	下单接口响应 			UserOrderPayController.doCreatOrder					（框架实现）
	//	1.2 	订单创建 			OrderFormService.creatOrder							（框架实现）【逻辑控制】
	//	1.3 	第三方订单创建 		MyChannelOrderPaySupport.creatOrderProcess			（框架实现）
	//	1.4		订单持久化保存		ISPayOrderPersistentService							（业务实现）
	
	//2--用户支付
	//	2.1 	根据返回PayOrderBean.data进行支付参数整理									（框架实现）
	//	2.2 	客户端拉起第三方支付														（框架实现）
	
	//3--第三方到账通知
	//	3.1		到账通知响应接口		PayNoticeAliController/PayNoticeWeixinController	（框架实现）
	//	3.2		订单支付结果检查		CheckOrderService.checkOrderProcess					（框架实现）
	//	3.3		支付到账通知			PayNoticeService.channelNoticeProcess				（框架实现）【逻辑控制】
	//	3.4		物品发货通知			PayNoticeService.noticeAfterProcess					（业务实现）
	//	3.5		订单持久化保存		ISPayOrderPersistentService							（业务实现）
}

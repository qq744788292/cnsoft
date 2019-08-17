package org.zmsoft.framework.weixin.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.constants.IBussinessConstants;
import org.zmsoft.framework.support.MyWeixinCommonSupport;
import org.zmsoft.framework.weixin.bean.WxUserBean;

/**
 * 获取用户基本信息(UnionID机制)
 * 
 * @author fcy
 *
 */
@Component("WeixinPlayerInfoLoadService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WeixinPlayerInfoLoadService extends MyWeixinCommonSupport implements IBussinessConstants {

	// 在关注者与公众号产生消息交互后，公众号可获得关注者的OpenID
	// （加密后的微信号，每个用户对每个公众号的OpenID是唯一的。对于不同公众号，同一用户的openid不同）。
	// 公众号可通过本接口来根据OpenID获取用户基本信息，包括昵称、头像、性别、所在城市、语言和关注时间。
	// https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
	@Async("threadPoolTaskExecutor")
	// TODO
	public void doLoad(WxUserBean user) {
		// MQ_TOPIC_WEIXIN
		//检查最后获取日期 getClientTimestamp
		
	}
}

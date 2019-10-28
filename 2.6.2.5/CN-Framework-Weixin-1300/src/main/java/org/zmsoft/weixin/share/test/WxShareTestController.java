package org.zmsoft.weixin.share.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.framework.support.MyTokenCommonSupport;

/**
 * 用户组管理
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/3/1
 * @since 0.1.0 2018/3/1
 */
@Controller
@RequestMapping(value = "/page/1.0/wxshare", method = { RequestMethod.GET})
public class WxShareTestController extends MyTokenCommonSupport {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView doTest() throws Exception {
		ModelAndView modelAndView = new ModelAndView("/weixin/WXShareTest");

		return modelAndView;
	}

}

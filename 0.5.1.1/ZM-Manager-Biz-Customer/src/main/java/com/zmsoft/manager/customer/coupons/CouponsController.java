package com.zmsoft.manager.customer.coupons;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.persistent.activity.C7110Coupon.C7110CouponDBO;
import org.zmsoft.jfp.persistent.activity.C7110Coupon.C7110CouponPVO;
import org.zmsoft.jfp.persistent.activity.C7110Coupon.C7110CouponService;

@Controller
public class CouponsController extends MyControllerSupport implements IFrameworkConstants{
	@Resource
	C7110CouponService c7110CouponService;
    @Resource
    CouponsBiz couponsBiz;
    /**
	 * 券码列表 列表/搜索
	 * @author 李小锋
	 */
	@RequestMapping(value = "/4040", method = RequestMethod.POST)
	public ModelAndView doSelectPage80102010(C7110CouponPVO c7110CouponPVO, PageModel<C7110CouponDBO> pageModel, RESTResultBean<String> message,String type) throws Exception {
		ModelAndView model = getModelAndView("/couponsManager/coupons-list");
		if (pageModel.getPageCurrent()==0){
			pageModel.setPageCurrent(1);
		}
		if (pageModel.getPageLimit()==2147483647){
			pageModel.setPageLimit(15);
		}
		c7110CouponPVO.setDelFlag(ZERO);//设置查询条件:删除标记为0的记录
//		C7110CouponPVO_.setWriteoffStatus(ZERO);//设置核销状态为0未核销
		pageModel.setFormParamBean(c7110CouponPVO);
        pageModel.setResultCountFlag(true);//开启总条数查询,总页数查询
        couponsBiz.doSelectPageByTime(pageModel,type);

		model.addObject("page", pageModel);
		model.addObject("searchCondition", c7110CouponPVO);
		model.addObject("message", message);

		return model;
	}
	

}

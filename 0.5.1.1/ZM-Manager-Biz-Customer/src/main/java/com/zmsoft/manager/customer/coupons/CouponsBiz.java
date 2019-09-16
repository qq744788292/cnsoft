package com.zmsoft.manager.customer.coupons;

import org.springframework.stereotype.Service;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.common.ISearchDatePrepare;
import org.zmsoft.jfp.framework.support.MyBusinessSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.persistent.activity.C7110Coupon.C7110CouponDBO;
import org.zmsoft.jfp.persistent.activity.C7110Coupon.C7110CouponService;

import com.alibaba.fastjson.JSONObject;

@Service("CouponBiz")
public class CouponsBiz extends MyBusinessSupport<JSONObject,Object>{
	/**
	 * 券码列表 显示/查询
	 * @author 李小锋
	 */
	public void doSelectPageByTime(PageModel<C7110CouponDBO> pageModel, String type) throws Exception {
		C7110CouponService C7110CouponService_ = BeanFactoryHelper.getBean("C7110CouponService");
		this.dateConvert((ISearchDatePrepare) pageModel.currentFormParamBean(), type); // 处理日期
		C7110CouponService_.doSelectPageByTime(pageModel);
	}
}

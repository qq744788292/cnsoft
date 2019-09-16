package com.zmsoft.manager.customer.activityManager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.framework.utils.PKHelper;
import org.zmsoft.jfp.persistent.activity.A403010ActivityType.A403010ActivityTypeDBO;
import org.zmsoft.jfp.persistent.activity.A403010ActivityType.A403010ActivityTypeService;
import org.zmsoft.jfp.persistent.activity.A403020Activity.A403020ActivityDBO;
import org.zmsoft.jfp.persistent.activity.A403020Activity.A403020ActivityService;
import org.zmsoft.jfp.persistent.activity.A403030ActivityPrize.A403030ActivityPrizeDBO;
import org.zmsoft.jfp.persistent.activity.A403030ActivityPrize.A403030ActivityPrizeService;
import org.zmsoft.jfp.persistent.activity.C7110Coupon.C7110CouponDBO;
import org.zmsoft.jfp.persistent.activity.C7110Coupon.C7110CouponService;
import org.zmsoft.jfp.persistent.activity.U104020ActivityUser.U104020ActivityUserDBO;
import org.zmsoft.jfp.persistent.activity.U104020ActivityUser.U104020ActivityUserService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
public class ActivityManagerController extends MyControllerSupport implements IFrameworkConstants{
	@Resource
	A403020ActivityService a403020ActivityService;
	@Resource
	A403030ActivityPrizeService a403030ActivityPrizeService;
	@Resource
	A403010ActivityTypeService a403010ActivityTypeService;
	@Resource
	C7110CouponService c7110CouponService;
	@Resource
	U104020ActivityUserService u104020ActivityUserService;
	/**
	 * 活动管理
	 * @author 李小锋
	 */
	@RequestMapping(value = "/202030", method = RequestMethod.POST)
	public ModelAndView selectActivityManagerA403020(A403020ActivityDBO a403020ActivityDBO, PageModel<A403020ActivityDBO> pageModel, RESTResultBean<String> message)throws Exception{
		ModelAndView model = getModelAndView("/activityManager/activity-manager-list");
		if (pageModel.getPageLimit()==2147483647){
			pageModel.setPageLimit(10);
		}
		a403020ActivityDBO.setDelFlag(ZERO);//有效标识
		pageModel.setFormParamBean(a403020ActivityDBO);
		pageModel.setResultCountFlag(true);
		a403020ActivityService.doSelectPage(pageModel);//分页查询
		model.addObject("list", pageModel);
		model.addObject("searchCondition", a403020ActivityDBO);
		model.addObject("message", message);
		return model;
	}
	
	/**
	 * 上线/下线管理
	 * @author 李小锋
	 */
	@RequestMapping(value = "/20203010", method=RequestMethod.POST)
	public ModelAndView is_senedActivityA403020(A403020ActivityDBO a403020ActivityDBO, RESTResultBean<String> message)throws Exception{
		A403020ActivityDBO A403020ActivityDBO=new A403020ActivityDBO();
		if(ONE.equals(a403020ActivityDBO.getIsSened())){
			System.out.println(1);
			A403020ActivityDBO.setPuk(a403020ActivityDBO.getPuk());
			A403020ActivityDBO.setIsSened(TWO);
			a403020ActivityService.doUpdate(A403020ActivityDBO);
			message.setMsg(MESSAGE_DB_UPDATE);
			message.setCode(ONE);
		}else{
			A403020ActivityDBO.setPuk(a403020ActivityDBO.getPuk());
			A403020ActivityDBO.setIsSened(ONE);
			System.out.println(2);
			a403020ActivityService.doUpdate(A403020ActivityDBO);
			message.setMsg(MESSAGE_DB_UPDATE);
			message.setCode(ONE);
		}
		
		return selectActivityManagerA403020(new A403020ActivityDBO(),new PageModel<A403020ActivityDBO>(),message);
	}
	
	/**
	 * 活动查看
	 * @author 李小锋
	 */
	@RequestMapping(value = "/20203020", method=RequestMethod.POST)
	public ModelAndView toViewActivityA403020(A403020ActivityDBO a403020ActivityDBO)throws Exception{
		ModelAndView model = getModelAndView("/activityManager/activity-toview");
		if(EmptyHelper.isNotEmpty(a403020ActivityDBO.getPuk())){
			model.addObject("data", a403020ActivityService.doSelectData(a403020ActivityDBO).get(0));
			A403030ActivityPrizeDBO a403030ActivityPrizeDBO=new A403030ActivityPrizeDBO();
			a403030ActivityPrizeDBO.setActivityId(a403020ActivityDBO.getPuk());//根据活动id查询
			List<A403030ActivityPrizeDBO> list = a403030ActivityPrizeService.doSelectData(a403030ActivityPrizeDBO);//查询对应活动的礼物
			model.addObject("list", list);
		}
		return model;
	}
	
/*	*//**
	 * 编辑活动
	 * @author 李小锋
	 *//*
	@RequestMapping(value = "/20203030", method=RequestMethod.POST)
	public ModelAndView editActivityA403020(A403020ActivityDBO a403020ActivityDBO, int JumpType)throws Exception{
		ModelAndView model = getModelAndView("/activityManager/activity-edit");
		if(JumpType == 2){
			A403020ActivityDBO data = a403020ActivityService.doSelectData(a403020ActivityDBO).get(0);
			model.addObject("data", data);
		}
		List<A403010ActivityTypeDBO> activityTypeList = a403010ActivityTypeService.doSelectActivityType(new A403010ActivityTypeDBO());//查询活动分类列表
		model.addObject("activityTypeList", activityTypeList);
		model.addObject("JumpType", JumpType);
		return model;
	}*/
	
	/**
	 * 活动--下一步
	 * @author 李小锋
	 */
	@RequestMapping(value = "/20203031", method=RequestMethod.POST)
	public ModelAndView nextEditActivityA403020(A403020ActivityDBO a403020ActivityDBO, int JumpType,RESTResultBean<String> message)throws Exception{
		ModelAndView model = getModelAndView("/activityManager/activity-edit");
		if(JumpType==1){
			String puk = PKHelper.creatPUKey();
			a403020ActivityDBO.setPuk(puk);
			a403020ActivityDBO.setCreator(SYSTEM);
			a403020ActivityService.doInsert(a403020ActivityDBO);
		}else if(JumpType==2){
			a403020ActivityService.doUpdate(a403020ActivityDBO);
		}
		String activityTypeType = a403020ActivityDBO.getActivityTypeType();
		if("1000".equals(activityTypeType)){//1000日常签到
			model = getModelAndView("/activityManager/activity-setGift");
			if(JumpType==2){
				A403030ActivityPrizeDBO a403030ActivityPrizeDBO = new A403030ActivityPrizeDBO();
				a403030ActivityPrizeDBO.setActivityId(a403020ActivityDBO.getPuk());
				a403030ActivityPrizeDBO.setDelFlag(ZERO);
				List<A403030ActivityPrizeDBO> list = a403030ActivityPrizeService.doSelectData(a403030ActivityPrizeDBO);
				model.addObject("activityPrizeList", list);
			}
			model.addObject("data", a403020ActivityDBO);
		}else if("2000".equals(activityTypeType)){//2000团体活动
			model=getModelAndView("/activityManager/activity-forTeam");
			if(JumpType==2){
				A403020ActivityDBO list = a403020ActivityService.doSelectData(a403020ActivityDBO).get(0);
				model.addObject("data",list );
			}
		}else if("3000".equals(activityTypeType)){//3000优惠券码
			model=getModelAndView("/activityManager/activity-coupons");
			if(JumpType==2){
				A403020ActivityDBO list = a403020ActivityService.doSelectData(a403020ActivityDBO).get(0);
				model.addObject("data",list );
			}
		}else{
			message.setCode(ONE);
			message.setMsg("活动模式不正确.请重新创建");
			model.addObject("message", message);
			return toPage20203060A403020(new A403020ActivityDBO(),1);
		}
		
		model.addObject("JumpType", JumpType);

		return model;
	}
	
	/**
	 *  3000 优惠券码--下一步
	 * @author 李小锋
	 */
	@RequestMapping(value = "/2020303110", method = RequestMethod.POST)
	public ModelAndView doUpdateCouponsA403020(C7110CouponDBO c7110CouponDBO, RESTResultBean<String> message, int JumpType)throws Exception{
		ModelAndView model = getModelAndView("/activityManager/activity-share");
		c7110CouponDBO.setWriteoffStatus(ZERO);

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(c7110CouponDBO.getActivityNo());//2018.2.1 增加活动编号
		stringBuilder.append(c7110CouponDBO.getTicketType());
		stringBuilder.append(c7110CouponDBO.getActivityType());

		// pv1
		if (EmptyHelper.isEmpty(c7110CouponDBO.getPv1()))
			return null;

		JSONArray datas = JSONArray.parseArray(c7110CouponDBO.getPv1());
		JSONObject item;
		String rewardLevel;
		// 判断优惠券类别
		for (int i = 0; i < datas.size(); i++) {

			item = datas.getJSONObject(i);
			List<String> codes = LotteryBiz.generateCode(item.getIntValue("rewardCount"));
			rewardLevel = item.getString("rewardLevel");
			// 根据数量生产
			for (String code : codes) {
				c7110CouponDBO.setTicketNumber(stringBuilder.toString() + rewardLevel + code);

				c7110CouponDBO.setRewardLevel(item.getString("rewardLevel"));
				String[] ds = item.getString("rewardName").split(SEMICOLON);
				c7110CouponDBO.setFb1(ds[0]);
				c7110CouponDBO.setRewardName(ds[1]);

				c7110CouponDBO.makePuk();
				c7110CouponService.doInsert(c7110CouponDBO);
			}
		}
		message.setMsg("签到奖品添加成功!");
		if (JumpType==2) {
			A403020ActivityDBO A403020ActivityDBO=new A403020ActivityDBO();
			A403020ActivityDBO.setPuk(A403020ActivityDBO.getActivityNumber());
			A403020ActivityDBO.setDelFlag(ZERO);
	         model.addObject("data", a403020ActivityService.doSelectData(A403020ActivityDBO).get(0));
	    }
		model.addObject("message", message);

		return model;
	}
	
	/**
	 * 显示已有券码
	 * @author 李小锋
	 */
	@RequestMapping(value = "/2020303120", method = RequestMethod.GET)
	public ModelAndView showCouponsListA403020(C7110CouponDBO c7110CouponDBO, PageModel<C7110CouponDBO> pageModel, RESTResultBean<String> message)throws Exception{
		ModelAndView model = getModelAndView("/activityManager/activity-coupons-list");
		c7110CouponDBO.setDelFlag(ZERO);// 设置查询条件:删除标记为0的记录
		pageModel.setFormParamBean(c7110CouponDBO);
        pageModel.setResultCountFlag(true);// 开启总条数查询,总页数查询
        c7110CouponService.doSelectPage(pageModel);
        model.addObject("message", message);
        model.addObject("page", pageModel);
        return model;
	}
	
	/**
	 * 添加和修改--保存分享信息
	 * @author 李小锋
	 */
	@RequestMapping(value = "/80201022", method = RequestMethod.POST)
	public ModelAndView doUpdateA403020(A403020ActivityDBO a403020ActivityDBO, PageModel<A403020ActivityDBO> pageModel, RESTResultBean<String> message) throws Exception {
		
		a403020ActivityService.doUpdate(a403020ActivityDBO);
		message.setMsg("添加分享信息添加成功!");

		return selectActivityManagerA403020(new A403020ActivityDBO(), pageModel, message);
	}
	
	/**
	 *  删除券码
	 * @author 李小锋
	 */
	@RequestMapping(value = "/80201023", method = RequestMethod.GET)
	public ModelAndView removeCoupons80201023(C7110CouponDBO c7110CouponDBO, RESTResultBean<String> message)throws Exception{
		c7110CouponService.doDelete(c7110CouponDBO);
		C7110CouponDBO c7110CouponDBO1 = new C7110CouponDBO();
		c7110CouponDBO1.setActivityNo(c7110CouponDBO.getActivityNo());
		message.setMsg("删除成功!");
		return showCouponsListA403020(c7110CouponDBO1, new PageModel<C7110CouponDBO>(), message);
	}
	
	/**
	 * 查看数据
	 * @author 李小锋
	 */
	@RequestMapping(value = "/20203040", method = RequestMethod.POST)
	public ModelAndView checkDataA403020(A403020ActivityDBO a403020ActivityDBO, PageModel<U104020ActivityUserDBO> pageModel)throws Exception{
		ModelAndView model = getModelAndView("/activityManager/check-data");
		// 根据活动id到活动用户表查找相关用户返回
		if(EmptyHelper.isNotEmpty(a403020ActivityDBO.getPuk())){
			U104020ActivityUserDBO u104020ActivityUserDBO = new U104020ActivityUserDBO();
			u104020ActivityUserDBO.setActivityId(a403020ActivityDBO.getPuk());
			pageModel.setPageLimit(10);
			pageModel.setFormParamBean(u104020ActivityUserDBO);
			pageModel.setResultCountFlag(true);
			PageModel<U104020ActivityUserDBO> page = u104020ActivityUserService.doSelectPage(pageModel);
			model.addObject("data", a403020ActivityService.doSelectData(a403020ActivityDBO).get(0));
			model.addObject("page",page);
		}
		return model;
	}
	/**
	 * 删除数据
	 * @author 李小锋
	 */
	@RequestMapping(value = "/20203050", method = RequestMethod.POST)
	public ModelAndView deleteActivityA403020(A403020ActivityDBO a403020ActivityDBO, RESTResultBean<String> message)throws Exception{
		a403020ActivityService.doDelete(a403020ActivityDBO);
		//删除活动的同时删除签到奖励设置
		if("1000".equals(a403020ActivityDBO.getActivityTypeType())){
			A403030ActivityPrizeDBO a403030ActivityPrizeDBO = new A403030ActivityPrizeDBO();
			a403030ActivityPrizeDBO.setActivityId(a403020ActivityDBO.getPuk());
			a403030ActivityPrizeService.doDelete(a403030ActivityPrizeDBO);
		}
		message.setCode(ONE);
		message.setMsg(MESSAGE_DB_DELETE);
		return selectActivityManagerA403020(new A403020ActivityDBO(), new PageModel<A403020ActivityDBO>(), message);
	}
	
	/**
	 * 添加和修改--跳转
	 * JumpType=1表示添加,JumpType=2表示修改
	 * @author 李小锋
	 */
	@RequestMapping(value = "/20203060", method = RequestMethod.POST)
	public ModelAndView toPage20203060A403020(A403020ActivityDBO a403020ActivityDBO,int JumpType) throws Exception {
		ModelAndView model = getModelAndView("/activityManager/activity-edit");
		if (JumpType == 2) {
			A403020ActivityDBO data = a403020ActivityService.doSelectData(a403020ActivityDBO).get(0);
			model.addObject("data", data);
		}
		List<A403010ActivityTypeDBO> activityTypeList = a403010ActivityTypeService.doSelectActivityType(new A403010ActivityTypeDBO());//查询活动分类列表
		model.addObject("activityTypeList", activityTypeList);
		model.addObject("JumpType", JumpType);
		return model;
	}
	
	/**
	 *  添加--下一步
	 * JumpType=1表示添加,JumpType=2表示修改
	 * @author 李小锋
	 */
	@RequestMapping(value = "/20203061", method = RequestMethod.POST)
	public ModelAndView toPage20203061A403010(A403020ActivityDBO a403020ActivityDBO, int JumpType, RESTResultBean<String> message)throws Exception{
		ModelAndView model = getModelAndView("/activityManager/activity-edit");
		if(JumpType==1){
			a403020ActivityDBO.setUpdator(SYSTEM);
			a403020ActivityService.doInsert(a403020ActivityDBO);
		}else if(JumpType==2){
			a403020ActivityService.doUpdate(a403020ActivityDBO);
		}
		String activityTypeType = a403020ActivityDBO.getActivityTypeType();
		if("1000".equals(activityTypeType)){
			model = getModelAndView("/activityManager/activity-setGift");
			if(JumpType==2){
				A403030ActivityPrizeDBO a403030ActivityPrizeDBO = new A403030ActivityPrizeDBO();
				a403030ActivityPrizeDBO.setActivityId(a403020ActivityDBO.getPuk());
				a403030ActivityPrizeDBO.setDelFlag(ZERO);
				List<A403030ActivityPrizeDBO> list = a403030ActivityPrizeService.doSelectData(a403030ActivityPrizeDBO);
				model.addObject("activityPrizeList", list);
			}
		}else if("2000".equals(activityTypeType)){
			model = getModelAndView("/activityManager/activity-forTeam");
			if(JumpType==2){
				A403020ActivityDBO data = a403020ActivityService.doSelectData(a403020ActivityDBO).get(0);
				model.addObject("data", data);
			}
		}else if("3000".equals(activityTypeType)){
			model = getModelAndView("/activityManager/activity-coupons");
			if(JumpType==2){
				A403020ActivityDBO data = a403020ActivityService.doSelectData(a403020ActivityDBO).get(0);
				model.addObject("data", data);
			}
		}else{
			message.setCode(ONE);
			message.setMsg("活动模式不正确.请重新创建");
			model.addObject("message", message);
			return toPage20203060A403020(new A403020ActivityDBO(),1);
		}
		
		return model;
	}
	
	/**
	 * 发布活动--一览
	 * @author 李小锋
	 */
	@RequestMapping(value = "/202030611", method = RequestMethod.POST)
	public ModelAndView saveActivity202030611A403010(PageModel<A403020ActivityDBO> pageModel, A403020ActivityDBO a403020ActivityDBO, int JumpType, String[] prizeNumber, String[] prizeType, String[] prize1000Time)throws Exception{
		if(EmptyHelper.isNotEmpty(prizeNumber)){
			String puk = a403020ActivityDBO.getPuk();
			if(JumpType==2){
				A403030ActivityPrizeDBO a403030ActivityPrizeDBO = new A403030ActivityPrizeDBO();
				a403030ActivityPrizeDBO.setActivityId(puk);
				a403030ActivityPrizeService.doDeleteByActivityId(a403030ActivityPrizeDBO);
			}
			for(int i = 0; i < prizeNumber.length; i++){
				A403030ActivityPrizeDBO a403030ActivityPrizeDBO1 = new A403030ActivityPrizeDBO();
				a403030ActivityPrizeDBO1.setActivityId(puk);
				a403030ActivityPrizeDBO1.setNumber(prizeNumber[i]);
				a403030ActivityPrizeDBO1.setPrizeType(prizeType[i]);
				a403030ActivityPrizeDBO1.setPrize(prize1000Time[i]);
				a403030ActivityPrizeService.doInsert(a403030ActivityPrizeDBO1);
			}
		}
		RESTResultBean<String> message = new RESTResultBean<String>();
		message.setCode(ONE);
		message.setMsg(MESSAGE_DB_INSERT);

		return selectActivityManagerA403020(new A403020ActivityDBO(), pageModel, message);
	}
	
	/**
	 * 保存后添加分享
	 * @author 李小锋
	 */
	@RequestMapping(value = "/202030612", method = RequestMethod.POST)
	public ModelAndView doUpdate80201121(A403020ActivityDBO a403020ActivityDBO,int JumpType, String[] prizeNumber, String[] prizeType, String[] prize1000Time) throws Exception {
		ModelAndView model = getModelAndView("/activityManager/activity-share");

		// 签到次数奖励插入奖品表
		if (EmptyHelper.isNotEmpty(prizeNumber)) {
			String puk = a403020ActivityDBO.getPuk();
            if(JumpType==2){
            	A403030ActivityPrizeDBO a403030ActivityPrizeDBO = new A403030ActivityPrizeDBO();
            	a403030ActivityPrizeDBO.setActivityId(puk);
            	a403030ActivityPrizeService.doDeleteByActivityId(a403030ActivityPrizeDBO);
            	A403020ActivityDBO data = a403020ActivityService.doSelectData(a403020ActivityDBO).get(0);
				model.addObject("data", data);
            }
			for (int i = 0; i < prizeNumber.length; i++) {
				A403030ActivityPrizeDBO a403030ActivityPrizeDBO1 = new A403030ActivityPrizeDBO();
				a403030ActivityPrizeDBO1.setActivityId(puk);
				a403030ActivityPrizeDBO1.setNumber(prizeNumber[i]);
				a403030ActivityPrizeDBO1.setPrizeType(prizeType[i]);
				a403030ActivityPrizeDBO1.setPrize(prize1000Time[i]);
				a403030ActivityPrizeService.doInsert(a403030ActivityPrizeDBO1);
			}
		}
		model.addObject("JumpType", JumpType);

		return model;
	}
	
	/**
	 * 添加和修改--保存分享信息
	 * @author 李小锋
	 */
	@RequestMapping(value = "/202030613", method = RequestMethod.POST)
	public ModelAndView doUpdate80201022(A403020ActivityDBO a403020ActivityDBO, PageModel<A403020ActivityDBO> pageModel, RESTResultBean<String> message) throws Exception {
		a403020ActivityService.doUpdate(a403020ActivityDBO);
		message.setMsg("添加分享信息添加成功!");

		return selectActivityManagerA403020(new A403020ActivityDBO(), pageModel, message);
	}
	
	/**
	 * 添加和修改--保存团体活动线下活动  不跳转分享
	 * @author 李小锋
	 */
	@RequestMapping(value = "/202030621", method = RequestMethod.POST)
	public ModelAndView doUpdate802011221(PageModel<A403020ActivityDBO> pageModel, A403020ActivityDBO a403020ActivityDBO, RESTResultBean<String> message) throws Exception {

		a403020ActivityService.doUpdate(a403020ActivityDBO);
		message.setCode(ONE);
		message.setMsg("添加成功!");

		return selectActivityManagerA403020(new A403020ActivityDBO(), pageModel, message);
	}
	
	/**
	 * 添加和修改--保存团体活动线下活动
	 * @author 李小锋
	 */
	@RequestMapping(value = "/80201122", method = RequestMethod.POST)
	public ModelAndView doUpdate80201122(A403020ActivityDBO a403020ActivityDBO,int JumpType, RESTResultBean<String> message) throws Exception {
		ModelAndView model = getModelAndView("/activityManager/activity-share");

		a403020ActivityService.doUpdate(a403020ActivityDBO);
		message.setMsg("操作成功!");
        if (JumpType==2) {
        	A403020ActivityDBO data = a403020ActivityService.doSelectData(a403020ActivityDBO).get(0);
			model.addObject("data", data);
        }
        model.addObject("message", message);

		return model;
	}
}

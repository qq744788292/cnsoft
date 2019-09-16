package com.zmsoft.manager.customer.amms.auditingData;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.persistent.user.U101010UserInfo.U101010UserInfoDBO;
import org.zmsoft.jfp.persistent.user.U101010UserInfo.U101010UserInfoService;

import javax.annotation.Resource;

/**
 * 昵称审核
 * @author 王杰
 */
@Controller
public class AuditingNickController extends MyControllerSupport implements IFrameworkConstants{

    @Resource
    U101010UserInfoService U101010UserInfoService_;

    /**
     * 一览
     * @author 王杰
     */
    @RequestMapping(value = "/20101210",method = RequestMethod.POST)
    public ModelAndView doSelectPage20101210(U101010UserInfoDBO u101010UserDBO, PageModel<U101010UserInfoDBO> pageModel, RESTResultBean<String> message){
        ModelAndView modelAndView=getModelAndView("/customer/auditingData/auditingNick-list");
        u101010UserDBO.setDelFlag(ZERO); //设置查询条件:删除标记为0的记录
        pageModel.setFormParamBean(u101010UserDBO);
        pageModel.setResultCountFlag(true);//开启总条数查询,总页数查询
        U101010UserInfoService_.doSelectPage(pageModel);
        modelAndView.addObject("page",pageModel);
        modelAndView.addObject("searchCondition",u101010UserDBO);
        modelAndView.addObject("message",message);
        return modelAndView;
    }

    /**
     * 昵称状态修改
     * @author 王杰
     */
    @ResponseBody
    @RequestMapping(value = "/20101230",method = RequestMethod.POST)
    public RESTResultBean doUpdate20101230(U101010UserInfoDBO u101010UserInfoDBO,RESTResultBean<String> message){
        U101010UserInfoService_.doUpdate(u101010UserInfoDBO);
        message.setCode(ONE);
        message.setMsg(MESSAGE_DB_UPDATE);
        return message;
    }

    /**
     * 查看--跳转
     * @author 王杰
     */
    @RequestMapping(value = "/20101231",method = RequestMethod.POST)
    public ModelAndView see20101231(U101010UserInfoDBO u101010UserInfoDBO){
        ModelAndView modelAndView=getModelAndView("/customer/auditingData/auditingNick-see");
        modelAndView.addObject("data",U101010UserInfoService_.doRead(u101010UserInfoDBO));
        return modelAndView;
    }
}

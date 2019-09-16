package com.zmsoft.manager.customer.amms.customer.userManagerment;

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
import java.util.List;

/**
 * 用户信息
 * @author 王杰
 */

@Controller
public class UserManagermentController extends MyControllerSupport implements IFrameworkConstants{

    @Resource
    U101010UserInfoService U101010UserInfoService_;

    /**
     * 一览
     * @author 王杰
     */
    @RequestMapping(value = "/20101010",method = RequestMethod.POST)
    public ModelAndView doSelectPage20101010(U101010UserInfoDBO u101010UserDBO, PageModel<U101010UserInfoDBO> pageModel, RESTResultBean<String> message){
        ModelAndView modelAndView=getModelAndView("/customer/userManagerment/userManagerment-list");
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
     * 新增--跳转
     * @author 王杰
     */
    @RequestMapping(value = "/20101020",method = RequestMethod.POST)
    public ModelAndView add20101020(U101010UserInfoDBO u101010UserInfoDBO){
        ModelAndView modelAndView=getModelAndView("/customer/userManagerment/userManagerment-addOrModify");
        modelAndView.addObject("data",u101010UserInfoDBO);
        modelAndView.addObject("mode",ONE);
        return  modelAndView;
    }

    /**
     * 修改--跳转
     * @author 王杰
     */
    @RequestMapping(value = "/20101030",method = RequestMethod.POST)
    public ModelAndView add20101030(U101010UserInfoDBO u101010UserInfoDBO){
        ModelAndView modelAndView=getModelAndView("/customer/userManagerment/userManagerment-addOrModify");
        modelAndView.addObject("data",U101010UserInfoService_.doRead(u101010UserInfoDBO));
        modelAndView.addObject("mode",TWO);
        return  modelAndView;
    }


    /**
     * 查看--跳转
     * @author 王杰
     */
    @RequestMapping(value = "/20101031",method = RequestMethod.POST)
    public ModelAndView see20101031(U101010UserInfoDBO u101010UserInfoDBO){
        ModelAndView modelAndView=getModelAndView("/customer/userManagerment/userManagerment-see");
        modelAndView.addObject("data",U101010UserInfoService_.doRead(u101010UserInfoDBO));
        return  modelAndView;
    }

    /**
     * 新增/修改--保存
     * @author 王杰
     */
    @RequestMapping(value = "/20101050",method = RequestMethod.POST)
    public ModelAndView save20101050(U101010UserInfoDBO u101010UserInfoDBO,String mode,RESTResultBean<String> message){
        if(mode.equals(ONE)){
            u101010UserInfoDBO.setRegisterType("9");
            U101010UserInfoService_.doInsert(u101010UserInfoDBO);
            message.setCode(ONE);
            message.setMsg(MESSAGE_DB_INSERT);
        }else{
            U101010UserInfoService_.doUpdate(u101010UserInfoDBO);
            message.setCode(ONE);
            message.setMsg(MESSAGE_DB_UPDATE);
        }
        return doSelectPage20101010(new U101010UserInfoDBO(),new PageModel<U101010UserInfoDBO>(),message);
    }

    /**
     * 删除
     * @author 王杰
     */
    @RequestMapping(value = "/20101040",method = RequestMethod.POST)
    public ModelAndView toDelete20101040(U101010UserInfoDBO u101010UserInfoDBO,RESTResultBean<String> message){
        U101010UserInfoService_.toDelete(u101010UserInfoDBO);
        message.setCode(ONE);
        message.setMsg(MESSAGE_DB_DELETE);
        return doSelectPage20101010(new U101010UserInfoDBO(),new PageModel<U101010UserInfoDBO>(),message);
    }

    /**
     * 用户状态修改
     * @author 王杰
     */
    @ResponseBody
    @RequestMapping(value = "/20101032",method = RequestMethod.POST)
    public RESTResultBean doUpdate20101032(U101010UserInfoDBO u101010UserInfoDBO,RESTResultBean<String> message){
        U101010UserInfoService_.doUpdate(u101010UserInfoDBO);
        message.setCode(ONE);
        message.setMsg(MESSAGE_DB_UPDATE);
        return message;
    }

    /**
     * 用户昵称验证--AJAX
     * @author 王杰
     */
    @ResponseBody
    @RequestMapping(value = "/20101060",method = RequestMethod.POST)
    public Boolean getNickName20101060(U101010UserInfoDBO u101010UserInfoDBO,String mode,String count){
        List<U101010UserInfoDBO> list=U101010UserInfoService_.doSelectData(u101010UserInfoDBO);
        if(mode.equals(ONE)){
            if(list.size()==0){
                return true;
            }else{
                return  false;
            }
        }else{
            if(list.size()>1){
                return false;
            }else if(list.size()==0){
                return true;
            }else{
                u101010UserInfoDBO=list.get(0);
                if(u101010UserInfoDBO.getPuk().equals(count)){
                    return true;
                }else{
                    return false;
                }
            }
        }
    }
}

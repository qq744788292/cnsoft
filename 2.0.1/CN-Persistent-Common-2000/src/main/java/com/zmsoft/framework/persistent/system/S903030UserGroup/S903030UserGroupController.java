package com.zmsoft.framework.persistent.system.S903030UserGroup;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.support.MyControllerSupport;


/**
 * 系统用户关联用户组信息接口
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/1.0/base/s903030usergroup", method = { RequestMethod.POST})
public class S903030UserGroupController extends MyControllerSupport { //MyTokenCommonSupport
    @Resource
    protected S903030UserGroupBusinesslogic bizS903030UserGroup;

    //private MyModelAndViewSupport model = super.getModelAndView("S903030_user_group");

    //一览（分页查询）
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public RESTResultBean<S903030UserGroupDBO> doList(S903030UserGroupDBO param, PageModel<S903030UserGroupDBO> pageModel) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 设定查询参数
        pageModel.setFormParamBean(param);
        // 开启记录数统计
        //pageModel.setResultCountFlag(true);
        // 分页查询
        return bizS903030UserGroup.doList(pageModel);
    }
    
    //信息详情
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public RESTResultBean<S903030UserGroupDBO> doInfo(S903030UserGroupDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 数据查询
        return bizS903030UserGroup.doInfo(param);
    }
    
    //信息插入
    @RequestMapping(value = "/append", method = RequestMethod.POST)
    public RESTResultBean<String> doAppend(S903030UserGroupDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS903030UserGroup.doAppend(param);
    }
    
    //信息编辑
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public RESTResultBean<String> doModify(S903030UserGroupDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS903030UserGroup.doModify(param);
    }
    
    //信息删除
    @RequestMapping(value = "/discard", method = RequestMethod.POST)
    public RESTResultBean<String> doDiscard(S903030UserGroupDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS903030UserGroup.doDiscard(param);
    }
}

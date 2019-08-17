package com.zmsoft.framework.persistent.system.S902040GroupPermission;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.support.MyControllerSupport;


/**
 * 用户组菜单权限表接口
 * @version 0.1.1
 * @since 0.1.1
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/1.0/base/s902040grouppermission", method = { RequestMethod.POST})
public class S902040GroupPermissionController extends MyControllerSupport { //MyTokenCommonSupport
    @Resource
    protected S902040GroupPermissionBusinesslogic bizS902040GroupPermission;

    //private MyModelAndViewSupport model = super.getModelAndView("S902040_group_permission");

    //一览（分页查询）
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public RESTResultBean<S902040GroupPermissionDBO> doList(S902040GroupPermissionDBO param, PageModel<S902040GroupPermissionDBO> pageModel) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 设定查询参数
        pageModel.setFormParamBean(param);
        // 开启记录数统计
        //pageModel.setResultCountFlag(true);
        // 分页查询
        return bizS902040GroupPermission.doList(pageModel);
    }
    
    //信息详情
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public RESTResultBean<S902040GroupPermissionDBO> doInfo(S902040GroupPermissionDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 数据查询
        return bizS902040GroupPermission.doInfo(param);
    }
    
    //信息插入
    @RequestMapping(value = "/append", method = RequestMethod.POST)
    public RESTResultBean<String> doAppend(S902040GroupPermissionDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS902040GroupPermission.doAppend(param);
    }
    
    //信息编辑
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public RESTResultBean<String> doModify(S902040GroupPermissionDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS902040GroupPermission.doModify(param);
    }
    
    //信息删除
    @RequestMapping(value = "/discard", method = RequestMethod.POST)
    public RESTResultBean<String> doDiscard(S902040GroupPermissionDBO param) throws Exception {
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        return bizS902040GroupPermission.doDiscard(param);
    }
}

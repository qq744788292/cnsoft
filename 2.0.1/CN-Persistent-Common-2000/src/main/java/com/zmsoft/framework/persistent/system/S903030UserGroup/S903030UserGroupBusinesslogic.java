package com.zmsoft.framework.persistent.system.S903030UserGroup;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.constants.ECodeMessageConstants;
import org.zmsoft.framework.support.MyBusinessLogicSupport;

/** 系统用户关联用户组信息*/
@Component("S903030UserGroupBusinesslogic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S903030UserGroupBusinesslogic extends MyBusinessLogicSupport {
    @Resource
    private S903030UserGroupDao daoS903030UserGroup;

    //获得API地址
    public String loadApiDocName() throws Exception {
        return "系统用户关联用户组信息;/doc/1.0/base/s903030usergroup/all";
    }
    
    //一览（分页查询）
    public RESTResultBean<S903030UserGroupDBO> doList(PageModel<S903030UserGroupDBO> pageModel) {
        // 定义返回结果集合
        RESTResultBean<S903030UserGroupDBO> result = new RESTResultBean<S903030UserGroupDBO>();
        // 分页查询
        result.setData(daoS903030UserGroup.doSelectPage(pageModel));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息详情
    public RESTResultBean<S903030UserGroupDBO> doInfo(S903030UserGroupDBO param) {
        // 定义返回结果集合
        RESTResultBean<S903030UserGroupDBO> result = new RESTResultBean<S903030UserGroupDBO>();
        // 查询详情
        result.setData(daoS903030UserGroup.doRead(param));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息插入
    public RESTResultBean<String> doAppend(S903030UserGroupDBO param) {
        // 定义返回结果集合
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS903030UserGroup.doInsert(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_INSERT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息编辑
    public RESTResultBean<String> doModify(S903030UserGroupDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS903030UserGroup.doUpdate(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_UPDATE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息删除
    public RESTResultBean<String> doDiscard(S903030UserGroupDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        daoS903030UserGroup.doDelete(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_DELETE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }

}

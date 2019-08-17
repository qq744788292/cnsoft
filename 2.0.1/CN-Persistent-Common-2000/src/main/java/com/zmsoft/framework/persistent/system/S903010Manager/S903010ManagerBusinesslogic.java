package com.zmsoft.framework.persistent.system.S903010Manager;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.constants.ECodeMessageConstants;
import org.zmsoft.framework.support.MyBusinessLogicSupport;

/** 系统管理用户表*/
@Component("S903010ManagerBusinesslogic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S903010ManagerBusinesslogic extends MyBusinessLogicSupport {
    @Resource
    private S903010ManagerDao daoS903010Manager;

    //获得API地址
    public String loadApiDocName() throws Exception {
        return "系统管理用户表;/doc/1.0/base/s903010manager/all";
    }
    
    //一览（分页查询）
    public RESTResultBean<S903010ManagerDBO> doList(PageModel<S903010ManagerDBO> pageModel) {
        // 定义返回结果集合
        RESTResultBean<S903010ManagerDBO> result = new RESTResultBean<S903010ManagerDBO>();
        // 分页查询
        result.setData(daoS903010Manager.doSelectPage(pageModel));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息详情
    public RESTResultBean<S903010ManagerDBO> doInfo(S903010ManagerDBO param) {
        // 定义返回结果集合
        RESTResultBean<S903010ManagerDBO> result = new RESTResultBean<S903010ManagerDBO>();
        // 查询详情
        result.setData(daoS903010Manager.doRead(param));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息插入
    public RESTResultBean<String> doAppend(S903010ManagerDBO param) {
        // 定义返回结果集合
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS903010Manager.doInsert(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_INSERT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息编辑
    public RESTResultBean<String> doModify(S903010ManagerDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS903010Manager.doUpdate(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_UPDATE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息删除
    public RESTResultBean<String> doDiscard(S903010ManagerDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        daoS903010Manager.doDelete(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_DELETE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }

}

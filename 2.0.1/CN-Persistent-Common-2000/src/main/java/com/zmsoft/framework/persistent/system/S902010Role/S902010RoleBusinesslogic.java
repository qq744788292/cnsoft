package com.zmsoft.framework.persistent.system.S902010Role;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.constants.ECodeMessageConstants;
import org.zmsoft.framework.support.MyBusinessLogicSupport;

/** 系统角色定义表*/
@Component("S902010RoleBusinesslogic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S902010RoleBusinesslogic extends MyBusinessLogicSupport {
    @Resource
    private S902010RoleDao daoS902010Role;

    //获得API地址
    public String loadApiDocName() throws Exception {
        return "系统角色定义表;/doc/1.0/base/s902010role/all";
    }
    
    //一览（分页查询）
    public RESTResultBean<S902010RoleDBO> doList(PageModel<S902010RoleDBO> pageModel) {
        // 定义返回结果集合
        RESTResultBean<S902010RoleDBO> result = new RESTResultBean<S902010RoleDBO>();
        // 分页查询
        result.setData(daoS902010Role.doSelectPage(pageModel));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息详情
    public RESTResultBean<S902010RoleDBO> doInfo(S902010RoleDBO param) {
        // 定义返回结果集合
        RESTResultBean<S902010RoleDBO> result = new RESTResultBean<S902010RoleDBO>();
        // 查询详情
        result.setData(daoS902010Role.doRead(param));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息插入
    public RESTResultBean<String> doAppend(S902010RoleDBO param) {
        // 定义返回结果集合
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS902010Role.doInsert(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_INSERT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息编辑
    public RESTResultBean<String> doModify(S902010RoleDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS902010Role.doUpdate(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_UPDATE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息删除
    public RESTResultBean<String> doDiscard(S902010RoleDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        daoS902010Role.doDelete(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_DELETE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }

}

package com.zmsoft.framework.persistent.system.S902040GroupPermission;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.page.PageModel;
import org.zmsoft.framework.constants.ECodeMessageConstants;
import org.zmsoft.framework.support.MyBusinessLogicSupport;

/** 用户组菜单权限表*/
@Component("S902040GroupPermissionBusinesslogic")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S902040GroupPermissionBusinesslogic extends MyBusinessLogicSupport {
    @Resource
    private S902040GroupPermissionDao daoS902040GroupPermission;

    //获得API地址
    public String loadApiDocName() throws Exception {
        return "用户组菜单权限表;/doc/1.0/base/s902040grouppermission/all";
    }
    
    //一览（分页查询）
    public RESTResultBean<S902040GroupPermissionDBO> doList(PageModel<S902040GroupPermissionDBO> pageModel) {
        // 定义返回结果集合
        RESTResultBean<S902040GroupPermissionDBO> result = new RESTResultBean<S902040GroupPermissionDBO>();
        // 分页查询
        result.setData(daoS902040GroupPermission.doSelectPage(pageModel));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息详情
    public RESTResultBean<S902040GroupPermissionDBO> doInfo(S902040GroupPermissionDBO param) {
        // 定义返回结果集合
        RESTResultBean<S902040GroupPermissionDBO> result = new RESTResultBean<S902040GroupPermissionDBO>();
        // 查询详情
        result.setData(daoS902040GroupPermission.doRead(param));
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_SELECT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
         return result;
    }
    
    //信息插入
    public RESTResultBean<String> doAppend(S902040GroupPermissionDBO param) {
        // 定义返回结果集合
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS902040GroupPermission.doInsert(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_INSERT);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息编辑
    public RESTResultBean<String> doModify(S902040GroupPermissionDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 信息插入
        daoS902040GroupPermission.doUpdate(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_UPDATE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }
    
    //信息删除
    public RESTResultBean<String> doDiscard(S902040GroupPermissionDBO param) {
        // 定义返回结果
        RESTResultBean<String> result = new RESTResultBean<String>();
        // 输出参数日志
        logger.debug("param=====>>>>" + param.toString());
        // 信息插入
        daoS902040GroupPermission.doDelete(param);
        // 设定提示信息
        result.setResult(ECodeMessageConstants.MESSAGE_DB_DELETE);
        // 输出结果日志
        logger.debug("result=====>>>>" + result.toString());
        return result;
    }

}
